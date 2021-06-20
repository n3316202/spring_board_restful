<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<script type="text/javascript">
	$(document).ready(function (){
		$('.a-delete').click(function(event){
			//prevendDefault()는 href로 연결해 주지 않고 단순히 click에 대한 처리를 하도록 해준다.
			event.preventDefault();
			console.log("ajax 호출전"); 
			//해당 tr제거
			var trObj =  $(this).parent().parent();
			
			$.ajax({
			    type : "DELETE",
			    url : $(this).attr("href"),
/* 			    data:{"bId":"${content_view.bId}"}, */
			    success: function (result) {       
			        console.log(result); 
					if(result == "SUCCESS"){
			           //getList();
				      $(trObj).remove();  
				      	       
					}					        
			    },
			    error: function (e) {
			        console.log(e);
			    }
			})
			 
		});
	});	
</script>
<body>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
			<td>삭제</td>
		</tr>
		<c:forEach items="${list}" var="dto">
		<tr>
			<td>${dto.bid}</td>
			<td>${dto.bname}</td>
			<td>
				<c:forEach begin="1" end="${dto.bindent}">-</c:forEach>
				<a href="${pageContext.request.contextPath}/restful/board/${dto.bid}">${dto.btitle}</a></td>
			<td>${dto.bdate}</td>
			<td>${dto.bhit}</td>
			<td><a class="a-delete" data-bid='${dto.bid}' href="${pageContext.request.contextPath}/restful/board/${dto.bid}">삭제</a></td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
	</table>
</body>
</html>