package Employeee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Home extends JFrame {

    JButton logoutButton, addEmployeeButton, viewRecordsButton, salaryEmployeeButton, attendanceButton, leaveRecordButton;

    public Home() {
        // Set the title of the JFrame
        setTitle("Admin Home");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1100, 800);
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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/administrator.png"));
        
        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

// Admin text label
        JLabel adminLabel = new JLabel("<html>Welcome<br>Admin</html>", SwingConstants.CENTER);
        int labelWidth = iconWidth; // Width of the label to match the icon
        int labelHeight = 50; // Height of the label
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight); // Position the label below the icon
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.BLACK);
        backgroundPanel.add(adminLabel);

        // Logout button
        // Create the logout button
        logoutButton = new JButton("Logout");

        // Load and resize the icon
        ImageIcon logoutIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/logout.png"));
        int iconWidthlogout = 20; // Width of the icon
        int iconHeightlogout = 20; // Height of the icon
        Image img1 = logoutIcon.getImage().getScaledInstance(iconWidthlogout, iconHeightlogout, Image.SCALE_SMOOTH);
        logoutIcon = new ImageIcon(img1);

        // Set the icon and text on the button
        logoutButton.setIcon(logoutIcon);
        logoutButton.setText("Logout");
        logoutButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        logoutButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon

        // Set button bounds and add to the background panel
        logoutButton.setBounds(50, 300, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(logoutButton);

        // Buttons for functionalities
        //Add Button
        addEmployeeButton = new JButton("Add Employee");
        ImageIcon addButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/manager.png"));
        int iconWidthadd = 40; // Width of the icon
        int iconHeightadd = 40; // Height of the icon
        Image imgadd = addButtonIcon.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        addButtonIcon = new ImageIcon(imgadd);
//        addEmployeeButton.addActionListener(this);
        // Set the icon and text on the button
        addEmployeeButton.setIcon(addButtonIcon);
        addEmployeeButton.setText("Add Employee");
        addEmployeeButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        addEmployeeButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        addEmployeeButton.setFocusPainted(false);
        // Set button bounds and add to the background panel
        addEmployeeButton.setBounds(300, 100, 200, 100); // Adjust bounds as needed
        backgroundPanel.add(addEmployeeButton);

        //View Button
        viewRecordsButton = new JButton("View Records");
        ImageIcon viewButton = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/directory.png"));
//        int iconWidthadd = 40; // Width of the icon
//        int iconHeightadd = 40; // Height of the icon
        Image imgview = viewButton.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        viewButton = new ImageIcon(imgview);

        // Set the icon and text on the button
        viewRecordsButton.setIcon(viewButton);
        viewRecordsButton.setText("View Employee");
        viewRecordsButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        viewRecordsButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        viewRecordsButton.setFocusPainted(false);

        viewRecordsButton.setBounds(550, 100, 200, 100);
        backgroundPanel.add(viewRecordsButton);

        //Remove Button
        salaryEmployeeButton = new JButton("Remove Employee");
        ImageIcon salaryButton = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/salary.png"));
//        int iconWidthadd = 40; // Width of the icon
//        int iconHeightadd = 40; // Height of the icon
        Image imgremove = salaryButton.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        salaryButton = new ImageIcon(imgremove);

        // Set the icon and text on the button
        salaryEmployeeButton.setIcon(salaryButton);
        salaryEmployeeButton.setText("Salary");
        salaryEmployeeButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        salaryEmployeeButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        salaryEmployeeButton.setFocusPainted(false);

        salaryEmployeeButton.setBounds(800, 100, 200, 100);
        backgroundPanel.add(salaryEmployeeButton);

        //Attendance
        attendanceButton = new JButton("Attendance");
        ImageIcon attButton = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/immigration.png"));
//        int iconWidthadd = 40; // Width of the icon
//        int iconHeightadd = 40; // Height of the icon
        Image imgatt = attButton.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        attButton = new ImageIcon(imgatt);

        // Set the icon and text on the button
        attendanceButton.setIcon(attButton);
        attendanceButton.setText("Attendance");
        attendanceButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        attendanceButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        attendanceButton.setFocusPainted(false);

        attendanceButton.setBounds(300, 250, 200, 100);
        backgroundPanel.add(attendanceButton);

        //Leave Button
        leaveRecordButton = new JButton("Leave Record");
        ImageIcon leaveButton = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/calendar.png"));
//        int iconWidthadd = 40; // Width of the icon
//        int iconHeightadd = 40; // Height of the icon
        Image imgleave = leaveButton.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        leaveButton = new ImageIcon(imgleave);

        // Set the icon and text on the button
        leaveRecordButton.setIcon(leaveButton);
        leaveRecordButton.setText("Leave Record");
        leaveRecordButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        leaveRecordButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        leaveRecordButton.setFocusPainted(false);
        leaveRecordButton.setBounds(550, 250, 200, 100);
        backgroundPanel.add(leaveRecordButton);

        JButton settingsButton = new JButton("Setting");
        settingsButton.setBounds(800, 250, 200, 100);
        backgroundPanel.add(settingsButton);

      
        // Add the background panel to the frame
        setContentPane(backgroundPanel);

        // Make the frame visible
        setVisible(true);


        // Action Listeners (if needed)
        viewRecordsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new All_Employee();
            }
        });

        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Add_Employee();
            }
        });

        salaryEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new All_Salary();
            }
        });

        attendanceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Attendanceview();
            }
        });
        leaveRecordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new LeaveTable();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Login_user();
            }
        });

    }

    

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            // Create the frame on the Event Dispatch Thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Home();
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
