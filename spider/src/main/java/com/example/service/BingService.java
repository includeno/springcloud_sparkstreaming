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
public class BingService implements SeleniumServiceInterface{
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

        ArrayList<String> urlList=new ArrayList<>();
        String baselink="";
        for(int i=0;i<page;i++){
            int pn = page * 10 + 1;
            baselink="https://cn.bing.com/search?q="+keyword+"&first="+pn+"&FORM=PORE";
            System.out.println("baselink:"+baselink);
            urlList.add(baselink);
        }

        ArrayList<String> reallinks=new ArrayList<>();
        for(String link:urlList){
            driver.get(baselink);
            Integer random = new Random(9).nextInt();
            try {
                TimeUnit.MILLISECONDS.sleep(2072+random*123);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            

            List<WebElement> links = driver.findElements(By.className("b_algo"));

            //TimeUnit.MILLISECONDS.sleep(263*random );
            for(WebElement element:links){
                WebElement classa = element.findElement(By.tagName("a"));
                String href=classa.getAttribute("href");
                reallinks.add(href);

            }
            log.info(reallinks.toString());
        }
        
        return reallinks;
    }
}
