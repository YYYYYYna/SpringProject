package com.sist.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.*;
import com.sist.vo.*;
import com.sist.service.*;
import com.sist.manager.*;

@Controller
@RequestMapping("freeboard/")
public class BoardController {
	
	//������ ����
	@Autowired
	private WordManager mgr;
	//������ ����
	@Autowired
	private FreeBoardService service;
	
	
	@GetMapping("list.do")
	public String freeboard_list()
	{
		return "freeboard/list";
	}
	
	@GetMapping("insert.do")
	public String freeboard_insert()
	{
		return "freeboard/insert";
	}
	
	//vue���� ���� �޴°� �ƴ϶� ����߿� no���� �޾ƾ��ϴϱ� model�� �����
	@GetMapping("detail.do")
	public String freeboard_detail(int no, Model model)
	{
		//������ ����
				FreeBoardVO vo=service.FreeBoardUpdateData(no);
				List<WordVO> list=mgr.wordListData(vo.getContent());
				model.addAttribute("list", list);
				
		model.addAttribute("no", no);
		return "freeboard/detail";
	}
	
	//�����ϱ�
	@GetMapping("update.do")
	public String board_update(int no,Model model)
	{	
	   model.addAttribute("no",no);
	   return "freeboard/update";
	}
}

