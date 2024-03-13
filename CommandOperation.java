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

        // else if (input.contains("category-report")) {
        // categoryReport(args[0]);
        // }

        else if (input.contains("report-passed-deadline")) {
            reportPasseDeadline(args[0], args[1], args[2]);
        }

    }
    // ----------------------------------------------------------------------
    // !----------------------------------------------------------------- LIBRARY

    public void addLibrary(String libraryId, String libraryName, String foundationYear, int deskNumber,
            String address) {
        Library library = new Library(libraryId, libraryName, foundationYear, deskNumber, address);
        System.out.println(manegment.addLibrary(library));

    }
    // !----------------------------------------------------------------- CATEGORY

    public void addCategory(String categoryId, String categoryName) {
        Category category = new Category(categoryId, categoryName);
        System.out.println(manegment.addCategory(category));
    }

    // !----------------------------------------------------------------- BOOK

    public void addBook(String id, String name, String authorName, String publisher, String year, int numBook,
            String categoryId, String libraryId) {
        Book book = new Book(id, name, authorName, publisher, year, numBook,
                categoryId, libraryId);
        System.out.println(manegment.addBook(book));
    }

    public void editBook(String id, String libraryId, String name, String authorName, String publisher, String year,
            int numBook, String categoryId) {

        Book book = new Book(id, name, authorName, publisher, year, numBook,
                categoryId, libraryId);

        System.out.println(manegment.editBook(book));
    }

    public void removeBook(String bookId, String libraryId) {
        System.out.println(manegment.removeBook(bookId, libraryId));
    }

    // !----------------------------------------------------------------- THESIS

    public void addThesis(String id, String name, String studentName, String advisor, String year, String categoryId,
            String libraryId) {
        Thesis thesis = new Thesis(id, name, studentName, advisor, year, categoryId,
                libraryId);
        System.out.println(manegment.addThesis(thesis));
    }

    public void editThesis(String id, String libraryId, String name, String studentName, String advisor, String year,
            String categoryID) {
        Thesis thesis = new Thesis(id, name, studentName, advisor, year, categoryID,
                libraryId);
        System.out.println((manegment.editThesis(thesis)));
    }

    public void removeThesis(String thesisId, String libraryId) {
        System.out.println(manegment.removeThesis(thesisId, libraryId));
    }

    // !----------------------------------------------------------------- STUDENT

    public void addStudent(String studentId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Student student = new Student(studentId, password, firstName, lastName, codeId,
                birthday, address);
        System.out.println(manegment.addStudent(student));
    }

    public void editStudent(String studentId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Student student = new Student(studentId, password, firstName, lastName, codeId,
                birthday, address);
        System.out.println(manegment.editStudent(student));
    }

    public void removeStudent(String studentId) {
        System.out.println(manegment.removeStudent(studentId));
    }

    // !----------------------------------------------------------------- STAFF

    public void addStaff(String StaffId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Staff staff = new Staff(StaffId, password, firstName, lastName, codeId, birthday,
                address);
        System.out.println(manegment.addStaff(staff));
    }

    public void editStaff(String StaffId, String password, String firstName, String lastName, String codeId,
            String birthday,
            String address) {
        Staff staff = new Staff(StaffId, password, firstName, lastName, codeId, birthday,
                address);
        System.out.println(manegment.editStaff(staff));
    }

    public void removeStaff(String StaffId) {
        System.out.println(manegment.removeStaff(StaffId));
    }

    // !----------------------------------------------------------------- BORROW

    public void borrow(String userId, String password, String libraryId, String stuffId, String strDate, String hour)
            throws ParseException {

        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());

        Borrow borrow = new Borrow(date, userId, stuffId, libraryId);

        System.out.println(manegment.borrow(borrow, password));

    }

    public void returning(String userId, String pass, String libraryId, String stuffId, String strDate, String hour)
            throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        Borrow borrow = new Borrow(date, userId, stuffId, libraryId);
        System.out.println(manegment.returning(borrow, pass));
    }

    // !----------------------------------------------------------------- SEARCH

    public void search(String key) {
        System.out.println(manegment.search(key));
    }

    public void searchUser(String userId, String pass, String key) {

        System.out.println(manegment.searchUser(userId, pass, key));
    }

    // !----------------------------------------------------------------- REPORT

    public void reportPenalties() {
        System.out.println(manegment.reportPenalties());
    }

    // public void categoryReport(String categoryId) {
    // System.out.println(manegment.categoryReport(categoryId));
    // }

    public void libraryReport(String libraryId) {
        System.out.println(manegment.libraryReport(libraryId));
    }

    public void reportPasseDeadline(String libraryId, String strDate, String hour) throws ParseException {
        java.util.Date utilDate = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(strDate + " " + hour);

        Date date = new Date(utilDate.getTime());
        System.out.println(manegment.reportPasseDeadline(libraryId, date));
    }
}