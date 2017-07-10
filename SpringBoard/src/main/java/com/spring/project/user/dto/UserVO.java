package com.spring.project.user.dto;

public class UserVO {

	private Integer userno; // 사용자 번호
	private String userid; // 아이디
	private String userpw; // 비밀번호
	private String username; // 이름
	private String email; // 이메일
	private String authority; // 권한 - AD: 관리자, US: 일반 사용자
	private String profile_Img; // 프로필 사진
	private String is_withdraw; // 탈퇴여부
	private int loginFaile; // 로그인 실패횟수
	private String islock; // 계정 잠금여부

	public Integer getUserno() {
		return userno;
	}

	public void setUserno(Integer userno) {
		this.userno = userno;
	}

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getProfile_Img() {
		return profile_Img;
	}

	public void setProfile_Img(String profile_Img) {
		this.profile_Img = profile_Img;
	}

	public String getIs_withdraw() {
		return is_withdraw;
	}

	public void setIs_withdraw(String is_withdraw) {
		this.is_withdraw = is_withdraw;
	}

	public int getLoginFaile() {
		return loginFaile;
	}

	public void setLoginFaile(int loginFaile) {
		this.loginFaile = loginFaile;
	}

	public String getIslock() {
		return islock;
	}

	public void setIslock(String islock) {
		this.islock = islock;
	}

}
