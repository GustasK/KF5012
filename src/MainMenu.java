import java.awt.*;
import java.util.List;
import java.util.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

import java.io.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.table.TableRowSorter;



public class MainMenu extends JFrame implements ActionListener {
	
    private Database database;
    private User currentUser;
    private List<User> users;
    private LoginDialog loginDlg;
    private ChangePassDlg changePassDlg;
    private AdminMenu adminMenu;
    private JButton login, logout, changePass, admin, exit, addTaskButton, searchButton, saveButton, refreshButton;

    private JFrame frame;
    private JButton logoutButton;
    private DefaultTableModel myTasksTable, regularTasksTable, oneOffTasksTable;
    private JComboBox comboBox;
    private JButton adminButton, changePassword;
    private JTextField searchField;
    private JTabbedPane tabbedPane;

    public MainMenu(int userID) {
       
    	database = new Database();
        currentUser = database.getUser(userID);
        users = database.getUsers();
    	
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
		
		changePassword = new JButton("Change password");
		changePassword.addActionListener(this);
		changePassword.setBounds(265, 11, 140, 29);
		frame.getContentPane().add(changePassword);
		
		addTaskButton = new JButton("Add task");
		frame.getContentPane().add(addTaskButton);
		addTaskButton.addActionListener(this);
		addTaskButton.setBounds(550, 11, 90, 29);
		
		JLabel lblLoggedInAs = new JLabel("Logged in as: " + currentUser.getName());
		lblLoggedInAs.setFont(new Font("Arial", Font.PLAIN, 13));
		lblLoggedInAs.setBounds(10, 14, 315, 20);
		frame.getContentPane().add(lblLoggedInAs);
		
		
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(10, 58, 714, 240);
		frame.getContentPane().add(tabbedPane);
		
		addMyTasksTab(tabbedPane);
		addRegularTasks(tabbedPane);
		addOneOffTasks(tabbedPane);
		
		searchField = new JTextField();
		searchField.setBounds(10, 330, 150, 20);
		frame.getContentPane().add(searchField);
		searchField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Filter");
		lblNewLabel.setFont(new Font("Arial", Font.PLAIN, 11));
		lblNewLabel.setBounds(10, 310, 150, 14);
		frame.getContentPane().add(lblNewLabel);
		
		searchButton = new JButton("Search..");
		searchButton.setFont(new Font("Arial", Font.PLAIN, 11));
		searchButton.setBounds(167, 329, 75, 23);
		frame.getContentPane().add(searchButton);
		searchButton.addActionListener(this);
		
		refreshButton = new JButton("Refresh");
		refreshButton.setFont(new Font("Arial", Font.BOLD, 11));
		refreshButton.setBounds(644, 324, 81, 29);
		frame.getContentPane().add(refreshButton);
		refreshButton.addActionListener(this);
		
		saveButton = new JButton("Save");
		saveButton.addActionListener(this);
		saveButton.setFont(new Font("Arial", Font.BOLD, 11));
		saveButton.setBounds(575, 324, 65, 29);
		frame.getContentPane().add(saveButton);
        
		frame.setVisible(true);       
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == refreshButton) {
        	switch(tabbedPane.getSelectedIndex()) {
        		case 0: {
        			for(int i = myTasksTable.getRowCount() - 1; i > -1; i--) {
        				myTasksTable.removeRow(i);
        			}
        			
        	        List<Task> tasks = new ArrayList<Task>();
        	        tasks = database.getTasks();
        	        
        	        for(Task task : tasks){
        	        	if(task.getAssignedTo() == currentUser.getUserID()) {
        	        		myTasksTable.addRow(new Object[]{
        	        			task.getTitle(), 
        	        			task.getPriority() + "", 
        	        			task.getStatus() ? "Done" : "Not finished",  
        	        			task.getStartDate() + "", 
        	        			task.getEndDate() + "", 
        	        			task.getExpectedTimeTaken() + " hours"});
        	        	}
        	    	}
        	        
        			break;
        		}
        		case 1: {
        			for(int i = regularTasksTable.getRowCount() - 1; i > -1; i--) {
        				regularTasksTable.removeRow(i);
        			}
        			
        	        List<Task> tasks = new ArrayList<Task>();
        	        tasks = database.getTasks();
        	        
        	        for(Task task : tasks){
        	        	if(task.getType().equals("regular")) {
        	        		
        	        		String assignedTo = "Unassigned";
        	        		for(User user: users) {
        	        			if(user.getUserID() == task.getAssignedTo())
        	        				assignedTo = user.getName().toString();
        	        		}
        	        		
        	        		regularTasksTable.addRow(new Object[]{
    	            			task.getTitle(), 
    	            			task.getPriority() + "", 
    	            			task.getStatus() ? "Done" : "Not finished", 
    	            			assignedTo,
    	            			task.getStartDate() + "", 
    	            			task.getEndDate() + "", 
    	            			task.getExpectedTimeTaken() + " hours"
        	            	});
        	        	}
        	    	}
        	        
        			break;
        		}
        		case 2: {
        			for(int i = oneOffTasksTable.getRowCount() - 1; i > -1; i--) {
        				oneOffTasksTable.removeRow(i);
        			}
        			
        	        List<Task> tasks = new ArrayList<Task>();
        	        tasks = database.getTasks();
        	        
        	        for(Task task : tasks){
        	        	if(task.getType().equals("one-off")) {
        	        		
        	        		String assignedTo = "Unassigned";
        	        		for(User user: users) {
        	        			if(user.getUserID() == task.getAssignedTo())
        	        				assignedTo = user.getName().toString();
        	        		}
        	        		
        	        		oneOffTasksTable.addRow(new Object[]{
    	            			task.getTitle(), 
    	            			task.getPriority() + "", 
    	            			task.getStatus() ? "Done" : "Not finished", 
    	            			assignedTo,
    	            			task.getStartDate() + "", 
    	            			task.getEndDate() + "", 
    	            			task.getExpectedTimeTaken() + " hours"
        	            	});
        	        	}
        	    	}
        	        
        			break;
        		}
        	}
        }
        else if (src == searchButton) {
        	switch(tabbedPane.getSelectedIndex()) {
        		case 0: {
        			JTable table = new JTable(myTasksTable);
                    TableRowSorter<TableModel> sorter = new TableRowSorter<>(((DefaultTableModel) table.getModel()));
                    sorter.setRowFilter(RowFilter.regexFilter(searchField.getText()));

                    table.setRowSorter(sorter);
        			break;
        		}
        	}
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
        else if (src == changePassword) {
            changePassDlg = new ChangePassDlg(this);
            changePassDlg.setVisible(true);
        }
    }

