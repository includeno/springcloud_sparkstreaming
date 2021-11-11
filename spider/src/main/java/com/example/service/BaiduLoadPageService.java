package com.example.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Slf4j
@Service
public class BaiduLoadPageService implements SeleniumServiceInterface {
    @Autowired
    WebDriver driver;

    @Override
    public ArrayList<String> searchKeyword(String keyword, int page) {
        OkHttpClient client = new OkHttpClient();
        Actions action = new Actions(driver);
        //页面加载超时时间设置为 30s
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        String baselink = "https://www.baidu.com/";
        driver.get(baselink);

        Integer random = new Random(9).nextInt();

        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver, 30, 1);
        WebElement searchInput = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("kw"));
            }
        });
        searchInput.sendKeys(keyword);
        try {
            TimeUnit.MILLISECONDS.sleep(1072 + random * 123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        searchInput.sendKeys(Keys.chord(Keys.ENTER));

        log.info("当前位置:" + driver.getCurrentUrl());

        ArrayList<String> reallinks = new ArrayList<>();
        for (int i = 0; i < page; i++) {

            try {
                TimeUnit.MILLISECONDS.sleep(234 * random + 1350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //显式等待， 针对某个元素等待
            wait = new WebDriverWait(driver,30,1);
            wait.until(new ExpectedCondition<WebElement>(){
                @Override
                public WebElement apply(WebDriver text) {
                    return text.findElement(By.id("page"));
                }
            });

            for(int j=0;j<new Random(9).nextInt();j++){

                action.sendKeys(Keys.ARROW_DOWN);
            }
            wait.until(new ExpectedCondition<WebElement>(){
                @Override
                public WebElement apply(WebDriver text) {
                    return text.findElement(By.className("t"));
                }
            });

            List<WebElement> links = driver.findElements(By.className("t"));

            for(WebElement element:links){
                try {
                    WebElement classa = element.findElement(By.tagName("a"));
                    String href=classa.getAttribute("href");


                    Request request = new Request.Builder().url(href).build();
                    try {
                        Response response = client.newCall(request).execute();
                        System.out.println(response.request().url().toString());
                        reallinks.add(response.request().url().toString());
                        response.close();
                    } catch (IOException e) {
                        continue;
                    }
                }
                catch (Exception e) {
                    try {
                        TimeUnit.MILLISECONDS.sleep(2340 * random + 1350);
                    } catch (InterruptedException a) {
                        a.printStackTrace();
                    }
                    continue;
                }
            }

            //翻页 class sb_pagN
            WebElement text = driver.findElement(By.xpath("//a[contains(text(),'下一页')]"));
            text.click();


        }

        return reallinks;
    }
}
