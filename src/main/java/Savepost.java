

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import fbdatabase.*;


@WebServlet("/Savepost")
@MultipartConfig(maxFileSize=16177215)
public class Savepost extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String wpostcont = request.getParameter("wpostcont");
		
		//Fetching file
		Part photo = request.getPart("photo");
		InputStream inputstream = null;
		if(photo!=null) {
        	inputstream = photo.getInputStream();
        }
		
		System.out.print(photo);
		HttpSession session= request.getSession();
		User user = (User) session.getAttribute("user");
		Dbhandler db= new Dbhandler();
		java.util.Date d= new Date();
		String date=(d.getYear()+1900)+"-"+(d.getMonth()+1)+"-"+d.getDate();
		Wallpost w = new Wallpost(1,user.getEmail(),wpostcont,date,inputstream.toString());
		db.insert(w);
		response.sendRedirect("welcome.jsp");
	}

}
