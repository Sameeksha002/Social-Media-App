<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="fbdatabase.*" %>
<%@ page import="java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"> </script>
<title>Welcome</title>
</head>
<body class="bg-gradient-to-b from-cyan-500 to-blue-500 h-screen">

<%if(session.getAttribute("user")!=null){
User u=(User)session.getAttribute("user");
Dbhandler db= new Dbhandler();
ArrayList <Friend> rf=db.pending(u.getEmail());
ArrayList <Friend> f=db.requestAccept(u.getEmail());
ArrayList <Wallpost> w=db.postDisplay(u.getEmail());%>


<table class="">
<%//start of 1 row--------------------------- %>
<tr>

<%//name of user %>
<td class="text-white text-bold text-4xl text-center py-9 w-96 ml-9">
Hello <%=u.getName()%>
</td>

<%//request box %>
<td class="text-white text-bold text-2xl text-center py-9" >
<form action="SendRequest" method="post">
<label class="font-medium text-lime-200 font-bold text-bold text-3xl" for="remail">Enter Friend Email</label>
<input class="w-80 border-solid border-4 border-blue-500 p-1 mt-4 mx-2" type="text" name="remail" placeholder="Enter Friend Email Here">
<input class="cursor-pointer text-center w-40 rounded-md text-blue-500 bg-green-300 p-1 mx-4" type="submit" value="SendRequest">
</form>

<div class="error"><%if(session.getAttribute("remailerror")!=null){%>
<%=session.getAttribute("remailerror")%><%}%>
</div>

<div class="error">
<%if(session.getAttribute("errorfriend")!=null) {%>
<%=session.getAttribute("errorfriend")%><%} %>
</div>
</td>


<%//edit profile option %>
<td class="text-white text-bold text-2xl text-center py-9">
<a class="bg-lime-600 mx-10 p-4" href="edit.jsp">EditProfile</a>
</td>

</tr>

<%//start of second row----------------------------- %>
<tr>

<%//All Request are shown here %>
<td class="text-white text-bold text-2xl text-center">
<h1 class="text-yellow-400 text-bold text-4xl font-bold">All Requests</h1>
<%for(Friend frd:rf){ 
   String semail=frd.getSemail();%>
   <%=db.getNames(semail)%>
   &nbsp&nbsp
<a href="Accept?fid=<%=frd.getFid()%>">Accept</a>&nbsp&nbsp
<a href="Reject?fid=<%=frd.getFid()%>">Reject</a><br>
<%} %>
</td>

<%//Write Post here %>
<td class="text-white text-bold text-2xl text-center mx-4">
<form action="Savepost" method="post" enctype="multipart/form-data">
<textarea class="text-black" name="wpostcont" placeholder="Write Post Here" rows="5" cols="70"></textarea>
<input type="file" name="photo" placeholder="Choose File"/>
<input class="cursor-pointer text-center w-40 rounded-md text-blue-500 bg-green-300 p-1 mx-4" type="submit" value="SavePost">
</form>
</td>

<%//All Friend Are shown here %>
<td class="text-white text-bold text-2xl text-center">
<h1 class="text-yellow-400 text-bold text-4xl font-bold">Friends</h1>
<%for(Friend frd:f){ 
	if(frd.getRemail().equals(u.getEmail())){
	   String semail=frd.getSemail();%>
<div class="grid-cols-2">
<div class="text-start text-left text-3xl text-lime-200 p-2 mt-2 w-fit">
	   <%=db.getNames(semail)%>
</div>      
	<%}else{
      String remail=frd.getRemail();%>
<div class="text-start text-left text-3xl text-lime-200 p-2 mt-2 w-fit">      
      <%=db.getNames(remail)%>
</div>      
      <%}%>
      &nbsp&nbsp
<div class="bg-yellow-400 mx-2 p-1 text-white">
<a href="profile.jsp?sprofile=<%=frd.getSemail()%>">Profile</a>
</div>
</div>
<br>
<%} %>
</td>

</tr>

<%//start of 3 row-------------------------------- %>
<tr>
<td class=""></td>

<td class="">
<div class="grid-cols-2 text-center">
<h1 class="text-yellow-400 text-bold text-4xl font-bold" >POST</h1>
<%for(Wallpost wp:w){
String email=wp.getSemail();
String name=db.getNames(email);%>
<div class="grid justify-items-start text-lime-200 text-bold text-xl">
<%=name%>
</div>
<div class="grid justify-items-end text-lime-200 text-bold text-xl">
<%=wp.getDt()%>
</div>
<div class="">
<%=wp.getWpostcontainent()%>	
</div>
<% }%>
</div>
 </td>
 
 <td class=""></td>
</tr>

</table>

<%} else{response.sendRedirect("index.jsp");}%>
</body>
</html>