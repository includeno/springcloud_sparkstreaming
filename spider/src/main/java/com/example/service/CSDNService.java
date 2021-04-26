package com.example.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class CSDNService {
    @Autowired
    WebDriver driver;

    //class ->time
    public String getTime(){
        WebElement timeElement = driver.findElement(By.className("time"));
        return timeElement.getText();
    }

    //class -> follow-nickName
    public String getAuthor(){
        WebElement authorElement = driver.findElement(By.className("follow-nickName"));
        return authorElement.getText();
    }

    public String getContent(){
        WebElement contentElement = driver.findElement(By.id("article_content"));
        return contentElement.getText();

    }

    public HashMap<String, String> getResult(String url){
        //页面加载超时时间设置为 5s
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        driver.get(url);
        Integer random=new Random(5).nextInt();
        try {
            TimeUnit.MILLISECONDS.sleep(234*random+1000 );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver,30,1);
        wait.until(new ExpectedCondition<WebElement>(){
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("mainBox"));
            }
        });
        HashMap<String, String> map=new HashMap<>();
        map.put("content",getContent());
        map.put("author",getAuthor());
        map.put("time",getTime());
        driver.close();
        return map;
    }
}
