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
	 <form action="/HR/EditEmpServlet" method="post">
		<table>
		
			<tr>
				<td><input type="hidden" value="${ employeeOne.empId}" name="empId">
				员工姓名:</td>
				<td><input type="text" name="addName" id="addName" value="${employeeOne.eName }"></td>
			<tr>
			<tr>
				<td>员工性别:</td>
				<td>
					 <input type="radio" name="gender" value="0" <c:if test="${employeeOne.egendar==0 }"> checked="checked"</c:if>  />    女&nbsp; &nbsp;
					<input type="radio" name="gender" value="1" <c:if test="${employeeOne.egendar==1 }"> checked="checked"</c:if> >男</td>
					
			<tr>
			<tr>
				<td>部门名称：</td>
				<td><select id="select" name="addDeptName">
						<option>请选择部门</option>
						<c:forEach items="${DeptList}" var="dept">
							<option <c:if test="${dept.deptId==employeeOne.deptId }">selected='selected'</c:if>>${dept.dName}</option>
						</c:forEach>
				</select></td>
			<tr>
			<tr>
				<td><input type="submit" value="修改">&nbsp; &nbsp;<input
					type="button" id="button" value="重置"></td>
			<tr>
		</table>
	</form>
	<script>
		
		$(function(){
			
			//为重置按钮绑定事件函数
			$("#button").click(function(){
				$("input[name='addName']").val("");
				$("input:radio[value='1']").attr('checked','checked');
				/* $("input:radio[value='0']").attr('checked','false');
				$("#single option:selected").val("1"); */
			});
		});
	</script>
</body>
</html>