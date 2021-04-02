package GUI.COACH;

import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.apache.commons.lang3.time.DurationFormatUtils;

public class CTrackrecord extends JFrame {

    JLabel tlabel;
    static JLabel totalDuration;
    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);
    JButton backButton = TemplateClass.getBackButton();

    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JTable scheduleTable = new JTable();
    static DefaultTableModel model;

    static long totalTime = 0;
    static String id;

    public CTrackrecord(String ID) {
        id = ID;
        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel schedulePanel = new JPanel();

        tlabel = new JLabel("   Track RECORD   ");
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
                Logger.getLogger(CTrackUser.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        scheduleTable.setPreferredSize(new Dimension(600, 388));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
        scheduleTable.getTableHeader().setResizingAllowed(false);
        scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        scheduleTable.getTableHeader().setBackground(new Color(216, 111, 147));
        scheduleTable.setBackground(new Color(185, 202, 222));
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 18));

        totalDuration.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane.setBounds(100, 165, 600, 360);
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

        tlabel.setBounds(255, 30, 300, 40);
        totalDuration.setBounds(100, 110, 200, 50);

        tlabel = (JLabel) TemplateClass.formatFont(tlabel, TextAttribute.UNDERLINE_ON, 28);

        body.add(tlabel);
        body.add(totalDuration);
        body.add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        renderTable();
    }

    public static void renderTable() {
        try {
            totalTime = 0;
            model.setRowCount(0);
            Connection connection = DbTools.getConnect();
            PreparedStatement ps = connection.prepareStatement("select * from logintable where id=?");
            PreparedStatement pst = connection.prepareStatement("select * from logouttable where id=?");

            ps.setString(1, id);
            pst.setString(1, id);
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
                    Logger.getLogger(CTrackUser.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        } catch (SQLException ex) {
            Logger.getLogger(CTrackUser.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            totalDuration.setText("Total Duration :" + DurationFormatUtils.formatDuration(totalTime, "HH:mm:ss"));
        }
    }

}
