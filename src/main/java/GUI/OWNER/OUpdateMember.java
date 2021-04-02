package GUI.OWNER;

import TOOLS.PasswordPanel;
import TOOLS.TemplateClass;
import TOOLS.UpdateTools;
import TOOLS.validateOps;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

public class OUpdateMember extends JFrame implements ActionListener {

    String userType, formType;

    JLabel updatelabel, fName, lName, Age, Num, Mail, occupation, DateOfBirth, Address, Gender, pass, cPass, healthLabel, dynamicLabel;

    static JTextField fNameField, lNameField, AgeField, NumberField, MailField, healthField, dynamicField;

    static PasswordPanel passwordField = new PasswordPanel();
    PasswordPanel cPasswordField = new PasswordPanel();

    static ArrayList<String> comboList = new ArrayList<>();
    static JComboBox coachComboBox = new JComboBox();
    static DatePicker date = new DatePicker();
    static JPanel innerBody = new JPanel(null);

    static JComboBox occupationcombo;
    static JTextArea addressArea;
    static JComboBox<String> genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Others"});
    JButton updateButton;
    JScrollPane scrollPane;

    static Object values[] = new Object[11];

    public OUpdateMember() throws IOException {

        Container cc = getContentPane();

        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();

        JPanel footer = TemplateClass.getFooter();
        JButton backButton = TemplateClass.getBackButton();

        cc.add(header, BorderLayout.NORTH);
        cc.add(body, BorderLayout.WEST);
        cc.add(footer, BorderLayout.SOUTH);

        try {
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost/GYM31", "yogesh", "java");
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery("select * from usertable");

            while (rs.next()) {
                String nameString = rs.getString("ufirstname");
                String idString = rs.getString("uid");
                comboList.add(idString);
                coachComboBox.addItem(idString + "  --->  " + nameString);
            }

        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, e);
        }

        updatelabel = new JLabel("   Update   ");
        fName = new JLabel("Firstname");
        lName = new JLabel("Lastname");
        Age = new JLabel("Age");
        Num = new JLabel("Number");
        Mail = new JLabel("Mail");
        Address = new JLabel("Address");
        Gender = new JLabel("Gender");
        occupation = new JLabel("Occupation");
        DateOfBirth = new JLabel("DateOfBirth");
        pass = new JLabel("Password");
        cPass = new JLabel("Confirm Password");
        healthLabel = new JLabel("Any Health conditions ?");
        dynamicLabel = new JLabel("Experience");

        Gender = new JLabel("Gender");
        fNameField = new JTextField("Enter your firstname");
        lNameField = new JTextField("Enter your lastname");
        AgeField = new JTextField("Enter the Age");
        NumberField = new JTextField("Enter the Phone Number");
        MailField = new JTextField("Enter the Mail-id");
        healthField = new JTextField("Enter if any");
        dynamicField = new JTextField("Experience:");

        scrollPane = new JScrollPane(innerBody, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100, 120, 600, 460);
        scrollPane.setViewportView(innerBody);
        body.add(scrollPane);

        occupationcombo = new JComboBox(new String[]{"Self-Employed", "Student", "Private Sector", "Government Employee"});

        addressArea = new JTextArea("Enter the Address");
        updateButton = new JButton("Update");

        coachComboBox.setBounds(250, 60, 300, 35);
        updatelabel.setFont(new Font("Arial", Font.BOLD, 24));
        updatelabel.setBounds(320, 10, 300, 30);
        updatelabel = (JLabel) TemplateClass.formatFont(updatelabel, TextAttribute.UNDERLINE_ON, 28);

