package com.spring.project.common.util;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.MediaType;

/**
 * 이미지 타입을 체크하는 클래스
 * - 브라우저에서 파일을 다운로드 할 것인지, 보여줄 것인지 결정하기 위해 필요
 * @author adm
 */
public class MediaUtils {

	private static Map<String, MediaType> mediaMap;
	
	static{
		mediaMap = new HashMap<String, MediaType>();
		mediaMap.put("JPG", MediaType.IMAGE_JPEG);
		mediaMap.put("GIF", MediaType.IMAGE_GIF);
		mediaMap.put("PNG", MediaType.IMAGE_PNG);
	}
	
	
	/**
	 * MediaType이 저장된 Map에서 해당하는 이미지 확장자를 가져온다.
	 * 
	 * @param type 이미지 확장자
	 * @return
	 */
	public static MediaType getMediaType(String type){
		return mediaMap.get(type.toUpperCase());
	}
}
