<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>增加用户</title>
</head>
<body>
	<form action="${path}/user/saveUser" method="post">
		<input name="id" type="hidden" value="${user.id}">
		用户名：<input name="username" type="text" value="${user.username }"/><br>
		密码：<input name="password" type="password" value="${user.password }"/><br>
		地址：<input name="address" value="${user.address }"><br>
		<input type="submit" value="保存">&nbsp;<input type="button" value="取消">
	</form>
</body>
<script type="text/javascript">
	function back(){
		window.localtion.href="${path}/user/userList";
	}
</script>
</html>