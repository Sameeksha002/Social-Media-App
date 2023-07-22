

import java.io.IOException;

import java.io.PrintWriter;
import java.sql.*;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fbdatabase.*;




@WebServlet("/Signup")
public class Signup extends HttpServlet {
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		User user= new User(name,email,password);
		Dbhandler db= new Dbhandler();
		db.insert(user);
		if(db.getError().length()==0) {
			HttpSession session= request.getSession();
			session.setAttribute("user",user);
			RequestDispatcher rd= request.getRequestDispatcher("welcome.jsp");  	  
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd= request.getRequestDispatcher("index.jsp");  
			request.setAttribute("error",db.getError());
			rd.forward(request, response);
		}
		
	}

}
