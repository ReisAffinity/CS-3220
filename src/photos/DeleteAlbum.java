package photos;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PhotoInfo;

/**
 * Servlet implementation class DeleteAlbum
 */
@WebServlet("/photos/DeleteAlbum")
public class DeleteAlbum extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id of the entry to be deleted
		int id = Integer.parseInt( request.getParameter("id") );
		
		// Get a reference to the array list of entries
		ArrayList<PhotoInfo> albumsEntries = (ArrayList<PhotoInfo>) getServletContext().getAttribute("albumsEntries");
		
		// Remove the element at the specified index.
		for (PhotoInfo albumsEntry : albumsEntries) {
			if (albumsEntry.getId() == id ) {
				albumsEntries.remove(albumsEntry);
				break;
			}
		}
		
		// Redirect the User back to the main guestbook page
		response.sendRedirect("Albums");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
