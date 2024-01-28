package com.sist.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class MainClass2 {
    public static void main(String[] args) {
        try {
            for(int i = 1; i < 16; i++) {
                String URL = "https://www.youthcenter.go.kr/youngSpc/youngSpcList.do?pageIndex=" + i + "&srchYgmnSpceId=&dtlOpenYn=Y&srchSpceWdrCtpvCd=&srchSpceBsgaCd=&srchUtzeCostCd=013004001&srchOperSttsCd=&srchSpnm=&srchProgYN=&srchEmpmConsCd=&srchVdcnOperYn=&srchSortOrder=latest";
                Document doc = Jsoup.connect(URL).timeout(5000000).get(); // 타임아웃으로 설정 (원하는 시간으로 조정)

                Elements link = doc.select("#srchFrm");

                for(int j = 0; j < link.size(); j++) {
                    String img = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li > a > div > img").first().attr("src");
                    System.out.println(img);

                    String loc = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li > a > div > div > dl > dt").first().text();
                    System.out.println(loc);

                    String a = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li > a").first().attr("onclick").substring(13, 25);
                    String completeUrl = "https://www.youthcenter.go.kr/youngSpc/youngSpcDetail.do?pageIndex=1&srchYgmnSpceId=" + a + "&dtlOpenYn=Y&srchSpceWdrCtpvCd=&srchSpceBsgaCd=&srchUtzeCostCd=013004001&srchOperSttsCd=&srchSpnm=&srchProgYN=&srchEmpmConsCd=&srchVdcnOperYn=&srchSortOrder=latest";
                    System.out.println(completeUrl);

                    // 상세페이지
                    Document doc2 = Jsoup.connect(completeUrl).get();
                    String num = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(1) > div.list_cont").first().text();
                    System.out.println(num);
                    String center = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(2) > div.list_cont").first().text();
                    System.out.println(center);
                    String time = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(3) > div.list_cont").first().text();
                    System.out.println(time);
                    String addr1 = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(4) > div.list_cont").first().text();
                    System.out.println(addr1);
                    String call = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(5) > div.list_cont").first().text();
                    System.out.println(call);
                    String cost = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(6) > div.list_cont").first().text();
                    System.out.println(cost);
                    String food = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(8) > div.list_cont").first().text();
                    System.out.println(food);
                    String method = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(9) > div.list_cont").first().text();
                    System.out.println(method);
                    String test1 = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(10) > div.list_cont").first().text();
                    System.out.println(test1);
                    String test2 = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(11) > div.list_cont").first().text();
                    System.out.println(test2);
                    
                    //String img2 = link.select(" #spc-tab1 > div > div.spc-for > ul > div > div > li.slick-slide.slick-current.slick-active > img").first().attr("src");
                    //System.out.println(img2);
                    String content = doc2.select("#content > div.spc-detail.type2 > div.info-txt > p").first().text();
                    System.out.println(content);
                    
                    String beam = doc2.select("#content > div.spc-detail.type2 > div.facilities-list > ul > li:nth-child(3) > strong > span.ea > em").first().text();
                    System.out.println("빔프로젝터"+beam+"개");
                    String pc = doc2.select("#content > div.spc-detail.type2 > div.facilities-list > ul > li:nth-child(2) > strong > span.ea > em").first().text();
                    System.out.println("컴퓨터"+pc+"개");
                    String printer = doc2.select("#content > div.spc-detail.type2 > div.facilities-list > ul > li:nth-child(1) > strong > span.ea > em").first().text();
                    System.out.println("프린터"+printer+"개");
                    String mic = doc2.select("#content > div.spc-detail.type2 > div.facilities-list > ul > li:nth-child(4) > strong > span.ea > em").first().text();
                    System.out.println("마이크"+mic+"개");
                    
                    
                    
                    
                    System.out.println("============================");
                    
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
