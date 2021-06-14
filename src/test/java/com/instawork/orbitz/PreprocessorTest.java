package com.instawork.orbitz;

import com.instawork.orbitz.drivers.impl.ChromeProvider;
import com.instawork.orbitz.drivers.impl.FirefoxProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class PreprocessorTest {
    public static WebDriver driver = null;
    private Properties prop;
    private final String propertyFilePath= "src/main/resources/Environment.properties";
    public static String url;
    public static String flying_from;
    public static String flying_to;

    @Parameters({ "browser" })
    @BeforeTest
    public void startUp(String browser)
    {
        readConstants();
        if(browser.equalsIgnoreCase("chrome")) {
            driver = new ChromeProvider().createDriver();
        }
        else if(browser.equalsIgnoreCase("firefox")){
            driver = new FirefoxProvider().createDriver();
        }else {
            driver = new ChromeProvider().createDriver();
        }
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    public static WebDriver getDriver()
    {
        return driver;
    }

    @AfterTest
    public void tearDown()
    {
        driver.quit();
    }

    public  void readConstants()
    {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            prop = new Properties();
            try {
                prop.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Environment.properties not found at " + propertyFilePath);
        }
        if(prop.getProperty("url")!= null)
            url= prop.getProperty("url");
        if(prop.getProperty("flying_from")!= null)
            flying_from= prop.getProperty("flying_from");
        if(prop.getProperty("flying_to")!= null)
            flying_to= prop.getProperty("flying_to");
    }
}
