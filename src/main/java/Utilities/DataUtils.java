package Utilities;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class DataUtils {

    private static final String Test_Data_path = "src/test/resources/TestData/";
    //TODO : READING DATA FROM JSON FILE
    public static String getJsonData(String FileName , String field)  {
        try {
            FileReader reader = new FileReader(Test_Data_path+ FileName + ".json");
            JsonElement jsonElement = JsonParser.parseReader(reader);
            return jsonElement.getAsJsonObject().get(field).getAsString();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return "";

    }
    //TODO : READING DATA FROM PROPERTIES FILE
    public static String getPropertyValue(String FileName , String key) throws IOException {
        Properties properties = new Properties() ;
        properties.load(new FileInputStream(Test_Data_path+ FileName + ".properties"));
        return properties.getProperty(key);
    }
}
