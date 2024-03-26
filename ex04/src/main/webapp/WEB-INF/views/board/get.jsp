<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<%@ include file="../includes/header.jsp"%>

<!-- CSS 추가하기 -->
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

<script type="text/javascript" src="/resources/js/reply.js"></script>

<script>
$(document).ready(function (){
	
	var bnoValue = '<c:out value = "${board.bno}"/>';
	var replyUL = $(".chat");
	
		showList(1);
		
		function showList(page){
			
			console.log("show list " + page);
			
			replyService.getList({bno:bnoValue,page: page||1}, function(replyCnt, list){
				
				console.log("replyCnt: " + replyCnt);
				console.log("list: " + list);
				//console.log(list);
				
				if(page == -1){
					pageNum = Math.ceil(replyCnt/10.0);
					showList(pageNum);
					return;
				}
				
				var str = "";
				if(list == null || list.length == 0){	
					return;
				}
				for (var i=0, len=list.length || 0; i<len; i++){
					str += "<li class='left clearfix' data-rno='"+list[i].rno+"'>";
					str += " <div><div class = 'header'><strong class = 'primary-font'>[" + list[i].rno + "] "+ list[i].replyer+"</strong>";
					str += " 	<small class = 'pull-right text-muted'>" + replyService.displayTime(list[i].replyDate)+"</small></div>"
					str += "	<p>"+ list[i].reply+"</p></div></li>";
				}
			replyUL.html(str);
			
			showReplyPage(replyCnt);
			
			});		//end function
		}	//end showList
		
		// panel-footer
		var pageNum = 1;
		var replyPageFooter = $(".panel-footer");
		
		function showReplyPage(replyCnt){
			var endNum = Math.ceil(pageNum / 10.0) * 10;
			var startNum = endNum - 9;
			
			var prev = startNum != 1;
			var next = false;
			
			if(endNum * 10 >= replyCnt){
				endNum = Math.ceil(replyCnt/10.0);
			}
			
			if(endNum * 10 < replyCnt){
				next = true;
			}
			
			var str = "<ul class ='pagination pull-right'>";
			
			if(prev){
				str += "<li class = 'page-item'><a class ='page-link' href= '"+(startNum -1) +"'>Previous</a></li>";
			}
			
			for(var i = startNum; i <= endNum; i++){
				var active = pageNum == i? "active" : "";
				
				str += "<li class = 'page-item "+active+" '><a class = 'page-link' href = '"+i+"'>"+i+"</a></li>";
			}
			
			if(next){
				str += "<li class = 'page-item'><a class = 'page-link' href = '"+(endNum + 1)+"'>Next</a></li>";
			}
			
			str += "</ul></div>";
			
			console.log(str);
			
			replyPageFooter.html(str);
		}
		
		var modal = $(".modal");
		var modalInputReply = modal.find("input[name='reply']");
		var modalInputReplyer = modal.find("input[name='replyer']");
		var modalInputReplyDate = modal.find("input[name='replyDate']");
		
		var modalModBtn = $("#modalModBtn");
		var modalRemoveBtn = $("#modalRemoveBtn");
		var modalRegisterBtn = $("#modalRegisterBtn");
		
		$("#addReplyBtn").on("click", function(e){
			
			modal.find("input").val("");
			modalInputReplyDate.closest("div").hide();
			modal.find("button[id != 'modalCloseBtn']").hide();
			
			modalRegisterBtn.show();
			
			$(".modal").modal("show");
		});
		
		modalRegisterBtn.on("click", function(e){
			
			var reply = {
					reply : modalInputReply.val(),
					replyer : modalInputReplyer.val(),
					bno:bnoValue
			};
		replyService.add(reply, function(result){
			
			alert(result);
			modal.find("input").val("");
			modal.modal("hide");
			
			//showList(1);
			showList(-1);
		});
	});
		
		//댓글 조회 클릭 이벤트 처리
		$(".chat").on("click", "li", function(e) {
			var rno = $(this).data("rno");
			
			replyService.get(rno, function(reply){
				modalInputReply.val(reply.reply);
				modalInputReplyer.val(reply.replyer);
				modalInputReplyDate.val(replyService.displayTime( reply.replyDate)).attr("readonly", "readonly");
				modal.data("rno", reply.rno);
				
				modal.find("button[id != 'modalCloseBtn']").hide();
				modalModBtn.show();
				modalRemoveBtn.show();
				
				$(".modal").modal("show");
			});			
			console.log(rno);
		});
		
		//댓글 수정(modify) 이벤트 처리
		modalModBtn.on("click", function(e){
			var reply = {rno:modal.data("rno"), reply: modalInputReply.val()};
			
			replyService.update(reply, function(result){
				
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		// 댓글 삭제(remove) 이벤트 처리
		modalRemoveBtn.on("click", function(e){
			var rno = modal.data("rno");
			
			replyService.remove(rno, function(result){
				
				alert(result);
				modal.modal("hide");
				showList(pageNum);
			});
		});
		
		//panel-footer
		replyPageFooter.on("click", "li a", function(e){
			e.preventDefault();
			console.log("page click");
			
			var targetPageNum = $(this).attr("href");
			
			console.log("targetPageNum: " + targetPageNum);
			
			pageNum = targetPageNum;
			
			showList(pageNum);
		});	
	});
</script>

<!-- <script>
console.log("================");
console.log("JS TEST");

var bnoValue = '<c:out value = "${board.bno}"/>';

//reply List Test
//replyService.getList({bno:bnoValue, page:1}, function(list){
//	for(var i = 0, len = list.length||0; i<len; i++){
//		console.log(list[i]);
//		}
//});

//remove
//replyService.remove(3, function(count){
//	console.log(count);
	
//	if(count === "success"){
//		alert("REMOVED");
//	}
//}, function(err) {
//	alert('ERROR...');
//});

//9번 댓글 수정 --- error
//replyService.update({
//	rno : 11,
//	bno : bnoValue,
//	reply : "Modified Reply..."
//}, function(result) {
//	alert("수정 완료...");
//});

//replyService.get(10, function(data){
//	console.log(data);
//});
</script>-->

<script type="text/javascript">
	$(document).ready(function() {
		var operForm = $("#operForm");

		$("button[data-oper='modify']").on("click", function(e) {
			operForm.attr("action", "/board/modify").submit();
		});

		$("button[data-oper='list']").on("click", function(e) {
			operForm.find("#bno").remove();
			operForm.attr("action", "/board/list")
			operForm.submit();
		});
	});
</script>

<!-- 자동으로 해당 게시물의 댓글을 가져오는 작업 -->
<script>
$(document).ready(function(){

	//즉시 실행 함수를 이용해서 첨부파일의 데이터를 가져온다.
	(function(){
		var bno = '<c:out value="${board.bno}"/>';
					
		$.getJSON("/board/getAttachList", {bno: bno}, function(arr){
			console.log(arr);
			
			var str ="";
			//화면에 파일을 보여주는 부분	
			$(arr).each(function(i, attach){
				//image type 일때
				if(attach.fileType){
					var fileCallPath = encodeURIComponent(attach.uploadPath+ "/s_"+attach.uuid + "_"+attach.fileName);
					
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename= '"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
					//이미지 파일일 때 원본파일(fileCallPath)을 섬네일로 보여준다.
					str += "<img src= '/display?fileName="+fileCallPath+"'>";
					str += "</div>";
					str += "</li>";
				} else {
					//일반 파일일 경우
					str += "<li data-path='"+attach.uploadPath+"' data-uuid='"+attach.uuid+"' data-filename='"+attach.fileName+"' data-type='"+attach.fileType+"'><div>";
					str += "<span>"+attach.fileName+"</span><br>";
					//일반 파일일 때 resources/img 파일에 있는 attach.png를 보여준다.
					str += "<img src='/resources/img/attach.png'>";
					str += "</div>";
					str += "</li>";
				}
			});
			$(".uploadResult ul").html(str);
		});		//end getjson
	})();	//end function
	
	// 이미지 파일을 클릭했을 때는 원본이미지로, 일반파일을 클릭했을 때는 다운로드가 되게끔 처리
	//uploadResult클래스를 가진 요소 내의 li요소를 클릭하면 실행되는 이벤트 핸들러
	//클릭된 li 요소에 대한 정보를 사용하여 이미지를 보여주거나 파일 다운로드
	$(".uploadResult").on("click", "li", function(e){
		console.log("view image");
		
		var liObj = $(this);
		
		var path = encodeURIComponent(liObj.data("path")+"/" + liObj.data("uuid")+"_" + liObj.data("filename"));
		
		//이미지 파일일 경우
		if(liObj.data("type")){
			showImage(path.replace(new RegExp(/\\/g),"/"));
		} else {
			//일반 파일일 경우
			self.location ="/download?fileName="+path
		}
	});
	//이미지를 보여주는 기능을 담당하는 함수
	function showImage(fileCallPath){
		//파일 경로를 경고창에 출력
		alert(fileCallPath);
		
		//.bigPictureWrapper 요소를 표시하여 이미지를 감싸는 영역을 화면에 보여준다.
		$(".bigPictureWrapper").css("display", "flex").show();
		
		//이미지를 화면에 표시하고 애니메이션 효과를 적용한다.
		$(".bigPicture")
		.html("<img src='/display?fileName="+fileCallPath+"'>")
		.animate({width:'100%', height: '100%'}, 1000);
	}	
	
	//원본 이미지 창 닫기
	//.bigPictureWrapper 요소를 클릭했을 때 실행되는 이벤트 핸들러
	$(".bigPictureWrapper").on("click", function(e){
		//.bigPicture 요소를 애니메이션 효과와 함께 크기를 줄임
		$(".bigPicture").animate({width: '0%', height: '0%'}, 1000);
		
		//1초 후에 실행되는 함수
		setTimeout(function(){
			//.bigPictureWrapper 요소를 숨긴다.
			$('.bigPictureWrapper').hide();
		},1000);	//1초 후에 실행되도록 설정
	});
});

</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Read</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<!-- /.row -->

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">

			<div class="panel-heading">Board Read Page</div>
			<!-- /.panel-heading -->
			<div class="panel-body">

				<div class="form-group">
					<label>Bno</label> <input class="form-control" name='bno'
						value='<c:out value="${board.bno }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Title</label> <input class="form-control" name='title'
						value='<c:out value="${board.title }"/>' readonly="readonly">
				</div>

				<div class="form-group">
					<label>Text area</label>
					<textarea class="form-control" rows="3" name='content'
						readonly="readonly"><c:out value="${board.content }" /></textarea>
				</div>

				<div class="form-group">
					<label>Writer</label> <input class="form-control" name='writer'
						value='<c:out value="${board.writer }"/>' readonly="readonly">
				</div>
				<button data-oper='modify' class="btn btn-default">Modify</button>
				<button data-oper='list' class="btn btn-info">List</button>


				<form id='operForm' action="/board/modify" method="get">
					<input type='hidden' id='bno' name='bno'
						value='<c:out value="${board.bno }"/>'> <input
						type='hidden' name='pageNum'
						value='<c:out value="${cri.pageNum}"/>'> <input
						type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					<input type='hidden' name='keyword'
						value='<c:out value="${cri.keyword}"/>'> <input
						type='hidden' name='type' value='<c:out value="${cri.type}"/>'>
				</form>
			</div>
			<!-- end panel body -->
		</div>
		
		<!-- 가져온 첨부파일을 보여줄 수 있는 div 생성하기 -->
		<div class="row">
			<div class="col-lg-12">
				<div class="panel panel-default">
					<div class="panel-heading">Files</div>
					<!-- /.panel-heading -->
					<div class="panel-body">
						<div class='uploadResult'>
							<ul>
							</ul>
						</div>
					</div>
					<!-- end panel-body -->
				</div>
				<!-- end panel-body -->
			</div>
			<!-- /.row -->
		</div>
		
		<div class='row'>
			<div class="col-lg-12">
				<!-- /.panel -->
				<div class="panel panel-default">
					<!-- <div class="panel-heading">
						<i class="fa fa-comments fa-fw"></i> Reply
					</div> -->
					
					<div class = "panel-heading">
						<i class = "fa fa-comments fa-fw"></i> Reply
							<button id = 'addReplyBtn' class = 'btn btn-primary btn-xs pull-right'>New Reply</button>
					</div>

					<!-- /.panel-heading -->
					<div class="panel-body">
						<ul class="chat">
							<!-- start reply -->
							<li class="left clearfix" data-rno='12'>
								<div>
									<div class="header">
										<strong class="primary-font">user00</strong> <small
											class="pull-right text-muted">2024-03-19 20:20</small>
									</div>
									<p>Good job!</p>
								</div>
							</li>
							<!-- end reply -->
						</ul>
						<!-- /.panel .chat-panel -->
							<div class = "panel-footer">
						</div>
					</div>
				</div>
				<!-- ./end row -->
			</div>

			<!-- end panel -->
		</div>
	</div>
	<!--  /.row -->
	
	<!-- Modal -->
		<div class = "modal fade" id = "myModal" tabindex ="-1" role = "dialog" aria-labelledby="myModalLabel" aria-hidden="true">
			<div class = "modal-dialog">
				<div class = "modal-content">
					<div class = "modal-header">
						<button type = "button" class = "close" data-dismiss="modal" aria-hidden = "true">&times;</button>
						<h4 class = "modal-title" id = "myModalLabel">REPLY MODAL</h4>
					</div>
					<div class = "modal-body">
						<div class = "form-group">
							<label>Reply</label>
							<input class = "form-control" name = 'reply' value = 'New Reply!!!!'>
						</div>
						<div class = "form-group">
							<label>Replyer</label>
							<input class = "form-control" name = 'replyer' value = 'replyer'>
						</div>
						<div class = "form-group">
							<label>Reply Date</label>
							<input class = "form-control" name = 'replyDate' value = ''>
						</div>
					</div>
				<div class = "modal-footer">
					<button id = 'modalModBtn' type ="button" class = "btn btn-warning">Modify</button>
					<button id = 'modalRemoveBtn' type = "button" class= "btn btn-danger" data-dismiss="modal">Remove</button>
					<button id = 'modalRegisterBtn' type = "button" class= "btn btn-primary" data-dismiss="modal">Register</button>
					<button id = 'modalCloseBtn' type = "button" class= "btn btn-default" data-dismiss="modal">Close</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal -->
	<%@ include file="../includes/footer.jsp"%>