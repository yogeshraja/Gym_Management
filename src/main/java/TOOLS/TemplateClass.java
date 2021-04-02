/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOLS;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.font.TextAttribute;
import java.io.File;
import java.io.IOException;
import java.util.Map;
import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TemplateClass {

    public static JPanel getHeader() {
        JPanel headerPanel = new JPanel();
        headerPanel.setLayout(null);
        JLabel iconLabel = new JLabel();
        JLabel textLabel = new JLabel("GYM - 31");

        headerPanel.setBackground(new Color(246, 231, 29));
        headerPanel.setBounds(0, 0, 800, 100);

        textLabel.setBounds(120, 0, 300, 100);
        textLabel.setFont(new Font("Arial", Font.BOLD, 32));

        iconLabel.setBackground(Color.WHITE);
        iconLabel.setBounds(0, 0, 100, 100);
        try {
            Icon TempIcon = new ImageIcon(new ImageIcon(ImageIO.read(new File("resource/bLogo.jpeg"))).getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH));
            iconLabel.setIcon(TempIcon);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        headerPanel.add(iconLabel);
        headerPanel.add(textLabel);
        return headerPanel;
    }

    public static JPanel getBody() {
        JPanel bodyPanel = new JPanel();
        bodyPanel.setLayout(null);
        bodyPanel.setBounds(0, 100, 800, 600);
        bodyPanel.setBackground(Color.WHITE);
        return bodyPanel;
    }

    public static JPanel getFooter() {

        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(null);
        footerPanel.setBounds(0, 700, 800, 55);
        footerPanel.setBackground(new Color(246, 231, 29));

        return footerPanel;
    }

    public static JButton getBackButton() {
        JButton button = new JButton();
        button.setBackground(null);
        button.setBorder(null);
        try {
            button.setIcon(new ImageIcon(ImageIO.read(new File("resource/back.png"))));
        } catch (IOException ex) {
            System.out.println(ex);
        }
        button.setBounds(380, 10, 32, 32);
        return button;
    }

    public static JComponent formatFont(JComponent component, int underLine, int size) {
        Font font = component.getFont();
        Map attributes = font.getAttributes();
        attributes.put(TextAttribute.UNDERLINE, underLine);
        attributes.put(TextAttribute.SIZE, size);
        component.setFont(font.deriveFont(attributes));
        return component;

    }
}
