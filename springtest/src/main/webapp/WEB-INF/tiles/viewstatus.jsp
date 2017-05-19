<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib tagdir="/WEB-INF/tags" prefix="fwt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<c:url var="url" value="/viewstatus"></c:url>



	<div class="col-md-8 col-md-offset-2">
		<fwt:pagination page="${page}" url="${url}" size="4" />
		<div class="panel panel-default">
			<c:forEach var="statusUpdate" items="${page.content}">

				<c:url var="editLink" value="/editstatus?id=${statusUpdate.id}"></c:url>
				<c:url var="deleteLink" value="/deletestatus?id=${statusUpdate.id}"></c:url>

				<div class="panel panel-default">
					<div class="panel-heading">
						<div class="panel-title">
							Status updated on
							<fmt:formatDate pattern="EEEE d MMM y 'at' H:mm:s"
								value="${statusUpdate.added}"></fmt:formatDate>
						</div>
					</div>
					<div class="panel-body">
						<div>${statusUpdate.text}</div>
						<div class="edit-links pull-right">
							<a href="${editLink}">Edit</a> | <a href="${deleteLink}" onclick="return confirm('Really want to delete this?');">Delete</a>
						</div>

					</div>

				</div>

			</c:forEach>
		</div>
	</div>
</body>
</html>