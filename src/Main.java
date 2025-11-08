import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.*;
import java.io.File;

//Credits to the Owner: Kenjie Maraasin

public class Main{
    public static int limiter = 0;
    public static String name = "";
    public static String pickedpres = "";
    public static String pickedvicepres = "";
    public static String pickedsecr = "";
    public static String pickedtres = "";
    public static String pickedaudi = "";

    public void createSQL(){
        String sql1 = "CREATE TABLE IF NOT EXISTS registeredVoters (id INTEGER PRIMARY KEY AUTOINCREMENT, name, sufix, age INTEGER,  birthdate, birthplace, email, username, password, status, state)";
        String sql2 = "CREATE TABLE IF NOT EXISTS presidentResult (id INTEGER PRIMARY KEY AUTOINCREMENT, name, votes INTEGER)";
        String sql3 = "CREATE TABLE IF NOT EXISTS vicepresidentResult (id INTEGER PRIMARY KEY AUTOINCREMENT, name, votes INTEGER)";
        String sql4 = "CREATE TABLE IF NOT EXISTS secretaryResult (id INTEGER PRIMARY KEY AUTOINCREMENT, name, votes INTEGER)";
        String sql5 = "CREATE TABLE IF NOT EXISTS tresurerResult (id INTEGER PRIMARY KEY AUTOINCREMENT, name, votes INTEGER)";
        String sql6 = "CREATE TABLE IF NOT EXISTS auditorResult (id INTEGER PRIMARY KEY AUTOINCREMENT, name, votes INTEGER)";
        try{
            Class.forName("org.sqlite.JDBC");
            Connection conn = DriverManager.getConnection("jdbc:sqlite:VoteManagement.db");
            Statement stat = conn.createStatement();
            stat.addBatch(sql1);
            stat.addBatch(sql2);
            stat.addBatch(sql3);
            stat.addBatch(sql4);
            stat.addBatch(sql5);
            stat.addBatch(sql6);

            stat.executeBatch();
            conn.close();
        } catch(ClassNotFoundException e) {
            e.printStackTrace();
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public static void main (String[] args) {
        Main main = new Main();
        File file = new File("VoteManagement.db");

        if (!file.exists()){
            main.createSQL();
        }

        new loginPage();
    }
}
