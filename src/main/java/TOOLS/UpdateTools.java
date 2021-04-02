/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOLS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UpdateTools implements ActionListener {

    final static public String COACH_FETCH[] = {"select * from coachtable where cid=?", "cfirstname", "clastname", "cage", "cdob", "cemail", "cphonenumber", "cgender", "coccupation", "caddress", "chealthcondition", "cexperience"};
    final static public String USER_FETCH[] = {"select * from usertable where uid=?", "ufirstname", "ulastname", "uage", "udob", "uemail", "uphonenumber", "ugender", "uoccupation", "uaddress", "uhealthcondition", "ugoal"};

    public static Object[] fetchData(String id, String type[]) {
        Object ob[] = new Object[11];
        try {
            Connection connection = DbTools.getConnect();
            System.out.println("fetch");
            PreparedStatement ps = connection.prepareStatement(type[0]);
            ps.setString(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                ob[0] = rs.getString(type[1]);
                ob[1] = rs.getString(type[2]);
                ob[2] = rs.getString(type[3]);
                ob[3] = rs.getString(type[4]);
                ob[4] = rs.getString(type[5]);
                ob[5] = rs.getString(type[6]);
                ob[6] = rs.getString(type[7]);
                ob[7] = rs.getString(type[8]);
                ob[8] = rs.getString(type[9]);
                ob[9] = rs.getString(type[10]);
                ob[10] = rs.getString(type[11]);
            }
            System.out.println("fetch complete");
            return ob;
        } catch (SQLException ex) {
            Logger.getLogger(UpdateTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static boolean updateuser(Object values[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("update usertable set ufirstname=?,ulastname=?,uage=?, udob=?,uemail =?,uphonenumber=?,ugender=?,uoccupation=?,uaddress=?,uhealthCondition=?,ugoal=?,upassword=? where uid=?");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, (String) values[0]);
            ps.setString(2, (String) values[1]);
            ps.setInt(3, Integer.parseInt((String) values[2]));
            ps.setString(4, (String) values[3]);
            ps.setString(5, (String) values[4]);
            ps.setString(6, (String) values[5]);
            ps.setString(7, (String) values[6]);
            ps.setString(8, (String) values[7]);
            ps.setString(9, (String) values[8]);
            ps.setString(10, (String) values[9]);
            ps.setString(11, (String) values[10]);
            ps.setString(12, (String) values[11]);
            ps.setString(13, (String) values[12]);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean updatecoach(Object values[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("update coachtable set cfirstname=?,clastname=?,cage=?, cdob=?,cemail =?,cphonenumber=?,cgender=?,coccupation=?,caddress=?,chealthCondition=?,cexperience=?,cpassword=? where cid=?");
            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, (String) values[0]);
            ps.setString(2, (String) values[1]);
            ps.setInt(3, Integer.parseInt((String) values[2]));
            ps.setString(4, (String) values[3]);
            ps.setString(5, (String) values[4]);
            ps.setString(6, (String) values[5]);
            ps.setString(7, (String) values[6]);
            ps.setString(8, (String) values[7]);
            ps.setString(9, (String) values[8]);
            ps.setString(10, (String) values[9]);
            ps.setString(11, (String) values[10]);
            ps.setString(12, (String) values[11]);
            ps.setString(13, (String) values[12]);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean updateadmin() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM2", "root", "@Krupasugu");
            String query = ("update admintables set afirstname=?,alastname=?,aemail =?,aage=?,agender=?,afinance=?, adob=?,aaddress=?,aphonenumber=?,apassword=? where aid=?");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(11, "18e");
            ps.setString(1, "xxx");
            ps.setString(2, "ishu");
            ps.setString(3, "isu@gmail.com");
            ps.setInt(4, 60);
            ps.setString(5, "m");
            ps.setString(6, "self financed");
            ps.setString(7, "2000.10.05");
            ps.setString(8, "34.t.nagar.chennai");
            ps.setString(9, "9658582451");
            ps.setString(10, "hiisjj");
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
