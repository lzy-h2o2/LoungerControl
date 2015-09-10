<%@page import="com.LRKZ.Info.Advice_info"%>
<%@page import="com.sun.corba.se.spi.orbutil.fsm.Input"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>用户反馈信息</title>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 1144px;
	height: 279px;
	z-index: 1;
	left: 2px;
	top: 120px;
}
</style>
<style type="text/css">
table.hovertable {
	font-family: verdana, arial, sans-serif;
	font-size: 11px;
	color: #333333;
	border-width: 1px;
	border-color: #999999;
	border-collapse: collapse;
}

table.hovertable th {
	background-color: #c3dde0;
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}

table.hovertable tr {
	background-color: #d4e3e5;
}

table.hovertable td {
	border-width: 1px;
	padding: 8px;
	border-style: solid;
	border-color: #a9c6c9;
}
</style>
</head>
<body bgcolor="#005bac" leftmargin="0" topmargin="0" marginwidth="0"
	marginheight="0">
	<!-- Save for Web Slices (查看与删除.psd) -->

	<tr>
		<td><img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_01.jpg"
			width="1147" height="63" alt=""></td>
	</tr>
	<tr>
		<td><img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_02.jpg"
			width="1147" height="597" alt="">
			<div id="apDiv1" align="center">
				<table class="hovertable" style="width: 558px; ">
					<tr>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">用户编号</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">用户姓名</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">用户电话</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">用户邮箱</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">反馈信息</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">修改</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">删除</div>
						</th>
					</tr>
					<c:set var="a" value="${not empty advice }"></c:set>
					<c:if test="${a }">
						<c:forEach items="${advice }" var="f">

							<tr onmouseover="this.style.backgroundColor='#ffff66';"
								onmouseout="this.style.backgroundColor='#d4e3e5';">
								<td>${f.uid }</td>
								<td>${f.uname }</td>
								<td>${f.utel }</td>
								<td>${f.uemail }</td>
								<td>${f.uadvice }</td>
								<td><a
									href="alter_advice.jsp?uid=${f.uid }&uname=${f.uname }&utel=${f.utel }&uemail=${f.uemail }&uadvice=${f.uadvice }"><img
										src="<%=request.getContextPath()%>/images/alter/alter.png" width="22" height="22" /></a></td>
								<td><a href="Advice_delet?uid=${f.uid }"><img onclick="return confirm('确认删除此项？');"
										src="<%=request.getContextPath()%>/images/alter/delete.png" width="22" height="22" /></a></td>
							</tr>

						</c:forEach>
					</c:if>
				</table>
			</div> <!-- End Save for Web Slices -->
</body>
</html>
