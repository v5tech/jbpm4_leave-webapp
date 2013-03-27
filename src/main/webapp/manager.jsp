<%@ page language="java" contentType="text/html; charset=UTF-8"   pageEncoding="UTF-8" isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <fieldset>
    <legend>经理审核  username: ${user }  param.id:${param.id }</legend>
    <form action="submitManager.html" method="post">
      <input type="hidden" name="id" value="${param.id}">
	      申请人：<s:property value="map['owner']"/>  <br/>
	  请假时间：<s:property value="map['day']"/>  <br/>
	    请假原因：<s:property value="map['reason']"/>  <br/>
	    姓名：<s:property value="map['name']"/>  <br/>
	     性别：<s:property value="map['sex']"/>  <br/>
   	年龄：<s:property value="map['age']"/>  <br/>
    <input name="result" type="submit" value="批准"/><input name="result" type="submit" value="驳回"/>
    </form>
  </fieldset>

</body>
</html>