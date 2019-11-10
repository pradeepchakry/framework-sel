package com.work.confluence.pageObjects;

import com.work.confluence.pageObjects.AddPagePO;
import com.work.confluence.pageObjects.HomePagePO;
import com.work.confluence.pageObjects.UserPagePO;
import com.work.framework.base.DriverBase;
import com.work.framework.data.PageInfo;
import com.work.framework.utils.Global_VARS;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

public class GlobalFunctionality {
    WebDriver driver = DriverBase.getInstance().getCurrentDriver();
    HomePagePO homePagePO = new HomePagePO(driver);
    AddPagePO addPagePO = new AddPagePO(driver);
    UserPagePO userPagePO = new UserPagePO(driver);
    private PageInfo pageInfo = new PageInfo();

    public GlobalFunctionality() throws Exception {
    }

    /**
     * createBlankPage - method to create a blank page with the given title
     *
     * @throws Exception
     */
    public void createBlankPage() throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        String title = "Title_" + sdf.format(timestamp.getTime());
        pageInfo.setPageTitle(title);
        TestUtils.waitFor(homePagePO.globalNavigationBarIcon, 5);

        TestUtils.waitFor(homePagePO.createGlobalItemButton, 2);

        homePagePO.createGlobalItemButton.click();

        TestUtils.waitFor(homePagePO.headingCreateDialog, 5);

        TestUtils.waitFor(homePagePO.spanCrateBlankPage, 2);

        homePagePO.spanCrateBlankPage.click();

        TestUtils.waitFor(homePagePO.createButtonOnDialog, 1);

        homePagePO.createButtonOnDialog.click();

        TestUtils.waitFor(addPagePO.pageTitleField, 5);

        if ( !addPagePO.pageTitleField.getAttribute( "value" ).equals( "" ) ) {
            addPagePO.pageTitleField.clear();
        }
        addPagePO.pageTitleField.click();

        DriverBase.getInstance().driverWait(5);
        addPagePO.pageTitleField.sendKeys(title);

        addPagePO.publishButton.click();

        TestUtils.waitFor(addPagePO.pageTitleHeading, 5);
        String pageHeading = addPagePO.pageTitleHeading.getText();

        Assert.assertEquals(title, pageHeading);
    }

    /**
     * createBlankPage - method to restrict page edit
     *
     * @param restriction
     * @throws Exception
     */
    public void restrictPageTo(String restriction) throws Exception {
        TestUtils.waitFor(addPagePO.pageTitleHeading, 5);

        TestUtils.waitFor(userPagePO.menuButton, 5);
        userPagePO.menuButton.click();

        TestUtils.waitFor(userPagePO.restrictionsMenuItem, 5);
        userPagePO.restrictionsMenuItem.click();

        TestUtils.waitFor(userPagePO.dialogHeadingRestrictions, 5);
        userPagePO.restrictEditingAndViewing(restriction);

        Assert.assertTrue(TestUtils.elementExists(userPagePO.lockedIcon, 2));
    }

    public void navigateToUserSpace() {
        TestUtils.waitFor(homePagePO.globalNavigationBarIcon, 5);
        homePagePO.globalNavigationBarIcon.click();
        TestUtils.waitFor(homePagePO.userSpace, 5);
        homePagePO.userSpace.click();
        TestUtils.waitFor(userPagePO.titleUserSpace, 5);
    }

    public void gotoExistingPage(int pageNumber) throws Exception {
        if( !userPagePO.pagesExist() ) {
            createBlankPage();
        }
        List<WebElement> pages = userPagePO.getPagesList();

        //go to first page
        pages.get( pageNumber - 1 ).click();
    }
}
