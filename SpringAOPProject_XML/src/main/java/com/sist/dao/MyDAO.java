package com.sist.dao;

public class MyDAO {
  public void getConnection()
  {
	 System.out.println("����Ŭ ����...");  
  }
  public void disConnection()
  {
	 System.out.println("����Ŭ ����...");  
  }
  /*
   *   ������ : getConnection(),disConnection()
   *            => �������� ���� �ڵ� ȣ�� 
   *   �ٽɸ�� : ���α׷��Ӵ� �ٽɸ� �ڵ�
   *   ** �ݺ��� �����ϴ� ���� (Ʈ����� , �α����� , ����)
   *      => ����� ���� AOP�� ���� �幰�� 
   *      => Footer
   *      
   *   ** ������ ���� : Proxy���� => ��� ȣ�� 
   */
  public void select()
  {
	  getConnection();
	  System.out.println("SELECT���� ���� ��û");
	  disConnection();
  }
  public void insert()
  {
	  getConnection();
	  System.out.println("INSERT���� ���� ��û");
	  disConnection();
  }
  public void update()
  {
	  getConnection();
	  System.out.println("UPDATE���� ���� ��û");
	  disConnection();
  }
  public void delete()
  {
	  getConnection();
	  System.out.println("DELETE���� ���� ��û");
	  disConnection();
  }
}