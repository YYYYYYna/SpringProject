package com.sist.main;
/*
 * 1. XML ���� =====> XML ���� ����
 * 2. Annotation =====> ���������� ����� ����
 *      => �о�ȭ (���帹�̻��)
 *    Annotation
 *      => ����� ���� Ŭ����
 *      => ���̺귯�� Ŭ���� (������̼��� ����) => ��� 
 * 3. ����� Ŭ������ ������̼� ���
 *    ���̺귯�� Ŭ���� XML => ������
 * ==========================================
 *    4���� ���� => 5���� �������� (�ڹ�) => 6���� �л�ó��
 *    ---------------------------        |MSA(Spring Cloud)
 *     |���籹������  =============================
 *                   | ���� (��� ���� => �ڹ�)
 * 
 * ������
 *   => Ʋ (���α׷� ������ ���� => ��� ����ڰ� ������ ����)
 *      => ���� ���� (������ ����) => ������ ��ũ
 *   => ���� ����
 *      1. DI => ��ü ����
 *               ------- ��ü �����ÿ� �ʿ��� �����͸� ����
 *                       ������� �ʱ�ȭ
 *                       = setXxx()
 *                       = ������
 *               ------- �����ڰ� ���
 *               -------
 *               ��ü �Ҹ�
 *               =================== �����̳� (����)
 *      2. �ߺ� �ڵ�
 *         => OOP(Object Oriented Programming, ��ü���� ���α׷���)���� ���� (�ڵ�ȣ���� �ȵȴ�)
 *            => �ߺ� �ڵ� (�޼ҵ�,�޼ҵ� ������ => Ŭ����)
 *         => �ڵ� ȣ���� ����
 *         => AOP(Aspect Oriented Programming, �������� ���α׷���) => �ܾ �� �˸� �����Ҽ� ����
 *            => Before / After / After-Returning / After-Throwing
 *               ����, JoinPointer,PointCut
 *            => public String 
 *               {
 *                  try{
 *                     @Before
 *                  }catch(Exception ex){
 *                     @After-Throwing
 *                  }finally{
 *                     @After
 *                  }
 *                  @After-Returning
 *                  return "";
 *               }
 *      --------------------------------------------------
 *      1. Application
 *      2. Web Application (MVC)              
 */
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import java.util.*;
import com.sist.dao.*;

public class MainClass {
	public static void main(String[] args) {
		
		ApplicationContext app=new ClassPathXmlApplicationContext("app2.xml");
		EmpDAO dao=(EmpDAO)app.getBean("empDAO");//�� "dao"�� app.xml�� ���̵��Ī
		                              //annotation�� ����ؼ� id�� ������ Ŭ�������� id����
		                              //VO���� ��κ��� Ŭ������ ���� ����
		
		List<EmpVO> list=dao.empDeptJoinData();
		for(EmpVO vo:list)
		{
			System.out.println(vo.getEmpno()+" "+vo.getEmpno()+" "+vo.getJob()+" "
		                      //+vo.getHiredate().toString()+" "+vo.getSal()+" "
            				  +vo.getDbday()+" "+vo.getSal()+" "
		                      +vo.getDvo().getDname()+" "+vo.getDvo().getLoc());
		}
		
		
	}
}



























