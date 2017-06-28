package com.spring.project.common.dto;

public class FileVO {

	private Integer fileno; // 파일번호
	private Integer brdno; // 글번호
	private String file_name; // 파일명
	private String original_file_name; // 원본 파일명
	private int file_size; // 글내용
	private String file_regdate; // 파일 업로드날짜
	private String file_delat; // 파일 삭제여부

	/**
	 * 첨부된 파일 사이즈를 계산하여 문자열로 리턴한다.
	 * @return String fileSize 파일사이즈
	 */
	public String getCalculateSize() {
		
		double changeSize = 0;
		double kb_Size = ((double)file_size / 1024);
		
		if ( kb_Size > 1) {  // 계산된 kb값이 1보다 큰지 체크 - 크다면 kb 또는 mb, 작다면 byte
				
			if ( ( kb_Size / 1024) > 1 ) { 		
				changeSize = (( kb_Size / 1024 ) * 100) / 100;
				return String.format("%.2f%n", changeSize) + " Mb";
		
			}else{ 
				changeSize = ( kb_Size * 100 ) / 100 ;
				return String.format("%.2f%n", changeSize) + " Kb";
			} 
		}else{
			changeSize = (file_size * 100) / 100;
			return String.format("%.2f%n", changeSize)  + " Byte";
		}
	}
	
	public Integer getFileno() {
		return fileno;
	}

	public void setFileno(Integer fileno) {
		this.fileno = fileno;
	}

	public Integer getBrdno() {
		return brdno;
	}

	public void setBrdno(Integer brdno) {
		this.brdno = brdno;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public String getOriginal_file_name() {
		return original_file_name;
	}

	public void setOriginal_file_name(String original_file_name) {
		this.original_file_name = original_file_name;
	}

	public int getFile_size() {
		return file_size;
	}

	public void setFile_size(int file_size) {
		this.file_size = file_size;
	}

	public String getFile_regdate() {
		return file_regdate;
	}

	public void setFile_regdate(String file_regdate) {
		this.file_regdate = file_regdate;
	}

	public String getFile_delat() {
		return file_delat;
	}

	public void setFile_delat(String file_delat) {
		this.file_delat = file_delat;
	}
}
