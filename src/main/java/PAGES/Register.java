package PAGES;

import TOOLS.DbTools;
import TOOLS.HintAreaField;
import TOOLS.HintTextField;
import TOOLS.PasswordPanel;
import TOOLS.TemplateClass;
import TOOLS.validateOps;
import com.github.lgooddatepicker.components.DatePicker;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Register extends JFrame implements ActionListener {

    String userType, formType;

    static JLabel Signup, fName, lName, Age, Num, Mail, occupation, DateOfBirth, Address, Gender, pass, cPass, healthLabel, dynamicLabel;

    static JTextField fNameField, lNameField, AgeField, NumberField, MailField, healthField, dynamicField;

    PasswordPanel passwordField = new PasswordPanel();
    PasswordPanel cPasswordField = new PasswordPanel();

    static JComboBox occupationcombo, genderComboBox;
    static HintAreaField addressArea;
    static JButton signUpButton;
    static JScrollPane scrollPane;

    JPanel innerBody = new JPanel(null);

    static Object values[] = new Object[12];
    static DatePicker date;

    public Register(String userType, String formType) throws IOException {
        this.userType = userType;
        this.formType = formType;
        Container cc = getContentPane();

        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();

        JPanel footer = TemplateClass.getFooter();
        JButton backButton = TemplateClass.getBackButton();

        cc.add(header, BorderLayout.NORTH);
        cc.add(body, BorderLayout.WEST);
        cc.add(footer, BorderLayout.SOUTH);

        Signup = new JLabel("   " + formType + "   ");
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
        dynamicLabel = new JLabel((userType.equals(Login.USER) ? "Goal" : "Experience"));

        date = new DatePicker();
        date.setDateToToday();
        Gender = new JLabel("Gender");
        fNameField = new HintTextField("Enter your firstname");
        lNameField = new HintTextField("Enter your lastname");
        AgeField = new HintTextField("Enter the Age");
        NumberField = new HintTextField("Enter the Phone Number");
        MailField = new HintTextField("Enter the Mail-id");
        healthField = new HintTextField("Enter if any");
        dynamicField = new HintTextField(userType.equals(Login.USER) ? "Goal" : "Experience:");

        scrollPane = new JScrollPane(innerBody, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBounds(100, 80, 600, 470);
        scrollPane.setViewportView(innerBody);
        body.add(scrollPane);

        occupationcombo = new JComboBox(new String[]{"Self-Employed", "Student", "Private Sector", "Government Employee"});
        genderComboBox = new JComboBox<>(new String[]{"Male", "Female", "Others"});

        addressArea = new HintAreaField("Enter the Address");
        signUpButton = new JButton(formType);

        Signup.setFont(new Font("Arial", Font.BOLD, 24));
        Signup.setBounds(310, 30, 200, 30);
        Signup = (JLabel) TemplateClass.formatFont(Signup, TextAttribute.UNDERLINE_ON, 28);

        footer.add(backButton);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    new Login(userType);
                } catch (HeadlessException ex) {
                    System.out.println(ex);
                }
                dispose();
            }
        });
        body.add(Signup);
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
        innerBody.add(signUpButton);
        innerBody.add(healthLabel);
        innerBody.add(healthField);
        innerBody.add(dynamicLabel);
        innerBody.add(dynamicField);
        innerBody.setPreferredSize(new Dimension(600, 800));
        innerBody.setBackground(Color.LIGHT_GRAY);

        fName.setBounds(70, 50, 200, 50);
        fNameField.setBounds(70, 90, 200, 50);
        lName.setBounds(300, 50, 200, 50);
        lNameField.setBounds(300, 90, 200, 50);
        Age.setBounds(70, 140, 200, 50);
        AgeField.setBounds(70, 180, 200, 50);
        DateOfBirth.setBounds(300, 140, 200, 50);
        date.setBounds(300, 180, 200, 50);
        Num.setBounds(300, 230, 200, 50);
        NumberField.setBounds(300, 270, 200, 50);
        Mail.setBounds(70, 230, 200, 50);
        MailField.setBounds(70, 270, 200, 50);
        occupation.setBounds(300, 320, 200, 50);
        occupationcombo.setBounds(300, 360, 200, 50);
        Gender.setBounds(70, 320, 200, 50);
        genderComboBox.setBounds(70, 360, 200, 50);
        Address.setBounds(70, 410, 200, 50);
        addressArea.setBounds(70, 450, 430, 80);
        healthLabel.setBounds(70, 530, 200, 50);
        healthField.setBounds(70, 570, 200, 50);
        dynamicLabel.setBounds(300, 530, 200, 50);
        dynamicField.setBounds(300, 570, 200, 50);
        pass.setBounds(70, 620, 200, 50);
        passwordField.setBounds(70, 660, 200, 50);
        cPass.setBounds(300, 620, 200, 50);
        cPasswordField.setBounds(300, 660, 200, 50);
        signUpButton.setBounds(180, 730, 200, 50);

        setSize(800, 800);
        setLocationRelativeTo(null);
        setIconImage(ImageIO.read(new File("resource/yLogo.png")));
        setLayout(null);
        setResizable(false);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        signUpButton.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == signUpButton) {
            try {
                values = Register.getValues(passwordField.getpassword());

                if (checkValues(values)) {
                    if (passwordField.getpassword().equals(cPasswordField.getpassword())) {
                        if (validateOps.validatePassword(passwordField.getpassword())) {
                            if (validateOps.validateForm(values)) {
                                if (userType.equals(Login.COACH)) {
                                    DbTools.register_coach(values);
                                    new Login(userType);
                                    dispose();
                                }
                                if (userType.equals(Login.USER)) {
                                    DbTools.register_user(values);
                                    new Login(userType);
                                    dispose();
                                }

                            } else {
                                JOptionPane.showMessageDialog(innerBody, "Enter valid data to proceed");
                            }
                        } else {
                            JOptionPane.showMessageDialog(innerBody, "Password should contain an uppercase,a lowercase,a number and a special character and 8 characters long");
                        }

                    } else {

                        JOptionPane.showMessageDialog(innerBody, "Passwords do not match");
                    }
                } else {
                    JOptionPane.showMessageDialog(innerBody, "Please complete the form before trying again");
                }

            } catch (HeadlessException ex) {
                System.out.println(ex);
            }
        }

    }

    static Object[] getValues(String pass) {
        Object values[] = new Object[12];
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
        values[11] = pass;
        return values;
    }

    static boolean checkValues(Object values[]) {
        for (int i = 0; i < values.length; i++) {
            String str = (String) values[i];
            if (str.isEmpty()) {
                return false;
            }
        }
        return true;
    }

}
