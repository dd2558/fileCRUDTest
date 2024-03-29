<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>       
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
경기도어린이박물관
<c:forEach var="imageUrl" items="${imageUrls}">
    <c:if test="${fn:startsWith(imageUrl, '/upload/202422925753759')}">
        <img src="${pageContext.request.contextPath}${imageUrl}" style="width:150px;">
    </c:if>
    </c:forEach>
</body>
</html>