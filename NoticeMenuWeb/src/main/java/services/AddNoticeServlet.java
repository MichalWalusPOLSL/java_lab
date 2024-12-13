package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.out;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 * AddNoticeServlet is responsible for handling requests to add new notices to
 * the shared NoticeList. It processes both GET and POST requests, extracts the
 * notice details from the request, and adds the notice to the list.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/AddNoticeServlet"})
public class AddNoticeServlet extends HttpServlet {

    
    /**
     * The shared instance of NoticeList used to store notices.
     */
    private NoticeList notices;

    /**
     * The author of the notice, retrieved from the current user session.
     */
    private String author;
    
    /**
     * Initializes the servlet and retrieves the shared NoticeList instance.
     */
    @Override
    public void init() {
        notices = SingletonModel.getInstanceNotice();
    }
    
    /**
     * Processes requests for both HTTP GET and POST methods. Extracts notice
     * details (title, author, type, and text) from the request, and adds a new
     * notice to the shared list if all required parameters are provided. If an
     * error occurs during the addition, an error page is displayed.
     *
     * @param request the HTTP request containing notice details
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String title = request.getParameter("title");
            author = SingletonModel.getInstanceUser().getName();
            Type type = Type.valueOf(request.getParameter("type"));
            String text = request.getParameter("text");

            if (title != null && author != null && type != null && text != null) {
                
                try {
                    notices.addNotice(new Notice(title,author,type,text));
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
                    Logger.getLogger(AddNoticeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
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
