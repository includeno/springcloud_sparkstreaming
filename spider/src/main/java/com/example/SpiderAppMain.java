package com.example;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient//zookeeper
@SpringBootApplication
public class SpiderAppMain {


    public static boolean isLinux() {
        return System.getProperty("os.name").toLowerCase().contains("linux");
    }

    public static boolean isWindows() {
        return System.getProperty("os.name").toLowerCase().contains("windows");
    }

    public static boolean isMac() {
        return System.getProperty("os.name").toLowerCase().contains("mac");
    }

    public static void main(String[] args) {

        //chromedriver http://npm.taobao.org/mirrors/chromedriver/
        if(isLinux()){
            System.setProperty("webdriver.chrome.driver", "/tools/chromedriver");//linux
        }
        if(isWindows()){
            System.setProperty("webdriver.chrome.driver", "C:\\EnvironmentSoftwares\\chromedriver_win32\\chromedriver.exe");//windows
        }
        if(isMac()){
            System.setProperty("webdriver.chrome.driver", "chromedriver");//mac
        }

        SpringApplication.run(SpiderAppMain.class, args);
    }

}
