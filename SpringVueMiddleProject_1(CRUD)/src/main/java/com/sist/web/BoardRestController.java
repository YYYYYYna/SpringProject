package com.sist.web;

import org.apache.commons.collections.map.HashedMap;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.BoardDAO;
import com.sist.vo.BoardVO;

//�ڹ� ��ũ��Ʈ ���� => �����͸� �޾Ƽ� ó���Ŀ� ����� ���� => Rest (Ÿ �ý��۰� �����) => Get/Post=> <<������ ���Ǵ� rest>>
//                                                      Put/Delete <<���X>>
//@Responsebody�� �����
import java.util.*;
@RestController
public class BoardRestController {
   @Autowired
   public BoardDAO dao;
   @GetMapping(value="board/list_vue.do",produces = "text/plain;charset=UTF-8")
   public String board_list_vue(int page) throws Exception
   {
      int rowsize=10;
      int start=(page*rowsize)-(rowsize-1);
      int end=(page*rowsize);
      List<BoardVO> list=dao.boardListData(start, end);
      ObjectMapper mapper=new ObjectMapper();
      String json=mapper.writeValueAsString(list); // jackson
      return json;
   }
   @GetMapping(value="board/page_vue.do",produces = "text/plain;charset=UTF-8")
   public String board_page_vue(int page) throws Exception
   {
      Map map=new HashedMap();
      int totalpage=dao.boardTotalPage();
      map.put("curpage", page);
      map.put("totalpage", totalpage);
      ObjectMapper mapper=new ObjectMapper();
      String json=mapper.writeValueAsString(map);
      return json;
   }
   
   @PostMapping(value="board/insert_ok.do",produces = "text/plain;charset=UTF-8")
   public void board_insert_ok(BoardVO vo)
   {
       dao.boardInsert(vo);	  
   }
   
//   @PostMapping("board/insert_ok.do")
//   public void board_insert_ok(BoardVO vo)
//   {
//      dao.boardInsert(vo);
////      String url="<script>"
////            + "location.href=\"list.do\""
////            + "</script>";
////      return url;
//      // return "redirect: ../board/list.do"; ==> �Ϲ� ���ڿ��� �Ѿ => ȭ�麯��뵵�� ��� �Ұ�!!!
//   }
   
   @GetMapping(value = "board/detail_vue.do", produces = "text/plain;charset=UTF-8")
   public String board_detail_vue(int no) throws Exception
   {
	   BoardVO vo=dao.boardDetailData(no);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(vo);
	   System.out.println("detail:"+json);
	   return json;
   }
   
   @GetMapping(value = "board/update_vue.do", produces = "text/plain;charset=UTF-8")
   public String board_update_vue(int no) throws Exception
   {
	   BoardVO vo=dao.boardUpdateData(no);
	   ObjectMapper mapper=new ObjectMapper();
	   String json=mapper.writeValueAsString(vo);
	   System.out.println("update:"+json);
	   return json;
   }
   
   @PostMapping(value = "board/update_ok.do", produces = "text/plain;charset=UTF-8")
   public String board_update_ok(BoardVO vo) throws Exception
   {
	   String result=dao.boardUpdate(vo);
	   return result;
   }
   
   @GetMapping(value = "board/delete_vue.do", produces = "text/plain;charset=UTF-8")
   public String board_delete_vue(int no, String pwd)
   {
	   String result=dao.boardDelete(no, pwd);
	   return result;
   }
}