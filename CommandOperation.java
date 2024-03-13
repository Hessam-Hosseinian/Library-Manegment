import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class CommandOperation {
    private Manegment manegment;

    public CommandOperation() {
        this.manegment = new Manegment();
    }

    public void input(String input) throws ParseException {
        String[] command = input.split("#");
        String[] args = new String[] {};
        if (command.length > 1) {
            args = command[1].split("\\|");
        }
        // !----------------------------------------------------------------- LIBRARY
        if (input.contains("add-library")) {

            addLibrary(args[0], args[1], args[2], Integer.valueOf(args[3]), args[4]);
        }
        // !----------------------------------------------------------------- CATEGORY
        else if (input.contains("add-category")) {

            addCategory(args[0], args[1]);
        }
        // !----------------------------------------------------------------- BOOK
        else if (input.contains("add-book")) {
            addBook(args[0], args[1], args[2], args[3], args[4], Integer.valueOf(args[5]), args[6], args[7]);
        }

        else if (input.contains("edit-book")) {
            if (args[6].equals("-")) {
                editBook(args[0], args[1], args[2], args[3], args[4], args[5], -1, args[7]);
            } else {
                editBook(args[0], args[1], args[2], args[3], args[4], args[5],
                        Integer.valueOf(args[6]), args[7]);
            }
        }

        else if (input.contains("remove-book")) {
            removeBook(args[0], args[1]);
        }
        // !----------------------------------------------------------------- THESIS

        else if (input.contains("add-thesis")) {
            addThesis(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        }

        else if (input.contains("edit-thesis")) {
            editThesis(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        }

        else if (input.contains("remove-thesis")) {
            removeThesis(args[0], args[1]);
        }
        // !----------------------------------------------------------------- STUDENT

        else if (input.contains("add-student")) {
            addStudent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        else if (input.contains("edit-student")) {
            editStudent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        } else if (input.contains("remove-student")) {
            removeStudent(args[0]);
        }
        // !----------------------------------------------------------------- STAFF

        else if (input.contains("add-staff")) {
            addStaff(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        else if (input.contains("edit-staff")) {
            editStaff(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        else if (input.contains("remove-staff")) {
            removeStaff(args[0]);
        }
        // !----------------------------------------------------------------- BORROW

        else if (input.contains("borrow")) {
            borrow(args[0], args[1], args[2], args[3], args[4], args[5]);
        }

        else if (input.contains("return")) {
            returning(args[0], args[1], args[2], args[3], args[4], args[5]);
        }

        // !----------------------------------------------------------------- SEARCH

        else if (input.contains("search")) {

            search(args[0]);

        }

        else if (input.contains("search-user")) {

            searchUser(args[0], args[1], args[2]);
        }
        // !----------------------------------------------------------------- REPORT

        else if (input.contains("report-penalties-sum")) {
            reportPenalties();
        }

        else if (input.contains("library-report")) {
            libraryReport(args[0]);
        }

        else if (input.contains("category-report")) {
            categoryReport(args[0]);
        }

        else if (input.contains("report-passed-deadline")) {
            reportPasseDeadline(args[0], args[1], args[2]);
        }

    }

    // ? ----------------------------------------------------------------------
    // !----------------------------------------------------------------- LIBRARY
    /**
     * Adds a new library to the management system with the provided details.
     * 
     * @param libraryId      The unique identifier of the library.
     * @param libraryName    The name of the library.
     * @param foundationYear The year the library was founded.
     * @param deskNumber     The number of desks in the library.
     * @param address        The address of the library.
     */
    public void addLibrary(String libraryId, String libraryName, String foundationYear, int deskNumber,
            String address) {
        Library library = new Library(libraryId, libraryName, foundationYear, deskNumber, address);
        System.out.println(manegment.addLibrary(library));

    }

    // !----------------------------------------------------------------- CATEGORY
    /**
     * Adds a new category to the management system with the provided details.
     * 
     * @param categoryId   The unique identifier of the category.
     * @param categoryName The name of the category.
     */
    public void addCategory(String categoryId, String categoryName) {
        Category category = new Category(categoryId, categoryName);
        System.out.println(manegment.addCategory(category));
    }

    // !----------------------------------------------------------------- BOOK
    /**
     * Adds a new book to the management system with the provided details.
     * 
     * @param id         The unique identifier of the book.
     * @param name       The name/title of the book.
     * @param authorName The name of the author of the book.
     * @param publisher  The name of the publisher of the book.
     * @param year       The year of publication of the book.
     * @param numBook    The number of copies of the book.
     * @param categoryId The unique identifier of the category to which the book
     *                   belongs.
     * @param libraryId  The unique identifier of the library where the book is
     *                   located.
     */
    public void addBook(String id, String name, String authorName, String publisher, String year, int numBook,
            String categoryId, String libraryId) {
        Book book = new Book(id, name, authorName, publisher, year, numBook,
                categoryId, libraryId);
        System.out.println(manegment.addBook(book));
    }

    /**
     * Edits the details of an existing book in the management system.
     * 
     * @param id         The unique identifier of the book to be edited.
     * @param libraryId  The unique identifier of the library where the book is
     *                   located.
     * @param name       The updated name/title of the book.
     * @param authorName The updated name of the author of the book.
     * @param publisher  The updated name of the publisher of the book.
     * @param year       The updated year of publication of the book.
     * @param numBook    The updated number of copies of the book.
     * @param categoryId The updated unique identifier of the category to which the
     *                   book belongs.
     */
    public void editBook(String id, String libraryId, String name, String authorName, String publisher, String year,
            int numBook, String categoryId) {

        Book book = new Book(id, name, authorName, publisher, year, numBook,
                categoryId, libraryId);

        System.out.println(manegment.editBook(book));
    }

    /**
     * Removes a book from the management system.
     * 
     * @param bookId    The unique identifier of the book to be removed.
     * @param libraryId The unique identifier of the library from which the book is
     *                  to be removed.
     */
    public void removeBook(String bookId, String libraryId) {
        System.out.println(manegment.removeBook(bookId, libraryId));
    }

    // !----------------------------------------------------------------- THESIS
    /**
     * Adds a new thesis to the management system with the provided details.
     * 
     * @param id          The unique identifier of the thesis.
     * @param name        The name/title of the thesis.
     * @param studentName The name of the student who authored the thesis.
     * @param advisor     The name of the advisor for the thesis.
     * @param year        The year of publication of the thesis.
     * @param categoryId  The unique identifier of the category to which the thesis
     *                    belongs.
     * @param libraryId   The unique identifier of the library where the thesis is
     *                    located.
     */
    public void addThesis(String id, String name, String studentName, String advisor, String year, String categoryId,
            String libraryId) {
        Thesis thesis = new Thesis(id, name, studentName, advisor, year, categoryId,
                libraryId);
        System.out.println(manegment.addThesis(thesis));
    }

    /**
     * Edits the details of an existing thesis in the management system.
     * 
     * @param id          The unique identifier of the thesis to be edited.
     * @param libraryId   The unique identifier of the library where the thesis is
     *                    located.
     * @param name        The updated name/title of the thesis.
     * @param studentName The updated name of the student who authored the thesis.
     * @param advisor     The updated name of the advisor for the thesis.
     * @param year        The updated year of publication of the thesis.
     * @param categoryId  The updated unique identifier of the category to which the
     *                    thesis belongs.
     */
    public void editThesis(String id, String libraryId, String name, String studentName, String advisor, String year,
            String categoryID) {
        Thesis thesis = new Thesis(id, name, studentName, advisor, year, categoryID,
                libraryId);
        System.out.println((manegment.editThesis(thesis)));
    }

    /**
     * Removes a thesis from the management system.
     * 
     * @param thesisId  The unique identifier of the thesis to be removed.
     * @param libraryId The unique identifier of the library from which the thesis
     *                  is to be removed.
     */
    public void removeThesis(String thesisId, String libraryId) {
        System.out.println(manegment.removeThesis(thesisId, libraryId));
    }

    // !----------------------------------------------------------------- STUDENT
    /**
     * Adds a new student to the management system with the provided details.
     * 
     * @param studentId The unique identifier of the student.
     * @param password  The password of the student.
     * @param firstName The first name of the student.
     * @param lastName  The last name of the student.
     * @param codeId    The code ID of the student.
     * @param birthday  The birthday of the student.
     * @param address   The address of the student.
     */

    public void addStudent(String studentId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Student student = new Student(studentId, password, firstName, lastName, codeId,
                birthday, address);
        System.out.println(manegment.addStudent(student));
    }

    /**
     * Edits the details of an existing student in the management system.
     * 
     * @param studentId The unique identifier of the student to be edited.
     * @param password  The updated password of the student.
     * @param firstName The updated first name of the student.
     * @param lastName  The updated last name of the student.
     * @param codeId    The updated code ID of the student.
     * @param birthday  The updated birthday of the student.
     * @param address   The updated address of the student.
     */

    public void editStudent(String studentId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Student student = new Student(studentId, password, firstName, lastName, codeId,
                birthday, address);
        System.out.println(manegment.editStudent(student));
    }

    /**
     * Removes a student from the management system.
     * 
     * @param studentId The unique identifier of the student to be removed.
     */

    public void removeStudent(String studentId) {
        System.out.println(manegment.removeStudent(studentId));
    }

    // !----------------------------------------------------------------- STAFF
    /**
     * Adds a new staff member to the management system with the provided details.
     * 
     * @param staffId   The unique identifier of the staff member.
     * @param password  The password of the staff member.
     * @param firstName The first name of the staff member.
     * @param lastName  The last name of the staff member.
     * @param codeId    The code ID of the staff member.
     * @param birthday  The birthday of the staff member.
     * @param address   The address of the staff member.
     */
    public void addStaff(String StaffId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Staff staff = new Staff(StaffId, password, firstName, lastName, codeId, birthday,
                address);
        System.out.println(manegment.addStaff(staff));
    }

    /**
     * Edits the details of an existing staff member in the management system.
     * 
     * @param staffId   The unique identifier of the staff member to be edited.
     * @param password  The updated password of the staff member.
     * @param firstName The updated first name of the staff member.
     * @param lastName  The updated last name of the staff member.
     * @param codeId    The updated code ID of the staff member.
     * @param birthday  The updated birthday of the staff member.
     * @param address   The updated address of the staff member.
     */
    public void editStaff(String StaffId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Staff staff = new Staff(StaffId, password, firstName, lastName, codeId, birthday,
                address);
        System.out.println(manegment.editStaff(staff));
    }

    /**
     * Removes a staff member from the management system.
     * 
     * @param staffId The unique identifier of the staff member to be removed.
     */
    public void removeStaff(String StaffId) {
        System.out.println(manegment.removeStaff(StaffId));
    }

    // !----------------------------------------------------------------- BORROW
    /**
     * Borrows an item from the library on behalf of a user.
     * 
     * @param userId    The unique identifier of the user borrowing the item.
     * @param password  The password of the user borrowing the item.
     * @param libraryId The unique identifier of the library from which the item is
     *                  being borrowed.
     * @param stuffId   The unique identifier of the item being borrowed.
     * @param strDate   The date in string format (yyyy-MM-dd) when the item is
     *                  being borrowed.
     * @param hour      The hour in string format (HH:mm) when the item is being
     *                  borrowed.
     * @throws ParseException If the parsing of the date and hour strings fails.
     */
    public void borrow(String userId, String password, String libraryId, String stuffId, String strDate, String hour)
            throws ParseException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());

        Borrow borrow = new Borrow(date, userId, stuffId, libraryId);

        System.out.println(manegment.borrow(borrow, password));

    }

    /**
     * Returns an item to the library on behalf of a user.
     * 
     * @param userId    The unique identifier of the user returning the item.
     * @param pass      The password of the user returning the item.
     * @param libraryId The unique identifier of the library to which the item is
     *                  being returned.
     * @param stuffId   The unique identifier of the item being returned.
     * @param strDate   The date in string format (yyyy-MM-dd) when the item is
     *                  being returned.
     * @param hour      The hour in string format (HH:mm) when the item is being
     *                  returned.
     * @throws ParseException If the parsing of the date and hour strings fails.
     */
    public void returning(String userId, String pass, String libraryId, String stuffId, String strDate, String hour)
            throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        Borrow borrow = new Borrow(date, userId, stuffId, libraryId);
        System.out.println(manegment.returning(borrow, pass));
    }

    // !----------------------------------------------------------------- SEARCH
    /**
     * Searches for items in the library management system based on the provided
     * key.
     * 
     * @param key The keyword to search for.
     */
    public void search(String key) {
        System.out.println(manegment.search(key));
    }

    /**
     * Searches for users in the library management system based on the provided
     * user ID, password, and key.
     * 
     * @param userId The unique identifier of the user performing the search.
     * @param pass   The password of the user performing the search.
     * @param key    The keyword to search for.
     */
    public void searchUser(String userId, String pass, String key) {

        System.out.println(manegment.searchUser(userId, pass, key));
    }

    // !----------------------------------------------------------------- REPORT
    /**
     * Generates a report of the total penalties accumulated by all users in the
     * library management system.
     * The penalties include any outstanding debts owed by both staff and students.
     */
    public void reportPenalties() {
        System.out.println(manegment.reportPenalties());
    }

    public void categoryReport(String categoryId) {
        System.out.println(manegment.categoryReport(categoryId));
    }

    /**
     * Generates a report for a specific library in the management system.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  generated.
     */
    public void libraryReport(String libraryId) {
        System.out.println(manegment.libraryReport(libraryId));
    }

    /**
     * Generates a report of items that have passed their deadline for return in a
     * specific library.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  generated.
     * @param strDate   The date in string format (yyyy-MM-dd) to check for passed
     *                  deadlines.
     * @param hour      The hour in string format (HH:mm) to check for passed
     *                  deadlines.
     * @throws ParseException If the parsing of the date and hour strings fails.
     */
    public void reportPasseDeadline(String libraryId, String strDate, String hour) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        System.out.println(manegment.reportPasseDeadline(libraryId, date));
    }
}