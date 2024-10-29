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

public class All_Employee extends JFrame {
    JLabel search, empId;
    JTextField txt,txtempId, txtemp;
    JButton logoutButton, addEmployeeButton, updateEmployeeButton, removeEmployeeButton, printEmployeeButton;
    JTable table;
    String[] h = {"Emp id", "Name", "Father Name", "Age", "Date of Birth", "Address", "Phone", "Email", "Education", "Post", "Citizen no"};
    String[][] d = new String[500][11];
    int i = 0, j = 0;

    public All_Employee() {
        // Set the title of the JFrame
        setTitle("All Employee");
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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/manager.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("Employees", SwingConstants.CENTER);
        int labelWidth = iconWidth; // Width of the label to match the icon
        int labelHeight = 50; // Height of the label
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight); // Position the label below the icon
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.BLACK);
        backgroundPanel.add(adminLabel);
        
        //Add Button
        addEmployeeButton = new JButton("Add Employee");
        ImageIcon addButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/manager.png"));
        int iconWidthadd = 20; // Width of the icon
        int iconHeightadd = 20; // Height of the icon
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
        addEmployeeButton.setBounds(50, 300, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(addEmployeeButton);
        
        //search and emoId
        search = new JLabel("Search Employee");
        search.setBounds(50, 350, 150, 30);
        backgroundPanel.add(search);
        txt = new JTextField(20);
        txt.setBounds(50, 375, 150, 30);
        backgroundPanel.add(txt);
        
        empId = new JLabel("Employee Id");
        empId.setBounds(50, 415, 150, 30);
        backgroundPanel.add(empId);
        txtempId = new JTextField(10);
        txtempId.setBounds(125, 415, 50, 30);
        backgroundPanel.add(txtempId);
        
         //Update Button
        updateEmployeeButton = new JButton("Update Employee");
        ImageIcon updateButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/refresh.png"));

        Image imgupdate = updateButtonIcon.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        updateButtonIcon = new ImageIcon(imgupdate);
//        addEmployeeButton.addActionListener(this);
        // Set the icon and text on the button
        updateEmployeeButton.setIcon(updateButtonIcon);
        updateEmployeeButton.setText("Update");
        updateEmployeeButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        updateEmployeeButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        updateEmployeeButton.setFocusPainted(false);
        // Set button bounds and add to the background panel
        updateEmployeeButton.setBounds(50, 475, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(updateEmployeeButton);
        
        
         //Update Button
        removeEmployeeButton = new JButton("Remove Employee");
        ImageIcon removeButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/trash.png"));

        Image imgremove = removeButtonIcon.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        removeButtonIcon = new ImageIcon(imgremove);
//        addEmployeeButton.addActionListener(this);
        // Set the icon and text on the button
        removeEmployeeButton.setIcon(removeButtonIcon);
        removeEmployeeButton.setText("Remove");
        removeEmployeeButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        removeEmployeeButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        removeEmployeeButton.setFocusPainted(false);
        // Set button bounds and add to the background panel
        removeEmployeeButton.setBounds(50, 535, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(removeEmployeeButton);
        
        
        // Logout button
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
        logoutButton.setBounds(50, 655, 150, 30); // Adjust bounds as needed
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
        
         txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = txt.getText();
                filterData(searchText);
            }
        });
        
        addEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Add_Employee();
            }
        });
        updateEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtempId.getText();
                if (!empIdValue.isEmpty()) {
                     setVisible(false);
                    new Update_Employee(empIdValue);
                    // Hide the current frame
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an employee from the table first.");
                }
            }
        });
        removeEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtempId.getText();
                if (!empIdValue.isEmpty()) {
                     setVisible(false);
                    new RemoveEmployee(empIdValue);
                    // Hide the current frame
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an employee from the table first.");
                }
            }
        });
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row != -1) {
                    String empIdValue = (String) table.getValueAt(row, 0);
                    txtempId.setText(empIdValue);
                }
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home();
            }
        });
    }

    private void loadData() {
        try {
            String q = "select * from employee";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            i = 0;
            while (rs.next()) {
                d[i][0] = rs.getString("emp_id");
                d[i][1] = rs.getString("name");
                d[i][2] = rs.getString("fname");
                d[i][3] = rs.getString("age");
                d[i][4] = rs.getString("dob");
                d[i][5] = rs.getString("address");
                d[i][6] = rs.getString("phone");
                d[i][7] = rs.getString("email");
                d[i][8] = rs.getString("education");
                d[i][9] = rs.getString("post");
                d[i][10] = rs.getString("citizen");

                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void filterData(String searchText) {
        if (searchText.isEmpty()) {
            table.setModel(new javax.swing.table.DefaultTableModel(d, h));
            return;
        }
        
        try {
            String q = "select * from employee where emp_id like '%" + searchText + "%' or name like '%" + searchText + "%'";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            String[][] data = new String[500][11];
            int k = 0;
            while (rs.next()) {
                data[k][0] = rs.getString("emp_id");
                data[k][1] = rs.getString("name");
                data[k][2] = rs.getString("fname");
                data[k][3] = rs.getString("age");
                data[k][4] = rs.getString("dob");
                data[k][5] = rs.getString("address");
                data[k][6] = rs.getString("phone");
                data[k][7] = rs.getString("email");
                data[k][8] = rs.getString("education");
                data[k][9] = rs.getString("post");
                data[k][10] = rs.getString("citizen");
                k++;
            }
            table.setModel(new javax.swing.table.DefaultTableModel(data, h));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new All_Employee();
            }
        });
    }
}