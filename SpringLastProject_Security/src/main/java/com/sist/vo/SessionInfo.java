package com.sist.vo;

import lombok.Data;
/*
 * HttpSession세션대신에 => principal에 저장하려구함
 */
@Data
public class SessionInfo {
	private String userId;
	private String userName;
	private String sex;
	private String email;
	private String phone;
	private String address;
}
