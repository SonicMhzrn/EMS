package Employeee;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class Leave extends JFrame {

    JLabel lbl;
    JButton backButton, reqButton;
    JTextField empIdField, nameField, typeField, fromField, toField, totalDaysField, lengthField, statusField;
    JTextArea detailsField;

    public Leave(String empId, String name) {
        setTitle("Leave Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);

        JPanel backgroundPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/bgnew.png"));
                Image img = icon.getImage();
                g.drawImage(img, 0, 0, 250, 800, this);
            }
        };
        backgroundPanel.setLayout(null);
        JLabel adminIconLabel = new JLabel();
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/leave.png"));

        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight);
        backgroundPanel.add(adminIconLabel);

        JLabel adminLabel = new JLabel("<html>Leave<br>Request</html>", SwingConstants.CENTER);
        int labelWidth = iconWidth;
        int labelHeight = 50;
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight);
        adminLabel.setFont(new Font("Arial", Font.BOLD, 24));
        adminLabel.setForeground(Color.BLACK);
        backgroundPanel.add(adminLabel);

        backButton = new JButton("Back");
        ImageIcon backIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/logout.png"));
        int iconWidthlogout = 20;
        int iconHeightlogout = 20;
        Image img1 = backIcon.getImage().getScaledInstance(iconWidthlogout, iconHeightlogout, Image.SCALE_SMOOTH);
        backIcon = new ImageIcon(img1);
        backButton.setIcon(backIcon);
        backButton.setText("Back");
        backButton.setHorizontalTextPosition(JButton.RIGHT);
        backButton.setVerticalTextPosition(JButton.CENTER);
        backButton.setBounds(50, 655, 150, 30);
        backgroundPanel.add(backButton);

        JPanel p = new JPanel();
        p.setLayout(null);

        boolean hasLeaveRequest = checkLeaveRequest(empId);

        if (hasLeaveRequest) {
            String leaveStatus = getLeaveStatus(empId);
            JLabel lbl = new JLabel("<html>Your Leave Requests: <u>" + leaveStatus + "</u></html>");
            lbl.setBounds(350, 50, 700, 50); // Increased width to 700
            lbl.setFont(new Font("serif", Font.ITALIC, 25));
            lbl.setForeground(Color.black);
            p.add(lbl);

            createLeaveDetailFields(p);
            loadLeaveData(empId);

        } else {
            lbl = new JLabel("<html>You have made no leave request.<br>If you want to make a leave request,<br>press on the Request Button</html>");
            lbl.setBounds(350, 100, 700, 200); // Increased width to 700
            lbl.setFont(new Font("serif", Font.ITALIC, 25));
            lbl.setForeground(Color.black);
            p.add(lbl);

            reqButton = new JButton("Request");
            reqButton.setBackground(Color.BLACK);
            reqButton.setForeground(Color.WHITE);
            reqButton.setBounds(450, 300, 150, 40);
            p.add(reqButton);

            reqButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    setVisible(false);
                    new LeaveRequest(empId, name);
                }
            });
        }

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250);
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        setContentPane(splitPane);
        setVisible(true);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new UserHome(empId, name);
            }
        });
    }

    private void createLeaveDetailFields(JPanel p) {
    int y = 100;
    int labelWidth = 200;
    int fieldWidth = 300;
    int height = 40;
    int gap = 50;

    Font font = new Font("serif", Font.BOLD, 20); // Create a Font object to reuse

    JLabel empIdLabel = new JLabel("Emp ID:");
    empIdLabel.setBounds(350, y, labelWidth, height);
    empIdLabel.setFont(font); // Set font
    p.add(empIdLabel);

    empIdField = new JTextField();
    empIdField.setBounds(550, y, fieldWidth, height);
    empIdField.setEditable(false);
    empIdField.setFont(font); // Set font
    p.add(empIdField);

    y += gap;
    JLabel nameLabel = new JLabel("Name:");
    nameLabel.setBounds(350, y, labelWidth, height);
    nameLabel.setFont(font); // Set font
    p.add(nameLabel);

    nameField = new JTextField();
    nameField.setBounds(550, y, fieldWidth, height);
    nameField.setEditable(false);
    nameField.setFont(font); // Set font
    p.add(nameField);

    y += gap;
    JLabel typeLabel = new JLabel("Leave Type:");
    typeLabel.setBounds(350, y, labelWidth, height);
    typeLabel.setFont(font); // Set font
    p.add(typeLabel);

    typeField = new JTextField();
    typeField.setBounds(550, y, fieldWidth, height);
    typeField.setEditable(false);
    typeField.setFont(font); // Set font
    p.add(typeField);

    y += gap;
    JLabel fromLabel = new JLabel("From:");
    fromLabel.setBounds(350, y, labelWidth, height);
    fromLabel.setFont(font); // Set font
    p.add(fromLabel);

    fromField = new JTextField();
    fromField.setBounds(550, y, fieldWidth, height);
    fromField.setEditable(false);
    fromField.setFont(font); // Set font
    p.add(fromField);

    y += gap;
    JLabel toLabel = new JLabel("To:");
    toLabel.setBounds(350, y, labelWidth, height);
    toLabel.setFont(font); // Set font
    p.add(toLabel);

    toField = new JTextField();
    toField.setBounds(550, y, fieldWidth, height);
    toField.setEditable(false);
    toField.setFont(font); // Set font
    p.add(toField);

    y += gap;
    JLabel totalDaysLabel = new JLabel("Total Days:");
    totalDaysLabel.setBounds(350, y, labelWidth, height);
    totalDaysLabel.setFont(font); // Set font
    p.add(totalDaysLabel);

    totalDaysField = new JTextField();
    totalDaysField.setBounds(550, y, fieldWidth, height);
    totalDaysField.setEditable(false);
    totalDaysField.setFont(font); // Set font
    p.add(totalDaysField);

    y += gap;
    JLabel lengthLabel = new JLabel("Length:");
    lengthLabel.setBounds(350, y, labelWidth, height);
    lengthLabel.setFont(font); // Set font
    p.add(lengthLabel);

    lengthField = new JTextField();
    lengthField.setBounds(550, y, fieldWidth, height);
    lengthField.setEditable(false);
    lengthField.setFont(font); // Set font
    p.add(lengthField);

    y += gap;
    JLabel detailsLabel = new JLabel("Details:");
    detailsLabel.setBounds(350, y, labelWidth, height);
    detailsLabel.setFont(font); // Set font
    p.add(detailsLabel);

    detailsField = new JTextArea();
    detailsField.setBounds(550, y, fieldWidth, height);
    detailsField.setEditable(false);
    detailsField.setFont(font); // Set font
    p.add(detailsField);


}


    private boolean checkLeaveRequest(String empId) {
        try {
            String q = "SELECT COUNT(*) FROM leave_table WHERE id = '" + empId + "'";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    private String getLeaveStatus(String empId) {
        try {
            String q = "SELECT status FROM leave_table WHERE id = '" + empId + "' ORDER BY fromDate DESC LIMIT 1";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                return rs.getString("status");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "Unknown";
    }

    private void loadLeaveData(String empId) {
        try {
            String q = "SELECT * FROM leave_table WHERE id = '" + empId + "'";
            conn c1 = new conn();
            ResultSet rs = c1.s.executeQuery(q);
            if (rs.next()) {
                empIdField.setText(rs.getString("id"));
                nameField.setText(rs.getString("name"));
                typeField.setText(rs.getString("type"));
                fromField.setText(rs.getString("fromDate"));
                toField.setText(rs.getString("toDate"));
                totalDaysField.setText(rs.getString("total_days")); // Updated column name
                lengthField.setText(rs.getString("length"));
                detailsField.setText(rs.getString("details"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Leave("","");
    }
}
