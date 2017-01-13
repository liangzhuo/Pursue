<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户列表</title>
<style type="text/css">
	table {
	  border-collapse:collapse;
	}
	table,td {
		border: 1px solid blue;
	}
</style>
</head>
<body>
	<table>
		<thead>
			<tr>
				<td>序号</td>
				<td>用户名</td>
				<td>密码</td>
				<td>地址</td>
				<td>操作</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="user" items="${userList}" varStatus="status">
				<tr>
					<td>${status.count }</td>
					<td>${user.username }</td>
					<td>${user.password }</td>
					<td>${user.address }</td>
					<td><a href="${path}/user/initUpdateUser?id=${user.id}">修改</a><a href="${path}/user/deleteUser?id=${user.id}">删除 </a></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<a href="${path}/user/initAddUser">增加</a>&nbsp;
</body>
</html>