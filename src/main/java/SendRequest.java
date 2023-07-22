

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import fbdatabase.*;

/**
 * Servlet implementation class SendRequest
 */
@WebServlet("/SendRequest")
public class SendRequest extends HttpServlet {
  

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String remail=request.getParameter("remail");
		HttpSession session= request.getSession();
		User u=(User) session.getAttribute("user");
		String semail=u.getEmail();
		java.util.Date d= new Date();
		String date=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
		
		if(remail.equals(semail)) {
			session.setAttribute("remailerror","Self Request Can't Made");
			response.sendRedirect("welcome.jsp");
		}
		else {
		Friend f= new Friend(0,0,semail,remail,date);
		Dbhandler db= new Dbhandler();
		db.insert(f);
		session.setAttribute("errorfriend",db.getError());
		response.sendRedirect("welcome.jsp");
		 
	

		}
	}

}
