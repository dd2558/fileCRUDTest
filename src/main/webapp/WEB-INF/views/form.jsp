<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<h1>파일 업로드 예제</h1>


<form action="/uploadMember" method="post" enctype="multipart/form-data">
    <input type="file" name="file">
    <input type="text" name="name" placeholder="작성자">
    <textarea name="content" placeholder="내용"></textarea>
    <button type="submit">전송</button>
</form>


	<form action="/uploadMultiple" method="post" enctype="multipart/form-data">
	        사진(여러장)<input type="file" name="files" multiple>
	        <br> <br>
        <button type="submit">여러장의 사진업로드(최대3장)</button>
    </form>

<p> <a href='/list'> 글 목록보기</a> /p>

</body>
</html>