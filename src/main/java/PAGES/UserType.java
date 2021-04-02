package PAGES;

import TOOLS.*;
import com.mycompany.gymcomp.Main;
import java.awt.*;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.*;

public class UserType extends JFrame {

    JLabel userType;

    JButton Owner;
    JButton Coach;
    JButton Member;

    public UserType() {
        setTitle("GYM-31");

        try {
            Container cc = getContentPane();
            JPanel header = TemplateClass.getHeader();
            JPanel body = TemplateClass.getBody();
            JPanel footer = TemplateClass.getFooter();

            setIconImage(ImageIO.read(new File("resource/yLogo.png")));

            cc.add(header);
            cc.add(body);
            cc.add(footer);

            userType = new JLabel("     Who Are You??     ");

            Owner = new JButton("Owner");
            Coach = new JButton("Coach");
            Member = new JButton("Member");

            userType.setFont(new Font("Arial", Font.BOLD, 32));
            Owner.setFont(new Font("Arial", Font.BOLD, 22));
            Coach.setFont(new Font("Arial", Font.BOLD, 22));
            Member.setFont(new Font("Arial", Font.BOLD, 22));

            userType = (JLabel) TemplateClass.formatFont(userType, TextAttribute.UNDERLINE_ON, 32);

            Owner.setBackground(Color.BLACK);
            Coach.setBackground(Color.BLACK);
            Member.setBackground(Color.BLACK);

            userType.setForeground(Color.BLACK);
            Owner.setForeground(new Color(246, 231, 29));
            Coach.setForeground(new Color(246, 231, 29));
            Member.setForeground(new Color(246, 231, 29));

            userType.setBounds(220, 80, 400, 50);
            Owner.setBounds(320, 170, 150, 50);
            Coach.setBounds(320, 270, 150, 50);
            Member.setBounds(320, 370, 150, 50);

            body.add(userType);
            body.add(Owner);
            body.add(Coach);
            body.add(Member);
            JButton backButton = TemplateClass.getBackButton();
            backButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    new Main();
                    dispose();
                }
            });
            footer.add(backButton);

            setSize(800, 800);
            setLocationRelativeTo(null);
            // setShape(new RoundRectangle2D.Double(30, 30, 350, 200, 50, 100));
            setLayout(null);
            setVisible(true);

            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Owner.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Login(Login.ADMIN);
                            dispose();
                        }
                    });

                }
            });

            Coach.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Login(Login.COACH);
                            dispose();
                        }
                    });
                }
            });
            Member.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    SwingUtilities.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new Login(Login.USER);
                            dispose();
                        }
                    });
                }
            });
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

}
