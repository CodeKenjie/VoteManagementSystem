import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class loginPage extends JFrame implements ActionListener{
    Main main = new Main();
    JTextField username;
    JPasswordField password;
    JComboBox chooseBox;
    JButton login;
    JButton register;
    String[] pick = {"Select role", "admin", "voter"};

    loginPage(){
        JPanel input = new JPanel(null);
        input.setPreferredSize(new Dimension(320, 100));
        JPanel button = new JPanel(new FlowLayout(FlowLayout.LEADING, 50, 5));
        JPanel chooser = new JPanel();

        chooseBox = new JComboBox(pick);
        chooseBox.addActionListener(this);
        chooser.add(chooseBox);
        JLabel un = new JLabel("Username:");
        un.setBounds(15, 20, 150, 30);
        username = new JTextField();
        username.setBounds(100, 20, 200, 30);
        input.add(un);
        input.add(username);

        JLabel pw = new JLabel("Password:");
        pw.setBounds(18, 60, 150, 30);
        password = new JPasswordField();
        password.setEchoChar('*');
        password.setBounds(100, 60, 200, 30);
        input.add(pw);
        input.add(password);

        login = new JButton("login");
        login.addActionListener(this);
        register = new JButton("register");
        register.addActionListener(this);
        button.add(login);
        button.add(register);


        this.add(chooser, BorderLayout.NORTH);
        this.add(input, BorderLayout.CENTER); 
        this.add(button, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == chooseBox){
            String role = (String) chooseBox.getSelectedItem();
            if (role.equals("admin")) {
                username.setEditable(true);
                register.setEnabled(false);
                username.setBackground(Color.white);
                password.setEditable(true);
                password.setBackground(Color.white);
                login.setEnabled(true);
                login.setText("login");
            } else if (role.equals("voter")){
                if (main.limiter == 25) {
                    login.setEnabled(false);
                }
                register.setEnabled(true);
                login.setText("vote");
            } else if (role.equals("Select role")){
                login.setEnabled(false);
                register.setEnabled(false);
                username.setEditable(false);
                password.setEditable(false);
                username.setBackground(Color.gray);
                password.setBackground(Color.gray);
            }
        }

        if (e.getSource() == login){
            String role = (String) chooseBox.getSelectedItem();
            String user = username.getText();
            String pass = password.getText();

            try {
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                PreparedStatement prep = conn.prepareStatement("SELECT * FROM registeredVoters WHERE username = ?");

                prep.setString(1, user);
                ResultSet rs = prep.executeQuery();

                String votername = rs.getString("username");
                String voterpass = rs.getString("password");
                String voterstatus = rs.getString("status");
                String voterstate = rs.getString("state");

                if (role.equals("voter") && user.equals(votername) && pass.equals(voterpass) && voterstate.equals("Voted")){
                    JOptionPane.showMessageDialog(null, "This Account already voted");
                } else if (role.equals("voter") && user.equals(votername) && pass.equals(voterpass) && voterstatus.equals("pending")){
                    JOptionPane.showMessageDialog(null, "This Account is not yet approved by the admin");
                }

                if (role.equals("voter") && user.equals(votername) && pass.equals(voterpass) && voterstate.equals("pending") && voterstatus.equals("Approved")) {
                    main.name = (String) rs.getString("name");
                    new Vote();
                    this.dispose();
                } else if (role.equals("voter") && !user.equals(votername) && !pass.equals(voterpass)) {
                    JOptionPane.showMessageDialog(null, "can't find information, you are not registered as a voter");
                }

                conn.close();
            } catch (ClassNotFoundException err) {
                err.printStackTrace();
            } catch (SQLException err) {
                err.printStackTrace();
            }

            if (role.equals("admin") && user.equals("admin") && pass.equals("1234")){
                new Admin();
                this.dispose();
            }
        }

        if (e.getSource() == register){
            String role = (String) chooseBox.getSelectedItem();
            if (role.equals("voter")) {
                new RegisterAsVoter(); 
                this.dispose();
            }
        }
    }
}
