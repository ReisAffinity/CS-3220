package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PhotoInfo;

@WebServlet("/photos/CreateAlbum")
public class CreateAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Set my content type
		response.setContentType("text/html");
		
		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();
		
		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Create Albums</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		out.println("<h1>Add Content</h1>");
		
		String name = request.getParameter("name");
		if (name == null)
			name = "";
		
		String description = request.getParameter("description");
		description = description == null ? "" : description;
		
		
		out.println("<form action=\"CreateAlbum\" method=\"post\">");
		
		out.println("	Name: <input type=\"text\" name=\"name\" value=\"" + name + "\"> <br>");
		
		if (request.getAttribute("nameError") != null)
			out.println("   <p class=\"text-danger\">Please enter a name</p>");
		
		out.println("	Description: <br>");		
		out.println("	<textarea name=\"description\">" + description + "</textarea> <br>");
		
		if (request.getAttribute("descriptionError") != null)
			out.println("   <p class=\"text-danger\">Please enter a description</p>");
		
		out.println("	<input type=\"submit\" name=\"submitBtn\" value=\"Create Album\">");
		out.println("</form>");
				
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
		
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		if (request.getParameter("submitBtn") != null) {
			
			// Read the name and the message that was submitted by the form
			String name = request.getParameter("name");
			String description = request.getParameter("description");
			
			boolean isValidName = name != null && name.trim().length() > 0;
			boolean isValidDescription = description != null && description.trim().length() > 0;
			
			
			// Add new entry to the guest book
			if (isValidName && isValidDescription) {
				// get a reference to the array list of guest book entries
				ArrayList<PhotoInfo> albumsEntries = (ArrayList<PhotoInfo>) getServletContext().getAttribute("albumsEntries");
				
				// Create a new entry and add it to the guestbook
				albumsEntries.add(new PhotoInfo(name, description));
			} 
			else {
				
				// Problem with the name?
				if (!isValidName)
					request.setAttribute("nameError", "Invalid Name");
			
				if (!isValidDescription)
					request.setAttribute("descriptionError", "Invalid Description");
				
				doGet(request, response);
				return;
			}
			
			
			
		}
		
		// Redirect the User back to the main guestbook page
		response.sendRedirect("Albums");
	}

}
