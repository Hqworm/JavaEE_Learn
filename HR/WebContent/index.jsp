<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/HR/ListEmpServlet?m=list&nowPage=1">
	管理员名：<input type="text" name="AdminName"></br>
	密码：<input type="password" name="AdminPwd"></br>
	验证码：<input type="text" name="verify"> &nbsp;&nbsp;&nbsp;
	<img onclick="change(this)" alt=" " style="cursor:pointer" src="/HR/jsp/img.jsp" ><br>
<input type="submit" value="登录">
</form>
<script>
function change(_this){
	_this.src="/HR/jsp/img.jsp?date="+new Date();//多了一个变量，每次访问的时间不太一样
	
}
</script>
</body>
</html>