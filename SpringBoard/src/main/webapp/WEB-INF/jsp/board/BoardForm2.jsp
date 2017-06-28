<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    
    <script src="//code.jquery.com/jquery-2.2.3.min.js"></script>
    
    <!-- Bootstrap Core CSS -->
    <link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="/resources/sb-admin/vendor/metisMenu/metisMenu.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="/resources/sb-admin/dist/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="/resources/sb-admin/vendor/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- jQuery -->
    <script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="/resources/sb-admin/vendor/metisMenu/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="/resources/sb-admin/dist/js/sb-admin-2.js"></script>
    
    <script src="/resources/ckeditor/ckeditor.js"></script>
    
    <script>
 
	
	$(document).ready(function() {		
		CKEDITOR.replace( 'content', { 'filebrowserUploadUrl': 'upload4ckeditor'});
	});

	</script>
    
</head>
<body>

	<div id="wrapper">

		<!-- 네비게이션 부분 -->
		<jsp:include page="../common/navigation.jsp" />

		<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><i class="fa fa-files-o fa-fw"></i> </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            						<!-- enctype="multipart/form-data"을 form 태그에 적용시 자동적으로 VO와 바인딩이 안됨, 
            							pom.xml과 설정파일(main-servlet.xml)에 파일업로드 관련 부분을 모두 추가해야함 -->
            <!-- /.row -->
            <div class="row">
            	<form id="registerForm"  name="registerForm" role="form" enctype="multipart/form-data" >
					<div class="panel panel-default">
	                    <div class="panel-body">
	                    	<div class="row form-group">
	                            <label class="col-lg-1">제목</label>
	                            <div class="col-lg-9">
	                            	<input type="text" class="form-control" id="title" name="title" size="70" maxlength="250" placeholder="Enter Title">
	                            	
	                            	<!-- 공지사항 
	                            	<c:if test="${bgInfo.bgnotice=='Y'}">
									 	<label>
				                        	<input type="checkbox" name="brdnotice" value="Y" <c:if test="${boardInfo.brdnotice=='Y'}">checked="checked"</c:if>/>
				                        	<s:message code="common.notice"/>
				                        </label>
	                            	</c:if>
	                            	-->
	                            </div>
	                        </div>
	                    	<div class="row form-group">
	                            <label class="col-lg-1">내용</label>
	                            <div class="col-lg-9">
	                            	<textarea id="content" class="form-control" name="content" rows="10" cols="60"></textarea>
	                            </div>
	                        </div>   
	                        <!--                      
	                    	<div class="row form-group">
	                            <label class="col-lg-1">파일</label>
	                            <div id="fileDiv" class="col-lg-9">
	                            	
	                            	<c:forEach var="listview" items="${listview}" varStatus="status">
										<input type="checkbox" name="fileno" value="<c:out value="${listview.fileno}"/>">	
				            			<a href="fileDownload?filename=<c:out value="${listview.filename}"/>&downname=<c:out value="${listview.realname }"/>"> 							 
										<c:out value="${listview.filename}"/></a> <c:out value="${listview.size2String()}"/><br/>
									</c:forEach>					

									 -->
									 <!-- 파일첨부
    					 	 		 <input id="files-upload" type="file" multiple="multiple" /> 
	                            </div>
	                        </div>  
	                         -->
	                         <!-- 
	                        <div class="row form-group">
	                        	<label class="col-lg-1"></label>
	                        	<div class="col-lg-9">
									<ul id="file-list">  
									    	<li class="no-items">(파일이 선택되지 않음)</li>  
									</ul>
								</div>
							</div>
							 -->
							 
	                        <!-- 테스트 -->		
	                        <div class="row form-group">
	                        	<label class="col-lg-1">파일</label>						
		                     	<div class="col-lg-9 fileUploadSection">			
									<div class="fileUpWrapper">				
										<div id="fileUpBtnWrapper" class="fileUpBtnWrapper" style="position:relative; left: 2px; width:70px;height:25px; overflow:hidden;border:1px solid #ccc;text-align:center;">				 								
											<%-- input의 id명 뒤의 숫자를 변경하지 말것(인덱스 번호로 사용됨) --%>				
											<input id="file0" onchange="makeUploadElem(this)" class='file' type="file" name="upFile[]" style="position: absolute;right:0px;top:0px; opacity:0; filter: alpha(opacity=0);cursor: pointer;outline:none;"/>					 				
											<span class="fileUp">파일첨부</span>
										 </div>
										 
										<div class="addAttachFileSection">
											<ui class="dropdown-menu attachFileList" role="menu" aria-labelledby="dropdownMenu">
											
											</ui>
										</div>
												
									</div>
								</div>	
							</div>
	                        <!-- 테스트 끝 -->
	                    </div>
	                </div>
					
