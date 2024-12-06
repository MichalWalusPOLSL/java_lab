/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.*;

/**
 *
 * @author micha
 */
@WebServlet(name = "AddNoticeServlet", urlPatterns = {"/AddNoticeServlet"})
public class AddNoticeServlet extends HttpServlet {

    
    private NoticeList notices;
    
    @Override
    public void init() {
        notices = SingletonModel.getInstanceNotice();
    }
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            String title = request.getParameter("title");
            String author = SingletonModel.getInstanceUser().getName();
            Type type = Type.valueOf(request.getParameter("type"));
            String text = request.getParameter("text");

            if (title != null && author != null && type != null && text != null) {
                
                try {
                    notices.addNotice(new Notice(title,author,type,text));
                } catch (MyThrownException ex) {
                    Logger.getLogger(AddNoticeServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Simple Table</title>");
            out.println("<link rel=\"stylesheet\" href=\"styles.css\">");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Table Example</h1>");
            out.println("<table>");
            out.println("<thead><tr><th>Title</th><th>Author</th><th>Type</th><th>Text</th></tr></thead>");
            out.println("<tbody>");

            for (Notice notice : notices.getAll()) {
                out.println("<tr>");
                out.println("<td>" + notice.getTitle() + "</td>");
                out.println("<td>" + notice.getAuthor() + "</td>");
                out.println("<td>" + notice.getType().name() + "</td>");
                out.println("<td>" + notice.getText() + "</td>");
                out.println("</tr>");
            }

            out.println("</tbody>");
            out.println("</table>");

            out.println("<div class=\"form-container\">");
            out.println("<form action=\"/addNotice\" method=\"POST\">");
            out.println("<label for=\"title\">Title</label>");
            out.println("<input type=\"text\" id=\"title\" name=\"title\" placeholder=\"Enter title\" required>");
            out.println("<label for=\"author\">Author</label>");
            out.println("<input type=\"text\" id=\"author\" name=\"author\" placeholder=\"Enter author\" required>");
            out.println("<label for=\"type\">Type</label>");
            out.println("<select id=\"type\" name=\"type\" required>");
            out.println("<option value=\"\">Select type</option>");
            out.println("<option value=\"SALE\">SALE</option>");
            out.println("<option value=\"RENTAL\">RENTAL</option>");
            out.println("<option value=\"SERVICE\">SERVICE</option>");
            out.println("<option value=\"HIRE\">HIRE</option>");
            out.println("<option value=\"OTHER\">OTHER</option>");
            out.println("</select>");
            out.println("<label for=\"text\">Text</label>");
            out.println("<input type=\"text\" id=\"text\" name=\"text\" placeholder=\"Enter text\" required>");
            out.println("<button type=\"submit\">Add</button>");
            out.println("</form>");
            out.println("</div>");
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
        return "Short description";
    }// </editor-fold>

}
