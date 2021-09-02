package be.atc.controler.servlet;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet( name = "UserSignOut", value = "/UserSignOut" )
public class UserSignOut extends HttpServlet {
    public static final  String VUE_HOME = "/index.jsp";
    private static final Logger logger   = Logger.getLogger( UserSignOut.class );

    @Override
    protected void doGet( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {
        /* Recovery and destruction of the current session */
        HttpSession session = request.getSession();
        session.invalidate();
        /* Redirection to the home page ! */
        this.getServletContext().getRequestDispatcher( VUE_HOME ).forward( request, response );
    }

    @Override
    protected void doPost( HttpServletRequest request, HttpServletResponse response )
            throws ServletException, IOException {

    }
}
