<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="loginUrl" value="/login"></c:url>
<div class="row">

	<div class="col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
		<div class="login-error">
			<form:errors path="user.*"></form:errors>
		</div>


		<div class="panel panel-default">
			<div class="panel-heading">
				<div class="panel-title">Create An Account</div>
			</div>
			<div class="panel-body">
				<form:form method="post" modelAttribute="user" class="login-form">


					<div class="input-group">
						<form:input type="text" path="email" placeholder="Email"
							class="form-control"></form:input>
					</div>

					<div class="input-group">
						<form:input type="password" path="plainPassword" placeholder="Password"
							class="form-control"></form:input>
					</div>

					<div class="input-group">
						<form:input type="password" path="repeatPassword"
							placeholder="Repeat Password" class="form-control"></form:input>
					</div>

					<div class="input-group">
						<button type="submit" class="btn-primary pull-right">Register</button>
					</div>

				</form:form>
			</div>

		</div>

	</div>


</div>
