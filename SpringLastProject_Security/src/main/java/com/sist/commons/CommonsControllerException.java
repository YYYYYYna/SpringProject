package com.sist.commons;

import org.springframework.stereotype.Controller;
/*
 *  ���� => ���ȭ���̳� ��ɿ� ����
 *  1) Aspect : �ߺ��� ���� �ڵ忡�� (����) => �ڵ�
 *  2) ���뿹��ó��
 *  3) ���ͼ��� => �ʿ�ø��� ó�� (ȭ�鸶�� ó���� ����)
 */
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.io.*;
import java.sql.*;

/*
 * Controller���� �߻��ϴ� ���� ������ ������
 */

@Controller
public class CommonsControllerException {

	// RuntimeException �����߻��� ��µǴ� ���� ����
	@ExceptionHandler(RuntimeException.class)
	public void runtimeException(RuntimeException ex) {
		System.out.println("============ RuntimeException �߻� =============");
		ex.printStackTrace();
		System.out.println("===============================================");
	}

	// IOException �����߻��� ��µǴ� ���� ����
	@ExceptionHandler(IOException.class)
	public void IOException(IOException ex) {
		System.out.println("============ IOException �߻� =============");
		ex.printStackTrace();
		System.out.println("==========================================");
	}

	// SQLException �����߻��� ��µǴ� ���� ����
	@ExceptionHandler(SQLException.class)
	public void SQLException(SQLException ex) {
		System.out.println("============ SQLException �߻� =============");
		ex.printStackTrace();
		System.out.println("===========================================");
	}
}
