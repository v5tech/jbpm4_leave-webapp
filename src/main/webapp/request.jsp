<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
  <fieldset>
    <legend>申请</legend>
    <!-- 此是普通用户，也就是变量owner的处理页面，不管当前登陆什么角色，统一提交完成任务的请求即可，
   	在submit.jsp里调用完成任务的API，至于会如何完成,完全由流程定义图决定，我们编程只管调用完成即可 
    -->
    <form action="submit.html" method="post">
      <input type="hidden" name="processVo.taskId" value="${param.id}">
      <!-- 此处的owner与day两个name值要与流程图中定义的一致,owner是在xml中定义的变量，不属于具体登陆者 -->
      申请人：<input type="text" name="processVo.owner" value="${user}"/><br/>
  请假时间：<input type="text" name="processVo.day" value=""/><br/>
  age：<input type="text" name="processVo.age" value=""/><br/>
  sex：<input type="text" name="processVo.sex" value=""/><br/>
    请假原因：<textarea name="processVo.reason"></textarea><br/>
    <input type="submit"/>
    </form>
  </fieldset>

</body>
</html>