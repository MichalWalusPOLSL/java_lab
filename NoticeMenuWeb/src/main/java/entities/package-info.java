package entities;
/**
 * Contains JPA entity classes used for persistence in the application.
 * 
 * This package includes classes that represent the main domain objects
 * of the application, such as users, notices, and related value objects.
 * Each class is mapped to a relational database table or embedded
 * as part of a larger entity. The entities are designed to enforce
 * business rules and constraints at the persistence layer.
 * 
 * Key classes:
 * - AppUser: Represents an application user with a unique username.
 * - Notice: Represents a notice created by a user.
 * - NoticeIdentity: Represents a composite value object that defines
 *   the identity of a notice, including its type and author.
 */
