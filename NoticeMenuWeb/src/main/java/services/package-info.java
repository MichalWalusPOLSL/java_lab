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
 * handle specific tasks like adding, updating, or deleting notices,
 * and they generate HTML responses or redirects accordingly.
 *
 * The package relies on the Singleton pattern for managing shared application data,
 * ensuring all servlets operate on the same instance of the data model.
 * 
 * @author Michal Walus
 * @version 1.0
 */
package services;
