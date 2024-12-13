package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import model.*;

/**
 * DeleteNoticeServlet is responsible for handling requests to delete a notice
 * from the shared NoticeList. The servlet identifies the notice to delete based
 * on the provided row index parameter.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@WebServlet(name = "DeleteNoticeServlet", urlPatterns = {"/DeleteNoticeServlet"})
public class DeleteNoticeServlet extends HttpServlet {
    
    /**
     * The shared instance of NoticeList used to store notices.
     */
    private NoticeList notices;

    /**
     * Initializes the servlet and retrieves the shared NoticeList instance.
     */
    @Override
    public void init() {
        notices = SingletonModel.getInstanceNotice();
    }
    
    
    /**
     * Processes requests for both HTTP GET and POST methods. Extracts the row
     * index of the notice to delete from the request and removes the
     * corresponding notice from the shared list. If an error occurs, an error
     * page is displayed.
     *
     * @param request the HTTP request containing the row index parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        String rowString = request.getParameter("rowIndex");
        if(rowString != null){
            int rowIndex = Integer.parseInt(rowString);

            try {
                notices.deleteOne(notices.getAll().get(rowIndex));
                response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
            } catch (MyThrownException ex) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + ex.getMessage() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }
           
        }
        else {
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
