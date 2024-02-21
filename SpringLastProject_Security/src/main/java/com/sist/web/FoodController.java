package com.sist.web;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FoodController {
	@GetMapping("food/food_find.do")
	public String food_food_find()
	{
		return "food/food_find";
	}
	
	@GetMapping("food/food_list.do")
	public String food_food_list()
	{
		return "food/food_list";
	}
	
	//food_list.jsp ��Ű�κ�	
	@GetMapping("food/food_before_list_detail.do")
	   public String food_before_detail(int fno,RedirectAttributes ra,
			   HttpServletResponse response)
	   {
		   Cookie cookie=new Cookie("food_"+fno, String.valueOf(fno));//cookie�� ���ڿ��� ������ ����
		   // Cookie(String,String)
		   cookie.setPath("/");
		   cookie.setMaxAge(60*60*24);
		   response.addCookie(cookie);
		   ra.addAttribute("fno", fno);
		   return "redirect:../food/food_list_detail.do";
	   }
	
	@GetMapping("food/food_list_detail.do")
	public String food_food_detail(int fno, Model model)
	{
		model.addAttribute("fno", fno);
		return "food/food_list_detail";
	}
	
	/*
	 * ����
�����Ǽ��� ���̺� PICK ��� �� ���ر������ Travellist ���� ������ �����θ����ø� ��Ʈ�� �¿�-�� ����� ��� ���� �����Ʈ��
�帣
���� �߶�� �� R&B/�ҿ� ��/��Ż POP ��/���� �Ϸ�Ʈ�δ�ī �ε� ��罺/��ũ Ʈ�� OST JPOP ���� Ŭ���� �������� �������
��Ȳ
��/��ٱ� �޽� ��/���� �� ī�� �ް�/���� ����̺� ��å � �Ͽ콺��Ƽ �û�� ���� �Ÿ� Ŭ�� ��� �غ� ���� ����� �ֵ�
����
�����ȯ �ܷο� ���� ���� �̺� ��ħ/���� ���� ���� ���� �� ���� ���� ��ħ ��� ��Ʈ����/¥�� �׸��� �߾� ��� �ູ �Ҿ� �г� ��� ����
��Ÿ��
���� �ų��� ����� ������ �׷���� �ε巯�� �θ�ƽ�� ������ ��Ȥ���� ��ȭ���� ������ ��ȯ���� ������ ������ �ÿ��� ������ ��ο� �������� �߷�Ÿ�ε��� ȭ��Ʈ����
����/����
�� ���� ���� �ܿ� ������ �߿ �帰�� ����³� ��� �Ȱ����� �����³�
	 */
	@GetMapping("food/food_recommend.do")
	public String food_recommend()
	{
		return "food/food_recommend";	
	}
}
