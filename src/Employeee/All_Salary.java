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

public class All_Salary extends JFrame {
    JLabel search, empId;
    JTextField txt,txtempId, txtemp;
    JButton logoutButton, addEmployeeButton, updateEmployeeButton, removeEmployeeButton, generateButton;
    JTable table;
    String[] h = {"Emp id", "House Rent Allowance(HRA)", "Dearness Allowance(DA)", "Medical Allowance(MA)", "Provident Fund(PF)", "Basic Salary"};
    String[][] d = new String[500][6];
    int i = 0, j = 0;

    public All_Salary() {
        // Set the title of the JFrame
        setTitle("All Salary");
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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/salarygroup.png"));

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
        addEmployeeButton = new JButton("Add Salary");
        ImageIcon addButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/salary.png"));
        int iconWidthadd = 20; // Width of the icon
        int iconHeightadd = 20; // Height of the icon
        Image imgadd = addButtonIcon.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        addButtonIcon = new ImageIcon(imgadd);
//        addEmployeeButton.addActionListener(this);
        // Set the icon and text on the button
        addEmployeeButton.setIcon(addButtonIcon);
        addEmployeeButton.setText("Add Salary");
        addEmployeeButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        addEmployeeButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        addEmployeeButton.setFocusPainted(false);
        // Set button bounds and add to the background panel
        addEmployeeButton.setBounds(50, 250, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(addEmployeeButton);
        
        //search and emoId
        search = new JLabel("Search Employee");
        search.setBounds(50, 300, 150, 30);
        backgroundPanel.add(search);
        txt = new JTextField(20);
        txt.setBounds(50, 325, 150, 30);
        backgroundPanel.add(txt);
        
        empId = new JLabel("Employee Id");
        empId.setBounds(50, 365, 150, 30);
        backgroundPanel.add(empId);
        txtempId = new JTextField(10);
        txtempId.setBounds(125, 365, 50, 30);
        backgroundPanel.add(txtempId);
        
         //Update Button
        updateEmployeeButton = new JButton("Update");
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
        updateEmployeeButton.setBounds(50, 425, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(updateEmployeeButton);
        
        
         //Update Button
        removeEmployeeButton = new JButton("Remove");
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
        removeEmployeeButton.setBounds(50, 485, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(removeEmployeeButton);
        
         //Update Button
        generateButton = new JButton("Generate");
        ImageIcon generateButtonIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/google-docs.png"));

        Image imggen = generateButtonIcon.getImage().getScaledInstance(iconWidthadd, iconHeightadd, Image.SCALE_SMOOTH);
        generateButtonIcon = new ImageIcon(imggen);
//        addEmployeeButton.addActionListener(this);
        // Set the icon and text on the button
        generateButton.setIcon(generateButtonIcon);
        generateButton.setText("Generate");
        generateButton.setHorizontalTextPosition(JButton.RIGHT); // Position text to the right of the icon
        generateButton.setVerticalTextPosition(JButton.CENTER); // Center text vertically with the icon
        generateButton.setFocusPainted(false);
        // Set button bounds and add to the background panel
        generateButton.setBounds(50, 540, 150, 30); // Adjust bounds as needed
        backgroundPanel.add(generateButton);

        
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
                new Add_Salary();
            }
        });
        updateEmployeeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtempId.getText();
                if (!empIdValue.isEmpty()) {
                    new Update_salary(empIdValue);
                    setVisible(false); // Hide the current frame
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an employee from the table first.");
                }
            }
        });
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtempId.getText();
                if (!empIdValue.isEmpty()) {
                    new pay_slip(empIdValue);
                    setVisible(false); // Hide the current frame
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
        removeEmployeeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtempId.getText();
                if (!empIdValue.isEmpty()) {
                     setVisible(false);
                    new RemoveSalary(empIdValue);
                    // Hide the current frame
                } else {
                    JOptionPane.showMessageDialog(null,"Please select an employee from the table first.");
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
            String q = "select * from salary";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            i = 0;
            while (rs.next()) {
                d[i][0] = rs.getString("id");
                d[i][1] = rs.getString("hra");
                d[i][2] = rs.getString("da");
                d[i][3] = rs.getString("med");
                d[i][4] = rs.getString("pf");
                d[i][5] = rs.getString("basic_salary");
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
            String q = "select * from salary where id like '%" + searchText + "%'";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            String[][] data = new String[500][11];
            int k = 0;
            while (rs.next()) {
                data[k][0] = rs.getString("id");
                data[k][1] = rs.getString("hra");
                data[k][2] = rs.getString("da");
                data[k][3] = rs.getString("med");
                data[k][4] = rs.getString("pf");
                data[k][5] = rs.getString("basic_salary");
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
                new All_Salary();
            }
        });
    }
}
