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

public class View_Salary extends JFrame {
    
    JButton backButton;
    JLabel l1, l2, l3, l4, l5;
    JTextField t1, t2, t3, t4, t5, txtempid;
    JButton b1, b2;
    
    public View_Salary(String id, String name) {
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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/salary.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("Salary", SwingConstants.CENTER);
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
        t1.setEditable(false);
        
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
        t2.setEditable(false);
        p.add(l2);
        p.add(t2);
        
        l3 = new JLabel("Medical Allowance");
        t3 = new JTextField(15);
        
        l3.setBounds(300, 280, 200, 40); // Center horizontally
        l3.setFont(new Font("Arial", Font.BOLD, 18));
        t3.setBounds(500, 280, 300, 40); // Center horizontally
        t3.setFont(new Font("Arial", Font.PLAIN, 18));
        t3.setEditable(false);
        p.add(l3);
        p.add(t3);
        
        l4 = new JLabel("Provident Fund");
        t4 = new JTextField(15);
        
        l4.setBounds(300, 340, 200, 40); // Center horizontally
        l4.setFont(new Font("Arial", Font.BOLD, 18));
        t4.setBounds(500, 340, 300, 40); // Center horizontally
        t4.setFont(new Font("Arial", Font.PLAIN, 18));
        t4.setEditable(false);
        p.add(l4);
        p.add(t4);
        
        l5 = new JLabel("Basic Salary");
        t5 = new JTextField(15);
        t5.setEditable(false);
        
        l5.setBounds(300, 400, 200, 40); // Center horizontally
        l5.setFont(new Font("Arial", Font.BOLD, 18));
        t5.setBounds(500, 400, 300, 40); // Center horizontally
        t5.setFont(new Font("Arial", Font.PLAIN, 18));
        p.add(l5);
        p.add(t5);
        
//        System.out.println(id + name);
        showdata(id,name);

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
                new UserHome(id,name);
            }
        });
        
        setVisible(true);
        setSize(1500, 800);
        setLocationRelativeTo(null);
    }
    
   void showdata(String id, String name) {
    try {
        conn c1 = new conn();
        String query = "select * from salary where id=" + id;
        ResultSet rs = c1.s.executeQuery(query);
        
        if (rs.next()) {
            System.out.println("Data found for ID: " + id);
            String hra = rs.getString("hra");
            String da = rs.getString("da");
            String med = rs.getString("med");
            String pf = rs.getString("pf");
            String basic = rs.getString("basic_salary");
            
            System.out.println("HRA: " + hra + ", DA: " + da + ", Med: " + med + ", PF: " + pf + ", Basic: " + basic);

            // Update the text fields on the Event Dispatch Thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    t1.setText(hra);
                    t2.setText(da);
                    t3.setText(med);
                    t4.setText(pf);
                    t5.setText(basic);
                }
            });
        } else {
            System.out.println("No data found for ID: " + id);
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
                new View_Salary("","");
            }
        });
    }
}
