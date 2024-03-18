import java.util.Date;
import java.util.HashSet;
//?----------------------------------------------------------------------------------------------------------------------

public class Borrow {

    private Date date;

    private String userId;
    private String documentId;
    private String libraryId;
    private boolean isStudent;
    private boolean isBook;

    public Borrow(Date date, String userId, String documentId, String libraryId) {
        this.libraryId = libraryId;
        this.date = date;
        this.userId = userId;
        this.documentId = documentId;
    }
    // ------------------------------------------------------------------------------------------------------------------
    // !------------------------------------------------------------------------------

    /**
     * Checks whether a user exists in either the student or staff set based on the
     * provided user ID.
     *
     * @param studentIds A HashSet containing student IDs to be checked.
     * @param staffIds   A HashSet containing staff IDs to be checked.
     * @return True if the user ID exists in either the student or staff set, false
     *         otherwise.
     */
    public boolean checkUser(HashSet<String> studentIds, HashSet<String> staffIds) {
        if (studentIds.contains(userId)) {

            this.isStudent = true;// set borrow as student`s borrow
            return true; // return true that means there is a user with that Id

        }

        else if (staffIds.contains(userId)) {

            this.isStudent = false;// set borrow as staff`s borrow
            return true; // return true that means there is a user with that Id

        }

        return false;// return false that means there is no user with that Id
    }

    // !------------------------------------------------------------------------------
    /**
     * Checks whether a document exists in either the book or thesis set based on
     * the provided document ID.
     *
     * @param bookIds   A HashSet containing book IDs to be checked.
     * @param thesisIds A HashSet containing thesis IDs to be checked.
     * @return True if the document ID exists in either the book or thesis set,
     *         false otherwise.
     */
    public boolean checkDoc(HashSet<String> bookIds, HashSet<String> thesisIds) {
        if (bookIds.contains(documentId)) {

            this.isBook = true;// set borrow as book`s borrow
            return true;// return true that means there is a document with that Id

        }

        else if (thesisIds.contains(documentId)) {

            this.isBook = false;// set borrow as thesis`s borrow
            return true;// return true that means there is a document with that Id
        }

        return false;// return false that means there is no document with that Id
    }
    // !------------------------------------------------------------------------------

    /**
     * Checks whether the user associated with this object is a student.
     *
     * @return True if the user is a student, false otherwise.
     */
    public boolean isStudent() {
        return isStudent;
    }
    // !------------------------------------------------------------------------------

    /**
     * Checks whether the document associated with this object is a book.
     *
     * @return True if the document is a book, false otherwise.
     */
    public boolean isBook() {
        return isBook;
    }
    // !------------------------------------------------------------------------------

    // ----------------------------------- Seters and Geters
    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDocumentId() {
        return this.documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public boolean isIsStudent() {
        return this.isStudent;
    }

    public boolean getIsStudent() {
        return this.isStudent;
    }

    public void setIsStudent(boolean isStudent) {
        this.isStudent = isStudent;
    }

    public boolean isIsBook() {
        return this.isBook;
    }

    public boolean getIsBook() {
        return this.isBook;
    }

    public void setIsBook(boolean isBook) {
        this.isBook = isBook;
    }
    // ----------------------------------- Seters and Geters

}
// ?----------------------------------------------------------------------------------------------------------------------
