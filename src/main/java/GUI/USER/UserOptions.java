package GUI.USER;

import PAGES.Login;
import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UserOptions extends JFrame {

    JLabel greetLabel;
    JLabel userOperations;
    JLabel timeTable;

    JButton updateMemberButton;
    JButton trackMemberButtton;
    static String id, LOG_ID;

    public UserOptions(String ID, String logID) throws HeadlessException, IOException {
        id = ID;
        LOG_ID = logID;
        setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel userBody = new JPanel();
        JPanel schedulePanel = new JPanel();
        schedulePanel.setLayout(null);
        JPanel footer = TemplateClass.getFooter();
        JButton logOutButton = new JButton("Logout !");

        cc.add(header);
        cc.add(body);
        body.add(userBody);
        body.add(schedulePanel);
        cc.add(footer);

        greetLabel = new JLabel("  " + Login.USER + "  ");
        JTable scheduleTable = new JTable();
        scheduleTable.setPreferredSize(new Dimension(600, 288));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
        scheduleTable.getTableHeader().setResizingAllowed(false);
        scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        scheduleTable.getTableHeader().setBackground(new Color(216, 111, 147));
        scheduleTable.setBackground(new Color(185, 202, 222));
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 18));
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(20, 30, 600, 320);
        scrollPane.setViewportView(scheduleTable);
        schedulePanel.add(scrollPane);
        DefaultTableModel model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        model.addColumn("Day", new Object[]{"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"});
        model.addColumn("Workout", new Object[]{"Chest/Abs", "Back", "off", "shoulders/Abs", "legs", "Arms/abs", "off"});

        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(41);

        userOperations = new JLabel("Member Operations");
        userOperations = (JLabel) TemplateClass.formatFont(userOperations, TextAttribute.UNDERLINE_ON, 20);

        updateMemberButton = new JButton((char) 8226 + " Update details");
        trackMemberButtton = new JButton((char) 8226 + " Track Me !");

        greetLabel.setFont(new Font("Arial", Font.BOLD, 24));
        greetLabel = (JLabel) TemplateClass.formatFont(greetLabel, TextAttribute.UNDERLINE_ON, 28);
        schedulePanel.setBounds(70, 70, 640, 370);
        userBody.setBounds(70, 445, 640, 100);

        userBody.setBackground(Color.CYAN);

        greetLabel.setBounds(300, 20, 220, 30);

        userOperations.setBounds(20, 20, 200, 30);

        updateMemberButton.setBounds(105, 25, 150, 50);
        trackMemberButtton.setBounds(380, 25, 150, 50);

        userBody.add(updateMemberButton);
        userBody.add(trackMemberButtton);

        body.add(greetLabel);
        footer.add(logOutButton);
        logOutButton.setBounds(330, 10, 100, 32);
        logOutButton.addActionListener((ActionEvent e) -> {
            try {
                SwingUtilities.invokeLater(() -> {
                    new Login(Login.USER);
                    DbTools.createlogout(LOG_ID, id);
                    dispose();
                });
            } catch (HeadlessException ex) {
                Logger.getLogger(UserOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        userBody.setLayout(null);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        updateMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new UUpdateDetails(id);
                } catch (IOException ex) {
                    Logger.getLogger(UserOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        trackMemberButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new UTrackrecord(id);
            }
        });

    }

}
