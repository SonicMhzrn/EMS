package Employeee;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class Login_user extends JFrame implements ActionListener {

    private JPanel panel;
    private JTextField textField;
    private JPasswordField passwordField;
    private JButton b1, b2, b3;

    public Login_user() {
        setTitle("Login");
        setBackground(new Color(169, 169, 169));
        setBounds(400, 200, 600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        panel.setBackground(new Color(150, 150, 150));
        setContentPane(panel);
        panel.setLayout(null);

        JLabel l1 = new JLabel("Username : ");
        l1.setBounds(124, 89, 95, 24);
        l1.setFont(new Font("Tahoma", Font.BOLD, 13));
        l1.setForeground(new Color(0, 0, 0));
        panel.add(l1);

        JLabel l2 = new JLabel("Password : ");
        l2.setBounds(124, 124, 95, 24);
        l2.setFont(new Font("Tahoma", Font.BOLD, 13));
        l2.setForeground(new Color(0, 0, 0));
        panel.add(l2);

        textField = new JTextField();
        textField.setBounds(210, 93, 157, 20);
        panel.add(textField);

        passwordField = new JPasswordField();
        passwordField.setBounds(210, 128, 157, 20);
        panel.add(passwordField);

        JLabel l3 = new JLabel("");
        l3.setBounds(377, 79, 46, 34);
        panel.add(l3);

        JLabel l4 = new JLabel("");
        l4.setBounds(377, 124, 46, 34);
        panel.add(l3);

        b1 = new JButton("Login");
        b1.addActionListener(this);

        b1.setForeground(new Color(0, 0, 0));
        b1.setBackground(new Color(204, 255, 255));
        b1.setFont(new Font("Tahoma", Font.BOLD, 13));
        b1.setBounds(149, 181, 113, 39);
        panel.add(b1);

        b2 = new JButton("SignUp");
        b2.addActionListener(this);
        b2.setFont(new Font("Tahoma", Font.BOLD, 13));
        b2.setForeground(new Color(0, 0, 0));
        b2.setBackground(new Color(204, 255, 255));
        b2.setBounds(289, 181, 113, 39);
        panel.add(b2);

        b3 = new JButton("Forgot Password");
        b3.addActionListener(this);

        b3.setForeground(new Color(255, 255, 255));
        b3.setBackground(new Color(255, 0, 0));
        b3.setFont(new Font("Tahoma", Font.BOLD, 15));
        b3.setBounds(199, 231, 179, 39);
        panel.add(b3);

        JLabel l5 = new JLabel("Trouble in Login?");
        l5.setFont(new Font("Tahoma", Font.BOLD, 13));
        l5.setForeground(new Color(0, 0, 0));
        l5.setBounds(70, 240, 130, 20);
        panel.add(l5);

        JPanel panel2 = new JPanel();
        panel2.setBackground(new Color(150, 150, 150));
        panel2.setBounds(24, 40, 434, 263);
        panel.add(panel2);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            Boolean status = false;
            try {
                conn con = new conn();
                String sql = "select * from account where username=? and password=?";
                PreparedStatement st = con.c.prepareStatement(sql);

                st.setString(1, textField.getText());
                st.setString(2, passwordField.getText());

                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String employeeId = rs.getString("emp_id");
                    String empname = rs.getString("name");
                    if (username.equals("admin") && password.equals("admin")) {
                        this.setVisible(false);
                        new Home();
                    } else {
                        this.setVisible(false);
                        new UserHome(employeeId,empname);
                    }

                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login...!.");
                }

            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        if (ae.getSource() == b2) {
            setVisible(false);
            Signup su = new Signup();
            su.setVisible(true);
        }
        if (ae.getSource() == b3) {
            setVisible(false);
            Forgot forgot = new Forgot();
            forgot.setVisible(true);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.aero.AeroLookAndFeel");
            // Create the frame on the Event Dispatch Thread
            SwingUtilities.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new Login_user().setVisible(true);
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        
    }

}
