
//?----------------------------------------------------------------------------------------------------------------------
public class Staff {

    private String StaffId;
    private String password;
    private String firstName;
    private String lastName;
    private String codeId;
    private String birthday;
    private String address;
    private int debt;

    public Staff(String StaffId, String password, String firstName, String lastName, String codeId, String birthday,
            String address) {
        this.StaffId = StaffId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.codeId = codeId;
        this.birthday = birthday;
        this.address = address;
        this.debt = 0;
    }
    // ?----------------------------------------------------------------------------------------------------------------------
    // !------------------------------------------------------------------------------

    /**
     * Edits the information of a staff member based on the provided Staff object.
     * If a field in the provided Staff object is not equal to "-", it updates the
     * corresponding field in this object.
     * This method allows partial updates of staff information.
     *
     * @param staff The Staff object containing the updated information.
     *              If a field in this object is not equal to "-", it will be
     *              updated in this object.
     */
    public void edit(Staff staff) {
        if (!staff.getPassword().equals("-")) {
            setPassword(staff.getPassword());
        }
        if (!staff.getFirstName().equals("-")) {
            setFirstName(staff.getFirstName());
        }
        if (!staff.getLastName().equals("-")) {
            setLastName(staff.getLastName());
        }
        if (!staff.getCodeId().equals("-")) {
            setCodeId(staff.getCodeId());
        }
        if (!staff.getBirthday().equals("-")) {
            setBirthday(staff.getBirthday());
        }
        if (!staff.getAddress().equals("-")) {
            setAddress(staff.getAddress());
        }
    }
    // !------------------------------------------------------------------------------

    // ?----------------------------------------------------------------------------------------------------------------------

    // ----------------------------------- Seters and Geters

    public String getStaffId() {
        return this.StaffId;
    }

    public void setStaffId(String StaffId) {
        this.StaffId = StaffId;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCodeId() {
        return this.codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;

    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getDebt() {
        return this.debt;
    }

    public void setDebt(int debt) {
        this.debt += debt; // increse the debt
    }
    // ----------------------------------- Seters and Geters

}
// ?----------------------------------------------------------------------------------------------------------------------
