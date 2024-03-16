import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class CommandOperation {

    private final HashMap<String, Library> libraries;
    private final HashMap<String, Category> categories;
    private final HashMap<String, Student> students;
    private final HashMap<String, Staff> staffs;
    private final HashMap<String, Reserve> reserves;

    public CommandOperation() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        students = new HashMap<>();
        staffs = new HashMap<>();
        reserves = new HashMap<>();
        categories.put("null", new Category("null", "null"));
        categories.put("-", new Category("-", "-"));

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
        else if (input.contains("search-user")) {

            searchUser(args[0], args[1], args[2]);
        }

        else if (input.contains("search")) {

            search(args[0]);

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
        // !----------------------------------------------------------------- RESERVE
        else if (input.contains("reserve-seat")) {
            reserveseat(args[0], args[1], args[2], args[3], args[4], args[5]);
        }

    }

    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // ? ----------------------------------------------------------------------
    // !----------------------------------------------------------------- LIBRARY
    /**
     * Adds a new library to the system.
     *
     * @param libraryId      The unique identifier for the library.
     * @param libraryName    The name of the library.
     * @param foundationYear The year the library was founded.
     * @param deskNumber     The number of desks available in the library.
     * @param address        The address of the library.
     */
    public void addLibrary(String libraryId, String libraryName, String foundationYear, int deskNumber,
            String address) {
        Library library = new Library(libraryId, libraryName, foundationYear, deskNumber, address);
        System.out.println(addLibrary2(library));

    }

    /**
     * Adds a library to the system.
     *
     * @param library The Library object representing the library to be added.
     * @return "duplicate-id" if a library with the same ID already exists in the
     *         system.
     *         "success" if the library is successfully added to the system.
     */
    public String addLibrary2(Library library) {
        if (libraries.get(library.getLibraryId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getLibraryId(), library);
        return "success";
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
        System.out.println(addCategory(category));
    }

    /**
     * Adds a new Category object to categories, if its ID is unique.
     * 
     * @param category the category that we want to add
     * @return "success" if the category is successfully added,
     *         "duplicate-id" if a category with the same ID already exists.
     */
    public String addCategory(Category category) {
        if (categories.get(category.getCategoryId()) != null) {
            return "duplicate-id";
        }
        categories.put(category.getCategoryId(), category);
        return "success";
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
        System.out.println(addBook2(book));
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

        System.out.println(editBook2(book));
    }

    /**
     * Removes a book from the management system.
     * 
     * @param bookId    The unique identifier of the book to be removed.
     * @param libraryId The unique identifier of the library from which the book is
     *                  to be removed.
     */
    public void removeBook(String bookId, String libraryId) {
        System.out.println(removeBook2(bookId, libraryId));
    }

    /**
     * Adds a new Book object to the library's collection of books.
     * 
     * @param book The Book object to be added.
     * @return "success" if successfully added,
     *         "not-found" if the library or category is not found,
     *         "duplicate-id" if a book with the same ID already exists in the
     *         library.
     */
    public String addBook2(Book book) {
        Library library = libraries.get(book.getLibraryId());
        if (library == null) {

            return "not-found";
        }
        Category category = categories.get(book.getCategoryId());
        if (category == null) {

            return "not-found";
        }
        if (library.getBook(book.getBookId()) != null) {
            return "duplicate-id";
        }
        library.addBook(book);
        return "success";
    }

    /**
     * Edits an existing Book object in the library's books.
     * 
     * @param book The Book that we want to edit.
     * @return "success" if the book is successfully edited,
     *         "not-found" if the library, category, or book is not found.
     */
    public String editBook2(Book book) {
        Library library = libraries.get(book.getLibraryId());
        if (library == null) {

            return "not-found";
        }
        Category category = categories.get(book.getCategoryId());
        if (category == null) {

            return "not-found";
        }
        Book book1 = library.getBook(book.getBookId());
        if (book1 == null) {

            return "not-found";
        }
        book1.edit(book);
        return "success";
    }

    /**
     * Removes a book from the library's collection.
     *
     * @param bookId    The unique identifier of the book to be removed.
     * @param libraryId The unique identifier of the library from which the book
     *                  should be removed.
     * 
     * @return "success" if the book is successfully removed, "not-found" if the
     *         library or book is not found,
     *         "not-allowed" if the book is currently borrowed and cannot be
     *         removed.
     */
    public String removeBook2(String bookId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        if (library.countDocs(bookId) != 0) {
            return "not-allowed";
        }
        if (library.getBook(bookId) == null) {
            return "not-found";
        }
        library.removeBook(bookId);
        return "success";
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
        System.out.println(addThesis2(thesis));
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
        System.out.println((editThesis2(thesis)));
    }

    /**
     * Removes a thesis from the management system.
     * 
     * @param thesisId  The unique identifier of the thesis to be removed.
     * @param libraryId The unique identifier of the library from which the thesis
     *                  is to be removed.
     */
    public void removeThesis(String thesisId, String libraryId) {
        System.out.println(removeThesis2(thesisId, libraryId));
    }

    /**
     * Adds a new Thesis object to the library's collection of theses.
     * 
     * @param thesis The Thesis object to be added.
     * @return "success" if the thesis is successfully added.
     *         "not-found" if the library or category is not found
     *         "duplicate-id" if a thesis with the same ID already exists in the
     *         library.
     */
    public String addThesis2(Thesis thesis) {
        Library library = libraries.get(thesis.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        Category category = categories.get(thesis.getCategoryId());
        if (category == null) {
            return "not-found";
        }
        if (library.getThesis(thesis.getThesisID()) != null) {
            return "duplicate-id";
        }
        library.addThesis(thesis);
        return "success";
    }

    /**
     * Edits an existing Thesis object in the library's collection of theses.
     * 
     * @param thesis The Thesis object containing the updated information.
     * @return "success" if the thesis is successfully edited
     *         "not-found" if the library, category, or thesis is not found.
     */
    public String editThesis2(Thesis thesis) {
        Library library = libraries.get(thesis.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        Category category = categories.get(thesis.getCategoryId());
        if (category == null) {
            return "not-found";
        }
        Thesis thesis1 = library.getThesis(thesis.getThesisID());
        if (thesis1 == null) {
            return "not-found";
        }
        thesis1.edit(thesis);
        return "success";
    }

    /**
     * Removes a thesis from the library's collection.
     *
     * @param thesisId  The unique identifier of the thesis to be removed.
     * @param libraryId The unique identifier of the library from which the thesis
     *                  should be removed.
     * @return "success" if the thesis is successfully removed
     *         "not-found" if the library or thesis is not found,
     *         "not-allowed" if the thesis is currently borrowed and cannot be
     *         removed.
     */
    public String removeThesis2(String thesisId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        if (library.countDocs(thesisId) != 0) {
            return "not-allowed";
        }
        if (library.getThesis(thesisId) == null) {
            return "not-found";
        }
        library.removeThesis(libraryId);
        return "success";
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
        System.out.println(addStudent2(student));
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
        System.out.println(editStudent2(student));
    }

    /**
     * Removes a student from the management system.
     * 
     * @param studentId The unique identifier of the student to be removed.
     */

    public void removeStudent(String studentId) {
        System.out.println(removeStudent2(studentId));
    }

    /**
     * Adds a new Student object to the collection of students.
     * 
     * @param student The Student object to be added.
     * @return "success" if the student is successfully added
     *         "duplicate-id" if a student with the same ID already exists.
     */
    public String addStudent2(Student student) {
        if (students.get(student.getStudentId()) != null) {
            return "duplicate-id";
        }
        students.put(student.getStudentId(), student);
        return "success";
    }

    /**
     * Edits an existing Student object in the collection of students.
     * 
     * @param student The Student object containing the updated information.
     * @return "success" if the student is successfully edited
     *         "not-found" if the student is not found.
     */
    public String editStudent2(Student student) {
        Student student1 = students.get(student.getStudentId());
        if (student1 == null) {
            return "not-found";
        }
        student1.edit(student);
        return "success";
    }

    /**
     * Removes a student from the collection of students.
     *
     * @param studentId The unique identifier of the student to be removed.
     * @return "success" if the student is successfully removed
     *         "not-found" if the student is not found
     *         "not-allowed" if the student is currently borrowing books or has
     *         outstanding debt.
     */
    public String removeStudent2(String studentId) {
        Student student = students.get(studentId);
        if (student == null) {
            return "not-found";
        }
        for (Library library : new ArrayList<>(libraries.values())) {
            if (library.checkUserBorrows(studentId) != null) {
                return "not-allowed";
            }
        }
        if (student.getDebt() != 0) {
            return "not-allowed";
        }
        students.remove(studentId);
        return "success";
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
        System.out.println(addStaff2(staff));
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
        System.out.println(editStaff2(staff));
    }

    /**
     * Removes a staff member from the management system.
     * 
     * @param staffId The unique identifier of the staff member to be removed.
     */
    public void removeStaff(String StaffId) {
        System.out.println(removeStaff2(StaffId));
    }

    /**
     * Adds a new Staff object to the collection of staff members.
     * 
     * @param staff The Staff object to be added.
     * @return "success" if the staff member is successfully added
     *         "duplicate-id" if a staff member with the same ID already exists.
     */
    public String addStaff2(Staff staff) {
        if (staffs.get(staff.getStaffId()) != null) {
            return "duplicate-id";
        }
        staffs.put(staff.getStaffId(), staff);
        return "success";
    }

    /**
     * Edits an existing Staff object in the collection of staff members.
     * 
     * @param staff The Staff object containing the updated information.
     * @return "success" if the staff member is successfully edited
     *         "not-found" if the staff member is not found.
     */
    public String editStaff2(Staff staff) {
        Staff staff1 = staffs.get(staff.getStaffId());
        if (staff1 == null) {
            return "not-found";
        }
        staff1.edit(staff);
        return "success";
    }

    /**
     * Removes a staff member from the collection of staff members.
     *
     * @param staffId The unique identifier of the staff member to be removed.
     * @return "success" if the staff member is successfully removed
     *         "not-found" if the staff member is not found,
     *         "not-allowed" if the staff member has outstanding debt or is
     *         currently borrowing items.
     */
    public String removeStaff2(String staffId) {
        Staff staff = staffs.get(staffId);
        if (staff == null) {
            return "not-found";
        }
        if (staff.getDebt() != 0) {
            return "not-allowed";
        }
        for (Library library : new ArrayList<>(libraries.values())) {
            if (library.checkUserBorrows(staffId) != null) {
                return "not-allowed";
            }
        }
        staffs.remove(staffId);
        return "success";
    }

    // !----------------------------------------------------------------- BORROW
    /**
     * Borrows an item from the library on behalf of a user.
     * 
     * @param userId    The unique identifier of the user borrowing the item.
     * @param password  The password of the user borrowing the item.
     * @param libraryId The unique identifier of the library from which the item is
     *                  being borrowed.
     * @param docId     The unique identifier of the item being borrowed.
     * @param strDate   The date in string format (yyyy-MM-dd) when the item is
     *                  being borrowed.
     * @param hour      The hour in string format (HH:mm) when the item is being
     *                  borrowed.
     * @throws ParseException If the parsing of the date and hour strings fails.
     */
    public void borrow(String userId, String password, String libraryId, String docId, String strDate, String hour)
            throws ParseException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());

        Borrow borrow = new Borrow(date, userId, docId, libraryId);

        System.out.println(borrow2(borrow, password));

    }

    /**
     * Returns an item to the library on behalf of a user.
     * 
     * @param userId    The unique identifier of the user returning the item.
     * @param pass      The password of the user returning the item.
     * @param libraryId The unique identifier of the library to which the item is
     *                  being returned.
     * @param docId     The unique identifier of the item being returned.
     * @param strDate   The date in string format (yyyy-MM-dd) when the item is
     *                  being returned.
     * @param hour      The hour in string format (HH:mm) when the item is being
     *                  returned.
     * @throws ParseException If the parsing of the date and hour strings fails.
     */
    public void returning(String userId, String pass, String libraryId, String docId, String strDate, String hour)
            throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        Borrow borrow = new Borrow(date, userId, docId, libraryId);
        System.out.println(returning2(borrow, pass));
    }

    /**
     * Handles the borrowing process for a user, checking user existence, password
     * validation, document availability, and borrowing limits.
     * 
     * @param borrow   The Borrow object containing information about the borrowing
     *                 request.
     * @param password The password provided by the user for authentication.
     * @return "success" if the borrowing process is successful, "not-found" if the
     *         user or library is not found,
     *         "invalid-pass" if the password provided is invalid
     *         "not-allowed" if the borrowing is not allowed due to limits or
     *         restrictions,
     *         "not-found" if the requested document is not found in the library.
     */
    public String borrow2(Borrow borrow, String password) {
        if (!borrow.checkUser(new HashSet<>(students.keySet()), new HashSet<>(staffs.keySet()))) {
            return "not-found"; // user not-found
        }
        if (borrow.isStudent()) {
            Student student = students.get(borrow.getUserId());
            if (!student.getPassword().equals(password)) {
                return "invalid-pass";// user is student and its pass is wrong
            }
        } else {
            Staff staff = staffs.get(borrow.getUserId());
            if (!staff.getPassword().equals(password)) {
                return "invalid-pass";// user is staff and its pass is wrong
            }
        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found"; // library not-found
        }
        if (!borrow.checkDoc(library.getBookIds(), library.getThesisIds())) {
            return "not-found"; // there is no book or thesis whit this ID
        }
        if (!library.borrow(borrow, countBorrow(borrow.getUserId()))) {
            return "not-allowed"; // the user can not borrow more doc
        }
        return "success";
    }

    /**
     * Counts the number of items borrowed by a user across all libraries.
     * 
     * @param userId The unique identifier of the user whose borrow count is to be
     *               calculated.
     * @return The total number of items borrowed by the user across all libraries.
     */
    public int countBorrow(String userId) {
        int borrowed = 0;
        for (Library library : libraries.values()) {
            borrowed += library.countBorrows(userId);
        }
        return borrowed;
    }

    /**
     * Handles the returning process for a user, checking user existence, password
     * validation, document availability, and returning conditions.
     * 
     * @param borrow The Borrow object containing information about the returning
     *               request.
     * @param pass   The password provided by the user for authentication.
     * @return "success" if the returning process is successful
     *         "not-found" if the user, library, or borrow record is not found,
     *         "invalid-pass" if the password provided is invalid, or a String
     *         representation of the debt incurred if any.
     */
    public String returning2(Borrow borrow, String pass) {
        if (!borrow.checkUser(new HashSet<>(students.keySet()), new HashSet<>(staffs.keySet()))) {
            return "not-found"; // user not-found
        }
        if (borrow.isStudent()) {
            Student student = students.get(borrow.getUserId());
            if (!student.getPassword().equals(pass)) {
                return "invalid-pass";// user is student and its pass is wrong
            }

        } else {
            Staff staff = staffs.get(borrow.getUserId());
            if (!staff.getPassword().equals(pass)) {
                return "invalid-pass";// user is staff and its pass is wrong
            }
        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found";// library not-found
        }
        if (!borrow.checkDoc(library.getBookIds(), library.getThesisIds())) {
            return "not-found";// there is no book or thesis whit this ID
        }
        Borrow borrowHelp = library.checkUserBorrows(borrow.getUserId(), borrow.getStuffId());
        if (borrowHelp == null) {
            return "not-found"; // there is no borrow that we want to return it
        }
        int debt = library.returning(borrowHelp, borrow.getDate()); // calculate the debt
        if (debt == 0) {
            return "success";
        }
        if (borrow.isStudent()) {
            students.get(borrow.getUserId()).setDebt(debt);
            return "" + debt;
        }
        staffs.get(borrow.getUserId()).setDebt(debt);
        return "" + debt;
    }

    // !----------------------------------------------------------------- SEARCH
    /**
     * Searches for items in the library management system based on the provided
     * key.
     * 
     * @param key The keyword to search for.
     */
    public void search(String key) {
        System.out.println(search2(key));
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

        System.out.println(searchUser2(userId, pass, key));
    }

    /**
     * Searches for the given key across all libraries and generates a sorted list
     * of search results.
     * 
     * @param key The search key to look for in the libraries.
     * @return A StringBuilder containing the sorted search results separated by "|"
     *         characters, or "not-found" if no results are found.
     */
    public StringBuilder search2(String key) {
        HashSet<String> output = new HashSet<>();
        StringBuilder stringBuilder = new StringBuilder();
        for (Library library : libraries.values()) {
            output.addAll(library.search(key));
        }
        ArrayList<String> hold = new ArrayList<>(output);
        Collections.sort(hold);
        for (String str : hold) {
            stringBuilder.append(str);
            stringBuilder.append("|");
        }
        if (stringBuilder.length() == 0) {
            return stringBuilder.append("not-found");
        }
        stringBuilder = stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        return stringBuilder;
    }

    /**
     * Searches for the given key across all libraries and generates a sorted list
     * of search results, after authenticating the user.
     * 
     * @param userId The unique identifier of the user.
     * @param pass   The password provided by the user for authentication.
     * @param key    The search key to look for in the libraries.
     * @return A StringBuilder containing the sorted search results separated by "|"
     *         characters, or "not-found" if the user is not found,
     *         "invalid-pass" if the password provided is invalid.
     */
    public StringBuilder searchUser2(String userId, String pass, String key) {

        Student student = students.get(userId);
        Staff staff = staffs.get(userId);

        if (staff == null && student == null) {
            return new StringBuilder("not-found");

        } else if (staff == null) {

            if (!student.getPassword().equals(pass)) {

                return new StringBuilder("invalid-pass");
            }
        } else if (student == null) {

            if (!staff.getPassword().equals(pass)) {
                return new StringBuilder("invalid-pass");
            }
        }

        return searchUser3(key);
    }

    /**
     * Searches for the given key among students' and staff members' first and last
     * names, and generates a sorted list of matching user IDs.
     * 
     * @param key The search key to look for in the user names.
     * @return A StringBuilder containing the sorted list of user IDs separated by
     *         "|" characters, or "not-found" if no matching users are found.
     */
    private StringBuilder searchUser3(String key) {
        HashSet<String> output = new HashSet<>();
        StringBuilder searchID = new StringBuilder();
        for (Student student : students.values()) {
            if (student.getFirstName().toLowerCase().contains(key.toLowerCase())) {
                output.add(student.getStudentId());
            }
            if (student.getLastName().toLowerCase().contains(key.toLowerCase())) {
                output.add(student.getStudentId());
            }
        }
        for (Staff staff : staffs.values()) {
            if (staff.getFirstName().toLowerCase().contains(key.toLowerCase())) {
                output.add(staff.getStaffId());
            }
            if (staff.getLastName().toLowerCase().contains(key.toLowerCase())) {
                output.add(staff.getStaffId());
            }
        }
        ArrayList<String> outputArray = new ArrayList<>(output);
        Collections.sort(outputArray);
        for (String i : outputArray) {
            searchID.append(i);
            searchID.append("|");
        }
        if (searchID.length() == 0) {
            return new StringBuilder("not-found");
        }
        searchID = searchID.deleteCharAt(searchID.length() - 1);
        return searchID;
    }

    // !----------------------------------------------------------------- REPORT
    /**
     * Generates a report of the total penalties accumulated by all users in the
     * library management system.
     * The penalties include any outstanding debts owed by both staff and students.
     */
    public void reportPenalties() {
        System.out.println(reportPenalties2());
    }

    public void categoryReport(String categoryId) {
        System.out.println(categoryReport2(categoryId));
    }

    /**
     * Generates a report for a specific library in the management system.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  generated.
     */
    public void libraryReport(String libraryId) {
        System.out.println(libraryReport2(libraryId));
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
        System.out.println(reportPasseDeadline2(libraryId, date));
    }

    /**
     * Generates a report of the total penalties accrued by both staff and students.
     * 
     * @return A String representation of the total penalties accrued by staff and
     *         students.
     */
    public String reportPenalties2() {
        int Penalties = 0;
        for (Staff staff : staffs.values()) {
            Penalties += staff.getDebt();
        }
        for (Student student : students.values()) {
            Penalties += student.getDebt();
        }
        return "" + Penalties;
    }

    /**
     * Generates a report for the specified category, showing the number of books
     * and theses in that category.
     * 
     * @param categoryId The unique identifier of the category for which the report
     *                   is requested.
     * @return A String representation of the report for the specified category, or
     *         "not-found" if the category is not found.
     */
    public String categoryReport2(String categoryId) {
        Category category = categories.get(categoryId);
        if (category == null) {
            return "not-found";
        }

        int booksCount = 0;
        int thesesCount = 0;

        for (Library library : libraries.values()) {
            booksCount += library.countBooksInCategory(categoryId);

            thesesCount += library.countThesesInCategory(categoryId);

        }

        return booksCount + " " + thesesCount;
    }

    /**
     * Generates a report for the specified library.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  requested.
     * @return A String representation of the report for the specified library, or
     *         "not-found" if the library is not found.
     */
    public String libraryReport2(String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        return library.libraryReport();
    }

    /**
     * Generates a report of items that have passed their deadline in the specified
     * library as of the given date.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  requested.
     * @param date      The date for which the report is generated.
     * @return A StringBuilder containing the report of items that have passed their
     *         deadline in the specified library as of the given date,
     *         or "not-found" if the library is not found, or "none" if no items
     *         have passed their deadline.
     */
    public StringBuilder reportPasseDeadline2(String libraryId, Date date) {
        StringBuilder output;
        Library library = libraries.get(libraryId);
        if (library == null) {
            return new StringBuilder("not-found");
        }
        output = library.reportPassedDeadline(date);
        if (output.length() == 0) {
            return new StringBuilder("none");
        }
        output = output.deleteCharAt(output.length() - 1);
        return output;
    }

    // !----------------------------------------------------------------- RESERVE
    /**
     * Reserves a seat in a library for a user.
     *
     * @param userId      The unique identifier of the user making the reservation.
     * @param password    The password associated with the user's account.
     * @param libraryId   The unique identifier of the library where the seat is
     *                    being reserved.
     * @param strDate     The date of the reservation in the format "yyyy-MM-dd".
     * @param startedHour The starting hour of the reservation.
     * @param endedHour   The ending hour of the reservation.
     * @throws ParseException If an error occurs while parsing the date string.
     */
    public void reserveseat(String userId, String password, String libraryId, String strDate, String startedHour,
            String endedHour)
            throws ParseException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd").parse(strDate);

        Date startedDate = new Date(utilDate.getTime());

        Reserve reserve = new Reserve(libraryId, startedDate, startedHour, endedHour, userId);

        System.out.println(reserveseat2(reserve, password));

    }

    /**
     * Attempts to reserve a seat based on the provided reservation details and
     * password.
     *
     * @param reserve  The reservation object containing details of the reservation.
     * @param password The password associated with the user's account.
     * @return A string indicating the result of the reservation attempt:
     *         - "not-found" if the user or library is not found.
     *         - "invalid-pass" if the provided password is incorrect.
     *         - "not-allowed" if the reservation is not allowed due to existing
     *         reservations or exceeding time limits.
     *         - "not-available" if all seats are already reserved.
     *         - "success" if the reservation is successfully made.
     */
    public String reserveseat2(Reserve reserve, String password) {

        if (!reserve.checkUser(new HashSet<>(students.keySet()), new HashSet<>(staffs.keySet()))) {
            return "not-found";
        }
        Library library = libraries.get(reserve.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        if (reserve.isStudent()) {
            Student student = students.get(reserve.getUserId());
            if (!student.getPassword().equals(password)) {
                return "invalid-pass";
            }
        } else {
            Staff staff = staffs.get(reserve.getUserId());
            if (!staff.getPassword().equals(password)) {
                return "invalid-pass";
            }
        }

        if (reserve.counthours()) {

            return "not-allowed";
        }

        for (Reserve reserve2 : reserves.values()) {

            if (reserve2.getUserId().equals(reserve.getUserId())
                    && reserve2.getDate().getTime() == reserve.getDate().getTime()) {
                return "not-allowed";
            }

        }

        if (countReservation(reserve) == library.getDeskNumber()) {
            return "not-available";
        }

        reserves.put(reserve.getUserId(), reserve);
        return "success";

    }

    /**
     * Counts the number of reservations for the same library and date as the
     * provided reservation.
     *
     * @param reserve The reservation for which the count is to be determined.
     * @return The number of reservations conflicting with the provided reservation.
     */
    public int countReservation(Reserve reserve) {

        int count = 0;
        for (Reserve reserve2 : reserves.values()) {
            if (reserve2.getLibraryId().equals(reserve.getLibraryId())
                    && reserve2.getDate().getTime() == reserve.getDate().getTime() && conflict(reserve, reserve2)) {
                count++;
            }

        }
        return count;

    }

    /**
     * Checks if there is a time conflict between two reservations.
     *
     * @param reserve1 The first reservation.
     * @param reserve2 The second reservation.
     * @return True if there is a time conflict between the reservations, false
     *         otherwise.
     */
    public boolean conflict(Reserve reserve1, Reserve reserve2) {

        LocalTime t1 = LocalTime.parse(reserve1.getStartDate());
        LocalTime t2 = LocalTime.parse(reserve1.getEndDate());
        LocalTime t3 = LocalTime.parse(reserve2.getStartDate());
        LocalTime t4 = LocalTime.parse(reserve2.getEndDate());
        if (timeToMin(t1) <= timeToMin(t3) && timeToMin(t3) <= timeToMin(t2)) {
            return true;
        }
        if (timeToMin(t1) <= timeToMin(t4) && timeToMin(t4) <= timeToMin(t2)) {
            return true;
        }

        return false;
    }

    /**
     * Converts the given time to minutes.
     *
     * @param time The time to be converted.
     * @return The time represented in minutes.
     */
    public long timeToMin(LocalTime time) {
        long miiin;
        miiin = time.getHour() * 60 + time.getMinute();
        return miiin;

    }

    public void res() {

        for (Reserve reserve1Reserve : reserves.values()) {

            System.out.println(reserve1Reserve.getStartDate());
        }

    }

}