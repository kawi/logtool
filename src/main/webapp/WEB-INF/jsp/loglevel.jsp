<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<c:url var="logtoolCss" value="/static/css/logtool.css" />
<c:url var="mainJs" value="/static/js/main.js" />
<c:url var="jquery" value="/static/js/jquery-1.7.2.min.js" />

<head>
	<meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	<title>Log levels</title>
    <meta name="viewport" content="width=device-width">
    
    <link rel="stylesheet" href="${logtoolCss}" />
 
</head>
<body>
	<div class="container">
	
		<div>
			<button id="error" value="error"><span class="logbutton">log.error</span></button>
			<button id="warn"  value="warn"><span class="logbutton">log.warn </span></button>
			<button id="info"  value="info"><span class="logbutton">log.info </span></button>
			<button id="debug" value="debug"><span class="logbutton">log.debug</span></button>
			<button id="trace" value="trace"><span class="logbutton">log.trace</span></button>
		</div>
		<div>
			<input list="packages" id="packagefinder" placeholder="Enter package" type="text" />
			  
			<datalist id="packages">
				<c:forEach var="package" items="${packages}">
					    <option value="${package.key}" /> 
				</c:forEach>
			</datalist>
		</div>

		<div>		
			<div id="log"></div>
		</div>

	</div>

	<script src="${jquery}"></script>
    <script src="${mainJs}"></script>
	
</body>
</html>
	