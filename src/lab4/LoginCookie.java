package lab4;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.LoginCookieInfo;

/**
 * Servlet implementation class LoginCookie
 */
@WebServlet("/lab4/LoginCookie")
public class LoginCookie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String getName( HttpServletRequest request )
    {
        Cookie[] cookies = request.getCookies();
        if( cookies != null )
            for( Cookie cookie : cookies )
                if( cookie.getName().equals( "email" ) )
                    return cookie.getValue();
        
        return null;
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Login Cookie</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
	

		out.println("<h1>Login ID</h1>");

		out.println("<div class=\"card-body\">");

		out.println(" <div class=\"form-group\">");
		out.println("<form action=\"MembersOnly \" method=\"post\">");
		out.println("<label for=\"email\" Email Address \">");
	
		String email = getName(request);
		Cookie cookie = new Cookie("email", email);
		if(email == null) {
		out.println("<input type=\"text\" class=\"form-control\" id=\"email\" name=\"email\" aria-describedby=\"emailHelp\" placeholder=\"Enter email\">");
		}
		else if(cookie != null) {
			response.sendRedirect("MembersOnly");
		}
		
		out.println("</div>");

		out.println(" <div class=\"form-group\">");
		out.println("<label for=\"name\" Password \">");
		out.println(
				"<input type=\"password\" class=\"form-control\" id=\"exampleInputPassword1\" name=\"password\" placeholder=\"Password\">");
		out.println("</div>");

		out.println("	<input type=\"submit\" class=\"btn btn-info\" name=\"submitBtn\" value=\"Login\">");
		out.println("</form>");
		out.println("</div>");

		out.println("</body>");
		out.println("</html>");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		
		if (request.getParameter("email") != null && request.getParameter("password") != null) {
			String email = request.getParameter("email");
			String password = request.getParameter("password");
			
			boolean isValidEmail = email != null && email.trim().length() > 0;
			boolean isValidPassword = password != null && password.trim().length() > 0;
			
			if (email == null) {
				email = getName(request);
			}
			
			if(isValidEmail && isValidPassword) {
				ArrayList<LoginCookieInfo> loginInfo = (ArrayList<LoginCookieInfo>) getServletContext().getAttribute("loginInfo");
				loginInfo.add(new LoginCookieInfo(email, password));
			
				Cookie cookie = new Cookie("email", email);
				
				cookie.setPath("/");
				response.addCookie(cookie);
			}
		}
		
		response.sendRedirect("MembersOnly");
	}
		

}