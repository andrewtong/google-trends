package at.GT.Client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import at.GT.HttpHelper.*;
import at.GT.RequestCreater.*;

public class GoogleTrendsClient {
	
	public static String CreateRequest(String query){
		if (query.length() == 0){
			return null;
		} else {
			GoogleTrendsRequest newrequest = new GoogleTrendsRequest(query);
			return newrequest.constructQuery();
		}	
	}
	
	public static void ExecutePost(String url) throws ClientProtocolException, IOException{
		HttpClient httpclient = HttpClients.createDefault();
		HttpGet post = new HttpGet(url);
		HttpResponse response = httpclient.execute(post);
		HttpEntity entity = response.getEntity();
		
		HttpHelper.ReadEntity(entity);
		
	}
	

	public static void main(String[] args) {
		String request = CreateRequest("java");
		System.out.println(request);
		try {
			ExecutePost(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
