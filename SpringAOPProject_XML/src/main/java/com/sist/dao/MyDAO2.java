package com.sist.dao;

public class MyDAO2 {
  public void select()
  {
	  //before  getConnection()
	  System.out.println("오라클 => SELECT문장 요청");
	  //after disConnection()
  }
  public void insert()
  {
	  // 
	  System.out.println("오라클 => INSERT문장 요청");
  }
  public void update()
  {
	  System.out.println("오라클 => UPDATE문장 요청");
  }
  public void delete()
  {
	  System.out.println("오라클 => DELETE문장 요청");
  }
}