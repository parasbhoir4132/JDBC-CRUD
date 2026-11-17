package in.jdbc;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or

import in.jdbc.model.Student;
import in.jdbc.repository.StudentRepository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args){

        StudentRepository studentRepository = new StudentRepository();

//        studentRepository.createUser(new Student("Bhushan","bhushan@gmail.com",26));

//        studentRepository.createUser();

//        studentRepository.updateUser(new Student("paras bhoir" , "bhoirparas@gmil.com",23), 2L);

//        studentRepository.deleteUser(4L);
//        studentRepository.getUserById(2L);

        studentRepository.getUser();

    }

}
