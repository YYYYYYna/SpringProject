package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

import java.util.*;

//Vue�� �����͸� ���� => [] , {}
@RestController
public class FoodRestController {
	@Autowired
	private FoodDAO dao;
	
	@GetMapping(value="food/list_vue.do",
			    produces = "text/plain;charset=UTF-8")
	public String food_list_vue() throws Exception
	{
		List<FoodVO> list=dao.foodListData();
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	//����Ʈ ��������� json��ȯ�ϴ¹��� �� ����ؾ���
	@GetMapping(value = "food/detail_vue.do",
			    produces = "text/plain;charset=UTF-8")
	public String food_detail_vue(int fno) throws Exception
	{
		FoodVO vo=dao.foodDetailData(fno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
}
