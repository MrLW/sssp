<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	$(function(){
		$("#lastName").change(function(){
			// 获取用户名
			var lastName = $(this).val();
			//去掉空格
			lastName = $.trim(lastName) ;
			// 重新设置
			$(this).val(lastName);
			
			// 如果修改的值和之前的lastName相同，则不发送ajax请求.
			var _oldLastName = $("#_oldLastName").val() ;
			_oldLastName = $.trim(_oldLastName) ;
			// 不用发送ajax请求
			if(_oldLastName != null && _oldLastName != "" && _oldLastName == lastName){
				alert(lastName + "可用")
				return ;
			}
			
			// 开始请求服务器
			var url = "${pageContext.request.contextPath}/validateLastName" ;
			var args = {"lastName":lastName,"date":new Date()};
			
			$.post(url,args,function(data){
				if(data == 1 ){
					alert(lastName + "可用")
				}else{
					alert(lastName + "不可用")
				}
			});
		});
	});
</script>
</head>
<body>
<!-- 默认情况下为报错url -->
<c:set value="${pageContext.request.contextPath }/emp/save" var="url"></c:set>
<c:if test="${employee.id != null }">
	 <!-- 更新url -->
	<c:set value="${pageContext.request.contextPath }/emp/update" var="url"></c:set>
</c:if>

<form:form action="${ url }" method="POST" modelAttribute="employee"> <!-- 这里的modelAttribute对应后台的employee属性 -->
<!--	在修改的情况下， 用一个隐藏域来存放上一次的用户名 -->
<c:if test="${employee.id != null }">
	<input type="hidden" id="_oldLastName" value="${employee.lastName }">
	<!-- 向后台传递一个id，用来查询 -->
	<form:hidden path="id"/> <!-- 当隐藏域和要提交的属性时使用form:hidden -->
	<!-- <input type="hidden" name="_method" value="PUT">:主要用于RESTful风格 -->
</c:if>

LastName:<form:input path="lastName" id="lastName"/><br>
Email:<form:input path="email"/><br>
Birth:<form:input path="birth"/><br>
Dept:<form:select path="dept.id" items="${depts }"
		itemLabel="deptName" itemValue="id">
	</form:select><br>
	<input type="submit" value="提交">
</form:form>
</body>
</html>