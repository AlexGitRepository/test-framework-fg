package core.webdriver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import static core.utils.PropertyLoader.getProperty;

public class WebDriverFactory {

    private static WebDriver webDriver;

    /** valid data:
     *  browserName: chrome, firefox
     *  platform: windows, linux
     *  gridUrl: true, false
     **/

    public static WebDriver getDriver(String location, String platform, String browserName) {
        if (webDriver != null && (((RemoteWebDriver)webDriver).getSessionId() != null)) {
            return webDriver;
        }

        switch (location){
            case "local":
                setLocalInstance(browserName, platform);
                break;
            case "grid":
                break;
            case "browserStack":
                break;
            default:
                throw new IllegalArgumentException(String.format("<%s> running location is not correct", location));
        }
        return webDriver;
    }


    private static void setLocalInstance(String browserName, String platform) {
        switch (browserName) {
            case "chrome":
                setPropertyForChrome(platform);
                ChromeOptions options = new ChromeOptions();
                options.addArguments("--no-sandbox");
                options.addArguments("start-maximized");
                webDriver = new ChromeDriver(options);
                break;
            case "firefox":
                setPropertyForFirefox(platform);
                DesiredCapabilities capabilities = DesiredCapabilities.firefox();
                capabilities.setCapability("marionette", true);
                webDriver = new FirefoxDriver(capabilities);
                webDriver.manage().window().maximize();
                break;
            default:
                throw new IllegalArgumentException(String.format("browser name <%s> is not correct", browserName));
        }
    }

    private static void setPropertyForChrome(String platform) {
        switch (platform) {
            case "windows":
                System.setProperty("webdriver.chrome.driver", getProperty("chrome.driver.path.windows"));
                break;
            case "linux":
                System.setProperty("webdriver.chrome.driver", getProperty("chrome.driver.path.linux"));
                break;
            default:
                throw new IllegalArgumentException(String.format("Platform type <%s> is not correct", platform));
        }
    }

    private static void setPropertyForFirefox(String platform) {
        switch (platform) {
            case "windows":
                System.setProperty("webdriver.gesco.driver", getProperty("gecko.driver.path.windows"));
                break;
            case "linux":
                System.setProperty("webdriver.gesco.driver", getProperty("gecko.driver.path.linux"));
                break;
            default:
                throw new IllegalArgumentException(String.format("Platform type <%s> is not correct", platform));
        }
    }

}