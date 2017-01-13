<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>baseInfo</title>
</head>
<body>
	<form method="post">
		username:<input type="text" name="username" value="${user.username }"/><br/>
		password:<input type="password" name="password"/><br/>
		realname:<input type="text" name="realname"/><br/>
		<input type="submit" name="_target1" value="下一步"/>
	</form>
</body>
</html>
