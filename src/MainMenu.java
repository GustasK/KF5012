import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import java.io.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableRowSorter;



public class MainMenu extends JFrame implements ActionListener {
	
    private Database database;
    private User currentUser;
    private LoginDialog loginDlg;
    private ChangePassDlg changePassDlg;
    private AdminMenu adminMenu;
    private JButton login, logout, changePass, admin, exit, addTaskButton;

    private JFrame frame;
    private JButton logoutButton;
    private DefaultTableModel table;
    private JComboBox comboBox;
    private JButton adminButton;
    
    private String username;

    public MainMenu(int userID) {
       
    	database = new Database();
        currentUser = database.getUser(userID);
    	
        frame = new JFrame("Main Menu | Capytec Ltd");
        frame.setBounds(100, 100, 750, 400);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(650, 11, 75, 29);
		frame.getContentPane().add(logoutButton);
		logoutButton.addActionListener(this);
		
		adminButton = new JButton("Manage users");
		adminButton.addActionListener(this);
		adminButton.setBounds(415, 11, 125, 29);
		frame.getContentPane().add(adminButton);
		
		addTaskButton = new JButton("Add task");
		frame.getContentPane().add(addTaskButton);
		addTaskButton.addActionListener(this);
		addTaskButton.setBounds(550, 11, 90, 29);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as: " + currentUser.getName());
		lblLoggedInAs.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLoggedInAs.setBounds(10, 14, 315, 20);
		frame.getContentPane().add(lblLoggedInAs);
		
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 714, 240);
		frame.getContentPane().add(tabbedPane);
		
		addMyTasksTab(tabbedPane);
		addRegularTasks(tabbedPane);
		addOneOffTasks(tabbedPane);
		
//		JPanel panel = new JPanel();
//		panel.setBounds(10, 45, 565, 171);
//		frame.getContentPane().add(panel);
//		
//		table = new DefaultTableModel();
//		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table);
//		
//		table.addColumn("ID");
//		table.addColumn("Task");
//		table.addColumn("Priority");
//		table.addColumn("Status");
//		table.addColumn("Assigned To");
//		table.addColumn("Start Date");
//		table.addColumn("End Date");
//		table.addColumn("Expected Time");
//		
//        JTable tasksList = new JTable(table);
//        tasksList.setRowHeight(25);
//        tasksList.setRowSorter(sorter);
//
//        List<Task> tasks = new ArrayList<Task>();
//        tasks = database.getTasks();
//        
//        for(Task task : tasks){
//        	table.addRow(new Object[]{task.getId() + "", task.getTitle(), task.getPriority() + "", task.getStatus() + "", task.getAssignedTo() + "", task.getStartDate() + "", task.getEndDate() + "", task.getExpectedTimeTaken() + ""});
//        }
//		
//        tasksList.setBounds(0, 40, 300, 200);
//        
//        JScrollPane scrollPanel = new JScrollPane(tasksList);
//        panel.add(scrollPanel);
//        
//		
//        TableColumn col = tasksList.getColumnModel().getColumn(4);
//        comboBox = new JComboBox();
//        comboBox.addItem("Mike");
//        comboBox.addItem("Anita");
//        comboBox.addItem("David");
//        comboBox.addItem("Livia");
//        col.setCellEditor(new DefaultCellEditor(comboBox));
//        tasksList.setAutoCreateRowSorter(true);
        
		frame.setVisible(true);       
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == login) {
            loginDlg.setVisible(true);
        }
        else if (src == addTaskButton) {
        	AddTask task = new AddTask();
        }
        else if (src == logoutButton) {
            System.exit(0); // or this.frame.dispose(); ??
        }
        else if (src == adminButton) {
        	AdminMenu adminMenu = new AdminMenu();
        }
        else if (src == changePass) {
            changePassDlg = new ChangePassDlg(this);
            changePassDlg.setVisible(true);
        }
        else if (src == admin) {
            adminMenu = new AdminMenu();
        } else if (src == exit) {
            System.exit(0);
        }
    }

    public void addMyTasksTab(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		table = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table);
		
		table.addColumn("Task");
		table.addColumn("Priority");
		table.addColumn("Status");
		table.addColumn("Start Date");
		table.addColumn("End Date");
		table.addColumn("Expected Time");
		
        JTable tasksList = new JTable(table);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);

        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getAssignedTo() == currentUser.getUserID()) {
        		table.addRow(new Object[]{task.getTitle(), task.getPriority() + "", task.getStatus() + "", task.getStartDate() + "", task.getEndDate() + "", task.getExpectedTimeTaken() + ""});
        	}
    	}
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList);
        tabbedPane.add("My tasks", scrollPanel);
    }
    
    public void addRegularTasks(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		table = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table);
		
		table.addColumn("Task");
		table.addColumn("Priority");
		table.addColumn("Status");
		table.addColumn("Assigned To");
		table.addColumn("Start Date");
		table.addColumn("End Date");
		table.addColumn("Expected Time");
		
        JTable tasksList = new JTable(table);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);

        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getType().equals("regular")) {
        		table.addRow(new Object[]{task.getTitle(), task.getPriority() + "", task.getStatus() + "", task.getAssignedTo() + "", task.getStartDate() + "", task.getEndDate() + "", task.getExpectedTimeTaken() + ""});
        		System.out.println("added");
        	}
    	}
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList);
        tabbedPane.add("Regular tasks", scrollPanel);
    }
    
    public void addOneOffTasks(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		table = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(table);
		
		table.addColumn("Task");
		table.addColumn("Priority");
		table.addColumn("Status");
		table.addColumn("Assigned To");
		table.addColumn("Start Date");
		table.addColumn("End Date");
		table.addColumn("Expected Time");
		
        JTable tasksList = new JTable(table);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);

        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getType().equals("one-off")) {
            	table.addRow(new Object[]{task.getTitle(), task.getPriority() + "", task.getStatus() + "", task.getAssignedTo() + "", task.getStartDate() + "", task.getEndDate() + "", task.getExpectedTimeTaken() + " hours"});	
        	}
        }
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList);
        tabbedPane.add("One-off tasks", scrollPanel);
    }

    public JButton getAdminButton() {
        return admin;
    }

    public JButton getChangePassButton() {
        return changePass;
    }

    public void setCurrentUser(User currentUser){
        this.currentUser = currentUser;
    }

    public User getCurrentUser() {
        return currentUser;
    }
}

