package com.sist.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sist.dao.EmpDAO;

@Aspect
@Component
public class DBAspect {
	
	@Autowired
	private EmpDAO dao;
	
	@Before("excution(* com.sist.dao.EmpDAO.emp*(..))")
	public void before()
	{
		dao.getConnection();
		System.out.println("getConnection CallBack");
	}
	
	@After("excution(* com.sist.dao.EmpDAO.emp*(..))")
	public void after()
	{
		dao.disConnection();
		System.out.println("disConnection CallBack");
	}
	
	@Around("excution(* com.sist.dao.EmpDAO.emp*(..))") 
	//이 부분은 마치 "만약 EmpDAO 클래스의 메소드 중에 이름이 'emp'로 시작하는 모든 메소드가 호출되면, 나는 그 전후에 어떤 일을 할 거야!"라고 말하고 있어.
	public Object around(ProceedingJoinPoint jp) throws Throwable
	//메소드 이름은 around이야. ProceedingJoinPoint는 어떤 메소드가 호출되었는지, 그리고 그 메소드에 전달된 인자 등에 대한 정보를 담고 있어.
	{
		System.out.println("around start CallBack");
		Object obj=null;
		//나중에 메소드가 끝나고 결과를 담아둘 변수를 만드는 거야.
		long start=System.currentTimeMillis();
		//현재 시간을 기록해서, 일이 일어나기 전에 시간을 기억해두는 거야.
		System.out.println("Call Method:"+jp.getSignature().getName());
		//일이 일어나기 전에 호출된 메소드의 이름을 출력하는 거야. 즉, "이 메소드가 호출되었어!"라고 알려주는 부분이야.
		obj=jp.proceed();
		//실제로 메소드를 실행하는 부분이야. 이 부분이 실행되면, 마치 "이제 일이 일어나는 중이야!"라고 생각하면 돼. 그리고 결과를 obj에 저장해둔 거야.
		long end=System.currentTimeMillis();
		//일이 일어난 후에 현재 시간을 기록해둬. 그래서 일이 일어난 시간을 알 수 있어.
		System.out.println("around end / time:"+(end-start));
		//일이 일어난 후에 실행되는 부분이야. 그리고 일이 일어난 시간을 출력해주는 거야. 마치 "일이 끝났어! 이렇게 오래 걸렸어!" 라고 알려주는 것이야.
		return obj;
		//메소드의 결과를 반환해주는 부분이야. 이 부분이 실행되면 마치 "이제 일이 끝났어! 결과를 돌려줄게!"라고 생각하면 돼.
	}//EmpDAO 클래스의 특정 메소드가 호출될 때, 일이 일어나기 전과 후에 내가 원하는 동작을 할 수 있어. 마치 일의 시작과 끝을 내가 지켜볼 수 있는 것
	
	
}
