package in.jdbc.repository;

import in.jdbc.model.Student;

import java.rmi.StubNotFoundException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentRepository {


    String url = "jdbc:mysql://localhost:3306/jdbc_crud";
    String username ="root";
    String password="Root@123";

    public void createUser(Student student){

        String sql = """
                         insert into student (name, email, age)
                         values(?, ? ,?)
                         """;

        try( Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());

            int rowAffected = preparedStatement.executeUpdate();

            if(rowAffected == 1){
                System.out.println("Student creation successfull");
            }else{
                System.out.println("Student creation failed");
            }
        }
        catch(SQLException e){
            System.out.println("Database connection failed");
            e.printStackTrace();
        };
    }

    public void updateUser(Student student ,Long id){

        String sql = """
                     UPDATE student set name = ?,
                                        email=?,
                                        age=?
                     where id = ?
                     """;

        try( Connection connection = DriverManager.getConnection(url,username,password);
             PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setString(1,student.getName());
            preparedStatement.setString(2,student.getEmail());
            preparedStatement.setInt(3,student.getAge());
            preparedStatement.setLong(4 ,id);

            int rowAffected = preparedStatement.executeUpdate();

            if (rowAffected == 1) {
                System.out.println("Update operation successfull");
            }else {
                System.out.println("Update operation failed");
            }

        } catch (SQLException e) {
            System.out.println("Database connection failed");
            e.printStackTrace();
        }

    }

    public void deleteUser(Long id){

        String sql = """
                     DELETE from student where id =?
                     """;

       try(Connection connection = DriverManager.getConnection(url,username,password);
           PreparedStatement preparedStatement = connection.prepareStatement(sql)){

           preparedStatement.setLong(1 ,id);

           int rowAffected = preparedStatement.executeUpdate();

           if (rowAffected == 1){
               System.out.println("Delete Operation successfull");
           }else{
               System.out.println("Delete operation failed");
           }

       }catch (SQLException e){
           System.out.println("Database Connection failed");
       }

    }

    public void getUserById(Long id){

        String sql = """
                     Select id ,name, email, age from student where id = ?
                     """;

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql)){

            preparedStatement.setLong(1 , id);

            try{
                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()){
                    Student student = mapRow(resultSet);
                    System.out.println(student);

                }
            }catch (SQLException e){
                e.printStackTrace();
            }

        }catch (SQLException e){
            System.out.println("Database Connection failed");
        }
    }

    public void getUser(){

        String sql = """
                      SELECT id, name, email, age  from student
                      """;

        try(Connection connection = DriverManager.getConnection(url,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ){

            try (ResultSet resultSet = preparedStatement.executeQuery()){
                List<Student> studentList = new ArrayList<>();
                while (resultSet.next()){
                   Student student = mapRow(resultSet);
                   studentList.add(student);
                    System.out.println(student);
                }
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }

    private Student mapRow(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setId(resultSet.getLong("id"));
        student.setName(resultSet.getString("name"));
        student.setEmail(resultSet.getString("email"));
        student.setAge(resultSet.getInt("age"));

        return student;
    }

}

