import java.io.*;
import java.util.*;

public class Main {
    static final String FILE_NAME = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ArrayList<Student> students = loadStudentsFromFile();

        System.out.println("Student Grade Management System (File-Based)");

        while (true) {
            System.out.println("\n1. Add Student\n2. View Students\n3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter student name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter number of subjects: ");
                    int subjects = scanner.nextInt();
                    int[] grades = new int[subjects];
                    for (int i = 0; i < subjects; i++) {
                        System.out.print("Enter grade for subject " + (i + 1) + ": ");
                        grades[i] = scanner.nextInt();
                    }

                    Student newStudent = new Student(name, grades);
                    students.add(newStudent);
                    saveStudentToFile(newStudent);
                }

                case 2 -> {
                    for (Student s : students) {
                        s.printDetails();
                        System.out.println();
                    }
                }

                case 3 -> {
                    System.out.println("Exiting...");
                    return;
                }

                default -> System.out.println("Invalid choice.");
            }
        }
    }

    static void saveStudentToFile(Student student) {
        try (FileWriter fw = new FileWriter(FILE_NAME, true);
             BufferedWriter bw = new BufferedWriter(fw);
             PrintWriter out = new PrintWriter(bw)) {
            out.println(student.toFileString());
        } catch (IOException e) {
            System.out.println("Error saving student: " + e.getMessage());
        }
    }

    static ArrayList<Student> loadStudentsFromFile() {
        ArrayList<Student> students = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                String name = parts[0];
                int[] grades = new int[parts.length - 1];
                for (int i = 1; i < parts.length; i++) {
                    grades[i - 1] = Integer.parseInt(parts[i]);
                }
                students.add(new Student(name, grades));
            }
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found, starting fresh.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
        return students;
    }
}
