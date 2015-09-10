<%@page import="com.LRKZ.Info.Files_info"%>
<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>文件查看与删除</title>

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
<body bgcolor="#005bac" leftmargin="0" topmargin="0" marginwidth="0" marginheight="0">
<!-- Save for Web Slices (查看与删除.psd) -->
<table id="__01" width="1147" height="660" border="0" cellpadding="0" cellspacing="0">
	<tr>
		<td>
			<img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_01.jpg" width="1147" height="63" alt=""></td>
	</tr>
	<tr>
		<td>
			<img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_02.jpg" width="1147" height="597" alt="">
			<div id="apDiv1" align="center">
				<table class="hovertable" style="width: 558px; ">
		<tr>
			
			<th width=100 height=20>
							<div style="width: 100; overflow: hidden">APP编号</div>
						</th>
		                <th width=100 height=20>
							<div style="width: 100; overflow: hidden">APP名称</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">APP大小</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">APP版本号</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">APP下载地址</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">修改</div>
						</th>
						<th width=100 height=20>
							<div style="width: 100; overflow: hidden">刪除</div>
						</th>
		</tr>
		<c:set var="a" value="${not empty allfiles }"></c:set>
		<c:if test="${a }">
			<c:forEach items="${allfiles }" var="f">

				<tr onmouseover="this.style.backgroundColor='#ffff66';"
						onmouseout="this.style.backgroundColor='#d4e3e5';">
					<td>${f.appId }</td>
					<td>${f.appName }</td>
					<td>${f.appSize }</td>
					<td>${f.appVersion }</td>
					<td>${f.appDownl }</td>
					<td><a
						href="alter_file.jsp?id=${f.appId }&name=${f.appName }&size=${f.appSize }&version=${f.appVersion }&downl=${f.appDownl }"><img
							src="<%=request.getContextPath()%>/images/alter/alter.png" width="22" height="22" target="window" /></a></td>
					<td><a href="File_Delet?id=${f.appId }"><img onclick="return confirm('确认删除此项？');"
							src="<%=request.getContextPath()%>/images/alter/delete.png" width="22" height="22" target="window" /></a></td>
				</tr>

			</c:forEach>
		</c:if>
	</table>
			</div></td>
	</tr>
</table>
<!-- End Save for Web Slices -->
</body>
</html>
