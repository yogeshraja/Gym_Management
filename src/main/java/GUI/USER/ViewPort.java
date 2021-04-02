/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI.USER;

import TOOLS.TemplateClass;
import java.awt.HeadlessException;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author rajay
 */
public class ViewPort extends JFrame {

    public ViewPort(String image) throws HeadlessException {
        try {
            JPanel header = TemplateClass.getHeader();
            JPanel body = TemplateClass.getBody();
            JPanel footer = TemplateClass.getFooter();

            JLabel viewerLabel = new JLabel();
            viewerLabel.setBounds(70, 50, 640, 480);

            body.add(viewerLabel);
            add(body);
            add(header);
            add(footer);

            viewerLabel.setIcon(new ImageIcon(ImageIO.read(new File("images/" + image + ".jpg"))));

            setDefaultCloseOperation(DISPOSE_ON_CLOSE);
            setSize(800, 800);
            setLocationRelativeTo(null);
            setLayout(null);
            setVisible(true);
            setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(ViewPort.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
