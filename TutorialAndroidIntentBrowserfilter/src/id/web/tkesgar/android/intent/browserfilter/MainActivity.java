package id.web.tkesgar.android.intent.browserfilter;

import java.net.MalformedURLException;
import java.net.URL;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.webkit.WebView;

public class MainActivity extends Activity {
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);

	    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
	        .permitAll().build();
	    StrictMode.setThreadPolicy(policy);

	    setContentView(R.layout.activity_main);
	    
	    Intent intent = getIntent();
	    WebView web = (WebView) findViewById(R.id.webView1);
	    
	    String action = intent.getAction();
	    if (!action.equals(Intent.ACTION_VIEW)) {
	    	throw new RuntimeException("This. Is. Impossible!");
	    }
	    
	    Uri data = intent.getData();
	    URL url;
		try {
			url = new URL(data.getScheme(), data.getHost(), data.getPath());
		    web.loadUrl(url.toString());
		} catch (MalformedURLException e) {
			Log.e("Browser", "Bad URI", e);
		}
	  }
}
