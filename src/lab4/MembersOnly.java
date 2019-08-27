package lab4;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.LoginCookieInfo;

/**
 * Servlet implementation class MembersOnly
 */
@WebServlet("/lab4/MembersOnly")
public class MembersOnly extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String getName( HttpServletRequest request )
    {
        Cookie cookie[] = request.getCookies();
        
        if( cookie != null ) {
            for(Cookie cookies : cookie ) {
                if( cookies.getName().equals( "email" ) ) {
                    return cookies.getValue();
                }
            }
        }
       
        return null;
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");  
        PrintWriter out=response.getWriter();  
          
        String emailUser = request.getParameter("email");
        String email = getName(request);

        
       
        
        if(email != null) {
        	if(email == "null") {
            	response.sendRedirect("LoginCookie");
            }
        	out.println("<b>Welcome to Mile High Club</b>");
        	out.println("<br>Welcome Back, "+ email + "<br>");
        }
       
        else {
        	out.println("<b>Welcome to Mile High Club</b>");
        	out.println("<br>Welcome, "+ emailUser + "<br>");
        }
        
        
       
        out.println("<a class=\"btn btn-info\" href=\"../lab4/LoginCookie\">Back to Login</a>");
        
       
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String email = request.getParameter("email");
		
		Cookie cookie = new Cookie( "email", email );
		cookie.setMaxAge(30);
		cookie.setPath("/");
		response.addCookie(cookie);
		
		
		doGet(request,response);
	}

}

