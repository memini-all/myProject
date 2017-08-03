package com.spring.project.common.controller;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.spring.project.board.dto.BoardVO;
import com.spring.project.common.util.FileUtils;
import com.spring.project.common.util.MediaUtils;
import com.spring.project.user.service.UserService;

/**
 * 공통된 URL을 처리할 CommonController
 * @author adm
 *
 */
@Controller
public class CommonController {

	/**
	 * common-context.xml에 등록한 프로필 이미지 업로드 경로
	 */
	@Resource(name = "profileImgPath")
	private String profileImgPath;

	/**
	 * common-context.xml에 등록한 파일 업로드 경로
	 */
	@Resource(name = "uploadPath")
	private String uploadPath;

	/**
	 * 파일업로드를 처리할 Utils 클래스
	 */
	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Inject
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	
	
	/**
	 * /index URL로 접근시 글목록 화면을 보여준다.
	 * 
	 * @return 글 목록 화면(redirect:/board/list)
	 * @throws Exception
	 */
	@RequestMapping(value = "/index")
	public String boardList() throws Exception {

		//logger.info(">>>>>>> CommonController");
		return "redirect:/board/list";
	}

	
	
	/**
	 * 파일 다운로드
	 * 
	 * @param fileName 저장된 파일명
	 * @param downName 원본 파일명
	 * 
	 * @return {@link ResponseEntity} 파일데이터
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/fileDownload")
	public ResponseEntity<byte[]> fileDownload(String fileName, String downName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		try {
			HttpHeaders headers = new HttpHeaders();

			in = new FileInputStream(uploadPath + "/" + fileName);

			headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
			headers.add("Content-Disposition",
					"attachment; filename=\"" + new String(downName.getBytes("UTF-8"), "ISO-8859-1") + "\"");

			// 리턴 타입은 파일 데이터이다.
			entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

		} catch (Exception e) {
			e.printStackTrace();
			entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
		} finally {
			in.close();
		}

		return entity;
	}

	
	
	/**
	 * 사용자의 프로필 이미지를 가져온다.
	 * 
	 * @param userno 사용자 번호
	 * 
	 * @return {@link ResponseEntity} 이미지 파일데이터
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/profile/image/{userno}")
	public ResponseEntity<byte[]> getImage(@PathVariable int userno) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		String fileName = service.selectProfileImg(userno);

		if (fileName != null) {
			// 확장자 추출
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			// 확장자에 해당하는 MediaType을 가져온다.
			MediaType mType = MediaUtils.getMediaType(formatName);

			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(mType);

				in = new FileInputStream(profileImgPath + "/" + fileName);

				// 리턴 타입은 파일 데이터이다.
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			} finally {
				in.close();
			}
		}

		return entity;
	}

	
	
	/**
	 * ckeditor 이미지 파일 업로드
	 * 
	 * @param request {@link HttpServletRequest}
	 * @param response {@link HttpServletResponse}
	 * @throws Exception
	 */
	@RequestMapping("/uploadckedit")
	public void uploadCkeditor(HttpServletRequest request, HttpServletResponse response) throws Exception {

		PrintWriter printWriter = null;
		String callback = request.getParameter("CKEditorFuncNum");

		// fileUtils을 통해 실제 저장된 이미지이름과 저장폴더를 가져온다.
		Map<String, String> resultMap = fileUtils.ckeditImgFileInfo(uploadPath, request);
		String fileName = resultMap.get("saveFileName");
		String path = resultMap.get("savedPath");

		if (fileName != null) {

			String url = request.getRequestURL().toString();

			Integer inx = url.lastIndexOf("/");

			// url에서 uploadckedit 부분을 제거
			url = url.substring(0, inx);
			url = url + "/ckeditor/img?fileName=" + path + "/" + fileName;

			String script = "<script>window.parent.CKEDITOR.tools.callFunction(";
			script += callback;
			script += ", '";
			script += url;
			script += " ' , 'Image Upload Success !!'";
			script += ") </script>";

			printWriter = response.getWriter();
			printWriter.println(script);
			printWriter.flush();
		}
	}

	
	/**
	 * ckeditor로 업로드된 이미지 보기
	 * 
	 * @param fileName 업로드된 이미지 파일명
	 * 
	 * @return {@link ResponseEntity} 이미지 파일데이터
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("/ckeditor/img")
	public ResponseEntity<byte[]> viewImg(String fileName) throws Exception {

		InputStream in = null;
		ResponseEntity<byte[]> entity = null;

		if (fileName != null) {
			// 확장자 추출
			String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);

			// 확장자에 해당하는 MediaType을 가져온다.
			MediaType mType = MediaUtils.getMediaType(formatName);

			try {

				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(mType);

				in = new FileInputStream(uploadPath + "/" + fileName);

				// 리턴 타입은 파일 데이터이다.
				entity = new ResponseEntity<byte[]>(IOUtils.toByteArray(in), headers, HttpStatus.CREATED);

			} catch (Exception e) {
				e.printStackTrace();
				entity = new ResponseEntity<byte[]>(HttpStatus.BAD_REQUEST);
			} finally {
				in.close();
			}
		}

		return entity;
	}

}
