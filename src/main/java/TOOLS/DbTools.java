package TOOLS;

import java.io.File;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class DbTools {

    static public final String[] ADMIN_STRINGS = {"SELECT * FROM admintable where aemail= ? OR aphonenumber= ? or aid=?", "apassword"};
    static public final String[] COACH_STRINGS = {"SELECT * FROM coachtable where cemail= ? OR cphonenumber= ? or cid=?", "cpassword"};
    static public final String[] USER_STRINGS = {"SELECT * FROM usertable where uemail= ? OR uphonenumber= ? or uid=?", "upassword"};

    static private Connection connection;

    static public Connection getConnect() {

        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/gym31", "yogesh", "java");
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return connection;

    }

    public static int authUser(String input, String password, String[] userType, JPanel panel) {
        ResultSet rs;
        try {
            Connection connection = getConnect();
            PreparedStatement st = connection.prepareStatement(userType[0]);
            st.setString(1, input);
            st.setString(2, input);
            st.setString(3, input);
            rs = st.executeQuery();
            if (!rs.next()) {
                JOptionPane.showMessageDialog(panel, "Account not found");
                return 2;
            }
            return (rs.getString(userType[1])).equals(CryptUtility.encryptString(password)) ? 1 : 0;
        } catch (SQLException ex) {
            System.out.println(ex);
        } catch (EncryptionException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public static void databaseCreation() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/", "yogesh", "java");
            Statement statement = con.createStatement();
            String database = "create database GYM31";
            String usedatabase = "use GYM31";
            String userstable = "create table usertable(uid varchar(6) primary key,ufirstname varchar(30) not null,ulastname varchar(30),uage int(5) ,udob date ,uemail varchar(40) not null,uphonenumber varchar(15) not null,ugender varchar(20),uoccupation varchar(20) ,uaddress varchar(150),uhealthcondition varchar(150) ,ugoal varchar(150) ,upassword varchar(50) not null,cid varchar(6) not null,ufees int(5) not null)";
            String coachtable = "create table coachtable(cid varchar(6) primary key,cfirstname varchar(30) not null,clastname varchar(30),cage int(5),cdob date ,cemail varchar(40) not null,cphonenumber varchar(15) not null,cgender varchar(20) ,coccupation varchar(20),caddress varchar(150) ,chealthcondition varchar(150) ,cexperience varchar(150) ,cpassword varchar(50) not null,csalary int(6) not null)";
            String admintables = "create table admintable(aid varchar(6) primary key,afirstname varchar(30) not null,alastname varchar(30) not null,aemail varchar(40) not null,aage int(5) not null,agender varchar(20) not null,afinance varchar(50) not null,adob date not null,aaddress varchar(150) not null,aphonenumber varchar(15) not null,apassword varchar(50)not null)";
            String imagetable = "create table imagetable(id varchar(8) ,image varchar(10) primary key,idate date not null)";
            String logintable = "create table logintable(logid varchar(20) primary key,logintime time,logdate date not null,id varchar(10) )";
            String logouttable = "create table logouttable(logid varchar(20) primary key,logouttime time,logdate date not null,id varchar(10) )";
            String userPool = "Create table userpool(id varchar(6) primary key,fname varchar(20),lname varchar(20),pnumber varchar(14),email varchar(30), amount int(6),cid varchar(6))";
            String fpasstable = "Create table fpasstable(id varchar(20) primary key,fans varchar(30),sans varchar(30))";
            System.out.println(statement.executeUpdate(database));
            System.out.println("db created");
            statement.executeUpdate(usedatabase);
            System.out.println("use done");
            statement.executeUpdate(userstable);
            System.out.println("user created");
            statement.executeUpdate(coachtable);
            System.out.println("coach created");
            statement.executeUpdate(admintables);
            System.out.println("admin created");
            statement.executeUpdate(imagetable);
            System.out.println("image created");
            statement.executeUpdate(logintable);
            System.out.println("login created");
            statement.executeUpdate(logouttable);
            System.out.println("logout created");
            statement.executeUpdate(userPool);
            System.out.println("userpool created");
            statement.executeUpdate(fpasstable);
            System.out.println("fpass created");

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    public static boolean register_user(Object values[]) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/gym31", "yogesh", "java");
            Object poolValues[] = getUserFromUserPool(values);
            String query = ("insert into usertable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) poolValues[0]);
            ps.setString(2, (String) values[0]);
            ps.setString(3, (String) values[1]);
            ps.setInt(4, Integer.parseInt((String) values[2]));
            ps.setString(5, (String) values[3]);
            ps.setString(6, (String) values[4]);

            ps.setString(7, (String) values[5]);

            ps.setString(8, (String) values[6]);

            ps.setString(9, (String) values[7]);

            ps.setString(10, (String) values[8]);
            ps.setString(11, (String) values[9]);
            ps.setString(12, (String) values[10]);
            ps.setString(13, CryptUtility.encryptString((String) values[11]));
            ps.setString(14, (String) poolValues[1]);
            ps.setInt(15, (Integer) poolValues[2]);
            ps.executeUpdate();
            Statement st = connection.createStatement();
            st.execute("Delete from userpool where id='" + poolValues[0] + "'");
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean register_coach(Object values[]) {
        try {
            Connection con = getConnect();
            Object poolValues[] = getCoachFromUserPool(values);
            String query = ("insert into coachtable values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) poolValues[0]);
            ps.setString(2, (String) values[0]);
            ps.setString(3, (String) values[1]);
            ps.setInt(4, Integer.parseInt((String) values[2]));
            ps.setString(5, (String) values[3]);
            ps.setString(6, (String) values[4]);

            ps.setString(7, (String) values[5]);

            ps.setString(8, (String) values[6]);

            ps.setString(9, (String) values[7]);

            ps.setString(10, (String) values[8]);
            ps.setString(11, (String) values[9]);
            ps.setString(12, (String) values[10]);
            ps.setString(13, CryptUtility.encryptString((String) values[11]));
            ps.setInt(14, (Integer) poolValues[1]
            );
            ps.executeUpdate();
            Statement st = connection.createStatement();
            st.execute("Delete from userpool where id='" + poolValues[0] + "'");
            return true;

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static boolean register_admin() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("insert into admintable values(?,?,?,?,?,?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, "AD001A");
            ps.setString(2, "Admin");
            ps.setString(3, "Admin");
            ps.setString(4, "Admin@team31.com");
            ps.setInt(5, 20);
            ps.setString(6, "Admin");
            ps.setString(7, "Student");
            ps.setString(8, "2020.06.25");

            ps.setString(9, "Tream-31,Bootcamp-SKI");

            ps.setString(10, "0123456789");

            ps.setString(11, CryptUtility.encryptString("Admin@31"));
            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean updatelogtable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM2", "root", "@Krupasugu");
            String query = ("update logtable set logintime=?,logoutime=?,logdate=?,logengagedtime=? where logid=?");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(5, "7vy");
            ps.setString(1, "11:11:11");
            ps.setString(2, "10:12:11");
            ps.setString(3, "1999-05-05");
            ps.setInt(4, 5);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e);

        }
        return true;
    }

    public static boolean updateimagetable() {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM2", "root", "@Krupasugu");
            String query = ("update imagetable set image=?,imagedate=? where imageid=?");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(3, "499");
            ps.setString(1, "Null");
            ps.setString(2, "1999-05-18");
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    public static int extractNumber(String str) {
        String st = str.charAt(2) + "" + str.charAt(3) + "" + str.charAt(4);
        return Integer.parseInt(st);
    }

    public static String generateCoachID() {
        Connection connection = getConnect();
        String ans = "";
        for (int i = 100; i <= 999; i++) {
            try {
                PreparedStatement st = connection.prepareStatement("select cid from coachtable where cid=?");
                PreparedStatement pst = connection.prepareStatement("select id from userpool where id=?");

                st.setString(1, String.format("CH%dR", i));
                pst.setString(1, String.format("CH%dR", i));
                ResultSet rs = st.executeQuery();
                ResultSet rst = pst.executeQuery();
                if (!(rs.next() || rst.next())) {
                    ans = String.format("CH%dR", i);
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public static String generateMemberID() {
        Connection connection = getConnect();
        String ans = "";
        for (int i = 100; i <= 999; i++) {
            try {
                PreparedStatement st = connection.prepareStatement("select uid from usertable where uid=?");
                PreparedStatement pst = connection.prepareStatement("select id from userpool where id=?");

                st.setString(1, String.format("MB%dX", i));
                pst.setString(1, String.format("MB%dX", i));
                ResultSet rs = st.executeQuery();
                ResultSet rst = pst.executeQuery();
                if (!rs.next()) {
                    if (!rst.next()) {
                        ans = String.format("MB%dX", i);
                        break;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public static boolean register_pool(Object values[]) {
        try {
            Connection con = getConnect();

            String query = ("insert into userpool values(?,?,?,?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, (String) values[0]);
            ps.setString(2, (String) values[1]);
            ps.setString(3, (String) values[2]);
            ps.setString(4, (String) values[3]);
            ps.setString(5, (String) values[4]);
            ps.setInt(6, Integer.parseInt((String) values[5]));
            ps.setString(7, (String) values[6]);
            System.out.println(ps.executeUpdate());

        } catch (Exception e) {
            System.out.println(e);
        }
        return true;
    }

    public static boolean checkValues(Object values[]) {
        for (int i = 0; i < values.length; i++) {
            String str = (String) values[i];
            if (str.isEmpty()) {
                return false;
            }
        }
        return true;
    }

    public static String getId(String input, String[] userType) {
        ResultSet rs;
        try {
            Connection connection = getConnect();
            PreparedStatement st = connection.prepareStatement(userType[0]);
            st.setString(1, input);
            st.setString(2, input);
            st.setString(3, input);
            rs = st.executeQuery();
            if (rs.next()) {
                return rs.getString(userType.equals(COACH_STRINGS) ? "cid" : "uid");
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        return null;
    }

    public static Object[] getUserFromUserPool(Object[] values) {
        try {
            Connection connection = getConnect();
            Object poolvalues[] = new Object[3];
            PreparedStatement st = connection.prepareStatement("select * from userpool where pnumber=? and email=?");
            st.setString(1, (String) values[5]);
            st.setString(2, (String) values[4]);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                poolvalues[0] = rs.getString("id");
                poolvalues[1] = rs.getString("cid");
                poolvalues[2] = rs.getInt("amount");
            }
            return poolvalues;
        } catch (SQLException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static Object[] getCoachFromUserPool(Object[] values) {
        try {
            Connection connection = getConnect();
            Object poolvalues[] = new Object[2];
            PreparedStatement st = connection.prepareStatement("select * from userpool where pnumber=? and email=?");
            st.setString(1, (String) values[5]);
            st.setString(2, (String) values[4]);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                poolvalues[0] = rs.getString("id");
                poolvalues[1] = rs.getInt("amount");
            }
            return poolvalues;
        } catch (SQLException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }

    }

    public static int deleteUser(String s) {
        try {
            Connection connection = getConnect();
            PreparedStatement ps = connection.prepareStatement("Delete from usertable where uid=?");
            ps.setString(1, s);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static int deleteCoach(String s) {
        try {
            Connection connection = getConnect();
            PreparedStatement ps = connection.prepareStatement("Delete from coachtable where cid=?");
            ps.setString(1, s);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static int deleteimg(String s) {
        try {
            Connection connection = getConnect();
            File f = new File("images/" + s + ".jpg");
            f.delete();
            PreparedStatement ps = connection.prepareStatement("Delete from imagetable where image=?");
            ps.setString(1, s);
            return ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }

    public static boolean createlogin(String logID, String uID) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("insert into logintable values(?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, logID);
            ps.setTime(2, Time.valueOf(LocalTime.now()));
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setString(4, uID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static boolean createlogout(String logID, String uID) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("insert into logouttable values(?,?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, logID);
            ps.setTime(2, Time.valueOf(LocalTime.now()));
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.setString(4, uID);
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }

    public static String generateLogID(String type) {
        Connection connection = getConnect();
        String ans = "";
        for (int i = 100; i <= 10000; i++) {
            try {
                PreparedStatement st = connection.prepareStatement("select logid from logintable where logid=?");
                st.setString(1, String.format("LG%d" + type, i));
                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    ans = String.format("LG%d" + type, i);
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }

    public static String getDuration(Time starttTime, Time endtTime) {

        long millis = endtTime.getTime() - starttTime.getTime();
        System.out.println(millis);
        return DurationFormatUtils.formatDuration(millis, "HH:mm:ss");

    }

    public static boolean addImageLog(String ID, String image) {
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            String query = ("insert into imagetable values(?,?,?)");
            PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, ID);
            ps.setString(2, image);
            ps.setDate(3, Date.valueOf(LocalDate.now()));
            ps.executeUpdate();
        } catch (Exception e) {
            System.out.println(e);

        }
        return false;
    }

    public static String generateImageID() {
        Connection connection = getConnect();
        String ans = "";
        for (int i = 100; i <= 10000; i++) {
            try {
                PreparedStatement st = connection.prepareStatement("select image from imagetable where image=?");
                st.setString(1, String.format("IG%dMB", i));
                ResultSet rs = st.executeQuery();
                if (!rs.next()) {
                    ans = String.format("IG%dMB", i);
                    break;
                }
            } catch (SQLException ex) {
                Logger.getLogger(DbTools.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return ans;
    }
}
