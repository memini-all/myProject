package com.spring.project.reply.dto;

public class ReplyVO {

	private Integer repno; // 댓글번호
	private Integer brdno; // 글 번호
	private Integer userno; // 작성자 번호
	private String rcontent; // 댓글 내용
	private Integer rgroup; // 댓글 그룹
	private Integer rparent; // 부모 댓글
	private String rdeletat; // 댓글 삭제여부
	private String regdate; // 댓글 작성일
	private String updatedate; // 댓글 수정일
	private String uname; // 댓글 작성자 이름
	private String userimg; // 댓글 작성자 프로필이미지
	private int childCnt; // 답글의 개수
	private String pname; // 부모댓글 작성자 이름
	private String pdelete; // 부모댓글 삭제여부
	private int level; // 댓글 레벨

	public Integer getRepno() {
		return repno;
	}

	public void setRepno(Integer repno) {
		this.repno = repno;
	}

	public Integer getBrdno() {
		return brdno;
	}

	public void setBrdno(Integer brdno) {
		this.brdno = brdno;
	}

	public Integer getUserno() {
		return userno;
	}

	public void setUserno(Integer userno) {
		this.userno = userno;
	}

	public String getRcontent() {

		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public Integer getRgroup() {
		return rgroup;
	}

	public void setRgroup(Integer rgroup) {
		this.rgroup = rgroup;
	}

	public Integer getRparent() {
		return rparent;
	}

	public void setRparent(Integer rparent) {
		this.rparent = rparent;
	}

	public String getRdeletat() {
		return rdeletat;
	}

	public void setRdeletat(String rdeletat) {
		this.rdeletat = rdeletat;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(String updatedate) {
		this.updatedate = updatedate;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public String getUserimg() {
		return userimg;
	}

	public void setUserimg(String userimg) {
		this.userimg = userimg;
	}

	public int getChildCnt() {
		return childCnt;
	}

	public void setChildCnt(int childCnt) {
		this.childCnt = childCnt;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPdelete() {
		return pdelete;
	}

	public void setPdelete(String pdelete) {
		this.pdelete = pdelete;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
}
