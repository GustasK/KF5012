import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

public class MainMenu extends JFrame implements ActionListener {
	
    private User currentUser;
    private Database database;
    private LoginDlg loginDlg;
    private ChangePassDlg changePassDlg;
    private AdminMenu adminMenu;
    private JButton login, logout, changePass, admin, exit;
    private User user;

    public MainMenu() {
       
    	database = new Database();
        loginDlg = new LoginDlg(this);

        this.user = user;

        JFrame frame = new JFrame("Main Menu | Capytec Ltd");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 400, 300);

        JPanel mainPnl = new JPanel();
        JPanel adminPnl = new JPanel();
        JPanel exitPnl = new JPanel();

        mainPnl.setLayout(new GridLayout(3, 1));
        adminPnl.setLayout(new FlowLayout(FlowLayout.RIGHT, 0, 2));

        login = new JButton("Log in");
        logout = new JButton("Log out");
        changePass = new JButton("Change password");
        admin = new JButton("Admin");
        exit = new JButton("Exit");

        logout.setVisible(false);
        changePass.setVisible(false);
        admin.setVisible(false);

        login.addActionListener(this);
        logout.addActionListener(this);
        changePass.addActionListener(this);
        admin.addActionListener(this);
        exit.addActionListener(this);

        Object[] columnNames = {"Column1",
                "Column2",
                "Column3",
                "Column4",
                "Column5"};
        Object[][] data = {
                {"ValueA1", "ValueA2", "ValueA3", "ValueA4", "ValueA5" },
                {"ValueB1", "ValueB2", "ValueB3", "ValueB4", "ValueB5" },
                {"ValueC1", "ValueC2", "ValueC3", "ValueC4", "ValueC5" },
                {"ValueD1", "ValueD2", "ValueD3", "ValueD4", "ValueD5" },
                {"ValueE1", "ValueE2", "ValueE3", "ValueE4", "ValueE5" }
        };
        JTable table = new JTable(data, columnNames);
        mainPnl.add(table);

        adminPnl.add(login);
        adminPnl.add(logout);
        adminPnl.add(changePass);
        adminPnl.add(admin);
        exitPnl.add(exit);

        frame.add(adminPnl, BorderLayout.NORTH);
        frame.add(mainPnl, BorderLayout.CENTER); //add pane to frame
        frame.add(exitPnl, BorderLayout.PAGE_END);
        frame.setSize(450, 400); //set size of frame (width, height)
        frame.setVisible(true); //make frame visible
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == login) {
            loginDlg.setVisible(true);
        }
        else if (src == logout) {
            loginDlg.setCurrentUser(null);
            toggleLoginLogout();
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

    public void toggleLoginLogout() {
        if (loginDlg.getCurrentUser() == null) { //if user has logged out
            login.setVisible(true); // makes 'Log in' button visible
            logout.setVisible(false); // hides 'Log out' button
            changePass.setVisible(false); // hides 'Change password' button
            admin.setVisible(false); // hides 'Admin' button
        } else  {    // if user has logged in
            login.setVisible(false); // hides 'Log in' button
            logout.setVisible(true); // makes 'Log out' button visible
            admin.setVisible(true); // makes 'Admin' button visible
            changePass.setVisible(true); // makes 'Change password' button visible
            currentUser = loginDlg.getCurrentUser();
        }
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

