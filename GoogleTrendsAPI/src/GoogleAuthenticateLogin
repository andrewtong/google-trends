package at.GT.GoogleAuthentication;

import java.net.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import at.GT.HttpHelper.*;



public class GoogleAuthenticateLogin {
	
	
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
	
	private String _email = "";
	private String _password = "";
	
	
	private boolean _isLoggedIn = false;
	
	private final String LOGIN_URL = "https://accounts.google.com/Login?hl=en";
	
	private String galx(){
	    String galx = null;
	    HttpGet get;
	    HttpClient client = HttpClients.createDefault();
	    try {

	      Pattern pattern = Pattern.compile("name=\"GALX\" type=\"hidden\"\n *value=\"(.+)\"");
	      get = new HttpGet("https://accounts.google.com/Login?hl=en");
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
	    
	    return galx;
	}
  

	    
	
	public List<NameValuePair> formLoginParams(){
		List<NameValuePair> loginParams = new ArrayList<NameValuePair>();
		loginParams.add(new BasicNameValuePair("Email", _email));
		loginParams.add(new BasicNameValuePair("Passwd", _password));
	}
	
	public boolean isLoggedIn(){
		return _isLoggedIn;
	}
	
	
	public boolean login(){
		if(!_isLoggedIn){
			
		}
		return true;
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		    URL url = new URL("http://www.google.com/");
		    URLConnection connection = url.openConnection();
		    List<String> cookies = connection.getHeaderFields().get("Set-Cookie");
		    Iterator<String> it = cookies.iterator();
		    while(it.hasNext()){
		    	System.out.println("a loop");
		    	System.out.println(it.next());
		    }

		} catch (Exception e) { }
	}

}
