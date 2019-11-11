package com.work.confluence.pageObjects;

import com.work.framework.base.DriverBase;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class UserPagePO<M extends WebElement> extends BrowserBasePO {
    @FindBy(id = "myApp_exception")
    public M error;
    @FindBy(id = "title-text")
    public WebElement titleUserSpace;
    @FindBy(id = "tools-menu-trigger")
    public WebElement menuButton;
    @FindBy(xpath = "//span[text()='Restrictions']")
    public WebElement restrictionsMenuItem;
    @FindBy(xpath = "//span[text()='Restrictions']")
    public WebElement dialogHeadingRestrictions;
    @FindBy(xpath = "//div[@data-test-id='restrictions-value-container']")
    public WebElement dialogRestrictionsDropDown;
    @FindBy(xpath = "//span[text()='Everyone can view, only some can edit.']")
    public WebElement spanEditRestrictedHeading;
    @FindBy(xpath = "//span[text()='Apply']")
    public WebElement dialogApplyButton;
    @FindBy(xpath = "//span[text()='Cancel']")
    public WebElement dialogCancelButton;
    @FindBy(xpath = "//img[@data-test-id='unlocked-icon']")
    public WebElement unlockedIcon;
    @FindBy(xpath = "//img[@data-test-id='locked-icon']")
    public WebElement lockedIcon;
    @FindBy(id = "react-select-2-option-0")
    public WebElement restrictionOption1;
    @FindBy(id = "react-select-2-option-1")
    public WebElement restrictionOption2;
    @FindBy(id = "react-select-2-option-2")
    public WebElement restrictionOption3;
    @FindBy(xpath = "//div[text()= 'Pages']")
    public WebElement pagesLabel;
    WebDriver driver;
    String pagesListLocator = "//div[text()= 'Pages']/following::a";

    public UserPagePO(WebDriver driver) throws Exception {
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

    /**
     * applyPageRestrictions - method to apply given page restrictions
     *
     * @param restriction
     * @throws Exception
     */
    public void applyPageRestrictions(String restriction) throws Exception {
        TestUtils.waitFor(dialogHeadingRestrictions, 5);

        // click on restrictions drop down
        TestUtils.waitFor(dialogRestrictionsDropDown, 5);
        dialogRestrictionsDropDown.click();

        TestUtils.waitFor(restrictionOption1, 2);
        TestUtils.waitFor(restrictionOption2, 2);

        TestUtils.waitFor(restrictionOption3, 2);
        DriverBase.getInstance().driverWait(4);

        // click on the restriction
        if (restriction.equalsIgnoreCase("Anyone can view and edit.")) {
            restrictionOption1.click();
        } else if (restriction.equalsIgnoreCase("Everyone can view, only some can edit.")) {
            restrictionOption2.click();
        } else if (restriction.equalsIgnoreCase("Only some people can view or edit.")) {
            restrictionOption3.click();
        }

        // click on apply button
        dialogApplyButton.click();
        TestUtils.waitFor(titleUserSpace, 4);
    }

    /**
     * getPagesList - method to return all the existing pages under User Space
     *
     * @return
     */
    public List<WebElement> getPagesList() {
        TestUtils.waitFor(pagesLabel, 5);
        return DriverBase.getInstance().getCurrentDriver().findElements(By.xpath(pagesListLocator));
    }

    /**
     * pageExist - method to check if there is already a page exist under User Space
     *
     * @return
     */
    public boolean pagesExist() {
        List<WebElement> pages = getPagesList();
        return pages.size() > 0;
    }
}
