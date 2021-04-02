package GUI.USER;

import TOOLS.DbTools;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
 *
 * @author rajay
 */
public class WebcamMini extends JFrame implements ActionListener {

    JButton button = new JButton();
    Image image;
    Image newImage;
    ImageIcon ico;
    static int i = 1;
    Webcam webcam;
    public static String id;

    public WebcamMini(String ID) throws HeadlessException {
        webcam = Webcam.getDefault();
        id = ID;
        image = Toolkit.getDefaultToolkit().getImage("resource/camera.png");
        newImage = image.getScaledInstance(70, 40, Image.SCALE_SMOOTH);
        ico = new ImageIcon(newImage);
        button.setIcon(ico);
        button.setBackground(Color.LIGHT_GRAY);
        button.setSize(new Dimension(60, 30));

        webcam.setViewSize(new Dimension(640, 480));
        for (Dimension d : webcam.getViewSizes()) {
            System.out.println(d.toString());
        }

        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setMirrored(true);

        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(panel, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        setSize(new Dimension(600, 600));
        setResizable(true);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        button.addActionListener(this);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String temp = DbTools.generateImageID();
            DbTools.addImageLog(id, temp);
            ImageIO.write(webcam.getImage(), "JPG", new File(String.format("images/" + temp + ".jpg", i++)));
            System.out.println("picture taken" + (i - 1));

        } catch (IOException ex) {
            Logger.getLogger(WebcamMini.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            dispose();
            webcam.close();
        }

    }
}
