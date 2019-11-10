package com.work.confluence.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AddPagePO<M extends WebElement> extends BrowserBasePO {
    WebDriver driver;

    @FindBy(id = "myApp_exception")
    protected M error;

    @FindBy(xpath = "//div[@class='EditorTitle_root_2Gy']/textarea")
    protected WebElement pageTitleField;

    @FindBy(id = "close-button")
    protected WebElement closeButton;

    @FindBy(id = "publish-button")
    protected WebElement publishButton;

    @FindBy(id = "title-text")
    protected WebElement pageTitleHeading;

    @Override
    public void setElementWait(int elementWait) {

    }

    @Override
    public int getElementWait() {
        return 0;
    }

    @Override
    public void setPageTitle(String pageTitle) {

    }

    @Override
    public String getPageTitle() {
        return null;
    }

    // constructor
    public AddPagePO(WebDriver driver) throws Exception {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
