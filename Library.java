
import java.sql.Date;
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

    public int countBorrows(String userId) {
        int count = 0;
        for (ArrayList<Borrow> stuffBorrows : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : stuffBorrows) {
                if (borrow.getUserId().equals(userId)) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countStuffs(String stuffId) {
        ArrayList<Borrow> myBorrow = borrows.get(stuffId);
        if (myBorrow == null) {
            return 0;
        }
        return myBorrow.size();
    }

    public boolean isAllowed(Borrow borrow) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getStuffId());
        if (borrows1 == null) {
            borrows1 = new ArrayList<>();

        }
        if (borrow.isBook()) {
            if (countStuffs(borrow.getStuffId()) < books.get(borrow.getStuffId()).getCopyNumber()) {
                borrows1.add(borrow);
                borrows.put(borrow.getStuffId(), borrows1);
                return true;
            }
            return false;
        }
        if (countStuffs(borrow.getStuffId()) == 0) {
            borrows1.add(borrow);
            borrows.put(borrow.getStuffId(), borrows1);
            return true;
        }
        return false;
    }

    public Borrow checkUserBorrows(String userId) {
        Borrow borr = null;
        for (ArrayList<Borrow> borrows1 : new ArrayList<>(borrows.values())) {
            for (Borrow borrow : borrows1) {
                if (borrow.getUserId().equals(userId)) {
                    if (borr == null) {
                        borr = borrow;
                    } else if (borr.getDate().getTime() < borrow.getDate().getTime()) {
                        borr = borrow;
                    }
                }
            }
        }
        return borr;
    }

    public Borrow checkUserBorrows(String userId, String stuffId) {
        Borrow borr = null;
        ArrayList<Borrow> hold = borrows.get(stuffId);
        if (hold == null) {
            return null;
        }
        for (Borrow borrow : hold) {
            if (borrow.getUserId().equals(userId)) {
                if (borr == null) {
                    borr = borrow;
                } else if (borr.getDate().getTime() < borrow.getDate().getTime()) {
                    borr = borrow;
                }
            }
        }
        return borr;
    }

    public int checkDebt(Borrow borrow, java.util.Date date) {
        long firstMin = borrow.getDate().getTime() / 3600000;
        long secondMin = date.getTime() / 3600000;
        long periodTime = secondMin - firstMin;
        if (borrow.isStudent()) {
            if (borrow.isBook()) {
                if (periodTime < (10 * 24)) {
                    return 0;
                }
                return (int) ((periodTime - (10 * 24)) * 50);
            }
            if (periodTime < (7 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (7 * 24)) * 50);
        }
        if (borrow.isBook()) {
            if (periodTime < (14 * 24)) {
                return 0;
            }
            return (int) ((periodTime - (14 * 24)) * 100);
        }
        if (periodTime < (10 * 24)) {
            return 0;
        }
        return (int) ((periodTime - (10 * 24)) * 100);
    }

    public int returning(Borrow borrow, java.util.Date date) {
        ArrayList<Borrow> borrows1 = borrows.get(borrow.getStuffId());
        int debt = checkDebt(borrow, date);
        borrows1.remove(borrow);
        return debt;
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