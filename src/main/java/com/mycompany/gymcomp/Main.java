package com.mycompany.gymcomp;

import PAGES.UserType;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame {

    private Container container = getContentPane();
    private JPanel headerPanel = new JPanel();
    private JTextArea quoteTextArea = new JTextArea("     Don't stop when you're tired.              Stop when you're DONE");
    private JLabel headerTitleLabel = new JLabel();
    private JPanel bodyPanel = new JPanel();
    private JButton letsGoButton = new JButton("Let's Go!");

    public Main() {
        try {
            setLayoutManager();
            setLocationAndSize();
            setBodyPanel();
            addComponentsToContainer();

            Image icon = ImageIO.read(new File("resource/yLogo.png"));
            setIconImage(icon);
            setTitle("GYM 31");
            setSize(800, 800);
            setLayout(null);
            setLocationRelativeTo(null);
            setResizable(false);
            setVisible(true);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }

    private void setLayoutManager() {
        container.setBackground(Color.WHITE);
        container.setLayout(null);
    }

    private void setLocationAndSize() {
        bodyPanel.setBounds(250, 95, 350, 458);
    }

    private void setBodyPanel() {
        headerTitleLabel.setBounds(170, 50, 450, 160);
        ImageIcon iconLogo = new ImageIcon(new ImageIcon("resource/fLogo.png").getImage().getScaledInstance(450, 160, Image.SCALE_SMOOTH));
        container.add(headerTitleLabel);
        headerTitleLabel.setIcon(iconLogo);
        bodyPanel.setBounds(0, 260, 800, 240);
        bodyPanel.setBackground(Color.BLACK);
        quoteTextArea.setBounds(0, 62, 800, 130);
        quoteTextArea.setOpaque(true);
        quoteTextArea.setFont(new Font("Arial", Font.BOLD, 47));
        quoteTextArea.setLineWrap(true);
        quoteTextArea.setEditable(false);
        quoteTextArea.setBackground(Color.BLACK);
        quoteTextArea.setForeground(Color.white);

        letsGoButton.setBounds(305, 560, 170, 50);
        letsGoButton.setBackground(new Color(246, 231, 29));
        letsGoButton.setFont(new Font("Arial", Font.BOLD, 28));
        letsGoButton.setForeground(Color.black);
        letsGoButton.addActionListener((ActionEvent e) -> {
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new UserType();
                    dispose();
                }
            });

        });
        container.add(letsGoButton);
        bodyPanel.add(quoteTextArea);
        bodyPanel.setLayout(null);

    }

    private void addComponentsToContainer() {
        container.setBackground(Color.WHITE);
        container.add(headerPanel);
        container.add(bodyPanel);
    }

    public static void main(String args[]) {

        new Main();

    }

}
