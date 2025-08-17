package jspiders.DB;

import java.sql.*;

public class ViewAll {
    public static void main(String[] args) {
        viewAllStudents();
    }

    public static void viewAllStudents() {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "Suman"; // replace with your MySQL password

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

            String selectStmt = "SELECT * FROM students";
            PreparedStatement statement = connection.prepareStatement(selectStmt);

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int sid = resultSet.getInt("sid");
                String name = resultSet.getString("sname");
                int dept = resultSet.getInt("dept");
                Date dob = resultSet.getDate("dob");
                String skills = resultSet.getString("skills");
                boolean active = resultSet.getBoolean("active");

                System.out.println("ID: " + sid);
                System.out.println("Name: " + name);
                System.out.println("Department: " + dept);
                System.out.println("DOB: " + dob);
                System.out.println("Skills: " + skills);
                System.out.println("Active: " + active);
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
