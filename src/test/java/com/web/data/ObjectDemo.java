package com.web.data;

import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.TargetUrl;

@TargetUrl("http://my.oschina.net/flashsword/blog")
public class ObjectDemo {
    @ExtractBy("//a[@class='header']/text()")
    private String title;

    @ExtractBy("//a[@class='header']/@href")
    private String address;

    public static void main(String[] args) {
        OOSpider.create(Site.me().setSleepTime(1000), new ConsolePageModelPipeline(),ObjectDemo.class)
                .addUrl("http://my.oschina.net/flashsword/blog").thread(5).run();
    }
}
