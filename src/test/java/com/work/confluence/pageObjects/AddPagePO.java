package com.work.confluence.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPagePO<M extends WebElement> extends BrowserBasePO {
    @FindBy(id = "myApp_exception")
    public M error;
    @FindBy(xpath = "//div[@class='EditorTitle_root_2Gy']/textarea")
    public WebElement pageTitleField;
    @FindBy(id = "close-button")
    public WebElement closeButton;
    @FindBy(id = "publish-button")
    public WebElement publishButton;
    @FindBy(id = "title-text")
    public WebElement pageTitleHeading;
    WebDriver driver;

    // constructor
    public AddPagePO(WebDriver driver) throws Exception {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @Override
    public int getElementWait() {
        return 0;
    }

    @Override
    public void setElementWait(int elementWait) {

    }

    @Override
    public String getPageTitle() {
        return null;
    }

    @Override
    public void setPageTitle(String pageTitle) {

    }
}
