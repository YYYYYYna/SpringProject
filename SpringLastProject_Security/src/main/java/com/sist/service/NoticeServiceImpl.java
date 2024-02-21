package com.sist.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sist.dao.NoticeDAO;
import com.sist.vo.FoodVO;
import com.sist.vo.NoticeVO;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeDAO dao;
	
	@Override
	public List<NoticeVO> noticeTop7() {
		// TODO Auto-generated method stub
		return dao.noticeTop7();
	}

	@Override
	public List<FoodVO> foodTop7() {
		// TODO Auto-generated method stub
		return dao.foodTop7();
	}

}
