import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class RegisterAsVoter extends JFrame implements ActionListener{
    Main main = new Main();
    String url = "jdbc:sqlite:VoteManagement.db";
    String cfn = "org.sqlite.JDBC";
    JTextField first_name;
    JTextField last_name;
    JTextField m_i;
    JTextField sufix;
    JTextField email;
    JTextField placeOfBirth;
    JTextField username;
    JPasswordField password;
    JPasswordField confirm_password;
    JSpinner age;
    JSpinner day;
    JSpinner month;
    JSpinner year;
    JButton register;
    JButton cancel;

    RegisterAsVoter(){
        String[] months = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
        JPanel fillup = new JPanel(null);
        fillup.setPreferredSize(new Dimension(0, 400));
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(350, 50));

        JLabel first_name_l = new JLabel("First Name:");
        first_name_l.setBounds(39, 20, 200, 30);
        first_name = new JTextField();
        first_name.setBounds(125, 20, 200, 30);
        fillup.add(first_name_l);
        fillup.add(first_name);

        JLabel last_name_l = new JLabel("Last Name:");
        last_name_l.setBounds(40, 55, 200, 30);
        last_name = new JTextField();
        last_name.setBounds(125, 55, 200, 30);
        fillup.add(last_name_l);
        fillup.add(last_name);

        JLabel m_i_l = new JLabel("M.I:");
        m_i_l.setBounds(93, 90, 200, 30);
        m_i = new JTextField();
        m_i.setBounds(125, 90, 50, 30);
        fillup.add(m_i_l);
        fillup.add(m_i);

        JLabel sufix_l = new JLabel("Sufix:");
        sufix_l.setBounds(190, 90, 200, 30);
        sufix = new JTextField("N/A");
        sufix.setBounds(235, 90, 50, 30);
        fillup.add(sufix_l);
        fillup.add(sufix);
        

        JLabel age_l = new JLabel("Age:");
        age_l.setBounds(88, 125, 50, 30);
        age = new JSpinner(new SpinnerNumberModel(18, 18, 120, 1));
        age.setBounds(125, 125, 50, 30);
        fillup.add(age_l);
        fillup.add(age);
        
        JLabel birthdate = new JLabel("Birth Date:");
        birthdate.setBounds(43, 160, 200, 30);
        month = new JSpinner(new SpinnerListModel(months));
        month.setBounds(125, 160, 80, 30);
        day = new JSpinner(new SpinnerNumberModel(1, 1, 31, 1));
        day.setBounds(215, 160, 40, 30);
        year = new JSpinner(new SpinnerNumberModel(2007, 1900, 2007, 1));
        year.setBounds(263, 160, 60, 30);
        fillup.add(birthdate);
        fillup.add(month);
        fillup.add(day);
        fillup.add(year);
        
        JLabel placeOfBirth_l = new JLabel("Birth Place:"); 
        placeOfBirth_l.setBounds(39, 195, 200, 30);
        placeOfBirth = new JTextField();
        placeOfBirth.setBounds(125, 195, 200, 30);
        fillup.add(placeOfBirth_l);
        fillup.add(placeOfBirth);

        JLabel email_l = new JLabel("Email:");
        email_l.setBounds(78, 230, 200, 30);
        email = new JTextField("Example@example.com");
        email.setBounds(125, 230, 200, 30);
        fillup.add(email_l);
        fillup.add(email);

        JLabel name = new JLabel("Username:");
        name.setBounds(43, 265, 200, 30);
        username = new JTextField();
        username.setBounds(125, 265, 200, 30);
        fillup.add(name);
        fillup.add(username);

        JLabel password_l = new JLabel("password:");
        password_l.setBounds(45, 300, 200, 30);
        password = new JPasswordField();
        password.setBounds(125, 300, 200, 30);
        fillup.add(password_l);
        fillup.add(password);
        fillup.add(password_l);

        JLabel c_password = new JLabel("Confirm:");
        c_password.setBounds(61, 335, 200, 30);
        confirm_password = new JPasswordField();
        confirm_password.setBounds(125, 335, 200, 30);
        fillup.add(c_password);
        fillup.add(confirm_password);

        register = new JButton("Register");
        register.addActionListener(this);
        cancel = new JButton("Cancel");
        cancel.addActionListener(this);
        buttons.add(register);
        buttons.add(cancel);

        this.add(fillup, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == register){
            String name = last_name.getText() + ", " + first_name.getText() + ", " + m_i.getText();
            String birthDate = (String) String.valueOf(month.getValue()) + ' ' + String.valueOf(day.getValue()) + ", " + String.valueOf(year.getValue()); 
            String birthPlace = placeOfBirth.getText(); 
            String email_s = email.getText();
            String user = username.getText();
            String password_s = password.getText();
            String c_password_s = confirm_password.getText();
            int age_i = (int) age.getValue();

            if (first_name.getText().equals("") && last_name.getText().equals("") && m_i.getText().equals("") && placeOfBirth.getText().equals("")){ 
                JOptionPane.showMessageDialog(null, "please Enter Complete information");
            } else if(!password_s.equals(c_password_s)){
                JOptionPane.showMessageDialog(null, "Password didn't match");
            } else {
                try{
                    Class.forName(cfn);
                    Connection conn = DriverManager.getConnection(url);
                    PreparedStatement prep = conn.prepareStatement("INSERT INTO registeredVoters(name, sufix, age, birthdate, birthplace, email, username, password, status, state) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    prep.setString(1, name);
                    prep.setString(2, sufix.getText());
                    prep.setInt(3, age_i);
                    prep.setString(4, birthDate);
                    prep.setString(5, birthPlace);
                    prep.setString(6, email_s);
                    prep.setString(7, user);
                    prep.setString(8, password_s);
                    prep.setString(9, "pending");
                    prep.setString(10, "pending");
                    prep.executeUpdate();

                    conn.close();
                    new loginPage();
                    this.dispose();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err){
                    err.printStackTrace();
                }
                
            }

        }

        if(e.getSource() == cancel){
            new loginPage();
            this.dispose();
        }
    }
}
