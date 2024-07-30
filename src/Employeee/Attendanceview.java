package Employeee;

import java.sql.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class Attendanceview implements ActionListener {
    JFrame frame;
    JPanel panel;
    JTable j1;
    JButton b1, b2;
    String[] h = {"Emp id", "Emp name", "Status", "Date Time"};
    String[][] d = new String[500][4];
    int i = 0, j = 0;

    Attendanceview() {
        frame = new JFrame("View Employees Attendance");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 300);
        frame.setLocation(450, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel(new FlowLayout());
        b1 = new JButton("Print");
        b2 = new JButton("Back");
        panel.add(b1);
        panel.add(b2);
        frame.add(panel, BorderLayout.SOUTH);

        try {
            String q = "select * from attendance";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            while (rs.next()) {
                d[i][j++] = rs.getString("emp_id");
                d[i][j++] = rs.getString("name");
                d[i][j++] = rs.getString("status");
                d[i][j++] = rs.getString("Date");
                i++;
                j = 0;
            }

            j1 = new JTable(d, h);
        } catch (Exception e) {
            e.printStackTrace();
        }

        JScrollPane s1 = new JScrollPane(j1);
        frame.add(s1, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        frame.setVisible(true);
    }
    
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == b1) {
            try {
                j1.print();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == b2) {
            frame.setVisible(false);
            new attendancebox();
        }
    }

    public static void main(String[] args) {
        new Attendanceview();
    }
}
