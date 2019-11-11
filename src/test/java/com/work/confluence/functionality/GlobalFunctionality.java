package com.work.confluence.functionality;

import com.work.confluence.pageObjects.AddPagePO;
import com.work.confluence.pageObjects.HomePagePO;
import com.work.confluence.pageObjects.UserPagePO;
import com.work.framework.base.DriverBase;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Class consists of global functionality applicable to the application
 */
public class GlobalFunctionality {
    WebDriver driver = DriverBase.getInstance().getCurrentDriver();
    HomePagePO homePagePO = new HomePagePO(driver);
    AddPagePO addPagePO = new AddPagePO(driver);
    UserPagePO userPagePO = new UserPagePO(driver);
    Logger logger = LoggerFactory.getLogger(GlobalFunctionality.class);

    // constructor
    public GlobalFunctionality() throws Exception {
    }

    /**
     * createBlankPage - method to create a blank page with the given title
     *
     * @throws Exception
     */
    public void createBlankPage(String title) throws Exception {
        // generate a random title based on time stamp
        if (title.equalsIgnoreCase("random")) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            title = "Title_" + sdf.format(timestamp.getTime());
        }

        TestUtils.waitFor(homePagePO.globalNavigationBarIcon, 5);

        // click on create button
        TestUtils.waitFor(homePagePO.createGlobalItemButton, 2);
        homePagePO.createGlobalItemButton.click();

        TestUtils.waitFor(homePagePO.headingCreateDialog, 5);

        // click on blank page button on dialog
        TestUtils.waitFor(homePagePO.spanCrateBlankPage, 2);
        homePagePO.spanCrateBlankPage.click();

        // click on create button on dialog
        TestUtils.waitFor(homePagePO.createButtonOnDialog, 1);
        homePagePO.createButtonOnDialog.click();

        TestUtils.waitFor(addPagePO.pageTitleField, 5);

        // clear title text field
        if (!addPagePO.pageTitleField.getAttribute("value").equals("")) {
            addPagePO.pageTitleField.clear();
        }
        addPagePO.pageTitleField.click();

        // enter page title
        DriverBase.getInstance().driverWait(5);
        addPagePO.pageTitleField.sendKeys(title);

        // click on publish button
        addPagePO.publishButton.click();

        TestUtils.waitFor(addPagePO.pageTitleHeading, 5);
        String pageHeading = addPagePO.pageTitleHeading.getText();

        Assert.assertEquals(title, pageHeading);
    }

    /**
     * navigateToUserSpace method to navigate to the User Space
     *
     * @param userSpace
     */
    public void navigateToUserSpace(String userSpace) {
        // click on global confluence logo
        TestUtils.waitFor(homePagePO.globalNavigationBarIcon, 5);
        homePagePO.globalNavigationBarIcon.click();

        // click on user space
        TestUtils.waitFor(homePagePO.userSpace, 5);
        homePagePO.userSpace.click();

        TestUtils.waitFor(userPagePO.titleUserSpace, 5);
        Assert.assertEquals(userPagePO.titleUserSpace.getText(), userSpace);
    }

    /**
     * goToExistingPage method to open an existing page from the User Space
     *
     * @param pageNumber
     * @throws Exception
     */
    public void goToExistingPage(int pageNumber) throws Exception {
        // check if there are no pages available and create one if none exists
        if (!userPagePO.pagesExist()) {
            createBlankPage("random");
        }

        // load existing pages if available
        List<WebElement> pages = userPagePO.getPagesList();
        logger.info("Pages under User Space --> " + pages.size());
        DriverBase.getInstance().driverWait(2);

        // click on the given page from the list
        if (pages.size() > 0) {
            pages.get(pageNumber - 1).click();
        }
    }
}
