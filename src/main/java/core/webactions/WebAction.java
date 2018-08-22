package core.webactions;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Set;

public class WebAction extends WebDriverActions{

    static final Logger LOGGER = LogManager.getLogger(WebAction.class);

    public WebAction(WebDriver driver){
        super(driver);
    }


    private void guide(){

        /** for work wih windows**/
        get("url");                                 //parent
        getCurrentUrl();                            //parent
        getPageSource();                            //parent
        getTitle();                                 //parent
        isUrlContains("url");
        isUrlEquals("url");
        close();                                    //parent
        quit();                                     //parent
        refresh();
        navigateToBack();
        navigateToForward();
        windowMaximize();
        getWindowHandle();                          //parent
        getWindowHandles();                         //parent
        switchToWindow("window handle");

        /** for work wih frames**/
        switchToDefaultContent();
        switchToFrame(By.xpath(".//iframe"));

        /** for input elements **/
        clearValue(By.xpath(".//"));
        setValue("some value", By.xpath(".//"));
        clearAndSetValue("some value", By.xpath(".//"));
        getValue(By.xpath(".//"));
        getText(By.xpath(".//"));

        /** for clicking **/
        click(By.xpath(".//"));

        /** other **/
        deleteAllCookies();
        findElement(By.xpath(".//"));                 //parent
        findElements(By.xpath(".//"));                //parent
        getAttribute(By.xpath(".//"), "attribute name");
        waitIsElementPresented(By.xpath(".//"));
        waitIsElementVisible(By.xpath(".//"));
        waitElementVisible(By.xpath(".//"));
        waitElementPresented(By.xpath(".//"));
        waitElementNotVisible(By.xpath(".//"));
        waitElementNotPresented(By.xpath(".//"));
        pause(1000);                        //parent
        makeScreenshot();                             //parent
    }

    /** There are some preconditions for an element to be clicked. The
     * element must be visible and it must have a height and width
     * greater then 0.
     * @throws StaleElementReferenceException If the element no
     * longer exists as initially defined
     * If element not found throw "TimeoutException" **/
//    public void click(By locator){
//        LOGGER.info(String.format("Click on element: %s", locator.toString()));
//        try{
//            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).click();
//        }catch (Exception e){
//            makeScreenshot();
//            throw e;
//        }
//    }

