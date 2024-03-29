package com.sist.web;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.FoodDAO;
import com.sist.dao.FoodVO;

import java.util.*;

//Vue로 데이터를 전송 => [] , {}
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
	
	//프론트 가고싶으면 json변환하는법을 꼭 기억해야함
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
