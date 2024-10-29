/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Employeee;

import javax.swing.JOptionPane;

public class RemoveEmployee {

    public RemoveEmployee(String id) {
        try {
            int response = JOptionPane.showConfirmDialog(null, "Are you sure you want to delete employee with ID " + id + "?", "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
            
            if (response == JOptionPane.YES_OPTION) {
                conn con = new conn();
                String str = "delete from employee where emp_id = " + id;
                con.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Deleted successfully");
                new All_Employee();
            } else {
                new All_Employee();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Exception occurred while deleting record: " + ex);
        }
    }
}
