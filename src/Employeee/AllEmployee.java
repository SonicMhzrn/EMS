package Employeee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;

public class AllEmployee implements ActionListener {
    JFrame frame;
    JPanel panel, panel2;
    JLabel lb1, empId;
    JTextField txt, txtemp;
    JTable j1;
    JButton b1, b2, edit;
    String[] h = {"Emp id", "Name", "Father Name", "Age", "Date of Birth", "Address", "Phone", "Email", "Education", "Post", "Aadhar"};
    String[][] d = new String[500][11];
    int i = 0, j = 0;

    AllEmployee() {
        frame = new JFrame("View Employees");
        frame.setLayout(new BorderLayout());
        frame.setSize(800, 300);
        frame.setLocation(450, 150);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel2 = new JPanel(new FlowLayout());
        lb1 = new JLabel("Search Employee");
        txt = new JTextField(20);
        empId = new JLabel("Employee Id");
        txtemp = new JTextField(3);
        txtemp.setEditable(false);
        edit = new JButton("Edit");
        panel2.add(lb1);
        panel2.add(txt);
        panel2.add(empId);
        panel2.add(txtemp);
        panel2.add(edit);
        frame.add(panel2, BorderLayout.NORTH);

        panel = new JPanel(new FlowLayout());
        b1 = new JButton("Print");
        b2 = new JButton("Back");
        panel.add(b1);
        panel.add(b2);
        frame.add(panel, BorderLayout.SOUTH);

        loadData();

        j1 = new JTable(d, h);
        JScrollPane s1 = new JScrollPane(j1);
        frame.add(s1, BorderLayout.CENTER);

        b1.addActionListener(this);
        b2.addActionListener(this);

        txt.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String searchText = txt.getText();
                filterData(searchText);
            }
        });

        edit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String empIdValue = txtemp.getText();
                if (!empIdValue.isEmpty()) {
                    new Update_Employee(empIdValue);
                    frame.setVisible(false); // Hide the current frame
                } else {
                    JOptionPane.showMessageDialog(frame, "Please select an employee from the table first.");
                }
            }
        });

        j1.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = j1.getSelectedRow();
                if (row != -1) {
                    String empIdValue = (String) j1.getValueAt(row, 0);
                    txtemp.setText(empIdValue);
                }
            }
        });

        frame.setVisible(true);
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
                d[i][10] = rs.getString("aadhar");
                i++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void filterData(String searchText) {
        if (searchText.isEmpty()) {
            j1.setModel(new javax.swing.table.DefaultTableModel(d, h));
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
                data[k][10] = rs.getString("aadhar");
                k++;
            }
            j1.setModel(new javax.swing.table.DefaultTableModel(data, h));
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        new AllEmployee();
    }
}
