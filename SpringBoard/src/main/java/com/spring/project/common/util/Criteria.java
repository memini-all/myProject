package com.spring.project.common.util;


/**
 * 페이지 정보를 처리하는 클래스
 * @author adm
 *
 */
public class Criteria {
	
	private int page;  			// 페이지 번호
	private int perPageNum;		// 한페이지당 보여지는 글의 수

	
	/**
	 * page = 1, perPageNum = 10 으로 초기화
	 */
	public Criteria() {
		this.page = 1;
		this.perPageNum = 10; // 1페이지당 10개의 글 보여지도록
	}

	

	/**
	 * 페이지를 세팅한다. 만약 페이지가 0보다 작다면 페이지를 1로 변경한다.
	 * @param page 페이지
	 */
	public void setPage(int page) {
		if (page <= 0) {
			this.page = 1;
			return;
		}

		this.page = page;
	}

	
	
	/**
	 * 한페이지당 보여지는 글의 수를 세팅한다.<br>
	 * 만약 보여지는 글의 수가 0보다 작거나 100보다 크면 보여지는 수를 10으로 변경한다.
	 * 
	 * @param perPageNum 한페이지당 보여지는 글의 수
	 */
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
	 * 예를들어 1페이지는 limit 0, 10 이렇게 되는데 여기서 0이 시작위치이다.
	 * 
	 * @return perPageNum 한페이지당 보여지는 글의 수
	 */
	public int getPageStart() {
		// limit의 시작 데이터 = (페이지번호 -1) * 페이지당 보여지는 글개수
		return (this.page - 1) * perPageNum;
	}

	
	
	/**
	 * 한페이지당 보여지는 글의 개수를 가져온다.
	 * 
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
