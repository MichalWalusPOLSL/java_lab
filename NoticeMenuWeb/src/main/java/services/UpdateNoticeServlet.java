package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import model.Type;
import entities.*;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletContext;

/**
 * Servlet responsible for updating existing notices.
 *
 * This servlet handles user requests to modify the title, type, or text of a
 * specific notice. It processes both GET and POST requests, validates the input
 * parameters, and updates the corresponding notice in the database. Upon
 * successful update, the servlet redirects the user to the notice display page.
 * If validation fails or an error occurs during the update, an error message is
 * displayed.
 *
 * @author Michal Walus
 * @version 1.1
 */
@WebServlet(name = "UpdateNoticeServlet", urlPatterns = {"/UpdateNoticeServlet"})
public class UpdateNoticeServlet extends HttpServlet {

    /**
     * The shared ServletContext for accessing application-wide resources.
     */
    private ServletContext context;
    
    /**
     * Initializes the servlet by setting up the shared ServletContext.
     */
    @Override
    public void init() {
        context = getServletContext();
    }
    
    
    /**
     * Processes both GET and POST requests for updating notices.
     *
     * This method retrieves the row index, title, type, and text parameters
     * from the request, validates them, and updates the corresponding notice in
     * the database. If all fields are valid and the update is successful, the
     * user is redirected to the notice display page. Otherwise, an error
     * message is displayed.
     *
     * @param request the HTTP request containing parameters for updating the
     * notice
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String row = request.getParameter("rowIndex");
        String title = request.getParameter("title");
        String typeString = request.getParameter("type");
        String text = request.getParameter("text");

        try (PrintWriter out = response.getWriter()) {
            if (row != null && title != null && typeString != null && text != null) {
                Long id = Long.parseLong(row); 
                Type type = Type.valueOf(typeString);

                if (updateNoticeInDatabase(id, title, type, text)) {
                    response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
                } else {
                    out.println("<h1>Error: Failed to update the notice!</h1>");
                }
            } else {
                out.println("<h1>Error: All fields are required!</h1>");
            }
        }
    }

    /**
     * Updates a notice in the database based on its ID.
     *
     * This method retrieves the notice from the database using the specified
     * ID, updates its title, type, and text, and commits the changes. If the
     * notice is not found or an error occurs during the update, the transaction
     * is rolled back.
     *
     * @param id the ID of the notice to update
     * @param title the new title of the notice
     * @param type the new type of the notice
     * @param text the new text of the notice
     * @return true if the update was successful, false otherwise
     */
    boolean updateNoticeInDatabase(Long id, String title, Type type, String text) {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        try {
            Notice noticeToUpdate = em.find(Notice.class, id); 
            if (noticeToUpdate != null) {
                noticeToUpdate.setTitle(title); 
                noticeToUpdate.getIdentity().setType(type);
                noticeToUpdate.setText(text);
                em.merge(noticeToUpdate); 
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (PersistenceException e) {
            e.printStackTrace(); 
            em.getTransaction().rollback();
            out.println("<h1>Error: " + e.getMessage() + "</h1>");
            
            return false;
        } finally {
            em.close();
        }
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
        return "Handles the update of existing notices by modifying their title, type, and text.";
    }// </editor-fold>

}
