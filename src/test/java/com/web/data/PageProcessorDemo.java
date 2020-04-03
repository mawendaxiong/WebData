package com.web.data;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

import java.util.List;

public class PageProcessorDemo implements PageProcessor {
    private Site site = Site.me().setDomain("my.oschina.net");

    private static String WEB1 = "http://my\\.oschina\\.net/flashsword/blog/\\d+";
    private static String WEB2 = "http://my.oschina.net/flashsword/blog";

    @Override
    public void process(Page page) {
        List<String> links = page.getHtml().links().regex(WEB1).all();
        page.addTargetRequests(links);
//        page.putField("title", page.getHtml().xpath("//div[@class='BlogEntity']/div[@class='BlogTitle']/h1").toString());/item blog-item/content/header
        page.putField("title", page.getHtml().xpath("//a[@class='header']/text()").regex("").all());
        page.putField("address", page.getHtml().xpath("//a[@class='header']/@href").all());

//        page.putField("content", page.getHtml().$("div.content").toString());
        page.getResultItems().get("title");
//        page.putField("tags", page.getHtml().xpath("//div[@class='BlogTags']/a/text()").all());
//        page.addTargetRequests(page.getHtml().links().regex("(https://my\\.oschina\\.net/flashsword/").all());
    }

    @Override
    public Site getSite() {
        return site;
    }

    /**
     * Spider 爬虫入口
     * Pipeline 结果输出和持久化
     * ConsolePipeline 控制台Pipeline 输出到控制台
     */
    public static void main(String[] args) {
        Spider.create(new PageProcessorDemo()).addUrl(WEB2).addPipeline(new ConsolePipeline()).run();
    }
}
    //获取class名为time timeago的span标签里的内容
//    Object s2 =page.getHtml().xpath("//div[@class='detail-wrapper']//div[@class='header ']/a/div/span[@class='time timeago']/text()")