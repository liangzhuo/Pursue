<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>registerAndValidator</title>
</head>
<body>
	<form:form commandName="user">
		<form:errors path="*" cssStyle="color:red"></form:errors><br/>
		<!-- 只显示username的信息 -->
		username:<form:input path="username"/><form:errors path="username" cssStyle="color:red"></form:errors><br/>
		password:<form:password path="password"/><form:errors path="password" cssStyle="color:red"></form:errors><br/>
		<input type="submit" value="注册"/>
	</form:form>
</body>
</html>