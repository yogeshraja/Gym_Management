/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOLS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DBProcess {

    public String insertValues1(String fname, String lname) {

        try {

            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://thundermail.mysql.database.azure.com:3306?useSSL=true&requireSSL=false";
            Connection con = DriverManager.getConnection(url, "Yogesh@thundermail", "Bootathon2.0");

            String query = "create database Yogesh";
            PreparedStatement pstmt = con.prepareStatement(query);
//
//            pstmt.setString(1, fname);
//            pstmt.setString(2, lname);

            pstmt.executeUpdate();
            con.setAutoCommit(true);
            con.close();
            return "Inserted Successfully!";
        } catch (Exception ex) {
            return "Exception------------------------>  " + ex;
        }

    }

    public String loginValidation(String uname, String pass) {

        try {

            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE", "servlets", "sql");

            String query = "select uname from admin where uname=? and pass=?";

            PreparedStatement pstmt = con.prepareStatement(query);

            pstmt.setString(1, uname);
            pstmt.setString(2, pass);

            ResultSet rs = pstmt.executeQuery();

            boolean check = false;

            if (rs.next()) {

                check = true;
            } else {
                check = false;
            }

            con.setAutoCommit(true);
            con.close();

            if (check) {
                return "success";
            } else {
                return "failed";
            }
        } catch (Exception ex) {
            return "Exception------------------------>  " + ex;
        }
    }

    public static void main(String[] args) {
        DBProcess obj = new DBProcess();
        obj.insertValues1("Yogesh", "DB");
    }
}
