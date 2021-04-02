package PAGES;

import TOOLS.TemplateClass;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.font.TextAttribute;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ForgotPassword extends JFrame {

    Container cc = getContentPane();
    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);

    JLabel forgetpassLabel, securityqueLabel, que1, que2;
    JTextField que1Field, que2Field;

    public ForgotPassword() throws HeadlessException {

        cc.add(header);
        cc.add(body);
        cc.add(footer);
        body.add(innerPanel);
        JButton backButton = TemplateClass.getBackButton();
        footer.add(backButton);

        forgetpassLabel = new JLabel(java.util.ResourceBundle.getBundle("com/mycompany/gymcomp/Bundle").getString("  FORGOT PASSWORD  "));
        forgetpassLabel = (JLabel) TemplateClass.formatFont(forgetpassLabel, TextAttribute.UNDERLINE_ON, 32);
        securityqueLabel = new JLabel(java.util.ResourceBundle.getBundle("com/mycompany/gymcomp/Bundle").getString("  SECURITY QUESTION  "));
        securityqueLabel = (JLabel) TemplateClass.formatFont(securityqueLabel, TextAttribute.UNDERLINE_ON, 32);
        que1 = new JLabel(java.util.ResourceBundle.getBundle("com/mycompany/gymcomp/Bundle").getString("WHICH IS YOUR FAVOURITE DESTINATION THAT YOU WOULD LIKE TO VISIT??"));
        que2 = new JLabel(java.util.ResourceBundle.getBundle("com/mycompany/gymcomp/Bundle").getString("WHAT IS YOUR FAVOURITE DISH??"));
        que1Field = new JTextField();
        que2Field = new JTextField();
        JButton continueButton = new JButton(java.util.ResourceBundle.getBundle("com/mycompany/gymcomp/Bundle").getString("CONTINUE"));

        innerPanel.setBounds(100, 100, 600, 400);
        forgetpassLabel.setBounds(260, 30, 300, 40);
        securityqueLabel.setBounds(140, 30, 350, 40);
        que1.setBounds(100, 90, 450, 40);
        que1Field.setBounds(100, 130, 400, 40);
        que2.setBounds(100, 180, 300, 40);
        que2Field.setBounds(100, 220, 400, 40);
        continueButton.setBounds(200, 300, 200, 40);

        body.add(forgetpassLabel);
        innerPanel.add(securityqueLabel);
        innerPanel.add(que1);
        innerPanel.add(que1Field);
        innerPanel.add(que2);
        innerPanel.add(que2Field);
        innerPanel.add(continueButton);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

}
