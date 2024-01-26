package com.sist.proxy;
//  �븮�� => ��� ȣ�� 
/*
 *    AOP ==> ������ �޼ҵ带 ���� 
 *    
 *    � Ŭ������ � �޼ҵ忡 ���� => PointCut
 *    � ���� => JoinPoint
 *       => Before : try������ 
 *       => After : finally 
 *       => After-Returning : ���� ����ÿ� => �� ���� 
 *       => After-Throwing : catch���� => �� (���� �߻�)
 *       => Around
 *          �α� / Ʈ����� 
 *          = �α� 
 *            => 1. �ð� , ȣ�� �޼ҵ� => setAutoCommit(false)
 *               => ���๮�� 
 *            => 2. �ð� => commit()
 *     PointCut+JoinPoint => Advice
 *     
 */
public class Proxy {
  private Sawon sawon;
  public Proxy(Sawon sa)
  {
	  this.sawon=sa;
  }
  // ����
  public void display()
  {
	  System.out.println("Before ó��");
	  sawon.display();
	  System.out.println("After ó��");
  }
}