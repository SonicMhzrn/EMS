package Employeee;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import com.toedter.calendar.JDateChooser;
//import java.sql.Date;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;
import javax.swing.JOptionPane;

public class LeaveRequest extends JFrame {

    JButton backButton, submitButton;
    JLabel typeOfLeaveLabel, fromDateLabel, toDateLabel, holidayLengthLabel, detailsLabel;
    JTextArea detailsField;
    JDateChooser fromDateChooser, toDateChooser;
    JComboBox<String> typeOfLeaveDropdown, holidayLengthDropdown;

    public LeaveRequest(String id, String name) {
        // Set the title of the JFrame
        setTitle("Leave Request");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1500, 800);
        setLocationRelativeTo(null);
        setVisible(true);

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
        ImageIcon adminIcon = new ImageIcon(ClassLoader.getSystemResource("Employeee/icons/leave.png"));

        // Resize the ImageIcon
        int iconWidth = 150;
        int iconHeight = 150;
        Image img = adminIcon.getImage().getScaledInstance(iconWidth, iconHeight, Image.SCALE_SMOOTH);
        adminIcon = new ImageIcon(img);
        adminIconLabel.setIcon(adminIcon);
        adminIconLabel.setBounds(70, 30, iconWidth, iconHeight); // Adjust the position and size as per the design
        backgroundPanel.add(adminIconLabel);

        // Admin text label
        JLabel adminLabel = new JLabel("<html>Leave<br>Request</html>", SwingConstants.CENTER);
        int labelWidth = iconWidth; // Width of the label to match the icon
        int labelHeight = 50; // Height of the label
        adminLabel.setBounds(70, 30 + iconHeight + 10, labelWidth, labelHeight); // Position the label below the icon
        adminLabel.setFont(new Font("Arial", Font.BOLD, 28));
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

        // Panel for form components
        JPanel p = new JPanel();
        p.setLayout(null); // Use null layout for absolute positioning

        // Font size adjustment
        Font labelFont = new Font("Arial", Font.PLAIN, 18);
        Font inputFont = new Font("Arial", Font.PLAIN, 18);

        JLabel Empid = new JLabel();
        Empid.setText(id);
        Empid.setBounds(50, 600, 200, 50);
        p.add(Empid);
        Empid.setVisible(false);
         JLabel ename = new JLabel();
        ename.setText(name);
        Empid.setBounds(50, 620, 200, 50);
        p.add(ename);
        ename.setVisible(false);
        
        // Label and dropdown for selecting the type of leave
        typeOfLeaveLabel = new JLabel("Select the type of leave you plan to take:");
        typeOfLeaveLabel.setBounds(50, 50, 500, 40);
        typeOfLeaveLabel.setFont(labelFont);
        p.add(typeOfLeaveLabel);

        String[] leaveTypes = {"Holiday", "Sickness", "Work From Home", "Add TOIL", "Take TOIL", "Authorized Absence"};
        typeOfLeaveDropdown = new JComboBox<>(leaveTypes);
        typeOfLeaveDropdown.setBounds(50, 100, 350, 40);
        typeOfLeaveDropdown.setFont(inputFont);
        p.add(typeOfLeaveDropdown);

        // Label and date chooser for selecting the 'From' date
        fromDateLabel = new JLabel("From:");
        fromDateLabel.setBounds(50, 160, 150, 40);
        fromDateLabel.setFont(labelFont);
        p.add(fromDateLabel);

        fromDateChooser = new JDateChooser();
        fromDateChooser.setBounds(150, 160, 250, 40);
        fromDateChooser.setFont(inputFont);
        p.add(fromDateChooser);

        // Label and date chooser for selecting the 'To' date
        toDateLabel = new JLabel("To:");
        toDateLabel.setBounds(50, 220, 150, 40);
        toDateLabel.setFont(labelFont);
        p.add(toDateLabel);

        toDateChooser = new JDateChooser();
        toDateChooser.setBounds(150, 220, 250, 40);
        toDateChooser.setFont(inputFont);
        p.add(toDateChooser);

