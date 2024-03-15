import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;

public class Reserve {
    private String libraryId;
    private Date date;
    private String startDate;
    private String endDate;

    private String userId;

    private boolean isStudent;

    public Reserve(String libraryId, Date date, String startDate, String endDate, String userId) {
        this.libraryId = libraryId;
        this.date = date;
        this.startDate = startDate;
        this.endDate = endDate;
        this.userId = userId;
    }

    public boolean checkUser(HashSet<String> studentIds, HashSet<String> staffIds) {
        // 1. Check if userId exists in studentIds or staffIds
        if (studentIds.contains(userId)) {
            this.isStudent = true;
            return true;
        } else if (staffIds.contains(userId)) {
            this.isStudent = false;
            return true;
        }
        return false; // userId not found
    }

    public boolean counthours() {
        // 2. Parse startDate and endDate to LocalTime objects for comparison
        LocalTime t1 = LocalTime.parse(startDate);
        LocalTime t2 = LocalTime.parse(endDate);

        // 3. Calculate the difference in minutes between t1 and t2
        long periodTime = t2.getHour() * 60 + t2.getMinute() - t1.getHour() * 60 - t1.getMinute();

        // 4. Check if the periodTime exceeds 8 hours (480 minutes)
        if (periodTime > 8 * 60) {
            return true; // Not allowed
        }

        return false; // Allowed
    }

    // Getters and Setters

    public boolean isStudent() {
        return isStudent;
    }

    public String getLibraryId() {
        return libraryId;
    }

    public void setLibraryId(String libraryId) {
        this.libraryId = libraryId;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
