<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>schoolInfo</title>
</head>
<body>
	<form method="post">
		学校类型:<select name="schoolInfo.type">
				<c:forEach items="${schoolTypeList }" var="type">
					<option value="${type}">${type}</option>
				</c:forEach>
			</select><br/>
		学校名称:<input type="text" name="schoolInfo.name"/><br/>
		专业:<input type="text" name="schoolInfo.specialty"/><br/>
		<input type="submit" name="_target0" value="上一步"/>
		<input type="submit" name="_target2" value="下一步"/>
	</form>
</body>
</html>
