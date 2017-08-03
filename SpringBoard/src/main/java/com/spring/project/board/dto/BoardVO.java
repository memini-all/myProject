package com.spring.project.board.dto;

public class BoardVO {

	private Integer brdno; // 글번호
	private Integer userno; // 작성자 번호
	private String username; // 글 작성자
	private String title; // 글제목
	private String content; // 글내용
	private String regdate; // 작성일
	private String updatedate; // 수정일
	private String deleteat; // 삭제여부
	private String notice; // 공지사항여부
	private int viewcnt; // 조회수
	private int replycnt; // 댓글수
	private int fileCnt; // 파일개수

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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getNotice() {
		return notice;
	}

	public void setNotice(String notice) {
		this.notice = notice;
	}

	public int getViewcnt() {
		return viewcnt;
	}

	public void setViewcnt(int viewcnt) {
		this.viewcnt = viewcnt;
	}

	public int getReplycnt() {
		return replycnt;
	}

	public void setReplycnt(int replycnt) {
		this.replycnt = replycnt;
	}

	public int getFileCnt() {
		return fileCnt;
	}

	public void setFileCnt(int fileCnt) {
		this.fileCnt = fileCnt;
	}
}
