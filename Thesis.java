//?----------------------------------------------------------------------------------------------------------------------

public class Thesis {
    private String thesisID;
    private String title;
    private String studentName;
    private String professorName;
    private String date;
    private String categoryId;
    private String libraryId;

    public Thesis(String thesisID, String title, String studentName, String professorName, String date,
            String categoryId, String libraryId) {
        this.thesisID = thesisID;
        this.title = title;
        this.studentName = studentName;
        this.professorName = professorName;
        this.date = date;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
    }
    // ?----------------------------------------------------------------------------------------------------------------------
    // !------------------------------------------------------------------------------

    public void edit(Thesis thesis) {
        if (!thesis.getTitle().equals("-")) {
            setTitle(thesis.getTitle());
        }
        if (!thesis.getStudentName().equals("-")) {
            setStudentName(thesis.getStudentName());
        }
        if (!thesis.getProfessorName().equals("-")) {
            setProfessorName(thesis.getProfessorName());
        }
        if (!thesis.getDate().equals("-")) {
            setDate(thesis.getDate());
        }
        if (!thesis.getCategoryId().equals("-")) {
            setCategoryId(thesis.getCategoryId());
        }
    }
    // !------------------------------------------------------------------------------
    // ?----------------------------------------------------------------------------------------------------------------------
    // -------------------------------------------- Getters and Setters

    public String getThesisID() {
        return this.thesisID;
    }

    public void setThesisID(String thesisID) {
        this.thesisID = thesisID;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStudentName() {
        return this.studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getProfessorName() {
        return this.professorName;
    }

    public void setProfessorName(String professorName) {
        this.professorName = professorName;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getLibraryId() {
        return this.libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }
    // -------------------------------------------- Getters and Setters

}
// ?----------------------------------------------------------------------------------------------------------------------
