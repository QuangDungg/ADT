import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

class Student {
    String id;
    String name;
    double marks;

    public Student(String id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Marks: " + marks;
    }
}

class StudentManagementSystem {
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Added: " + student);
    }

    public void deleteStudent(String id) {
        boolean removed = students.removeIf(student -> student.id.equals(id));
        if (removed) {
            System.out.println("Deleted student with ID: " + id);
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    public Optional<Student> searchStudent(String id) {
        return students.stream().filter(student -> student.id.equals(id)).findFirst();
    }
    
     public boolean editStudent(String id, String newName, double newMarks) {
        Optional<Student> optionalStudent = searchStudent(id);
        if (optionalStudent.isPresent()) {
            Student student = optionalStudent.get();
            student.setName(newName);
            student.setMarks(newMarks);
            System.out.println("Updated student: " + student);
            return true;
        } else {
            System.out.println("Student with ID " + id + " not found.");
            return false;
        }
    }

    public void displayStudents() {
        if (students.isEmpty()) {
            System.out.println("No students to display.");
        } else {
            System.out.println("Student List:");
            for (Student student : students) {
                System.out.println(student);
            }
        }
    }

    // Bubble Sort Implementation
    public void bubbleSort(boolean ascending) {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if ((ascending && students.get(j).marks > students.get(j + 1).marks) ||
                    (!ascending && students.get(j).marks < students.get(j + 1).marks)) {
                    Student temp = students.get(j);
                    students.set(j, students.get(j + 1));
                    students.set(j + 1, temp);
                }
            }
        }
        System.out.println("Students sorted using Bubble Sort in " + (ascending ? "ascending" : "descending") + " order.");
    }



    private int partition(int low, int high, boolean ascending) {
        Student pivot = students.get(high);
        int i = (low - 1);

        for (int j = low; j < high; j++) {
            if ((ascending && students.get(j).marks <= pivot.marks) ||
                (!ascending && students.get(j).marks >= pivot.marks)) {
                i++;
                Student temp = students.get(i);
                students.set(i, students.get(j));
                students.set(j, temp);
            }
        }

        Student temp = students.get(i + 1);
        students.set(i + 1, students.get(high));
        students.set(high, temp);

        return i + 1;
    }

    // Selection Sort Implementation
//    public void selectionSort(boolean ascending) {
//        int n = students.size();
//        for (int i = 0; i < n - 1; i++) {
//            int selectedIdx = i;
//            
//            for (int j = i + 1; j < n; j++) {
//                if ((ascending && students.get(j).marks < students.get(selectedIdx).marks) ||
//                    (!ascending && students.get(j).marks > students.get(selectedIdx).marks)) {
//                    selectedIdx = j;
//                }
//            }
//
//            Student temp = students.get(selectedIdx);
//            students.set(selectedIdx, students.get(i));
//            students.set(i, temp);
//        }
//        System.out.println("Students sorted using Selection Sort in " + (ascending ? "ascending" : "descending") + " order.");
//    }

    public void sortStudents(String algorithm, boolean ascending) {
        switch (algorithm.toLowerCase()) {
            case "bubble":
                bubbleSort(ascending);
                break;
            
            
            default:
                System.out.println("Invalid sorting algorithm specified.");
                break;
        }
    }
}

  class Main {
    private static StudentManagementSystem sms = new StudentManagementSystem();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean exit = false;
        while (!exit) {
            showMenu();
            try {
                int choice = getChoice();
                switch (choice) {
                    case 1:
                        addStudentUI();
                        break;
                    case 2:
                        deleteStudentUI();
                        break;
                    case 3:
                        searchStudentUI();
                        break;
                    case 4:
                        editStudentUI();
                        break;
                    case 5:
                        sortStudentsUI();
                        break;
                    case 6:
                        sms.displayStudents();
                        break;
                    case 7:
                        exit = true;
                        System.out.println("Exiting the system. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (Exception e) {
                System.out.println("An unexpected error occurred. Please try again.");
                scanner.nextLine(); // Clear the scanner buffer
            }
        }
        scanner.close();
    }

        private static void showMenu() {
        System.out.println("\n=== Student Management System ===");
        System.out.println("1. Add Student");
        System.out.println("2. Delete Student");
        System.out.println("3. Search Student by ID");
        System.out.println("4. Edit Student");
        System.out.println("5. Sort Students");
        System.out.println("6. Display All Students");
        System.out.println("7. Exit");
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
        try {
            System.out.print("Enter Student ID: ");
            String id = scanner.nextLine().trim();
            
            String name;
            while (true) {
                System.out.print("Enter Student Name (letters only): ");
                name = scanner.nextLine().trim();
                if (name.matches("[a-zA-Z ]+")) {
                    break;
                } else {
                    System.out.println("Invalid name. Please enter letters only.");
                }
            }

            double marks = -1;
            while (true) {
                System.out.print("Enter Student Marks (0 - 10): ");
                try {
                    marks = Double.parseDouble(scanner.nextLine());
                    if (marks < 0 || marks > 10) {
                        System.out.println("Marks must be between 0 and 10. Please try again.");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input for marks. Please enter a number between 0 and 10.");
                }
            }
            Student student = new Student(id, name, marks);
            sms.addStudent(student);
        } catch (Exception e) {
            System.out.println("Error adding student. Please try again.");
        }
    }

    private static void deleteStudentUI() {
        System.out.print("Enter Student ID to delete: ");
        String id = scanner.nextLine().trim();
        sms.deleteStudent(id);
    }
    
     private static void editStudentUI() {
        try {
            System.out.print("Enter Student ID to edit: ");
            String id = scanner.nextLine().trim();
            Optional<Student> optionalStudent = sms.searchStudent(id);
            if (optionalStudent.isPresent()) {
                System.out.print("Enter new name (current: " + optionalStudent.get().name + "): ");
                String newName = scanner.nextLine().trim();

                double newMarks = -1;
                while (true) {
                    System.out.print("Enter new marks (0 - 10) (current: " + optionalStudent.get().marks + "): ");
                    try {
                        newMarks = Double.parseDouble(scanner.nextLine());
                        if (newMarks < 0 || newMarks > 10) {
                            System.out.println("Marks must be between 0 and 10. Please try again.");
                        } else {
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input for marks. Please enter a number between 0 and 10.");
                    }
                }
                
                sms.editStudent(id, newName, newMarks);
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            System.out.println("Error editing student. Please try again.");
        }
    }

    private static void searchStudentUI() {
        System.out.print("Enter Student ID to search: ");
        String id = scanner.nextLine().trim();
        Optional<Student> optionalStudent = sms.searchStudent(id);
        if (optionalStudent.isPresent()) {
            System.out.println("Found student: " + optionalStudent.get());
        } else {
            System.out.println("Student with ID " + id + " not found.");
        }
    }

    private static void sortStudentsUI() {
        
        String algorithm = scanner.nextLine().trim();
        System.out.print("Sort in ascending order? (yes/no): ");
        String order = scanner.nextLine().trim();
        boolean ascending = order.equalsIgnoreCase("yes");

        sms.sortStudents(algorithm, ascending);
    }
}
