package GUI.OWNER;

import TOOLS.DbTools;
import TOOLS.HintTextField;
import TOOLS.TemplateClass;
import TOOLS.validateOps;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class OAddMember extends JFrame implements ActionListener {

    static JLabel addMemberLabel, fnameLabel, lnameLabel, numberLabel, emailLabel, feeLabel, idLabel;
    static JTextField fnameField, lnameField, numField, emailField, feeField;
    JButton update;

    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);
    JButton backButton = TemplateClass.getBackButton();
    JPanel scrollPanel = new JPanel(null);
    JScrollPane scrollPane = new JScrollPane(scrollPanel, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
    static final String memberID = DbTools.generateMemberID();
    static String coachID;

    static Object values[] = new Object[7];
    static JComboBox coachComboBox = new JComboBox();
    static ArrayList<String> comboList = new ArrayList<>();

    public OAddMember() {
        Container cc = getContentPane();
        cc.setLayout(null);

        cc.add(header);
        cc.add(body);
        cc.add(footer);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from coachtable");

            while (rs.next()) {
                String nameString = rs.getString("cfirstname");
                String idString = rs.getString("cid");
                comboList.add(idString);
                coachComboBox.addItem(idString + "  --->  " + nameString);
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

        addMemberLabel = new JLabel("   AddMember   ");
        fnameLabel = new JLabel("Firstname");
        lnameLabel = new JLabel("Lastname");
        numberLabel = new JLabel("Number");
        emailLabel = new JLabel("Email Id");
        feeLabel = new JLabel("Fee");
        idLabel = new JLabel("ID : " + memberID);
        fnameField = new HintTextField("Enter Firstname");
        lnameField = new HintTextField("Enter Lastname");
        numField = new HintTextField("Enter Member Number");
        emailField = new HintTextField("Enter Member Email");
        feeField = new HintTextField("Enter Member Fee");

        update = new JButton("Update");

        addMemberLabel = (JLabel) TemplateClass.formatFont(addMemberLabel, TextAttribute.UNDERLINE_ON, 30);
        idLabel = (JLabel) TemplateClass.formatFont(idLabel, TextAttribute.UNDERLINE_ON, 26);

        innerPanel.setBounds(130, 80, 540, 530);
        scrollPane.setBounds(50, 40, 450, 350);
        scrollPane.setViewportView(scrollPanel);
        addMemberLabel.setBounds(300, 20, 250, 50);

        coachComboBox.setBounds(120, 30, 200, 40);
        fnameLabel.setBounds(120, 70, 200, 40);
        fnameField.setBounds(120, 110, 200, 40);
        lnameLabel.setBounds(120, 150, 200, 40);
        lnameField.setBounds(120, 190, 200, 40);
        numberLabel.setBounds(120, 230, 200, 40);
        numField.setBounds(120, 270, 200, 40);
        emailLabel.setBounds(120, 310, 200, 40);
        emailField.setBounds(120, 350, 200, 40);
        feeLabel.setBounds(120, 390, 200, 40);
        feeField.setBounds(120, 430, 200, 40);
        idLabel.setBounds(140, 475, 200, 40);

        update.setBounds(170, 410, 200, 40);

        body.add(addMemberLabel);
        body.add(innerPanel);
        innerPanel.add(scrollPane);
        scrollPanel.add(coachComboBox);
        scrollPanel.add(fnameLabel);
        scrollPanel.add(fnameField);
        scrollPanel.add(lnameLabel);
        scrollPanel.add(lnameField);
        scrollPanel.add(numberLabel);
        scrollPanel.add(numField);
        scrollPanel.add(emailLabel);
        scrollPanel.add(emailField);
        scrollPanel.add(feeLabel);
        scrollPanel.add(feeField);
        scrollPanel.add(idLabel);

        scrollPanel.setPreferredSize(new Dimension(450, 530));
        innerPanel.add(update);
        footer.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            try {
                dispose();
            } catch (HeadlessException ex) {
                Logger.getLogger(OAddMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        setSize(800, 800);
        setLocationRelativeTo(null);
        scrollPanel.setLayout(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        update.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == update) {
            OAddMember.getValues();
            if (DbTools.checkValues(values)) {
                if (validateOps.validateNumber((String) values[3]) && validateOps.validateEmail((String) values[4])) {
                    DbTools.register_pool(values);
                    JOptionPane.showMessageDialog(scrollPanel, "registration successful");
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(scrollPanel, "Enter valid data");
                }
            } else {
                JOptionPane.showMessageDialog(scrollPanel, "Please Complete the form before tyring again");
            }
        }
    }

    static void getValues() {
        values[0] = memberID;
        values[1] = lnameField.getText();
        values[2] = fnameField.getText();
        values[3] = numField.getText();
        values[4] = emailField.getText();
        values[5] = feeField.getText();
        values[6] = comboList.get(coachComboBox.getSelectedIndex());

    }

}
