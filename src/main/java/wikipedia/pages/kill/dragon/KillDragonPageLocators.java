package wikipedia.pages.kill.dragon;

public class KillDragonPageLocators {

    //use format: public static final String block1_block2_elementName = ".//";
    public static final String articleImage = ".//img[@alt='To Kill a Dragon.jpg']";
    public static final String articleTitle = ".//h1[@id='firstHeading']/i[text()='To Kill a Dragon']";
    public static final String castListAuthors = ".//span[@id='Cast']/../following-sibling::*[1]//a";
}
