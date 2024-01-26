package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.sist.dao.*;

//�̺κ��� <aop:aspect>��� ���� �κ�
@Aspect
@Component
public class DBAspect {
	@Autowired
	private EmpDAO dao;
	//1. before
	@Before("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before()
	{
		dao.getConnection();
		System.out.println("����Ŭ ���� �ڵ� ȣ��:Callback");
	}
	//2. after
	@After("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after()
	{
		dao.disConnection();
		System.out.println("����Ŭ ���� �ڵ� ȣ��:Callback");
	}
	//3. around(�ɸ��ð�����)
	@Around("execution(* com.sist.dao.EmpDAO.emp*(..))")
	public Object around(ProceedingJoinPoint jp) throws Throwable
	{
		System.out.println("����Ŭ �α� �ڵ� ȣ��:Callback");
		Object obj=null;
		long start=System.currentTimeMillis();
		System.out.println("ȣ��� �޼ҵ�:"+jp.getSignature().getName());
		obj=jp.proceed();
		long end=System.currentTimeMillis();
		System.out.println("����ð�:"+(end-start));
		return obj;
	}
	
	//@AfterReturning
	//@AfterThrowing
}
