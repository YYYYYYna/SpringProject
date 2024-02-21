package com.sist.web;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sist.service.Memberservice;
import com.sist.vo.MemberVO;

@Controller
public class MemberController {
	@Autowired
	private Memberservice mSercive;
	
	//��ȣȭ��Ű�� ����
	//security => 4,5(�ݵ�� ��к�ȣ ��ȣȭ)
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join()
	{
		return "member/join";
	}
	
	//��ȣȭ��Ű�� ����
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo)
	{
		vo.setPhone(vo.getPhone1()+"-"+vo.getPhone2());
		String enPwd=encoder.encode(vo.getUserPwd());//��ȣȭ
		vo.setUserPwd(enPwd);
		mSercive.memberInsert(vo);
		//�Ʒ��� ���� �ο� // �츮 ������ ���ص� �Ǵ� �κ��ε�...
		mSercive.memberAutorityInsert(vo.getUserId()); 
		//������ id���� ȸ�������Ҷ� �Է��ϴ� id����!!!! �̰� �����ؼ�?
		//�ȷο� Ŭ���Ҷ� ���̵���? sns_follow���̺� insert ��Ű�°ɷ� �ص� �ɵ�
		return "main";
	}
	
	//�α��� �̵�
	//��ť��Ƽ ����ϸ鼭 RequestMapping���� �ٲ�
	@RequestMapping("member/login.do")
	public String member_login()
	{
		return "member/login";
	}
	
	//�α׾ƿ� �̵�
//	@GetMapping("member/logout.do")
//	public String member_logout(HttpSession session)
//	{
//		//�������� ����
//		session.invalidate();
//		return "redirect:../main/main.do";
//	}
	
	
}
