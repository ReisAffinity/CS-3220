package test;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PhotoAlbum;

/**
 * Servlet implementation class uploadPhoto
 */
@WebServlet(name = "/test/RemovePhotoServlet", urlPatterns = { "/test/RemovePhotoServlet" })
public class RemovePhotoServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String indexString = request.getParameter("photo");
		int index = (new Integer(indexString.trim())).intValue();
		PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(request.getServletContext());
		pa.removePhoto(index);
		RequestDispatcher rd = request.getRequestDispatcher("DisplayAlbumServlet");
		rd.forward(request, response);
		
	}
}

