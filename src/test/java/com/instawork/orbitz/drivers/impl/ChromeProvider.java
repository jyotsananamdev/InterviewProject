package com.instawork.orbitz.drivers.impl;

import com.instawork.orbitz.drivers.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class ChromeProvider implements WebDriverProvider {
    WebDriver driver = null;
    @Override
    public WebDriver createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }
}
