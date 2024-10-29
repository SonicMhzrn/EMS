package Employeee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class Attendanceview extends JFrame {
    JLabel search, empId;
    JTextField txt,txtempId, txtemp;
    JButton logoutButton, addEmployeeButton, updateEmployeeButton, removeEmployeeButton;
    JTable table;
        String[] h = {"Emp id", "Emp name", "Status", "Date Time"};
    String[][] d = new String[500][4];
    int i = 0, j = 0;

    public Attendanceview() {
        // Set the title of the JFrame
        setTitle("Attendance");
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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/attendance.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("Attendance", SwingConstants.CENTER);
        int labelWidth = iconWidth; // Width of the label to match the icon
        int labelHeight = 50; // Height of the label
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight); // Position the label below the icon
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.BLACK);
        backgroundPanel.add(adminLabel);
        

        // Back button
        logoutButton = new JButton("Back");
        ImageIcon logoutIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/logout.png"));
        int iconWidthlogout = 20; // Width of the icon
        int iconHeightlogout = 20; // Height of the icon
        Image img1 = logoutIcon.getImage().getScaledInstance(iconWidthlogout, iconHeightlogout, Image.SCALE_SMOOTH);
        logoutIcon = new ImageIcon(img1);
        logoutButton.setIcon(logoutIcon);
        logoutButton.setText("Back");
        logoutButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        logoutButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        logoutButton.setBounds(50, 600, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(logoutButton);

        // Create a table with some dummy data
        loadData();
        table = new JTable(d, h);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, tableScrollPane);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);
        setVisible(true);
        
       logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home();
            }
        });
        
        
    }

    void loadData(){
        try {
            String q = "select * from attendance";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            i = 0;
            while (rs.next()) {
                d[i][0] = rs.getString("emp_id");
                d[i][1] = rs.getString("name");
                d[i][2] = rs.getString("status");
                d[i][3] = rs.getString("Date");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Attendanceview();
            }
        });
    }
}
