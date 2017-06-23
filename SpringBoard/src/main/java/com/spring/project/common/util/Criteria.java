package com.spring.project.common.util;

public class Criteria {
	
	private int page;  			// 페이지 번호
	private int perPageNum;		// 한페이지당 보여지는 글의 수

	public Criteria() {
		this.page = 1;
		this.perPageNum = 10; // 1페이지당 10개의 글 보여지도록
	}

	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	public void setPerPageNum(int perPageNum) {

		if (perPageNum <= 0 || perPageNum > 100) {
			this.perPageNum = 10;
			return;
		}

		this.perPageNum = perPageNum;
	}

	public int getPage() {
		return page;
	}


	/**
	 * limit 구문의 시작위치 - 즉 1페이지, 2페이지 등을 나타냄 <br>
	 * 예를들어 1페이지 => limit 0, 10 이렇게 되는데 여기서 0이 시작위치이다.
	 * @return
	 */
	public int getPageStart() {
		// limit의 시작 데이터 = (페이지번호 -1) * 페이지당 보여지는 글개수
		return (this.page - 1) * perPageNum;
	}

	/**
	 * 한페이지당 보여지는 글의 개수를 가져온다.
	 * @return perPageNum 한페이지당 보여지는 글의 수
	 */
	public int getPerPageNum() {
		return perPageNum;
	}

	@Override
	public String toString() {
		return "Criteria [page = " + page + " , " + "perPageNum = " + perPageNum + "]";
	}
}
