<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>管理员操作平台</title>
<style type="text/css">
#apDiv1 {
	position: absolute;
	width: 604px;
	height: 19px;
	z-index: 1;
	left: 0px;
	top: 0px;
}
#apDiv2 {
	position: absolute;
	width: 87px;
	height: 343px;
	z-index: 2;
	left: 0px;
	top: 178px;
}
#apDiv3 {
	position: absolute;
	width: 348px;
	height: 287px;
	z-index: 3;
	left: 220px;
	top: 178px;
}
</style>
</head>

<body>

<table width="614" height="538" border="0">
  <tr>
    <td height="182" colspan="2"><div id="apDiv1"><iframe src="top-1.jsp" frameborder="0" width="1368" height="177"></iframe></div>
    </td>
  </tr>
  <tr>
    <td width="223"><div id="apDiv2">
      <iframe src="menu.jsp" frameborder="0" width="238" height="660"></iframe>
    </div></td>
    <td width="1139"><div id="apDiv3">
      <iframe src="admin_rule.jsp" name="window" frameborder="0" width="1147" height="660"></iframe>
    </div></td>
  </tr>
</table>
</body>
</html>