import java.util.HashMap;
import java.util.Scanner;

class Student {
    private String name;  
    private int rollNumber; 
    private String className; 
    private HashMap<String, Double> grades; 
    private double GPA; 

    public Student(String name, int rollNumber, String className) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.className = className;
        this.grades = new HashMap<>();
        this.GPA = 0.0;
    }

    public void addGrade(String subject, double grade) {
        grades.put(subject, grade); 
        calculateGPA();  
    }

    public void calculateGPA() {
        double total = 0;
        for (double grade : grades.values()) {
            total += grade;  
        }
        this.GPA = total / grades.size(); 
    }

    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.println("Class: " + className);
        System.out.println("Grades: " + grades);
        System.out.println("GPA: " + GPA);
    }

    public int getRollNumber() {
        return rollNumber;
    }
}

class StudentManager {
    private HashMap<Integer, Student> students; 

    public StudentManager() {
        this.students = new HashMap<>();
    }

    public void addStudent(Student student) {
        students.put(student.getRollNumber(), student); 
    }

    public Student getStudent(int rollNumber) {
        return students.get(rollNumber); 
    }
    public void addGrade(int rollNumber, String subject, double grade) {
        Student student = students.get(rollNumber);
        if (student != null) {
            student.addGrade(subject, grade); 
        } else {
            System.out.println("Student not found.");  
        }
    }

    public void displayAllStudents() {
        for (Student student : students.values()) {
            student.displayDetails();  
            System.out.println("-------------------------");
        }
    }
}

public class ManagementSystem {
    public static void main(String[] args) {
        StudentManager manager = new StudentManager(); 
        Scanner scanner = new Scanner(System.in); 

        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. Add Grade");
            System.out.println("3. View Student Details");
            System.out.println("4. View All Students");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter Name: ");
                    String name = scanner.next();
                    System.out.print("Enter Roll Number: ");
                    int rollNumber = scanner.nextInt();
                    System.out.print("Enter Class: ");
                    String className = scanner.next();
                    Student student = new Student(name, rollNumber, className);
                    manager.addStudent(student);
                    break;
                case 2:
                    System.out.print("Enter Roll Number: ");
                    int roll = scanner.nextInt();
                    System.out.print("Enter Subject: ");
                    String subject = scanner.next();
                    System.out.print("Enter Grade: ");
                    double grade = scanner.nextDouble();
                    manager.addGrade(roll, subject, grade);
                    break;
                case 3:
                    System.out.print("Enter Roll Number: ");
                    int rNumber = scanner.nextInt();
                    Student stu = manager.getStudent(rNumber);
                    if (stu != null) {
                        stu.displayDetails();
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                    manager.displayAllStudents();
                    break;
                case 5:
                    System.out.println("Exiting...");
                    scanner.close(); 
                    System.exit(0);
                default:
                    System.out.println("Invalid choice."); 
            }
        }
    }
}
