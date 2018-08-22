package features;

import core.utils.PropertyLoader;
import core.webactions.WebAction;
import core.webdriver.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import wikipedia.WikiAppManager;

public class BaseInitialisation {

    public WebAction web;               // WebActions for tests
    public WikiAppManager wikiApp;      // Application actions for tests


    @BeforeClass
    public void initialisation(){
        String location = PropertyLoader.getProperty("location");               // local, grid, browserStack
        String platform = PropertyLoader.getProperty("platform");               // windows or linux
        String browserName = PropertyLoader.getProperty("browser.name");        // chrome, firefox

        WebDriver driver = WebDriverFactory.getDriver(location, platform, browserName);
        web = new WebAction(driver);
        wikiApp = new WikiAppManager(driver);
    }

    @AfterClass
    public void cleaning(){
        web.quit();
    }
}
