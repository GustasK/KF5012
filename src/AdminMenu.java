import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.SpringLayout;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class AdminMenu extends JFrame implements ActionListener {
    private Database database;
    private List<User> allUsers;
    private JFrame f;
    private JTabbedPane tp;
    private JComboBox<String> roleComboBox;
    private JButton btnExit, btnAUConfirm, btnGenerateTempPass, btnEUConfirm, btnEUReset, btnDUConfirm;
    private String[] roles = {"Select one...", "Caretaker", "Administrator", "Manager"};

    public AdminMenu() {
        database = new Database();
        allUsers = database.getUsers();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(200, 100, 400, 300);

        f = new JFrame("Admin | Capytec Ltd"); //creates new frame
        tp = new JTabbedPane(); //creates tabbed pane
        tp.setBounds(50,50,200,200); //sets size of tabbed pane
        setupAddUser(tp);
        setupEditUser(tp);
//        setupDeleteUser(tp);
        JPanel pnlMenuExit = new JPanel();
        btnExit = new JButton("Exit");
        btnExit.addActionListener(this);
        pnlMenuExit.add(btnExit);
        f.add(tp, BorderLayout.CENTER); //add pane to frame
        f.add(pnlMenuExit, BorderLayout.PAGE_END);
        f.setSize(600,600); //set size of frame
        f.setVisible(true); //make frame visible
    }

    public void setupAddUser(JTabbedPane tp) {
        // start of add user tab set up
        JPanel tbAU = new JPanel(); //creates 'Add user' tab
        JLabel lblForename = new JLabel("Forename: ");
        JTextField fieldForename = new JTextField(20);
        JLabel lblRole = new JLabel("Role: ");
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        JLabel lblTempPass = new JLabel("Temporary password: ");
        JTextField fieldTempPass = new JTextField("pass");
        fieldTempPass.setEditable(false);
        JButton btnGenerateTempPass = new JButton("Generate temporary password");
        btnGenerateTempPass.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fieldTempPass.setText(generateTempPass());
                btnGenerateTempPass.setEnabled(false);
            }
        });


        JButton btnAUConfirm = new JButton("Confirm");
        btnAUConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String forename = fieldForename.getText().trim();
                String role = (String) roleComboBox.getSelectedItem();
                String tempPass = fieldTempPass.getText();
                System.out.print(forename + " " + role + " " + tempPass);
                if (forename.equals("") || role.equals("Select one...") || tempPass.equals("")) {
                    JOptionPane.showMessageDialog(null,"Missing fields");
                }
                else {
                    if (JOptionPane.showConfirmDialog(null, "Add user?", "Confirm add user", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                        if (role.equals("Caretaker")) {
                            role = "1";
                        }
                        else if (role.equals("Administrator")) {
                            role = "2";
                        }
                        else {
                            role = "3";
                        }
                        for (User user : allUsers) {
                            if (forename.equals(user.name)) {
                                JOptionPane.showMessageDialog(null,"That username is already taken!");
                                break;
                            }
                            else {
                                String[] fields = {"name", "password", "permissions"};
                                String[] values = {forename, tempPass, role};
                                database.insert("users", fields, values);
                                break;
                            }
                        }

                    }
                }
                fieldForename.setText("");
                roleComboBox.setSelectedIndex(0);
                fieldTempPass.setText("Generate a temporary password");
                btnGenerateTempPass.setEnabled(true);
            }
        });
        JButton btnAUReset = new JButton("Reset");
        btnAUReset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                fieldForename.setText("");
                roleComboBox.setSelectedIndex(0);
                fieldTempPass.setText("Generate a temporary password");
                btnGenerateTempPass.setEnabled(true);
            }
        });

        tbAU.add(lblForename);
        tbAU.add(fieldForename);
        tbAU.add(lblRole);
        tbAU.add(roleComboBox);
        tbAU.add(lblTempPass);
        tbAU.add(fieldTempPass);
        tbAU.add(btnGenerateTempPass);
        tbAU.add(btnAUConfirm);
        tbAU.add(btnAUReset);
        tp.add("Add user", tbAU); //add tabs to pane

    } //end of setupAddUser()

    public void setupEditUser(JTabbedPane tp) {
        JPanel tbEU = new JPanel(); //creates 'Edit user' tab
        JLabel lblselectUser = new JLabel("Select user: ");
        List<User> users = database.getUsers();
        String[] usernames;
        //JComboBox<String> comboUsers = new JComboBox<>(users);
        JLabel lblEUForename = new JLabel("Forename: ");
        JTextField fieldEUOldForename = new JTextField("Update values to view");
        fieldEUOldForename.setEditable(false);
        JTextField fieldEUNewForename = new JTextField(20);
        JLabel lblAccessLevel = new JLabel("Access level: ");
        JTextField fieldOldRole = new JTextField("Update values to view");
        fieldOldRole.setEditable(false);
        JButton btnEUUpdate = new JButton("Update values");
        /*btnEUUpdate.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                String selected = comboUsers.getSelectedItem().toString();
                System.out.print(selected);
                for (User user : allUsers) {
                    if (selected.equals(user.name)) {
                        fieldEUOldForename.setText(user.name);
                        fieldOldRole.setText(String.valueOf(user.getAccessLevel()));
                        break;
                    } else {
                        System.out.print("no user found");
                    }
                }
            }
        });*/
        roleComboBox = new JComboBox<>(roles);
        btnEUConfirm = new JButton("Confirm");
        btnEUConfirm.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnEUReset = new JButton("Reset");
        btnEUReset.addActionListener(this);

        tbEU.add(lblselectUser);
        //tbEU.add(comboUsers);
        tbEU.add(btnEUUpdate);
        tbEU.add(lblEUForename);
        tbEU.add(fieldEUOldForename);
        tbEU.add(fieldEUNewForename);
        tbEU.add(lblAccessLevel);
        tbEU.add(fieldOldRole);
        tbEU.add(roleComboBox);
        tbEU.add(btnEUConfirm);
        tbEU.add(btnEUReset);
        tp.add("Edit user", tbEU);
    }

//    public void setupDeleteUser(JTabbedPane tp) {
//        JPanel tbDU = new JPanel(); //creates 'Delete user' tab
//        JLabel lblDUUserID = new JLabel("Select user: ");
//        String[] users = null;
//        for (int i = 0; i < allUsers.size; i++)
//            users[i] = allUsers[i].name;
//        
//        JComboBox<String> comboUsers = new JComboBox<String>(users);
//        btnDUConfirm = new JButton("Confirm");
//        btnDUConfirm.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                //database.delete("users","id", "=", )
//
//            }
//
//        });
//
//        tbDU.add(lblDUUserID);
//        tbDU.add(comboUsers);
//        tbDU.add(btnDUConfirm);
//        tp.add("Delete user", tbDU);
//    }

    public void actionPerformed(ActionEvent evt) {
        Object src = evt.getSource();
        if (src == btnExit) {
            if (JOptionPane.showConfirmDialog(null,"Are you sure you want to exit?", "Exit Admin Menu | Capytec Ltd", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                f.dispose();
            }
        }
        else if (src == btnAUConfirm) {

        }
    }

    public String generateTempPass() {
        char[] alphaNum = "AaBbCcDdEeFfGgHhIiJjKkLlMmNnOoPpQqRrSsTtUuVvWwXxYyZz012345789".toCharArray();
        Random random = new Random();
        StringBuilder string = new StringBuilder(12);

        for (int i = 0; i < 12; i++) {
            char c = alphaNum[random.nextInt(alphaNum.length)];
            string.append(c);
        }
        System.out.print(string);
        return string.toString();
    }
}
