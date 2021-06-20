<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	$(document).ready(function(){
	    
		//https://m.blog.naver.com/PostView.nhn?blogId=moonv11&logNo=220605582547&proxyReferer=https:%2F%2Fwww.google.com%2F
		$("#updateForm").submit(function(event){
			
			event.preventDefault();
			
	        var bname = $("#bName").val();
	        var btitle = $("#bTitle").val();
	        var bcontent = $("#bContent").val();	        
	        var bid = $("#bId").val();
	        console.log(bcontent);
	        console.log($(this).attr("action"));
	        
	        var form = {
	        		bid: bid,
	        		bname: bname,
	                bcontent: bcontent,
	                btitle: btitle
	        };
		    //dataType: 'json',
	        $.ajax({
			    type : "PUT",
			    url : $(this).attr("action"),
			    cache : false,
			    contentType:'application/json; charset=utf-8',
 			    data: JSON.stringify(form), 
			    success: function (result) {       
					if(result == "SUCCESS"){
						//list로 
						$(location).attr('href', '${pageContext.request.contextPath}/restful/board/')				      	       
					}					        
			    },
			    error: function (e) {
			        console.log(e);
			    }
			})	       
	
	    }); // end submit()
	    
	}); // end ready()
</script>
</head>
<body>
	<table id="list-table" width="500" cellpadding="0" cellspacing="0" border="1">
		<form id="updateForm" action="${pageContext.request.contextPath}/restful/board/${content_view.bid}" method="post">
			<input type="hidden" id="bId" value="${content_view.bid}">
			<tr>
				<td> 번호 </td>
				<td> ${content_view.bid} </td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td> ${content_view.bhit} </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" id="bName" value="${content_view.bname}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" id="bTitle" value="${content_view.btitle}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10" id="bContent" name="content" >${content_view.bcontent}</textarea></td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; <a href="${pageContext.request.contextPath}/restful/board/">목록보기</a> &nbsp;&nbsp; <a id="a-delete">삭제</a> &nbsp;&nbsp; <a href="reply_view?bId=${content_view.bid}">답변</a></td>
			</tr>
		</form>
	</table>
	
</body>
</html>