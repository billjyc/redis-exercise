<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("path", path);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>Add user</title>
    
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  <script type="text/javascript" src="<%=path %>/js/jquery-3.1.0.min.js"></script>
  <body>
    <form id="addUserForm" method="post">
    	<table>
	    	<tr>
	    		<td>姓名：</td>
	    		<td><input id="nameId" name="name" type="text" style="width:150px" /></td>
	    	</tr>
	    	<tr>
	    		<td>年龄：</td>
	    		<td><input id="ageId" name="age" type="text" style="width:150px" /></td>
	    	</tr>
    	</table>
    	<input type="button" value="Submit" onclick="addUser()" />
    </form>
  </body>
</html>
<script type="text/javascript">
	function addUser() {
		var url = "<%=basePath%>" + "user/addUser";
		console.log(url);
		//$("#addUserForm").attr("action", url);
		var name = $("#nameId").val();
		var age = $("#ageId").val();
		
		$.post(url, {"name":name, "age":age}, 
			function(data) {
				if(data.success == true) {
					$.message.alert("add user success!");
			}	
		});
			
	}
</script>
