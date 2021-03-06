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
import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.ClientInfoStatus;
import java.sql.Date;
import java.util.ArrayList;
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
        // Prepare list product, supplier for JSP to create a command
        List<Object[]> lst_Product = productService.ShowIdAndNameProduct();
        List<Object[]> lst_Supplier = supplierService.suppliersShowAll();
        request.setAttribute("products",lst_Product);
        request.setAttribute("suppliers",lst_Supplier);
        request.getRequestDispatcher(VUE).forward(request,response);
        HttpSession sessionScoop = request.getSession();
        UsersEntity usersEntity = (UsersEntity) sessionScoop.getAttribute("SessionUserEntity");
        logger.log(Level.INFO , "USER ENTITY : "+usersEntity.getRoles().getRole() );
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        /**
         * Check IF User session is administrateur directeur or pr??parateur Before Traitment
         * ELSE Access denied and go to Error page
         */
        HttpSession sessionScoop = request.getSession();
        UsersEntity sessionUsersEntity = (UsersEntity) sessionScoop.getAttribute("SessionUserEntity");
        logger.log(Level.INFO , "USER ENTITY : "+sessionUsersEntity.getRoles().getRole() );
        String sessionRole = sessionUsersEntity.getRoles().getRole().trim();
        if (sessionRole.equals("administrateur")
                || sessionRole.equals("directeur")
                || sessionRole.equals("pr??parateur")){


            //Insert a new commande Supplier
            int supplierId = 0;
            int userId = 0;
            int nbRowProduct = 0;
            Date dateNow = new Date(System.currentTimeMillis()) ;
            EntityTransaction transaction = em.getTransaction();

            // Check IF Data from user,
            // IF no correct = go to page Error
            List<Integer> lst_ProductId = new ArrayList<>();
            List<Integer> lst_ProductQty = new ArrayList<>();
            boolean dataChecked = true;
            try{
                nbRowProduct = Integer.parseInt(request.getParameter("nbRow"));
                supplierId = Integer.parseInt(request.getParameter("Supplier"));
                userId = Integer.parseInt(request.getParameter("User"));
                // If the user add 1 product
                if (nbRowProduct == 1){
                    lst_ProductId.add(Integer.parseInt(request.getParameter("Product")));
                    lst_ProductQty.add(Integer.parseInt(request.getParameter("Quantity")));
                }
                // If the user are more product
                else{
                    lst_ProductId.add(Integer.parseInt(request.getParameter("Product")));
                    lst_ProductQty.add(Integer.parseInt(request.getParameter("Quantity")));
                    //for start to "Product2" and "Product3","Product4",...
                    for (int i = 2; i <= nbRowProduct; i++){
                        lst_ProductId.add(Integer.parseInt(request.getParameter("Product"+i)));
                        lst_ProductQty.add(Integer.parseInt(request.getParameter("Quantity"+i)));
                    }
                }

            }catch (Exception e){
                dataChecked = false;
            }
            // DATA IS OK
            if (dataChecked){
                logger.log(Level.INFO,"Data is : "+ dataChecked);
                try {
                    transaction.begin();

                    SuppliersEntity suppliersEntity = em.find(SuppliersEntity.class, supplierId);
                    UsersEntity usersEntity = em.find(UsersEntity.class, userId);

                    //Commande Fournisseur
                    CommandsuppliersEntity commandsuppliersEntity = new CommandsuppliersEntity();
                    commandsuppliersEntity.setSuppliers(suppliersEntity);
                    commandsuppliersEntity.setUsers(usersEntity);
                    commandsuppliersEntity.setOrderDate(dateNow);

                    // Liste des batches/produits de la commande ?? cr??er
                    for (int i = 0 ;i < lst_ProductId.size();i++)
                    {
                        //CREATION  PRODUIT et son BATCH
                        ProductsEntity productsEntity = em.find(ProductsEntity.class,lst_ProductId.get(i));
                        BatchsEntity batchsEntity = new BatchsEntity();
                        batchsEntity.setQuantity(lst_ProductQty.get(i));
                        batchsEntity.setProducts(productsEntity);
                        em.merge(productsEntity);
                        em.merge(batchsEntity);

                        CommandsuppliersBatchsEntity commandsuppliersBatchsEntity = new CommandsuppliersBatchsEntity();
                        commandsuppliersBatchsEntity.setCommandsuppliers(commandsuppliersEntity);
                        commandsuppliersBatchsEntity.setBatchs(batchsEntity);
                        em.merge(commandsuppliersBatchsEntity);
                    }
                    em.merge(commandsuppliersEntity);
                    transaction.commit();
                }
                catch (Exception e){
                    transaction.rollback();
                    logger.log(Level.FATAL,"Servlet CommandSupplier Transaction EM Fatal Error");
                }finally {
//                    em.close();
                    logger.log(Level.INFO,"CommandSupplier TRY CATCH Transaction Finish");
                }
                response.sendRedirect(request.getContextPath()+"/CommandSupplierShowAll");
            }
            // DATA is KO! :
            else{
                //Throw Error page : Data issues
                logger.log(Level.WARN,"Data is : "+ dataChecked);
                request.setAttribute("message","Certaines entr??es sont incoh??rentes");
                request.getRequestDispatcher("/views/error.jsp").forward(request,response);
            }
        }
        else {
            // Throw Error Page : Not autorize user
            logger.log(Level.WARN,"Access denied for: "+ sessionUsersEntity.getLogin());
            request.setAttribute("message","Vous n'??tes pas autoris?? ?? cette action");
            request.getRequestDispatcher("/views/error.jsp").forward(request,response);
        }
    }
}
