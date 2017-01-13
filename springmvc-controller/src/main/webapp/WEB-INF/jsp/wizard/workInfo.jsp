<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>baseInfo</title>
</head>
<body>
	<form method="post">
		所在城市:<select name="workInfo.city">
				<c:forEach items="${cityList }" var="city">
					<option value="${city }">${city }</option>
				</c:forEach>
			</select><br/>
		职位:<input type="text" name="workInfo.job"/><br/>
		工作年限:<input type="text" name="workInfo.year"/><br/>
		<input type="submit" name="_target1" value="上一步"/>
		<input type="submit" name="_finish" value="完成"/>
		<input type="submit" name="_cancel" value="取消"/>
	</form>
</body>
</html>
