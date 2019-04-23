- ==JSTL：（JSP standard Tag Library==），是==jsp标签的集合，它封装了jsp应用的通用核心功能，实现了jsp页面的逻辑处理==。
- JSTL:与EL表达式一起使用，来实现在jsp中不出现java代码块。
- 核心标签（core tags)通过限制了作用域的变量管理数据，以及执行页面内容的迭代和条件操作。提供用来生成和操作的url的标记。
- 格式化标签（FOrmating tags) 定义了用来格式化的数据，数字和日期。
- sql标签
- xml标签：包含一些标记，用来操作xml表示数据
### jstl的安装：
```
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><!--使用核心标签-->
<%@ taglib prrefix="x" uri="http://java.sun.com/jsp/jstl/xml" %><!--使用xml标签库-->
<%@ taglib prrefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %><!--使用格式化标签库-->
<%@ taglib prrefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %><!--使用sql标签库-->
<%@ taglib prrefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %><!--使用函数标签库-->
 ```
## 1.jstl标签库
### 1.jstl:通用标签
#### 1.<c:out>：在jsp中显示数据
- <c:out> 与<%= %>区别：
```
<c:out value="person.address.street"></c:out>
${person.address.street}
<%= person.address().street() %>
```
- ==<c:out>== el，jsp表达式value为null时为空白，但<c:out value="user.name" default="当value为null时，会显示的值"></c:out>
#### 2.<c:set>可以计算表达式的值，使用结算结果来设置javaBean对象或java.util.Map对象的值。
- value:要存储的值 可以是对象，el表达式   <c:set var="person" scope="session" value="${person.dog} "></c:set>
- target:要修改的属性所属的对象 书：P220
- property:要修改的属性
- var:要存储的变量
- scope:要存储的位置
![JSTL01](142D8D368E7D4FE4B7B0990B6A3F24C5)
#### 3.<c:remove> 删除变量
- <c:remove var="变量名string"></c:remove>
#### 4.<c:catch>
- 处理产生错误信息的异常状况，可以存储错误信息。
### 2.条件处理
#### 1.==<c:if>==
```
<c:if test="${3>1 }">
	<c:out value="if为真"></c:out>
</c:if>
```
#### 2.==<c:choose><c:when>  <c:otherwise>==
```

<%
	Calendar now=Calendar.getInstance();//
	Integer hour=new Integer(now.get(Calendar.HOUR_OF_DAY));//获取当前时间
	request.setAttribute("hour", hour);
%>

<c:choose>
	<c:when test="${hour>=0 && hour<=11 }">
		<c:set var="sayHello" value="早上好！"></c:set>
	</c:when>
	<c:when test="${hour>=12 && hour<=17 }">
		<c:set var="sayHello" value="下午好！"></c:set>
	</c:when>
	<c:otherwise>
		<c:set var="sayHello" value="晚上好！"></c:set>
	</c:otherwise>
</c:choose>
<c:out value="现在的时间是：${hour}时，${sayHello } "></c:out>
```
### 3.循环处理
#### 1.<c:forEach>
- ==<c:forEach>单层循环==
```
<%
  List<String> songs =new ArrayList();
songs.add(0, "听妈妈的话");
songs.add(1,"稻香");
songs.add(2,"告白气球 ");
request.setAttribute("songs", songs);
%>
<!-- var:循环变量的变量名
	items:循环变量信息
 -->
<table>
<c:forEach var="song" items="${songs }">
	<tr>
	 	<td>${song }</td>
	</tr>
</c:forEach>
</table>
<c:forEach var="song" items="${songs }">
	${song }
</c:forEach>

<!-- vatStatus:获取循环变量的变量名称 -->
<table>
<c:forEach var="song" items="${songs }"  varStatus="songLoopCount">
	<tr>
		<td>编号:${songLoopCount.count }</td>
		<td>${song }</td>
	</tr>
</c:forEach>
</table>
```
- ==嵌套循环<c:forEach>==
```
<c:forEach var="ListElement" items="${movies }">
	<c:forEach var="movie" items="${ListElement }">
		${movie }
	</c:forEach>
</c:forEach>
```
### 4.URL
- 1.<c:import>
```
<%@ include file="/head.jsp" %>
<jsp:include page="/head.jsp" >
<!-- 可以跳转到其它wab容器1的资源，在请求时，将url属性值的内容插入到当前页面  -->
<c:import url="http://w3school.com.cn/jQuery/index.asp"></c:import><!--  -->
```
- 2.<c:param >属性: name value
与<c:url><c:import><c:redirect>配合使用,用于传参
#### <fmt:
```
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
.<!--添加相应的标签库-->
.
.
<%
	request.setAttribute("date", new Date());
	request.setAttribute("num", 100.12132432);
%>
<fmt:formatDate value="${date }" pattern="yyyy-MM-dd"/><br>
<fmt:formatDate value="${date }" pattern="yyyy-MM-dd:HH:mm:ss"/><br>
<fmt:formatDate value="${date }" pattern="yyyy-MM-dd:HH:mm:ss" var="MyDate"/><br>
日期为：<input type="text" value="${myDate }">

数字格式化：
<fmt:formatNumber value="${num }" pattern="##.##.00"></fmt:formatNumber><br/>
```



