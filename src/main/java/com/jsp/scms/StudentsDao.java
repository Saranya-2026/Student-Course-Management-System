package com.jsp.scms;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class StudentsDao {
    Scanner sc = new Scanner(System.in);
    // 1. Insert Student
    public void insertStudent() {
        String query = "INSERT INTO students(id,name,email,dob) VALUES(?,?,?,?)";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter Student " + i + " Details");
                System.out.print("Enter Student ID: ");
                ps.setInt(1, sc.nextInt());
                System.out.print("Enter Name: ");
                ps.setString(2, sc.next());
                System.out.print("Enter Email: ");
                ps.setString(3, sc.next());
                System.out.print("Enter DOB (yyyy-mm-dd): ");
                ps.setDate(4, Date.valueOf(sc.next()));
                ps.executeUpdate();
            }
            System.out.println("Student(s) Inserted Successfully.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Update Student
    public void updateStudent() {
        String query = "UPDATE students SET name=?,email=?,dob=? WHERE id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            int id = sc.nextInt();
            System.out.print("Enter New Name: ");
            ps.setString(1, sc.next());
            System.out.print("Enter New Email: ");
            ps.setString(2, sc.next());
            System.out.print("Enter New DOB (yyyy-mm-dd): ");
            ps.setDate(3, Date.valueOf(sc.next()));
            ps.setInt(4, id);
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Student Updated Successfully.");
            else
                System.out.println("Student ID Not Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Delete Student
    public void deleteStudent() {
        String query = "DELETE FROM students WHERE id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            ps.setInt(1, sc.nextInt());
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Student Deleted Successfully.");
            else
                System.out.println("Student ID Not Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Fetch All Students
    public void fetchAllStudents() {
        String query = "SELECT * FROM students";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("\n----------------------------------------------");
            System.out.println("ID\tNAME\tEMAIL\t\tDOB");
            System.out.println("----------------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t" +
                        rs.getString("email") + "\t" +
                        rs.getDate("dob"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Used by EnrollmentDAO
    public boolean studentExists(int id) {
        String query = "SELECT * FROM students WHERE id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
   //5. Calculate GPA
    public void calculateGPA() {
        String query = "SELECT calculate_gpa(?) AS gpa";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                System.out.println("\n========== STUDENT GPA ==========");
                System.out.println("Student ID : " + studentId);
                System.out.println("GPA        : " + rs.getDouble("gpa"));
            } else {
                System.out.println("GPA Not Found");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
