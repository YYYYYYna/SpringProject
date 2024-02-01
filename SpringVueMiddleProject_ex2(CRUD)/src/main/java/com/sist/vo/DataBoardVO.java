package com.sist.vo;
import java.util.*;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
/*
 *     �⺻ 
 *     ===
 *      src 
 *       = com.sist.config : XML�� ��� �������� Ŭ���� ���踦 ���� 
 *                           => 5������ �ٽ� (����) =>  ���� 
 *                           => Spring-Boot (���� => SpringFramework) => XML(pom.xml)
 *                           => �����ϰ� �ڹٷθ� ���� 
 *                              <context:component-scan> 
 *                              @ComponentScan()
 *                              <mybatis-spring>
 *                              @MapperScan()
 *                              <bean>
 *                              @Bean
 *       ***= com.sist.dao :  �����ͺ��̽� ���� 
 *       = com.sist.service : DAO ���� => interface (���ռ� ����)
 *       ***= com.sist.vo : ����� ���� �������� 
 *       ***= com.sist.web : Model (��û => ����)
 *       = com.sist.interceptor : Model���� �� , JSP���� �� 
 *                                preHandle()  postHandle()
 *                                => �ڵ� �α���  => ���� 
 *       = com.sist.aop : ���� ��� 
 *       = com.sist.manager : OPEN API => �ǽð� ���� �б� 
 *       = com.sist.commons : ���� ����ó�� 
 *       = com.sist.security : ���� => ��ȣȭ / ��ȭȭ / ���� / �޼ҵ� ���� 
 *       = com.sist.chat : �ǽð� ��� 
 *       
 *       => ��� ��Ű���� �޸� �Ҵ� ��û  ==> ������ (Ŭ���� ����) => ���� / �Ҹ� 
 *                                                          ----------
 *                                                          1. �������� Ŭ���� �޸� �ּҸ� ��û
 *                                                             =>@Autowired
 *       => ���� (�ڹٷ� ������� ������) 
 *          server + let 
 *          => �������� �����ϴ� ������ ���α׷� 
 *          => ������ ������ �ϴ� ��� => ��Ĺ (request/response)
 *             ============================================== ������
 *             => ������ ������ ��Ĺ�� ��� 
 *                            =========
 *                             web.xml => @WebServlet("*.do")
 *                              => DispatcherServlet
 *             => DispatcherServlet ��� 
 *                => WebApplicationContext
 *                => HandlerMapping
 *                => XML => �ڵ� ���� , ������̼��� ���� ���� 
 *                => URL�� ���� => ��Ĺ�� ����
 *        list.do =====================> DisaptcherServlet 
 *                ��Ĺ(request,response)
 *                       public void service(request,response)
 *                       {
 *                           => Model ã�� ==> HandlerMapping
 *                              => ��� ���� (�޼ҵ�) => ������ @GetMapping/@PostMapping
 *                           => return�� �о� �´� 
 *                           => JSPã��    ==> ViewResolver (��θ�)
 *                           => request�� ���� 
 *                       }
 *                       
 */
@Data
public class DataBoardVO {
   private int no,hit,filecount;
   private String name,subject,content,pwd,filename,filesize,dbday;
   private Date regadate;
   private List<MultipartFile> files;
}