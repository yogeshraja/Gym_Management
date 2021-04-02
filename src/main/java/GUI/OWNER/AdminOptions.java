package GUI.OWNER;

import PAGES.Login;
import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
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

public class AdminOptions extends JFrame {

    JLabel greetLabel, updateLabel, deleteLabel;
    JLabel manageLabel;
    JLabel trackLabel;

    JButton addCoachButton, addMemberButton;
    JButton updateCoachButton, updateMemberButton;
    JButton trackCoachButton, trackMemberButtton;
    JButton deleteCoachButton, deleteMemberButton;

    static String LOG_ID;

    public AdminOptions(String logID) throws HeadlessException, IOException {
        LOG_ID = logID;
        setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel manageBody = new JPanel();
        JPanel trackBody = new JPanel();
        JPanel updateBody = new JPanel();
        JPanel Deletebody = new JPanel();
        JPanel footer = TemplateClass.getFooter();
        JButton logoutButton = new JButton("LOgout !");

        cc.add(header);
        cc.add(body);
        body.add(manageBody);
        body.add(trackBody);
        body.add(updateBody);
        body.add(Deletebody);
        cc.add(footer);

        greetLabel = new JLabel("  " + Login.ADMIN + "  ");

        manageLabel = new JLabel("Manage");
        manageLabel = (JLabel) TemplateClass.formatFont(manageLabel, TextAttribute.UNDERLINE_ON, 20);
        trackLabel = new JLabel("Track");
        trackLabel = (JLabel) TemplateClass.formatFont(trackLabel, TextAttribute.UNDERLINE_ON, 20);
        updateLabel = new JLabel("Update");
        updateLabel = (JLabel) TemplateClass.formatFont(updateLabel, TextAttribute.UNDERLINE_ON, 20);
        deleteLabel = new JLabel("Delete");
        deleteLabel = (JLabel) TemplateClass.formatFont(deleteLabel, TextAttribute.UNDERLINE_ON, 20);

        addCoachButton = new JButton((char) 8226 + " Add Coach");
        addMemberButton = new JButton((char) 8226 + " Add Member");
        updateCoachButton = new JButton((char) 8226 + " Update Coach");
        updateMemberButton = new JButton((char) 8226 + " Update Member");
        trackCoachButton = new JButton((char) 8226 + " TrackCoach");
        trackMemberButtton = new JButton((char) 8226 + " TrackMember");
        deleteCoachButton = new JButton((char) 8226 + " Delete Coach");
        deleteMemberButton = new JButton((char) 8226 + " Delete Member");

        greetLabel.setFont(new Font("Arial", Font.BOLD, 24));
        greetLabel = (JLabel) TemplateClass.formatFont(greetLabel, TextAttribute.UNDERLINE_ON, 28);

        manageBody.setBounds(70, 70, 300, 250);
        trackBody.setBounds(420, 70, 300, 250);
        updateBody.setBounds(70, 330, 300, 250);
        Deletebody.setBounds(420, 330, 300, 250);

        manageBody.setBackground(Color.CYAN);
        trackBody.setBackground(Color.CYAN);
        updateBody.setBackground(Color.CYAN);
        Deletebody.setBackground(Color.cyan);

        greetLabel.setBounds(300, 20, 220, 30);

        manageLabel.setBounds(20, 20, 150, 30);
        trackLabel.setBounds(20, 20, 150, 30);
        updateLabel.setBounds(20, 20, 150, 30);
        deleteLabel.setBounds(20, 20, 150, 30);

        addCoachButton.setBounds(75, 90, 150, 50);
        addMemberButton.setBounds(75, 160, 150, 50);
        updateCoachButton.setBounds(75, 90, 150, 50);
        updateMemberButton.setBounds(75, 160, 150, 50);
        trackCoachButton.setBounds(75, 90, 150, 50);
        trackMemberButtton.setBounds(75, 160, 150, 50);
        deleteCoachButton.setBounds(75, 90, 150, 50);
        deleteMemberButton.setBounds(75, 160, 150, 50);

        manageBody.add(manageLabel);
        manageBody.add(addCoachButton);
        manageBody.add(addMemberButton);
        trackBody.add(trackLabel);
        trackBody.add(trackCoachButton);
        trackBody.add(trackMemberButtton);
        updateBody.add(updateLabel);
        updateBody.add(updateCoachButton);
        updateBody.add(updateMemberButton);
        Deletebody.add(deleteLabel);
        Deletebody.add(deleteCoachButton);
        Deletebody.add(deleteMemberButton);

        body.add(greetLabel);
        footer.add(logoutButton);
        logoutButton.setText("Logout!");
        logoutButton.setBounds(345, 10, 100, 32);
        logoutButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(() -> {

                new Login(Login.ADMIN);
                DbTools.createlogout(LOG_ID, "AD001A");
                dispose();
            });
        });

        manageBody.setLayout(null);
        trackBody.setLayout(null);
        updateBody.setLayout(null);
        Deletebody.setLayout(null);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addCoachButton.addActionListener((ActionEvent e) -> {
            new OAddCoach();
        });

        addMemberButton.addActionListener((ActionEvent e) -> {
            new OAddMember();
        });

        updateCoachButton.addActionListener((ActionEvent e) -> {
            try {
                new OUpdateCoach();
            } catch (IOException ex) {
                Logger.getLogger(AdminOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        updateMemberButton.addActionListener((ActionEvent e) -> {
            try {
                new OUpdateMember();
            } catch (IOException ex) {
                Logger.getLogger(AdminOptions.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        deleteCoachButton.addActionListener((ActionEvent e) -> {
            new ODeleteCoach();
        });
        deleteMemberButton.addActionListener((ActionEvent e) -> {
            new ODeleteMember();
        });
        trackCoachButton.addActionListener((ActionEvent e) -> {
            new OTrackCoach();
        });
        trackMemberButtton.addActionListener((ActionEvent e) -> {
            new OTrackUser();
        });

    }

}
