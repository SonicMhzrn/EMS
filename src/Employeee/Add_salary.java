package Employeee;

import java.awt.Choice;
import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Add_Salary extends JFrame {

    JButton backButton;
    JLabel l1, l2, l3, l4, l5, l6, l7, id;
    JTextField t1, t2, t3, t4, t5, t6, t7, idtxt;
    JButton b1, b2;

    public Add_Salary() {
        // Set the title of the JFrame
        setTitle("Add Employee");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);

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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/add.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("<html>Add<br>Employees</html>", SwingConstants.CENTER);
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

        // Panel for form fields
        JPanel p = new JPanel();
        p.setLayout(null); // Use null layout for absolute positioning

        // Set bounds for labels and text fields
        int xLabel = 50;
        int yLabel = 50;
        int widthLabel = 300; // Increased width for labels
        int heightLabel = 40; // Increased height for labels

        int xText = 370;
        int yText = 50;
        int widthText = 300; // Increased width for text fields
        int heightText = 40; // Increased height for text fields

        id = new JLabel("Employee id:");
        id.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(id);
        
        idtxt = new JTextField(15);
        idtxt.setBounds(xText, yText, widthText, heightText);
        p.add(idtxt);

        l1 = new JLabel("HRA (House Rent Allowance)");
        yLabel += 60; // Increased spacing between labels
        yText += 60;
        l1.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(l1);

        t1 = new JTextField(15);
        t1.setBounds(xText, yText, widthText, heightText);
        p.add(t1);

        l3 = new JLabel("DA (Dearness Allowance)");
        yLabel += 60; // Increased spacing between labels
        yText += 60; // Increased spacing between text fields
        l3.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(l3);

        t3 = new JTextField(15);
        t3.setBounds(xText, yText, widthText, heightText);
        p.add(t3);

        l4 = new JLabel("MA (Medical Allowance)");
        yLabel += 60;
        yText += 60;
        l4.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(l4);

        t4 = new JTextField(15);
        t4.setBounds(xText, yText, widthText, heightText);
        p.add(t4);

        l5 = new JLabel("PF (Provident Fund)");
        yLabel += 60;
        yText += 60;
        l5.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(l5);

        t5 = new JTextField(15);
        t5.setBounds(xText, yText, widthText, heightText);
        p.add(t5);

        l6 = new JLabel("Basic Salary");
        yLabel += 60;
        yText += 60;
        l6.setBounds(xLabel, yLabel, widthLabel, heightLabel);
        p.add(l6);

        t6 = new JTextField(15);
        t6.setBounds(xText, yText, widthText, heightText);
        p.add(t6);

        b1 = new JButton("Submit");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(50, 420, 150, 40); // Increased size of the buttons
        p.add(b1);

        b2 = new JButton("Reset");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setBounds(220, 420, 150, 40); // Increased size of the buttons
        p.add(b2);

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);
        setVisible(true);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hra = t1.getText();
                String id = idtxt.getText();
                String da = t3.getText();
                String med = t4.getText();
                String pf = t5.getText();
                String basic = t6.getText();
                String qry = "insert into salary values(" + id + "," + hra + "," + da + "," + med + "," + pf + "," + basic + ")";
                // salary table
                try {
                    conn c1 = new conn();
                    c1.s.executeUpdate(qry);
                    JOptionPane.showMessageDialog(null, "Salary updated");
                    setVisible(false);
                    new All_Salary();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        
         b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                idtxt.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
            }
        });
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
                new Add_Salary();
            }
        });
    }
}
