<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Member List</title>
</head>
<body>
    <h2>Member List</h2>
    <table border="1">
        <thead>
            <tr>
                <th>작성자</th>
                <th>내용</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${members}" var="member">
                <tr>
                    <td>${member.name}</td>
                    <td>${member.content}</td>
                </tr>
            </c:forEach>
            
             <c:forEach var="imageUrl" items="${imageUrls}">
	 <img src="${pageContext.request.contextPath}${imageUrl}" style="width:150px;">
        <button onclick="deleteImage('${imageUrl}')">삭제</button>
        </c:forEach>
        </tbody>
    </table>
</body>
</html>
