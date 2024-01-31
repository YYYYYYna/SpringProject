package com.sist.main;

import java.io.*;
import java.net.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.sist.dao.MoimDAO;
import com.sist.vo.MoimVO;

public class MainClass2 {
    public static void main(String[] args) {
        try {
            for(int i = 1; i < 19; i++) {
            	
            	System.out.println("페이지번호"+i);
                String URL = "https://www.youthcenter.go.kr/youngSpc/youngSpcList.do?pageIndex=" + i + "&srchYgmnSpceId=&dtlOpenYn=Y&srchSpceWdrCtpvCd=&srchSpceBsgaCd=&srchUtzeCostCd=013004001&srchOperSttsCd=&srchSpnm=&srchProgYN=&srchEmpmConsCd=&srchVdcnOperYn=&srchSortOrder=latest";
                //Document doc = Jsoup.connect(URL).get();
                InputStream inputStream = new BufferedInputStream(new URL(URL).openStream());
                Document doc = Jsoup.parse(inputStream, "UTF-8", URL);
                
                Elements link = doc.select("#srchFrm");
                
                for(int j=1;j<=12;j++)
                {
                //상세페이지:링크
                Elements a = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li:nth-child("+j+") > a");
                for(int k=0;k<a.size();k++)
                {
                	
                String aa = a.attr("onclick").substring(13, 25);
                String completeUrl = "https://www.youthcenter.go.kr/youngSpc/youngSpcDetail.do?pageIndex="+k+"&srchYgmnSpceId=" + aa + "&dtlOpenYn=Y&srchSpceWdrCtpvCd=&srchSpceBsgaCd=&srchUtzeCostCd=013004001&srchOperSttsCd=&srchSpnm=&srchProgYN=&srchEmpmConsCd=&srchVdcnOperYn=&srchSortOrder=latest";
                //System.out.println(completeUrl);
                System.out.println("aa:"+aa);
                
                
                	
                    //이미지
                	//String img = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li > a > div > img").first().attr("src");
                    //System.out.println(img);
                    
                    //장소명
                    //String loc = link.select(" div.spc-srh-list > div.spc-space-wrap > ul > li > a > div > div > dl > dt").text();
                    //System.out.println(loc);

                    
                    try {

                    
                    // 상세페이지
                    Document doc2 = Jsoup.connect(completeUrl).timeout(50000).get();
                    
                    //장소명+운영여부
                    String title = doc2.select("#content > h2").first().text();
                    
                    //장소명
                    String loc=title.substring(0, title.length() - 3);
                    System.out.println("loc:"+loc);
                    
                    //운영여부
                    String type=title.substring(title.length() - 4);
                    System.out.println("type:"+type);
                    
                    //이미지
                    String img1 = doc2.select(" #content > div.spc-detail.type2 > div.spc-detail-header > div.left > div > img").first().attr("src");
                    String img = "https://www.youthcenter.go.kr"+img1;
                    System.out.println("img"+img);
                    
                    
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
                    String page = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(10) > div.list_cont").first().text();
                    System.out.println("페이지링크"+page);
                    String test1 = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(11) > div.list_cont").first().text();
                    System.out.println(test1);
                    String test2 = doc2.select("#content > div.spc-detail.type2 > div.spc-detail-header > div.table_wrap > ul > li:nth-child(12) > div.list_cont").first().text();
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
                    
                    
                    MoimVO vo=new MoimVO();
        			vo.setImg(img);
        			vo.setLoc(loc);
        			vo.setType(type);
        			vo.setNum(num);
        			vo.setCenter(center);
        			vo.setTime(time);
        			vo.setAddr1(addr1);
        			vo.setCall(call);
        			vo.setCost(cost);
        			vo.setFood(food);
        			vo.setMethod(method);
        			vo.setPage(page);
        			vo.setTest1(test1);
        			vo.setTest2(test2);
        			vo.setContent(content);
        			vo.setBeam(beam);
        			vo.setPc(pc);
        			vo.setPrinter(printer);
        			vo.setMic(mic);
                    MoimDAO dao=new MoimDAO();
        			dao.insert(vo);
                    
                    	}catch(Exception ex) {}
                    	
                    	
                   
                }
                //Thread.sleep(1000);
            }
          }
        } catch (Exception e) {
            e.printStackTrace();
        }
     
        
    }
}
