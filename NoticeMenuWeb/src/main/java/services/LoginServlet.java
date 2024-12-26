package pl.pols.lab.services;

import entities.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.*;

/**
 * Servlet responsible for handling user login.
 *
 * This servlet validates the username provided by the user, tracks the user's
 * visit count using cookies, and redirects the user to the notice display page
 * upon successful login. If the user does not exist in the database, a new user
 * is created and persisted.
 *
 * @author Michal Walus
 * @version 1.1
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/userlogin"})
public class LoginServlet extends HttpServlet {
    
    /**
     * The user currently using the system, retrieved or created during login.
     */
    private AppUser currentUser;
    
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
     * Processes both GET and POST requests for username validation.
     *
     * This method retrieves the username from the request, checks cookies to
     * track the user's visit count, ensures the user exists in the database,
     * and redirects to the notice display page. If an error occurs, an error
     * page is displayed.
     *
     * @param request the HTTP request containing the username parameter
     * @param response the HTTP response to be sent back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an input or output error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        String username = request.getParameter("username");
        Cookie[] cookies = request.getCookies();
        int cookieValue = 1;
        Cookie loginCookie = null;

        try (PrintWriter out = response.getWriter()) {
            if (username == null || username.isBlank()) {
                throw new MyThrownException("Username cannot be empty.");
            }
            
            ensureUserInDatabase(username);

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals(username)) {
                        cookieValue = Integer.parseInt(cookie.getValue()) + 1;
                        cookie.setMaxAge(0);
                        break;
                    }
                }
            }

            loginCookie = new Cookie(username, Integer.toString(cookieValue));
            loginCookie.setPath("/");
            loginCookie.setMaxAge(60 * 60); 
            response.addCookie(loginCookie);

            response.sendRedirect(request.getContextPath() + "/DisplayNoticeServlet");
        } catch (MyThrownException ex) {
            try (PrintWriter out = response.getWriter()) {
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
        }
    }

    /**
     * Ensures a user with the given username exists in the database.
     *
     * If the user does not exist, a new user is created and persisted. If the
     * user exists, it is retrieved and updated as the shared user instance.
     *
     * @param username the username of the user to ensure
     * @throws MyThrownException if an error occurs while ensuring the user
     * exists
     */
    private void ensureUserInDatabase(String username) throws MyThrownException {
        EntityManagerFactory emf = (EntityManagerFactory) context.getAttribute("emf");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            AppUser user;
            try {
                user = em.createQuery("SELECT u FROM AppUser u WHERE u.name = :name", AppUser.class)
                        .setParameter("name", username)
                        .getSingleResult();
                
                context.removeAttribute("sharedUser");
                context.setAttribute("sharedUser", user);
            } catch (NoResultException e) {
                user = new AppUser();
                user.setName(username);
                em.persist(user);
                context.removeAttribute("sharedUser");
                context.setAttribute("sharedUser", user);
            }
            em.getTransaction().commit();
            } catch (PersistenceException e) {
                em.getTransaction().rollback();
                throw new RuntimeException("Error ensuring user in database: " + e.getMessage());
            } finally {
                em.close();

            }
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
        return "LoginServlet validates the username, tracks user visits using cookies, and redirects to the notice display page.";
    }
}
