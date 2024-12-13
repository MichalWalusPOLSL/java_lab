package pl.pols.lab.services;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import model.SingletonModel;
import model.*;

/**
 * Servlet responsible for validating the username and providing feedback to the user.
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/userlogin"})
public class LoginServlet extends HttpServlet {

    private User user;

    /**
     * Initializes the servlet and its dependencies.
     */
    @Override
    public void init() {
       
    }

    /**
     * Processes both GET and POST requests for username validation.
     *
     * @param request  the HTTP request containing the username parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        
        user = SingletonModel.getInstanceUser();
        String username = request.getParameter("username");
        Cookie[] cookies = request.getCookies();
        int cookieValue = -1;
        Cookie loginCookie;

        try{
            SingletonModel.getInstanceUser().setName(username);
            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(username)) {
                        cookieValue = Integer.parseInt(cookie.getValue());
                        cookie.setMaxAge(0);
                    }
                }
                if(cookieValue == -1){
                    loginCookie = new Cookie(username, "1");
                }
                else{
                    cookieValue++;
                    loginCookie = new Cookie(username, Integer.toString(cookieValue));
                }
                loginCookie.setPath("/");
                response.addCookie(loginCookie);
            }
            else{
                response.addCookie(new Cookie(username, "1"));
            }
                
        } catch (MyThrownException ex) {
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Login Error</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Error: " + ex.getMessage() + "</h1>");
                out.println("<a href=\"/NoticeMenuWeb/\">Back to Login Page</a>");
                out.println("</body>");
                out.println("</html>");
            }
        
        response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request  the HTTP request containing the username parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request  the HTTP request containing the username parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an input or output error occurs
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
        return "LoginServlet validates username and provides feedback.";
    }
}
