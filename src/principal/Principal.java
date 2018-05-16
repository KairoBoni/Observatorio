package principal;

import java.sql.ResultSet;

import database.DataBase;
import robot.Robot;

public class Principal {
	public static void main(String[] args) throws Exception {
		Robot rob = new Robot();
		
		ResultSet rs;
		DataBase db = new DataBase();
		db.Connect("jdbc:postgresql://localhost:5432/Observatorio", "postgres", "12345");
		rs = db.setQuery("SELECT * FROM Sites;");
		
		while(rs.next()){
			if(rs.getInt(1) == 2){
				System.out.println("Site G1 COM PRIBLEMA");
			} else if(rs.getInt(1) == 4){
				System.out.println("Site G1 COM PRIBLEMA");
			} else {
				rob.setPage(rs.getString(3), rs.getInt(1));
				rob.finderNotice();
			}
		}
	}
}
