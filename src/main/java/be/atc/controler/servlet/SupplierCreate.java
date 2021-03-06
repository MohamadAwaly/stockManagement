package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.entities.SuppliersEntity;
import be.atc.entities.UsersEntity;
import be.atc.service.SupplierService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;


@WebServlet(name = "supplierCreate", value = "/supplierCreate")
public class SupplierCreate extends HttpServlet {

    public static final String VUE = "/views/supplierCreate.jsp";
    private             SupplierService supplierService = new SupplierService();
    private             Logger logger = Logger.getLogger(SupplierCreate.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.getServletContext().getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sessionScoop = request.getSession();
        UsersEntity sessionUsersEntity = (UsersEntity) sessionScoop.getAttribute("SessionUserEntity");
        logger.log(Level.INFO , "USER ENTITY : "+sessionUsersEntity.getRoles().getRole());

        // Add commande if you are administrateur directeur or préparateur
        String sessionRole = sessionUsersEntity.getRoles().getRole().trim();
        if (sessionRole.equals("administrateur")
                || sessionRole.equals("directeur")
                || sessionRole.equals("préparateur")
        ){
            String supplierName = request.getParameter("supplierName").toString();
            int supplierExist = Integer.parseInt(supplierService.supplierExist(supplierName).toString());
            if (supplierExist == 1){
                request.setAttribute("message","Le founisseur inséré est déjà existant");
                request.getRequestDispatcher("/views/error.jsp").forward(request,response);
            }else{
                SuppliersEntity newSuppliersEntity = new SuppliersEntity();
                newSuppliersEntity.setName(supplierName);
                supplierService.supplierCreate(newSuppliersEntity);
                response.sendRedirect(request.getContextPath()+"/suppliersShowAll");
            }
        }
        else {
            // Throw Error Page : Not autorize user
            logger.log(Level.WARN,"Access denied for: "+ sessionUsersEntity.getLogin());
            request.setAttribute("message","Vous n'êtes pas autorisé à cette action");
            request.getRequestDispatcher("/views/error.jsp").forward(request,response);
        }


    }
}
