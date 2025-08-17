package jspiders.DB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UpdateStudent {
    public static void main(String[] args) {
        updateStudent();
    }

    public static void updateStudent() {
        String url = "jdbc:mysql://localhost:3306/studentdb";
        String user = "root";
        String password = "Suman"; // change this

        try {
            Connection connection = DriverManager.getConnection(url, user, password);
            System.out.println("Connected");

            String updateStmt= "UPDATE students SET sname = ?, skills = ? WHERE sid = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(updateStmt);

            preparedStatement.setString(1, "UpdateStudent");
            preparedStatement.setString(2, "Java, SQL,Web");
            preparedStatement.setInt(3, 10);
            preparedStatement.execute();
            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

