import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Admin extends JFrame implements ActionListener{
    Main main = new Main();
    JButton logout;
    JButton voterslist;
    JButton addprescandi;
    JButton remprescandi;
    JButton addvicecandi;
    JButton remvicecandi;
    JButton addseccandi;
    JButton remseccandi;
    JButton addtrescandi;
    JButton remtrescandi;
    JButton addaudicandi;
    JButton remaudicandi;
    DefaultTableModel pres;
    DefaultTableModel vice;
    DefaultTableModel secr;
    DefaultTableModel tres;
    DefaultTableModel audi;
    JTable presidentResult;
    JTable vicepresResult;
    JTable secretaryResult;
    JTable tresurerResult;
    JTable auditorResult;
    JTextField RegisteredVoters;

    public void LoadCandidates(DefaultTableModel model, String sqlTable){
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM " + sqlTable);

            while (rs.next()){
                model.addRow(new Object[]{rs.getInt("id"),rs.getString("name"), rs.getInt("votes")});
            }
            conn.close();
        } catch(ClassNotFoundException err) {
            err.printStackTrace();
        } catch(SQLException err) {
            err.printStackTrace();
        }
    }

    Admin(){
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);
        JPanel tables = new JPanel(null);
        JScrollPane tablessc = new JScrollPane(tables);
        tablessc.setViewportView(tables);
        tables.setPreferredSize(new Dimension(500, 1220));
        JPanel buttons = new JPanel(null);
        buttons.setPreferredSize(new Dimension(500, 100));
        String[] cols = {"id", "Candidates", "Votes"};

        addprescandi = new JButton("+");
        addprescandi.addActionListener(this);
        addprescandi.setBounds(438, 18, 25, 20);
        addprescandi.setMargin(new Insets(0, 0, 0, 0));
        remprescandi = new JButton("-");
        remprescandi.addActionListener(this);
        remprescandi.setBounds(468, 18, 25, 20);
        remprescandi.setMargin(new Insets(0, 0, 0, 0));
        JLabel pres_l = new JLabel("President Canditates:");
        pres_l.setBounds(5, 15, 200, 30);
        pres = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        };
        pres.setColumnIdentifiers(cols);
        presidentResult = new JTable(pres);
        presidentResult.getColumnModel().getColumn(0).setCellRenderer(cr);
        presidentResult.getColumnModel().getColumn(1).setCellRenderer(cr);
        presidentResult.getColumnModel().getColumn(2).setCellRenderer(cr);
        JScrollPane pressc = new JScrollPane(presidentResult);
        pressc.setViewportView(presidentResult);
        pressc.setBounds(5, 40, 490, 200);
        tables.add(addprescandi);
        tables.add(remprescandi);
        tables.add(pres_l);
        tables.add(pressc);

        addvicecandi = new JButton("+");
        addvicecandi.addActionListener(this);
        addvicecandi.setBounds(438, 258, 25, 20);
        addvicecandi.setMargin(new Insets(0, 0, 0, 0));
        remvicecandi = new JButton("-");
        remvicecandi.addActionListener(this);
        remvicecandi.setBounds(468, 258, 25, 20);
        remvicecandi.setMargin(new Insets(0, 0, 0, 0));
        JLabel vice_l = new JLabel("Vice President Canditates:");
        vice_l.setBounds(5, 255, 200, 30);
        vice = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        };
        vice.setColumnIdentifiers(cols);
        vicepresResult = new JTable(vice);
        vicepresResult.getColumnModel().getColumn(0).setCellRenderer(cr);
        vicepresResult.getColumnModel().getColumn(1).setCellRenderer(cr);
        vicepresResult.getColumnModel().getColumn(2).setCellRenderer(cr);
        JScrollPane vicesc = new JScrollPane(vicepresResult);
        vicesc.setViewportView(vicepresResult);
        vicesc.setBounds(5, 280, 490, 200);
        tables.add(addvicecandi);
        tables.add(remvicecandi);
        tables.add(vice_l);
        tables.add(vicesc);

        addseccandi = new JButton("+");
        addseccandi.addActionListener(this);
        addseccandi.setBounds(438, 498, 25, 20);
        addseccandi.setMargin(new Insets(0, 0, 0, 0));
        remseccandi = new JButton("-");
        remseccandi.addActionListener(this);
        remseccandi.setBounds(468, 498, 25, 20);
        remseccandi.setMargin(new Insets(0, 0, 0, 0));
        JLabel secr_l = new JLabel("Secretary Canditates:");
        secr_l.setBounds(5, 495, 200, 30);
        secr = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        };
        secr.setColumnIdentifiers(cols);
        secretaryResult = new JTable(secr);
        secretaryResult.getColumnModel().getColumn(0).setCellRenderer(cr);
        secretaryResult.getColumnModel().getColumn(1).setCellRenderer(cr);
        secretaryResult.getColumnModel().getColumn(2).setCellRenderer(cr);
        JScrollPane secrsc = new JScrollPane(secretaryResult);
        secrsc.setViewportView(secretaryResult);
        secrsc.setBounds(5, 520, 490, 200);
        tables.add(addseccandi);
        tables.add(remseccandi);
        tables.add(secr_l);
        tables.add(secrsc);
        
        addtrescandi = new JButton("+");
        addtrescandi.addActionListener(this);
        addtrescandi.setBounds(438, 738, 25, 20);
        addtrescandi.setMargin(new Insets(0, 0, 0, 0));
        remtrescandi = new JButton("-");
        remtrescandi.addActionListener(this);
        remtrescandi.setBounds(468, 738, 25, 20);
        remtrescandi.setMargin(new Insets(0, 0, 0, 0));
        JLabel tres_l = new JLabel("Tresurer Canditates:");
        tres_l.setBounds(5, 735, 200, 30);
        tres = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        };
        tres.setColumnIdentifiers(cols);
        tresurerResult = new JTable(tres);
        tresurerResult.getColumnModel().getColumn(0).setCellRenderer(cr);
        tresurerResult.getColumnModel().getColumn(1).setCellRenderer(cr);
        tresurerResult.getColumnModel().getColumn(2).setCellRenderer(cr);
        JScrollPane tressc = new JScrollPane(tresurerResult);
        tressc.setViewportView(tresurerResult);
        tressc.setBounds(5, 760, 490, 200);
        tables.add(addtrescandi);
        tables.add(remtrescandi);
        tables.add(tres_l);
        tables.add(tressc);

        addaudicandi = new JButton("+");
        addaudicandi.addActionListener(this);
        addaudicandi.setBounds(438, 983, 25, 20);
        addaudicandi.setMargin(new Insets(0, 0, 0, 0));
        remaudicandi = new JButton("-");
        remaudicandi.addActionListener(this);
        remaudicandi.setBounds(468, 983, 25, 20);
        remaudicandi.setMargin(new Insets(0, 0, 0, 0));
        JLabel audi_l = new JLabel("Auditor Canditates:");
        audi_l.setBounds(5, 980, 200, 30);
        audi = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        }; 
        audi.setColumnIdentifiers(cols);
        auditorResult = new JTable(audi);
        auditorResult.getColumnModel().getColumn(0).setCellRenderer(cr);
        auditorResult.getColumnModel().getColumn(1).setCellRenderer(cr);
        auditorResult.getColumnModel().getColumn(2).setCellRenderer(cr);
        JScrollPane audisc = new JScrollPane(auditorResult);
        audisc.setViewportView(auditorResult);
        audisc.setBounds(5, 1005, 490, 200);
        tables.add(addaudicandi);
        tables.add(remaudicandi);
        tables.add(audi_l);
        tables.add(audisc);

        JLabel tvr = new JLabel("Total Registered Voters:");
        tvr.setBounds(5, 10, 200, 30);
        voterslist = new JButton("Voters List");
        voterslist.addActionListener(this);
        voterslist.setBounds(240, 10, 120, 30);
        RegisteredVoters = new JTextField();
        RegisteredVoters.setBounds(185, 10, 50, 30);
        RegisteredVoters.setEditable(false);
        RegisteredVoters.setText(String.valueOf(main.limiter));
        buttons.add(tvr);
        buttons.add(voterslist);
        buttons.add(RegisteredVoters);

        logout = new JButton("Logout"); 
        logout.addActionListener(this);
        logout.setBounds(410, 60, 100, 30);
        buttons.add(logout);

        this.LoadCandidates(pres, "presidentResult");
        this.LoadCandidates(vice, "vicepresidentResult");
        this.LoadCandidates(secr, "secretaryResult");
        this.LoadCandidates(tres, "tresurerResult");
        this.LoadCandidates(audi, "auditorResult");
        this.add(tablessc, BorderLayout.CENTER);
        this.add(buttons, BorderLayout.SOUTH);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(520, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void addCandidate(DefaultTableModel model, String sqlTable){
        String candidateName = JOptionPane.showInputDialog(null, "Enter candidate name: ");

        if (!candidateName.equals("")) {
            try{
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                PreparedStatement prep = conn.prepareStatement("INSERT INTO " + sqlTable + "(name, votes) VALUES(?, ?)");
                prep.setString(1, candidateName);
                prep.setInt(2, 0);
                prep.executeUpdate();

                conn.close();
                model.setRowCount(0);
                this.LoadCandidates(model, sqlTable);
            } catch(ClassNotFoundException err) {
                err.printStackTrace();
            } catch(SQLException err) {
                err.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Please Input a candidate name");
        }
    }

    public void removeCandidate(DefaultTableModel model, JTable table, String sqlTable){
        int selRow = table.getSelectedRow();
        int delRow = (int) table.getValueAt(selRow, 0);
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
            PreparedStatement prep = conn.prepareStatement("DELETE FROM " + sqlTable + " WHERE id = ?");
            prep.setInt(1, delRow);
            prep.executeUpdate();

            conn.close();
            model.setRowCount(0);
            this.LoadCandidates(model, sqlTable);
        } catch(ClassNotFoundException err) {
            err.printStackTrace();
        } catch(SQLException err) {
            err.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if(e.getSource() == addprescandi){
            this.addCandidate(pres, "presidentResult");
        }

        if(e.getSource() == remprescandi) {
            this.removeCandidate(pres, presidentResult, "presidentResult");
        }

        if(e.getSource() == addvicecandi){
            this.addCandidate(vice, "vicepresidentResult");
        }

        if(e.getSource() == remvicecandi) {
            this.removeCandidate(vice, vicepresResult, "vicepresidentResult");
        }

        if(e.getSource() == addseccandi){
            this.addCandidate(secr, "secretaryResult");
        }

        if(e.getSource() == remseccandi) {
            this.removeCandidate(secr, secretaryResult, "secretaryResult");
        }

        if(e.getSource() == addtrescandi){
            this.addCandidate(tres, "tresurerResult");
        }

        if(e.getSource() == remtrescandi) {
            this.removeCandidate(tres, tresurerResult, "tresurerResult");
        }

        if(e.getSource() == addaudicandi){
            this.addCandidate(audi, "auditorResult");
        }

        if(e.getSource() == remaudicandi) {
            this.removeCandidate(audi, auditorResult, "auditorResult");
        }

        if (e.getSource() == voterslist){
            new voterslist();
            this.dispose();
        }
        if(e.getSource() == logout){
            new loginPage();
            this.dispose();
        }
    }
}
