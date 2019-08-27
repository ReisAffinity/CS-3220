package ToDoLab;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/finals/Admin")
public class ToDo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public void init( ServletConfig config ) throws ServletException
    {
        super.init( config );

        try
        {
            Class.forName( "com.mysql.jdbc.Driver" );
        }
        catch( ClassNotFoundException e )
        {
            throw new ServletException( e );
        }
    }
    
    protected void doGet( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
    		List<ToDoEntry> todos = new ArrayList<ToDoEntry>();
    		List<ToDoEntry> archived = new ArrayList<ToDoEntry>();
    		int remaining = 0;
            Connection c = null;
            try
            {
                String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu11";
                String username = "cs3220stu11";
                String password = "RtmdCCoV";

                c = DriverManager.getConnection( url, username, password );
                Statement stmt = c.createStatement();
                ResultSet rs = stmt.executeQuery( "select * from admin" );
                
                while( rs.next() )
                {
                	
                	ToDoEntry entry = new ToDoEntry(rs.getInt("id"),rs.getString("name"),rs.getString("quotation"));
                	{
                		remaining++;
                	}
                	todos.add(entry);
                	      	
                }
                
                Statement stmt2 = c.createStatement();
                ResultSet rs2 = stmt2.executeQuery( "select * from todo where state = 'archived'" );

                while( rs2.next() )
                {
                	ToDoEntry entry2 = new ToDoEntry(rs2.getInt("id"),rs2.getString("description"),rs2.getString("state"));
                	archived.add(entry2);
                	      	
                }
                
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
            finally
            {
                try
                {
                    if( c != null ) c.close();
                }
                catch( SQLException e )
                {
                    throw new ServletException( e );
                }
            }

            request.setAttribute( "todos", todos );
            request.setAttribute( "remaining", remaining );
            request.setAttribute( "archived", archived );
            request.getRequestDispatcher( "/WEB-INF/Todo.jsp" ).forward(
                request, response );
        }

        protected void doPost( HttpServletRequest request,
            HttpServletResponse response ) throws ServletException, IOException
        {
        	String description = request.getParameter( "description" );
        	String error = "";
            Connection c = null;
            try
            {
                String url = "jdbc:mysql://cs3.calstatela.edu/cs3220stu42";
                String username = "cs3220stu42";
                String password = "6O!A14Aa";

                
                if(description != null && description.trim().length() != 0){
                String sql = "insert into todo (id, description, state) values (?,?,?)";
                c = DriverManager.getConnection( url, username, password );
                PreparedStatement pstmt = c.prepareStatement( sql );
                pstmt.setInt(1,0);
                pstmt.setString( 2, description );
                pstmt.setString(3, "notdone");
                pstmt.executeUpdate();
                }else{
                	error = "Please enter a valid description";
                }
            }
            catch( SQLException e )
            {
                throw new ServletException( e );
            }
            finally
            {
                try
                {
                    if( c != null ) c.close();
                }
                catch( SQLException e )
                {
                    throw new ServletException( e );
                }
            }
            request.setAttribute( "error", error );
        	doGet( request, response );
        }

    }