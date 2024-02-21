package com.sist.web;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.ReplyService;
import com.sist.vo.MemberVO;
import com.sist.vo.ReplyVO;

@RestController
public class ReplyRestController {
	@Autowired
	private ReplyService service;
	
	public String commonsReplyData(int rno) throws Exception
	{
		List<ReplyVO> list=service.replyListData(rno);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	//�߰�
	@PostMapping(value="recipe/reply_insert_vue.do", produces="text/plain;charset=UTF-8")
	public String reply_insert(ReplyVO vo, Principal p) throws Exception
	{
		//����� ���� �߰� (��ť��Ƽ)
		String userId=p.getName();
		MemberVO mvo=service.memberInfoData(userId);
		String userName=mvo.getUserId();
		//����� ���� �߰� (��ť��Ƽ)
		
		vo.setUserId(userId);
		vo.setUserName(userName);
		service.replyInsert(vo);
		return commonsReplyData(vo.getRno());
	}
	//����
	//����
	@GetMapping(value = "recipe/reply_delete_vue.do", produces="text/plain;charset=UTF-8")
	public String reply_delete(int no, int rno) throws Exception
	{
		service.replyDelete(rno);
		return commonsReplyData(rno);
	}
}