    public void click(By locator){
        LOGGER.info(String.format("Click on element: %s", locator.toString()));
        try{
            driverWait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /**  If element not found throw "TimeoutException"  **/
    public void clearAndSetValue(String data, By locator){
        clearValue(locator);
        setValue(data, locator);
    }

    /** If field cleared - command passed
     *  If element not main-editable throw "InvalidElementStateException"
     *  If element not found throw "TimeoutException"  **/
    public void clearValue(By locator){
        LOGGER.info(String.format("clear value in field: %s", locator.toString()));
        try{
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).clear();
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /**  If element not found throw "TimeoutException"  **/
    public void setValue(String data, By locator){
        LOGGER.info(String.format("set value in field: %s", locator.toString()));
        try{
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator)).sendKeys(data);
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /**@param url
     * @return boolean tru or false
     */
    public boolean isUrlContains(String url){
        LOGGER.info(String.format("is current url contains \"%s\"", url));
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.contains(url);
    }

    /**@param url
     * @return boolean tru or false
     */
    public boolean isUrlEquals(String url){
        LOGGER.info(String.format("is current url equals to \"%s\"", url));
        String currentUrl = driver.getCurrentUrl();
        return currentUrl.equals(url);
    }

    /** The attribute/property's current value or null if the value is not set.
     * @param locator
     * @param attributeName The name of the attribute
     * @return The attribute/property's current value or null if the value is not set.
     * @throws TimeoutException If element not found **/
    public String getAttribute(By locator, String attributeName){
        LOGGER.info(String.format("get attribute %s of element: %s", attributeName, locator.toString()));
        try{
            return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute(attributeName);
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /** not finished
     * @param locator
     * @return the innerText of this element.
     * @throws TimeoutException if element not found throw
     */
    public String getText(By locator){
        LOGGER.info(String.format("get text of element: %s", locator.toString()));
        try{
            return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator)).getText();
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    /**
     * @param locator
     * @return the value of "value" attribute of the element.
     * @throws TimeoutException if element not found throw
     */
    public String getValue(By locator){
        LOGGER.info(String.format("get value of element: %s", locator.toString()));
        try{
            return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator)).getAttribute("value");
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    public boolean waitIsElementPresented(By locator){
        LOGGER.info(String.format("wait is element presented: %s", locator.toString()));
        try{
            driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
            return true;
        } catch (TimeoutException e){
            return false;
        }
    }

    public boolean waitIsElementVisible(By locator){
        LOGGER.info(String.format("wait is element visible: %s", locator.toString()));
        try{
            driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            return true;
        }catch (TimeoutException e){
            return false;
        }
    }

    public WebElement waitElementVisible(By locator){
        try{
            LOGGER.info(String.format("wait visible element: %s", locator.toString()));
            return driverWait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    public WebElement waitElementPresented(By locator){
        try{
            LOGGER.info(String.format("wait presented element: %s", locator.toString()));
            return driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    public void waitElementNotPresented(By locator){
        LOGGER.info(String.format("wait element not presented: %s", locator.toString()));
        for (int i = 0; i <= timeout * 5; i++){
            try {
                driver.findElement(locator);
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            } catch (NoSuchElementException e) {
                return;
            }
        }
        makeScreenshot();
        throw new TimeoutException(String.format("Unexpectedly element %s present on page", locator.toString()));
    }

    public void waitElementNotVisible(By locator) {
        LOGGER.info(String.format("wait element not visible: %s", locator.toString()));
        try {
            driver.findElement(locator);
        } catch (NoSuchElementException e){
            return;
        }
        try{
            driverWait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        }catch (Exception e){
            makeScreenshot();
            throw e;
        }
    }

    public void refresh() {
        LOGGER.info(String.format("refresh page %s", driver.getCurrentUrl()));
        driver.navigate().refresh();
    }

    public void navigateToBack() {
        LOGGER.info("click navigate to back on page");
        driver.navigate().back();
    }

    public void navigateToForward() {
        LOGGER.info("click navigate to forward");
        driver.navigate().forward();
    }

    public WebDriver switchToDefaultContent(){
        LOGGER.info("switching to default content");
        return driver.switchTo().defaultContent();
    }

    public WebDriver switchToFrame(By locator){
        LOGGER.info(String.format("switch to frame %s", locator.toString()));
        WebElement frame;
        try{
            frame = driverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        }catch (Exception e){
            LOGGER.info(String.format("frame %s is not found on page", locator.toString()));
            throw e;
        }
        return driver.switchTo().frame(frame);
    }

    public WebDriver switchToWindow(String nameOrHandle){
        LOGGER.info(String.format("switch to window %s", nameOrHandle));

        Set<String> handles = driver.getWindowHandles();
        LOGGER.info("Now opened : " + handles.size() + " windows!");
        for (String window : handles) {
            WebDriver result = driver.switchTo().window(window);
            if (driver.getTitle().contains(nameOrHandle)) {
                LOGGER.info("Switch to window with title: <" + driver.getTitle() + "> which contains : <" + nameOrHandle + ">");
                makeScreenshot();
                return result;
            }else{
                LOGGER.info("Can't switch to window, because current title: <" + this.getTitle() + "> doesn't contains : <" + nameOrHandle + ">");
                makeScreenshot();
            }
        }
        return null;
    }

    public void deleteAllCookies(){
        LOGGER.info("delete all cookies");
        driver.manage().deleteAllCookies();
    }

    public void windowMaximize(){
        LOGGER.info("window maximize");
        driver.manage().window().maximize();
    }

}


