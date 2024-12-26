package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import model.Type;
import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletContext;
import static java.lang.System.out;

import entities.AppUser;

/**
 * AddNoticeServlet is responsible for handling requests to add new notices to
 * the shared NoticeList. It processes both GET and POST requests, extracts the
 * notice details from the request, and adds the notice to the list.
 * 
 * @author Michal Walus
 * @version 1.1
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/AddNoticeServlet"})
public class AddNoticeServlet extends HttpServlet {

    /**
     * The current user interacting with the system. Retrieved from the shared
     * context attribute "sharedUser".
     */
    private AppUser currentUser;
    
    /**
     * The shared ServletContext for accessing application-wide resources.
     */
    private ServletContext context;
    
    /**
     * Initializes the servlet and retrieves the shared ServletContext.
     */
    @Override
    public void init() {
        context = getServletContext();
    }
    
    /**
     * Processes requests for both HTTP GET and POST methods.
     *
     * This method extracts notice details (title, type, and text) from the
     * request, validates them, and creates a new Notice object. If all required
     * data is provided, the notice is persisted to the database. If any data is
     * missing or invalid, an error message is displayed to the user.
     *
     * @param request the HTTP request containing notice details
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        currentUser = (AppUser) context.getAttribute("sharedUser");
        try (PrintWriter out = response.getWriter()) {
            
            String title = request.getParameter("title");
            Type type = Type.valueOf(request.getParameter("type"));
            String text = request.getParameter("text");

            if (title != null && currentUser != null && type != null && text != null) {
                
                NoticeIdentity iden = new NoticeIdentity(type, currentUser);

                persistObject(new Notice(title, iden, text));
                
                response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
            }
            else{
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + "Not enough data Error, please try again" + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }

            
        }
    }
    
    /**
     * Persists the given object to the database.
     *
     * This method uses the EntityManagerFactory from the ServletContext to
     * create an EntityManager, begin a transaction, and persist the object. If
     * an error occurs, the transaction is rolled back, and an error is logged.
     *
     * @param object the object to persist in the database
     */
    void persistObject(Object object) {
        
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            em.persist(object);
            em.getTransaction().commit();
        } catch (PersistenceException e) {
            e.printStackTrace(); 
            em.getTransaction().rollback();
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
        } finally {
            em.close();
        }
    }
    
    /**
     * Finds a user by their username in the database.
     *
     * This method queries the database for a user with the specified username.
     * If no user is found, it returns null.
     *
     * @param username the username to search for
     * @return the AppUser object representing the user, or null if not found
     */
    AppUser findUser(String username) {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        AppUser current = null;

        try {
            current = em.createQuery(
                    "SELECT u FROM AppUser u WHERE u.name = :username", AppUser.class)
                    .setParameter("username", username)
                    .getSingleResult();
        } catch (jakarta.persistence.NoResultException e) {
            out.println("<h1> " + e.getMessage() + "</h1>");
        } finally {
            em.close();
        }

        return current;
    }


    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Handles adding notices to the shared list.";
    }// </editor-fold>

}
