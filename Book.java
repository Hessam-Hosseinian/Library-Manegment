
public class Book {

    private String bookId;
    private String title;
    private String author;
    private String publisher;
    private String publicationYear;
    private int copyNumber;
    private String categoryId;
    private String libraryId;

    public Book(String bookId, String title, String author, String publisher, String publicationYear, int copyNumber,
            String categoryId, String libraryId) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publicationYear = publicationYear;
        this.copyNumber = copyNumber;
        this.categoryId = categoryId;
        this.libraryId = libraryId;
    }

    public void edit(Book book) {
        if (!book.getTitle().equals("-")) {
            setTitle(book.getTitle());
        }
        if (!book.getAuthor().equals("-")) {
            setAuthor(book.getAuthor());
        }
        if (!book.getPublisher().equals("-")) {
            setPublisher(book.getPublisher());
        }
        if (!book.getPublicationYear().equals("-")) {
            setPublicationYear(book.getPublicationYear());
        }
        if (book.getCopyNumber() != -1) {
            setCopyNumber(book.getCopyNumber());
        }
        if (book.getCategoryId().equals("-")) {
            setCategoryId(book.getCategoryId());
        }
    }

    public String getBookId() {
        return this.bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getPublicationYear() {
        return this.publicationYear;
    }

    public void setPublicationYear(String publicationYear) {
        this.publicationYear = publicationYear;
    }

    public int getCopyNumber() {
        return this.copyNumber;
    }

    public void setCopyNumber(int copyNumber) {
        this.copyNumber = copyNumber;
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

}
