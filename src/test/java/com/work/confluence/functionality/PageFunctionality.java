package com.work.confluence.functionality;

import com.work.confluence.pageObjects.AddPagePO;
import com.work.confluence.pageObjects.HomePagePO;
import com.work.confluence.pageObjects.UserPagePO;
import com.work.framework.base.DriverBase;
import com.work.framework.utils.TestUtils;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class PageFunctionality {
    WebDriver driver = DriverBase.getInstance().getCurrentDriver();
    HomePagePO homePagePO = new HomePagePO(driver);
    AddPagePO addPagePO = new AddPagePO(driver);
    UserPagePO userPagePO = new UserPagePO(driver);

    // constructor
    public PageFunctionality() throws Exception {

    }

    /**
     * createBlankPage - method to restrict page edit
     *
     * @param restriction
     * @throws Exception
     */
    public void restrictPageTo(String restriction) throws Exception {
        TestUtils.waitFor(addPagePO.pageTitleHeading, 5);

        // click on menu button
        TestUtils.waitFor(userPagePO.menuButton, 5);
        userPagePO.menuButton.click();

        // click on restrictions menu item
        TestUtils.waitFor(userPagePO.restrictionsMenuItem, 5);
        userPagePO.restrictionsMenuItem.click();

        // apply page restrictions by calling applyPageRestrictions method
        TestUtils.waitFor(userPagePO.dialogHeadingRestrictions, 5);
        userPagePO.applyPageRestrictions(restriction);

        Assert.assertTrue(TestUtils.elementExists(userPagePO.lockedIcon, 2));
    }
}
