import java.util.ArrayList;
import java.util.HashMap;

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
    }

    public Library getLibrary(String libraryId) {
        return libraries.get(libraryId);
    }

    public String addLibrary(Library library) {
        if (libraries.get(library.getLibraryId()) != null) {
            return "duplicate-id";
        }
        libraries.put(library.getLibraryId(), library);
        return "success";
    }

    public String addCategory(Category category) {
        if (categories.get(category.getCategoryId()) != null) {
            return "duplicate-id";
        }
        categories.put(category.getCategoryId(), category);
        return "success";
    }

    public String addBook(Book book) {
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

    public String editBook(Book book) {
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

    public String addThesis(Thesis thesis) {
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

    public String editThesis(Thesis thesis) {
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

    public String removeBook(String bookId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        // if (library.countStuffs(bookId) != 0) {
        // return "not-allowed";
        // }
        if (library.getBook(bookId) == null) {
            return "not-found";
        }
        library.removeBook(bookId);
        return "success";
    }

    public String removeThesis(String thesisId, String libraryId) {
        Library library = libraries.get(libraryId);
        if (library == null) {
            return "not-found";
        }
        // if (library.countStuffs(thesisId) != 0) {
        // return "not-allowed";
        // }
        if (library.getThesis(thesisId) == null) {
            return "not-found";
        }
        library.removeThesis(libraryId);
        return "success";
    }

    public String addStudent(Student student) {
        if (students.get(student.getStudentId()) != null) {
            return "duplicate-id";
        }
        students.put(student.getStudentId(), student);
        return "success";
    }

    public String editStudent(Student student) {
        Student student1 = students.get(student.getStudentId());
        if (student1 == null) {
            return "not-found";
        }
        student1.edit(student);
        return "success";
    }

    // TODO:
    // // this method need another condition to say not-allowed
    // public String removeStudent(String studentId) {
    // Student student = students.get(studentId);
    // if (student == null) {
    // return "not-found";
    // }
    // for (Library library : new ArrayList<>(libraries.values())) {
    // if (library.checkUserBorrows(studentId) != null) {
    // return "not-allowed";
    // }
    // }
    // if (student.getDebt() != 0) {
    // return "not-allowed";
    // }
    // students.remove(studentId);
    // return "success";
    // }

    public String addStaff(Staff staff) {
        if (staffs.get(staff.getStaffId()) != null) {
            return "duplicate-id";
        }
        staffs.put(staff.getStaffId(), staff);
        return "success";
    }

    public String editStaff(Staff staff) {
        Staff staff1 = staffs.get(staff.getStaffId());
        if (staff1 == null) {
            return "not-found";
        }
        staff1.edit(staff);
        return "success";
    }

    // // TODO:
    // // this method need another condition to say not-allowed
    // public String removeStaff(String id) {
    // Staff staff = staffs.get(id);
    // if (staff == null) {
    // return "not-found";
    // }
    // if (staff.getDebt() != 0) {
    // return "not-allowed";
    // }
    // for (Library library : new ArrayList<>(libraries.values())) {
    // if (library.checkUserBorrows(id) != null) {
    // return "not-allowed";
    // }
    // }
    // staffs.remove(id);
    // return "success";
    // }

}
