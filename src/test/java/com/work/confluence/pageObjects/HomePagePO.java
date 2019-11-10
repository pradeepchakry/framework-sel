package com.work.confluence.pageObjects;

import com.work.framework.utils.Global_VARS;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class HomePagePO<M extends WebElement> extends BrowserBasePO {
    WebDriver driver;

    @FindBy(id = "myApp_exception")
    protected M error;

    @FindBy(id = "productLogoGlobalItem")
    protected WebElement confluenceProductLogo;

    @FindBy(xpath = "//div[@data-testid='GlobalNavigation']")
    protected WebElement globalNavigationBarIcon;

    @FindBy(id = "createGlobalItem")
    protected WebElement createGlobalItemButton;

    @FindBy(xpath = "//*[@id='create-dialog']/div/h2")
    protected WebElement headingCreateDialog;

    @FindBy(xpath = "//span[@class='template-preview icon-blank-page-large']")
    protected WebElement spanCrateBlankPage;

    @FindBy(xpath = "//button[@data-test-id='create-dialog-create-button']")
    protected WebElement createButtonOnDialog;

    @FindBy(xpath = "//div[@class='css-ps7n2c']/img")
    protected WebElement userSpace;

    //constructor
    public HomePagePO(WebDriver driver) throws Exception {
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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

}
