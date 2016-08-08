<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>All users</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table>
  	<c:if test="${not empty userList}"> 
	    <c:forEach items="${userList}" var="user" varStatus="vs">
	    	<tr>
	    		<td align="center">${user.id}</td>
	    		<td align="center">${user.name }</td>
	    		<td align="center">${user.age }</td>
	    	</tr>
	    </c:forEach>
    </c:if> 
    </table>
  </body>
</html>
