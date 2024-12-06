package model;


/**
 * Represents the identity of a notice, combining the type of the notice
 * and the author who created it.
 * 
 * @param type The type of the notice, representing its category (e.g., SALE, RENTAL, etc.).
 * @param author The username of the person who created the notice.
 * 
 * @author Michal Walus
 * @version 1.0
 */
public record NoticeIdentity(Type type, String author){

}
