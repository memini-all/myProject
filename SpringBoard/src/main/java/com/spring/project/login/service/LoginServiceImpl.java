package com.spring.project.login.service;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.project.login.dao.LoginDAO;
import com.spring.project.login.dto.LoginVO;
import com.spring.project.user.dto.UserVO;

@Service
public class LoginServiceImpl implements LoginService {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);

	@Inject
	private LoginDAO loginDAO;
	
	@Transactional
	@Override
	public Map<String, Object> login(LoginVO loginVO) throws Exception {
		
		Map<String, Object> resultMap = new HashMap<String,Object>();
		
		String loginID = loginVO.getUserid();
		
		// 1.계정유무 체크
		if( !loginDAO.isExistId(loginID) ){
			resultMap.put("msg", "FAIL"); 		// 계정 없을시 FAIL 메시지 전송
			return resultMap;
		}
		
		// 2. 계정잠김 확인
		if( loginDAO.selectAccountLock(loginID) ){
			resultMap.put("msg", "LOCK");  		// 잠긴 계정일 경우 LOCK 메시지 전송
			return resultMap;
		}
		
		// 3. 로그인 체크
		UserVO loginUser = loginDAO.login(loginVO);
		
		// 4. 비밀번호가 틀릴경우
		if(loginUser == null){
			
			// 로그인 실패횟수 체크
			int loginFailCnt = loginDAO.selectLoginFailCnt(loginID);
			
			// 5회 실패시
			if(loginFailCnt == 5){
				loginDAO.accountLock(loginID); 	// 계정을 잠근다.
				resultMap.put("msg", "LOCK");  	// LOCK 메시지 전송
				return resultMap;
			}
			
			// 비밀번호 틀릴경우 로그인 실패횟수 +1
			loginDAO.plusFailCount(loginID);
			resultMap.put("msg", "FAIL");  	// FAIL 메시지 전송
			return resultMap;
		}
		
		// 로그인 성공
		resultMap.put("msg", "SUCCESS");
		resultMap.put("userVO", loginUser);
		
		logger.info(">>>>>>>>>>> LOGIN SUCCESS");
		
		return resultMap;
	}
	


//	@Override
//	public int selectLoginFailCnt(String userID) throws Exception {
//		
//		return loginDAO.selectLoginFailCnt(userID);
//	}
//
//	@Override
//	public boolean selectAccountLock(String userID) throws Exception {
//
//		return loginDAO.selectAccountLock(userID);
//	}


	

}
