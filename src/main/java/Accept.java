

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fbdatabase.Dbhandler;

@WebServlet("/Accept")
public class Accept extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int fid= Integer.parseInt(request.getParameter("fid"));
		Dbhandler db= new Dbhandler();
		db.Accept(fid);
		response.sendRedirect("welcome.jsp");
	
	}

}
