<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="fbdatabase.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
<link href="fbcss.css" rel="stylesheet">
<title>Edit Page</title>
</head>
<body>

<%User u =(User) session.getAttribute("user");%>
<div class=" container text-center rounded border border-success border border-4 bg-success-subtle">
<form action="Editprofile" method="post">
<label for="name">Enter Name:</label>
<input type="text"  name="name" value="<%=u.getName()%>" placeholder="Enter Your Name" ><br>
<label for="email">Your Email:</label>
<input type="text" name="email"  value="<%=u.getEmail()%>"placeholder="Enter Your Email" readonly="readonly"><br>
<label for="password">Enter Password:</label>
<input type="password" name="password" value="<%=u.getPassword()%>" placeholder="Enter Password Here"><br>
<input type="submit" value="Edit"><br>

<%if(request.getAttribute("editerror")!=null){%>
	<div class="error"><%=request.getAttribute("editerror")%></div>
<%}else if(request.getAttribute("editsuccess")!=null){%>
	<div class="success"><%=request.getAttribute("editsuccess")%></div>
<%}%>
<a href="welcome.jsp">Back</a>
</form>
</div>
</body>
</html>