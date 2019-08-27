package photos;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import models.PhotoInfo;

/*
10pts - Each photo album should have a name and a description.
20pts - A User should be able to create and delete albums.
30pts - A User should be able to upload images to a specific album
40pts - A User should be able to view all images in an album
10pts - A User should be able to delete any image from an album
This should also delete the image from the file system
20pts - A User should be able to download any image from an album
10pts - A User should be able to link directly to an image in an album
 */
@WebServlet("/photos/Albums")
public class Albums extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Created a local, empty array list of type Guest Book Entry
		ArrayList<PhotoInfo> albumsEntries = new ArrayList<PhotoInfo>();
		
		// Pre-populate my guest book with some entries
		albumsEntries.add( new PhotoInfo("Class Photos", "This is my class photos"));
		albumsEntries.add( new PhotoInfo("Hidden Pictures", "My stash photos"));
		albumsEntries.add( new PhotoInfo("Etc", "Any other photos that is one time use"));
		
		// Add the array list to the application scope (Servlet Context)
		getServletContext().setAttribute("albumsEntries", albumsEntries);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		

		// Get the array list of guest book entries from the application scope
		ArrayList<PhotoInfo> albumsEntries = (ArrayList<PhotoInfo>) getServletContext().getAttribute("albumsEntries");
		
		// Set my content type
		response.setContentType("text/html");
		
		// Get a reference to the Print Writer
		PrintWriter out = response.getWriter();
		
		// Generate our content
		out.println("<!DOCTYPE html>");
		out.println("<html lang=\"en\">");
		out.println("<head>");
		out.println("    <meta charset=\"UTF-8\">");
		out.println("    <title>Guest Book</title>");
		out.println("    <link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css\" integrity=\"sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO\" crossorigin=\"anonymous\">");
		out.println("</head>");
		out.println("<body>");
		out.println("<div class=\"container\">");
		
		out.println("<h1>Photo Book</h1>");
		
		// Display a table of all albums entries
		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println("  <tr>");
		out.println("    <th>Name</th>");
		out.println("    <th>Description</th>");
		out.println("    <th>Actions</th>");
		out.println("  </tr>");
		
		
		// Iterate over all albums entries, and display one row per entry in my table
		for( PhotoInfo entry : albumsEntries ) {
			out.println("<tr>");
			out.println("  <td>" + entry.getName() + "</td>");
			out.println("  <td>" + entry.getDescription() + "</td>");
			out.println("  <td>");
			out.println("       <a href=\"ViewAlbum?id=" + entry.getId() + "&name=" + entry.getName() + "\">View</a>");
			out.println("       | ");
			out.println("       <a href=\"DeleteAlbum?id=" + entry.getId() + "\">Delete</a>");
			out.println("  </td>");
			out.println("</tr>");

		}
		
		out.println("</table>");
		
		out.println("<a class=\"btn btn-primary\" href=\"CreateAlbum\">Create a Album</a>");
		
	
		
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
