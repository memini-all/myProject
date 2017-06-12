package com.spring.project.board.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * 페이지 계산
 * 
 * @author adm
 *
 */
public class PageCalculate {

	private int totalCount; // 전체 글 개수
	private int totalPage; // 전체 페이지 수
	private int startPage; // 시작 페이지
	private int endPage; // 끝 페이지
	private boolean next; // [다음] 표시 여부
	private boolean prev; // [이전] 표시 여부
	private int displayPageNum = 5; // 글 목록 하단에 출력되는 페이지번호 개수

	private Criteria cri;

	/**
	 * 페이지 계산
	 */
	private void calcData() {

		// 현재 페이지 번호
		int currentPage = cri.getPage();
		// 한 페이지당 보여지는 글의 수
		int currentPageCnt = cri.getPerPageNum();

		// 끝페이지
		endPage = (int) (Math.ceil(currentPage / (double) displayPageNum) * displayPageNum);

		// 시작페이지 = (끝페이지 - 페이지번호 수) +1
		startPage = (endPage - displayPageNum) + 1;

		// 전체페이지
		totalPage = (int) (Math.ceil(totalCount / (double) currentPageCnt));

		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 이전, 다음 계산
		prev = startPage == 1 ? false : true;
		next = endPage * currentPageCnt >= totalCount ? false : true;

		// System.out.println("tempEndPage : "+tempEndPage);
	}


	/**
	 * 검색조건이 없는 페이지 URL 생성
	 * @param page 현재 페이지
	 * @return
	 */
	public String makeURI(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
									.queryParam("page", page)
									.queryParam("perPageNum", cri.getPerPageNum())
									.build();

		 //System.out.println("\nURL : "+uriComponents.toUriString()+"\n");

		return uriComponents.toUriString();
	}

	/**
	 * 검색조건이 있는 페이지 URL 생성
	 * @param page 현재 페이지
	 * @return
	 */
	public String makeSearchURI(int page) {

		UriComponents uriComponents = UriComponentsBuilder.newInstance()
									.queryParam("page", page)
									.queryParam("perPageNum", cri.getPerPageNum())
									.queryParam("searchType", ((SearchCriteria) cri).getSearchType())
									.queryParam("keyword", encoding(((SearchCriteria) cri).getKeyword()))
									.build();

		//System.out.println("\nURL : "+uriComponents.toUriString()+"\n");
		
		return uriComponents.toUriString();
	}


	/**
	 * 인코딩 처리
	 * @param keyword 검색할 키워드
	 * @return
	 */
	private String encoding(String keyword) {

		// 키워드값이 없으면 공백을 리턴
		if (keyword == null || keyword.trim().length() == 0) {
			return "";
		}

		try {
			return URLEncoder.encode(keyword, "UTF-8");
		} catch (UnsupportedEncodingException ue) {
			return "";
		}
	}

	@Override
	public String toString() {
		return "PageCalculate [Start = " + startPage + " , " + "end = " + endPage + "]";
	}

	/*********** getter & setter *************/

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		calcData();
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public int getDisplayPageNum() {
		return displayPageNum;
	}

	public void setDisplayPageNum(int displayPageNum) {
		this.displayPageNum = displayPageNum;
	}

	public Criteria getCri() {
		return cri;
	}

	public void setCri(Criteria cri) {
		this.cri = cri;
	}

}
