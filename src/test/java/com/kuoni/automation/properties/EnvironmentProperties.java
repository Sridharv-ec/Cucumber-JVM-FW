package com.kuoni.automation.properties;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class EnvironmentProperties extends Properties {

    static String propFile = "";
    static Properties prop = new Properties();
    private Properties properties = new Properties();

    public EnvironmentProperties(){
        String appPropertiesFile = "";
        String envSiteStr = "";

        envSiteStr = "env-" + System.getProperty("testEnv");
        appPropertiesFile = System.getProperty("user.dir")+"\\src\\test\\resources\\configs\\"+envSiteStr+".properties";
        try {
            prop.load(new FileInputStream(appPropertiesFile));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getValueForProperty(String key){
        return prop.getProperty(key);
    }

}
