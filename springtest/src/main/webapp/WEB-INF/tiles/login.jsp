<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/login"></c:url>
<div class="row">

	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<c:if test="${param.error !=null}">
			<div class="login-error">Incorrent username or password</div>

		</c:if>
		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">User Login</div>
			</div>
			<div class="panel-body">
				<form method="post" action="${loginUrl}" class="login-form">

					<input type="hidden" name="${_csrf.parameterName}"
						value="${_csrf.token}">
					<div class="input-group">
						<input type="text" name="username" placeholder="Username"
							class="form-control"></input>
					</div>

					<div class="input-group">
						<input type="password" name="password" placeholder="Password"
							class="form-control"></input>
					</div>

					<div class="input-group">
						<button type="submit" class="btn-primary pull-right">Sign
							In</button>
					</div>

				</form>
			</div>

		</div>

	</div>


</div>
