<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<script src="https://cdn.tailwindcss.com"> </script>


<title>Home Page</title>
</head>

<body >

<script>

//Password Validation Function 
function passwordValidate(){
	
	var pass = document.getElementById("spass");
	var upper = document.getElementById("upper");
	var lower = document.getElementById("lower");
	var special = document.getElementById("special_ch");
	var num = document.getElementById("number");
	var length = document.getElementById("length");
	
	const lowcase=new RegExp('(?=.*[a-z])');
	const uprcase=new RegExp('(?=.*[A-Z])');
	const no=new RegExp('(?=.*[0-9])');
	const spch=new RegExp('(?=.*[!@#\$%\^&\*])');
	const len=new RegExp('(?=.{8,20})');
	
	
	//check if password contain number
	if(pass.value.match(no)){  
		//match is function which matchs a regular expression 
		num.style.color = "green";
	}
	else{num.style.color = "red";}
	
	//check if password contain uppercase
	if(pass.value.match(uprcase)){   
		//match is function which matchs a regular expression 
		upper.style.color = "green";
	}
	else{upper.style.color = "red";}
	
	//check if password contain lowercase
	if(pass.value.match(lowcase)){   
		//match is function which matchs a regular expression 
		lower.style.color = "green";
	}
	else{lower.style.color = "red";}
	
	//check if password contain length
	if(pass.value.match(len)){   
		//match is function which matchs a regular expression 
		length.style.color = "green";
	}
	else{length.style.color = "red";}

	//check if password contain special
	if(pass.value.match(spch)){  
		//match is function which matchs a regular expression 
		special.style.color = "green";
	}
	else{special.style.color = "red";}
}

//Input Field Can't Leave Blank
function isValid(value,id){
	if(value.length==0){
		  document.getElementById(id).innerHTML="Cant Leave It Blank";
	  }
	else document.getElementById(id).innerHTML="";
}

//check that nothing is leave blank before form submission
function Form_submit(){
	  var flag=0;
	  
	  if(document.getElementById("snm").value.length==0){
		  flag=1;
	  }
	  if(document.getElementById("sem").value.length==0){
		  flag=1;
	  }
	  if(document.getElementById("spass").value.length==0){
		  flag=1;
	  }
	
	  if(flag=1){
		  return false;
	  }
	  else return true;
}
</script>

<div class="bg-gradient-to-b from-cyan-500 to-blue-500 h-screen">

<%//Website Name %>
<div class="text-center font-medium text-lime-200 font-bold text-5xl pt-6 ">
Welcome To ChitChat
</div>

<%//--------------------------------------------------------- %>
<div class="grid grid-cols-1 lg:grid-cols-2 gap-x-10 mx-32">

<%//SignUp Code %>
<div class="grid justify-items-center my-14 border-double border-4 border-lime-300">
<h1 class="text-center font-medium text-lime-200 font-bold text-4xl pt-6">----SignUp Here----</h1>

<%//SignUp Form %>
<form class="my-6" action="Signup" method="post"  onsubmit="return Form_submit()">
<label class="text-white text-bold text-xl"  for="name">Enter Name:</label><br>
<input class="w-80 border-solid border-4 border-blue-500 p-1 mt-4" type="text" name="name" id="snm" placeholder="Enter Your Name" onblur="isValid(this.value,'error')"><br>
<div  class="text-red-700 my-2" id="error"></div>
<label class="text-white text-bold text-xl"  for="email">Enter Email:</label><br>
<input class="w-80 border-solid border-4 border-blue-500 p-1  mt-4" type="email" name="email" id="sem" placeholder="Enter Your Email" onblur="isValid(this.value,'error1')"><br>
<div class="text-red-700 my-2" id="error1"></div>
<label class="text-white text-bold text-xl"  for="password">Enter Password:</label><br>
<input class="w-80 border-solid border-4 border-blue-500 p-1 mt-4" type="password" name="password" id="spass" placeholder="Enter Password Here" onkeyup="return passwordValidate()"><br>
<div class="text-red-700 my-2" id="error2"></div>

<%//Submit Button %>
<div class="flex justify-center">
<input class="hover:bg-yellow-200 cursor-pointer text-center w-28 rounded-md text-blue-500 bg-green-300 font-extrabold p-2 my-4"  type="submit" value="Signup"><br>
</div>

<%//Password Checking Conditions %>
<div>
 <ul>
 <li id="upper" class="text-slate-50">At Least One UpperCase</li>
 <li id="lower" class="text-slate-50">At Least One LowerCase</li>
 <li id="special_ch" class="text-slate-50">At Least One Special Character</li>
 <li id="number" class="text-slate-50">At Least One Number</li>
 <li id="length" class="text-slate-50">At Least Six Character</li>
 </ul>
</div>

<%if(request.getAttribute("error")!=null){%>
	<div class="text-red-900"><%=request.getAttribute("error") %></div>
<%}%>

</form>
</div>

<%//--------------------------------------------------------- %>
<div class="grid justify-items-center my-14 border-double border-4 border-lime-300">
<h1 class="text-center font-medium text-lime-200 font-bold text-4xl pt-6">----Login Here----</h1>

<%//Login Form %>
<form class="my-6" action="Login" method="post">
<label class="text-white text-bold text-xl"  for="email">Enter Email:</label><br>
<input class="w-80 border-solid border-4 border-blue-500 p-1 mt-4" type="email" name="email" id="lem" placeholder="Enter Your Email" onblur="isValid(this.value,'error3')"><br>
<div class="text-red-700 mb-12" id="error3"></div>
<label class="text-white text-bold text-xl"  for="password">Enter Password:</label><br>
<input class="w-80 border-solid border-4 border-blue-500 p-1 mt-4" type="password" name="password" id="lpass" placeholder="Enter Password Here" onblur="isValid(this.value,'error4')"><br>
<div class="text-red-700" id="error4"></div>

<%//Submit Button %>
<div class="flex justify-center mt-8">
<input class=" hover:bg-yellow-200 cursor-pointer text-center w-28 rounded-md text-blue-500 bg-green-300 font-extrabold p-2 my-4"  type="submit" value="Login"><br>
</div>


<%if(request.getAttribute("error_login")!=null){%>
	<div class="text-red-900"><%=request.getAttribute("error_login") %></div>
<%}%>

</form>
</div>

</div>
</div>

</body>
</html>