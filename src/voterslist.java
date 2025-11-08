import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class voterslist extends JFrame implements ActionListener{
    Main main = new Main();
    JButton close;
    JButton clear;
    JButton approve;
    JTable table;
    DefaultTableModel model;

    public void LoadData(DefaultTableModel model){
        try {
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("SELECT * FROM registeredVoters;");

            while(rs.next()){
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("name"), rs.getString("sufix"), rs.getInt("age"), rs.getString("birthdate"), rs.getString("birthplace"), rs.getString("email"), rs.getString("username"), rs.getString("password"), rs.getString("status"), rs.getString("state")});
            }
            conn.close();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e) {
            e.printStackTrace();
        }

    }

    voterslist(){
        DefaultTableCellRenderer cr = new DefaultTableCellRenderer();
        cr.setHorizontalAlignment(JLabel.CENTER);

        String[] cols = {"id", "name", "sufix", "age", "birthdate", "birthplace", "email", "username", "password", "status","state"};
        JPanel table_p = new JPanel(new BorderLayout());
        table_p.setPreferredSize(new Dimension(1500, 400));
        JScrollPane tsp = new JScrollPane(table_p);
        tsp.setViewportView(table_p);
        JPanel button = new JPanel(new FlowLayout());
        button.setPreferredSize(new Dimension(500, 50));
        model = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int rows, int columns){
                return false;
            }
        };
        model.setColumnIdentifiers(cols);
        table = new JTable(model);
        JScrollPane sc = new JScrollPane(table);
        sc.setViewportView(table);
        table_p.add(sc, BorderLayout.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(cr);
        table.getColumnModel().getColumn(2).setCellRenderer(cr);
        table.getColumnModel().getColumn(3).setCellRenderer(cr);
        table.getColumnModel().getColumn(4).setCellRenderer(cr);

        close = new JButton("Back");
        close.addActionListener(this);
        button.add(close);

        approve = new JButton("Approve");
        approve.addActionListener(this);
        button.add(approve);

        clear = new JButton("clear");
        clear.addActionListener(this);
        button.add(clear);

        LoadData(model);
        this.add(tsp, BorderLayout.CENTER); 
        this.add(button, BorderLayout.SOUTH); 
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(500, 600);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == close){
            new Admin();
            this.dispose();
        }

        if (e.getSource() == approve){
            int row = table.getSelectedRow();
            int updateRow = (int) table.getValueAt(row, 0);
            try{
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                PreparedStatement prep = conn.prepareStatement("UPDATE registeredVoters SET status = ? WHERE id = ?");

                prep.setString(1, "Approved");
                prep.setInt(2, updateRow);
                prep.executeUpdate();
                conn.close();
                model.setRowCount(0);
                LoadData(model);
            }catch (ClassNotFoundException err) {
                err.printStackTrace();
            }catch (SQLException err) {
                err.printStackTrace();
            }
        }

        if (e.getSource() == clear){
            try{
                Class.forName("org.sqlite.JDBC");
                Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
                Statement stat = conn.createStatement();
                stat.executeUpdate("DELETE FROM registeredVoters; VACUUM;");

                conn.close();
                model.setRowCount(0);
                LoadData(model);
            }catch(ClassNotFoundException err) {
                err.printStackTrace();
            }catch(SQLException err) {
                err.printStackTrace();
            }
        }
    }
}
