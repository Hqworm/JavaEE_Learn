# Servlet #
#### 1.JAVAEE基本了解 ####
- javaee为企业开发版本.==Security安全是==Java EE的一个重要特点，也就是基于容器的安全访问。==跨J2SE/WEB/EJB的微容器.==
- bs/cs模式：
**B/S**:==B/S结构（Browser/Server==，浏览器/服务器模式），是WEB兴起后的一种网络结构模式，WEB浏览器是客户端最主要的应用软件。统一客户端，将系统功能实现的核心部分集中到服务器上。
**c/s**:==c/s(Client/Server)它是软件系统体系结构==，通过它可以充分利用两端硬件环境的优势，将==任务合理分配到Client端和Server端==来实现，降低了系统的通讯开销
 #### 2.http协议基本基本了解
1.   ==http:HyperText Transfer Protocal超文本传输协议==。http是客服端与服务端的请求 和 相应的（tcp）的一种交流 协议。
1.   特点：基于tcp  无状态  默认端口 80 基于请求响应模型 。
1. ==常用状态码==：
-   200（访问成功）
-   302（临时重定向，指出被请求的文档已被临时移动到别处，此文档的新的URL在Location响应头中给出）
  - 304（未修改，客户机缓存的版本是最新的，客户机应该继续使用 ）
  - ===404：访问的文件不存在===（服务器上不存在客户机所请求的资源）
  - ==500===：内部服务器错误（服务器端的CGI、ASP、JSP等程序发生错误）
#### 3. Tomcat基本了解
##### 1.tomcat基础：
- 默认端口8080 可以在server.xml修改
- 开启关闭在 bin/staruo.bat stop.bat
- 端口 被 占用 netstat -ano |findstr "8080"
- 在log/catalina-日期查看
 ##### 2.tomcat的基本结构
- ==serlvet创建==
![图片1](BC25359A00AB404E8592E7ACE6638F8E)
- ==在web.xml中配置servlet==
'''
 <!-- 配置该servlet -->
	<servlet>
		<!-- 给serlvet取的名字 默认可以和类名一致 -->
		<servlet-name>_1FirstServlet</servlet-name>
		<!-- serlvet 的全路径 -->
		<servlet-class>com.gok.servlet1._1FirstServlet</servlet-class>
	</servlet>
	<!-- 配置访问信息 -->
	<servlet-mapping>
		<!-- 必须跟上面的servlet-name 一致 -->
		<servlet-name>_1FirstServlet</servlet-name>
		<!-- 给serlvet的访问路径 默认可以是/类名 -->
		<!--  最终该servlet的访问路径是  http://localhost:8080/项目名/<url-pattern> 配置的值-->
		<url-pattern>/_1FirstServlet</url-pattern>
	</servlet-mapping>
'''
- ==路径访问==
![路径访问](A3D47E25AB4C4DC495679618E5095E5D)
- ==中文乱码==
![中文乱码](30CF08262D3D4EB79D7F4C4FF8287706)
- ==生命周期==
1. ==加载和实例化==：Servlet容器负责加载和实例化servlet，可以设置为servlet启动时，创建实例。在容器接受到servlet第一个请求时，创建servlet实例。通过类加载器去加载servlet类，然后调用servlet的默认构造函数构造，创建实例；
2. ==初始化== ：实例化之后会调用init()方法去初始化对象。初始化的目的：为了让servlet对象在处理客户端请求前完成初始化，如获取数据库的连接和信息。
3. 处理请求：  通过service（）方法处理请求。servlet实例通过==request==对象获取客户相关信息和请求信息。response的方法来进行响应。
4. 服务终止：  servlet容器在移出servlet实例之前，会·调用实例的==destroy（）==，以便该实例可以释放所用的资源。服务器终止发生在当需要释放内存或容器关闭时，以便垃圾回收收集进行回收。只能调用一次。
5. servlet的生命周期top：
          1、不要使用实例变量，容器为每一个调用分配一个线程，实例变变量最好用作常量以外最好不要用其他的； 2、不要使用init()方法中获取数据库（自实例创建后一直运行在服务器内存中，处理请求，直到销毁。数据库连接的数量是有上限的。） ；

#### 4.servlet内置对象
- ==Get和post区别==：
1. POST传输数据时，不需要在URL中显示出来，而GET方法要在URL中显示；
2. GET方法由于受到URL长度的限制,只能传递大约1024字节；POST传输的数据量大，可以达到2M
3. 请求效率  get会高一些 
注意：Get请求主要是如:“http://localhost:8080/ee1902_servlet1/_4Request?id=100&like=aaa&like=bbbb”

打开IE浏览器直接在地址栏输入Servlet的访问地址时产生的请求；
'a href="" 也是get请求；
表单也可以以get方式提交产生Get请求；
Ajax 的get请求
Post：form的post提交，ajax的post提交
- ==request==和请求相关的东西在这里面（request respoinse 都是doget dopost（）方法的直接使用）
       
        //获取参数  
        request.getParameter("参数名")
        String id = request.getParameter("id");
        System.out.println("id "+id);
        request.getParameterValues("参数名")  //获取同一个变量名有多个值
       	String[] arr = request.getParameterValues("like");
        System.out.println(Arrays.toString(arr));
        getContextpath()  //返回值是斜杠+项目名
        //==4获取请求的URl==
        //System.out.println(request.getRequestURL()+request.getQueryString());
        //request.getQueryString() ?后面的参数列表 （不包含问号 ）
        //设置编码  request.setCharacterEncoding("utf-8");
        //request还有一个作用就是可以下servlet之间进行数据传递 
        //setAttribute设置一个值 
        request.setAttribute("id", 10000);
        
        //转发到下一个serlvet(转发的含义是服务器内部进行跳转是不能调到其他服务器
        		request.getRequestDispatcher("/_5ReciveServlet").forward(reques, response);
        //获取值
        	request.getAttribute("id");
- ==response==


	response.setContentType("text/html;charset=utf-8");//向浏览器输出息
	response.getWriter().println("输出的信息sadadsa");
	//重定向（可以跳转到任意位置） 建议大家写完整的路径
	//response.sendRedirect("http://baidu.com");
	//response.setHeader("", value); 设置不同的头会有不同的效果 
	//设置3秒钟跳转
    response.setHeader("refresh", "3;url=http://baidu.com"); 

- 其他对象 Servlet上下文:ervletContext【代表当前web应用】他也可以保存数据，可以配置一些全局参数 （了解 ，能看懂 不要求记住）
Servlet配置相关:ServletConfig  【serlet的配置信息】
在web。Xml 的servlet标签 
       <init-param>
            <param-name>gok</param-name>
            <param-value>gokvalue</param-value>
            </init-param>





  



