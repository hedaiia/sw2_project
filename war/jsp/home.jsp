
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1256">
<title>Insert title here</title>
</head>
<body>
<p> Welcome b2a ya ${it.name} </p>
<p> This is should be user home page </p>
<p> Current implemented services "http://fci-swe-apps.appspot.com/rest/RegistrationService --- {requires: uname, email, password}" </p>
<p> and "http://fci-swe-apps.appspot.com/rest/LoginService --- {requires: uname,  password}" </p>
<p> you should implement sendFriendRequest service and addFriend service
<!-- <form action="/social/signout" method="post">
   
  <input type="submit" value="signout">
  
  </form>
   -->
   
   
   <a href="/social/signout">SignOut</a>
  <form action="/social/SendFriendRequest" method="post">
  addFriend : <input type="text" name="Femail" /> <br>
  <input type="submit" value="Login">


<form action="/social/acceptFriend" method="post">
  Name : <input type="text" name="uname" /> <br>
<input type="submit" value="Login">
</form>
</body>
</html>