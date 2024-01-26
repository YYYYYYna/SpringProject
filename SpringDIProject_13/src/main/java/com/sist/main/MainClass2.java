package com.sist.main;
import java.util.*;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sist.config.EmpConfig;
import com.sist.dao.*;
public class MainClass2 {
	public static void main(String[] args) {
		AnnotationConfigApplicationContext app=
				new AnnotationConfigApplicationContext(EmpConfig.class);
		EmpDAO dao=(EmpDAO)app.getBean("eDAO");
		List<EmpVO> list=dao.empListData();
		for(EmpVO vo:list)
	    {
	       System.out.println(vo.getEmpno()+" "
	                      +vo.getEname()+" "
	                      +vo.getJob());
	    }
	}
}
