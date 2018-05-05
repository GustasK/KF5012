import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.LineBorder;
import java.util.ArrayList;
import java.util.List;

public class LoginDlg extends JDialog implements ActionListener {
    Database database;
    MainMenu parent;
    List<User> allUsers;
    private User currentUser;
    private JLabel lblUsername;
    private JLabel lblPassword;
    private JTextField fieldUsername;
    private JPasswordField fieldPassword;
    private JButton btnSubmit, btnCancel;

    public LoginDlg(MainMenu parent) {
        this.parent = parent;
        database = new Database();
        allUsers = database.getUsers();
        setTitle("Log in | Cleaning | Capytec Ltd");

        JPanel pnl = new JPanel();

        lblUsername = new JLabel("Username");
        lblPassword = new JLabel("Password");
        fieldUsername = new JTextField(20);
        fieldPassword = new JPasswordField(20);
        btnSubmit = new JButton("Log-in");
        btnCancel = new JButton("Cancel");

        pnl.add(lblUsername);
        pnl.add(fieldUsername);
        pnl.add(lblPassword);
        pnl.add(fieldPassword);

        btnSubmit.addActionListener(this);
        btnCancel.addActionListener(this);

        pnl.add(btnSubmit);
        pnl.add(btnCancel);
        add(pnl);

        setBounds(100,100,350,150);
    }

    public boolean verifyCredentials(String username, String password) {
        boolean output = false;
        allUsers = database.getUsers();
        for (User user : allUsers) {
            if (username.equals(user.getName()) && password.equals(user.getPassword())) {
                output = true;
                setCurrentUser(user);
                break;
            } else {
                output = false;
            }
        }
        return output;
    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == btnCancel) {
            fieldUsername.setText("");
            fieldPassword.setText("");
            setVisible(false);
        }
        else if (src == btnSubmit) {
            String username = fieldUsername.getText();
            String password = String.valueOf(fieldPassword.getPassword());
            if (verifyCredentials(username, password)) { //if user successfully logs in
                parent.setCurrentUser(getCurrentUser()); //sets current user
                this.dispose(); //closes this dialog box
                resetLogin(); //resets login fields
                parent.getChangePassButton().setVisible(true); //makes 'Change password' button visible
                if (getCurrentUser().accessLevel > 1) { //if user is an administrator or manager
                    parent.getAdminButton().setVisible(true);
                }
                parent.toggleLoginLogout();
                JOptionPane.showMessageDialog(null,"Logged in as: " + username);
            }
            else {
                JOptionPane.showMessageDialog(null, "Incorrect username/password.");
            }
        }
    } //end actionPerformed

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        currentUser = user;
    }

    public void resetLogin() {
        fieldUsername.setText("");
        fieldPassword.setText("");
    }
}