package com.sist.dao;

public class MyDAO2 {
  public void select()
  {
	  //before  getConnection()
	  System.out.println("����Ŭ => SELECT���� ��û");
	  //after disConnection()
  }
  public void insert()
  {
	  // 
	  System.out.println("����Ŭ => INSERT���� ��û");
  }
  public void update()
  {
	  System.out.println("����Ŭ => UPDATE���� ��û");
  }
  public void delete()
  {
	  System.out.println("����Ŭ => DELETE���� ��û");
  }
}