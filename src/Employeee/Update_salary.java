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
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class Update_salary extends JFrame {
    
    JButton backButton;
    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2, t3, t4, t5, txtempid;
    JButton b1, b2;
    
    public Update_salary(String id) {
        // Set the title of the JFrame
        setTitle("Update Salary");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
        
        JPanel p = new JPanel();
        p.setLayout(null); // Use null layout for absolute positioning

        JLabel emp = new JLabel("Employee ID");
        emp.setBounds(300, 100, 200, 40); // Center horizontally
        emp.setFont(new Font("Arial", Font.BOLD, 18));
        p.add(emp);
        
        txtempid = new JTextField(15);
        txtempid.setText(id);
        txtempid.setEditable(false);
        txtempid.setBounds(500, 100, 300, 40); // Center horizontally
        txtempid.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(txtempid);
        
        l1 = new JLabel("House Rent");
        t1 = new JTextField(15);
        
        l1.setBounds(300, 160, 200, 40); // Center horizontally
        l1.setFont(new Font("Arial", Font.BOLD, 18));
        t1.setBounds(500, 160, 300, 40); // Center horizontally
        t1.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l1);
        p.add(t1);
        
        l2 = new JLabel("Dearness Allowance");
        t2 = new JTextField(15);
        
        l2.setBounds(300, 220, 200, 40); // Center horizontally
        l2.setFont(new Font("Arial", Font.BOLD, 18));
        t2.setBounds(500, 220, 300, 40); // Center horizontally
        t2.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l2);
        p.add(t2);
        
        l3 = new JLabel("Medical Allowance");
        t3 = new JTextField(15);
        
        l3.setBounds(300, 280, 200, 40); // Center horizontally
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        t3.setBounds(500, 280, 300, 40); // Center horizontally
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l3);
        p.add(t3);
        
        l4 = new JLabel("Provident Fund");
        t4 = new JTextField(15);
        
        l4.setBounds(300, 340, 200, 40); // Center horizontally
        l4.setFont(new Font("Arial", Font.BOLD, 18));
        t4.setBounds(500, 340, 300, 40); // Center horizontally
        t4.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l4);
        p.add(t4);
        
        l5 = new JLabel("Basic Salary");
        t5 = new JTextField(15);
        
        l5.setBounds(300, 400, 200, 40); // Center horizontally
        l5.setFont(new Font("Arial", Font.BOLD, 18));
        t5.setBounds(500, 400, 300, 40); // Center horizontally
        t5.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l5);
        p.add(t5);
        
        b1 = new JButton("Update");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setFont(new Font("Arial", Font.BOLD, 18));
        
        b2 = new JButton("Cancel");
        b2.setBackground(Color.BLACK);
        b2.setForeground(Color.WHITE);
        b2.setFont(new Font("Arial", Font.BOLD, 18));
        
        b1.setBounds(400, 480, 150, 50); // Center horizontally
        b2.setBounds(600, 480, 150, 50); // Center horizontally
        p.add(b1);
        p.add(b2);
        
        showdata(id);

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);
        
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String hra = t1.getText();
                String id = txtempid.getText();
                String da = t2.getText();
                String med = t3.getText();
                String pf = t4.getText();
                String basic = t5.getText();
                
                String qry = "update salary set hra=" + hra + ",da=" + da + ",med=" + med + ",pf=" + pf + ",basic_salary=" + basic + "  where id=" + id;
                
                try {
                    conn c1 = new conn();
                    c1.s.executeUpdate(qry);
                    JOptionPane.showMessageDialog(null, "Salary Updated");
                    setVisible(false);
                    new All_Salary();
                } catch (Exception ee) {
                    ee.printStackTrace();
                }
            }
        });
        
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new All_Salary();
            }
        });
        
        setVisible(true);
        setSize(1500, 800);
        setLocationRelativeTo(null);
    }
    
    void showdata(String id) {
        try {
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery("select * from salary where id=" + id);
            if (rs.next()) {
                t1.setText(rs.getString("hra"));
                t2.setText(rs.getString("da"));
                t3.setText(rs.getString("med"));
                t4.setText(rs.getString("pf"));
                t5.setText(rs.getString("basic_salary"));
                
            }
        } catch (Exception ee) {
            ee.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Update_salary("Update Employee");
            }
        });
    }
}
