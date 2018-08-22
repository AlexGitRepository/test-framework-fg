package core.webactions;

import core.utils.Time;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class WebDriverActions {

    static final Logger LOGGER = LogManager.getLogger(WebDriverActions.class);

    protected WebDriver driver;
    protected WebDriverWait driverWait;

    protected int timeout = 5;

    public WebDriverActions(WebDriver driver){
        this.driver = driver;
        driverWait= new WebDriverWait(driver, timeout);
    }

    private void guide(){
        /** for work with PAGE**/
        get("url");
        getCurrentUrl();
        getPageSource();
        getTitle();

        /** for finding WebElement **/
        findElement(By.xpath(".//"));
        findElements(By.xpath(".//"));

        /** for work wih windows**/
        close();
        quit();
        getWindowHandle();
        getWindowHandles();

    }

    /****************************************************************************************
     * Methods of WebDriver Interface
     * https://seleniumhq.github.io/selenium/docs/api/java/org/openqa/selenium/WebDriver.html
     ****************************************************************************************/

    /**
     * @param locator The locating mechanism
     * @return The first matching element on the current page
     * @throws NoSuchElementException If no matching elements are found
     **/
    public WebElement findElement(By locator){
        LOGGER.info(String.format("find element by locator: %s"), locator.toString());
        try{
            return driver.findElement(locator);
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /**
     * @param locator The locating mechanism to use
     * @return A list of all {@link WebElement}s, or an empty list if nothing matches
     **/
    public List<WebElement> findElements(By locator){
        LOGGER.info(String.format("find all elements by locator: %s"), locator.toString());
        return driver.findElements(locator);
    }

    /**
     * Load a new web page in the current browser window.
     * @param url The URL to load. It is best to use a fully qualified URL
     **/
    public void get(String url){
        LOGGER.info(String.format("Opening url: %s", url));
        driver.get(url);
    }

    /**
     * Get a string representing the current URL that the browser is looking at.
     * @return The URL of the page currently loaded in the browser
     */
    public String getCurrentUrl(){
        LOGGER.info("get current url");
        return driver.getCurrentUrl();
    }

    /**
     * Get the source of the last loaded page.
     * @return The source of the current page
     */
    public String getPageSource(){
        LOGGER.info("get page source");
        return driver.getPageSource();
    }

    /**
     * The title of the current page.
     * @return The title of the current page, with leading and trailing whitespace stripped, or null. if one is not already set
     */
    public String getTitle(){
        LOGGER.info("get title of page");
        return driver.getTitle();
    }

    /**
     * Return an opaque handle to this window that uniquely identifies it within this driver instance.
     * This can be used to switch to this window at a later date
     * @return the current window handle
     **/
    public String getWindowHandle(){
        LOGGER.info("get window handle");
        return driver.getWindowHandle();
    }

    /**
     * Return a set of window handles which can be used to iterate over all open windows of this
     * @return A set of window handles which can be used to iterate over all open windows.
     */
    public Set<String> getWindowHandles(){
        LOGGER.info("get all window handles");
        return driver.getWindowHandles();
    }

    /**
     * Quits this driver, closing every associated window.
     **/
    public void quit(){
        LOGGER.info(String.format("Close browser: %s", driver.toString()));
        driver.quit();
    }

    /**
     * Close the current window, quitting the browser if it's the last window currently open.
     **/
    public void close(){
        LOGGER.info(String.format("Close window: %s", driver.getWindowHandle()));
        driver.close();
    }

    /**
     * @param milliseconds
     */
    public void pause(int milliseconds){
        try {
            LOGGER.info(String.format("pause for %s milliseconds", milliseconds));
            Thread.sleep(TimeUnit.MILLISECONDS.toMillis(milliseconds));
        } catch (InterruptedException e) {
            LOGGER.error("Errors happened when executing pause() method", e);
        }
    }

    public void makeScreenshot() {
        try {
            byte[] screenshotBytes = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
            File screenShotFile = new File("test-output/html/screenshoots", "screenshot-" + Time.timeStampDetailed() + ".png");
            FileUtils.writeByteArrayToFile(screenShotFile, screenshotBytes);
            LOGGER.info("Taken screenshoot <a href='screenshoots/" + screenShotFile.getName() + "'>" + screenShotFile.getName()
                    + "</a>");
        } catch (Exception e) {
            LOGGER.error("Error during taking screenshoot", e);
        }
    }

}
