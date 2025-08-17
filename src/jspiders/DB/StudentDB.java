package jspiders.DB;

import jspiders.entity.StudentEntity;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDB {
    private String dburl = "jdbc:mysql://localhost:3306/studentdb";
    private String user = "root";
    private String password = "root";
    private Connection connection;

    public StudentDB() {
        try {
            connection = DriverManager.getConnection(dburl, user, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(StudentEntity studentEntity) throws SQLException {

        String insertSQL = "INSERT INTO students (sname, dept, dob, skills, active, phone_number, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(insertSQL);

        preparedStatement.setString(1, studentEntity.getName());
        preparedStatement.setInt(2, studentEntity.getDept());
        preparedStatement.setString(3, studentEntity.getDob());
        preparedStatement.setString(4, studentEntity.getSkills());
        preparedStatement.setBoolean(5, studentEntity.isActive());
        preparedStatement.setString(6, studentEntity.getPhone_number());
        preparedStatement.setString(7, studentEntity.getEmail());

        preparedStatement.execute();
        System.out.println("Student data added to DB");
    }

    public void update(StudentEntity studentEntity) throws SQLException {
        String updateStmt = "UPDATE students SET sname = ?, skills = ?, phone_number = ?, email = ? WHERE sid = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(updateStmt);

        preparedStatement.setString(1, studentEntity.getName());
        preparedStatement.setString(2, studentEntity.getSkills());
        preparedStatement.setString(3, studentEntity.getPhone_number());
        preparedStatement.setString(4, studentEntity.getEmail());
        preparedStatement.setInt(5, studentEntity.getSid());

        preparedStatement.execute();
        System.out.println("Student record updated.");
    }

    public void delete(int id) throws SQLException {
        String deleteStmt = "DELETE FROM students WHERE sid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(deleteStmt);
        preparedStatement.setInt(1, id);
        preparedStatement.execute();
        System.out.println("Student record deleted.");
    }

    public List<StudentEntity> getAll() throws SQLException {
        String select = "SELECT * FROM students";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);

        List<StudentEntity> studentsList = new ArrayList<>();

        while (resultSet.next()) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setSid(resultSet.getInt("sid"));
            studentEntity.setName(resultSet.getString("sname"));
            studentEntity.setDept(resultSet.getInt("dept"));
            studentEntity.setDob(resultSet.getString("dob"));
            studentEntity.setSkills(resultSet.getString("skills"));
            studentEntity.setActive(resultSet.getBoolean("active"));
            studentEntity.setPhone_number(resultSet.getString("phone_number"));
            studentEntity.setEmail(resultSet.getString("email"));

            studentsList.add(studentEntity);
        }
        return studentsList;
    }

    public StudentEntity getById(int id) throws SQLException {
        String selectById = "SELECT * FROM students WHERE sid=?";
        PreparedStatement preparedStatement = connection.prepareStatement(selectById);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            StudentEntity studentEntity = new StudentEntity();
            studentEntity.setSid(resultSet.getInt("sid"));
            studentEntity.setName(resultSet.getString("sname"));
            studentEntity.setDept(resultSet.getInt("dept"));
            studentEntity.setDob(resultSet.getString("dob"));
            studentEntity.setSkills(resultSet.getString("skills"));
            studentEntity.setActive(resultSet.getBoolean("active"));
            studentEntity.setPhone_number(resultSet.getString("phone_number"));
            studentEntity.setEmail(resultSet.getString("email"));
            return studentEntity;
        } else {
            throw new IllegalArgumentException("Invalid sid: " + id);
        }
    }
}
