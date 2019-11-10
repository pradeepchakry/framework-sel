package com.work.confluence.pageObjects;

import com.work.framework.utils.Global_VARS;
import com.work.framework.utils.JavaScriptUtils;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class LoginPagePO<M extends WebElement> extends BrowserBasePO {

    WebDriver driver;

    @FindBy(id = "myApp_exception")
    protected M error;

    @FindBy(xpath = "//*[@id='root']/div/div/div/div[2]/div[2]/div/header/div[1]/span/svg")
    protected WebElement confluenceTitle;

    @FindBy(id = "username")
    protected WebElement userNameField;

    @FindBy(id = "login-submit")
    protected WebElement submitButton;

    @FindBy(id = "password")
    protected WebElement passwordField;

    @FindBy(id = "confluence-ui")
    protected WebElement homePageUIDiv;

    //constructor
    public LoginPagePO(WebDriver driver) throws Exception{
        super();
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setElementWait(int elementWait) {

    }

    public int getElementWait() {
        return 0;
    }

    public void setPageTitle(String pageTitle) {

    }

    public String getPageTitle() {
        return null;
    }

    /**
     * login - method to login to app with error handling
     *
     * @param username
     * @param password
     * @throws Exception
     */
    public void login(String username,
                      String password) throws Exception {
        if ( !this.userNameField.getAttribute( "value" ).equals( "" ) ) {
            this.userNameField.clear();
        }
        this.userNameField.sendKeys(username);

        submitButton.click();

        TestUtils.waitFor(passwordField, 5);

        if( !this.passwordField.getAttribute( "value" ).equals( "" ) ) {
            this.passwordField.clear();
        }
        this.passwordField.sendKeys(password);

        submitButton.click();

        TestUtils.waitFor(homePageUIDiv, 10);

        if ( TestUtils.elementExists(error, Global_VARS.TIMEOUT_SECOND) ) {
            String getError = error.getText();
            throw new Exception("Login Failed with error = " + getError);
        }
    }

}
