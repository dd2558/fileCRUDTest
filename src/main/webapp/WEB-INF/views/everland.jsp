<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>에버랜드 이미지</title>
</head>
<body>
    <h1>에버랜드 이미지</h1>
    <div class="image-list">
        <c:forEach var="imageUrl" items="${imageUrls}">
        <c:if test="${fn:startsWith(imageUrl, '/upload/')}">
            <img src="${pageContext.request.contextPath}${imageUrl}" style="width:150px;">
            </c:if>
        </c:forEach>
    </div>
</body>
</html>
