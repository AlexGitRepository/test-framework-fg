package wikipedia.pages.kill.dragon;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wikipedia.WikiAppManager;
import wikipedia.pages.AbstractPage;

import java.util.LinkedList;
import java.util.List;

import static core.utils.PropertyLoader.getProperty;
import static wikipedia.pages.kill.dragon.KillDragonPageLocators.*;

public class KillDragonPage extends AbstractPage {

    private WikiAppManager manager;
    private String article = "To Kill a Dragon";

    public  KillDragonPage(WikiAppManager manager, WebDriver driver) {
        super(driver);
        this.manager = manager;
    }

    public void openPage(){
        get(getProperty("application.url") + getProperty("kill.dragon.page.url"));
    }

    public void waitPageLoaded(){
        waitElementVisible(By.xpath(articleImage));
        waitElementVisible(By.xpath(articleTitle));
    }

    public List<String> getCastListUrls(){
        List<String> castAuthorsList = new LinkedList<>();
        waitElementVisible(By.xpath(castListAuthors));

        List<WebElement> elementList = driver.findElements(By.xpath(castListAuthors));
        for(WebElement element: elementList){
            castAuthorsList.add(element.getAttribute("href"));
        }
        return castAuthorsList;
    }

    public String getArticleTitle() {
        return article;
    }
}
