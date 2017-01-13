<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>update</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user2?action=delete&username=${command.username}" method="post">
		用户名：${command.username}<br/>
		真实姓名：${command.realname}<br/>
		<input type="submit" value="删除"/>
	</form>
</body>
</html>
