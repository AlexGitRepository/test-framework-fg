package wikipedia;

import org.openqa.selenium.WebDriver;
import wikipedia.pages.author.AuthorPage;
import wikipedia.pages.kill.dragon.KillDragonPage;

public class WikiAppManager {

    public AuthorPage authorPage;
    public KillDragonPage killDragonPage;

    public WikiAppManager(WebDriver driver){
        authorPage = new AuthorPage(this, driver);
        killDragonPage = new KillDragonPage(this, driver);
    }

}
