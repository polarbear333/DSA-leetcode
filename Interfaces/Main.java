// Main class to test the Student class
public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Amy", "Chan", 1);
        Student student2 = new Student("Alice", "Smith", 1234);
        Student student3 = new Student("Alice", "Smith", 1234);


        System.out.println(student1);
        student1.setStudentID(1234);
        System.out.println(student1.equals(student2));
        System.out.println(student1.compareTo(student2));
        System.out.println(student3.getFirstName());

    }
}