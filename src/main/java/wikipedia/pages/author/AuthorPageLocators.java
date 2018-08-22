package wikipedia.pages.author;

public class AuthorPageLocators {

    //use format: public static final String block1_block2_elementName = ".//";
    public static final String selectedFilmographiTitle =
            ".//span[@class='mw-headline' and (@id='Selected_filmography' or @id='Filmography')]";
    public static final String filmographyList1 =
            ".//span[@class='mw-headline' and @id='Selected_filmography' or @id='Filmography']/../following-sibling::*[1]//li";
    public static final String filmographyList2 =
            ".//span[@class='mw-headline' and @id='Selected_filmography' or @id='Filmography']/../following-sibling::*[2]//li";
    public static final String filmographyTable =
            ".//span[@class='mw-headline' and @id='Selected_filmography' or @id='Filmography']/../following-sibling::*[1]//i";
    public static final String filmographyBlock =
            ".//span[@class='mw-headline' and @id='Selected_filmography' or @id='Filmography']/../following-sibling::*[1]";
}
