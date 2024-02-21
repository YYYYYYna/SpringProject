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
	
	//암호화시키는 과정
	//security => 4,5(반드시 비밀빈호 암호화)
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@GetMapping("member/join.do")
	public String member_join()
	{
		return "member/join";
	}
	
	//암호화시키는 과정
	@PostMapping("member/join_ok.do")
	public String member_join_ok(MemberVO vo)
	{
		vo.setPhone(vo.getPhone1()+"-"+vo.getPhone2());
		String enPwd=encoder.encode(vo.getUserPwd());//암호화
		vo.setUserPwd(enPwd);
		mSercive.memberInsert(vo);
		//아래는 권한 부여 // 우리 플젝은 안해도 되는 부분인듯...
		mSercive.memberAutorityInsert(vo.getUserId()); 
		//위에서 id값은 회원가입할때 입력하는 id값임!!!! 이거 응용해서?
		//팔로우 클릭할때 아이디값을? sns_follow테이블에 insert 시키는걸로 해도 될듯
		return "main";
	}
	
	//로그인 이동
	//시큐리티 사용하면서 RequestMapping으로 바꿈
	@RequestMapping("member/login.do")
	public String member_login()
	{
		return "member/login";
	}
	
	//로그아웃 이동
//	@GetMapping("member/logout.do")
//	public String member_logout(HttpSession session)
//	{
//		//세션정보 해제
//		session.invalidate();
//		return "redirect:../main/main.do";
//	}
	
	
}
