package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//zookeeper
@SpringBootApplication
public class AppMain {

    public static void main(String[] args) {

        System.setProperty("webdriver.chrome.driver", "/tools/chromedriver");//linux
        SpringApplication.run(AppMain.class, args);
    }

}
