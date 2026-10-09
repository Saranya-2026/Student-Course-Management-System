package com.jsp.scms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class EnrollementsDao {
    Scanner sc = new Scanner(System.in);
    // 1. Enroll Student
    public void enrollStudent() {
        try (Connection con = ConnectionPool.getConnection()) {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            // Check Student
            PreparedStatement ps1 = con.prepareStatement(
                    "SELECT * FROM students WHERE id=?");
            ps1.setInt(1, studentId);
            ResultSet rs1 = ps1.executeQuery();

            if (!rs1.next()) {
                System.out.println("Student ID Not Found.");
                return;
            }
            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();
            // Check Course
            PreparedStatement ps2 = con.prepareStatement(
                    "SELECT * FROM courses WHERE id=?");
            ps2.setInt(1, courseId);
            ResultSet rs2 = ps2.executeQuery();

            if (!rs2.next()) {
                System.out.println("Course ID Not Found.");
                return;
            }
            // Insert Enrollment
            PreparedStatement ps3 = con.prepareStatement(
                    "INSERT INTO enrollements(student_id,course_id,grade) VALUES(?,?,NULL)");
            ps3.setInt(1, studentId);
            ps3.setInt(2, courseId);
            int result = ps3.executeUpdate();
            if (result > 0)
                System.out.println("Student Enrolled Successfully.");
            else
                System.out.println("Enrollment Failed.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Update Grade
    public void updateGrade() {
        String query = "UPDATE enrollements SET grade=? WHERE student_id=? AND course_id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            System.out.print("Enter Course ID: ");
            int courseId = sc.nextInt();
            System.out.print("Enter Grade: ");
            String grade = sc.next();
            ps.setString(1, grade);
            ps.setInt(2, studentId);
            ps.setInt(3, courseId);
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Grade Updated Successfully.");
            else
                System.out.println("Enrollment Not Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Delete Enrollment
    public void deleteEnrollment() {
        String query = "DELETE FROM enrollements WHERE student_id=? AND course_id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            ps.setInt(1, sc.nextInt());
            System.out.print("Enter Course ID: ");
            ps.setInt(2, sc.nextInt());
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Enrollment Deleted Successfully.");
            else
                System.out.println("Enrollment Not Found.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Fetch Student Enrollments
    public void fetchStudentEnrollments() {
        String query = "SELECT s.id,s.name,c.id,c.name,e.grade "
                + "FROM enrollements e "
                + "JOIN students s ON e.student_id=s.id "
                + "JOIN courses c ON e.course_id=c.id "
                + "WHERE s.id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            ps.setInt(1, sc.nextInt());
            ResultSet rs = ps.executeQuery();
            System.out.println("-----------------------------------------------");
            System.out.println("SID\tSNAME\tCID\tCOURSE\tGRADE");
            System.out.println("-----------------------------------------------");
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println(
                        rs.getInt(1) + "\t" +
                        rs.getString(2) + "\t" +
                        rs.getInt(3) + "\t" +
                        rs.getString(4) + "\t" +
                        rs.getString(5));
            }
            if (!found) {
                System.out.println("No Enrollment Found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 4. view grades()
    public void viewGrades() {
        String query = "SELECT s.id, s.name, c.name, e.grade "
                + "FROM enrollements e "
                + "JOIN students s ON e.student_id=s.id "
                + "JOIN courses c ON e.course_id=c.id "
                + "WHERE s.id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Student ID: ");
            int studentId = sc.nextInt();
            ps.setInt(1, studentId);
            ResultSet rs = ps.executeQuery();
            System.out.println("\n========== STUDENT GRADES ==========");
            System.out.println("ID\tNAME\tCOURSE\tGRADE");
            System.out.println("------------------------------------");
            boolean found = false;
            while(rs.next()) {
                found = true;
                System.out.println(
                    rs.getInt(1) + "\t" +
                    rs.getString(2) + "\t" +
                    rs.getString(3) + "\t" +
                    rs.getString(4)
                );
            }
            if(!found) {
                System.out.println("No Grades Found.");
            }
        } catch(SQLException e) {
            e.printStackTrace();
        }

    } 
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
