package be.atc.controler.servlet;

import be.atc.controler.mail.Mail;
import be.atc.controler.mail.MailSender;
import be.atc.entities.UsersEntity;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.mindrot.jbcrypt.BCrypt;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
    public static final String VUE = "/UsersShowAll";
    public static final String VUE_LOGIN = "/views/login.jsp";
    public static final  String       VUE_HOME  = "/index.jsp";

    private static final Logger logger = Logger.getLogger(Login.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String login = request.getParameter("login-user");
        String password = request.getParameter("password");
        UserService userService = new UserService();
        UsersEntity user = new UsersEntity();
        String error = "";
        boolean checkpw = false;
        String sessionOK = "OK";

        try {
            user = userService.checkLogin(login);
            checkpw = BCrypt.checkpw(password, user.getPassword());
            if (!checkpw) {
                error = "Erreur! Username/password incorrect ";
            }
        } catch (NullPointerException e) {
            error = "Erreur! Username/password incorrect ";
        }
        if (checkpw) {
            session.setAttribute("SessionUserEntity", user);
            session.setAttribute("SessionUser", login);
            session.setAttribute("sessionOK", sessionOK);
            if (user.getRoles().getRole().trim().equals("administrateur")) {
                response.sendRedirect(request.getContextPath() + VUE);
            } else {
                this.getServletContext().getRequestDispatcher(VUE_HOME).forward(request, response);
            }
        } else {
            session.setAttribute("SessionUser", null);
            request.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher(VUE_LOGIN).forward(request, response);
        }
    }
}
