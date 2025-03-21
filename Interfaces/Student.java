public class Student implements Comparable<Student> {
    private String firstName, lastName;
    private int studentID;

    public Student(String firstName, String lastName, int studentID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.studentID = studentID;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setStudentID(int studentID) {
        if (studentID >= 0 && studentID <= 9999) {
            this.studentID = studentID;
        }
    }

    @Override
    public String toString() {
        return lastName + ", " + firstName + " (" + studentID + ")";
    }

    @Override
    public int compareTo(Student other) {
        return Integer.compare(this.studentID, other.studentID);
    }

    @Override
    public boolean equals(Object other) {
        if (other instanceof Student) {
            Student otherStudent = (Student) other;
            return this.firstName == otherStudent.firstName && this.lastName == otherStudent.lastName && this.studentID == otherStudent.studentID;
        }
        return false;
    }
}