        footer.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            dispose();
        });
        body.add(updatelabel);
        body.add(coachComboBox);
        innerBody.add(fName);
        innerBody.add(fNameField);
        innerBody.add(lName);
        innerBody.add(lNameField);
        innerBody.add(Age);
        innerBody.add(AgeField);
        innerBody.add(Num);
        innerBody.add(NumberField);
        innerBody.add(Mail);
        innerBody.add(MailField);
        innerBody.add(occupation);
        innerBody.add(occupationcombo);
        innerBody.add(Gender);
        innerBody.add(genderComboBox);
        innerBody.add(Address);
        innerBody.add(addressArea);
        innerBody.add(DateOfBirth);
        innerBody.add(date);
        innerBody.add(pass);
        innerBody.add(passwordField);
        innerBody.add(cPass);
        innerBody.add(cPasswordField);
        innerBody.add(updateButton);
        innerBody.add(healthLabel);
        innerBody.add(healthField);
        innerBody.add(dynamicLabel);
        innerBody.add(dynamicField);
        innerBody.setPreferredSize(new Dimension(600, 800));
        innerBody.setBackground(Color.LIGHT_GRAY);

        fName.setBounds(70, 20, 200, 50);
        fNameField.setBounds(70, 60, 200, 50);
        lName.setBounds(300, 20, 200, 50);
        lNameField.setBounds(300, 60, 200, 50);
        Age.setBounds(70, 110, 200, 50);
        AgeField.setBounds(70, 150, 200, 50);
        DateOfBirth.setBounds(300, 110, 200, 50);
        date.setBounds(300, 150, 200, 50);
        Num.setBounds(300, 200, 200, 50);
        NumberField.setBounds(300, 240, 200, 50);
        Mail.setBounds(70, 200, 200, 50);
        MailField.setBounds(70, 240, 200, 50);
        occupation.setBounds(300, 290, 200, 50);
        occupationcombo.setBounds(300, 330, 200, 50);
        Gender.setBounds(70, 290, 200, 50);
        genderComboBox.setBounds(70, 330, 200, 50);
        Address.setBounds(70, 380, 200, 50);
        addressArea.setBounds(70, 420, 430, 80);
        healthLabel.setBounds(70, 500, 200, 50);
        healthField.setBounds(70, 540, 200, 50);
        dynamicLabel.setBounds(300, 500, 200, 50);
        dynamicField.setBounds(300, 540, 200, 50);
        pass.setBounds(70, 590, 200, 50);
        passwordField.setBounds(70, 630, 200, 50);
        cPass.setBounds(300, 590, 200, 50);
        cPasswordField.setBounds(300, 630, 200, 50);
        updateButton.setBounds(180, 700, 200, 50);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        updateButton.addActionListener(this);

        coachComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = coachComboBox.getSelectedIndex();
                Object vals[] = UpdateTools.fetchData(comboList.get(index), UpdateTools.USER_FETCH);
                setValues(vals);

            }
        });

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == updateButton) {
            try {
                values = OUpdateMember.getValues();
                if (passwordField.getpassword().equals(cPasswordField.getpassword())) {
                    if (validateOps.validatePassword(passwordField.getpassword())) {
                        if (validateOps.validateForm(values)) {

                            UpdateTools.updateuser(values);
                            JOptionPane.showMessageDialog(scrollPane, "Updated Sucessfully");
                            dispose();

                        } else {
                            JOptionPane.showMessageDialog(innerBody, "Enter valid data to proceed");
                        }
                    } else {
                        JOptionPane.showMessageDialog(innerBody, "Password should contain an uppercase,a lowercase,a number and a special character and 8 characters long");
                    }

                } else {

                    JOptionPane.showMessageDialog(innerBody, "Passwords do not match");
                }

            } catch (HeadlessException ex) {
                System.out.println(ex);
            }
        }

    }

    static Object[] getValues() {
        Object values[] = new Object[13];
        values[0] = fNameField.getText();
        values[1] = lNameField.getText();
        values[2] = AgeField.getText();
        values[3] = date.getDate().toString();
        values[4] = MailField.getText();
        values[5] = NumberField.getText();
        values[6] = genderComboBox.getSelectedItem();
        values[7] = occupationcombo.getSelectedItem();
        values[8] = addressArea.getText();
        values[9] = healthField.getText();
        values[10] = dynamicField.getText();
        values[11] = passwordField.getpassword();
        values[12] = comboList.get(coachComboBox.getSelectedIndex());
        return values;
    }

    static void setValues(Object vals[]) {
        fNameField.setText((String) vals[0]);
        lNameField.setText((String) vals[1]);
        AgeField.setText((String) vals[2]);
        date.setDate(LocalDate.parse((String) vals[3]));
        MailField.setText((String) vals[4]);
        NumberField.setText((String) vals[5]);
        genderComboBox.setSelectedItem(vals[6]);
        occupationcombo.setSelectedItem(vals[7]);
        addressArea.setText((String) vals[8]);
        healthField.setText((String) vals[9]);
        dynamicField.setText((String) vals[10]);

    }
}
