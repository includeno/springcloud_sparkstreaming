package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/tools/chromedriver");//linux
        SpringApplication.run(AppMain.class, args);
    }

}