        // Label and dropdown for selecting holiday length
        holidayLengthLabel = new JLabel("Holiday Length:");
        holidayLengthLabel.setBounds(50, 280, 200, 40);
        holidayLengthLabel.setFont(labelFont);
        p.add(holidayLengthLabel);

        String[] holidayLengths = {"Full Day", "Half Day"};
        holidayLengthDropdown = new JComboBox<>(holidayLengths);
        holidayLengthDropdown.setBounds(250, 280, 150, 40);
        holidayLengthDropdown.setFont(inputFont);
        p.add(holidayLengthDropdown);

        // Label and text area for additional details
        detailsLabel = new JLabel("Details:");
        detailsLabel.setBounds(50, 340, 150, 40);
        detailsLabel.setFont(labelFont);
        p.add(detailsLabel);

        detailsField = new JTextArea();
        detailsField.setBounds(150, 340, 500, 100);
        detailsField.setFont(inputFont);
        detailsField.setLineWrap(true);
        detailsField.setWrapStyleWord(true);
        p.add(detailsField);

        // Submit button
        submitButton = new JButton("Submit request");
        submitButton.setBounds(50, 460, 200, 50);
        submitButton.setFont(new Font("Arial", Font.BOLD, 18));
        p.add(submitButton);

        // Use JSplitPane to split the frame
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, backgroundPanel, p);
        splitPane.setDividerLocation(250); // Position of the divider
        splitPane.setOneTouchExpandable(true);
        splitPane.setDividerSize(10);

        // Add the split pane to the frame
        setContentPane(splitPane);

        // Action listener for the back button
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
                new Leave(id, name);
            }
        });

        // Action listener for the submit button
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String eid = (String) Empid.getText();
                String empname = (String) ename.getText();
                String typeOfLeave = (String) typeOfLeaveDropdown.getSelectedItem();
                String fromDate = ((JTextField) fromDateChooser.getDateEditor().getUiComponent()).getText();
                String toDate = ((JTextField) toDateChooser.getDateEditor().getUiComponent()).getText();
                String holidayLength = (String) holidayLengthDropdown.getSelectedItem();
                String details = detailsField.getText();
                String status = "Pending";
                int total = 12;
                System.out.println(id + name);

                try {

                    SimpleDateFormat sdf = new SimpleDateFormat("MMM d, yyyy", Locale.ENGLISH);
                    Date from = sdf.parse(fromDate);
                    Date to = sdf.parse(toDate);

                    // Calculate the difference in milliseconds
                    long diffInMillies = Math.abs(to.getTime() - from.getTime());
                    // Convert the difference in milliseconds to days
                    long diff = diffInMillies / (1000 * 60 * 60 * 24) + 1;

//                    System.out.println(id+name);
                    if (diff > 12) {
                        // If total days exceed 12, show an error message
                        JOptionPane.showMessageDialog(null, "Invalid days: The leave duration exceeds 12 days.");
                    } else {
                        // If valid, proceed to insert into the database
                        conn cc = new conn();
                        String q = "insert into leave_table (id, name, type, fromDate, toDate, total_days, length, details, status) values ('"
                                + eid + "','" + empname + "','" + typeOfLeave + "','" + fromDate + "','" + toDate + "','" + diff + "','"
                                + holidayLength + "','" + details + "','" + status + "')";

                        cc.s.executeUpdate(q);
                        JOptionPane.showMessageDialog(null, "Leave request submitted:\nEmployee Id: "+eid+"\nName: "+empname+"\nType: " + typeOfLeave + "\nFrom: " + fromDate + "\nTo: " + toDate +"\nTotalDays: "+diff+ "\nLength: " + holidayLength + "\nDetails: " + details);
                        setVisible(false);
                        new UserHome(id,name);
                    }
                } catch (Exception ee) {
                    System.out.println("The error is:" + ee);
                }

            }
        });
    }

    public static void main(String[] args) {
        // Create the frame on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LeaveRequest("", "");
            }
        });
    }
}
