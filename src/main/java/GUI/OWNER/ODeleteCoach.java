package GUI.OWNER;

import TOOLS.DbTools;
import TOOLS.TemplateClass;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.TextAttribute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ODeleteCoach extends JFrame implements ActionListener {

    JLabel tlabel;
    JLabel selectCoachLabel;
    JButton deleteButton = new JButton("Delete");
    JPanel header = TemplateClass.getHeader();
    JPanel body = TemplateClass.getBody();
    JPanel footer = TemplateClass.getFooter();
    JPanel innerPanel = new JPanel(null);
    JButton backButton = TemplateClass.getBackButton();

    static ArrayList<String> comboList = new ArrayList<>();
    JScrollPane scrollPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
    JTable scheduleTable = new JTable();
    static DefaultTableModel model;

    public ODeleteCoach() {

        Container cc = getContentPane();
        JPanel header = TemplateClass.getHeader();
        JPanel body = TemplateClass.getBody();
        JPanel schedulePanel = new JPanel();

        tlabel = new JLabel("   DELETE COACH   ");
        selectCoachLabel = new JLabel("Select Coach");

        cc.add(header);
        cc.add(body);
        body.add(schedulePanel);
        cc.add(footer);
        footer.add(backButton);
        backButton.addActionListener((ActionEvent e) -> {
            try {
                dispose();
            } catch (HeadlessException ex) {
                Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        scheduleTable.setPreferredSize(new Dimension(600, 288));
        scheduleTable.getTableHeader().setReorderingAllowed(false);
        scheduleTable.getTableHeader().setResizingAllowed(false);
        scheduleTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 20));
        scheduleTable.getTableHeader().setBackground(new Color(216, 111, 147));
        scheduleTable.setBackground(new Color(185, 202, 222));
        scheduleTable.setFont(new Font("Arial", Font.PLAIN, 18));

        scrollPane.setBounds(100, 170, 600, 320);
        scrollPane.setViewportView(scheduleTable);
        schedulePanel.add(scrollPane);
        model = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; //To change body of generated methods, choose Tools | Templates.
            }

        };
        model.addColumn("Id");
        model.addColumn("Name");
        model.addColumn("PhoneNumber");

        scheduleTable.setModel(model);
        scheduleTable.setRowHeight(35);

        selectCoachLabel.setFont(new Font("Arial", Font.BOLD, 20));

        tlabel.setBounds(255, 30, 300, 40);
        selectCoachLabel.setBounds(330, 90, 200, 50);
        deleteButton.setBounds(310, 510, 150, 40);

        tlabel = (JLabel) TemplateClass.formatFont(tlabel, TextAttribute.UNDERLINE_ON, 28);

        body.add(tlabel);
        body.add(selectCoachLabel);
        body.add(deleteButton);
        body.add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 800);
        setLocationRelativeTo(null);
        setLayout(null);
        setVisible(true);
        setResizable(false);

        deleteButton.addActionListener(this);
        renderTable();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteButton) {
            String delID = (String) scheduleTable.getValueAt(scheduleTable.getSelectedRow(), 0);
            if (DbTools.deleteCoach(delID) == 1) {
                JOptionPane.showMessageDialog(scheduleTable, "Deleted Successfully");
            } else {
                JOptionPane.showMessageDialog(scheduleTable, "Delete Unsuccessful");
            }
            renderTable();
        }

    }

    public static void renderTable() {
        try {
            model.setRowCount(0);
            Connection connection = DbTools.getConnect();
            PreparedStatement ps = connection.prepareStatement("select * from coachtable ");
            ResultSet rs = ps.executeQuery();
            System.out.println("wait");
            while (rs.next()) {
                try {
                    System.out.println("Enter");
                    model.addRow(new Object[]{rs.getString("cid"), rs.getString("cfirstname"), rs.getString("cphonenumber")});
                } catch (SQLException ex) {
                    Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ODeleteMember.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
