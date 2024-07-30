package Employeee;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class UserHome implements ActionListener {

    JFrame f;
    JLabel l1, l2;
    JButton b1, b2, b3, b4;
    String empId, name;

    // Constructor with parameters
    UserHome(String empId, String name) {
        this.empId = empId;
        this.name = name;
        System.out.println(empId+name);
        initialize();
    }

    private void initialize() {
        f = new JFrame("USER HOME PAGE");
        f.setBackground(Color.white);
        f.setLayout(null);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        l1 = new JLabel();
        l1.setBounds(0, 0, 700, 400);
        l1.setLayout(null);
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/Home.jpg"));
        l1.setIcon(i1);
        f.add(l1);

        l2 = new JLabel("HOME ICON");
        l2.setBounds(400, 20, 240, 40);
        l2.setFont(new Font("serif", Font.BOLD, 30));
        l2.setForeground(Color.black);
        l1.add(l2);

        b1 = new JButton("Attendance");
        b1.setBounds(380, 140, 120, 40);
        b1.setFont(new Font("serif", Font.BOLD, 15));
        b1.addActionListener(this);
        b1.setForeground(new Color(0, 0, 0));
        b1.setBackground(new Color(255, 255, 255));
        l1.add(b1);

        b2 = new JButton("Take Leave");
        b2.setBounds(530, 140, 120, 40);
        b2.setFont(new Font("serif", Font.BOLD, 15));
        b2.addActionListener(this);
        b2.setForeground(new Color(0, 0, 0));
        b2.setBackground(new Color(255, 255, 255));
        l1.add(b2);

//        b3 = new JButton("Attendance Box");
//        b3.setBounds(530, 190, 120, 40);
//        b3.setFont(new Font("serif", Font.BOLD, 15));
//        b3.addActionListener(this);
//        b3.setForeground(new Color(0, 0, 0));
//        b3.setBackground(new Color(255, 255, 255));
//        l1.add(b3);

        b4 = new JButton("EXIT");
        b4.setBounds(440, 250, 150, 40);
        b4.setFont(new Font("serif", Font.BOLD, 15));
        b4.addActionListener(this);
        b4.setForeground(Color.red);
        b4.setBackground(new Color(255, 255, 255));
        l1.add(b4);

        f.setVisible(true);
        f.setSize(700, 400);
        f.setLocation(400, 200);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            Connection con = null;
            PreparedStatement ps = null;
            try {
                con = new conn().c;
                String sql = "INSERT INTO attendance (emp_id, name, status, date) VALUES (?, ?, ?, ?)";
                ps = con.prepareStatement(sql);

                String date = new java.util.Date().toString();
                ps.setString(1, empId);
                ps.setString(2, name);
                ps.setString(3, "Present");
                ps.setString(4, date);

                int result = ps.executeUpdate();
                if (result > 0) {
                    JOptionPane.showMessageDialog(null, "Attendance marked successfully!");
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to mark attendance.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (ps != null) ps.close();
                    if (con != null) con.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        if (ae.getSource() == b2) {
            f.setVisible(false);
            new salarybox(); // Assuming salarybox is a valid class
        }
//        if (ae.getSource() == b3) {
//            f.setVisible(false);
//            new attendancebox(); // Assuming attendancebox is a valid class
//        }
        if (ae.getSource() == b4) {
            f.setVisible(false);
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        // Use the parameterized constructor
        new UserHome("defaultEmpId", "defaultName"); // Replace with actual values or handle as necessary
    }
}
