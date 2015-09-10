<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改文件</title>
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
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 200px;
	height: 64px;
	z-index: 1;
	left: 0px;
	top: 0px;
}
#apDiv2 {
	position: absolute;
	width: 200px;
	height: 115px;
	z-index: 2;
	left: 0px;
	top: 74px;
}
#apDiv3 {
	position: absolute;
	width: 200px;
	height: 47px;
	z-index: 1;
	left: 27px;
	top: 55px;
}
</style>
</head>

<body>
<%String name=request.getParameter("name");name=new String(name.getBytes("ISO-8859-1"),"utf-8");%>
<div id="apDiv1"><img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_01.jpg" width="1147" height="63" /></div>
<div id="apDiv2">
  <div id="apDiv3" align="center">
    <form id="form1" name="form1" method="post" action="file_alter">
     <table class="hovertable" style="width: 558px; ">
        <tr>
				<th width="500px">APP编号</th>
				<th width="500px">APP名称</th>
				<th width="500px">APP大小</th>
				<th width="500px">APP版本号</th>
				<th width="500px">APP下载地址</th>
			</tr>
        <tr>
          <td><input type="text" name="appid" id="appid"
					value='<%=request.getParameter("id")%>' /></td>
				<td><input type="text" name="appname" id="appname"
					value='<%=name%>' /></td>
				<td><input type="text" name="appsize" id="appsize"
					value='<%=request.getParameter("size")%>' /></td>
				<td><input type="text" name="appversion" id="appversion"
					value='<%=request.getParameter("version")%>' /></td>
				<td><input type="text" name="appdownl" id="appdownl"
					value='<%=request.getParameter("downl")%>' /></td>
        </tr>
      </table>
      <input name="" type="submit" value="完成"/>
      <a href="appfiles.jsp"><input type="button" value="返回"></a>
    </form>
  </div>
  <img src="<%=request.getContextPath()%>/images/ADwindow/Right_Down/Right_Down_02.jpg" width="1145" height="597" />
  
</div>
</body>
</html>