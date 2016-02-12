package at.GT.HttpHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;

public class HttpHelper {
	
	public static String ReadEntity(HttpEntity entity) throws UnsupportedOperationException, IOException{
	 	String string;
		StringBuilder output = new StringBuilder();
		if (entity.getContent() != null){
			BufferedReader reader = new BufferedReader(new InputStreamReader(entity.getContent()));
			
	        while ((string = reader.readLine()) != null) {
	            output.append(string);
	        }
	        reader.close();
		}
		return output.toString();
	}

}
