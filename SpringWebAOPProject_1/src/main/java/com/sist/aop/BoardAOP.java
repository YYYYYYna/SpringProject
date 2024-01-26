package com.sist.aop;
import java.util.*;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.sist.dao.*;
/*
 *  [AOP의 역할]
 *  1. 모니터링, 로깅, 오류검사(처리), 성능파악
 *       => 트랜잭션, 보안, 암호화 복호화, 데이터읽기 => 분석
 *     = 공통적인 기능을 재사용하는 기법
 *     = 공통적인 내용을 모아서 관리 => 유지보수
 *     = 라이브러리 (트랜잭션, 보안)
 *  Spring FrameWork
 *   => DI
 *   => AOP
 *   => MVC
 *  
 *  [AOP를 사용하는법]
 *  1. 어떤 클래스의 메소드에서 적용할건지 => PointCut
 *     => execution("특정위치 지정")
 *     => within("패키지명")
 *  2. 메소드 위치 => JoinPoint
 *     = Before
 *     = After : finally에서 수행
 *     = AfterReturning : 정상수행시 수행 => return값을 받을수 있다.
 *     = AfterThrowing : 에러처리시 catch절에서 수행
 *     = Around : try문 소스코딩 전후에 수행
 *  3. 인터셉트 : 자동로그인, ID 저장
 *       예) @Before
 *          public void before() //try진입전 수행
 *          {
 *          }
 *          @After
 *          public void After() //finally에서 수행
 *          {
 *          }
 *          @AfterReturning
 *          public void AfterReturning() //try~finally다 끝나고 수행
 *          {
 *          }
 *          @AfterThrowing
 *          public void AfterThrowing() //catch절에서 수행
 *          {
 *          }
 */
//<aop:aspect> @Component 로 메모리 할당해줌
@Aspect
@Component
public class BoardAOP {
	@Autowired
	private BoardDAO dao;
	
	                    //매개변수 상관없이 모든 컨트롤러에 적용하겠다는 말
	@After("execution(* com.sist.web.*Controller.*(..))") 
	public void after()
	{
		List<BoardVO> list=dao.boardTop5();
		//실제 사용중인 request를 가지고 올때 사용 => Cookie때 보통 사용
		HttpServletRequest request=
				((ServletRequestAttributes)RequestContextHolder
						.getRequestAttributes()).getRequest();
		request.setAttribute("tList", list);
	}
}
