package com.jsp.scms;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Scanner;
public class BatchInsertDemo {
    public static void batchInsertStudents() {
        Scanner sc = new Scanner(System.in);
        String query = "INSERT INTO students(id,name,email,dob) VALUES(?,?,?,?)";
        try(Connection con = ConnectionPool.getConnection();
            PreparedStatement ps = con.prepareStatement(query)) {
            System.out.print("Enter number of students: ");
            int n = sc.nextInt();
            for(int i=1; i<=n; i++) {
                System.out.println("Enter Student " + i + " Details");
                System.out.print("ID: ");
                ps.setInt(1, sc.nextInt());
                System.out.print("Name: ");
                ps.setString(2, sc.next());
                System.out.print("Email: ");
                ps.setString(3, sc.next());
                System.out.print("DOB (yyyy-mm-dd): ");
                ps.setDate(4, java.sql.Date.valueOf(sc.next()));
                ps.addBatch();
            }
            ps.executeBatch();
            System.out.println("Batch Insert Completed Successfully");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}