package jspiders.services;

import jspiders.dto.StudentDTO;

public class StudentDTOValidator {

    public static void validateStudentDTO(StudentDTO studentDTO) {
        checkName(studentDTO);
        checkDeptNo(studentDTO);
        checkEmail(studentDTO);
        checkPhoneNumber(studentDTO); // Phone number validation added
    }

    private static void checkName(StudentDTO studentDTO) {
        String name = studentDTO.getName();
        if (name == null || name.length() < 4) {
            throw new IllegalArgumentException("Name should be minimum 4 characters: " + name);
        }
    }

    private static void checkDeptNo(StudentDTO studentDTO) {
        int deptNo = studentDTO.getDept();
        if (deptNo <= 0) {
            throw new IllegalArgumentException("Invalid Department Number: " + deptNo);
        }
    }

    private static void checkEmail(StudentDTO studentDTO) {
        String email = studentDTO.getEmail();
        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";

        if (email == null || !email.matches(regex)) {
            throw new IllegalArgumentException("Invalid Email Format: " + email);
        }
    }

    private static void checkPhoneNumber(StudentDTO studentDTO) {
        String phone = studentDTO.getphone_number();
        String regex = "^[789]\\d{9}$";

        if (phone == null || !phone.matches(regex)) {
            throw new IllegalArgumentException("Invalid Phone Number: " + phone);
        }
    }
}
