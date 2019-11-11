package com.work.confluence.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePagePO<M extends WebElement> extends BrowserBasePO {
    @FindBy(id = "myApp_exception")
    public M error;
    @FindBy(id = "productLogoGlobalItem")
    public WebElement confluenceProductLogo;
    @FindBy(xpath = "//div[@data-testid='GlobalNavigation']")
    public WebElement globalNavigationBarIcon;
    @FindBy(id = "createGlobalItem")
    public WebElement createGlobalItemButton;
    @FindBy(xpath = "//*[@id='create-dialog']/div/h2")
    public WebElement headingCreateDialog;
    @FindBy(xpath = "//span[@class='template-preview icon-blank-page-large']")
    public WebElement spanCrateBlankPage;
    @FindBy(xpath = "//button[@data-test-id='create-dialog-create-button']")
    public WebElement createButtonOnDialog;
    @FindBy(xpath = "//div[@class='css-ps7n2c']/img")
    public WebElement userSpace;
    WebDriver driver;

    //constructor
    public HomePagePO(WebDriver driver) throws Exception {
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
