
//this class will pre process the title of notice, i.e. will remove every things
//that not in interest

package preProcess;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordProcess {
	private String str = new String();
	//Create a list of prepositions for remove to phrases
	private static List<String> prepositions = new ArrayList<String>(Arrays.asList(
			"A", "DE", "EM", "POR", "O", "AO", "DO", "NO", "PELO", "DA", "NA, PELA", "OS",
			"AOS", "DOS", "NOS", "PELOS", "AS", "DAS", "NAS", "PELAS", "UM", "UMA", "UNS",
			"UMAS", "DUM", "DUMAS", "DUNS", "DUMAS", "NUM", "NUMA", "NUNS", "NUMAS","EU", "TU",
			"ELE", "NOS", "VOS", "ELES", "E", "PRA"));
	
	//set method
	public void setString(String str){
		this.str = str;
	}
	
	//method will remove every prepositions and  subjects of title
	public List<String> process(){
		//flag mark if the word relationed is a prepsiton
		int isPreposition = 0;
		
		//list of result
		List<String> result = new ArrayList<String>();	
		
		str = str.toUpperCase();
		
		//here we replace every characters that dispanceble for our analysis
		str = str.replaceAll(",", "");
		str = str.replaceAll(";", "");
		str = str.replaceAll("\\.", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("'", "");
		str = str.replaceAll(":", "");
		str = str.replaceAll("!", "");
		str = str.replaceAll("\\?", "");
		str = str.replaceAll("\\[", "");
		str = str.replaceAll("\\]", "");
		str = str.replaceAll("\\(", "");
		str = str.replaceAll("\\)", "");
		str = str.replaceAll("\\{", "");
		str = str.replaceAll("\\}", "");
		str = str.replaceAll("Ç", "C");
		str = str.replaceAll("Á", "A");
		str = str.replaceAll("À", "A");
		str = str.replaceAll("É", "E");
		str = str.replaceAll("Í", "I");
		str = str.replaceAll("Ã", "A");
		str = str.replaceAll("Õ", "O");
		str = str.replaceAll("Ó", "O");
		
		String[] listStrings = str.split(" ");
	

		for (String word :listStrings){
			for (String pre :prepositions){
				if(word.compareTo(pre) == 0){
					isPreposition = 1;
					break;
				}
			}
			if(isPreposition == 0){
				result.add(word);
			} else {
				isPreposition = 0;
			}
		}
		return result;
	}
}
