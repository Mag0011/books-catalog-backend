package com.company.books.backend.response;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 
 * @author Enrique Magdaleno
 * This class is used for adding metada related to a response HTTP method
 *
 */
public class ResponseRest {

	private ArrayList<HashMap<String,String>> metadata =  new ArrayList<>();
	
	public ArrayList<HashMap<String,String>> getMedata(){		
		return metadata;
	}
	
	public void setMetadata(String type, String code, String date ){
	 HashMap<String,String> map = new HashMap<>();
	 map.put("type", type);
	 map.put("code", code);
	 map.put("date", date);
	 metadata.add(map);
	}
	
}
