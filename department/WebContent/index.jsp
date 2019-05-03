<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
    request.setAttribute("path", path);
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%-- 判断list对象是否为空，重定向到servlet --%>
<c:if test="${list == null}">
	<c:redirect url="/DepartmentServlet?m=list"></c:redirect>
</c:if> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>部门管理综合应用demo</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="<%=path%>/css/main.css">
<link rel="stylesheet" type="text/css"
	href="<%=path%>/css/bootstrap.css">
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
</style>

</head>

<body>
	<div class="main">
		<div class="title">部门信息列表</div>
		<div class="selectDiv">
			<form action="${path}/DepartmentServlet?m=list" name="selectForm" method="post">
				部门编号:<input type="text" name="number" value="${number}" class="text" />&nbsp;
				部门名称:<input type="text" name="name" value="${name}" class="text" />&nbsp;
				<input type="submit" value="搜 索" class="button" style="padding-bottom: 10px" />

			</form>
		</div>

		<table class="tableList">
			<tr bgcolor="#66ccff">

				<th>部门编号</th>
				<th>部门名称</th>
				<th>部门位置</th>
				<th>相关操作&nbsp;[<a href="<%=path%>/jsp/addDept.jsp">添加部门</a>]
				</th>
			</tr>
			<c:if test="${!empty list }">
			<c:forEach items="${list }" var="d">
				<tr>
				<td>${ d.number}</td>
				<td>${ d.name}</td>
				<td>${d.location}</td>
				<td> <input type="button" value="删除"  onclick="del(${d.id})"> 
				<a href="/department/DepartmentServlet?m=updateBefore&id=${d.id }"> 编辑</a>
				  </td>
				</tr>
			</c:forEach>
			</c:if>

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
				//使用局部ajax进行局部刷新
	/* 			$.post("http://localhost:8080/department/DepartmentServlet",{"m":"del","currentPage":i},function(msg){
					//	alert(msg);//服务器返回的数据
					
				
				}); */
		//window.location.href='<%=path%>/pageServlet?currentPage='+i;
				selectForm.action = '<%=path%>/pageServlet?currentPage='+ i;
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

 
<%--  <a href="/department/pageServlet?currentPage=${page.firstPage }"> 首页</a>
 <a href="/department/pageServlet?currentPage=${page.prePage }"> 上一页</a>
 <a href="/department/pageServlet?currentPage=${page.nextPage }"> 下一页</a>
 <a href="/department/pageServlet?currentPage=${page.lastPage }"> 尾页</a>
 	$.post("http://localhost:8080/department/pageServlet",{},function(msg){
			//	alert(msg);//服务器返回的数据
				});
	跳转到：<input type="text" name="currentPage" size="2"/>页<input type="button" onclick="tiaozhuan()">
	跳转到：<input type="text"
 --%>
</body>
<script type="text/javascript">
/* 	function del(id) {
		//js原生的confirm方法会弹出一个提示框 有确认和取消两个操作 
		var msg = "您确定要删除该数据吗？";
		if (confirm(msg) == true) { //表示选择的是 确定
			window.location.href = "/department/DepartmentServlet?m=del&id=" + id

		}

	}
 */
 //使用ajax来代替 
	function del(id) {
	 
	//js原生的confirm方法会弹出一个提示框 有确认和取消两个操作 
		var msg1 = "您确定要删除该数据吗？";
		if (confirm(msg1) == true) { //表示选择的是 确定
			//window.location.href = "/department/DepartmentServlet?m=del&id=" + id
			$.post("http://localhost:8080/department/DepartmentServlet",{"m":"del","id":id},function(msg){
			//	alert(msg);//服务器返回的数据
				});
			
		}
		
	}
	
 	//在id等于	pagination 这个控件上生成分页控件
 	$.jqPaginator(
					'#pagination',
					{
						totalPages : 100, //一共有多少页  
						visiblePages : 10, //显示有多少页
						currentPage : parseInt($("#nowPage").val()), //当前是第几页 parseInt String转int parseInt(nowPage)
						first : '<li class="first"><a href="javascript:">首页</a></li>',
						prev : '<li class="prev"><a href="javascript:">上一页</a></li>',
						next : '<li class="next"><a href="javascript:">下一页</a></li>',
						page : '<li class="page"><a href="javascript:">{{page}}</a></li>',
						last : '<li class="last"><a href="javascript:">末页</a></li>',
						//当你点击上一页或者下一页的时候会触发这个函数 num是点击以后的第几页
						onPageChange : function(num, type) {
							if (type == 'change') {
								alert(num)
								location.href = "http://localhost:8080/department/pageServlet?m=list&nowPage="
										+ num;

							}

						}
					});  
</script>
</html>
