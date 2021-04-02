package PAGES;

import GUI.COACH.CoachOptions;
import GUI.OWNER.AdminOptions;
import GUI.USER.UserOptions;
import TOOLS.DbTools;
import TOOLS.PasswordPanel;
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
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Login extends JFrame implements ActionListener {

    public static final String ADMIN = "Hello Admin !";
    public static final String COACH = "Hello Coach !";
    public static final String USER = "  Hello User !";

    JLabel log;
    JLabel idText;
    JLabel passText;
    JLabel greet;
    JButton reset;

    JTextField IdField;
    PasswordPanel PasswordField;

    JButton login;
    JButton signup;
    String userType;

    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);
    JButton backButton = TemplateClass.getBackButton();

    public Login(String userType) {

        this.userType = userType;
        Container cc = getContentPane();
        cc.setLayout(null);

        cc.add(header);
        cc.add(body);
        cc.add(footer);

        log = new JLabel("Login Page");
        idText = new JLabel("Email ID/ Number/ ID");
        passText = new JLabel("Password");
        greet = new JLabel("  " + userType + "  ");

        IdField = new JTextField();
        PasswordField = new PasswordPanel(20);

        reset = new JButton("forgot password?");
        login = new JButton("Login");
        signup = new JButton("SignUp");

        log = (JLabel) TemplateClass.formatFont(log, TextAttribute.UNDERLINE_ON, 30);
        greet = (JLabel) TemplateClass.formatFont(greet, TextAttribute.UNDERLINE_ON, 28);
        reset = (JButton) TemplateClass.formatFont(reset, TextAttribute.UNDERLINE_ON, 18);

        reset.setForeground(Color.red);
        reset.setBackground(null);
        reset.setBorder(null);

        log.setBounds(300, 20, 200, 50);
        greet.setBounds(85, 20, 215, 50);
        idText.setBounds(70, 90, 280, 50);
        IdField.setBounds(70, 140, 250, 50);
        passText.setBounds(70, 190, 160, 50);
        PasswordField.setBounds(70, 230, 250, 50);
        reset.setBounds(95, 290, 200, 30);
        login.setBounds(130, 340, 130, 30);
        signup.setBounds(130, 390, 130, 30);

        innerPanel.setBackground(Color.CYAN);

        header.setSize(800, 100);
        innerPanel.setBounds(190, 80, 400, 470);

        log.setFont(new Font("Arial", Font.BOLD, 32));
        log.setForeground(new Color(28, 50, 180));

        idText.setFont(new Font("Arial", Font.BOLD, 18));
        idText.setForeground(new Color(27, 102, 152));
        IdField.setFont(new Font("Arial", Font.PLAIN, 20));

        passText.setFont(new Font("Arial", Font.BOLD, 18));
        passText.setForeground(new Color(27, 102, 152));
        PasswordField.setFont(new Font("Arial", Font.BOLD, 20));

        login.setFont(new Font("Perpetua", Font.BOLD, 24));
        login.setBackground(new Color(115, 227, 109));
        login.setForeground(Color.darkGray);

        signup.setFont(new Font("Perpetua", Font.BOLD, 24));
        signup.setBackground(Color.red);
        signup.setForeground(new Color(253, 182, 32));
        if (userType == ADMIN) {
            signup.setVisible(false);
        }

        greet.setForeground(Color.GRAY);

        IdField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));
        PasswordField.setBorder(BorderFactory.createLineBorder(Color.GRAY, 2));

        body.add(log);
        body.add(innerPanel);
        innerPanel.add(greet);
        innerPanel.add(idText);
        innerPanel.add(IdField);
        innerPanel.add(passText);
        innerPanel.add(PasswordField);
        innerPanel.add(login);
        innerPanel.add(signup);
        innerPanel.add(reset);
        footer.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            new UserType();
            dispose();
        });

        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        try {
            setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        login.addActionListener(this);
        signup.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == login) {
            {
                if (userType.equals(ADMIN)) {
                    try {
                        int res = DbTools.authUser(IdField.getText(), PasswordField.getpassword(), DbTools.ADMIN_STRINGS, header);
                        if (res == 1) {
                            String templogID = DbTools.generateLogID("AD");
                            DbTools.createlogin(templogID, "AD001A");
                            new AdminOptions(templogID);
                            dispose();
                        }
                        if (res == 0) {
                            JOptionPane.showMessageDialog(innerPanel, "Invalid UserID or Password");
                        }
                    } catch (HeadlessException | IOException ex) {
                        System.out.println(ex);
                    }

                }
                if (userType.equals(COACH)) {
                    try {
                        int res = DbTools.authUser(IdField.getText(), PasswordField.getpassword(), DbTools.COACH_STRINGS, innerPanel);
                        if (res == 1) {
                            String templogID = DbTools.generateLogID("CH");
                            String tempusrID = DbTools.getId(IdField.getText(), DbTools.COACH_STRINGS);
                            DbTools.createlogin(templogID, tempusrID);
                            new CoachOptions(tempusrID, templogID);
                            dispose();
                        }
                        if (res == 0) {
                            JOptionPane.showMessageDialog(innerPanel, "Invalid UserID or Password");
                        }
                    } catch (HeadlessException | IOException ex) {
                        System.out.println(ex);
                    }

                }
                if (userType.equals(USER)) {
                    try {
                        int res = DbTools.authUser(IdField.getText(), PasswordField.getpassword(), DbTools.USER_STRINGS, innerPanel);
                        if (res == 1) {
                            String templogID = DbTools.generateLogID("MB");
                            String tempusrID = DbTools.getId(IdField.getText(), DbTools.USER_STRINGS);
                            DbTools.createlogin(templogID, tempusrID);
                            new UserOptions(tempusrID, templogID);
                            dispose();
                        }
                        if (res == 0) {
                            JOptionPane.showMessageDialog(innerPanel, "Invalid UserID or Password");
                        }
                    } catch (HeadlessException | IOException ex) {
                        System.out.println(ex);
                    }

                }
            }

        } else if (e.getSource() == signup) {
            try {
                new Register(userType, "SignUp");
                dispose();
            } catch (IOException ex) {
                System.out.println(ex);
            }
        }
    }

    public static void main(String[] args) {
        new Login(USER);
    }
}
