# Project


스프링 게시판 테이블 쿼리



-- 스프링 게시판 테이블
CREATE TABLE `tbl_sboard` (
  `brdno` INT(10) NOT NULL AUTO_INCREMENT COMMENT '글번호',
  `userno` INT(10) NOT NULL COMMENT '글 작성자',
  `title` VARCHAR(250) NOT NULL COMMENT '제목',
  `content` MEDIUMTEXT NOT NULL COMMENT '내용',
  `regdate` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '작성일',
  `updatedate` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '수정일',
  `deleteat` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '삭제여부',
  `viewcnt` INT(20) NOT NULL DEFAULT '0' COMMENT '조회수',
  `notice` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '공자사항',
  PRIMARY KEY (`brdno`)
)



-- 게시판 댓글 테이블
CREATE TABLE `tbl_sreply`(  
  `repno` INT(10) NOT NULL AUTO_INCREMENT COMMENT '댓글번호',
  `brdno` INT(10) NOT NULL COMMENT '글 번호',
  `userno` INT(10) NOT NULL COMMENT '작성자 번호',
  `rcontent` VARCHAR(1000) NOT NULL COMMENT '댓글 내용',
  `rgroup` INT(10) NOT NULL COMMENT '댓글 그룹',
  `rparent` INT(10) NOT NULL COMMENT '부모 댓글',
  `rdeletat` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '댓글 삭제여부',
  `regdate` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '댓글 작성일',
  `updatedate` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '댓글 수정일',
  PRIMARY KEY (`repno`),
  CONSTRAINT `brdno` FOREIGN KEY (`brdno`) REFERENCES `tbl_sboard`(`brdno`)
);



-- 파일 테이블
CREATE TABLE `tbl_sboardfile`(  
  `fileno` INT(10) NOT NULL AUTO_INCREMENT COMMENT '파일번호',
  `brdno` INT(10) NOT NULL COMMENT '글번호',
  `file_name` VARCHAR(300) NOT NULL COMMENT '저장된 파일명',
  `original_file_name` VARCHAR(300) NOT NULL COMMENT '원본 파일명',
  `file_size` INT(11) COMMENT '파일크기',
  `file_regdate` TIMESTAMP NOT NULL DEFAULT NOW() COMMENT '저장날짜',
  `file_delat` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '파일 삭제여부',
  PRIMARY KEY (`fileno`),
  CONSTRAINT `sbrdno` FOREIGN KEY (`brdno`) REFERENCES `tbl_sboard`(`brdno`)
);


-- 유저 테이블
CREATE TABLE `tbl_suser`(  
  `userno` INT(10) NOT NULL AUTO_INCREMENT COMMENT '사용자 번호',
  `userid` VARCHAR(30) NOT NULL COMMENT '아이디',
  `userpw` VARCHAR(100) NOT NULL COMMENT '비밀번호',
  `username` VARCHAR(50) NOT NULL COMMENT '이름',
  `email` VARCHAR(60) NOT NULL COMMENT '이메일',
  `authority` CHAR(2) NOT NULL COMMENT '권한',
  `profile_Img` VARCHAR(300) COMMENT '프로필 사진',
  `is_withdraw` CHAR(1) NOT NULL DEFAULT 'N' COMMENT '탈퇴여부',
  `loginFaile` INT(5) DEFAULT '0' COMMENT '로그인 실패횟수',
  `islock` CHAR(1) DEFAULT 'N' COMMENT '계정 잠금여부',
  `regdate` TIMESTAMP NULL DEFAULT NULL COMMENT '가입일',
  PRIMARY KEY (`userno`)
);


-- 로그인 히스토리 테이블
CREATE TABLE `tbl_login_history` (
  `hstno` INT(100) NOT NULL AUTO_INCREMENT COMMENT '로그인 기록번호',
  `userno` INT(20) NOT NULL COMMENT '로그인 사용자',
  `ldate` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '로그인 날짜',
  `conip` VARCHAR(30) NOT NULL COMMENT '접속 IP',
  `ltype` VARCHAR(8) NOT NULL COMMENT '유형 - 로그인,로그아웃',
  PRIMARY KEY (`hstno`),
  KEY `userno` (`userno`),
  CONSTRAINT `userno` FOREIGN KEY (`userno`) REFERENCES `tbl_suser` (`userno`)
) 


-- 댓글 계층구조 함수

DELIMITER $$
 
CREATE FUNCTION  fn_reply_structure() RETURNS INT
NOT DETERMINISTIC
READS SQL DATA
BEGIN
    DECLARE v_repno INT;
    DECLARE v_rparent INT;
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET @repno = NULL;
 
    SET v_rparent = @repno;
    SET v_repno = -1;
 
    IF @repno IS NULL THEN
        RETURN NULL;
    END IF;
 
    LOOP
      SELECT 
       MIN(repno)
      INTO @repno FROM
       tbl_sreply
      WHERE
       rparent = v_rparent
       AND repno > v_repno;
        
  IF @repno IS NOT NULL OR v_rparent = @start_with THEN
   SET @level = @level + 1;
   RETURN @repno;
  END IF;
    
  SET @level := @level - 1;
    
  SELECT 
   repno, rparent
  INTO v_repno , v_rparent 
  FROM
   tbl_sreply
  WHERE
   repno = v_rparent;
    END LOOP;
END
$$
DELIMITER ;


