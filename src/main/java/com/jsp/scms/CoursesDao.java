package com.jsp.scms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
public class CoursesDao {
    Scanner sc = new Scanner(System.in);
    // 1. Insert Course
    public void insertCourse() {
        String query = "INSERT INTO courses(id,name,credits) VALUES(?,?,?)";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter number of courses: ");
            int n = sc.nextInt();
            for (int i = 1; i <= n; i++) {
                System.out.println("\nEnter Course " + i + " Details");
                System.out.print("Enter Course ID: ");
                ps.setInt(1, sc.nextInt());
                System.out.print("Enter Course Name: ");
                sc.nextLine();
                ps.setString(2, sc.nextLine());
                System.out.print("Enter Credits: ");
                ps.setInt(3, sc.nextInt());
                ps.executeUpdate();
            }
            System.out.println("Course(s) Inserted Successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 2. Update Course
    public void updateCourse() {
        String query = "UPDATE courses SET name=?,credits=? WHERE id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Course ID: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.print("Enter New Course Name: ");
            ps.setString(1, sc.nextLine());
            System.out.print("Enter Credits: ");
            ps.setInt(2, sc.nextInt());
            ps.setInt(3, id);
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Course Updated Successfully");
            else
                System.out.println("Course ID Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 3. Delete Course
    public void deleteCourse() {
        String query = "DELETE FROM courses WHERE id=?";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter Course ID: ");
            ps.setInt(1, sc.nextInt());
            int result = ps.executeUpdate();
            if (result > 0)
                System.out.println("Course Deleted Successfully");
            else
               System.out.println("Course ID Not Found");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 4. Fetch All Courses
    public void fetchAllCourses() {
        String query = "SELECT * FROM courses";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            System.out.println("--------------------------------------");
            System.out.println("ID\tNAME\t\tCREDITS");
            System.out.println("--------------------------------------");
            while (rs.next()) {
                System.out.println(
                        rs.getInt("id") + "\t" +
                        rs.getString("name") + "\t\t" +
                        rs.getInt("credits"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Used by EnrollmentDAO
    public boolean courseExists(int id) {
        String query = "SELECT * FROM courses WHERE id=?";
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
}