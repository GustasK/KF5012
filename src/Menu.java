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
		frame.setBounds(100, 100, 600, 402);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnNewButton = new JButton("Logout");
		btnNewButton.setBounds(500, 11, 75, 29);
		frame.getContentPane().add(btnNewButton);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as:");
		lblLoggedInAs.setBounds(10, 14, 315, 20);
		lblLoggedInAs.setFont(new Font("Arial", Font.PLAIN, 13));
		frame.getContentPane().add(lblLoggedInAs);
		
		JButton btnNewButton_1 = new JButton("Add task");
		btnNewButton_1.setBounds(400, 11, 90, 29);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnNewButton_1);
		
		JButton btnManageUsers = new JButton("manage users\r\n");
		btnManageUsers.setBounds(265, 11, 125, 29);
		btnManageUsers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		frame.getContentPane().add(btnManageUsers);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 564, 261);
		frame.getContentPane().add(tabbedPane);
		
		JScrollPane scrollPane = new JScrollPane();
		tabbedPane.addTab("New tab", null, scrollPane, null);
		
		textField = new JTextField();
		textField.setBounds(10, 330, 150, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setBounds(10, 310, 150, 14);
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		frame.getContentPane().add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("Search..");
		btnNewButton_2.setBounds(167, 329, 75, 23);
		btnNewButton_2.setFont(new Font("Arial", Font.PLAIN, 11));
		frame.getContentPane().add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Refresh");
		btnNewButton_3.setFont(new Font("Arial", Font.BOLD, 11));
		btnNewButton_3.setBounds(494, 324, 81, 29);
		frame.getContentPane().add(btnNewButton_3);
		
		JButton button = new JButton("Refresh");
		button.setFont(new Font("Arial", Font.BOLD, 11));
		button.setBounds(425, 324, 65, 29);
		frame.getContentPane().add(button);
	}
}
