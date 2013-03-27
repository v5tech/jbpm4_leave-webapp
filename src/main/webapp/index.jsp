<%@ page language="java" contentType="text/html; charset=UTF-8" isELIgnored="false"%>
<%@taglib uri="/struts-tags" prefix="s" %>
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>index</title>
	</head>
	<body>
		<a href="deploy.html">发布新流程</a>&nbsp;[username: ${user }]
		<a href="login.jsp">登陆</a>

		<table border="1" width="100%">
			<caption>
				流程定义
			</caption>
			<thead>
				<tr>
					<td>
						id
					</td>
					<td>
						name
					</td>
					<td>
						version
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</thead>
			<tbody>
				<s:iterator value="processDefinitionList">
				<tr>
					<td>${id }</td>
					<td>${name }</td>
					<td>${version }</td>
					<td>
						<!-- 在定义流程中，删除的是整个部署 ，所以传定义的部署id-->
						<a href="remove.html?id=${deploymentId }">remove</a>
						&nbsp;|&nbsp;
						<a href="start.html?id=${id }">start</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>

		<table border="1" width="100%">
			<caption>
				流程实例
			</caption>
			<thead>
				<tr>
					<td>
						id
					</td>
					<td>
						activity
					</td>
					<td>
						state
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="processInstanceList">
					<tr>
						<td>${id }</td>
						<td><s:property value="findActiveActivityNames()"/>  </td>
						<td>${state }</td>
						<td>
							<a href="view.html?id=${id }">view</a>
						</td>
					</tr>
				</s:iterator>
			</tbody>
		</table>

		<table border="1" width="100%">
			<caption>
				待办任务
			</caption>
			<thead>
				<tr>
					<td>
						id
					</td>
					<td>
						name
					</td>
					<td>
						&nbsp;
					</td>
				</tr>
			</thead>
			<tbody>
				
				<s:iterator value="taskList">
				<tr>
					<td>${id }</td>
					<td>${name }</td>
					<td>
						<!-- getFormResourceName从xml文件中取值 -->
						<a href="${formResourceName }?id=${id}">view</a>
					</td>
				</tr>
				</s:iterator>
			</tbody>
		</table>
	</body>
</html>
