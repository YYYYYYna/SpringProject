package com.sist.ann;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/*
 *  <!-- getConnection disConnection ������ִ� �� -->
   <bean id="ssf" class="org.mybatis.spring.SqlSessionFactoryBean"
      p:dataSource-ref="ds"
     />
 */
@Component("ssf") // �޸� �Ҵ��� �ϵ� ���̵�� ssf�� �ϰڴ�
// �������� �ʴ� ��� => mySqlSessionFactoryBean ���� M�� �ƴ� m �ҹ���
public class MySqlSessionFactoryBean extends SqlSessionFactoryBean {

   @Autowired
   public void setDataSource(DataSource dataSource) {
      // TODO Auto-generated method stub
      super.setDataSource(dataSource);
   }

}