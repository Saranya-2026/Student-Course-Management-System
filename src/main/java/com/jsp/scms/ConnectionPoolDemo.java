package com.jsp.scms;
import java.sql.Connection;
public class ConnectionPoolDemo {
    public static void showConnection() {
        try {
            Connection con1 = ConnectionPool.getConnection();
            System.out.println("Connection Pool Demo");
            System.out.println("--------------------");
            if(con1 != null) {
                System.out.println("Connection obtained successfully from HikariCP Pool");
            }
            con1.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}