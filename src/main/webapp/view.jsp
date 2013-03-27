<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- <img src="pic.jsp?id=${id }" style="position:absolute;left:0px;top:0px;"> -->
<img src="pic.html?id=${id }" style="position:absolute;left:0px;top:0px;">
<div style="position:absolute;border:2px solid red;left:${ac.x }px;top:${ac.y }px;width:${ac.width }px;height:${ac.height}px;"></div>
</body>
</html>