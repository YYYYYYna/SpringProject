package com.sist.main;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sist.config.EmpConfig;
import com.sist.dao.EmpDAO;
import com.sist.dao.EmpVO;

public class MainClass {
	public static void main(String[] args) {
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app.xml");
		
		//AnnotationConfigApplicationContext app=new AnnotationConfigApplicationContext(EmpConfig.class);
		
		EmpDAO dao=(EmpDAO)app.getBean("eDAO");
		List<EmpVO> list=dao.empAllData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "
					+vo.getEname()+" "
					+vo.getDvo().getDeptno()+" "
					+vo.getSvo().getGrade());
		}
	}
}
