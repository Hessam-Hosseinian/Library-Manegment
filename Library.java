import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

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

}