### ==AJAX==:
- Ajax 即“**Asynchronous Javascript And XML**”（异步 JavaScript 和 XML），是指一种创建交互式网页应用的网页开发技术，前端的js
- 可以部分刷新
- **可以在部分刷新时跟服务器之间进行通信**
#### XMLhttp协议
- ==Xmlhttp:客户端与服务器进行通信的协议==
- ajax:可以发post get请求
#### 原生ajax[了解]：
```
<script>
	function ajaxget(){
		//创建异步对象
		var ajax=new XMLHttpRequest();
		ajax.open('get','http://localhost:8080/AJAX/testAjax?name=中文');// post: ajax.open('post','http://localhost:8080/AJAX/testAjax');
		// post: ajax.setRequestHeader("Content-type","application/x-ww-form-urlencoded");
		//发送求
		//post :ajax.send('name=XXX &age=78')---如果需要进行传递值得话
		ajax.send();
		//回调函数
		ajax.onreadystatechange=function(){
			//请求成功之后要做的事儿
			if(ajax.readyState==4 && ajax.status==200){
				//如果请求成功，则运行的函数
				alert(ajax.responseText);
			}
		}
		
	}
</script>
```
#### **重点** 
```
<script>
    $.ajax({"type":"get",
        "url":"url",
        "data":"name=aa&b=bb",
        success.function(msg){}
    })
    $.get({"url",
        {"变量名1"："变量值1","变量名2"："变量值2"},
        function(msg){}
    })
    $.post({"url",
        {"变量名1"："变量值1","变量名2"："变量值2"},
        function(msg){}
    })
·</script>
```  
具体实现：
```
<input type="button" value="触发Jquery ajax" onclick="ajaxGet()">
<script>
    function ajaxGet(){
	    $.ajax({
			"type":"post",//请求类型
			"url":"http://localhost:8080/AJAX/testAjax",//请求路径
			"data":"name=HQ & location=xhua",//请求参数,可以为空
			success:function(msg){//请求成功服务器返回后返回的信息
				$("#divId").html(msg);
			}
		}); 
	}
</script>
```
jQuery的简化版本，写AJAX
```
/* 
		post:发送的请求
		url:发送路径
		：参数
		：响应成功后执行的函数
		*/
		$.post("http://localhost:8080/AJAX/testAjax",{"name":"HQ"},function(msg){
			alert(msg);//服务器返回的数据
			});
```
- {}json里面是一个对象 ，JSON(JavaScript Object Notation, JS 对象简谱)是一种轻量级的数据交换格式。
```
//{"name":"HQ"}  js的一个数据格式--json {}每个元素之间用"，"隔开
		var jsonOBJ={"name":"HQ","age":"22"};
		//获取对象属性
		alert(jsonOBJ.name);
```
- ajax用来提交表单，就servlet没有办法进行跳转或重定向，如通需要跳转，需要在success中去跳转。
