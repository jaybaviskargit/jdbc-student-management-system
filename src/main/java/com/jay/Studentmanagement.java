package com.jay;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Studentmanagement {

    static Scanner sc = new Scanner(System.in);

    static Connection getConnection() throws SQLException {

        String url =  "jdbc:mysql://localhost:3306/college";
        String username = "root";
        String password = "jaysql2004";

       
             return DriverManager.getConnection(url,username,password);
              
 }

     static void addStudent(){
       

        System.out.print("Enter student ID: ");
        int id = sc.nextInt();
        sc.nextLine();
      

        System.out.print("Enter Student name: ");
        String name = sc.nextLine();

        System.out.print("Enter student age: ");
        int age = sc.nextInt();
        sc.nextLine();

        System.out.println("Enter student course: ");
        String course = sc.nextLine();

             String sql = "INSERT INTO student VALUES (?,?,?,?)";
      try(

            Connection con = getConnection();

        PreparedStatement stmt = con.prepareStatement(sql);
      ){

        stmt.setInt(1,id);
        stmt.setString(2, name);
        stmt.setInt(3, age);
        stmt.setString(4, course);

        int rows = stmt.executeUpdate();

        System.out.println(rows + "student inserted!");
          
      } catch (SQLException e) {
           System.out.println("Database Error: " + e.getMessage());
      }
        
     }

     static void viewstudent() {

       System.out.println("viewstudent() called");

    String sql = "SELECT * FROM student";

    try (
      
        Connection con = getConnection();
        PreparedStatement stmt = con.prepareStatement(sql);
        ResultSet rs = stmt.executeQuery();
    ){

        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id"));
            System.out.println("Name: " + rs.getString("name"));
            System.out.println("Age: " + rs.getInt("age"));
            System.out.println("Course: " + rs.getString("course"));
        }


    } catch (SQLException e) {
             System.out.println("Database error: " + e.getMessage());
    }
}

        static void updatestudent(){

            System.out.println("Enter student ID: ");
            int id = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter student name: ");
            String name = sc.nextLine();


             System.out.println("Enter student age: ");
            int age = sc.nextInt();
            sc.nextLine();

            System.out.println("Enter student course: ");
           String course = sc.nextLine();

              String sql = "UPDATE student SET name=?, age=?, course=? WHERE id=?";


            try (
                Connection con = getConnection();

                PreparedStatement stmt = con.prepareStatement(sql);
            ){
                stmt.setString(1, name);
                stmt.setInt(2, age);
                stmt.setString(3, course);
                stmt.setInt(4, id);
                
                int rows = stmt.executeUpdate();

                if(rows >0 ){
                    System.out.println("Student updated successfully!");

                }else{
                    System.out.println("Studnet ID not found");
                }

            } catch (SQLException e) {
                     System.out.println("Database error: " + e.getMessage());
}
            }
        

        static void deletestudent(){

            System.out.println("Enter student ID to Delete");
            int id = sc.nextInt();

            String sql = "DELETE FROM student WHERE id =?";

            try (
                
                Connection con = getConnection();
                
                PreparedStatement stmt = con.prepareStatement(sql);
            ){
                stmt.setInt(1, id);

                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    
                    System.out.println("Student deleted Successfully!");
                }else {
                    System.out.println("Student ID not found!");
                }

            } catch (SQLException e) {
                 System.out.println("Database Error: " + e.getMessage());
            }
        }

    public static void main(String[] args) {

        while(true){

            System.out.println("\n ===== Student Management System");
            System.out.println("1. Add student");
            System.out.println("2. View students");
            System.out.println("3. Update student");
            System.out.println("4. Delete student");
            System.out.println("5. Exit");

            System.out.println("Enter your choice");
            int choice = sc.nextInt();

            switch (choice){
                
                case 1 :
                       addStudent();
                    break; 

                   case 2:
                      viewstudent();
                         break;

                   case 3 :
                       updatestudent();
                     break;
                    
                     case 4 :
                      deletestudent();
                      break;

                        case 5 : 
                        System.out.println("Program Exited");
                        sc.close();
                        return;

                        default:
                            System.out.println("Invalid choice");

                  

            }

        }

        
    }
    
}
