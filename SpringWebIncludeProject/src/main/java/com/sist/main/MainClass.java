package com.sist.main;

import java.util.List;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class MainClass{

	 public static void main(String[] args) {
		 
		 MainClass selTest = new MainClass();
	        selTest.crawl();
	        
	    }
	    //WebDriver
	    private WebDriver driver;
	    
	    //Properties
	    public static final String WEB_DRIVER_ID = "webdriver.chrome.driver";
	    public static final String WEB_DRIVER_PATH = "c:\\chromedriver-win64\\chromedriver.exe";
	    
	    //크롤링 할 URL
	    private String base_url;
	    
	    public MainClass() {
	        super();
	 

	        //System Property SetUp
	        System.setProperty(WEB_DRIVER_ID, WEB_DRIVER_PATH);
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--remote-allow-origins=*");


	        //Driver SetUp
	        driver = new ChromeDriver(options);
	        base_url = "https://www.eshare.go.kr/UserPortal/adv/resources/AdvEsSearchResourcesMainView.do?rsrcClsCd=010000";

	    }
	 
	    public void crawl() {
	 
	        try {

	            //get page (= 브라우저에서 url을 주소창에 넣은 후 request 한 것과 같다)
		            driver.get(base_url);

		           //System.out.println(driver.getPageSource());
		           List<WebElement> posterList=driver.findElements(By.cssSelector("img"));
		           System.out.println("test1");
		           System.out.println(posterList);
		           System.out.println("test1");
		        } catch (Exception e) {

		            e.printStackTrace();
		        
		        } finally {

		            driver.close();
		        }
		 
		    }
		 
		}