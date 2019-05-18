## 1.概念
- Spring web开发框架
- Spring使用的是基本的JavaBean来完成以前只可能由EJB完成的事情。然而，Spring的用途不仅仅限于服务器端的开发。从简单性、可测试性和松耦合性角度而言，绝大部分Java应用都可以从Spring中受益。
- 使用基本的JavaBean代替EJB，并提供了更多的企业应用功能
- Spring是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架
- 容器——Spring包含并管理应用对象的配置和生命周期，在这个意义上它是一种容器，
- Spring是一个轻量级控制反转(IoC)和面向切面(AOP)的容器框架。
- 任何的java和javaBean都会被当做Bean来处理，推荐使用ApplicationContext.
## 2.Spring的具体开发步骤
1. 导入相应的jar包
2. 写实体类
3. 写配置文件applicationContexxt.xml
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
	xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="
        http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <!-- id是对象的名字,一个标识      class 是类名 （全类名） -->
    <!-- 通过ioc容器进行创建对象-->
    <bean id="user" class="spring.demo1.User" >
     <!-- 使用setter方式  用的是property标签  name是属性的名字 -->
   </bean>
</beans>

```
4. 测试（1.获取ICO容器；2.获取对象）
```java
		//1.获取IOC容器
		ApplicationContext applicationCont=new ClassPathXmlApplicationContext("applicationContext.xml");
		//2.从容器中获得对象  由IOC创建对象
		User user=(User) applicationCont.getBean("user");

```
## 3.scope
```
// 如果 <bean id="user" class="spring.demo1.User" scope="singleton">单例 则创建的对象为同一个
		// 默认时是singleton
		// 如果 <bean id="user" class="spring.demo1.User" scope="prototype">多例 则创建的不是同一对象
		/*
		 * scope:prototype在使用到对象时才创建对象(ser user1=(User) applicationCont.getBean("user");
		 * 会调用无参构造) scope:singleton 单例时 首先对象被创建
		 * 在获得IOC容器，在启动时，创建bean,整个应用中只有一个对象，容器被初始化之前
		 *
		 */
```
```
<bean id="user" class="spring.demo1.User" scope="singleton">
    </bean>
    
```

## 4.lazy-init
- 延迟创建 lazy-init="default" 默认为default
- default:不延迟，启动时创建   无参构造   得到IOC容器 
- true: 延迟 启动时不创建 使用时创建  得到IOC容器    无参构造
```xml
 <bean id="user" class="spring.demo1.User" scope="singleton" lazy-init="true"></bean>
 ```

## 5.init-method  destroy-method
- init-method="init_user" : 对象的初始化
- destroy-method="destroy_user"  :对象的销毁
```xml
 <bean id="user" class="spring.demo1.User" scope="singleton" lazy-init="true" init-method="init_user" destroy-method="destroy_user"></bean>
 ```
在User.java中:
```java
public void init_user() {
		System.out.println("对象初始化---");
	}
	public void destroy_user() {
		System.out.println("对象销毁---");
	}
```
## 6. IOC创建对象的方式
- SpringIOC容器：spring的核心容器  作用  创建对象 处理对象的依赖关系
### 6.1 调用无参构造
```xml
<!-- 1.调用无参构造 -->
    <bean id="user" class="spring.demo2.User" ></bean>
```
### 6.2.使用带参构造
```xml
<!-- 2.调用带参构造 -->
    <bean id="user2" class="spring.demo2.User">
        <!-- 说明构造的参数 必须跟构造函数的参数顺序一样  或者 index-->
        <!--
        <constructor-arg value="111">      </constructor-arg>
        <constructor-arg value="Tom">   </constructor-arg>
        
        -->
         <constructor-arg value="Tom" index="1">   </constructor-arg>
         <constructor-arg value="111" index="0">      </constructor-arg>
       
    </bean>
```
```xml
<!-- 3.带参构造 引用其他对象 -->
    <bean id="s" class="java.lang.String">
    	 <constructor-arg value="Tom">      </constructor-arg>
    </bean>
    <bean id="user3" class="spring.demo2.User">
        <constructor-arg value="111">      </constructor-arg>
        <constructor-arg  ref="s">   </constructor-arg>
    </bean>
```

### 6.3.工厂创建
#### 6.3.1.静态方法
```xml
 <!-- 使用工厂静态方法创建对象 -->
    <bean id="user5" class="spring.demo2.UserFactory"  factory-method="getStaticInstance"></bean>
```
UserFactory.java:
```
public static User getStaticInstance() {
	 return new User(103,"Jack");
 }

```
#### 6.3.2.实例方法(不重要)
```xml
<!-- 4.工厂方法创建对象 -->
    <!-- 创建一个工厂类的实例 -->
    <bean id="userFactory" class="spring.demo2.UserFactory" ></bean>
    <!-- 使用工厂实例方法创建对象 -->
    <bean id="user4" factory-bean="userFactory" factory-method="getInstance"></bean>
```
UserFactory.java:
```java
public User getInstance() {
	 System.out.println("使用工厂类中的实例方法创建");
	 return new User(102,"Herry");
 }
```
## 7.DI--Dependency Injection，依赖注入
- 指类与类之间的联接，依赖关系表示一个类的定义依赖另一个类。类与类之间使用与被使用的关系
- java语言体现在 局部变量，方法的形参，或对静态方法的调用。
- ioc 控制反转 就是我们自己不new来创建对象 而是把对象的创建权利交给Spring容器

### 7.1 通过构造
```xml
  <!-- 1.通过构造依赖注入 -->
    <bean id="s" class="java.lang.String">
    	 <constructor-arg value="Tom">      </constructor-arg>
    </bean>
    <bean id="user3" class="spring.demo3.User">
        <constructor-arg value="111">      </constructor-arg>
        <constructor-arg  ref="s">   </constructor-arg>
    </bean>
    

```
### 7.2 通过setter
```xml
	<bean id="b1" class="ioc.B"/>
	<bean id="a" class="ioc.A">
		<!-- 
			property元素：指定容器采用set方法来注入相应的值。
			name属性值为 A类中 set属性名() 去掉set前缀并小写的字母，
				 比如：A类中有setBx(B b)，则 name=“bx” 
			ref属性指定要依赖的bean	，  这里值为 B类 的bean 的id值，
	 	-->
		<property name="bx" ref="b1"/>
	</bean>

```
