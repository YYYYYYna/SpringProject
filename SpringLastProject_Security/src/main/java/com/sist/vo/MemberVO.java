package com.sist.vo;
import java.util.*;
import lombok.Data;

@Data
public class MemberVO {
	private int enabled;
	private String userId,userName,userPwd,sex,birthday,email,post,addr1,addr2,phone,phone1,phone2,content,reg_dbday,mod_dbday,last_dbday;
	private Date regdate,modifydate,lastLogin;
	private String authority,msg;//join걸려면 일단 다른테이블이라도 가져와서 여기 적어두면됨~!~!~!
	                        //msg가 NOID 이거 가져가는거암
}
