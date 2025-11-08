import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Vote extends JFrame implements ActionListener{
    Main main = new Main();
    JButton pickPres;
    JButton pickVicePres;
    JButton pickSecr;
    JButton pickTres;
    JButton pickAudi;
    JButton submit;
    JButton cancel;

    Vote(){
        JPanel Account = new JPanel(new GridLayout(2, 1));
        Account.setPreferredSize(new Dimension(400, 50));
        JPanel VotePanel = new JPanel(null);
        VotePanel.setPreferredSize(new Dimension(400, 300));
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setPreferredSize(new Dimension(400, 50));

        JLabel votersName = new JLabel("    Name: " + main.name);
        JLabel Title = new JLabel("    Title: voter");
        Account.add(votersName);
        Account.add(Title);

        JLabel vote = new JLabel("Vote:");
        vote.setBounds(50, 10, 200, 30);
        VotePanel.add(vote);

        JLabel pres_l = new JLabel("President Position:");
        pres_l.setBounds(62, 50, 200, 30);
        pickPres = new JButton("pick");
        pickPres.addActionListener(this);
        pickPres.setBounds(210, 50, 100, 30);
        VotePanel.add(pres_l);
        VotePanel.add(pickPres);

        JLabel vice_l = new JLabel("Vice President Position:");
        vice_l.setBounds(30, 100, 200, 30);
        pickVicePres = new JButton("pick");
        pickVicePres.addActionListener(this);
        pickVicePres.setBounds(210, 100, 100, 30);
        VotePanel.add(vice_l);
        VotePanel.add(pickVicePres);

        JLabel secr_l = new JLabel("Secretary Position:");
        secr_l.setBounds(62, 150, 200, 30);
        pickSecr = new JButton("pick");
        pickSecr.addActionListener(this);
        pickSecr.setBounds(210, 150, 100, 30);
        VotePanel.add(secr_l);
        VotePanel.add(pickSecr);

        JLabel tres_l = new JLabel("Tresurer Position:");
        tres_l.setBounds(70, 200, 200, 30);
        pickTres = new JButton("pick");
        pickTres.addActionListener(this);
        pickTres.setBounds(210, 200, 100, 30);
        VotePanel.add(tres_l);
        VotePanel.add(pickTres);

        JLabel audi_l = new JLabel("Auditor Position:");
        audi_l.setBounds(80, 250, 200, 30);
        pickAudi = new JButton("pick");
        pickAudi.addActionListener(this);
        pickAudi.setBounds(210, 250, 100, 30);
        VotePanel.add(audi_l);
        VotePanel.add(pickAudi);
        
        submit = new JButton("Submit");
        submit.addActionListener(this);
        cancel = new JButton("logout");
        cancel.addActionListener(this);
        cancel.addActionListener(this);
        buttonPanel.add(submit);
        buttonPanel.add(cancel);

        this.add(Account, BorderLayout.NORTH);
        this.add(VotePanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }


    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == pickPres){
            new President(); 
        }
        
        if (e.getSource() == pickVicePres){
            new VicePres(); 
        }

        if (e.getSource() == pickSecr){
            new Secretary(); 
        }

        if (e.getSource() == pickTres){
            new Tresurer(); 
        }

        if (e.getSource() == pickAudi){
            new Auditor(); 
        }
        if(e.getSource() == submit){
            if (main.pickedpres.equals("") || main.pickedvicepres.equals("") || main.pickedsecr.equals("") || main.pickedtres.equals("") || main.pickedaudi.equals("")) {
                JOptionPane.showMessageDialog(null, "you missed a position, please pick a Candidate in each position");
            } else {
                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE presidentResult SET votes = votes + 1 WHERE name = ?");

                    prep.setString(1, main.pickedpres);
                    prep.addBatch();
                    prep.executeBatch();

                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }       

                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE vicepresidentResult SET votes = votes + 1 WHERE name = ?");

                    prep.setString(1, main.pickedvicepres);
                    prep.addBatch();
                    prep.executeBatch();

                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }

                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE secretaryResult SET votes = votes + 1 WHERE name = ?");

                    prep.setString(1, main.pickedsecr);
                    prep.addBatch();
                    prep.executeBatch();

                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }

                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE tresurerResult SET votes = votes + 1 WHERE name = ?");

                    prep.setString(1, main.pickedtres);
                    prep.addBatch();
                    prep.executeBatch();

                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }

                try{
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE auditorResult SET votes = votes + 1 WHERE name = ?");

                    prep.setString(1, main.pickedaudi);
                    prep.addBatch();
                    prep.executeBatch();

                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }

                try {
                    Class.forName("org.sqlite.JDBC");
                    Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                    PreparedStatement prep = conn.prepareStatement("UPDATE registeredVoters SET state = ? WHERE name = ?");
                    prep.setString(1, "Voted");
                    prep.setString(2, main.name);
                    prep.executeUpdate();
                    conn.close();
                } catch(ClassNotFoundException err) {
                    err.printStackTrace();
                } catch(SQLException err) {
                    err.printStackTrace();
                }

                JOptionPane.showMessageDialog(null, "You have Successfully Voted. Please Wait for the admin to release the result. Thanks For Voting.");
                main.limiter++;
                new loginPage();
                this.dispose();
            }
        }

        if(e.getSource() == cancel){
            new loginPage();
            this.dispose();
        }
    }
}
