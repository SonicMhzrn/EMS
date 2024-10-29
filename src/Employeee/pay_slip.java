package Employeee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class pay_slip extends JFrame {

    JButton backButton, printButton;
    JTextArea t1;

    public pay_slip(String id) {
        // Set the title of the JFrame
        setTitle("Pay Slip");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setVisible(true);

        // Background panel
        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/bgnew.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, 250, 800, this); // Adjusted to match the background portion
            }
        };
        backgroundPanel.setLayout(null); // Set layout to null for absolute positioning
        JLabel adminIconLabel = new JLabel();
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/payment.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("<html>Generate<br>Pay Slip</html>", SwingConstants.CENTER);
        int labelWidth = iconWidth; // Width of the label to match the icon
        int labelHeight = 50; // Height of the label
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight); // Position the label below the icon
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.BLACK);
        backgroundPanel.add(adminLabel);

        // Back button
        backButton = new JButton("Back");
        ImageIcon logoutIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/logout.png"));
        int iconWidthlogout = 20; // Width of the icon
        int iconHeightlogout = 20; // Height of the icon
        Image img1 = logoutIcon.getImage().getScaledInstance(iconWidthlogout, iconHeightlogout, Image.SCALE_SMOOTH);
        logoutIcon = new ImageIcon(img1);
        backButton.setIcon(logoutIcon);
        backButton.setText("Back");
        backButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        backButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        backButton.setBounds(50, 600, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(backButton);

        JPanel p = new JPanel();
        t1 = new JTextArea(30, 50);
        JScrollPane jsp = new JScrollPane(t1);

        Font f1 = new Font("arial", Font.BOLD, 20);
        t1.setFont(f1);
        p.setLayout(null); // Use null layout for absolute positioning

        // Resize the JScrollPane to fit the panel
        jsp.setBounds(0, 0, 1000, 700); // Adjusted size to fill more space in the panel
        p.add(jsp);

        // Print button
        printButton = new JButton("Print");
        printButton.setBounds(450, 720, 100, 30); // Adjust position according to the new size
        p.add(printButton);

        // Add ActionListener for printing
        printButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    boolean complete = t1.print();
                    if (complete) {
                        JOptionPane.showMessageDialog(null, "Print Successful", "Information", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "Printing Cancelled", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        try {
            conn c = new conn();

            ResultSet rs = c.s.executeQuery("select * from employee where emp_id=" + id);
            rs.next();
            String name = rs.getString("name");
            rs.close();

            rs = c.s.executeQuery("select * from salary where id=" + id);
            double gross = 0;
            double net = 0;

            java.util.Date d1 = new java.util.Date();
            int month = d1.getMonth();
            int year = d1.getYear();

            t1.setText(" ----------------   PAY SLIP FOR THE MONTH OF " + month + " , " + year + "  ------------------------");
            t1.append("\n");

            if (rs.next()) {
                t1.append("\n     Employee ID " + rs.getString("id"));
                t1.append("\n     Employee Name " + name);

                t1.append("\n----------------------------------------------------------------");
                t1.append("\n");

                double hra = rs.getDouble("hra");
                t1.append("\n                  HRA         : " + hra);
                double da = rs.getDouble("da");
                t1.append("\n                  DA          : " + da);
                double med = rs.getDouble("med");
                t1.append("\n                  MED         : " + med);
                double pf = rs.getDouble("pf");
                t1.append("\n                  PF          : " + pf);
                double basic = rs.getDouble("basic_salary");
                gross = hra + da + med + pf + basic;
                net = gross - pf;
                t1.append("\n                  BASIC SALARY : " + basic);

                t1.append("\n-------------------------------------------------------");
                t1.append("\n");

                t1.append("\n       GROSS SALARY :" + gross + "    \n       NET SALARY : " + net);
                t1.append("\n       Tax   :   2.1% of gross " + (gross * 2.1 / 100));
                t1.append("\n -------------------------------------------------");
                t1.append("\n");
                t1.append("\n");
                t1.append("   (  Signature  )      ");
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new All_Salary();
            }
        });
    }

    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new pay_slip("");
            }
        });
    }
}
