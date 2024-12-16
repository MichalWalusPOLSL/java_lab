package pl.pols.lab.services;

import entities.AppUser;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceException;
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
 * Servlet responsible for handling user login. Validates the username, tracks
 * user visit count using cookies, and redirects to the notice display page.
 * 
 * @author Michal Walus
 * @version 1.0
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/userlogin"})
public class LoginServlet extends HttpServlet {
    
    /**
     * The user currently using the system.
     */
    private User user;


    /**
     * Processes both GET and POST requests for username validation. Retrieves
     * the username from the request, checks cookies for the user's visit count,
     * updates or creates a new cookie, and sets the username in the shared
     * SingletonModel instance.
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
     * Ensures a user with the given username exists in the database. If the
     * user does not exist, it is created and persisted.
     *
     * @param username the username of the user
     */
    private void ensureUserInDatabase(String username) throws MyThrownException {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("my_persistence_unit");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            AppUser user;
            try {
                user = em.createQuery("SELECT u FROM AppUser u WHERE u.name = :name", AppUser.class)
                        .setParameter("name", username)
                        .getSingleResult();
            } catch (NoResultException e) {
                user = new AppUser();
                user.setName(username);
                em.persist(user);
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