<!-- 					
					<div class="fileUploadSectionWrapper">
	<form id="fileform" enctype="multipart/form-data" method="post">   
-->
		<!-- 첨부파일 
		<div class="fileUploadSection">			
			<div class="fileUpWrapper">				
				<div id="fileUpBtnWrapper" class="fileUpBtnWrapper" style="position:relative; left: 2px; width:40px;height:25px; overflow:hidden;border:1px solid #ccc;text-align:center;">				 								
					<%-- input의 id명 뒤의 숫자를 변경하지 말것(인덱스 번호로 사용됨) --%>				
					<input id="file0" onchange="makeUploadElem(this)" class='file' type="file" name="upFile[]" style="position: absolute;right:0px;top:0px; opacity:0; filter: alpha(opacity=0);cursor: pointer;outline:none;"/>					 				
					<span class="fileUp">파일</span>
				 </div>
			</div>
		</div>		
-->		
		<!-- 첨부추가/제거 
		<div class="addAttachFileSection">
			<ul class="attachFileList">
			</ul>
		</div>				
	</form>			
</div>
-->



					
					
					<!-- 작성자 : 현재는 hidden으로 임의의 값을 넣음 -->
					<!-- 추후 세션에 저장된 로그인한 아이디를 넘겨주고 controller에서 그걸 토대로 회원번호를 검색하도록 설정 -->
					<input type="hidden" name="userno" value="01" />
				</form>
				
				<button id="addFileBtn" class="btn btn-outline btn-primary">파일추가</button>
				<button type="submit" id="registBtn" class="btn btn-outline btn-primary">작성</button>
				<button id="cancelBtn" class="btn btn-outline btn-primary">취소</button>
                
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->
    
    
    <script type="text/javascript">
//input file의 인덱스번호로 쓰임(전역변수)
var fileIndex = 0;	

$(function(){
	var fileUploadElem = $("input.file");
	var fileUploadBtnElem = $("span.sendData");
	var fileUploadFrm = $("#fileform");	
		
	//ajax Progress image view Elem
	var viewLoadingImgElem = $("div#viewLoading");
	$(viewLoadingImgElem).hide();	//초기로딩시에는 이미지를 숨긴다.
	
	var intervalID = 0;
	//ajax 요청시작과 완료시의 프로그레스 이미지 element의 동작
	$(viewLoadingImgElem).ajaxStart(function(){
		// 로딩이미지의 위치 및 크기조절	
		$(viewLoadingImgElem).css('position', 'absolute');
		$(viewLoadingImgElem).css('left', $("body").offset().left);
		$(viewLoadingImgElem).css('top', $("body").offset().top);
		$(viewLoadingImgElem).css('width', $(document).width());
		$(viewLoadingImgElem).css('height', $(document).height());
		
		
		intervalID = setInterval(function(){			
			getFileUploadProgress();	//ajax요청중에 파일업로드 상태를 주기적으로 요청한다.	
		},50);
		$(this).fadeIn(250);
	}).ajaxStop(function(){		
		clearInterval(intervalID); //Stop updating		
		$(this).fadeOut(250);
	});					

	$(fileUploadBtnElem).click(function(){
		$(fileUploadFrm).ajaxSubmit({			
			url : '/testproj/fileupload.do',
			type : 'POST',
			data : $(fileUploadFrm).serialize(),
			success : function(data){
				deleteFileListAndFileElems();											
				clearInterval(intervalID); //Stop updating
				//alert("전송 완료 되었습니다.");
			},error : function(){
				deleteFileListAndFileElems();
				clearInterval(intervalID); //Stop updating	
				//alert("전송 실패 했습니다.");
			}
		});	
	});
    
	//파일업로드 상태를 주기적으로 확인해서 가져온다.
	var getFileUploadProgress = function(){
		$.ajax({
			url : '/testproj/uploadstatus.do',
			success : function(data){
				var jsonData = eval('('+ data +')');

				$(viewLoadingImgElem).html(										
						"<div class='progressTitle'>" +
						"	<span><strong>업로드 진행상태</strong></span>" +		
						"</div>" +
						"<div class='progressWrapper'>" +
						"	<div class='progresspercent'>" +
						"		<span class='percentwrapper'>"+
						"			<span class='pgbar'>&nbsp;</span>"+
						"			<span class='pgpercent'><strong>"+ jsonData.percent+"%</strong></span>" +				
						"		</span>" +
						"	</div>"+
						"	<div class='progressfilereadsize'>"+
						"		<span class='readsize'>" + jsonData.bytesread + "<strong> bytes</strong></span>" +
						"		<span class='divider'><strong>/</strong></span>" +
						"		<span class='filelength'>" + jsonData.contentlength + "<strong> bytes</strong></span>" +
						"	</div>" +
						"	<div class='progressSpeed'>" +
						"		<span class='kbps'>" + jsonData.kbps + "<strong> kbps</strong></span>" +
						"	</div>" +								
						"</div>"	);										
				$(viewLoadingImgElem).find("div.progresspercent span.pgbar").width(jsonData.percent+"%").addClass("pgbarbgcolor");				
			}
		});
	};	
});


