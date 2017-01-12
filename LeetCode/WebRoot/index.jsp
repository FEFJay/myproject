<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
.Style {
		color: #000000;
		font-size: 11px;
		font-family: Arial, sans-serif;
		letter-spacing: 3px;
		background: none;
		margin-bottom: 0px;
		margin-left: 0px;
		margin-right: 0px;
		margin-top: 0px;
		margin-bottom: 3px;
		text-decoration: none;
		line-height: 14px;
		display: compact;
		padding: 0px;
}
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
     
 <table width="100%" border="0" cellspacing="0" cellpadding="4">

	<tr> 

		<td bgcolor="#000099"> 

			<table width="100%" border="0" cellspacing="0" cellpadding="4">

				<tr> 

					<td bgcolor="#FFFFFF">&nbsp;<b>*</b>&nbsp;</td>

						<td width="100%"><font color="#CCCCCC">&nbsp; <font color="#FFFFFF">Title</font></font></td>

				</tr>

			</table></td>

	</tr>

	<tr> 

		<td width="100%" bgcolor="#EAEAEA" colspan="2"> 

			<form name="Name" action="post"><p>

				<label for="textfield">Field 1</label>

				<br>

				<input type="text" name="textfield" id="textfield">

				</p>

				<p>

				<label for="textfield2">Field 2</label>

				<br>

				<input type="text" name="textfield2" id="textfield2">

				</p>

				<p> 

				<input type="submit" name="Submit" value="GO">

				</p>

				<p>&nbsp; </p>

				</form>

		</td>

  </tr>

</table>
     
     
  </body>
</html>
