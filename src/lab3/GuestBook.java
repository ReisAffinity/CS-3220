package lab3;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.GuestBookEntry;


@WebServlet(urlPatterns= {"/lab3/GuestBook"}, loadOnStartup=1)
public class GuestBook extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		
		// Created a local, empty array list of type Guest Book Entry
		ArrayList<GuestBookEntry> guestbookEntries = new ArrayList<GuestBookEntry>();
		
		// Pre-populate my guest book with some entries
		guestbookEntries.add( new GuestBookEntry("John Doe", "Hello, World!"));
		guestbookEntries.add( new GuestBookEntry("Mary Jane", "Hi!"));
		guestbookEntries.add( new GuestBookEntry("Joe Boxer", "Howdy!"));
		
		// Add the array list to the application scope (Servlet Context)
		getServletContext().setAttribute("guestbookEntries", guestbookEntries);
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the array list of guest book entries from the application scope
		ArrayList<GuestBookEntry> guestbookEntries = (ArrayList<GuestBookEntry>) getServletContext().getAttribute("guestbookEntries");
		
		
		
		// Check to see if the form was submitted
		if (request.getParameter("searchBtn") != null) {
			String searchType = request.getParameter("searchType");
			String searchQuery = request.getParameter("searchQuery");
			
			
			if (searchType.equals("ID")) {
				int id = Integer.parseInt(searchQuery);
				ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
				for(GuestBookEntry entry : guestbookEntries) {
					if (entry.getId() == id)
						searchResults.add(entry);
						
				}
				
				guestbookEntries = searchResults;
			}
			else if(searchType.equals("Name")) {
				
				String name = searchQuery;
				ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
					for(GuestBookEntry entry: guestbookEntries) {
						if(entry.getName().toLowerCase().contains(name.toLowerCase()))
							searchResults.add(entry);
					
				}
					guestbookEntries = searchResults;	
			}
			else if(searchType.equals("Message")) {
				String message = searchQuery;
				ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
				for(GuestBookEntry entry: guestbookEntries) {
					if(entry.getMessage().toLowerCase().contains(message.toLowerCase()))
						searchResults.add(entry);
				
				}
				guestbookEntries = searchResults;	
			}
			
			else if(searchType.equals("All Text Fields")) {
				String textField = searchQuery;
				ArrayList<GuestBookEntry> searchResults = new ArrayList<GuestBookEntry>();
					for(GuestBookEntry entry: guestbookEntries) {
						if(entry.getMessage().toLowerCase().contains(textField.toLowerCase()) && entry.getName().toLowerCase().contains(textField.toLowerCase()) ) {
							searchResults.add(entry);
						}
					}
					guestbookEntries = searchResults;
			}
			
		}
		
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
		
		out.println("<h1>Guest Book</h1>");
		
		out.println("<form action=\"GuestBook\" method=\"get\">");
		String searchQuery = request.getParameter("searchQuery");
		if(searchQuery == null) {
			searchQuery = "";
		}
		
		
		String[] searchType = request.getParameterValues("searchType");
		
		
		out.println("  <input type=\"text\" name=\"searchQuery\" value=\"" + searchQuery + "\">");
		out.println("  <select name=\"searchType\" value=\"" + searchType + "\">");
		out.println("    <option>ID</option>");
		out.println("    <option>Name</option>");
		out.println("    <option>Message</option>");
		out.println("     <option>All Text Fields</option>");
		out.println("  </select>");
		out.println("  <input type=\"submit\" name=\"searchBtn\" value=\"Search\">");
		out.println("</form>");
		
		// Display a table of all guest book entries
		out.println("<table class=\"table table-bordered table-striped table-hover\">");
		out.println("  <tr>");
		out.println("    <th>Name</th>");
		out.println("    <th>Message</th>");
		out.println("    <th>Actions</th>");
		out.println("  </tr>");
		
		
		// Iterate over all guest book entries, and display one row per entry in my table
		for( GuestBookEntry entry : guestbookEntries ) {
			out.println("<tr>");
			out.println("  <td>" + entry.getName() + "</td>");
			out.println("  <td>" + entry.getMessage() + "</td>");
			out.println("  <td>");
			out.println("       <a href=\"EditComment?id=" + entry.getId() + "\">Edit</a>");
			out.println("       | ");
			out.println("       <a href=\"DeleteComment?id=" + entry.getId() + "\">Delete</a>");
			out.println("  </td>");
			out.println("</tr>");

		}
		
		out.println("</table>");
		
		out.println("<a class=\"btn btn-primary\" href=\"AddComment\">Add a Comment</a>");
		
		out.println("</div>");
		out.println("</body>");
		out.println("</html>");		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String searchQuery = request.getParameter("searchQuery");
		String searchValue = request.getParameter("searchQuery");
		
		
		if(searchQuery.equals("")) {
			request.setAttribute(searchQuery, "searchQuery");
			doGet(request, response);
		}
		else if(searchQuery.equals(searchValue)){
			searchQuery = searchValue;
			request.setAttribute(searchQuery, "value");
			doGet(request, response);
			return;
		}
		else {
		doGet(request, response);
		}
	}

}