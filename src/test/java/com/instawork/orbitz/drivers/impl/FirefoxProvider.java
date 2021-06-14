package com.instawork.orbitz.drivers.impl;

import com.instawork.orbitz.drivers.WebDriverProvider;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class FirefoxProvider implements WebDriverProvider {
    WebDriver driver = null;
    @Override
    public WebDriver createDriver() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        return driver;
    }
}
