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

import com.spring.project.common.util.FileUtils;
import com.spring.project.common.util.MediaUtils;
import com.spring.project.user.service.UserService;

@Controller
public class CommonController {

	@Resource(name = "profileImgPath")
	private String profileImgPath;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Resource(name = "fileUtils")
	private FileUtils fileUtils;

	@Inject
	private UserService service;

	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);

	@RequestMapping(value = "/index")
	public String boardList() throws Exception {

		logger.info(">>>>>>> CommonController");
		return "redirect:/board/list";
	}

	/**
	 * 브라우저에서 파일을 다운로드 할수 있게 하는 메서드
	 * @param fileName : 저장된 파일명
	 * @param downname : 원본 파일명
	 * @return
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
	 * 프로필 이미지를 가져온다.
	 * @param userno
	 * @return
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
	 * @param request
	 * @param response
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
	 * @param fileName
	 * @return
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
