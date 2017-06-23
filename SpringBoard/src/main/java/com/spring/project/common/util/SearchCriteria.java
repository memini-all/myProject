package com.spring.project.common.util;

/**
 * 검색 키워드, 검색타입을 관리하는 클래스
 * Criteria를 상속
 * @author adm
 *
 */
public class SearchCriteria extends Criteria {

	
	private String searchType; 	// 검색타입
	private String keyword; 	// 키워드
	
	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Override
	public String toString(){
		return super.toString() + " : SearchCriteria " + "[searchType = "+searchType+ ", keyword = "+keyword+ "]";
	}
}
