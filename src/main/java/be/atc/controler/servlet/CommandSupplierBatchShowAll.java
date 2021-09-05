package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CommandSupplierBatchShowAll", value = "/CommandSupplierBatchShowAll")
public class CommandSupplierBatchShowAll extends HttpServlet {
    public static final String        VUE = "/views/CommandSupplierBatchList.jsp";
    private EntityManager               em = EMF.getEM();
    private Logger                  logger = Logger.getLogger(CommandSupplierBatchShowAll.class);


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.log(Level.INFO, "doGET sevlet entered");
        //prepare list of the command number # :
        int pWhere = Integer.parseInt(request.getParameter("idCmdSupp"));
        Query query =  em.createNamedQuery("commandSuppliersBatchs.FindByIdCommand").setParameter("pWhere",pWhere);
        List<Object[]> commandSupplierBatch = query.getResultList();
        request.setAttribute("commandSuppliersBatch",commandSupplierBatch);
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
