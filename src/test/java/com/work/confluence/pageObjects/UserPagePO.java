package com.work.confluence.pageObjects;

import com.work.framework.base.DriverBase;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.List;

public class UserPagePO<M extends WebElement> extends BrowserBasePO {
    WebDriver driver;

    HomePagePO homePagePO;

    @FindBy(id = "myApp_exception")
    protected M error;

    @FindBy(id = "title-text")
    protected WebElement titleUserSpace;

    @FindBy(id = "tools-menu-trigger")
    protected WebElement menuButton;

    @FindBy(xpath = "//span[text()='Restrictions']")
    protected WebElement restrictionsMenuItem;

    @FindBy(xpath = "//span[text()='Restrictions']")
    protected WebElement dialogHeadingRestrictions;

    @FindBy(xpath = "//div[@data-test-id='restrictions-value-container']")
    protected WebElement dialogRestrictionsDropDown;

    @FindBy(xpath = "//span[text()='Everyone can view, only some can edit.']")
    protected WebElement spanEditRestrictedHeading;

    @FindBy(xpath = "//span[text()='Apply']")
    protected WebElement dialogApplyButton;

    @FindBy(xpath = "//span[text()='Cancel']")
    protected WebElement dialogCancelButton;

    @FindBy(xpath = "//img[@data-test-id='unlocked-icon']")
    protected WebElement unlockedIcon;

    @FindBy(xpath = "//img[@data-test-id='locked-icon']")
    protected WebElement lockedIcon;

    @FindBy(id = "react-select-2-option-0")
    protected WebElement restrictionOption1;

    @FindBy(id = "react-select-2-option-1")
    protected WebElement restrictionOption2;

    @FindBy(id = "react-select-2-option-2")
    protected WebElement restrictionOption3;

    @FindBy(xpath = "//div[text()= 'Pages']")
    protected WebElement pagesLabel;

    String pagesListLocator = "//div[text()= 'Pages']/following::a";

    public UserPagePO(WebDriver driver) throws Exception {
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


    public void restrictEditingAndViewing(String restriction) throws Exception {
        TestUtils.waitFor(dialogHeadingRestrictions, 5);
        TestUtils.waitFor(dialogRestrictionsDropDown, 5);
        dialogRestrictionsDropDown.click();
        TestUtils.waitFor(restrictionOption1, 2);
        TestUtils.waitFor(restrictionOption2, 2);
        TestUtils.waitFor(restrictionOption3, 2);
        DriverBase.getInstance().driverWait(4);


        if( restriction.equalsIgnoreCase("Everyone can view and edit this page.") ) {
            restrictionOption1.click();
        } else if ( restriction.equalsIgnoreCase("Everyone can view, only some can edit.") ) {
            restrictionOption2.click();
        } else if ( restriction.equalsIgnoreCase("Only some people can view or edit.") ) {
            restrictionOption3.click();
        }
//
        dialogApplyButton.click();
        TestUtils.waitFor(titleUserSpace, 4);
    }

    public List<WebElement> getPagesList() {
        TestUtils.waitFor(pagesLabel, 5);
        return DriverBase.getInstance().getCurrentDriver().findElements(By.xpath(pagesListLocator));
    }

    public boolean pagesExist() {
        List<WebElement> pages = getPagesList();
        if ( pages.size() > 0 ) {
            return true;
        } else {
            return false;
        }
    }
}
