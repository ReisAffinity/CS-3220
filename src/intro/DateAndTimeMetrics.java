package intro;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DateAndTimeMetrics
 */
@WebServlet("/DateAndTimeMetrics")
public class DateAndTimeMetrics extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      response.setContentType("text/html");
	        
	        // Get a reference to the Print Writer
	        PrintWriter out = response.getWriter();
	        
	        // Generate our content
	        out.println("<!DOCTYPE html>");
	        out.println("<html lang=\"en\">");
	        out.println("<head>");
	        out.println("    <meta charset=\"UTF-8\">");
	        out.println("    <title>Document</title>");
	        out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
	        out.println("</head>");
	        out.println("<body>");
	        out.println("<div class=\"container\">");
	        
	        out.println("<h1>Metrics</h1>");
	        out.println("<p class=\"lead">The date and time page has been viewd: " + count + ")
	        out.println("</div>");
	        out.println("</body>");
	        out.println("</html>");    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
