package com.jsp.scms;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
public class MetadataDemo {
    // 1. Database Metadata
    public void databaseMetadata() {
        try (Connection con = ConnectionPool.getConnection()) {
            DatabaseMetaData db = con.getMetaData();
            System.out.println("\n========= DATABASE METADATA =========");
            System.out.println("Database Product Name : " + db.getDatabaseProductName());
            System.out.println("Database Version      : " + db.getDatabaseProductVersion());
            System.out.println("Driver Version        : " + db.getDriverVersion());
            System.out.println("\nAvailable Tables:");
            ResultSet rs = db.getTables(null, null, "%", new String[] { "TABLE" });
            while (rs.next()) {
                System.out.println(rs.getString("TABLE_NAME"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // 2. ResultSet Metadata
    public void resultSetMetadata() {
        String query = "SELECT * FROM students";
        try (Connection con = ConnectionPool.getConnection();
             PreparedStatement ps = con.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {
            ResultSetMetaData rsmd = rs.getMetaData();
            System.out.println("\n========== STUDENT DETAILS ==========");
            for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                System.out.print(rsmd.getColumnName(i) + "\t");
            }
            System.out.println();
            while (rs.next()) {
                for (int i = 1; i <= rsmd.getColumnCount(); i++) {
                    System.out.print(rs.getObject(i) + "\t");
                }
                System.out.println();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}