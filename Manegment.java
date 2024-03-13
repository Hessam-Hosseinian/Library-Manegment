
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class Manegment {

    private final HashMap<String, Library> libraries;
    private final HashMap<String, Category> categories;
    private final HashMap<String, Student> students;
    private final HashMap<String, Staff> staffs;

    public Manegment() {
        libraries = new HashMap<>();
        categories = new HashMap<>();
        students = new HashMap<>();
        staffs = new HashMap<>();
        categories.put("null", new Category("null", "null"));
        categories.put("-", new Category("-", "-"));

    }
    // !----------------------------------------------------------------- LIBRARY

    /**
     * Adds a new Library object to libraries, if its ID is unique.
     * 
     * @param library the library that we want to add
     * @return "success" if the library is successfully added,
     *         or "duplicate-id" if a library with the same ID already exists.
     */
    public String addLibrary(Library library) {
        if (libraries.get(library.getLibraryId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getLibraryId(), library);
        return "success";
    }

    // !----------------------------------------------------------------- CATEGORY

    /**
     * Adds a new Category object to categories, if its ID is unique.
     * 
     * @param category the category that we want to add
     * @return "success" if the category is successfully added,
     *         or "duplicate-id" if a category with the same ID already exists.
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
     * Adds a new Book object to the library's collection of books.
     * 
     * @param book The Book object to be added.
     * @return "success" if successfully added,
     *         "not-found" if the library or category is not found,
     *         "duplicate-id" if a book with the same ID already exists in the
     *         library.
     */
    public String addBook(Book book) {
        Library library = libraries.get(book.getLibraryId());
        Category category = categories.get(book.getCategoryId());
        if (library == null || category == null) {
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
    public String editBook(Book book) {
        Library library = libraries.get(book.getLibraryId());
        Category category = categories.get(book.getCategoryId());
        Book book1 = library.getBook(book.getBookId());
        if (library == null || category == null || book1 == null) {

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
    public String removeBook(String bookId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null || library.getBook(bookId) == null) {
            return "not-found";
        }
        if (library.countDocs(bookId) != 0) {
            return "not-allowed";
        }

        library.removeBook(bookId);
        return "success";
    }
    // !----------------------------------------------------------------- THESIS

    /**
     * Adds a new Thesis object to the library's collection of theses.
     * 
     * @param thesis The Thesis object to be added.
     * @return "success" if the thesis is successfully added.
     *         "not-found" if the library or category is not found
     *         "duplicate-id" if a thesis with the same ID already exists in the
     *         library.
     */
    public String addThesis(Thesis thesis) {
        Library library = libraries.get(thesis.getLibraryId());
        Category category = categories.get(thesis.getCategoryId());
        if (library == null || category == null) {
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
    public String editThesis(Thesis thesis) {
        Library library = libraries.get(thesis.getLibraryId());
        Category category = categories.get(thesis.getCategoryId());
        Thesis thesis1 = library.getThesis(thesis.getThesisID());
        if (library == null || category == null || thesis1 == null) {
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
    public String removeThesis(String thesisId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null || library.getThesis(thesisId) == null) {
            return "not-found";
        }
        if (library.countDocs(thesisId) != 0) {
            return "not-allowed";
        }

        library.removeThesis(libraryId);
        return "success";
    }

    // !----------------------------------------------------------------- STUDENT
    /**
     * Adds a new Student object to the collection of students.
     * 
     * @param student The Student object to be added.
     * @return "success" if the student is successfully added
     *         "duplicate-id" if a student with the same ID already exists.
     */
    public String addStudent(Student student) {
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
    public String editStudent(Student student) {
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
    public String removeStudent(String studentId) {
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
     * Adds a new Staff object to the collection of staff members.
     * 
     * @param staff The Staff object to be added.
     * @return "success" if the staff member is successfully added
     *         "duplicate-id" if a staff member with the same ID already exists.
     */
    public String addStaff(Staff staff) {
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
    public String editStaff(Staff staff) {
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
    public String removeStaff(String staffId) {
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
    public String borrow(Borrow borrow, String password) {
        if (!borrow.checkUser(new HashSet<>(students.keySet()), new HashSet<>(staffs.keySet()))) {
            return "not-found";
        }
        if (borrow.isStudent()) {
            Student student = students.get(borrow.getUserId());
            if (!student.getPassword().equals(password)) {
                return "invalid-pass";
            }
        } else {
            Staff staff = staffs.get(borrow.getUserId());
            if (!staff.getPassword().equals(password)) {
                return "invalid-pass";
            }
        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        if (!borrow.checkDoc(library.getBookIds(), library.getThesisIds())) {
            return "not-found";
        }
        if (!library.borrow(borrow, countBorrow(borrow.getUserId()))) {
            return "not-allowed";
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
    public String returning(Borrow borrow, String pass) {
        if (!borrow.checkUser(new HashSet<>(students.keySet()), new HashSet<>(staffs.keySet()))) {
            return "not-found";
        }
        if (borrow.isStudent()) {
            Student student = students.get(borrow.getUserId());
            if (!student.getPassword().equals(pass)) {
                return "invalid-pass";
            }

        } else {
            Staff staff = staffs.get(borrow.getUserId());
            if (!staff.getPassword().equals(pass)) {
                return "invalid-pass";
            }
        }
        Library library = libraries.get(borrow.getLibraryId());
        if (library == null) {
            return "not-found";
        }
        if (!borrow.checkDoc(library.getBookIds(), library.getThesisIds())) {
            return "not-found";
        }
        Borrow borrowHelp = library.checkUserBorrows(borrow.getUserId(), borrow.getStuffId());
        if (borrowHelp == null) {
            return "not-found";
        }
        int debt = library.returning(borrowHelp, borrow.getDate());
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
     * Searches for the given key across all libraries and generates a sorted list
     * of search results.
     * 
     * @param key The search key to look for in the libraries.
     * @return A StringBuilder containing the sorted search results separated by "|"
     *         characters, or "not-found" if no results are found.
     */
    public StringBuilder search(String key) {
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
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
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
    public StringBuilder searchUser(String userId, String pass, String key) {

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

        return searchUser(key);
    }

    /**
     * Searches for the given key among students' and staff members' first and last
     * names, and generates a sorted list of matching user IDs.
     * 
     * @param key The search key to look for in the user names.
     * @return A StringBuilder containing the sorted list of user IDs separated by
     *         "|" characters, or "not-found" if no matching users are found.
     */
    private StringBuilder searchUser(String key) {
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
        searchID.deleteCharAt(searchID.length() - 1);
        return searchID;
    }

    // !----------------------------------------------------------------- REPORT
    /**
     * Generates a report of the total penalties accrued by both staff and students.
     * 
     * @return A String representation of the total penalties accrued by staff and
     *         students.
     */
    public String reportPenalties() {
        int Penalties = 0;
        for (Staff staff : staffs.values()) {
            Penalties += staff.getDebt();
        }
        for (Student student : students.values()) {
            Penalties += student.getDebt();
        }
        return "" + Penalties;
    }

    // public String categoryReport(String categoryId) {
    // Point hold;
    // Point output = new Point();

    // if (categories.get(categoryId) == null) {
    // return "not-found";
    // }
    // for (Library library : libraries.values()) {
    // hold = library.categoryReport(categoryId);
    // output.x += hold.x;
    // output.y += hold.y;
    // }
    // return output.x + " " + output.y;
    // }

    /**
     * Generates a report for the specified library.
     * 
     * @param libraryId The unique identifier of the library for which the report is
     *                  requested.
     * @return A String representation of the report for the specified library, or
     *         "not-found" if the library is not found.
     */
    public String libraryReport(String libraryId) {
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
    public StringBuilder reportPasseDeadline(String libraryId, Date date) {
        StringBuilder output;
        Library library = libraries.get(libraryId);
        if (library == null) {
            return new StringBuilder("not-found");
        }
        output = library.reportPassedDeadline(date);
        if (output.length() == 0) {
            return new StringBuilder("none");
        }
        output.deleteCharAt(output.length() - 1);
        return output;
    }

}
