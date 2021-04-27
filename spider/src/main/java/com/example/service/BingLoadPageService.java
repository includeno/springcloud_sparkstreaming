package com.example.service;

import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class BingLoadPageService implements SeleniumServiceInterface {
    @Autowired
    WebDriver driver;
    //https://cn.bing.com/search?q=ps4&first=1&FORM=PERE
    //https://cn.bing.com/search?q=ps4&first=11&FORM=PORE
    //https://cn.bing.com/search?q=ps4&ensearch=1&FORM=BESBTB

    @Override
    public ArrayList<String> searchKeyword(String keyword, int page) {
        OkHttpClient client = new OkHttpClient();
        //页面加载超时时间设置为 30s
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
        String baselink = "https://www.bing.com/?mkt=zh-CN";
        driver.get(baselink);

        Integer random = new Random(9).nextInt();
        try {
            TimeUnit.MILLISECONDS.sleep(1072 + random * 123);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //显式等待， 针对某个元素等待
        WebDriverWait wait = new WebDriverWait(driver, 30, 1);
        WebElement searchInput = wait.until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver text) {
                return text.findElement(By.id("sb_form_q"));
            }
        });
        searchInput.sendKeys(keyword);
        {
            //确认按钮 id sb_form_go
            WebElement submitButton = driver.findElement(By.id("sb_form_go"));
            submitButton.click();
            log.info("当前位置:" + driver.getCurrentUrl());
        }

        ArrayList<String> reallinks = new ArrayList<>();
        for (int i = 0; i < page; i++) {

            try {
                TimeUnit.MILLISECONDS.sleep(234 * random + 1350);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            List<WebElement> links = driver.findElements(By.className("b_algo"));

            for (WebElement element : links) {
                WebElement classa = element.findElement(By.tagName("a"));
                String href = classa.getAttribute("href");
                reallinks.add(href);
            }
            log.info(reallinks.toString());
            log.info("当前位置:" + driver.getCurrentUrl());

            //翻页 class sb_pagN
            WebElement nextPage = driver.findElement(By.className("sb_pagN"));
            nextPage.click();
        }

        return reallinks;
    }
}
