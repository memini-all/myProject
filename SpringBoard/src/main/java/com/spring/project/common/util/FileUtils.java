package com.spring.project.common.util;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;


public class FileUtils {

	private static final Logger logger = LoggerFactory.getLogger(FileUtils.class);

	/**
	 * 파일을 업로드한다.
	 * @param brdno 글번호
	 * @param uploadPath 파일업로드 경로
	 * @param request
	 * @return List<Map<String,Object>> 파일정보를 담은 list
	 * @throws Exception
	 */
	public List<Map<String,Object>> uploadFileInfo(Integer brdno, String uploadPath, HttpServletRequest request) throws Exception{

		// MultipartHttpServletRequest에서 파일명을 추출한다.
		MultipartHttpServletRequest multipartReq = (MultipartHttpServletRequest)request;
    	Iterator<String> iterator = multipartReq.getFileNames();
    	
    	MultipartFile multipartFile = null;
    	String originalFileName = null;
    	String savedFileName = null;
    	
    	List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
        Map<String, Object> listMap = null; 
        
        // randomUUID() : 중복되지 않는 난수 생성
     	UUID uid = UUID.randomUUID();
        
        // 파일 저장될 경로
        String savedFilePath = calcPath(uploadPath);
        
        logger.info(">>>>>>> 파일경로 ........"+uploadPath);
        
        while(iterator.hasNext()){
        	// 파일을 가져온다.
        	multipartFile = multipartReq.getFile(iterator.next());
        	
        	if(multipartFile.isEmpty() == false){
        		
        		originalFileName = multipartFile.getOriginalFilename();		// 원본 파일명
        		savedFileName = uid.toString() + "_" + originalFileName;	// 저장될 파일명

        		logger.info(">>>>>>> 원본파일명 ........"+originalFileName);
        		logger.info(">>>>>>> 파일사이즈 ........"+multipartFile.getSize());
        		
        		File file = new File(uploadPath + savedFilePath, savedFileName);
        		
        		// 파일생성
        		multipartFile.transferTo(file);  
        		
        		listMap = new HashMap<String,Object>();
        		listMap.put("brdno", brdno);
        		listMap.put("file_name", savedFileName);
        		listMap.put("original_file_name", originalFileName);
        		listMap.put("file_size", multipartFile.getSize());
        		list.add(listMap);
        	}
        }
		return list;
        
	}
	
	/**
	 * 파일이 저장될 폴더 생성에 필요한 년/월/일 정보를 생성한다.
	 * @param uploadPath
	 * @return
	 */
	private static String calcPath(String uploadPath) {

		Calendar cal = Calendar.getInstance();

		// 년을 얻는다.
		String yearPath = File.separator + cal.get(Calendar.YEAR);
		// 월을 얻는다.
		String monthPath = yearPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.MONTH) + 1);
		// 일을 얻는다.
		String datePath = monthPath + File.separator + new DecimalFormat("00").format(cal.get(Calendar.DATE));

		logger.info(">>>>>>>>>>>>> datePath : " + datePath);
		
		logger.info("\n        >>>>>>>>>>>>> UploadFileUtils.calcPath() 메서드 실행\n");
		
		makeDir(uploadPath, yearPath, monthPath, datePath);
		
		return datePath;
	}
	
	/**
	 * 년/월/일 정보와 파일 업로드 경로를 이용해서 폴더를 생성한다.
	 * @param uploadPath
	 * @param paths
	 */
	private static void makeDir(String uploadPath, String... paths) {
		
		if (new File(paths[paths.length - 1]).exists()) {
			return;
		}
		
		logger.info("\n        >>>>>>>>>>>>> UploadFileUtils.makeDir() 메서드 실행\n");

		for (String path : paths) {
			File dirPath = new File(uploadPath + path);
			
			logger.info("        >>>>>>>>>>>>> makeDir() File dirPath : " + dirPath);
			
			if (!dirPath.exists()) {
				dirPath.mkdirs(); // 폴더 생성
			}
		}
	}
}