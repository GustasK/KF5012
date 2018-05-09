import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JMenuBar;
import javax.swing.JProgressBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLayeredPane;
import javax.swing.JTabbedPane;
import java.awt.Color;
import java.awt.SystemColor;

public class Menu {

	private JFrame frame;
	private JTextField textField;
	private JTextField textField_1;
	private JLabel lblConfirmNewPassword;
	private JTextField textField_2;
	private JButton btnSubmit;
	private JButton btnCancel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Menu() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(SystemColor.menu);
		frame.setBounds(100, 100, 340, 162);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Current password");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 11, 97, 14);
		frame.getContentPane().add(lblNewLabel);
		
		textField = new JTextField();
		lblNewLabel.setLabelFor(textField);
		textField.setBounds(142, 8, 172, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewPassword = new JLabel("New password");
		lblNewPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewPassword.setBounds(10, 39, 97, 14);
		frame.getContentPane().add(lblNewPassword);
		
		textField_1 = new JTextField();
		lblNewPassword.setLabelFor(textField_1);
		textField_1.setColumns(10);
		textField_1.setBounds(142, 36, 172, 20);
		frame.getContentPane().add(textField_1);
		
		lblConfirmNewPassword = new JLabel("Confirm new password");
		lblConfirmNewPassword.setFont(new Font("Arial", Font.PLAIN, 11));
		lblConfirmNewPassword.setBounds(10, 67, 134, 14);
		frame.getContentPane().add(lblConfirmNewPassword);
		
		textField_2 = new JTextField();
		lblConfirmNewPassword.setLabelFor(textField_2);
		textField_2.setColumns(10);
		textField_2.setBounds(142, 64, 172, 20);
		frame.getContentPane().add(textField_2);
		
		btnSubmit = new JButton("Submit");
		btnSubmit.setFont(new Font("Arial", Font.PLAIN, 11));
		btnSubmit.setBounds(65, 92, 89, 23);
		frame.getContentPane().add(btnSubmit);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setFont(new Font("Arial", Font.PLAIN, 11));
		btnCancel.setBounds(164, 92, 89, 23);
		frame.getContentPane().add(btnCancel);
	}
}
