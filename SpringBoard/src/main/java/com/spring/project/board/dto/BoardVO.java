package com.spring.project.board.dto;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class BoardVO {

	private Integer brdno;
	private Integer userno;
	private String title;
	private String content;
	private Date regdate;
	private Date updatedate;
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

	public Date getRegdate() {
		
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public Date getUpdatedate() {
		return updatedate;
	}

	public void setUpdatedate(Date updatedate) {
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
