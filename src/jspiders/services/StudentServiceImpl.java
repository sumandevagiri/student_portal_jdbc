package jspiders.services;

import jspiders.DB.StudentDB;
import jspiders.dto.StudentDTO;
import jspiders.entity.StudentEntity;

public class StudentServiceImpl implements  StudentService{

    public void addStudent(StudentDTO studentDTO) {
        StudentDTOValidator.validateStudentDTO(studentDTO);
        StudentDB studentDB=new StudentDB();
        StudentEntity studentEntity=new StudentEntity();

        studentEntity.setName(studentDTO.getName());
        studentEntity.setDept(studentDTO.getDept());
        studentEntity.setDob(studentDTO.getDob());
        studentEntity.setSkills(studentDTO.getSkills());
        studentEntity.setSid(0);
        studentEntity.setActive(true);

        System.out.println("Student registered");

    }
}