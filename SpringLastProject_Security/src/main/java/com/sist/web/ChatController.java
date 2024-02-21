package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.sist.service.Memberservice;
import com.sist.vo.MemberVO;

@Controller
public class ChatController {
	@Autowired
	private Memberservice service;
	
	@GetMapping("chat/chat.do")
	public String chat_chat(Model model)
	{
		///MemberVO vo=service.memberInfo(p.getName());
		//model.addAttribute("vo", vo);
		return "site/chat/chat"; //tiles가 아니라 viewResolver에서 처리함
	}
}
