package wikipedia.pages.author;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import wikipedia.WikiAppManager;
import wikipedia.pages.AbstractPage;

import java.util.List;

import static wikipedia.pages.author.AuthorPageLocators.*;

public class AuthorPage extends AbstractPage {

    private WikiAppManager manager;

    public  AuthorPage(WikiAppManager manager, WebDriver driver) {
        super(driver);
        this.manager = manager;
    }


    public boolean isFilmPresentInFilmography(String filmName){
        String locator;
        WebElement filmBlock = waitElementVisible(By.xpath(filmographyBlock));
        if(filmBlock.getTagName().equals("table")){
            locator = filmographyTable;
        } else if(filmBlock.getTagName().equals("dl")){
            locator = filmographyList2;
        } else {
            locator = filmographyList1;
        }

        List<WebElement> elementList = driver.findElements(By.xpath(locator));
        for(WebElement element: elementList){
            if(element.getAttribute("textContent").contains(filmName)){
                return true;
            }
        }
        return false;
    }

}