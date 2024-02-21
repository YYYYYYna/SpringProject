package com.sist.commons;

import org.springframework.stereotype.Controller;
/*
 *  공통 => 모든화면이나 기능에 적용
 *  1) Aspect : 중복이 많이 코드에서 (제거) => 자동
 *  2) 공통예외처리
 *  3) 인터셉터 => 필요시마다 처리 (화면마다 처리가 가능)
 */
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.*;
import java.sql.*;

/*
 * Controller에서 발생하는 공통 에러를 만들어둠
 */

@Controller
public class CommonsControllerException {

	// RuntimeException 에러발생시 출력되는 내용 설정
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("============ RuntimeException 발생 =============");
		ex.printStackTrace();
		System.out.println("===============================================");
	}

	// IOException 에러발생시 출력되는 내용 설정
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex) {
		System.out.println("============ IOException 발생 =============");
		ex.printStackTrace();
		System.out.println("==========================================");
	}

	// SQLException 에러발생시 출력되는 내용 설정
	@ExceptionHandler(SQLException.class)
	public void SQLException(SQLException ex) {
		System.out.println("============ SQLException 발생 =============");
		ex.printStackTrace();
		System.out.println("===========================================");
	}
}
