package com.work.framework.utils;



import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.json.JSONArray;
import org.testng.annotations.DataProvider;

import java.io.FileReader;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class JSONDataProvider {
    public static String dataFile = "";
    public static String testCaseName = "NA";

    @DataProvider(name = "myData_JSON")
    public static Object[][] fetchData(Method method) throws Exception {
        Object rowID, description;
        Object result [][];
        testCaseName = method.getName();

        JsonArray testData = (JsonArray) extractData_JSON(dataFile).get(method.getName());

        List<JsonObject> testDataList = new ArrayList<JsonObject>();

        for( int i = 0; i < testData.size(); i++) {
            testDataList.add((JsonObject) testData.get(i));
        }

        result = new Object[testDataList.size()][testDataList.get(0).size()];
        for ( int i = 0; i < testDataList.size(); i++ ) {
            result[i] = new Object[] {testDataList.get(i)};
        }
        return result;

    }

    public static JsonObject extractData_JSON(String file) throws Exception {
        FileReader reader = new FileReader(file);
        JsonObject jsonObject = new JsonParser().parse(file).getAsJsonObject();

        return jsonObject;
    }
}
