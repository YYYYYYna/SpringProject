package com.sist.vo;

import lombok.Data;

@Data
public class MoimVO {
	/*
	 * 고유번호 rno
	 * 조회수 hit
	 * 
	 * 이미지 img
       장소명 loc
       운영여부 type
       센터번호 num
       운영기관 center
       센터이용시간 time
       주소 addr1
       문의처 call
       센터이용비용 cost
       식음료 food
       센터이용방법 method
       홈페이지 page
       화상면접 test1
       취업상담 test2
       센터소개 content
       빔프로젝터 beam
       컴퓨터 pc
       프린터 printer
       마이크 mic
	 */
	private int rno,hit;
	private String img,loc,type,num,center,
	time,addr1,call,cost,food,method,
	page,test1,test2,content,beam,
	pc,printer,mic;
}
