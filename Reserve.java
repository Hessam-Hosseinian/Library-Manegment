import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;

//?----------------------------------------------------------------------------------------------------------------------
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
    // ?----------------------------------------------------------------------------------------------------------------------
    // !------------------------------------------------------------------------------

    /**
     * Checks whether a user exists in either the student or staff set based on the
     * provided user ID.
     *
     * @param studentIds A HashSet containing student IDs to be checked.
     * @param staffIds   A HashSet containing staff IDs to be checked.
     * @return True if the user ID exists in either the student or staff set, false
     *         otherwise.
     */
    public boolean checkUser(HashSet<String> studentIds, HashSet<String> staffIds) {

        if (studentIds.contains(userId)) {

            this.isStudent = true;// set reserve as student`s reserve
            return true; // return true that means there is a user with that Id
        }

        else if (staffIds.contains(userId)) {

            this.isStudent = false;// set reserve as staff`s reserve
            return true; // return true that means there is a user with that Id
        }

        return false;// return false that means there is no user with that Id
    }

    // !------------------------------------------------------------------------------
    /**
     * Counts the duration between the start and end times and checks if it exceeds
     * 8 hours.
     *
     * @return True if the duration between start and end times exceeds 8 hours,
     *         false otherwise.
     */
    public boolean countDuration() {

        LocalTime t1 = LocalTime.parse(startDate);
        LocalTime t2 = LocalTime.parse(endDate);

        long periodTime = t2.getHour() * 60 + t2.getMinute() - t1.getHour() * 60 - t1.getMinute();

        if (periodTime > 8 * 60) {

            return true; // Not allowed
        }

        return false; // Allowed
    }
    // !------------------------------------------------------------------------------

    // ?----------------------------------------------------------------------------------------------------------------------

    // -------------------------------------------- Getters and Setters

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

    // -------------------------------------------- Getters and Setters

}
// ?----------------------------------------------------------------------------------------------------------------------
