

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import model.MyThrownException;
import model.NoticeList;
import model.Notice;
import model.Type;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import model.NoticeIdentity;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 * Class consisting of multiple NoticeList tests.
 *
 * @version 1.0
 * @author Michal Walus
 */

public class NoticeListTest {
    
    /**List of predefined notices*/
    private static NoticeList testList;
    
   /**
    * Sets up a test environment for the NoticeList class.
    * Initializes a list of notices with predefined data.
    */
    @BeforeEach
    public void setUpClass() {
        try {
            testList = new NoticeList();

            /**
             * List of predefined notices to be added to the NoticeList.
             */
            List<Notice> notices = new ArrayList<>();
            notices.add(new Notice("Initial Notice", "User1", Type.OTHER, "Initial content"));
            notices.add(new Notice("Second Notice", "User2", Type.RENTAL, "Second content"));
            notices.add(new Notice("Apple", "Alice123", Type.SALE, "Orchard"));
            notices.add(new Notice("Banana", "BobBuilder", Type.HIRE, "Tropical"));
            notices.add(new Notice("Cherry", "CharlieCoder", Type.SERVICE, "Delicious"));
            notices.add(new Notice("Date", "DaisyDesigner", Type.RENTAL, "Desert"));

            for (Notice notice : notices) {
                testList.addNotice(notice);
            }
        } catch (MyThrownException ex) {
            fail("Correct data caused exception: " + ex.getMessage());
        }
    }
    
    /**
     * Clears the list of notices after each test.
     *
     * Ensures that the NoticeList is empty, preventing test interference caused
     * by leftover data from previous tests.
     */
    @AfterEach
    public void clearList(){
        testList.getAll().clear();
    }
    
    /**
     * Provides valid Notice objects for testing.
     *
     * @return Stream of valid Notice objects.
     */
    static Stream<org.junit.jupiter.params.provider.Arguments> provideValidNotices() {
        try{
             return Stream.of(
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("Apple", "Author1", Type.RENTAL, "Fruit description")),
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("Banana", "Author2", Type.SALE, "Tropical fruit")),
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("Car", "Author3", Type.SERVICE, "Vehicle details")),
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("House", "Author4", Type.HIRE, "Property details")),
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("Book", "Author5", Type.SALE, "Literary item")),
                     org.junit.jupiter.params.provider.Arguments.of(new Notice("Chair", "Author6", Type.RENTAL, "Furniture"))

             );
         }
         catch(MyThrownException ex){
             fail("Correct data caused an exception");
             return null;
         }
    }
    

    /**
     * Parametrized test for adding valid Notice objects using the addNotice
     * method. Ensures that adding a valid notice does not throw an
     * exception.
     *
     * @param notice The valid Notice object to be added.
     */
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testAddNoticeWithValidData(Notice notice) {
        assertDoesNotThrow(() -> testList.addNotice(notice), "Exception should't occur with valid data");
    }
    
    
    /**
     * Parametrized test for setting the title to null and testing validation.
     *
     * @param notice The valid Notice object to be modified.
     */
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testAddNoticeWithNullTitle(Notice notice) {
        notice.setTitle(null);
        Exception ex = assertThrows(MyThrownException.class, () -> testList.addNotice(notice), "This should throw MyThrownException");
        assertEquals("Title cannot be empty or null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }

    /**
     * Parametrized test for setting the author to null and testing validation.
     *
     * @param notice The valid Notice object to be modified.
     */
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testAddNoticeWithNullAuthor(Notice notice) {
        notice.setIdentity(new NoticeIdentity(Type.OTHER, null));
        Exception ex = assertThrows(MyThrownException.class, () -> testList.addNotice(notice), "This should throw MyThrownException");
        assertEquals("Author cannot be empty or null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }

    /**
     * Parametrized test for setting the type to null and testing validation.
     *
     * @param notice The valid Notice object to be modified.
     */
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testAddNoticeWithNullType(Notice notice) {
        notice.setIdentity(new NoticeIdentity(null, "authorRandom"));
        Exception ex = assertThrows(MyThrownException.class, () -> testList.addNotice(notice), "This should throw MyThrownException");
        assertEquals("Type cannot be null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }

    /**
     * Parametrized test for setting the text to null and testing validation.
     *
     * @param notice The valid Notice object to be modified.
     */
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testAddNoticeWithNullText(Notice notice) {
        notice.setText(null);
        Exception ex = assertThrows(MyThrownException.class, () -> testList.addNotice(notice), "This should throw MyThrownException");
        assertEquals("Description cannot be empty or null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }
    
    /**
     * Test for adding a null Notice object. Ensures that passing a null Notice
     * throws MyThrownException.
     */
    @Test
    void testAddNoticeWithNullObject() {
        Exception ex = assertThrows(MyThrownException.class, () -> testList.addNotice(null), "This should throw MyThrownException");
        assertEquals("The notice object cannot be null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }
    
    
    @ParameterizedTest
    @ValueSource(ints = {0, 1, 2, 3, 4, 5})
    void testDeleteNoticeWithValidData(int i){
        Notice notice = testList.getAll().get(i);
        assertDoesNotThrow(() -> testList.deleteOne(notice), "Exception should't occur with correct data");
    }
    
    @ParameterizedTest
    @MethodSource("provideValidNotices")
    void testDeleteNoticeWithWrongData(Notice notice){
        Exception ex = assertThrows(MyThrownException.class, () -> testList.deleteOne(notice), "This should throw MyThrownException");
        assertEquals("The notice does not exist in the list.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }
    
    @Test
    void testDeleteNoticeWithNullData(){
        Exception ex = assertThrows(MyThrownException.class, () -> testList.deleteOne(null), "This should throw MyThrownException");
        assertEquals("The notice to be deleted cannot be null.", ex.getMessage(), "Incorrect exception message: " + ex.getMessage());
    }
    
    
    
}
