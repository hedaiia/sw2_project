<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send friend request</title>
</head>
<body>
<form action="/social/groupMessage" method="post">	
Email addresses : <input type = "text" name="receiverEmail" /><br>		
	Message name : <input type = "text" name="messageName" /><br>
	Message :<br> <textarea rows="5" cols="21" name = "messageText"></textarea><br>
<input type = "submit" value="Send Message">
  </form>
  </body>
</html>