import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class AssignTask {

    private JFrame f;
    private JComboBox comboBox;
    private DefaultTableModel model;
    
	public AssignTask(List<Task> tasks) {

        f = new JFrame();
        model = new DefaultTableModel();
        final TableRowSorter<TableModel> sorter = new TableRowSorter<>(model);

        model.addColumn("ID");
        model.addColumn("Task");
        model.addColumn("Priority");
        model.addColumn("Status");
        model.addColumn("Assigned To");
        model.addColumn("Start Date");
        model.addColumn("End Date");
        model.addColumn("Expected Time");
        JTable table = new JTable(model);
        table.setRowSorter(sorter);

        for(Task task : tasks){
            model.addRow(new Object[]{task.getId() + "", task.getTitle(), task.getPriority() + "", task.getStatus() + "", task.getAssignedTo() + "", task.getStartDate() + "", task.getEndDate() + "", task.getExpectedTimeTaken() + ""});
        }

        table.setBounds(30, 40, 300, 300);
        JScrollPane sp = new JScrollPane(table);
        f.add(sp, BorderLayout.CENTER);

        JPanel panel = new JPanel(new BorderLayout());
        JLabel label = new JLabel("Filter");
        panel.add(label, BorderLayout.WEST);
        final JTextField filterText = new JTextField("A");
        panel.add(filterText, BorderLayout.CENTER);
        f.add(panel, BorderLayout.NORTH);
        JButton button = new JButton("Filter");
        button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(((DefaultTableModel) table.getModel()));
                sorter.setRowFilter(RowFilter.regexFilter(filterText.getText()));

                table.setRowSorter(sorter);
            }
        });
        f.add(button, BorderLayout.SOUTH);
        f.setSize(300, 250);
        f.setVisible(true);


        f.add(sp);
        f.setSize(700, 400);
        f.setVisible(true);
        TableColumn col = table.getColumnModel().getColumn(4);
        comboBox = new JComboBox();
        comboBox.addItem("Mike");
        comboBox.addItem("Anita");
        comboBox.addItem("David");
        comboBox.addItem("Livia");
        col.setCellEditor(new DefaultCellEditor(comboBox));
        table.setAutoCreateRowSorter(true);
    }

	
}
