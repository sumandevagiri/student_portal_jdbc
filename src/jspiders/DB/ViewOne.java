package jspiders.DB;

import java.sql.*;
import java.util.Scanner;

public class ViewOne{
    public static void main(String[] args) {
        viewOneStudent();
    }

    public static void viewOneStudent() {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "Suman";

        try (
                Connection connection = DriverManager.getConnection(url, user, password);
                Scanner scanner = new Scanner(System.in)
        ) {
            System.out.println("Connected");

            System.out.print("Enter student ID to view: ");
            int sid = scanner.nextInt();

            String query = "SELECT * FROM students WHERE sid = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, sid);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                System.out.println("ID      : " + resultSet.getInt("sid"));
                System.out.println("Name    : " + resultSet.getString("sname"));
                System.out.println("Dept    : " + resultSet.getInt("dept"));
                System.out.println("DOB     : " + resultSet.getDate("dob"));
                System.out.println("Skills  : " + resultSet.getString("skills"));
                System.out.println("Active  : " + resultSet.getBoolean("active"));
            } else {
                System.out.println(" No student found with ID: " + sid);
            }


            statement.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
