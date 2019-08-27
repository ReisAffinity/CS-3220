package Lab6;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.GameBean;

@javax.servlet.annotation.WebServlet({"/GameController"})
public class GameController extends HttpServlet
{
  private static final long serialVersionUID = 1L;
  
  public GameController() {}
  
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);
  }
  
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    HttpSession session = request.getSession();
    
    if (session.getAttribute("game") == null)
    {
      session.setAttribute("game", new GameBean());
    }
    

    request.getRequestDispatcher("/WEB-INF/Lab6/GameView.jsp").forward(request, response);
  }
  
  protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException
  {
    doGet(request, response);
  }
}
