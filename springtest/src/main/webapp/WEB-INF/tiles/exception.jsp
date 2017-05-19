<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<div class="row">
		<div class="message">
		<div class="col-md-6 col-md-offset-3 col-sm-8 col-md-offset-2 text-cen">
			<c:out value="${message}"></c:out>
		</div>
		<!--  
		Exception : <c:out value="${exception}"></c:out>
		Failed Url : <c:out value="${url}"></c:out>
		
		Exception Message : <c:out value="${exception.message}"></c:out>
		
		<c:forEach var="line" items="${exception.stackTrace}">
		
			<c:out value="${line}"></c:out>
		</c:forEach>
		-->
		</div>
</div>