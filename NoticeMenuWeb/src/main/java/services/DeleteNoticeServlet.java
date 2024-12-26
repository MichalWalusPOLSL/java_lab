package services;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import entities.*;
import jakarta.servlet.ServletContext;
import model.MyThrownException;

/**
 * DeleteNoticeServlet is responsible for handling requests to delete a notice.
 * It identifies the notice to delete based on the provided row index parameter
 * in the request. If the notice exists, it is removed from the database. If the
 * notice cannot be found or another error occurs, an error message is displayed
 * to the user.
 * 
 * @author Michal Walus
 * @version 1.1
 */
@WebServlet(name = "DeleteNoticeServlet", urlPatterns = {"/DeleteNoticeServlet"})
public class DeleteNoticeServlet extends HttpServlet {
    
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
     * This method extracts the row index of the notice to delete from the
     * request parameters. It calls the deleteNoticeById method to remove the
     * specified notice from the database. If the row index is missing or an
     * error occurs during deletion, an error page is displayed.
     *
     * @param request the HTTP request containing the row index parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String rowIndexParam = request.getParameter("rowIndex"); 
        if (rowIndexParam != null) {
            try {
                Long rowIndex = Long.parseLong(rowIndexParam); 
                deleteNoticeById(rowIndex); 
                response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
            } catch (Exception ex) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + "Failed to delete notice" + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Error</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Error: " + "No rowIndex, please try again." + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }
    
    /**
     * Deletes a notice from the database by its ID.
     *
     * This method uses the EntityManagerFactory to create an EntityManager and
     * attempts to find and remove the notice with the specified ID. If the
     * notice cannot be found, or another error occurs, an exception is thrown.
     *
     * @param id the ID of the notice to delete
     * @throws MyThrownException if the notice cannot be found or an error
     * occurs during deletion
     */
    private void deleteNoticeById(Long id) throws MyThrownException {
        
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Notice noticeToDelete = em.find(Notice.class, id);
            if (noticeToDelete != null) {
                em.remove(noticeToDelete);
            } else {
                throw new MyThrownException("Notice with id " + id + " not found.");
            }

            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new MyThrownException("Failed to delete notice from database: " + e.getMessage());
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
        return "Handles requests for deleting a specific notice.";
    }// </editor-fold>

}
