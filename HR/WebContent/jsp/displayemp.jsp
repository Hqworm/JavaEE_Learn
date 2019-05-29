<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
* {
	font-size: 14px;
	font-family: "黑体";
}

table {
	width: 300px;
	border: #ccc solid 1px;
}

table, td {
	border: #ccc solid 1px;
	text-align: center;
	height: 25px;
}
</style>

</head>
<body>
	<!--  显示员工的所有信息  -->
	<table>
		<tr>
			<td>员工编号：
			<td>
			<td>${employeeOne.empId }
			<td>
		</tr>
		<tr>
			<td>员工姓名：
			<td>
			<td>${employeeOne.eName }
			<td>
		</tr>
		<tr>
			<td>员工性别：
			<td>
			<td>			
				<c:choose>
					<c:when test="${employeeOne.egendar==1}"> 男</c:when>
					 <c:otherwise>女</c:otherwise>
				</c:choose>
			<td>
		</tr>
		<tr>
			<td>部门名称：
			<td>
			<td>${DeptOne.dName }
			<td>
		</tr>
		<tr>
			<td><a
				href="${request.getContextPath }/HR/ListEmpServlet?m=list">返回</a></td>
		<tr>
	</table>
</body>
</html>