<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Insert title here</title>
</head>
<!-- 실제 원본 이미지를 보여주는 영역(CSS, HTML) -->
<div class='bigPictureWrapper'>
	<div class='bigPicture'>
	</div>
</div>

<style>
.uploadResult {
	width:100%;
	background-color:gray;
}
.uploadResult ul{
	display:flex;
	flex-flow:row;
	justify-content:center;
	align-items:center;
}
.uploadResult ul li {
	list-style:none;
	padding:10px;
	align-content:center;
	text-align:center;
}
.uploadResult ul li img {
	width:100px;
}
.uploadResult ul li span {
	color:white;
}
.bigPictureWrapper{
	position:absolute;
	display:none;
	justify-content:center;
	align-items:center;
	top:0%;
	width:100%;
	height:100%;
	background-color:gray;
	z-index:100;
	background:rgba(255,255,255,0.5);
}
.bigPicture {
	position:relative;
	display:flex;
	justify-content:center;
	align-items:center;
}
.bigPicture img {
	width:600px;
}

</style>
<body>
<h1>Upload with Ajax</h1>

<div class = 'uploadDiv'>
	<input type = 'file' name = 'uploadFile' multiple>
</div>

<button id ='uploadBtn'>Upload</button>

<!-- <ul> 태그를 작성해서 첨부파일 이름을 목록으로 처리할 수 있도록 한다. -->
<div class='uploadResult'>
	<ul>
	
	</ul>
</div>

<script
	src="https://code.jquery.com/jquery-3.3.1.min.js"
	integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
	crossorigin="anonymous"></script>
	
<script>
//원본 이미지 보여주기
function showImage(fileCallPath){
	//alert(fileCallPath);
	
	$(".bigPictureWrapper").css("display", "flex").show();
	
	$(".bigPicture")
	.html("<img src='/display?fileName="+encodeURI(fileCallPath)+"'>")
	.animate({width:'100%', height:'100%'}, 1000);

}

$(document).ready(function(){
	var regex = new RegExp("(.*?)\.(exe|sh|zip|alz)$");
	var maxSize = 5242880;		//5MB
	
	function checkExtension(fileName, fileSize){
		
		if(fileSize >= maxSize){
			alert("파일 사이즈 초과");
			return false;
		}
		
		if(regex.test(fileName)){
			alert("해당 종류의 파일은 업로드 할 수 없습니다.");
			return false;
		}
		return true;
	}
	
	var cloneObj = $(".uploadDiv").clone();
	
	$('#uploadBtn').on("click", function(e){
		var formData = new FormData();
		var inputFile = $("input[name='uploadFile']");
		var files = inputFile[0].files;
		
		console.log(files);
		
		//add File Date to formData
		for(var i=0; i<files.length; i++){
			
			if(!checkExtension(files[i].name, files[i].size)){
				return false;
			}
			
			formData.append("uploadFile", files[i]);
		}
		
	var uploadResult = $(".uploadResult ul");
		//목록을 보여주는 부분을 함수로 처리한다.
		
		//섬네일 클릭 시 showImage()가 호출될 수 있는 코드 작성
		//이미지 파일 삭제하기
		function showUploadedFile(uploadResultArr) {
			var str = "";
			
			$(uploadResultArr).each(function(i, obj){
				// 일반 파일 경우 attach.png 이미지가 보이게 수정
				//obj.fileName = 파일 이름
				//이미지 파일이 아닐 때 if 안 코드로 처리
				if(!obj.image){
					var fileCallPath = encodeURIComponent(obj.uploadPath+"/"+obj.uuid +"_"+obj.fileName);
					var fileLink = fileCallPath.replace(new RegExp(/\\/g),"/");
					
					//URL 부분에서는 공백이 있으면 안된다.
					//fileName= 뒤에 공백 있으면 안된다.
					str += "<li><div><a href='/download?fileName="+fileCallPath+"'><img src='/resources/img/attach.png'>"+obj.fileName+"</a>"+
							"<span data-file=\'"+fileCallPath+"\' data-type='file'> x </span>"+"<div><li>"
				} else {
				//이미지 파일일 때 else 안 코드로 처리
				//str += "<li>" + obj.fileName + "</li>";
				var fileCallPath = encodeURIComponent( obj.uploadPath+ "/s_"+obj.uuid +"_"+obj.fileName);				
				var originPath = obj.uploadPath + "\\"+obj.uuid +"_"+obj.fileName;
				
				originPath = originPath.replace(new RegExp(/\\/g),"/");
				
				str += "<li><a href=\"javascript:showImage(\'"+originPath+"\')\">"+
				"<img src='/display?fileName="+fileCallPath+"'></a>"+
						"<span data-file=\'"+fileCallPath+"\' data-type='image'> x </span>"+"<li>";								
				}
			});
			
			uploadResult.append(str);
		}
			
	//원본 사이즈 이미지를 다시 클릭 시 사라지도록 <div> 이벤트 처리하기
	$(".bigPictureWrapper").on("click", function(e){
		$(".bigPicture").animate({width:'0%', height:'0%'}, 1000);
		setTimeout(() => {
			$(this).hide();
		}, 1000);
	});
	
					
		$.ajax({
			url: '/uploadAjaxAction',
				processData : false,
				contentType : false,
				data : formData,
				type : 'POST',
				dataType : 'json',
				success : function(result){
					
					console.log(result);
					
					//Ajax 결과에서 받은 JSON 테이터를 showUploadedFile을 호출
					showUploadedFile(result);
					
					//파일을 전송 한 뒤 초기화 된 부분을 덮어쓰기 -> 초기 상태로 변경
					$(".uploadDiv").html(cloneObj.html());
				}
		});		//$.ajax
	});
	
	// 'x' 표시에 대한 이벤트 처리하기
	$(".uploadResult").on("click", "span", function(e){
		var targetFile = $(this).data("file");
		var type = $(this).data("type");
		console.log(targetFile);
		
		$.ajax({
			url : '/deleteFile',
			data : {fileName: targetFile, type:type},
			dataType: 'Text',
			type : 'POST',
				success : function(result){
					alert(result);
				}
		}); 	//$.ajax
	});
});
</script>

</body>
</html>