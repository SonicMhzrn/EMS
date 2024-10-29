package Employeee;


import java.sql.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
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

public class Add_Employee extends JFrame {

    JButton backButton;
    JLabel id, id1, id2, id3, id4, id5, id6, id7, id8, id9, id10, id11, id12, id15, id16, id17, lab, lab1;
    JTextField t, t1, t2, t3, t4, t5, t6, t7, t8, t9, t10, t11, t12, t13;
    JButton b, b1, b2, b3;

    public Add_Employee() {
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

        JPanel p = new JPanel();
        p.setLayout(null); // Use null layout for absolute positioning
        id8 = new JLabel("New Employee Details");
        id8.setBounds(320, 30, 500, 50);
        id8.setFont(new Font("serif", Font.ITALIC, 25));
        id8.setForeground(Color.black);
        p.add(id8);

        // First row (Name and Father's Name)
        id1 = new JLabel("Name");
        id1.setBounds(100, 100, 100, 30);
        id1.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id1);

        t1 = new JTextField();
        t1.setBounds(250, 100, 150, 30);
        p.add(t1);

        id2 = new JLabel("Father's Name");
        id2.setBounds(450, 100, 200, 30);
        id2.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id2);

        t2 = new JTextField();
        t2.setBounds(650, 100, 150, 30);
        p.add(t2);

        // Second row (Age and DOB)
        id3 = new JLabel("Age");
        id3.setBounds(100, 150, 100, 30);
        id3.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id3);

        t3 = new JTextField();
        t3.setBounds(250, 150, 150, 30);
        p.add(t3);

        id4 = new JLabel("DOB (yyyy-mm-dd)");
        id4.setBounds(450, 150, 200, 30);
        id4.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id4);

        t4 = new JTextField();
        t4.setBounds(650, 150, 150, 30);
        p.add(t4);

        // Third row (Address and Phone)
        id5 = new JLabel("Address");
        id5.setBounds(100, 200, 100, 30);
        id5.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id5);

        t5 = new JTextField();
        t5.setBounds(250, 200, 150, 30);
        p.add(t5);

        id6 = new JLabel("Phone");
        id6.setBounds(450, 200, 100, 30);
        id6.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id6);

        t6 = new JTextField();
        t6.setBounds(650, 200, 150, 30);
        p.add(t6);

        // Fourth row (Email Id and Education)
        id7 = new JLabel("Email Id");
        id7.setBounds(100, 250, 100, 30);
        id7.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id7);

        t7 = new JTextField();
        t7.setBounds(250, 250, 150, 30);
        p.add(t7);

        id9 = new JLabel("Education");
        id9.setBounds(450, 250, 100, 30);
        id9.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id9);

        t8 = new JTextField();
        t8.setBounds(650, 250, 150, 30);
        p.add(t8);

        // Fifth row (Job Post and Aadhar No)
        id10 = new JLabel("Job Post");
        id10.setBounds(100, 300, 100, 30);
        id10.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id10);

        t9 = new JTextField();
        t9.setBounds(250, 300, 150, 30);
        p.add(t9);

        id11 = new JLabel("Citizen No");
        id11.setBounds(450, 300, 100, 30);
        id11.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id11);

        t10 = new JTextField();
        t10.setBounds(650, 300, 150, 30);
        p.add(t10);

        // Sixth row (Employee Id)
        id12 = new JLabel("Employee Id");
        id12.setBounds(100, 350, 150, 30);
        id12.setFont(new Font("serif", Font.BOLD, 20));
        p.add(id12);

        t11 = new JTextField();
        t11.setBounds(250, 350, 150, 30);
        p.add(t11);

        // Labels for potential future use
        lab = new JLabel();
        lab.setBounds(250, 400, 250, 200);
        p.add(lab);

        lab1 = new JLabel("");
        lab1.setBounds(650, 400, 250, 200);
        p.add(lab1);

        // Submit button
        b = new JButton("Submit");
        b.setBackground(Color.BLACK);
        b.setForeground(Color.WHITE);
        b.setBounds(300, 550, 150, 40);

        b1 = new JButton("Reset");
        b1.setBackground(Color.BLACK);
        b1.setForeground(Color.WHITE);
        b1.setBounds(500, 550, 150, 40);
        p.add(b);
        p.add(b1);

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);
        setVisible(true);

        b.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = t1.getText();
                String fname = t2.getText();
                String age = t3.getText();
                String dob = t4.getText();
                String addr = t5.getText();
                String phone = t6.getText();
                String email = t7.getText();
                String edu = t8.getText();
                String job = t9.getText();
                String citizen = t10.getText();
                String empid = t11.getText();
                try {
                    conn cc = new conn();
                    String q = "insert into employee values('" + name + "','" + fname + "','" + age + "','" + dob + "','" + addr + "','" + phone + "','" + email + "','" + edu + "','" + job + "','" + citizen + "','" + empid + "')";
                    cc.s.executeUpdate(q);
                    JOptionPane.showMessageDialog(null, "Details Successfully Inserted");
                    setVisible(false);
                    new Home();
                } catch (Exception ee) {
                    System.out.println("The error is:" + ee);
                }
            }
        });

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                t1.setText("");
                t2.setText("");
                t3.setText("");
                t4.setText("");
                t5.setText("");
                t6.setText("");
                t7.setText("");
                t8.setText("");
                t9.setText("");
                t10.setText("");
                t11.setText("");
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Home();
            }
        });
    }

  
    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Add_Employee();
            }
        });
    }
}
