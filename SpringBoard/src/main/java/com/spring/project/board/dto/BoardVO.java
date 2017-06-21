package com.spring.project.board.dto;


public class BoardVO {

	private Integer brdno;
	private Integer userno;
	private String title;
	private String content;
	private String regdate;
	private String updatedate;
	private String deleteat;
	private Integer viewcnt;

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public String getDeleteat() {
		return deleteat;
	}

	public void setDeleteat(String deleteat) {
		this.deleteat = deleteat;
	}

	public Integer getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(Integer viewcnt) {
		this.viewcnt = viewcnt;
	}

}
