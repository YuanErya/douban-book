package com.ruayou.entity;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.annotation.*;
import com.geccocrawler.gecco.spider.HtmlBean;
import org.apache.commons.lang3.StringUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.TextNode;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Author：ruayou
 * @Date：2024/8/23 23:46
 * @Filename：BookInfo
 */
@Gecco(matchUrl = "https://book.douban.com/subject/{doubanId}/", pipelines = "boobInfo")
public class BookInfo implements HtmlBean {
    @RequestParameter
    private String doubanId;

    @HtmlField(cssPath = "h1 > span[property=v:itemreviewed]")
    private String bookName;

    @HtmlField(cssPath = "#info > span > span:contains(作者) ~ a , #info > span:contains(作者) + a")
    private List<String> author;
    /**
     * 出版社
     */
    @HtmlField(cssPath = "#info > a[href*='/press/']")
    private String press;
    /**
     * 出品方
     * <a href="https://book.douban.com/producers/621">后浪文学</a>
     */
    @HtmlField(cssPath = "#info > a[href*='/producers/']")
    private List<String> producer;

    @HtmlField(cssPath = "#info > span > span:contains(译者) ~ a , #info > span:contains(译者) + a")
    private List<String> translator;

    @HtmlField(cssPath = "#info > span:contains(丛书) + a")
    private List<String> series;

    /**
     * 原作名
     * 出版年:
     * 页数:
     * 定价:
     * 装帧:
     * ISBN:
     * 上述属性非元素
     */
    @HtmlField(cssPath = "#info")
    private String info;

    private String originalTitle;

    private String publishTime;

    private Integer page;

    private String price;
    /**
     * 装帧
     */
    @HtmlField(cssPath = "")
    private String binding;

    private String ISBN;

    @HtmlField(cssPath = "#interest_sectl > div > div.rating_self.clearfix > strong")
    private String doubanScore;

//#link-report > div > div
    @HtmlField(cssPath = "#link-report > span.all.hidden > div > div , #link-report > div > div.intro")
    private String intro;


    private String introduce;


    @HtmlField(cssPath = "img[alt]")
    @Image
    private String imgUrl;

    //#db-tags-section > div > span:nth-child(1) > a
    @HtmlField(cssPath = "#db-tags-section > div.indent > span > a")
    private List<String> tags= Collections.emptyList();

    public void parseInfo(){
        Document infoDoc = Jsoup.parse(info);
        List<TextNode> textNodes = infoDoc.body().textNodes();
        ArrayList<String> txt = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (TextNode textNode : textNodes) {
            if (!StringUtils.isBlank(textNode.text())) {
                String trim = textNode.text().trim();
                if (StringUtils.isNumeric(trim)&&trim.length()<8) {
                    numbers.add(Integer.parseInt(trim));
                }
                else if (StringUtils.isNumeric(trim)&&(trim.length()==13||trim.length()==10)) {
                    ISBN=trim;
                }else if(trim.contains("-")){
                    publishTime=trim;
                } else if (trim.contains("装")) {
                    binding=trim;
                } else if (trim.contains("元")) {
                    price=trim;
                }else {
                    if (!trim.equals("/")) {
                        txt.add(trim);
                    }

                }
            }
        }
        if (numbers.size()>0) {
            page=numbers.get(0);
        }
        if(info.contains("原作名")){
            originalTitle=txt.get(0);
        }

        Document introDoc = Jsoup.parse(intro);
        StringBuilder sb = new StringBuilder();
        introDoc.body().children().forEach(element -> {
                sb.append(element.text()+"\n");

        });
        introduce=sb.toString();

    }

    public List<String> getSeries() {
        return series;
    }

    public void setSeries(List<String> series) {
        this.series = series;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getDoubanId() {
        return doubanId;
    }

    public void setDoubanId(String doubanId) {
        this.doubanId = doubanId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public List<String> getAuthor() {
        return author;
    }

    public void setAuthor(List<String> author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public List<String> getProducer() {
        return producer;
    }

    public void setProducer(List<String> producer) {
        this.producer = producer;
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getBinding() {
        return binding;
    }

    public void setBinding(String binding) {
        this.binding = binding;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public String getDoubanScore() {
        return doubanScore;
    }

    public void setDoubanScore(String doubanScore) {
        this.doubanScore = doubanScore;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public List<String> getTranslator() {
        return translator;
    }

    public void setTranslator(List<String> translator) {
        this.translator = translator;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public void setOriginalTitle(String originalTitle) {
        this.originalTitle = originalTitle;
    }

    @Override
    public String toString() {
        return "BookInfo{" +
                "doubanId='" + doubanId + '\'' +
                ", bookName='" + bookName + '\'' +
                ", author=" + author +
                ", press='" + press + '\'' +
                ", producer=" + producer +
                ", translator=" + translator +
                ", series=" + series +
                ", originalTitle='" + originalTitle + '\'' +
                ", publishTime='" + publishTime + '\'' +
                ", page=" + page +
                ", price='" + price + '\'' +
                ", binding='" + binding + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", doubanScore='" + doubanScore + '\'' +
                ", introduce='" + introduce + '\'' +
                ", imgUrl='" + imgUrl + '\'' +
                ", tags=" + tags +
                '}';
    }

    public static void main(String[] args) {
        GeccoEngine engine = GeccoEngine.create()
                //工程的包路径
                .classpath("com.ruayou")
                //开始抓取的页面地址
                .start("https://book.douban.com/subject/36669377")
                //开启几个爬虫线程
                .thread(16)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(5000)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .mobile(false);
        engine.start();
    }
}
