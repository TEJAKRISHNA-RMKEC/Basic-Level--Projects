public class Student {
    String name;
    int[] grades;

    public Student(String name, int[] grades) {
        this.name = name;
        this.grades = grades;
    }

    public double calculateAverage() {
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return (double) total / grades.length;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder(name);
        for (int grade : grades) {
            sb.append(",").append(grade);
        }
        return sb.toString();
    }

    public void printDetails() {
        System.out.println("Student: " + name);
        for (int i = 0; i < grades.length; i++) {
            System.out.println("Subject " + (i + 1) + " Grade: " + grades[i]);
        }
        System.out.println("Average: " + calculateAverage());
    }
}
