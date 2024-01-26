package com.sist.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import com.sist.dao.*;
@Component
/*
 * @Component �� �����ġ
 * : Ŭ�������� ����
 * 
 * @Autowired �� �����ġ
 * @Target:������ �����Ұ��� ����
 * @Target(value={CONSTRUCTOR, METHOD, PARAMETER, FIELD, ANNOTATION_TYPE})
 *            ����     ������      �޼ҵ�     �Ű�����    �������     ������̼�   ���� ���� ����
 */
public class MainClass {
   @Autowired
   @Qualifier("memberDAO")
   private OracleDB ob;
   public static void main(String[] args) {
      ApplicationContext app=
            new ClassPathXmlApplicationContext("app.xml");
      
      //1. getBean
      //getBean�ƴϰ� new����ϸ� �޸� ���� �Ҵ�Ǽ� �� ��������
      MainClass mc=(MainClass)app.getBean("mainClass"); 
      
      //2. @Qualifier("memberDAO")
      //���� ���� �Ʒ� �����ϸ� dao�ΰ� �� @repository�� �������� � dao�� �θ����� ���� ������
      mc.ob.display();
   }

}