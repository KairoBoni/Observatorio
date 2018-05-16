//This class realize connection with the database

package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DataBase {
	 /**use to connetct with database */
    private Connection con = null;
    /** Retrurn the instructions of SQL */
    private Statement stmt; 
    /** return the tables of command*/
    private ResultSet rs; 

    private String end;
    private String user;
    private String pw; 
    /** Esse metodo realiza a conexao com o banco, ele precisa de tres argumentos, o primeiro, recebe
     * o endereço do banco, o segundo recebe o nome do usuario e o terceiro recebe a senha do 
     * banco de dados. 
     * 
     * EXP: "jdbc:postgresql://localhost:5432/Observatorio", "sa", "sa"
     * 
     * **************************************************************************************************/
    public void Connect(String strEnd, String strUser, String strPw) {

        this.end = strEnd; 
        this.user = strUser;
        this.pw = strPw;
        try {
        	/** Driver of installed PostgreSQL */
            Class.forName("org.postgresql.Driver");
            /** Get the connection*/
            con = DriverManager.getConnection(this.end, this.user, this.pw);
            /** Create the o Statement */
            stmt = con.createStatement();
        /** test the connection*/
        } catch (ClassNotFoundException cnfe) {
            JOptionPane.showMessageDialog(null, "Error to connet with the driver");
            cnfe.printStackTrace();
         /** Return Error to used QuerySQL */   
        } catch (SQLException sqlex) {
            JOptionPane.showMessageDialog(null, "Error to use the Query");
            sqlex.printStackTrace();
        }
    }
    
    public ResultSet setQuery(String query){
    	try {
			rs = stmt.executeQuery(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
    	return rs;	
    }
    /** this method disconnect the database */
    public void Disconnet() {
        try {
            con.close();
        /** error if have any impossibilitis for disconnet database */    
        } catch (SQLException onConClose) {
            JOptionPane.showMessageDialog(null, "Erro ao desconectar o banco");
            onConClose.printStackTrace();
        }
    }
}
