package bankguru;

public class AbstractPageUI {
    public static final String DYNAMIC_LINK = "//ul[@class = 'menusubnav']//a[text()='%s']";
    public static final String DYNAMIC_TEXTBOX_BUTTON_CHECKBOX = "(//textarea | //input)[@name='%s']";
    public static final String DYNAMIC_DROPDOWN_LIST = "//select[@name='%s']";
    public static final String DYNAMIC_ERROR_MESSAGE = "//td[contains(text(),'%s')]//following-sibling::td/label";
    public static final String DYNAMIC_PAGE_TITLE = "//p[@class='heading3' and text()='%s']";
    public static final String DYNAMIC_VALUE_IN_TABLE = "//td[text()='%s']/following-sibling::td";
}
