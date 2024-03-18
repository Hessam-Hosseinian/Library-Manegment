
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
//?----------------------------------------------------------------------------------------------------------------------

public class Library {

    private final String libraryId;
    private final String libraryName;
    private final String foundationYear;
    private final int deskNumber;
    private final String address;
    private final HashMap<String, Book> books;
    private final HashMap<String, Thesis> theses;
    private final HashMap<String, ArrayList<Borrow>> borrows;

    public Library(String libraryId, String libraryName, String foundationYear, int deskNumber, String address) {
        this.libraryId = libraryId;
        this.libraryName = libraryName;
        this.foundationYear = foundationYear;
        this.deskNumber = deskNumber;
        this.address = address;
        this.books = new HashMap<>();
        this.theses = new HashMap<>();
        this.borrows = new HashMap<>();

    }
    // !------------------------------------------------------------------------------BORROW

    /**
     * Processes a borrow operation for a user based on the provided Borrow object
     * and the user's current borrow count.
     * 
     * @param borrow     The Borrow object representing the item to be borrowed.
     * @param userBorrow The current number of items borrowed by the user.
     * @return true if the borrow operation is successful, false
     *         otherwise.
     */
    public boolean borrow(Borrow borrow, int userBorrow) {
        if (borrow.isStudent()) {
            if (userBorrow < 3) {
                if (isAllowed(borrow)) {
                    return true;
                }
            }
        } else {
            if (userBorrow < 5) {
                if (isAllowed(borrow)) {
                    return true;
                }
            }
        }
        return false;
    }
    // !------------------------------------------------------------------------------COUNT-BORROWS

