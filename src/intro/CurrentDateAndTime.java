package intro;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.catalina.servlet4preview.ServletContext;


@WebServlet("/CurrentDateAndTime")
public class CurrentDateAndTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		
		int count = 0; //Local Variable
		
		ServletContext context = (ServletContext) this.getServletContext();
		
		context.setAttribute("dateAndTimeCounter", count);
		
		
	}


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
			ServletContext context = (ServletContext) this.getServletContext();
			
			//Get a reference to the current count
			
			int count = (Integer) context.getAttribute("dateAndTimeCounter");
			count++;
			
			context.setAttribute("datAndTimeCounter", count);
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
	        
	        out.println("<h1> Current Date and Time </h1>");
	        out.println("<p class=\"lead\">The current date and time is: " + new Date() + "</p>");
	        
	        out.println("</div>");
	        out.println("</body>");
	        out.println("</html>");    
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