    public void addMyTasksTab(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		myTasksTable = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(myTasksTable);
		
		myTasksTable.addColumn("Task");
		myTasksTable.addColumn("Priority");
		myTasksTable.addColumn("Status");
		myTasksTable.addColumn("Start Date");
		myTasksTable.addColumn("End Date");
		myTasksTable.addColumn("Expected Time");
		
        JTable tasksList = new JTable(myTasksTable);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);
        
        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getAssignedTo() == currentUser.getUserID()) {
        		myTasksTable.addRow(new Object[]{
        			task.getTitle(), 
        			task.getPriority() + "", 
        			task.getStatus() ? "Done" : "Not finished",  
        			task.getStartDate() + "", 
        			task.getEndDate() + "", 
        			task.getExpectedTimeTaken() + " hours"});
        	}
    	}
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList); 
        
        tasksList.getColumnModel().getColumn(1).setPreferredWidth(25);
        tasksList.getColumnModel().getColumn(2).setPreferredWidth(25);
        tasksList.getColumnModel().getColumn(5).setPreferredWidth(25);
        
        tabbedPane.add("My tasks", scrollPanel);
    }
    
    public void addRegularTasks(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		regularTasksTable = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(regularTasksTable);
		
		regularTasksTable.addColumn("Task");
		regularTasksTable.addColumn("Priority");
		regularTasksTable.addColumn("Status");
		regularTasksTable.addColumn("Assigned To");
		regularTasksTable.addColumn("Start Date");
		regularTasksTable.addColumn("End Date");
		regularTasksTable.addColumn("Expected Time");
		
        JTable tasksList = new JTable(regularTasksTable);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);

        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getType().equals("regular")) {
        		
        		String assignedTo = "Unassigned";
        		for(User user: users) {
        			if(user.getUserID() == task.getAssignedTo())
        				assignedTo = user.getName().toString();
        		}
        		
        		regularTasksTable.addRow(new Object[]{
        			task.getTitle(), 
        			task.getPriority() + "", 
        			task.getStatus() ? "Done" : "Not finished", 
        			assignedTo,
        			task.getStartDate() + "", 
        			task.getEndDate() + "", 
        			task.getExpectedTimeTaken() + " hours"
        		});
        	}
    	}
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList);
        
		TableColumn col = tasksList.getColumnModel().getColumn(3);
		comboBox = new JComboBox();
		for(User user: users) {
			comboBox.addItem(user.getName());
		}
		col.setCellEditor(new DefaultCellEditor(comboBox));
		tasksList.setAutoCreateRowSorter(true);
		
        tabbedPane.add("Regular tasks", scrollPanel);
    }
    
    public void addOneOffTasks(JTabbedPane tabbedPane) {
    	
    	JPanel panel = new JPanel();
		panel.setBounds(10, 45, 565, 171);
		frame.getContentPane().add(panel);
		
		oneOffTasksTable = new DefaultTableModel();
		final TableRowSorter<TableModel> sorter = new TableRowSorter<>(oneOffTasksTable);
		
		oneOffTasksTable.addColumn("Task");
		oneOffTasksTable.addColumn("Priority");
		oneOffTasksTable.addColumn("Status");
		oneOffTasksTable.addColumn("Assigned To");
		oneOffTasksTable.addColumn("Start Date");
		oneOffTasksTable.addColumn("End Date");
		oneOffTasksTable.addColumn("Expected Time");
		
        JTable tasksList = new JTable(oneOffTasksTable);
        tasksList.setRowHeight(25);
        tasksList.setRowSorter(sorter);

        List<Task> tasks = new ArrayList<Task>();
        tasks = database.getTasks();
        
        for(Task task : tasks){
        	if(task.getType().equals("one-off")) {
        		
        		String assignedTo = "Unassigned";
        		for(User user: users) {
        			if(user.getUserID() == task.getAssignedTo())
        				assignedTo = user.getName().toString();
        		}
        		
        		oneOffTasksTable.addRow(new Object[]{
        			task.getTitle(), 
        			task.getPriority() + "", 
        			task.getStatus() ? "Done" : "Not finished",
        			assignedTo,
        			task.getStartDate() + "", 
        			task.getEndDate() + "", 
        			task.getExpectedTimeTaken() + " hours"
        		});
        	}
    	}
		
        tasksList.setBounds(0, 40, 300, 200);
        
        JScrollPane scrollPanel = new JScrollPane(tasksList);
        
		TableColumn col = tasksList.getColumnModel().getColumn(3);
		comboBox = new JComboBox();
		for(User user: users) {
			comboBox.addItem(user.getName());
		}
		col.setCellEditor(new DefaultCellEditor(comboBox));
		tasksList.setAutoCreateRowSorter(true);
		
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

