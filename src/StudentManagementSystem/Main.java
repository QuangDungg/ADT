import java.util.Optional;
import java.util.Scanner;

public class Main {
    private static StudentManagementSystem sms = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = getChoice();
            switch (choice) {
                case 1:
                    addStudentUI();
                    break;
                case 2:
                    displayStudentsUI();
                    break;
                case 3:
                    sortStudentsUI();
                    break;
                case 4:
                    searchStudentUI();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Display Students");
        System.out.println("3. Sort Students by ID (Selection Sort)");
        System.out.println("4. Search Student by ID (Binary Search)");
        System.out.println("5. Exit");
        System.out.print("Enter your choice: ");
    }

    private static int getChoice() {
        int choice = -1;
        try {
            choice = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
        }
        return choice;
    }

    private static void addStudentUI() {
        System.out.print("Enter Student ID: ");
        String id = scanner.nextLine();
        System.out.print("Enter Student Name: ");
        String name = scanner.nextLine();
        double marks = -1;
        while (marks < 0 || marks > 10) {
            try {
                System.out.print("Enter Student Marks (0 - 10): ");
                marks = Double.parseDouble(scanner.nextLine());
                if (marks < 0 || marks > 10) {
                    System.out.println("Marks must be between 0 and 10.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid marks. Please enter a number.");
            }
        }
        sms.addStudent(new Student(id, name, marks));
    }

    private static void displayStudentsUI() {
        sms.displayStudents();
    }

    private static void sortStudentsUI() {
        sms.selectionSortByID();
    }

    private static void searchStudentUI() {
        System.out.print("Enter Student ID to search: ");
        String targetID = scanner.nextLine();
        Optional<Student> student = sms.binarySearchByID(targetID);
        student.ifPresentOrElse(
            s -> System.out.println("Found: " + s),
            () -> System.out.println("Student with ID " + targetID + " not found.")
        );
    }
}
