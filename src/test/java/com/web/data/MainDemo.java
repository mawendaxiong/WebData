package com.web.data;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class MainDemo implements PageProcessor {
    public static final String URL_LIST = "http://blog\\.sina\\.com\\.cn/s/articlelist_1487828712_0_\\d+\\.html";

    public static final String URL_POST = "http://blog\\.sina\\.com\\.cn/s/blog_\\w+\\.html";

    private Site site = Site
            .me()
            .setDomain("blog.sina.com.cn")
            .setSleepTime(3000)
            .setUserAgent(
                    "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    @Override
    public void process(Page page) {
        //列表页
        if (page.getUrl().regex(URL_LIST).match()) {
            //page根据xPath的条件来过滤组件并按regex的规律来过滤url
            page.addTargetRequests(page.getHtml().xpath("//div[@class=\"articleList\"]").links().regex(URL_POST).all());

            //把整个网页的按照regex的规律来过滤url
            page.addTargetRequests(page.getHtml().links().regex(URL_LIST).all());
            //文章页
        } else {
            page.putField("title", page.getHtml().xpath("//div[@class='articalTitle']/h2"));
            page.putField("content", page.getHtml().xpath("//div[@id='articlebody']//div[@class='articalContent']"));
            page.putField("date",
                    page.getHtml().xpath("//div[@id='articlebody']//span[@class='time SG_txtc']").regex("\\((.*)\\)"));
        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MainDemo()).addUrl("http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html").addPipeline(new ConsolePipeline())
                .run();
    }
}
        //0 = {Request@1969} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html', method='null', extras=null, priority=0, headers={}, cookies={}}"
        //1 = {Request@1970} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html', method='null', extras=null, priority=0, headers={}, cookies={}}"
        //2 = {Request@1971} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html', method='null', extras=null, priority=0, headers={}, cookies={}}"
        //3 = {Request@1972} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_1.html', method='null', extras=null, priority=0, headers={}, cookies={}}"
        //4 = {Request@1973} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_2.html', method='null', extras=null, priority=0, headers={}, cookies={}}"
        //5 = {Request@1974} "Request{url='http://blog.sina.com.cn/s/articlelist_1487828712_0_2.html', method='null', extras=null, priority=0, headers={}, cookies={}}"