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

}
