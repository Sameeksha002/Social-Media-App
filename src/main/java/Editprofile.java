

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fbdatabase.Dbhandler;
import fbdatabase.User;

@WebServlet("/Editprofile")
public class Editprofile extends HttpServlet {
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String name= request.getParameter("name");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		User user= new User(name,email,password);
		Dbhandler db= new Dbhandler();
		db.update(user);
		if(db.getError().length()==0) {
			HttpSession session= request.getSession();
			session.setAttribute("user",user);
			request.setAttribute("editsuccess","Updation Successfully");
			RequestDispatcher rd= request.getRequestDispatcher("edit.jsp");  	  
			rd.forward(request, response);
		}
		else {
			RequestDispatcher rd= request.getRequestDispatcher("edit.jsp");  
			request.setAttribute("editerror","Updation Unsuccessful");
			rd.forward(request, response);
		}
	}

}
