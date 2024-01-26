package com.sist.spring;

public class Member {
	private int mno;
	private String name;
	private String address;
	
	//생성자 생성
	public Member(int mno, String name, String address) {
		this.mno = mno;
		this.name = name;
		this.address = address;
	}
	
	public void print()
	{
		System.out.println("회원번호:"+mno);
		System.out.println("이름:"+name);
		System.out.println("주소:"+address);
	}
}