    /**
     * Counts the number of items currently borrowed by a user.
     * 
     * @param userId The unique identifier of the user.
     * @return The number of items currently borrowed by the user.
     */
    public int countBorrows(String userId) {
        int count = 0;
        for (ArrayList<Borrow> docBorrows : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : docBorrows) {
                if (borrow.getUserId().equals(userId)) {
                    count++;
                }
            }
        }
        return count;
    }

    // !------------------------------------------------------------------------------COUNT-DOC
    /**
     * Counts the number of borrow records associated with a specific document.
     *
     * @param docId The ID of the document for which borrow records are being
     *              counted.
     * @return The number of borrow records associated with the specified document
     *         ID.
     */
    public int countDocs(String docId) {
        ArrayList<Borrow> myBorrow = borrows.get(docId);
        if (myBorrow == null) {
            return 0;
        }
        return myBorrow.size();
    }
    // !------------------------------------------------------------------------------IS-ALLOWED

    /**
     * Checks if borrowing the specified document is allowed.
     * This method determines if the document can be borrowed based on the document
     * type and availability.
     * If borrowing is allowed, the borrow record is added to the borrows
     * collection.
     *
     * @param borrow The Borrow object representing the document being borrowed.
     * @return True if borrowing is allowed and the borrow record is successfully
     *         added, false otherwise.
     */
    public boolean isAllowed(Borrow borrow) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getDocumentId());
        if (borrows1 == null) {
            borrows1 = new ArrayList<>();

        }
        if (borrow.isBook()) {
            if (countDocs(borrow.getDocumentId()) < books.get(borrow.getDocumentId()).getCopyNumber()) {
                borrows1.add(borrow);
                borrows.put(borrow.getDocumentId(), borrows1);
                return true;
            }
            return false;
        }
        if (countDocs(borrow.getDocumentId()) == 0) {
            borrows1.add(borrow);
            borrows.put(borrow.getDocumentId(), borrows1);
            return true;
        }
        return false;
    }

    // !------------------------------------------------------------------------------CHECK-USER-BORROWES

    /**
     * Checks if a user has any borrow records and returns the most recent borrow
     * record.
     *
     * @param userId The ID of the user whose borrow records are being checked.
     * @return The most recent Borrow object associated with the specified user, or
     *         null if no borrow record is found.
     */
    public Borrow checkUserBorrows(String userId) {
        Borrow borrwohelper = null;
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : borrows1) {
                if (borrow.getUserId().equals(userId)) {
                    if (borrwohelper == null) {
                        borrwohelper = borrow;
                    } else if (borrwohelper.getDate().getTime() < borrow.getDate().getTime()) {
                        borrwohelper = borrow;
                    }
                }
            }
        }
        return borrwohelper;
    }

    // !------------------------------------------------------------------------------CHECK-USER-BORROWES-OVERWITED
    /**
     * Checks if a user has borrowed a specific document and returns the most recent
     * borrow record.
     *
     * @param userId The ID of the user whose borrow records are being checked.
     * @param docId  The ID of the document for which borrow records are being
     *               checked.
     * @return The most recent Borrow object associated with the specified user and
     *         document, or null if no borrow record is found.
     */
    public Borrow checkUserBorrows(String userId, String docId) {
        Borrow borrwohelper = null;
        ArrayList<Borrow> hold = borrows.get(docId);
        if (hold == null) {

            return null;
        }
        for (Borrow borrow : hold) {
            if (borrow.getUserId().equals(userId)) {
                if (borrwohelper == null) {
                    borrwohelper = borrow;
                } else if (borrwohelper.getDate().getTime() < borrow.getDate().getTime()) {
                    borrwohelper = borrow;
                }

            }
        }
        return borrwohelper;
    }

    // !------------------------------------------------------------------------------CHECK-DEBT
    /**
     * Checks for any late return fees associated with a borrowed document.
     * This method calculates the difference in hours between the borrow date and
     * the specified return date,
     * and based on the type of borrower and document, calculates any late return
     * fees.
     *
     * @param borrow The Borrow object representing the borrowed document.
     * @param date   The date when the document is being returned.
     * @return The late return fee, if applicable, otherwise 0.
     */
    public int checkDebt(Borrow borrow, java.util.Date date) {
        long firstMin = borrow.getDate().getTime() / 3600000; // getTime return time as millisecond
        long secondMin = date.getTime() / 3600000; // getTime return time as millisecond
        long periodTime = secondMin - firstMin;
        if (borrow.isStudent()) {
            if (borrow.isBook()) {
                if (periodTime < (10 * 24)) {
                    return 0;
                }
                return (int) ((periodTime - (10 * 24)) * 50); // BOOK AND STUDENT
            }
            if (periodTime < (7 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (7 * 24)) * 50); // THESIS AND STUDENT
        }
        if (borrow.isBook()) {
            if (periodTime < (14 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (14 * 24)) * 100); // BOOK AND STAFF
        }
        if (periodTime < (10 * 24)) {
            return 0;
        }
        return (int) ((periodTime - (10 * 24)) * 100);// THESIS AND STAFF
    }
    // !------------------------------------------------------------------------------RETURNING

    /**
     * Processes the returning of a borrowed document and calculates any late return
     * fees if applicable.
     * This method removes the specified borrow from the borrows list associated
     * with the document ID,
     * and calculates any late return fees by calling the checkDebt method.
     *
     * @param borrow The Borrow object representing the borrowed document to be
     *               returned.
     * @param date   The time at which the document is being returned.
     * @return The late return fee, if applicable, otherwise 0.
     */
    public int returning(Borrow borrow, java.util.Date date) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getDocumentId());
        int debt = checkDebt(borrow, date);
        borrows1.remove(borrow);
        return debt;
    }

    // !------------------------------------------------------------------------------COUNT-BORROWED-BOOKS-BY-ID

    /**
     * Counts the number of times a specific book has been borrowed.
     * This method iterates through all borrows and checks if each borrowed document
     * matches the specified book ID.
     *
     * @param bookId The ID of the book to count borrows for.
     * @return The number of times the specified book has been borrowed.
     */
    public int countBorrowedBooksById(String bookId) {
        int count = 0;

        if (books.containsKey(bookId)) {

            for (ArrayList<Borrow> borrowsList : borrows.values()) {
                for (Borrow borrow : borrowsList) {

                    if (borrow.isBook() && borrow.getDocumentId().equals(bookId)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // !------------------------------------------------------------------------------COUNT-BORROWED-THESES-BY-ID
    /**
     * Counts the number of times a specific thesis has been borrowed.
     * This method iterates through all borrows and checks if each borrowed document
     * matches the specified thesis ID.
     *
     * @param thesisId The ID of the thesis to count borrows for.
     * @return The number of times the specified thesis has been borrowed.
     */
    public int countBorrowedThesesById(String thesisId) {
        int count = 0;

        if (books.containsKey(thesisId)) {

            for (ArrayList<Borrow> borrowsList : borrows.values()) {
                for (Borrow borrow : borrowsList) {

                    if (!borrow.isBook() && borrow.getDocumentId().equals(thesisId)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    // !------------------------------------------------------------------------------SEARCH

    public HashSet<String> search(String key) {
        HashSet<String> output = new HashSet<>();
        for (Book book : books.values()) {

            if (book.getTitle().toLowerCase().contains(key.toLowerCase())) {
                output.add(book.getBookId());
            }
            if (book.getAuthor().toLowerCase().contains(key.toLowerCase())) {
                output.add(book.getBookId());
            }
            if (book.getPublisher().toLowerCase().contains(key.toLowerCase())) {
                output.add(book.getBookId());
            }

        }
        for (Thesis thesis : theses.values()) {

            if (thesis.getTitle().toLowerCase().contains(key.toLowerCase())) {
                output.add(thesis.getThesisID());
            }
            if (thesis.getStudentName().toLowerCase().contains(key.toLowerCase())) {
                output.add(thesis.getThesisID());
            }
            if (thesis.getProfessorName().toLowerCase().contains(key.toLowerCase())) {
                output.add(thesis.getThesisID());
            }

        }
        return output;
    }
    // !------------------------------------------------------------------------------COUNT-BOOKS-IN-CATEGORY

    /**
     * Counts the number of books in the specified category in the library.
     * 
     * @param categoryId The unique identifier of the category.
     * @return The number of books in the specified category.
     */
    public int countBooksInCategory(String categoryId) {
        int count = 0;
        for (Book book : books.values()) {
            if (book.getCategoryId().equals(categoryId)) {
                count += book.getCopyNumber();
            }
        }
        return count;
    }
    // !------------------------------------------------------------------------------COUNT-THESES-IN-CATEGORY

    /**
     * Counts the number of theses in the specified category in the library.
     * 
     * @param categoryId The unique identifier of the category.
     * @return The number of theses in the specified category.
     */
    public int countThesesInCategory(String categoryId) {
        int count = 0;
        for (Thesis thesis : theses.values()) {
            if (thesis.getCategoryId().equals(categoryId)) {
                count++;
            }
        }
        return count;
    }

    // !------------------------------------------------------------------------------COUNT-BORROWED-BOOKS-IN-CATEGORY
    /**
     * Counts the number of borrowed books in the specified category.
     * This method iterates through all borrows and checks if each borrowed document
     * is a book
     * and belongs to the specified category.
     *
     * @param categoryId The ID of the category to count borrowed books from.
     * @return The number of borrowed books in the specified category.
     */
    public int countBorrowedBooksInCategory(String categoryId) {
        int count = 0;

        for (ArrayList<Borrow> borrowsList : borrows.values()) {
            for (Borrow borrow : borrowsList) {

                if (borrow.isBook() && books.containsKey(borrow.getDocumentId())) {
                    Book borrowedBook = books.get(borrow.getDocumentId());
                    if (borrowedBook.getCategoryId().equals(categoryId)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    // !------------------------------------------------------------------------------COUNT-BORROWED-THESES-IN-CATEGORY

    /**
     * Counts the number of borrowed theses in the specified category.
     * This method iterates through all borrows and checks if each borrowed document
     * is a thesis
     * and belongs to the specified category.
     *
     * @param categoryId The ID of the category to count borrowed theses from.
     * @return The number of borrowed theses in the specified category.
     */
    public int countBorrowedThesesInCategory(String categoryId) {
        int count = 0;

        for (ArrayList<Borrow> borrowsList : borrows.values()) {
            for (Borrow borrow : borrowsList) {

                if (!borrow.isBook() && theses.containsKey(borrow.getDocumentId())) {
                    Thesis borrowedThesis = theses.get(borrow.getDocumentId());
                    if (borrowedThesis.getCategoryId().equals(categoryId)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // !------------------------------------------------------------------------------LIBRARY-REPORT
    /**
     * Generates a report summarizing the library's current state.
     * This method calculates the total number of books and theses in the library,
     * as well as the number of borrowed books and theses.
     *
     * @return A String containing the following information separated by spaces:
     *         - Total number of books
     *         - Total number of theses
     *         - Number of borrowed books
     *         - Number of borrowed theses
     */
    public String libraryReport() {
        int allBookNum = 0;
        int allThesisNum = theses.size(); // becuse all theses have one copy
        int borrowedBoolNum = 0;
        int borrowedThesisNum = 0;
        for (Book book : books.values()) {
            allBookNum += book.getCopyNumber(); // count the all books
        }
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(borrows.values())) { // count the borrowed books theses
            for (Borrow borrow : borrows1) {
                if (borrow.isBook()) {
                    borrowedBoolNum++;
                }

                else {
                    borrowedThesisNum++;
                }
            }
        }
        return allBookNum + " " + allThesisNum + " " + borrowedBoolNum + " "
                + borrowedThesisNum;
    }
    // !------------------------------------------------------------------------------REPORT-PASSED-DEADLINE

    /**
     * Generates a report of documents that have passed their deadline based on the
     * provided date.
     * This method iterates through all borrows and checks if each borrow has passed
     * its deadline.
     *
     * @param date The current date used to check if a borrow has passed its
     *             deadline.
     * @return A StringBuilder object containing a report of document IDs that have
     *         passed their deadline, separated by "|".
     */
    public StringBuilder reportPassedDeadline(Date date) {
        HashSet<String> outPut = new HashSet<>();
        StringBuilder hold = new StringBuilder();
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : borrows1) {
                if (checkDebt(borrow, date) != 0) {// check if there is debt for the borrow and date
                    outPut.add(borrow.getDocumentId());
                }
            }
        }

        ArrayList<String> outputArray = new ArrayList<>(outPut);
        Collections.sort(outputArray); // sort the list and separate them whit "|"
        for (String i : outputArray) {
            hold.append(i);
            hold.append("|");
        }
        return hold;
    }
    // !------------------------------------------------------------------------------

    // ?---------------------------------------------------------------------
    // ----------------------------------- Seters and Geters

    public Book getBook(String bookId) {
        return books.get(bookId);
    }

    public void addBook(Book book) {
        this.books.put(book.getBookId(), book);
    }

    public Thesis getThesis(String thesisId) {
        return theses.get(thesisId);
    }

    public void addThesis(Thesis thesis) {
        this.theses.put(thesis.getThesisID(), thesis);
    }

    public void removeBook(String bookId) {
        books.remove(bookId);
    }

    public void removeThesis(String thesisId) {
        theses.remove(thesisId);
    }

    public HashSet<String> getBookIds() {
        return new HashSet<>(books.keySet());
    }

    public HashSet<String> getThesisIds() {
        return new HashSet<>(theses.keySet());
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public String getLibraryName() {
        return this.libraryName;
    }

    public String getFoundationYear() {
        return this.foundationYear;
    }

    public int getDeskNumber() {
        return this.deskNumber;
    }

    public String getAddress() {
        return this.address;
    }

    public HashMap<String, Book> getBooks() {
        return this.books;
    }

    public HashMap<String, Thesis> getTheses() {
        return this.theses;
    }

    public HashMap<String, ArrayList<Borrow>> getBorrows() {
        return this.borrows;
    }
    // ----------------------------------- Seters and Geters

}
// ?----------------------------------------------------------------------------------------------------------------------
