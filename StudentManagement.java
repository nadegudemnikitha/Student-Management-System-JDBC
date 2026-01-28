import java.sql.*;
import java.util.Scanner;

public class StudentManagement {
   
    static final String DB_URL = "jdbc:mysql://localhost:3306/student_db";
    static final String USER = "root"; 
    static final String PASS = "nikki@17"; 

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        try {
       
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = conn.createStatement();
            
            while(true) {
                System.out.println("\n--- Student Management System ---");
                System.out.println("1. Add Student");
                System.out.println("2. View Students");
                System.out.println("3. Exit");
                System.out.print("Enter choice: ");
                
                int choice = sc.nextInt();
                
                if(choice == 1) {
                   
                    System.out.print("Enter Student Name: ");
                    String name = sc.next();
                    System.out.print("Enter Student Grade: ");
                    String grade = sc.next();
                    
                    String sql = "INSERT INTO students (name, grade) VALUES ('" + name + "', '" + grade + "')";
                    stmt.executeUpdate(sql);
                    System.out.println("Student Added Successfully!");
                    
                } else if (choice == 2) {
                    
                    String sql = "SELECT * FROM students";
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    System.out.println("\nID\tName\tGrade");
                    System.out.println("---------------------");
                    while(rs.next()) {
                        System.out.println(rs.getInt("id") + "\t" + rs.getString("name") + "\t" + rs.getString("grade"));
                    }
                    
                } else {
                    break;
                }
            }
            conn.close();
            
        } catch (Exception e) {
            System.out.println("Database Connection Error: " + e.getMessage());
            System.out.println("Make sure MySQL is running and database 'student_db' exists.");
        }
    }
}