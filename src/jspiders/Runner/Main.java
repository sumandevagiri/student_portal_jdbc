package jspiders.Runner;

import jspiders.DB.StudentDB;
import jspiders.entity.StudentEntity;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Program starts...");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose an option:");
        System.out.println("1. Add Student");
        System.out.println("2. Get All Students");
        System.out.println("3.Get by id");
        System.out.println("4. Delete Student by ID");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> testAdd();
            case 2 -> testGetAll();
            case 3 -> {
                System.out.println("Enter student ID to fetch:");
                int id = scanner.nextInt();
                testGetByid(id);
            }

            case 4 -> {
                System.out.println("Enter student ID to delete:");
                int id = scanner.nextInt();
                testDelete(id);
            }


            default -> System.out.println("Invalid option!");
        }

        System.out.println("Program ends...");
    }

    public static void testAdd() {
        Scanner input = new Scanner(System.in);
        StudentEntity studentEntity = new StudentEntity();

        System.out.println("Enter student name:");
        studentEntity.setName(input.nextLine());

        System.out.println("Enter student dept:");
        studentEntity.setDept(input.nextInt());
        input.nextLine();

        System.out.println("Enter student DOB (yyyy-mm-dd):");
        studentEntity.setDob(input.nextLine());

        System.out.println("Enter student skills:");
        studentEntity.setSkills(input.nextLine());

        System.out.println("Enter student email:");
        studentEntity.setEmail(input.nextLine());

        System.out.println("Enter student phone number:");
        studentEntity.setPhone_number(input.nextLine());

        studentEntity.setActive(true);

        try {
            StudentDB studentDB = new StudentDB();
            studentDB.save(studentEntity);
        } catch (Exception ex) {
            ex.printStackTrace();
            System.err.println("Error while saving student: " + ex.getMessage());
        }
    }

    public static void testGetAll() {
        try {
            StudentDB studentDB = new StudentDB();
            List<StudentEntity> studentList = studentDB.getAll();
            for (StudentEntity student : studentList) {
                System.out.println(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while fetching students: " + e.getMessage());
        }
    }


    public static void testGetByid(int id) {
        try {
            StudentDB studentDB = new StudentDB();
            StudentEntity student = studentDB.getById(id);
            if (student != null) {
                System.out.println(student);
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while fetching student: " + e.getMessage());
        }
    }


    public static void testDelete(int id) {
        try {
            StudentDB studentDB = new StudentDB();
            studentDB.delete(id);
            System.out.println("Student with ID " + id + " deleted.");
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error while deleting student: " + e.getMessage());
        }
    }
}
