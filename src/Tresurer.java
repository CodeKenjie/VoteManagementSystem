import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Enumeration;

public class Tresurer extends JFrame implements ActionListener{
    Main main = new Main();
    JButton pick;
    JButton cancel;
    ButtonGroup choose;

    Tresurer(){
        JPanel position = new JPanel();
        JPanel chooseBox = new JPanel(new GridLayout(10, 1, 10, 10));
        JScrollPane sc = new JScrollPane(chooseBox);
        sc.setViewportView(chooseBox);
        JPanel buttons = new JPanel(new FlowLayout());
        buttons.setPreferredSize(new Dimension(100, 50));
        choose = new ButtonGroup();
        
        JLabel position_l = new JLabel("Tresurer Position");
        position.add(position_l);

        pick = new JButton("pick");
        pick.addActionListener(this);
        cancel = new JButton("cancel");
        cancel.addActionListener(this);
        buttons.add(pick);
        buttons.add(cancel);
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM tresurerResult");
            
            while(rs.next()){
                JRadioButton rb = new JRadioButton(rs.getString("name"));
                choose.add(rb);
                chooseBox.add(rb);
            }

        } catch(ClassNotFoundException err) {
            err.printStackTrace();
        } catch(SQLException err) {
            err.printStackTrace();
        }
        
        this.add(position, BorderLayout.NORTH);
        this.add(sc, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(200, 300);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == pick){

            for(Enumeration<AbstractButton> buttons = choose.getElements(); buttons.hasMoreElements();){
                AbstractButton button = buttons.nextElement();
                if(button.isSelected()){
                    main.pickedtres = button.getText();
                    break;
                }
            }

            this.dispose();
        }
        if (e.getSource() == cancel) {
            this.dispose();
        }
    }
}
