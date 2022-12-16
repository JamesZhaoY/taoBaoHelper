package com.zhanyang.taobaohelper;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author: zzy
 * @date: 2022-12-16 10:11
 * @description: 抢钱神器-by赵占阳
 **/
@Service
public class TaoBaoHelperService {


/**
 * @Author zzy
 * @Description  /api/taoBao?time=1    目前只支持整点抢购，输入24小时制时间
 * @Date 14:58 2022/12/16
 * @Param time
 **/
    public void taoBao(String time) throws Exception {

        //浏览器驱动路径
        System.setProperty("webdriver.chrome.driver", "D:\\soft\\chrome\\chromedriver.exe");
        //设置秒杀时间 暂时支持整点抢购
        LocalDateTime localDateTime = LocalDateTime.now()
                    .withHour(Integer.parseInt(time)).withMinute(0).withSecond(0);
        //1、打开浏览器
        ChromeDriver browser = new ChromeDriver();
        Actions actions = new Actions(browser);
        //2、输入网址
        browser.get("https://www.taobao.com");
        //3、点击登录
        browser.findElement(By.linkText("亲，请登录")).click();
        //4、扫码登录
        browser.findElement(By.className("icon-qrcode")).click();
        Thread.sleep(15000);
        //5、进入购物车页面
        browser.get("https://cart.taobao.com/cart.htm");
        Thread.sleep(1000);
        //6、点击选择第一个按钮
        browser.findElement(By.xpath("//*[@id=\"J_SelectAll1\"]/div/label")).click();
        Thread.sleep(2000);
        while (true) {
            LocalDateTime now = LocalDateTime.now();
            //当前时间
            if (now.isBefore(localDateTime)) {
                if (browser.findElement(By.xpath("//*[@id=\"J_Go\"]")).isEnabled()) {
                    browser.findElement(By.xpath("//*[@id=\"J_Go\"]")).click();
                    browser.findElement(By.xpath("//*[@id=\"submitOrderPC_1\"]/div[1]/a[2]")).click();
                    break;
                }
            }else if(now.plusHours(1).isBefore(localDateTime)) {
                Thread.sleep(1000*60*60);
            }else if(now.plusMinutes(10).isBefore(localDateTime)) {
                Thread.sleep(1000*60*10);
            }else if(now.plusMinutes(1).isBefore(localDateTime)) {
                Thread.sleep(1000*60);
            }
        }
    }

}
