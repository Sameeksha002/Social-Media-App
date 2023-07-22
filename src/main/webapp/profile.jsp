<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fbdatabase.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-aFq/bzH65dt+w6FI2ooMVUpc+21e0SRygnTpmBvdBgSdnuTN7QbdgL+OapgHtvPp" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha2/dist/js/bootstrap.bundle.min.js" integrity="sha384-qKXV1j0HvMUeCBQ+QVp7JcfGl760yU08IQ+GpUo5hlbpg51QRiuqHAJz8+BrxE/N" crossorigin="anonymous"></script>
<link href="fbcss.css" rel="stylesheet">
<title>Profile</title>
</head>
<body>
<%String sprofile= request.getParameter("sprofile");
Dbhandler db= new Dbhandler();
User u = db.getProfile(sprofile); %>
<table class="table">
<tr ><h1 class="table-success text-center">-----Profile-----</h1></tr>

<tr>
<td class="table-success text-center"><h3><%=u.getName()%></h3></td>
<td class="table-success text-center"><h3><%=u.getEmail()%></h3></td>
</tr>
</table>
</body>
</html>