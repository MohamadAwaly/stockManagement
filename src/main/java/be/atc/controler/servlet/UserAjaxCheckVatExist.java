package be.atc.controler.servlet;

import be.atc.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "UserAjaxCheckVatExist", value = "/UserAjaxCheckVatExist")
public class UserAjaxCheckVatExist extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserService userService = new UserService();
        String login = request.getParameter("vat");
//        logger.log(Level.INFO, "checkUserAjax: " + login);
        boolean checkLoginExist = userService.checkUserExist(login);
        if (checkLoginExist) {
//            response.getWriter().write("Error - user exist");
            response.getWriter().write("error");
        } else {
            response.getWriter().write("ok");
        }
    }
}
