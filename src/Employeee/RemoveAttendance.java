/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employeee;

import javax.swing.JOptionPane;

/**
 *
 * @author Dell
 */
public class RemoveAttendance {
    public RemoveAttendance(String id) {
        try {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete employee with ID " + id + "?", "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                conn con = new conn();
                String str = "delete from leave_table where id = " + id;
                con.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Deleted successfully");
                new LeaveTable();
            } else {
                new LeaveTable();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exception occurred while deleting record: " + ex);
        }
    }
}
