package photos;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import models.PhotoAlbum;

/**
 * Servlet implementation class RemovePhoto
 */
@WebServlet("/photos/RemovePhoto")
public class RemovePhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String indexString = request.getParameter("photo");
		int index = (new Integer(indexString.trim())).intValue();
		PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(request.getServletContext());
		pa.removePhoto(index);
		RequestDispatcher rd = request.getRequestDispatcher("ViewAlbum");
		rd.forward(request, response);
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
