package com.sist.vo;

import lombok.Data;

@Data
public class MoimVO {
	/*
	 * ������ȣ rno
	 * ��ȸ�� hit
	 * 
	 * �̹��� img
       ��Ҹ� loc
       ����� type
       ���͹�ȣ num
       ���� center
       �����̿�ð� time
       �ּ� addr1
       ����ó call
       �����̿��� cost
       ������ food
       �����̿��� method
       Ȩ������ page
       ȭ����� test1
       ������ test2
       ���ͼҰ� content
       ���������� beam
       ��ǻ�� pc
       ������ printer
       ����ũ mic
	 */
	private int rno,hit;
	private String img,loc,type,num,center,
	time,addr1,call,cost,food,method,
	page,test1,test2,content,beam,
	pc,printer,mic;
}
