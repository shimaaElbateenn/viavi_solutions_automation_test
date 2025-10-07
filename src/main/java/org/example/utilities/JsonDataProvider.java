package org.example.utilities;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.data.AccountData;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class JsonDataProvider {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    /**
     * Read test data from JSON file in resources folder
     */
    public static Object[][] getAccountData() throws Exception {
        // Read JSON file
        InputStream inputStream = JsonDataProvider.class
                .getClassLoader()
                .getResourceAsStream("testdata/accounts.json");

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
