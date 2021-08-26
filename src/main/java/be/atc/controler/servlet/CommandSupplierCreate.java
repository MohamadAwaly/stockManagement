package be.atc.controler.servlet;

import be.atc.controler.connexion.EMF;
import be.atc.entities.*;
import be.atc.service.ProductService;
import be.atc.service.SupplierService;
import be.atc.service.UserService;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NamedQuery;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(name = "CommandSupplierCreate", value = "/CommandSupplierCreate")
public class CommandSupplierCreate extends HttpServlet {
    public static final String VUE = "/views/CommandSupplierCreate.jsp";
    public static final String VUEsupplierList = "/views/CommandSupplierList.jsp";
    private SupplierService supplierService = new SupplierService();
    private ProductService productService = new ProductService();
    private UserService userService = new UserService();
    private Logger logger = Logger.getLogger(SuppliersShowAll.class);
    private EntityManager em = EMF.getEM();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Object[]> lst_Product = productService.ShowIdAndNameProduct();
        List<Object[]> lst_Supplier = supplierService.suppliersShowAll();
        List<Object[]> lst_User = userService.showAllUsersIdAndName();
        request.setAttribute("products",lst_Product);
        request.setAttribute("suppliers",lst_Supplier);
        request.setAttribute("users",lst_User);
        request.getRequestDispatcher(VUE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int supplierId = 0;
        int userId = 0;
        int productId = 0;
        int quantity = 0;
        Date dateNow = new Date(System.currentTimeMillis()) ;
        EntityTransaction transaction = em.getTransaction();

        //CONTROL DATA supply from user
        boolean dataChecked = true;
        try{
            supplierId = Integer.parseInt(request.getParameter("Supplier"));
            userId = Integer.parseInt(request.getParameter("User"));
            productId = Integer.parseInt(request.getParameter("Product"));
            quantity = Integer.parseInt(request.getParameter("Quantity"));

        }catch (Exception e){
            dataChecked = false;
        }
        // DATA IS OK
        if (dataChecked){
            logger.log(Level.INFO,"Data is : "+ dataChecked);
            try{
                transaction.begin();
                SuppliersEntity suppliersEntity = em.find(SuppliersEntity.class,supplierId);
                UsersEntity usersEntity = em.find(UsersEntity.class,userId);
                ProductsEntity productsEntity = em.find(ProductsEntity.class, productId);

                //CREATION BATCH with quantity
                BatchsEntity batchsEntity = new BatchsEntity();
                batchsEntity.setProducts(productsEntity);
                batchsEntity.setQuantity(quantity);
                em.merge(batchsEntity);

                //CREATION CommandSupplier with date today
                CommandsuppliersEntity commandsuppliersEntity = new CommandsuppliersEntity();
                commandsuppliersEntity.setSuppliers(suppliersEntity);
                commandsuppliersEntity.setUsers(usersEntity);
                commandsuppliersEntity.setOrderDate(dateNow);
                em.merge(commandsuppliersEntity);

                //CREATION CommandsuppliersBatchsEntity
                CommandsuppliersBatchsEntity commandsuppliersBatchsEntity = new CommandsuppliersBatchsEntity();
                commandsuppliersBatchsEntity.setBatchs(batchsEntity);
                commandsuppliersBatchsEntity.setCommandsuppliers(commandsuppliersEntity);
                em.merge(commandsuppliersBatchsEntity);

                transaction.commit();
            }
            catch (Exception e){
                transaction.rollback();
                logger.log(Level.FATAL,"Servlet CommandSupplier Transaction EM Fatal Error");
            }finally {
                //em.close();
                logger.log(Level.INFO,"CommandSupplier TRY CATCH Transaction Finish");
            }
            response.sendRedirect(request.getContextPath()+"/CommandSupplierShowAll");
        }
        // DATA is KO :
        else{
            logger.log(Level.WARN,"Data is : "+ dataChecked);
            request.setAttribute("message","Certaines entrées sont incohérentes");
            request.getRequestDispatcher("/views/error.jsp").forward(request,response);
        }
    }
}