import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DatePickerExample {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Date Picker Example");
        frame.setLayout(new FlowLayout());
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Create a JDateChooser
        JDateChooser dateChooser = new JDateChooser();
        dateChooser.setDateFormatString("yyyy-MM-dd");

        // Add the date chooser to the frame
        frame.add(dateChooser);
        frame.setVisible(true);
    }
}

// Custom formatter class
class DateLabelFormatter extends JFormattedTextField.AbstractFormatter {
    private static final String DATE_PATTERN = "yyyy-MM-dd";
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat(DATE_PATTERN);

    @Override
    public Object stringToValue(String text) throws ParseException {
        return dateFormatter.parseObject(text);
    }

    @Override
    public String valueToString(Object value) throws ParseException {
        if (value != null) {
            java.util.Date date = (java.util.Date) value;
            return dateFormatter.format(date);
        }
        return "";
    }
}
