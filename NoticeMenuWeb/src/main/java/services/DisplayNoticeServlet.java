
package services;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import static java.lang.System.console;
import model.Notice;
import model.NoticeList;
import model.SingletonModel;
import model.Type;

/**
 *
 * @author micha
 */
@WebServlet(name = "DisplayNoticeServlet", urlPatterns = {"/DisplayNoticeServlet"})
public class DisplayNoticeServlet extends HttpServlet {

    
    private NoticeList notices;
    private String currentUserName;
    
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
            currentUserName = SingletonModel.getInstanceUser().getName();
            int rowIndex = 0;
            String visitCount = "1ewfwefd";
            
            Cookie[] cookies = request.getCookies();
            
            for(Cookie cookie : cookies){
                if(cookie.getName().equals(currentUserName)){
                    visitCount = cookie.getValue();
                }
            }

            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");
            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>Editable Table</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; margin: 0; padding: 20px; background-color: #f9f9f9; }");
            out.println("table { width: 100%; border-collapse: collapse; margin-bottom: 20px; }");
            out.println("table, th, td { border: 1px solid #ccc; }");
            out.println("th, td { padding: 10px; text-align: left; }");
            out.println("th { background-color: #f2f2f2; }");
            out.println(".form-container { margin-top: 20px; padding: 20px; background-color: #fff; border: 1px solid #ccc; border-radius: 5px; }");
            out.println(".form-container label { display: block; margin-bottom: 5px; font-weight: bold; }");
            out.println(".form-container input, .form-container select, .form-container button { width: 100%; padding: 10px; margin-bottom: 10px; border: 1px solid #ccc; border-radius: 5px; }");
            out.println(".form-container button { background-color: #007bff; color: white; cursor: pointer; }");
            out.println(".form-container button:hover { background-color: #0056b3; }");
            out.println(".action-button { padding: 5px 10px; font-size: 14px; margin: 0 5px; border: none; cursor: pointer; border-radius: 3px; }");
            out.println(".update-button { background-color: #28a745; color: white; }");
            out.println(".update-button:hover { background-color: #218838; }");
            out.println(".delete-button { background-color: #dc3545; color: white; }");
            out.println(".delete-button:hover { background-color: #c82333; }");
            out.println(".disabled-button { background-color: #cccccc; color: #666666; cursor: not-allowed; }");
            out.println(".leave-button { padding: 10px 20px; font-size: 16px; background-color: #007bff; color: white; border: none; border-radius: 5px; cursor: pointer; }");
            out.println(".leave-button:hover { background-color: #0056b3; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Notices Table</h1>");
            out.println("<h2>It's your " + visitCount + " visit on this site " + currentUserName + "!</h2>");
            out.println("<table>");
            out.println("<thead><tr><th>Title</th><th>Author</th><th>Type</th><th>Text</th><th>Action</th></tr></thead>");
            out.println("<tbody>");

            for (Notice notice : notices.getAll()) {
                out.println("<tr>");

                out.println("<form action=\"/NoticeMenuWeb/UpdateNoticeServlet\" method=\"POST\">");
                out.println("<td><input type=\"text\" name=\"title\" value=\"" + notice.getTitle() + "\" required></td>");
                out.println("<td>" + notice.getAuthor() + "</td>");
                out.println("<td><select name=\"type\" required>");
                for (Type currentType : Type.values()) {
                    if (currentType == notice.getType()) {
                        out.println("<option value=\"" + currentType.name() + "\" selected>" + currentType.name() + "</option>");
                    } else {
                        out.println("<option value=\"" + currentType.name() + "\">" + currentType.name() + "</option>");
                    }
                }
                out.println("</select></td>");
                out.println("<td><input type=\"text\" name=\"text\" value=\"" + notice.getText() + "\" required></td>");
                out.println("<td>");

                if (notice.getAuthor().equals(currentUserName) || currentUserName.equals("admin")) {
                    out.println("<input type=\"hidden\" name=\"rowIndex\" value=\"" + rowIndex + "\">");
                    out.println("<button type=\"submit\" class=\"action-button update-button\">Update</button>");
                    out.println("</form>");
                    out.println("<form action=\"/NoticeMenuWeb/DeleteNoticeServlet\" method=\"POST\" style=\"display:inline;\">");
                    out.println("<input type=\"hidden\" name=\"rowIndex\" value=\"" + rowIndex + "\">");
                    out.println("<button type=\"submit\" class=\"action-button delete-button\">Delete</button>");
                    out.println("</form>");
                } else {
                    
                    out.println("<input type=\"hidden\" name=\"rowIndex\" value=\"" + rowIndex + "\">");
                    out.println("<button type=\"button\" class=\"action-button update-button disabled-button\" disabled>Update</button>");
                    out.println("</form>");
                    out.println("<form action=\"/NoticeMenuWeb/DeleteNoticeServlet\" method=\"POST\" style=\"display:inline;\">");
                    out.println("<input type=\"hidden\" name=\"rowIndex\" value=\"" + rowIndex + "\">");
                    out.println("<button type=\"button\" class=\"action-button delete-button disabled-button\" disabled>Delete</button>");
                    out.println("</form>");
                    
                }


                out.println("</td>");
                out.println("</tr>");
                rowIndex++;
            }

            out.println("</tbody>");
            out.println("</table>");

            out.println("<div class=\"form-container\">");
            out.println("<form action=\"/NoticeMenuWeb/AddNoticeServlet\" method=\"POST\">");
            out.println("<label for=\"title\">Title</label>");
            out.println("<input type=\"text\" id=\"title\" name=\"title\" placeholder=\"Enter title\" required>");
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

            out.println("<div style=\"text-align:center; margin-top:20px;\">");
            out.println("<a href=\"/NoticeMenuWeb\"><button class=\"leave-button\">Leave</button></a>");
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
