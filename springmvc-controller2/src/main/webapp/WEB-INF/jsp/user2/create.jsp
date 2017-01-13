<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ include file="/WEB-INF/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>create</title>
</head>
<body>
	<form action="${pageContext.request.contextPath}/user2" method="post">
		用户名：<input type="text" name="username" /><br/>
		真实姓名：<input type="text" name="realname" /><br/>
		<input type="submit" name="create" value="新建"/>
	</form>
</body>
</html>
