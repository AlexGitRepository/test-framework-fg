package features.article.killdragon.validation.cast;

import features.BaseInitialisation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;

public class SuiteValidateAuthor extends BaseInitialisation {

    static final Logger LOGGER = LogManager.getLogger(SuiteValidateAuthor.class);

    @BeforeClass
    public void beforeSuiteActions(){
        LOGGER.info("==================================");
        LOGGER.info("Start suite \"Suite Validate Cast section\"");
        LOGGER.info("==================================");
    }

    @AfterClass
    public void afterSuiteActions(){
        LOGGER.info("==================================");
        LOGGER.info("Finish suite \"Suite Validate Cast section\"");
        LOGGER.info("==================================");
    }

    @BeforeMethod
    public void beforeTestActions(){
        LOGGER.info("----------------------------------");
    }

    @AfterMethod
    public void afterTestActions(){
        LOGGER.info("----------------------------------");
    }


    @Test(testName = "Validate Authors in Cast section", enabled = true)
    public void successfulLogin() {
        LOGGER.info("*** Start test \"Validate Authors in Cast section\" ***");
        wikiApp.killDragonPage.openPage();
        wikiApp.killDragonPage.waitPageLoaded();
        List<String> authorsList = wikiApp.killDragonPage.getCastListUrls();

        for (String authorPage: authorsList){
            web.get(authorPage);
            Assert.assertTrue(wikiApp.authorPage.isFilmPresentInFilmography(wikiApp.killDragonPage.getArticleTitle()));
        }


        LOGGER.info("*** Finish test \"Validate Authors in Cast section\" ***");
    }
}
