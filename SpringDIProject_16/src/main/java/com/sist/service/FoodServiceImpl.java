package com.sist.service;
import java.util.List;

import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

/*
 * ***�̷��� ����ͼ� �޼ҵ庰�� ��ġ�� ���Ἲ�� ���� �ڵ带 ������� ����
 */

@Service("fService")
public class FoodServiceImpl implements FoodService{
	
	@Autowired
	private FoodDAO dao;
	
	@Override
	public List<FoodVO> foodListData(String type) {
		// TODO Auto-generated method stub
		return dao.foodListData(type);
	}

	@Override
	public FoodVO foodDetailData(int fno) {
		// TODO Auto-generated method stub
		return dao.foodDetailData(fno);
	}

}
