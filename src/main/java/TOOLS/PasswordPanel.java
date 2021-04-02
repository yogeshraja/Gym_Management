/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TOOLS;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;

/**
 *
 * @author DELL
 */
public class PasswordPanel extends JPanel {

    private boolean mouseClick = true;
    private JLabel icoLabel;
    private JPasswordField passwordField = new JPasswordField();
    private char ecChar;

    public PasswordPanel() {
        try {
            icoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("resource\\closedEye24.png"))));
            setLayout(new BorderLayout());
            add(passwordField, BorderLayout.CENTER);
            add(icoLabel, BorderLayout.EAST);
            ecChar = passwordField.getEchoChar();
            icoLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (mouseClick) {
                        mouseClick = false;
                        try {
                            icoLabel.setIcon(new ImageIcon(ImageIO.read(new File("resource\\eye24.png"))));
                        } catch (IOException ex) {
                            System.out.println(ex);
                        } finally {
                            passwordField.setEchoChar((char) 0);

                        }
                    } else {
                        mouseClick = true;
                        try {
                            icoLabel.setIcon(new ImageIcon(ImageIO.read(new File("resource\\closedEye24.png"))));
                        } catch (IOException ex) {
                            System.out.println(ex);
                        } finally {

                            passwordField.setEchoChar(ecChar);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    public PasswordPanel(int Size) {
        try {
            icoLabel = new JLabel(new ImageIcon(ImageIO.read(new File("resource\\closedEye24.png"))));
            setLayout(new BorderLayout());
            add(passwordField, BorderLayout.CENTER);
            add(icoLabel, BorderLayout.EAST);
            ecChar = passwordField.getEchoChar();
            passwordField.setFont(new Font("Arial", Font.PLAIN, Size) {
            });
            icoLabel.addMouseListener(new MouseListener() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    if (mouseClick) {
                        mouseClick = false;
                        try {
                            icoLabel.setIcon(new ImageIcon(ImageIO.read(new File("resource\\eye24.png"))));
                        } catch (IOException ex) {
                            System.out.println(ex);
                        } finally {
                            passwordField.setEchoChar((char) 0);

                        }
                    } else {
                        mouseClick = true;
                        try {
                            icoLabel.setIcon(new ImageIcon(ImageIO.read(new File("resource\\closedEye24.png"))));
                        } catch (IOException ex) {
                            System.out.println(ex);
                        } finally {

                            passwordField.setEchoChar(ecChar);
                        }
                    }
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }
            });
            setBorder(BorderFactory.createLineBorder(Color.gray, 1));
            setVisible(true);
        } catch (IOException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void setFont(Font font) {
        //To change body of generated methods, choose Tools | Templates.
        Font oldFont = getFont();
        super.setFont(font);
        if (font != oldFont) {
            revalidate();
            repaint();
        }
    }

    public String getpassword() {
        return passwordField.getText();
    }
}
