

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fbdatabase.*;

import java.sql.*;


@WebServlet("/Login")
public class Login extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String email= request.getParameter("email");
		String password= request.getParameter("password");
	
		User user= new User(null,email,password);
		Dbhandler db= new Dbhandler();
		User u=db.isValid(user);

		if(u!=null) {
			RequestDispatcher r= request.getRequestDispatcher("welcome.jsp");
			HttpSession session= request.getSession();
			session.setAttribute("user",u);
			r.forward(request, response);
		}
		else {
			RequestDispatcher r=request.getRequestDispatcher("index.jsp");
			request.setAttribute("error_login","Invalid id or password");
			r.forward(request, response);
		}
	}

}
