<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
request.setAttribute("path", path);
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<script type="text/javascript" src="<%=path%>/js/1.8.3_jquery.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/jqPaginator.js"></script>
<style type="text/css">
* {
	font-size: 14px;
	font-family: "黑体";
}

.main {
	width: 800px;
	margin: 0 auto;
}

.selectDiv {
	margin-top: 10px;
	margin-bottom: 10px;
	border: #33ccff solid 1px;
	border-radius: 4px;
	text-align: center;
	height: 40px;
	line-height: 40px;
}

.tableList {
	width: 800px;
	border-collapse: collapse;
	border: #ccc solid 1px;
}

.tableList td, th {
	border: #ccc solid 1px;
	border-collapse: collapse;
	text-align: center;
	height: 25px;
}

.trHover:hover {
	background-color: #ccffff;
}
.title{
	text-align: center;
	height:50px;
	font-size: 25px;
	font-family: "黑体";
}
</style>

</head>

<body>
	<div class="main">
		<div class="title" >员工信息列表</div>

		<table class="tableList">
			<tr bgcolor="#66ccff">
				<th>员工编号</th>
				<th>员工姓名</th>
				<th>部门名称</th>
				<th>修改</th>
				<th>删除</th>
			</tr>
			 <c:if test="${!empty employeeList}">
			<c:forEach items="${employeeList}" var="employee">
				<tr>
					<td>${employee.empId}</td>
					<td><a href="${request.getContextPath }/HR/DisplayEmpServlet?id=${employee.empId}&deptId=${employee.deptId}&m=display">${employee.eName}</a></td>
					<td>
						<c:forEach items="${DeptList}" var="dept">
							<c:if test="${employee.deptId eq dept.deptId}">
								${dept.dName}
								
							</c:if>
							
						</c:forEach>
						
					</td>

					<td><a href="${request.getContextPath }/HR/PreAddEmpServlet?id=${employee.empId}&deptId=${employee.deptId}&m=update">编辑</a></td>
					
					<td>  <input type="button" value="删除" onclick="del(${employee.empId})"> </td>
				</tr>
			</c:forEach>
			</c:if>
			
			 <tr><td><a href="/HR/PreAddEmpServlet?m=display">添加</a><td></tr>

		</table>

			<!-- 分页条  -->
		<div class="pagination" style="margin: 0px;" id="pagination"></div>

		<div class="line" style="text-align: center;">
			<!-- 分页标签输出 -->
			总记录:${count}&nbsp;每页${pageSize}条&nbsp;
			当前页:${currentPage}/${pageCount}&nbsp;

			<!-- 首页  上一页    currentPage==1-->
			<c:choose>
				<c:when test="${currentPage <= 1}">
					首页 &nbsp;上一页 &nbsp;
				</c:when>
				<c:otherwise>
					<a href="javascript:pageTo(1);">首页</a>&nbsp;
					<a href="javascript:pageTo(${currentPage-1});">上一页</a>&nbsp;
				</c:otherwise>
			</c:choose>

			<!-- 下一页  尾页  currentPage==pageCount  -->
			<c:choose>
				<c:when test="${currentPage >= pageCount}">
					下一页 &nbsp;尾页 &nbsp;
				</c:when>
				<c:otherwise>
					<a href="javascript:pageTo(${currentPage+1});">下一页</a>&nbsp;
					<a href="javascript:pageTo(${pageCount});">尾页</a>&nbsp;
				</c:otherwise>
			</c:choose>

			跳转到第<select onchange="pageTo(this.value);">
				<c:forEach var="i" begin="1" end="${pageCount}">
					<option value="${i}" ${currentPage==i?'selected':'' }>${i}</option>
				</c:forEach>
			</select>页
			<script type="text/javascript">
			function pageTo(i){
		
		window.location.href='/HR/pageServlet?currentPage='+i;
				selectForm.action = '/HR/pageServlet?currentPage='+ i;
					selectForm.submit();
				}
			</script>
		</div>

<!-- 百度效果 -->
		<div class="line" style="text-align: center;">
			<c:forEach var="i" begin="1" end="${pageCount}">
			<c:choose>
			<c:when test="${currentPage==i}">
			[${i}]&nbsp;
			</c:when>
			<c:otherwise>
			[<a href="javascript:pageTo(${i});">${i}</a>]&nbsp;
			</c:otherwise>
			</c:choose>
			
			</c:forEach>


		</div>



	</div>

<!-- 使用到nowPage,但是又不显示 -->
	<input type="hidden" id="nowPage" value="${ nowPage}"> 

</body>
<script type="text/javascript">
	function del(empId) {
		//js原生的confirm方法会弹出一个提示框 有确认和取消两个操作 
		var msg = "您确定要删除该数据吗？";
		if (confirm(msg) == true) { //表示选择的是 确定
			window.location.href = "/HR/DeleteEmpServlet?m=del&empId=" + empId

		}

	}

	//在id等于	pagination 这个控件上生成分页控件
	$
			.jqPaginator(
					'#pagination',
					{
						totalPages : 100, //一共有多少页  
						visiblePages : 10, //显示有多少页
						currentPage : parseInt($("#nowPage").val()), //当前是第几页 parseInt String转int parseInt(nowPage)
						first : '<li class="first"><a href="javascript:;">首页</a></li>',
						prev : '<li class="prev"><a href="javascript:;">上一页</a></li>',
						next : '<li class="next"><a href="javascript:;">下一页</a></li>',
						page : '<li class="page"><a href="javascript:;">{{page}}</a></li>',
						last : '<li class="last"><a href="javascript:;">末页</a></li>',
						//当你点击上一页或者下一页的时候会触发这个函数 num是点击以后的第几页
						onPageChange : function(num, type) {
							if (type == 'change') {
								alert(num)
								location.href = "http://localhost:8080/HR/ListEmpServlet?m=list&nowPage="
										+ num;

							}

						}
					});
	</script>
</body>
</html>
