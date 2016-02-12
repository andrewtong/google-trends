package at.GT.GoogleAuthentication;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import at.GT.HttpHelper.*;

public class GoogleAuthenticateLogin {
	//Credit is given to https://github.com/elibus/j-google-trends-api
	//for providing insight on how to solve this issue
	
//	self.url_CookieCheck = 'https://www.google.com/accounts/CheckCookie?chtml=LoginDoneHtml'
//			self.url_PrefCookie = 'http://www.google.com'
//
//
//			and:
//
//
//			self.opener.open(self.url_CookieCheck)
//			self.opener.open(self.url_PrefCookie)
//
//	http://stackoverflow.com/questions/11494693/httpsurlconnection-and-cookies
	
//    URL url = new URL("http://www.google.com:80");
//    URLConnection conn = url.openConnection();
//    // Set the cookie value to send
//    conn.setRequestProperty("Cookie", "name1=value1; name2=value2");
//    // Send the request to the server
//    conn.connect();

	
	private String _email = "";
	private String _password = "";
	private String _galx = "";
	
	
	private boolean _isLoggedIn = false;
	
	//private final String LOGIN_URL = "https://accounts.google.com/Login?hl=en";
	
	private String galx(){
	    String galx = null;
	    HttpGet get;
	    HttpClient client = HttpClients.createDefault();
	    try {

	      Pattern pattern = Pattern.compile("name=\"GALX\" type=\"hidden\"\n *value=\"(.+)\"");
	      get = new HttpGet("https://accounts.google.com/trends");
	      HttpResponse response = client.execute(get);
	      String html = HttpHelper.ReadEntity(response.getEntity());
	      get.releaseConnection();
	      Matcher matcher = pattern.matcher(html);
	      if (matcher.find()) {
	        galx = matcher.group(1);
	      }

	      if (galx == null) {
	        throw new Exception();
	      }
	      
	    } catch (Exception e){
	    	System.out.println("GALX is incorrect.");
	    }
	    
	    if (galx != null){
	    	_galx = galx;
	    }
	    
	    return galx;
	}
  

	    
	
	public List<NameValuePair> formLoginParams(){
		List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
		loginParams.add(new BasicNameValuePair("Email", _email));
		loginParams.add(new BasicNameValuePair("Passwd", _password));
		loginParams.add(new BasicNameValuePair("GALX", _galx));
		
		return loginParams;
	}
	
	public boolean isLoggedIn(){
		return _isLoggedIn;
	}
	
	
	public boolean login(){
		if(!_isLoggedIn){
			HttpClient client = HttpClients.createDefault();
			HttpPost httpPost = new HttpPost("https://accounts.google.com/ServiceLoginAuth");
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(formLoginParams()));
				HttpResponse response = client.execute(httpPost);
				String res = HttpHelper.ReadEntity(response.getEntity());
				httpPost.releaseConnection();
			} catch (UnsupportedOperationException | IOException e) {
				e.printStackTrace();
			}
			_isLoggedIn = true;
		}
		
		return true;	
	}
	
	public boolean authenticate(){
		_galx = galx();
		return login();
	}
	
	public static void main(String[] args) {
		
	}

}
