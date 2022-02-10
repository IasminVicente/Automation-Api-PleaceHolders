package utils;

import java.io.IOException;
import java.security.Key;
import java.util.Properties;

public class PropertiesUltils {

    Properties properties = new Properties();

    public String getProp(String key) throws IOException {
        try{
            if(System.getProperty("env") == null){
                properties.load(getClass().getClassLoader().getResourceAsStream("hom.properties"));
            }else {
                properties.load(getClass().getClassLoader().getResourceAsStream(System.getProperty("env")));
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return properties.getProperty(key);
    }
}
