<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:url var="profilePhoto" value="/profilephoto"></c:url>
<c:url var="editProfileAbout" value="/edit-profile-about"></c:url>
<div class="row">

	<div class="col-md-10 col-md-offset-1">
		<div id="photo-upload-status"></div>
		<div class="profile-about">

			<div class="profile-image">
				<div>
					<img id="profilePhotoLink" src="${profilePhoto}" />
				</div>
				<div class="text-center">
					<a href="#" id="uploadLink">Upload Photo</a>
				</div>
			</div>
			

			<div class="profile-text">
			
			<c:choose>
				<c:when test="${profile.about==null}">
					click edit to add information about your profile.
				</c:when>
				<c:otherwise>
				${profile.about}	
				</c:otherwise>
			
			
			</c:choose>
				
			</div>

		</div>

		<div class="profile-about-edit">/

			<a href="${editProfileAbout}">Edit</a>
		</div>
		
		<c:url value="/upload-profile-photo" var="uploadPhotoLink" />
		<form method="post" enctype="multipart/form-data" id="photoUploadForm"
			action="${uploadPhotoLink}">

			<input type="file" accept="image/*" name="file" id="photoFileInput"/> <input
				type="submit" value="upload" /> <input type="hidden"
				name="${_csrf.parameterName}" value="${_csrf.token}" />

		</form>


	</div>


</div>

<script>

function setStatusUpdateText(text){
	
	$('#photo-upload-status').text(text);
	
	window.setTimeout(function(){
		$('#photo-upload-status').text("");
	},2000);
	
}

function uploadSuccess(data){
	
	$('#profilePhotoLink').attr('src','${profilePhoto}');
	$('#photoFileInput').val("");
	setStatusUpdateText(data.message);
}

function uploadPhoto(event) {
	console.log("form being submitted")
	event.preventDefault();
	
	$.ajax({
		url:$(this).attr('action'),
		type:'POST',
		data:new FormData(this),
		processData:false,
		contentType:false,
		success:uploadSuccess,
		error: function(){
			setStatusUpdateText("server unreachable..")
		}
		
	});
	
}

$(document).ready(function() {
	console.log("Hello loaded");
	
	$('#uploadLink').on('click', function(event){
		event.preventDefault();
		
		$('#photoFileInput').trigger('click');
		
	});
	
	$('#photoFileInput').change(function(){
		
		$('#photoUploadForm').submit();
		
	});
	
	$('#photoUploadForm').on('submit', uploadPhoto);
	
});

</script>
