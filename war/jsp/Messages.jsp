<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Message</title>
</head>
<body>

<form action="/social/sendMessage" method="post">
Email :<br><input type="text" name = "receiverEmail"></input><br><br>
Message :<br> <textarea rows="5" cols="21" name = "messageText"></textarea><br>
<input type = "submit" value="Send message">
</form>
  </body>
</html>
