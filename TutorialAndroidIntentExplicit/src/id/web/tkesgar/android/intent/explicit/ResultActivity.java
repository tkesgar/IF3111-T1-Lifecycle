package id.web.tkesgar.android.intent.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class ResultActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
	    Bundle extras = getIntent().getExtras();
	    String request = extras.getString("request");
	    
	    TextView view = (TextView) findViewById(R.id.displayintentextra);
	    view.setText(request);
	}

	@Override
	public void finish() {
		Intent intent = new Intent();
		
		EditText editText= (EditText) findViewById(R.id.returnValue);
		String string = editText.getText().toString();
		intent.putExtra("response", string);
		
		setResult(RESULT_OK, intent);
		
		super.finish();
	}
}
