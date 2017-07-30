package com.spring.project.common.util;

public class UtilEtc {

	
	/**
	 * 비밀번호 자리수를 입력받아 해당 자리수 만큼의 임시 비밀번호를 생성한다.
	 * @param size 비밀번호 자리수
	 * @return
	 */
	public static String randomPw(int size) {

		char pwChars[] = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G',
				'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b',
				'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w',
				'x', 'y', 'z'};


		StringBuffer randomPw = new StringBuffer();

		
		for (int i = 0; i < size; i++) {
			
			int selectNum = (int) ( Math.random() * (pwChars.length) ); 
			randomPw.append( pwChars[selectNum] );
		}
		return randomPw.toString();
	}

}
