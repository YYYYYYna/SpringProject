package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;
@Component
/*
 * @Component 의 사용위치
 * : 클래스에만 적용
 * 
 * @Autowired 의 사용위치
 * @Target:무엇을 제어할건지 정함
 * @Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
 *            각각     생성자      메소드     매개변수    멤버변수     어노테이션   등을 구분 가능
 */
public class MainClass {
   @Autowired
   @Qualifier("memberDAO")
   private OracleDB ob;
   public static void main(String[] args) {
      ApplicationContext app=
            new ClassPathXmlApplicationContext("app.xml");
      
      //1. getBean
      //getBean아니고 new사용하면 메모리 새로 할당되서 값 못가져옴
      MainClass mc=(MainClass)app.getBean("mainClass"); 
      
      //2. @Qualifier("memberDAO")
      //위에 없이 아래 실행하면 dao두개 다 @repository가 있음으로 어떤 dao를 부르는지 몰라서 오류남
      mc.ob.display();
   }

}