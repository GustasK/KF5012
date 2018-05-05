import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ChangePassDlg extends JDialog implements ActionListener
{

    private MainMenu parent;
    private User currentUser;
    private Database database;
    private List<User> allUsers;
    private JLabel lblOldPassword, lblConfirmNewPass, lblNewPassword;
    private JPasswordField fieldOldPassword, fieldConfirmNewPass, fieldNewPassword;
    private JButton btnConfirm, btnCancel;

    public ChangePassDlg(MainMenu parent) {
        this.parent = parent;
        currentUser = parent.getCurrentUser();
        database = new Database();
        allUsers = database.getUsers();
        setTitle("Change password | Capytec Ltd");

        JPanel pnl = new JPanel();

        lblOldPassword = new JLabel("Enter current password: ");
        lblNewPassword = new JLabel("Enter new password: ");
        lblConfirmNewPass = new JLabel("Confirm new password: ");
        fieldOldPassword = new JPasswordField(20);
        fieldNewPassword = new JPasswordField(20);
        fieldConfirmNewPass = new JPasswordField(20);
        btnConfirm = new JButton("Confirm");
        btnCancel = new JButton("Cancel");

        pnl.add(lblOldPassword);
        pnl.add(lblNewPassword);
        pnl.add(lblConfirmNewPass);
        pnl.add(fieldOldPassword);
        pnl.add(fieldNewPassword);
        pnl.add(fieldConfirmNewPass);
        pnl.add(btnConfirm);
        pnl.add(btnCancel);

        btnConfirm.addActionListener(this);
        btnCancel.addActionListener(this);
        add(pnl);

        setBounds(100,100,350,150);
    }

    public void actionPerformed(ActionEvent e) {
    Object src = e.getSource();
    if (src == btnConfirm) {
        String currentPassword = convertPassword(fieldOldPassword);
        String newPassword = convertPassword(fieldNewPassword);
        String newPasswordConfirm = convertPassword(fieldConfirmNewPass);

        if (verifyPassword(currentPassword)) {

            if (newPassword.equals(newPasswordConfirm)) {
                if (isPasswordValid(newPassword)) {
                    database.update("users", currentUser.getUserID(), "password", newPassword);
                }
                else {
                    JOptionPane.showMessageDialog(null, "Password requirements not met");
                }
            }
            else {
               JOptionPane.showMessageDialog(null, "Passwords do not match");
            }
        }
        else {
            JOptionPane.showMessageDialog(null, "Incorrect password");
        }
    }
    else if (src == btnCancel) {
        this.dispose();
    }
    }

    public boolean verifyPassword(String password) {
    boolean output = false;
    User currentUser = parent.getCurrentUser();
    if (password.equals(currentUser.getPassword())) {
        output = true;
    }
    return output;
    }

    public String convertPassword(JPasswordField password) {
        char[] passwordChar = password.getPassword();
        return new String(passwordChar);
    }

    public boolean isPasswordValid(String password) {
        boolean output = false;
        boolean caps = false;
        boolean number = false;
        System.out.print(password);
        if (password.length() < 8) {
            System.out.print("Password too short");
            output = false;
        }
        for (char c : password.toCharArray()) {
            System.out.println(c);
            if (Character.isDigit(c)) {
                System.out.print(c);
                number = true;
            }
            else if(caps && number) {
                output = true;
                break;
            }
            if(Character.isUpperCase(c)) {
                System.out.print(c);
                caps = true;
            }
            else if (caps && number) {
                output = true;
                break;
            }
        }
        return output;
    }
}

