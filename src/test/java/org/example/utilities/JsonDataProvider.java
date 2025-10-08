package org.example.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.AccountData;
import org.testng.annotations.DataProvider;

import java.io.InputStream;

public class JsonDataProvider {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @DataProvider(name = "jsonAccountData")
    public static Object[][] getAccountData() throws Exception {
        // Read JSON file
        InputStream inputStream = JsonDataProvider.class
                .getClassLoader()
                .getResourceAsStream(Constants.JSON_FILE);

        // Convert JSON to AccountData array
        ObjectMapper mapper = new ObjectMapper();
        AccountData[] accounts = mapper.readValue(inputStream, AccountData[].class);

        // Convert to TestNG format
        Object[][] data = new Object[accounts.length][1];
        for (int i = 0; i < accounts.length; i++) {
            data[i][0] = accounts[i];
        }

        return data;
    }

}
