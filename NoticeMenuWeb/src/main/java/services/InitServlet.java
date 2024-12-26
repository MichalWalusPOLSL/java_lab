package services;

import entities.AppUser;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;

/**
 * Servlet responsible for initializing shared application resources.
 * 
 * This servlet sets up the application-wide EntityManagerFactory for JPA
 * and a shared AppUser instance. These resources are stored in the
 * ServletContext and made available to other servlets in the application.
 * 
 * The persistence unit name is retrieved from the web application's context
 * parameters, and the servlet ensures that it is correctly configured. If
 * the persistence unit name is not set, the servlet throws an exception
 * during initialization.
 * 
 * @author Michal Walus
 * @version 1.0
 */
public class InitServlet extends HttpServlet {
    
    /**
     * The EntityManagerFactory used for managing database connections. This
     * factory is shared across the application and is stored in the
     * ServletContext.
     */
    private EntityManagerFactory emf;
    
    /**
     * The shared AppUser instance for application-wide access. This object is
     * used to represent the current user.
     */
    private AppUser sharedUser;
    
    /**
     * Initializes the servlet by setting up shared application resources.
     *
     * This method retrieves the persistence unit name from the web
     * application's context parameters and initializes the EntityManagerFactory
     * for JPA. It also creates a shared AppUser instance for global use. Both
     * resources are stored in the ServletContext for access by other servlets.
     *
     * @throws ServletException if the persistence unit name is not set or
     * initialization fails
     */
    @Override
    public void init() throws ServletException {
        ServletContext context = getServletContext();

        String persistenceUnitName = context.getInitParameter("persistenceUnitName");
        if (persistenceUnitName == null || persistenceUnitName.isBlank()) {
            throw new ServletException("Persistence unit name is not set in web.xml");
        }

        emf = Persistence.createEntityManagerFactory(persistenceUnitName);

        sharedUser = new AppUser();

        context.setAttribute("emf", emf);
        context.setAttribute("sharedUser", sharedUser);

        context.log("InitServlet successfully initialized with persistence unit: " + persistenceUnitName);
    }
    
    /**
     * Releases resources during servlet destruction.
     *
     * This method closes the EntityManagerFactory if it is open, ensuring that
     * all database connections are properly released.
     */
    @Override
    public void destroy() {
        if (emf != null && emf.isOpen()) {
            emf.close();
        }
    }
}
