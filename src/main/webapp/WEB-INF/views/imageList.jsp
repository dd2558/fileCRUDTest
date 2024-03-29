<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
    <meta charset="UTF-8">
    <title>이미지 목록</title>
      <script>
      function deleteImage(imageUrl) {
    	    if (confirm("이미지를 삭제하시겠습니까?")) {
    	        // 이미지 삭제 요청
    	        var xhr = new XMLHttpRequest();
    	        xhr.open("POST", "/deleteImage?url=" + imageUrl, true);
    	        xhr.onreadystatechange = function () {
    	            if (xhr.readyState === 4 && xhr.status === 200) {
    	                // 이미지 삭제 후 페이지 새로고침
    	                location.reload();
    	            }
    	        };
    	        xhr.send();
    	    }
    	}
    </script>
</head>
<body>
    <h1>이미지 목록</h1>
    <div class="image-list">
    
    <c:forEach var="imageUrl" items="${imageUrls}">
	 <img src="${pageContext.request.contextPath}${imageUrl}" style="width:150px;">
        <button onclick="deleteImage('${imageUrl}')">삭제</button>
    <c:if test="${fn:startsWith(imageUrl, '/upload/202422923133943')}">
    <a href="/everland">에버랜드 </a>
       <img src="${pageContext.request.contextPath}${imageUrl}" style="width:150px;">
        <button onclick="deleteImage('${imageUrl}')">삭제</button>
    </c:if>
    
     
    </c:forEach>
    
       
   
        
       
    </div>
</body>
</html>