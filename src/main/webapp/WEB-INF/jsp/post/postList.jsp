<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="d-flex justify-content-center">
	<div class="w-50">
		<h1>글목록</h1>
		
		<table class="table">
			<thead>
				<tr>
					<th>No.</th>
					<th>제목</th>
					<th>작성날짜</th>
					<th>수정날짜</th>
				</tr>
			</thead>
			<tbody>
			<c:forEach items="${postList}" var="post">
				<tr>
					<td>${post.id}</td>
					<td><a href="/post/post_detail_view?postId=${post.id}">${post.subject}</a></td>
					<td>
						<%--ZonedDateTime -> Date -> String 두번의 형변환 필요 --%>
						<fmt:parseDate value="${post.createdAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedCreatedAt"/>
						<fmt:formatDate value="${parsedCreatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss"/>
					</td>
					<td>
						<fmt:parseDate value="${post.updatedAt}" pattern="yyyy-MM-d'T'HH:mm:ss" var="parsedUpdatedAt"/>
						<fmt:formatDate value="${parsedUpdatedAt}" pattern="yyyy년 M월 d일 HH:mm:ss"/>
					</td>
				</tr>
			</c:forEach>
			</tbody>
		</table>
		
		<div class="d-flex justify-content-end">
			<a href="/post/post_create_view" class="btn btn-primary">글쓰기</a>
		</div>
		
	</div>
</div>






