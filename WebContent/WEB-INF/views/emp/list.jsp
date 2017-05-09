<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript" src="${pageContext.request.contextPath }/scripts/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
	// 将一个超链接转换成为一个post提交
	$(function(){
		$(".delete").click(function(){
			var flag = confirm("确定要删除吗?");
			if(flag){
				var url = $(this).attr("href");
				// 获取表单对象
				$("#_form").attr("action",url);
				// 提交
				$("#_form").submit() ;
			}
			return false; // 不走超链接使用form表单提交 
		});
	});
</script>
</head>
<body>

<form action="" method="post" id="_form"></form>

<c:if test="${ page == null || page.numberOfElements == 0 }">
	无记录
</c:if>

<c:if test="${page != null || page.numberOfElements >0 }">
	<table border="1" cellpadding="10" cellspacing="0">
		<tr>
			<th>Id</th>
			<th>LastName</th>
			<th>Email</th>
			<th>Birth</th>
			<th>CreateTime</th>
			<th>Dept</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${page.content }" var="item">
			<tr>
				<td>${item.id }</td>
				<td>${item.lastName }</td>
				<td>${item.email }</td>
				<td>
					<fmt:formatDate value="${item.birth }" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${item.birth }" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<!-- 级联属性：部门 ,由于默认没有开启懒加载，所以可以正常显示
						开在在@ManyToOne注解开启懒加载
				-->
				<td>${item.dept.deptName }</td>
				<td>
					<a href="${pageContext.request.contextPath }/emp/${item.id}">编辑</a>
				</td>
				<td>
					<a class="delete" href="${pageContext.request.contextPath }/emp/delete/${item.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
				<td colspan="8">
					共${page.totalElements} 条记录
					共${ page.totalPages} 页
					当前第${page.number+1 } 页
					<a href="?pageNo=${page.number+1-1 }">上一页</a>
					<a href="?pageNo=${page.number+1+1 }">下一页</a>
				</td>
			</tr>
	</table>
</c:if>

<c:forEach items="${page.content}" var="itme">
	
</c:forEach>
</body>
</html>