package com.web.data;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class MainDemo2 implements PageProcessor {

    private Site site = Site.me()
            .setSleepTime(3000)
            .setUserAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_7_2) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.65 Safari/537.31");

    private final static String WEB1 = "https://my\\.oschina\\.net/flashsword/widgets/_space_index_popular_blog?q=&p=\\w+&type=ajax";

    @Override
    public void process(Page page) {
        if (page.getUrl().regex(WEB1).match()){
            page.addTargetRequests(page.getHtml().xpath("//a[@class='pagination__next']").links().regex("").all());
        }else {

        }
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new MainDemo2()).addUrl("https://my.oschina.net/flashsword").addPipeline(new ConsolePipeline()).thread(4).run();
    }
}
