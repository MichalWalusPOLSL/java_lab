/**
 * The services package contains servlets responsible for handling HTTP requests
 * in the Notice Management Web Application.
 *
 * This package includes the following functionalities:
 * - User login and management of cookies for tracking user visits.
 * - Displaying a table of notices with options to edit or delete entries.
 * - Adding new notices to the system.
 * - Updating existing notices with modified details.
 * - Deleting notices based on user requests.
 *
 * The servlets interact with the model layer to manage notices and users,
 * ensuring a consistent application state. Each servlet is designed to
 * handle specific tasks like adding, updating, or deleting notices.
 * Responses are generated as HTML pages or as HTTP redirects to other views.
 *
 * The package relies on a shared application context for managing resources
 * like the database connection (EntityManagerFactory) and the current user.
 * This ensures all servlets work with the same data and maintain consistency.
 *
 * @author Michal Walus
 * @version 1.2
 */
package services;