//업로드 파일 지정
var makeUploadElem = function(fileElemObj){
	fileIndex++;	//global var	
	var addAttachFileSectionElem = $("div").filter(".addAttachFileSection");
	//var attachFileListElem = $(addAttachFileSectionElem).find("ul");	

	
	var targetElem=$("div#fileUpBtnWrapper");
	var fileElem=$("<input id='file"+fileIndex+"' class='file' type='file' name='upFile[]' style='position: absolute;right:0px;top:0px; opacity:0; filter: alpha(opacity=0);cursor: pointer;outline:none;'/>");
	var fileUpBtnElem = $("<span class='fileUp'>&nbsp;파일</span>");
	
	$(fileElem).change(function(){
		makeUploadElem(this);	//recursive				
	});
	
	$(targetElem).append(fileElem);
	$(targetElem).append(fileUpBtnElem);
	//$(fileElemObj).hide();	
	
	var icon = "   <button class='btn btn-default btn-xs'><i class='fa fa-times' style='font-size:1em;color:red;' aria-hidden='true'></i></button>";
	
	
	//파일 목록 추가 / 삭제
	var fileElemObjVal = $(fileElemObj).val();	
	$(addAttachFileSectionElem).append(
		"<li class='list-group-item'>" +			
			"<span class='attachFileName'>"+fileElemObjVal+"</span>" +
			"<span class='removeAttach' onClick='deleteFileList(this,"+fileIndex+");'>"+icon+"</span>" +
		"</li>"
	);			
	
};
//업로드 파일 리스트 삭제
var deleteFileList = function(fileListElem,fileElemIndex){
	//기본으로 마크업된 input file의 인덱스 번호가 0으로 시작되는데
	//전역변수로 증가 시킨 상태이므로 -1을 시켜서 인덱스 번호를 맞춰준다.
	var fileElemIdx = fileElemIndex - 1;
	var fileUpBtnWrapElem = $("div").filter("#fileUpBtnWrapper");
	
	$(fileUpBtnWrapElem).find("input:file").filter("#file"+fileElemIdx).remove();	
	$(fileListElem).parent().remove();
	
	$("div#debug").text($("div.fileUploadSection").html());
};

//전송이 완료되거나 실패시에도 input file과 li를 모두삭제 시켜주고
//전역변수로 사용된 인덱스를 초기화 시켜준다.
var deleteFileListAndFileElems = function(){
	var fileUploadSectionElem = $("div").filter(".fileUpWrapper");
	var addAttachFileSectionElem = $("div").filter(".addAttachFileSection").find("ul");
	$(fileUploadSectionElem).children().remove();
	$(addAttachFileSectionElem).children().remove();
	fileIndex = 0;
};
</script>
    
    <script type="text/javascript">
    
    /* 파일업로드 테스트  */
    
    var filesUpload = document.getElementById("files-upload"), 
        fileList = document.getElementById("file-list"); 
     
    function traverseFiles (files) { 
      var li, 
          file, 
          fileInfo; 
      fileList.innerHTML = ""; 
      
      var icon = "<i style='color:red;' class='fa fa-times' aria-hidden='true'></i>";
    	 
    	  
      
      for (var i=0, il=files.length; i<il; i++) { 
        li = document.createElement("li"); 
        file = files[i]; 
        fileInfo = "<div><strong>Name:</strong> "
                     + file.name +"  <span class='removeAttach' onClick='deleteFileList(this,"+i+");'>"
                     +icon+"</span></div>"; 
         
                     
        fileInfo += "<div><strong>Size:</strong> "
                      + file.size + " bytes</div>"; 

                      
        li.innerHTML = fileInfo; 
        fileList.appendChild(li); 
      }; 
    }; 
     
   /* 
    filesUpload.onchange = function () { 
      traverseFiles(this.files); 
    }; */
    
