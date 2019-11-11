package com.work.confluence.data;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.testng.annotations.DataProvider;

import java.io.File;

/**
 * Data Provider class to fetch data from json files
 */
public class JSONDataProvider {
    public static String accountInfoFile = "src/test/resources/json/account_info.json";
    public static String pageInfoFile = "src/test/resources/json/page_info.json";

    /**
     * fetchAccountData method to fetch data from account_info.json file
     *
     * @return
     * @throws Exception
     */
    @DataProvider(name = "accountInfo_JSON")
    public static Object[][] fetchAccountData() throws Exception {
        Object[][] result = new Object[1][1];
        ObjectMapper objectMapper = new ObjectMapper();
        AccountInfo accountInfo = objectMapper.readValue(
                new File(accountInfoFile), AccountInfo.class);
        result[0] = new Object[]{accountInfo};
        return result;
    }

    /**
     * fetchPageInfoData method to fetch data from page_info.json file
     *
     * @return
     * @throws Exception
     */
    @DataProvider(name = "pageInfo_JSON")
    public static Object[][] fetchPageInfoData() throws Exception {
        Object[][] result = new Object[1][1];
        ObjectMapper objectMapper = new ObjectMapper();
        PageInfo pageInfo = objectMapper.readValue(
                new File(pageInfoFile), PageInfo.class);
        result[0] = new Object[]{pageInfo};
        return result;
    }
}
