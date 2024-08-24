package com.ruayou;

import com.geccocrawler.gecco.GeccoEngine;

import java.util.Scanner;

/**
 * @Author：ruayou
 * @Date：2024/8/24 13:25
 * @Filename：Main
 */
public class Main {
    public static void main(String[] args) {

        int threadNum;
        int interval;
        String url;
        Scanner sc = new Scanner(System.in);
        if(args.length!=3){
            System.out.println("请输入线程数:");
            threadNum = sc.nextInt();
            System.out.println("请输入间隔时间:");
            interval = sc.nextInt();
            System.out.println("请输入开始爬取的url");
            url = sc.next();
        }else {
            threadNum = Integer.parseInt(args[0]);
            interval = Integer.parseInt(args[1]);
            url = args[2];
        }
        GeccoEngine engine = GeccoEngine.create()
                //工程的包路径
                .classpath("com.ruayou")
                //开始抓取的页面地址
                .start(url)
                //开启几个爬虫线程
                .thread(threadNum)
                //单个爬虫每次抓取完一个请求后的间隔时间
                .interval(interval)
                //循环抓取
                .loop(false)
                //使用pc端userAgent
                .mobile(false);
        engine.start();


    }
}
