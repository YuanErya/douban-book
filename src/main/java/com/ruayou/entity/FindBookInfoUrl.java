package com.ruayou.entity;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.Href;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;
import java.util.List;

/**
 * @Author：ruayou
 * @Date：2024/8/24 11:48
 * @Filename：FindBookInfoUrl
 */
@Gecco(pipelines = "findBookInfo")
public class FindBookInfoUrl implements HtmlBean {

    @Request
    private HttpRequest request;

    @Href(value = "href")
    @HtmlField(cssPath = "a.nbg")
    private List<String> bookInfoUrl;

    @Href
    @HtmlField(cssPath = "span.next > link")
    private String nextPage;

    public HttpRequest getRequest() {
        return request;
    }

    public void setRequest(HttpRequest request) {
        this.request = request;
    }

    public List<String> getBookInfoUrl() {
        return bookInfoUrl;
    }

    public void setBookInfoUrl(List<String> bookInfoUrl) {
        this.bookInfoUrl = bookInfoUrl;
    }

    public String getNextPage() {
        return nextPage;
    }

    public void setNextPage(String nextPage) {
        this.nextPage = nextPage;
    }

    @Override
    public String toString() {
        return "FindBookInfoUrl{" +
                "bookInfoUrl=" + bookInfoUrl +
                ", nextPage='" + nextPage + '\'' +
                '}';
    }

    public static void main(String[] args) {
        GeccoEngine engine = GeccoEngine.create()
                //工程的包路径
                .classpath("com.ruayou")
                //开始抓取的页面地址
                .start("https://book.douban.com/press/2146/")
                //开启几个爬虫线程
                .thread(1)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(2000)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .mobile(false);
        engine.start();
    }



}
