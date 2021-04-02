package GUI.OWNER;

import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class OTrackUser extends JFrame implements ActionListener {

    JLabel tlabel;
    static JLabel selectCoachLabel, totalDuration;
    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);
    JButton backButton = TemplateClass.getBackButton();

    static ArrayList<String> comboList = new ArrayList<>();
    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    static JComboBox coachComboBox = new JComboBox();
    JTable scheduleTable = new JTable();
    static DefaultTableModel model;

    static long totalTime = 0;

    public OTrackUser() {

        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel schedulePanel = new JPanel();

        tlabel = new JLabel("   Track USER   ");
        selectCoachLabel = new JLabel("Select User :");
        totalDuration = new JLabel("Total Duration");

        cc.add(header);
        cc.add(body);
        body.add(schedulePanel);
        cc.add(footer);
        footer.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            try {
                dispose();
            } catch (HeadlessException ex) {
                Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        scheduleTable.setPreferredSize(new Dimension(600, 288));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
        scheduleTable.getTableHeader().setResizingAllowed(false);
        scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        scheduleTable.getTableHeader().setBackground(new Color(216, 111, 147));
        scheduleTable.setBackground(new Color(185, 202, 222));
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 18));

        totalDuration.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane.setBounds(100, 195, 600, 320);
        scrollPane.setViewportView(scheduleTable);
        schedulePanel.add(scrollPane);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        model.addColumn("Log Id");
        model.addColumn("Login time");
        model.addColumn("Logout time");
        model.addColumn("Date");
        model.addColumn("Duration");

        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(35);

        selectCoachLabel.setFont(new Font("Arial", Font.BOLD, 20));

        tlabel.setBounds(255, 30, 300, 40);
        selectCoachLabel.setBounds(150, 80, 200, 50);
        coachComboBox.setBounds(310, 90, 300, 35);
        totalDuration.setBounds(100, 140, 200, 50);

        tlabel = (JLabel) TemplateClass.formatFont(tlabel, TextAttribute.UNDERLINE_ON, 28);

        body.add(tlabel);
        body.add(selectCoachLabel);
        body.add(totalDuration);
        body.add(coachComboBox);
        body.add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        coachComboBox.addActionListener(this);
        renderBox();
        renderTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == coachComboBox) {
            System.out.println("combo LISTENER");
            renderTable();
        }
    }

    public static void renderTable() {
        try {
            totalTime = 0;
            int index = coachComboBox.getSelectedIndex();
            model.setRowCount(0);
            Connection connection = DbTools.getConnect();
            PreparedStatement ps = connection.prepareStatement("select * from logintable where id=?");
            PreparedStatement pst = connection.prepareStatement("select * from logouttable where id=?");

            ps.setString(1, comboList.get(index));
            pst.setString(1, comboList.get(index));
            ResultSet rs = ps.executeQuery();
            ResultSet rst = pst.executeQuery();
            System.out.println("wait");
            while (rs.next() && rst.next()) {
                try {
                    System.out.println("Enter");
                    Time startTime = rs.getTime("logintime"), endTime = rst.getTime("logouttime");
                    model.addRow(new Object[]{rs.getString("logid"), startTime, endTime, rst.getDate("logdate"), DbTools.getDuration(startTime, endTime)});
                    totalTime += endTime.getTime() - startTime.getTime();
                } catch (SQLException ex) {
                    Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            totalDuration.setText("Total Duration :" + DurationFormatUtils.formatDuration(totalTime, "HH:mm:ss"));
        }
    }

    public static void renderBox() {
        coachComboBox.removeAllItems();
        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from Usertable");

            while (rs.next()) {
                String nameString = rs.getString("ufirstname");
                String idString = rs.getString("uid");
                comboList.add(idString);
                coachComboBox.addItem(idString + "  --->  " + nameString);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }
    }

}
