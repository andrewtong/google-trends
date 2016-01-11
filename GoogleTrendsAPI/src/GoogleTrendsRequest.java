package at.GT.RequestCreater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableMap;

public class GoogleTrendsRequest {
	
	//http://www.google.com/trends/fetchComponent?q=christmas&cid=TIMESERIES_GRAPH_0&export=3&date=now%201-d (past day)
	//http://www.google.com/trends/fetchComponent?q=christmas&cid=TIMESERIES_GRAPH_0&export=3&date=now%207-d (past 7 days)
	//query 'q' : search term
	//cid : defaulted to TIMESERIES_GRAPH_0
	//export (display output) : defaulted to 3
	//date 'date' : date of search
	
//	//Currently alterable
//	private String q = "java";
//	private String date = "now%201-d";
//	
//	//Alterable and optional
//	private String geo = "US";
//	
//	//Currently unalterable
//	private String cid = "TIMESERIES_GRAPH_0";
//	private String export = "3";
	private static String baseQuery = "http://www.google.com/trends/fetchComponent";
	private static String requestQuery;
	
	private HashMap<String, String> queryItems = new HashMap<String, String>();
	
	public GoogleTrendsRequest(String query){
		queryItems.put("q", query);
		queryItems.put("date", "now%201-d");
		queryItems.put("cid", "TIMESERIES_GRAPH_0");
		queryItems.put("export", "3");
		queryItems.put("geo", "US");
	}
	
	public void setQuery(String newquery){
		queryItems.put("q", newquery);
	}
	
	public String getQuery(){
		return queryItems.get("q");
	}
	
	public void setDate(String newdate){
		if(isValidDate(newdate)){
			queryItems.put("date", newdate);
		} else {
			System.out.println("New date is invalid, current settings were not changed.");
		}
	}
	
	public String getDate(){
		return queryItems.get("date");
	}
	
	public void setGeo(String newloc){
		if (isValidLoc(newloc)){
			queryItems.put("geo", newloc);
		} else {
			System.out.println("New location is invalid, current settings were not changed.");
		}
	}
	
	private final boolean isValidDate(String newdate){
		return true;
	}
	
	private final boolean isValidLoc(String newloc){
		return true;
	}
	
	public final String constructQuery(){
		String searchDivisor = "?";
		requestQuery =  baseQuery;
		RequestFlags requestCreator = new RequestFlags();
		List<String> queryTerms = requestCreator.retrieveSearchConditions();
		Iterator<String> it = queryTerms.iterator();
		if (!queryTerms.isEmpty()){
			requestQuery = requestQuery.concat(searchDivisor);
		}
		
		int remainder = 1;
		while (remainder == 1){
			String term = it.next();
			requestQuery = requestQuery.concat(term);
			requestQuery = requestQuery.concat("=");
			requestQuery = requestQuery.concat(queryItems.get(term));
			if(it.hasNext()){
				requestQuery = requestQuery.concat("&");
			} else {
				remainder = 0;
			}
		}
		
		return requestQuery;
	}
	
	public final class RequestFlags {
		private int USE_GEO = 0;
		private final int USE_QUERY = 1;
		private final int USE_DATE = 1;
		private final int USE_CID = 1;
		private final int USE_EXPORT = 1;
		
		private ImmutableMap<String, Integer> querySettings;
		
		private void set_GEO_FLAG(int n){
			if (n == 1 || n == 0 && n != USE_GEO){
				USE_GEO = n;
			}
		}
		
		public RequestFlags(){
			querySettings = ImmutableMap.<String, Integer>builder()
					.put("q", USE_QUERY)
					.put("date", USE_DATE)
					.put("cid", USE_CID)
					.put("export", USE_EXPORT)
					.put("geo", USE_GEO)
					.build();
		}
		
		public List<String> retrieveSearchConditions(){
			List<String> SearchConditions = new ArrayList<String>();
			for (ImmutableMap.Entry<String, Integer> entry : querySettings.entrySet()){
				if(entry.getValue() == 1){
					SearchConditions.add(entry.getKey());	
				}
			}
			return SearchConditions;
		}	
	}
}
