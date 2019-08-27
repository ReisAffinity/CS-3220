package test;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.ServletContext;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import models.PhotoAlbum;

@WebServlet(name = "/test/DisplayAlbumServlet", urlPatterns = { "/test/DisplayAlbumServlet" })
@MultipartConfig()
public class DisplayAlbumServlet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		handleRequest(request, response);
	}

	protected void handleRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ServletContext servletContext = request.getServletContext();
		PhotoAlbum pa = PhotoAlbum.getPhotoAlbum(servletContext);
		if (request.getContentType() != null && request.getContentType().startsWith("multipart/form-data")) {
			this.uploadPhoto(request, pa);
		}
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		try {
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Photo Viewer</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h3 align='center'>Photos</h3>");
			this.displayAlbum(pa, "", out);
			out.println("</body>");
			out.println("</html>");
		} finally {
			out.close();
		}
	}

	private void uploadPhoto(HttpServletRequest request, PhotoAlbum pa) throws IOException, ServletException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		String filename = null;
		for (Part p : request.getParts()) {
			this.copyBytes(p.getInputStream(), baos);
			filename = p.getSubmittedFileName();
		}
		if (!"".equals(filename)) {
			String photoName = filename.substring(0, filename.lastIndexOf("."));
			pa.addPhoto(photoName, baos.toByteArray());
		}
	}

	private void displayAlbum(PhotoAlbum pa, String label, PrintWriter out) {
		out.println("<h3 align='center'>" + label + "</h3>");
		out.println("<table align='center'>");
		for (int j = 0; j < pa.getPhotoCount(); j++) {
			out.println("<td>");
			out.println("<a href='./DisplayPhotoServlet?photo=" + j + "'>");
			out.println("<img src='./DisplayPhotoServlet?photo=" + j + "' alt='photo' height='120' width='150'> ");
			out.println("</a>");
			out.println("</td>");
		}
		out.println("<td bgcolor='#cccccc' width='120' height='120'>");
		out.println("<form align='left' action='DisplayAlbumServlet' method='post' enctype='multipart/form-data'>");
		out.println("<input value='Choose' name='myFile' type='file'accept='image/jpeg'><br>");
		out.println("<input value='Upload' type='submit\'><br>");
		out.println("</form>");
		out.println("</td>");
		out.println("</tr>");
		out.println("<tr>");
		for (int j = 0; j < pa.getPhotoCount(); j++) {
			out.println("<td align='center'>");
			out.println(pa.getPhotoName(j));
			out.println("</td>");
		}
		out.println("</tr>");
		out.println("<tr>");
		for (int j = 0; j < pa.getPhotoCount(); j++) {
			out.println("<td align='center'>");
			out.println("<a href='RemovePhotoServlet?photo=" + j + "'>remove</a>");
			out.println("</td>");
		}
		out.println("</tr>");
		out.println("</table>");
	}

	private void copyBytes(InputStream is, OutputStream os) throws IOException {
		int i;
		while ((i = is.read()) != -1) {
			os.write(i);
		}
		is.close();
		os.close();
	}
}
