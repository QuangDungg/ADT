import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class StudentManagementSystem {
    // LinkedList to store students
    private List<Student> students;

    public StudentManagementSystem() {
        this.students = new LinkedList<>();
    }

    // Add a student to the list
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Added: " + student);
    }

    // Display all students
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

    // Selection Sort to sort students by ID
    public void selectionSortByID() {
        int n = students.size();
        for (int i = 0; i < n - 1; i++) {
            int selectedIdx = i;

            // Find the minimum element in the remaining list
            for (int j = i + 1; j < n; j++) {
                if (students.get(j).id.compareTo(students.get(selectedIdx).id) < 0) {
                    selectedIdx = j;
                }
            }

            // Swap the found minimum element with the current element
            Student temp = students.get(selectedIdx);
            students.set(selectedIdx, students.get(i));
            students.set(i, temp);
        }
        System.out.println("Students sorted by ID using Selection Sort.");
    }

    // Binary Search to find a student by ID
    public Optional<Student> binarySearchByID(String targetID) {
        int left = 0;
        int right = students.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            String midID = students.get(mid).id;

            if (midID.equals(targetID)) {
                return Optional.of(students.get(mid));
            }

            if (midID.compareTo(targetID) < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return Optional.empty();
    }
}
