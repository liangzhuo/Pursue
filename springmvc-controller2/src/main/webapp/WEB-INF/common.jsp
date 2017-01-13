<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	String appPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>

