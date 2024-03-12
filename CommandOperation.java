
public class CommandOperation {
    private Manegment manegment;

    public CommandOperation() {
        this.manegment = new Manegment();
    }

    public void input(String input) {
        String[] command = input.split("#");
        String[] args = new String[] {};
        if (command.length > 1) {
            args = command[1].split("\\|");
        }

        if (input.startsWith("add-library")) {

            addLibrary(args[0], args[1], args[2], Integer.valueOf(args[3]), args[4]);
        }

        else if (input.startsWith("add-category")) {

            addCategory(args[0], args[1]);
        }

        else if (input.startsWith("add-book")) {
            addBook(args[0], args[1], args[2], args[3], args[4], Integer.valueOf(args[5]), args[6], args[7]);
        }

        else if (input.startsWith("edit-book")) {
            if (args[6].equals("-")) {
                editBook(args[0], args[1], args[2], args[3], args[4], args[5], -1, args[7]);
            } else {
                editBook(args[0], args[1], args[2], args[3], args[4], args[5],
                        Integer.valueOf(args[6]), args[7]);
            }
        }

        else if (input.startsWith("remove-book")) {
            removeBook(args[0], args[1]);
        }

        else if (input.startsWith("add-thesis")) {
            addThesis(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        }

        else if (input.startsWith("edit-book")) {
            editThesis(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);
        }

        // case "remove-book":
        // removeBook(args[0], args[1]);
        // break;
        // case "remove-thesis":
        // removeThesis(args[0], args[1]);
        // break;
        else if (input.startsWith("add-student")) {
            addStudent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        } else if (input.startsWith("edit-student")) {
            editStudent(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        // case "remove-student":
        // removeStudent(args[0]);
        // break;
        else if (input.startsWith("add-staff")) {
            addStaff(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        else if (input.startsWith("edit-staff")) {
            editStaff(args[0], args[1], args[2], args[3], args[4], args[5], args[6]);

        }

        // case "remove-staff":
        // removeStaff(args[0]);
        // break;
        // case "borrow":
        // borrow(args[0], args[1], args[2], args[3], args[4], args[5]);
        // break;
        // case "return":
        // returning(args[0], args[1], args[2], args[3], args[4], args[5]);
        // break;
        // case "search":
        // search(args[0]);
        // break;
        // case "search-user":
        // searchUser(args[0], args[1], args[2]);
        // break;
        // case "report-penalties-sum":
        // reportPenalties();
        // break;
        // case "library-report":
        // libraryReport(args[0]);
        // break;
        // case "category-report":
        // categoryReport(args[0]);
        // break;
        // case "report-passed-deadline":
        // reportPasseDeadline(args[0], args[1], args[2]);
        // break;
        // }

    }

    public void addLibrary(String libraryId, String libraryName, String foundationYear, int deskNumber,
            String address) {

        Library library = new Library(libraryId, libraryName, foundationYear, deskNumber, address);
        System.out.println(manegment.addLibrary(library));

    }

    public void addCategory(String categoryId, String categoryName) {
        Category category = new Category(categoryId, categoryName);
        System.out.println(manegment.addCategory(category));
    }

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

    public void removeBook(String bookId, String libraryId) {
        System.out.println(manegment.removeBook(bookId, libraryId));
    }

    // public void removeThesis(String thesisId, String libraryId) {
    // System.out.println(center.removeThesis(thesisId, libraryId));
    // }

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

    // public void removeStudent(String studentId) {
    // System.out.println(manegment.removeStudent(studentId));
    // }

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

    // public void removeStaff(String id) {
    // System.out.println(center.removeStaff(id));
    // }

    // public void borrow(String userId, String pass, String libraryId, String
    // stuffId, String strDate, String hour)
    // throws ParseException {
    // Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " +
    // hour);
    // Borrow borrow = new Borrow(date, userId, stuffId, libraryId);
    // System.out.println(center.borrow(borrow, pass));
    // }

    // public void returning(String userId, String pass, String libraryId, String
    // stuffId, String strDate, String hour)
    // throws ParseException {
    // Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " +
    // hour);
    // Borrow borrow = new Borrow(date, userId, stuffId, libraryId);
    // System.out.println(center.returning(borrow, pass));
    // }

    // public void search(String key) {
    // System.out.println(center.search(key));
    // }

    // public void searchUser(String userId, String pass, String key) {
    // System.out.println(center.searchUser(userId, pass, key));
    // }

    // public void reportPenalties() {
    // System.out.println(center.reportPenalties());
    // }

    // public void categoryReport(String categoryId) {
    // System.out.println(center.categoryReport(categoryId));
    // }

    // public void libraryReport(String libraryId) {
    // System.out.println(center.libraryReport(libraryId));
    // }

    // public void reportPasseDeadline(String libraryId, String strDate, String
    // hour) throws ParseException {
    // Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm").parse(strDate + " " +
    // hour);
    // System.out.println(center.reportPasseDeadline(libraryId, date));
    // }
}