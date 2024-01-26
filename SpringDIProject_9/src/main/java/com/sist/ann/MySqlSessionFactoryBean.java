package com.sist.ann;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 *  <!-- getConnection disConnection 만들어주는 곳 -->
   <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
      p:dataSource-ref="ds"
     />
 */
@Component("ssf") // 메모리 할당을 하되 아이디는 ssf로 하겠다
// 지정하지 않는 경우 => mySqlSessionFactoryBean 앞이 M이 아닌 m 소문자
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {

   @Autowired
   public void setDataSource(DataSource dataSource) {
      // TODO Auto-generated method stub
      super.setDataSource(dataSource);
   }

}