<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>v

<%!%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<div class="row">

		<div class="col-md-8 col-md-offset-2">

			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">Add a status update</div>
				</div>
				
					<form:form modelAttribute="statusUpdate">
						<div class="errors">
							<form:errors path="text"></form:errors>
						</div>
						<div class="form-group">
							<form:textarea path="text" rows="10" cols="50" name="text"></form:textarea>
							<input type="submit" name="submit" value="Add Status">
						</div>
					</form:form>
			</div>

			<div class="panel panel-default">
				<div class="panel-heading">
					<div class="panel-title">
						Status updated on
						<fmt:formatDate pattern="EEEE d MMM y 'at' H:mm:s"
							value="${latestStatusUpdate.added}"></fmt:formatDate>
					</div>
				</div>
				<div class="panel-body">
					${latestStatusUpdate.text}
				</div>

			</div>


		</div>


	</div>
	<script src='//cdn.tinymce.com/4/tinymce.min.js'></script>
	<script>
		tinymce.init({
			selector : 'textarea',
			plugins: 'link'
		});
	</script>
</body>
</html>