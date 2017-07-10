package com.spring.project.login.dto;

/**
 * 화면에서 전달되는 데이터를 수집하는 용도의 클래스
 * 
 * @author adm
 *
 */
public class LoginVO {

	private String userid; // 아이디
	private String userpw; // 비밀번호
	private boolean useCookie; // 로그인상태유지 체크
	private int loginFaile; // 로그인 실패횟수
	private String ip;
	
	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUserpw() {
		return userpw;
	}

	public void setUserpw(String userpw) {
		this.userpw = userpw;
	}

	public boolean isUseCookie() {
		return useCookie;
	}

	public void setUseCookie(boolean useCookie) {
		this.useCookie = useCookie;
	}

	public int getLoginFaile() {
		return loginFaile;
	}

	public void setLoginFaile(int loginFaile) {
		this.loginFaile = loginFaile;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

}
