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
 * Servlet responsible for updating existing notices. Handles user requests to
 * modify the title, type, or text of a specific notice.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@WebServlet(name = "UpdateNoticeServlet", urlPatterns = {"/UpdateNoticeServlet"})
public class UpdateNoticeServlet extends HttpServlet {

    /**
     * Processes both GET and POST requests for updating notices. Retrieves the
     * row index, title, type, and text from the request, validates them, and
     * updates the corresponding notice in the model. Redirects to the display
     * page upon success or displays an error message if validation fails.
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
        Type type = Type.valueOf(request.getParameter("type"));
        String text = request.getParameter("text");
        
        try (PrintWriter out = response.getWriter()) {
            
            if(title != null && type != null && text != null && row != null){
                int rowIndex = Integer.parseInt(row);
                Notice notice = SingletonModel.getInstanceNotice().getAll().get(rowIndex);
                notice.setText(text);
                notice.setTitle(title);
                notice.setIdentity(new NoticeIdentity(type, notice.getAuthor()));
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
