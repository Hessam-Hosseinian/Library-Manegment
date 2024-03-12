public class Student {
    private String studentId;
    private String password;
    private String firstName;
    private String lastName;
    private String codeId;
    private String birthday;
    private String address;
    private int debt;

    public Student(String studentId, String password, String firstName, String lastName, String codeId, String birthday,
            String address) {
        this.studentId = studentId;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.codeId = codeId;
        this.birthday = birthday;
        this.address = address;
        this.debt = 0;
    }

    public void edit(Student student) {
        if (!student.getPassword().equals("-")) {
            setPassword(student.getPassword());
        }
        if (!student.getFirstName().equals("-")) {
            setFirstName(student.getFirstName());
        }
        if (!student.getLastName().equals("-")) {
            setLastName(student.getLastName());
        }
        if (!student.getCodeId().equals("-")) {
            setCodeId(student.getCodeId());
        }
        if (!student.getBirthday().equals("-")) {
            setBirthday(student.getBirthday());
        }
        if (!student.getAddress().equals("-")) {
            setAddress(student.getAddress());
        }
    }

    public String getStudentId() {
        return this.studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
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
        this.debt += debt;
    }

}
