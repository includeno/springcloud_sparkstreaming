package com.example.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class GlobalConfig {

    @Bean
    //@Scope("prototype")
    WebDriver getWebDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--no-sandbox");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--disable-dev-shm-usage");

        //设置为 headless 模式 （必须）
        //chromeOptions.addArguments("--headless");
        //设置浏览器窗口打开大小  （非必须）
        //chromeOptions.addArguments("--window-size=1920,1080");
        return new ChromeDriver(chromeOptions);
    }
}
