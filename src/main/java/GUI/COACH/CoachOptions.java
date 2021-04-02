package GUI.COACH;

import PAGES.Login;
import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class CoachOptions extends JFrame {

    JLabel greetLabel;
    JLabel userOperations;
    JLabel coachOpertions;

    JButton addMemberButton;
    JButton updateCoachButton, updateMemberButton;
    JButton trackCoachButton, trackMemberButtton;
    JButton deleteMemberButton;

    static String id, LOG_ID;

    public CoachOptions(String ID, String logID) throws HeadlessException, IOException {
        id = ID;
        LOG_ID = logID;
        System.out.println(id);
        setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel userBody = new JPanel();
        JPanel coachBody = new JPanel();
        JPanel footer = TemplateClass.getFooter();
        JButton logoutButton = new JButton();

        cc.add(header);
        cc.add(body);
        body.add(userBody);
        body.add(coachBody);
        cc.add(footer);

        greetLabel = new JLabel("  " + Login.COACH + "  ");

        userOperations = new JLabel("Member Operations");
        userOperations = (JLabel) TemplateClass.formatFont(userOperations, TextAttribute.UNDERLINE_ON, 20);
        coachOpertions = new JLabel("Coach Operations");
        coachOpertions = (JLabel) TemplateClass.formatFont(coachOpertions, TextAttribute.UNDERLINE_ON, 20);

        addMemberButton = new JButton((char) 8226 + " Add Member");
        updateCoachButton = new JButton((char) 8226 + " Update details");
        updateMemberButton = new JButton((char) 8226 + " Update Member");
        trackCoachButton = new JButton((char) 8226 + " Trackrecord");
        trackMemberButtton = new JButton((char) 8226 + " Track Member");
        deleteMemberButton = new JButton((char) 8226 + " Delete Member");

        greetLabel.setFont(new Font("Arial", Font.BOLD, 24));
        greetLabel = (JLabel) TemplateClass.formatFont(greetLabel, TextAttribute.UNDERLINE_ON, 28);

        userBody.setBounds(70, 80, 640, 270);
        coachBody.setBounds(70, 370, 640, 200);

        userBody.setBackground(Color.CYAN);
        coachBody.setBackground(Color.cyan);

        greetLabel.setBounds(300, 20, 220, 30);

        userOperations.setBounds(20, 20, 200, 30);
        coachOpertions.setBounds(20, 20, 200, 30);

        addMemberButton.setBounds(105, 90, 150, 50);
        updateMemberButton.setBounds(105, 160, 150, 50);
        trackMemberButtton.setBounds(380, 160, 150, 50);
        deleteMemberButton.setBounds(380, 90, 150, 50);
        trackCoachButton.setBounds(105, 90, 150, 50);
        updateCoachButton.setBounds(380, 90, 150, 50);

        userBody.add(userOperations);
        userBody.add(addMemberButton);
        userBody.add(updateMemberButton);
        userBody.add(trackMemberButtton);
        userBody.add(deleteMemberButton);

        coachBody.add(coachOpertions);
        coachBody.add(trackCoachButton);
        coachBody.add(updateCoachButton);

        body.add(greetLabel);
        footer.add(logoutButton);
        logoutButton.setText("Logout!");
        logoutButton.setBounds(330, 10, 100, 32);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        new Login(Login.COACH);
                        DbTools.createlogout(LOG_ID, id);
                        dispose();
                    }
                });
            }
        });

        userBody.setLayout(null);
        coachBody.setLayout(null);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CAddMember(id);

            }
        });
        deleteMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TimeLine(id);
            }
        });

        updateMemberButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CUpdateMember(id);
                } catch (IOException ex) {
                    Logger.getLogger(CoachOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        updateCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new CUpdateDetails(id);
                } catch (IOException ex) {
                    Logger.getLogger(CoachOptions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        trackCoachButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CTrackrecord(id);
            }
        });
        trackMemberButtton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new CTrackUser(id);
            }
        });
    }

}
