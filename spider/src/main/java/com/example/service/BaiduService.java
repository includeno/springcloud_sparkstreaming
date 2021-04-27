package com.example.service;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Service
public class BaiduService implements SeleniumServiceInterface{
    @Autowired
    WebDriver driver;


    public ArrayList<String> searchKeyword(String keyword, int page) {
        OkHttpClient client = new OkHttpClient();
        //页面加载超时时间设置为 5s
        driver.manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);

        ArrayList<String> urlList=new ArrayList<>();
        String baselink="";
        for(int i=0;i<page;i++){
            int pn = page * 10 + 1;
            baselink="http://www.baidu.com/s?wd="+keyword+"&pn="+pn+"&cl=3&rn=100";
            System.out.println("baselink:"+baselink);
            urlList.add(baselink);
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
                    return text.findElement(By.id("kw"));
                }
            });
            //driver.findElement(By.id("kw"));
            List<WebElement> links = driver.findElements(By.className("t"));

            //TimeUnit.MILLISECONDS.sleep(263*random );
            for(WebElement element:links){
                WebElement classa = element.findElement(By.tagName("a"));
                String href=classa.getAttribute("href");

                Request request = new Request.Builder().url(href).build();
                try {
                    Response response = client.newCall(request).execute();
                    System.out.println(response.request().url().toString());
                    reallinks.add(response.request().url().toString());
                } catch (IOException e) {
                    continue;
                    //reallinks.add("");
                }
            }

        }

        return reallinks;
    }
}
