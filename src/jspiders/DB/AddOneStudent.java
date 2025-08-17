package jspiders.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddOneStudent {
    public static void main(String[] args) {
        addStudent();
    }

    public static void addStudent() {

        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "root";

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

            String insertStmt = "INSERT INTO students (sid, sname, dept, dob, skills, active) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement preparedStatement = connection.prepareStatement(insertStmt);

            // Set values
            preparedStatement.setInt(1, 20);
            preparedStatement.setString(2, "Student2");
            preparedStatement.setInt(3, 7);
            preparedStatement.setString(4, "2003-02-11");
            preparedStatement.setString(5, "Java");
            preparedStatement.setBoolean(6, true);
            preparedStatement.execute();





            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
