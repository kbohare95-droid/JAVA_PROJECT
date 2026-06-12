import java.util.Scanner;

public class newStudent {
    static class Person {
        private String name;
        private int age;

        Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        void showRole() {
            System.out.println("I am a Person.");
        }

        void displayDetails() {
            System.out.println("Name: " + name);
            System.out.println("Age: " + age);
        }
    }

    static class Teacher extends Person {
        private String subject;
        private double salary;

        Teacher(String name, int age, String subject, double salary) {
            super(name, age);
            this.subject = subject;
            this.salary = salary;
        }

        @Override
        void showRole() {
            System.out.println("I am a Teacher. I teach students.");
        }

        @Override
        void displayDetails() {
            super.displayDetails();
            System.out.println("Subject: " + subject);
            System.out.println("Salary: " + salary);
        }
    }

    static class Student extends Person {
        private int rollNumber;
        private String course;

        Student(String name, int age, int rollNumber, String course) {
            super(name, age);
            this.rollNumber = rollNumber;
            this.course = course;
        }

        @Override
        void showRole() {
            System.out.println("I am a Student. I study subjects.");
        }

        @Override
        void displayDetails() {
            super.displayDetails();
            System.out.println("Roll Number: " + rollNumber);
            System.out.println("Course: " + course);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter Teacher Details");
        String teacherName = readLine(sc, "Name: ");
        int teacherAge = readInt(sc, "Age: ");
        String subject = readLine(sc, "Subject: ");
        double salary = readDouble(sc, "Salary: ");

        System.out.println("\nEnter Student Details");
        String studentName = readLine(sc, "Name: ");
        int studentAge = readInt(sc, "Age: ");
        int rollNumber = readInt(sc, "Roll Number: ");
        String course = readLine(sc, "Course: ");

        Person[] people = new Person[2];
        people[0] = new Teacher(teacherName, teacherAge, subject, salary);
        people[1] = new Student(studentName, studentAge, rollNumber, course);

        System.out.println("\nPolymorphism Output");
        for (Person p : people) {
            p.displayDetails();
            p.showRole();
            System.out.println();
        }

        sc.close();
    }

    private static String readLine(Scanner sc, String message) {
        System.out.print(message);
        return sc.nextLine();
    }

    private static int readInt(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextInt()) {
            System.out.println("Please enter a valid number.");
            sc.next();
            System.out.print(message);
        }

        int value = sc.nextInt();
        sc.nextLine();
        return value;
    }

    private static double readDouble(Scanner sc, String message) {
        System.out.print(message);
        while (!sc.hasNextDouble()) {
            System.out.println("Please enter a valid amount.");
            sc.next();
            System.out.print(message);
        }

        double value = sc.nextDouble();
        sc.nextLine();
        return value;
    }
}
