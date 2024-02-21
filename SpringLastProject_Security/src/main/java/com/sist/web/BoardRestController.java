package com.sist.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.FreeBoardService;
import com.sist.vo.FreeBoardVO;

@RestController
@RequestMapping("freeboard/")
public class BoardRestController {
	@Autowired
	private FreeBoardService service;
	
	@GetMapping(value = "list_vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_list_vue(int page)throws Exception
	{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		Map map=new HashMap();
		map.put("start", start);
		map.put("end", end);
		List<FreeBoardVO> list=service.freeboardListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "page_vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_page_vue(int page)throws Exception
	{
		int totalpage=service.freeboardTotalPage();
		   
	    Map map=new HashMap();
	    map.put("curpage", page);
 	    map.put("totalpage", totalpage);
		   
	    ObjectMapper mapper=new ObjectMapper();
	    String json=mapper.writeValueAsString(map);
	    return json;
	}
	
	//새글쓰기
	@PostMapping(value = "insert_vue.do",produces = "text/plain;charset=UTF-8")
	public String freeboard_insert_vue(FreeBoardVO vo) throws Exception
	{
		String result="";
		try {
			//로그인성공시 yes보내기
			service.freeboardInsert(vo);
			result="yes";
		}catch(Exception ex)
		{
			//오류출력
			result=ex.getMessage();
		}
		return result;
	}
	
	//글상세보기
	@GetMapping(value = "detail_vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_detail_vue(int no) throws Exception
	{
		FreeBoardVO vo=service.FreeBoardDetailData(no);
		// VO =JSON=> {} , List =JSON=> [{},{},{}]
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	//삭제하기
	@GetMapping(value = "delete.vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_delete_vue(int no, String pwd) throws Exception
	{
		//objectmapper로 json변환 안해도됨 => vo,list가 있을때만 사용
		//지금처럼 일반 문자열 있을때는 사용 안함
		String result=service.FreeboardDelete(no, pwd);
		return result;
	}
	
	//수정하기
	@GetMapping(value = "update_vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_update(int no) throws Exception
	{
		FreeBoardVO vo=service.FreeBoardUpdateData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	//수정하기_ok
	@PostMapping(value = "update_ok_vue.do", produces = "text/plain;charset=UTF-8")
	public String freeboard_update_ok(FreeBoardVO vo) throws Exception
	{
		String result=service.freeboardUpdate(vo);
		return result;
	}
	
}
