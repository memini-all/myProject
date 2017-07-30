<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>

<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- jQuery -->
    <script src="/resources/sb-admin/vendor/jquery/jquery.min.js"></script>

    <!-- Bootstrap Core CSS -->
    <link href="/resources/sb-admin/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    
    <!-- Bootstrap Core JavaScript -->
    <script src="/resources/sb-admin/vendor/bootstrap/js/bootstrap.min.js"></script>


    
<style type="text/css">
.error-template{
	padding: 40px 15px;
	text-align:center;
}

</style>
</head>

<body>

<div class="wrapper">
    <div class="row">
        <div class="col-md-12">
            <div class="error-template">
 
            	<img src="/resources/image/error.jpg">
                <div class="row error-details">
                   Please contact the server administrator
                </div>
                <br>
                <div class="row">
                    <a href="/index" class="btn btn-default btn-lg">
						<span class="glyphicon glyphicon-home"></span> Go Home </a>
                </div>
            </div>
        </div>
    </div>
</div>


</body>

</html>
