<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>修改部门界面</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">

</head>

<body>
	<div class="main">
		<div class="title">
			<a href="<%=path%>/DeptServletUrl">部门列表</a> &gt;&nbsp;修改部门
		</div>
		<form action="<%=path%>/DepartmentServlet?m=update" method="post">
		<!-- 不用显示 但是要使用某一个数据 使用隐藏表单  -->
		<input type="hidden" name="id" value="${d.id }" class="text" />
			<div class="line">
				部门编号:<input type="text" name="number" value="${d.number}" class="text"/>
			</div>
			<div class="line">
				部门名称:<input type="text" name="name" value="${d.name}" class="text" />
			</div>
			<div class="line">
				部门位置:<input type="text" name="location" value="${d.location}" class="text" />
			</div>
			<div class="line">
				<input type="submit" value="修改部门" class="button" />&nbsp;<input
					type="button" value="返回列表" onclick="window.history.back();"
					class="button" />&nbsp;
			</div>
		</form>
	</div>
</body>
</html>