/*
    //업로드 파일 리스트 삭제
    var deleteFileList = function(fileListElem,fileElemIndex){
    	//기본으로 마크업된 input file의 인덱스 번호가 0으로 시작되는데
    	//전역변수로 증가 시킨 상태이므로 -1을 시켜서 인덱스 번호를 맞춰준다.
    	
    	alert("fileListElem : "+fileListElem+" / fileElemIndex :"+fileElemIndex);
    	
    	
    };
    
    */
    /* 파일업로드 테스트 끝 */
    
	
	// 전송 부분 - 제이쿼리 이용 x
	/*
	function fn_formSubmit(){
		CKEDITOR.instances["content"].updateElement();
		
		// 내용 입력 체크
		if ( ! chkInputValue("#title", "제목")) return false;
		if ( ! chkInputValue("#content", "내용")) return false;
	
		$("#registerForm").submit();
	} 
	*/
    
	var formObj = $("form[role='form']");
	
	// 등록
	$("#registBtn").on("click", function(){
		
		CKEDITOR.instances["content"].updateElement();
		
		if ( ! fn_chkInputValue("#title", "제목")) return false;
		if ( ! fn_chkInputValue("#content", "내용")) return false;
		
		formObj.attr("action", "/board/regist");
		formObj.attr("method", "post");	
		//fn_fileSizeChk();
		
		formObj.submit();
	});
	
	
	// 취소
	$("#cancelBtn").on("click", function(){
	  	self.location = "/board/list";	  
	});
	
	
	function fn_fileUpload(){
		//var formData = new FormData();
		//formData.append("uploadfile",$("input[name=uploadfile]")[0].files[0]);
		//formData.append("uploadfile",$("input[name=uploadfile]")[1].files[0]);
		
		// 파일개수
		//alert($("input[name=uploadfile]").length);
		// 파일명
		//alert($("input[name=uploadfile]")[0].files[0].name);
		
		
	}
	
	function fn_chkInputValue(id, msg){

		if ( $.trim($(id).val()) == "") {
			alert(msg+"을(를) 입력해주세요.");
			$(id).focus();
			return false;
		}
		return true;
	}
	

	// 파일 사이즈 체크 최대 10mb까지 업로드 가능
	function fn_fileSizeChk(){
		// 파일개수 찾기
		alert($("input:file").length);	
	}
		

	// 파일 추가 
	var fileNum = 1;
	
	$("#addFileBtn").on("click", function(){

		var str = "<span class='col-lg-9'><input type='file' name='uploadfile' class='col-xs-6' />"
				+ "<button name='fileDelBtn' class='btn btn-outline btn-default btn-sm'>삭제</button></span>";

		$("#fileDiv").append(str);
		$("button[name='fileDelBtn']").on("click", function(e){
			e.preventDefault();
			fn_deleteFile($(this));
		});
	});
	
	
	/*
	function fn_addFile(){
		
		// var aaa = "<span class='col-lg-9'><input type='file' name='uploadfile_"+(fileNum++)+"' class='col-xs-6' />"
		var str = "<span class='col-lg-9'><input type='file' name='uploadfile' class='col-xs-6' />"
			+ "<button name='fileDelBtn' class='btn btn-outline btn-default btn-sm'>삭제</button></span>";

			$("#fileDiv").append(str);
			$("button[name='fileDelBtn']").on("click", function(e){
				e.preventDefault();
				fn_deleteFile($(this));
			});
	}
	*/
	// 파일추가 - 삭제
	function fn_deleteFile(obj){
		
		
		obj.parent().remove();
	}
	

    </script>
    
</body>

</html>
