<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>
<html>
 <body bgcolor=white><Font size=1 >
 <script language="javascript">

 function login_click()
 {
 form1.action="Login";
 form1.submit();
 }
    
 function register_click()
 {
 form1.action="register.jsp";
 form1.submit();
 }
 </script>
 <form name="form1">
 <table>
 <tr>
 <td align="center">
 Account:
 </td>
 <td>
 <input size="18" type="text" name="account">
 </td>
 </tr>

<tr>
 <td align="center">
 Password:
 </td>
 <td>
 <input size="20" type="password" name="password">
 </td>
 </tr>
 </table>
 <table>
 <tr>
 <td width="60">
 </td>
 <td width="80">
  <input type="submit" name="register" value="register" onClick="return register_click()">
 </td>
                      
 <td width="80">
 <input type="submit" name="login" value="  login  " onClick="return login_click()">
 </td>
 </tr>
 </table>
 </form>
 </body>
 </html>

