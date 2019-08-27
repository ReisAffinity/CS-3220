package Lab6;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@javax.servlet.annotation.WebServlet({"/NewGameController"})
public class NewGameController extends javax.servlet.http.HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  public NewGameController() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException
  {
    HttpSession session = request.getSession();
    
    if (session.getAttribute("game") != null)
    {
      session.removeAttribute("game");
    }
    

    response.sendRedirect("GameController");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, java.io.IOException
  {
    doGet(request, response);
  }
}
