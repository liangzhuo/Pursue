<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>资源列表</title>
</head>
<body>
	<table>
		<c:forEach items="${resources}" var="resource">
			<tr>
				<td>${resource.fileName}</td>
				<td>${resource.contentType}</td>
				<td><a href="">修改</a>&nbsp;
					<a href="${path}/resource/delete?id=${resource.id}">删除</a>&nbsp;
					<a href="${path}/resource/download?id=${resource.id}">下载</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	<a href="${path}/resource/addResource">上传</a>
</body>
</html>