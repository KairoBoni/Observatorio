/**this class is a robot
 * here we finder every notices from site
 * and check with the table Words in database*/

package robot;

import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.HtmlAnchor;
import com.gargoylesoftware.htmlunit.html.HtmlPage;

import database.DataBase;
import preProcess.WordProcess;

public class Robot {
	//create a new web user
	private WebClient webClient = new WebClient(BrowserVersion.BEST_SUPPORTED);
	//page who are searched
	private HtmlPage page;
	//the id of page (relationed a database)
	private int idPage;
	
	//this method charge page searched
	public void setPage(String site, int idSite) throws FailingHttpStatusCodeException, MalformedURLException, IOException{
		//this line is just for close every warningus relationed for the framework
		Logger.getLogger("com.gargoylesoftware.htmlunit").setLevel(Level.OFF);
		webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
		
		//log page
		this.page = webClient.getPage(site);
		this.idPage = idSite;
	}
	
	//this method find the notice
	public void finderNotice() throws SQLException{
		
		//open the database
		ResultSet rs;
		DataBase db = new DataBase();
		db.Connect("jdbc:postgresql://localhost:5432/Observatorio", "postgres", "12345");
		
		//get every links ecistent in page
		List<HtmlAnchor> anchors = page.getAnchors();
		
		//call the method for the process title of notices
		WordProcess wp = new WordProcess();
		List<String> titleNotice;
		
		//pass in every links of page
		int i;
		for (i = 0; i < anchors.size(); i++){
			//process this title
			wp.setString(anchors.get(i).getAttribute("title"));
			titleNotice = wp.process();
			
			//pass in every words of the title notice
			for(String wordNotice :titleNotice){
				rs = db.setQuery("SELECT * FROM Words;");
				//pass in every words notices in database
				while(rs.next()){
					//if is egual we save this notice in database and stop the process
					if(wordNotice.compareTo(rs.getString(2)) == 0){
						db.setQuery("INSERT INTO Notices VALUES (DEFAULT,"+rs.getString(1)+", '"+idPage+"', '"+anchors.get(i).getAttribute("title")+"','"
								+anchors.get(i).getAttribute("href")+"',  CURRENT_TIMESTAMP);");
					break;
					}
				}
			}
		}
		
		System.out.println("Pesquisa Feita com Sucesso");
		
		
	}
}
