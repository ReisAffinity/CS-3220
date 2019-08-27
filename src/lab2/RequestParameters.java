package lab2;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/lab2/RequestParameters")
public class RequestParameters extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void init(ServletConfig config) throws ServletException {
		super.init(config);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String userName =request.getParameter("username");
		String password = request.getParameter("password");
		String fullName = request.getParameter("fullName");
		String phoneNumber = request.getParameter("phoneNumber");
		String days[]; 
		days = request.getParameterValues("favorite");
		String hobbies[];
		hobbies = request.getParameterValues("hobby");
		String languages[];
		languages = request.getParameterValues("language");
		
		PrintWriter out = response.getWriter();
		

		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");

		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Request Info</title>");
		out.println(
				"     <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		out.println("<h1>Requests and Value</h1>");
		
		out.println("    <td> Request " + request.getMethod() + ":</td>");
		out.println("    <td>" + new Date() + "</td>");
		
		out.println("<table class=\"table table-bordered table-striped table-hover\">");

		out.println("  <tr>");
		out.println("    <th>Request</th>");
		out.println("    <th>Value</th>");
		out.println("  </tr>");
		
		// Display Username
		out.println(" <tr>");
		out.println("  <td>UserName</td>");
		out.println("  <td>" +  userName + "</td>");
		out.println(" </tr>");
		
		// Display Password
		out.println(" <tr>");
		out.println("  <td>Password</td>");
		out.println("  <td>" + password + "</td>");
		out.println(" </tr>");

		out.println("</table>");
		out.println("</div>");

		out.println("<div class=\"container\">");
		
		
		out.println("<h3>Get Request with Multiple Forms</h3>");
		
		out.println("    <td> Request " + request.getMethod() + ":</td>");
		out.println("    <td>" + new Date() + "</td>");
		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println(" <tr>");
		out.println(" <th>Favorite</th>");
		out.println(" </tr>");
		
		out.print("<tr>");
		out.println("<td>");
		if(days !=null) {
			for(int i = 0; i < days.length; i++) {
				out.println(days[i] + ",");
			}
		}
		else {
			out.println("none");
		}
		out.println("</td>");
		out.println("</table>");

		out.println("</div>");
		
		
		
		out.println("<div class=\"container\">");
		
		out.println("<h3> Post Request Parameters</h3>");
		out.println("    <td> Request " + request.getMethod() + ":</td>");
		out.println("    <td>" + new Date() + "</td>");
		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		
		out.println("<tr>");
		out.println(" <td>Parameters</td>");
		out.println(" <td>Values</td>");
		out.println("<tr>");
		
		out.println("<tr>");
		out.println(" <td>Full Name</td>");
		out.println("  <td>" + fullName +  "</td>");
		out.println(" </tr>");
		
		out.println("<tr>");
		out.println(" <td>Phone Number</td>");
		out.println("  <td>" + phoneNumber + "</td>");
		out.println(" </tr>");
		
		
		out.println("</table>");
		out.println("</div>");
		
		out.println("<div class=\"container\">");
		
		out.println("<h3> Post Request Multiple Values</h3>");
		out.println("    <td> Request " + request.getMethod() + ":</td>");
		out.println("    <td>" + new Date() + "</td>");
		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		
		out.println("<tr>");
		out.println(" <td>Hobbies</td>");
		out.println(" <td>Favorite Language</td>");
		out.println("<tr>");
		
		out.println("<tr>");
		out.println("  <td>");
		if(hobbies != null) {
			for(int i = 0; i < hobbies.length; i++) {
				out.println(hobbies[i]);
				out.println("<br>");
			}
		}
		else {
			out.println("None");
		}
		out.println(" </td>");
		out.println("<td>");
		if(languages !=null) {
			for(int i = 0; i < languages.length; i++) {
				out.println(languages[i]);
				out.println("<br>");
			}
		}
		else {
			out.println("None");
		}
		
		out.println(" </td>");
		out.println(" </tr>");
		
		
		out.println("</table>");
		out.println("</div>");
		
		out.println("</body>");
		out.println("</html>");

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	

}