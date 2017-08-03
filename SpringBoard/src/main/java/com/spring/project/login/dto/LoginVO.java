package com.spring.project.login.dto;

/**
 * 로그인 정보를를 담을 VO 클래스
 * 
 * @author adm
 *
 */
public class LoginVO {

	private String userid; // 아이디
	private String userpw; // 비밀번호
	private boolean useCookie; // 로그인상태유지 체크
	private int loginFaile; // 로그인 실패횟수
	private String ldate; // 로그인날짜
	private String conip; // 접속 아이피
	private String ltype; // 로그인유형 - 로그인/로그아웃

	
	/*********** Getter & Setter ***********/
	
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

	public String getLdate() {
		return ldate;
	}

	public void setLdate(String ldate) {
		this.ldate = ldate;
	}

	public String getConip() {
		return conip;
	}

	public void setConip(String conip) {
		this.conip = conip;
	}

	public String getLtype() {
		return ltype;
	}

	public void setLtype(String ltype) {
		this.ltype = ltype;
	}

}
