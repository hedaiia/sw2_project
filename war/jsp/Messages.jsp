<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send message</title>
</head>
<body>

<form action="/social/sendMessage" method="post">
Name : <input type="text" name="uname" /> <br>
message : <input type="text" name="message" /> <br>
  
  <input type="submit" value="send message ">
  </form>
  </body>
</html>
