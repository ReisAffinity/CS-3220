package Lab6;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.GameBean;

@javax.servlet.annotation.WebServlet({"/PlayController"})
public class PlayController extends javax.servlet.http.HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  public PlayController() {}
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
  {
    try
    {
      int location = Integer.parseInt(request.getParameter("location"));
      
      GameBean game = (GameBean)request.getSession().getAttribute("game");
      game.action(location);
    }
    catch (Exception localException) {}
    
    response.sendRedirect("GameController");
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doGet(request, response);
  }
}
