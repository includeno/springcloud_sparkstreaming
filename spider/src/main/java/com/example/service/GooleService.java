package com.example.service;

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

@Service
public class GooleService implements SeleniumServiceInterface{
    @Autowired
    WebDriver driver;
    //https://cn.bing.com/search?q=ps4&first=1&FORM=PERE
    //https://cn.bing.com/search?q=ps4&first=11&FORM=PORE
    //https://cn.bing.com/search?q=ps4&ensearch=1&FORM=BESBTB
    //https://www.google.com.hk/search?q=ps5&start=10
    @Override
    public ArrayList<String> searchKeyword(String keyword, int page) {
        OkHttpClient client = new OkHttpClient();
        //页面加载超时时间设置为 30s
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);


        ArrayList<String> urlList=new ArrayList<>();
        String baselink="";
        for(int i=0;i<page;i++){
            int pn = page * 10 ;
            baselink="https://www.google.com.hk/search?q="+keyword+"&start="+pn+"";
            urlList.add(baselink);
            System.out.println("baselink:"+baselink);
        }

        ArrayList<String> reallinks=new ArrayList<>();
        for(String link:urlList){
            driver.get(baselink);
            Integer random=new Random(5).nextInt();
            try {
                TimeUnit.MILLISECONDS.sleep(234*random+1500 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //显式等待， 针对某个元素等待
            WebDriverWait wait = new WebDriverWait(driver,30,1);
            wait.until(new ExpectedCondition<WebElement>(){
                @Override
                public WebElement apply(WebDriver text) {
                    return text.findElement(By.id("sb_form_q"));
                }
            });
            List<WebElement> links = driver.findElements(By.className("b_algo"));

            //TimeUnit.MILLISECONDS.sleep(263*random );
            for(WebElement element:links){
                WebElement classa = element.findElement(By.tagName("a"));
                String href=classa.getAttribute("href");
                reallinks.add(href);

            }
        }
        return reallinks;
    }
}