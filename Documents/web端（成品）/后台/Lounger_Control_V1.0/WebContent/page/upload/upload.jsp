<%@ page contentType="text/html;charset=UTF-8" %>
<%response.setHeader("Pragma","No-cache"); %>
<%response.setHeader("Cache-Control","no-cache"); %>
<%response.setHeader("Expires","0");%>

<html>
<head>
<script language='Javascript'>
function doSubmit(){
	var phone = document.getElementById('photo').value;
	if(phone == ''){
		alert('文件为空，请重新选择');
		return;
	}

	document.getElementById('sform').submit();
}

</script>
<title>上传文件</title>
</head>
<body >
<div align='center'>
  <form id='sform' action = "File_Upload2" enctype="multipart/form-data" method="post">
<input type="file" name="nfile" id='photo'>
</form>
<input type='button' onclick='doSubmit()' value='上传文件'/>
<br/>
</div>
</body>
</html>

