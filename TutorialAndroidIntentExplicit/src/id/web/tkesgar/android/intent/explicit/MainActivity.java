package id.web.tkesgar.android.intent.explicit;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	private static final int REQUEST_CODE = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    
    public void onClick(View view) {
        EditText text = (EditText) findViewById(R.id.inputforintent);
        String value = text.getText().toString();
        
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra("request", value);
        startActivityForResult(intent, REQUEST_CODE);
    }
    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK && requestCode == REQUEST_CODE) {
    		if (data.hasExtra("response")) {
    			String result = data.getExtras().getString("response");
    			if (result != null) {
    				Toast.makeText(this, result, Toast.LENGTH_LONG)
    				.show();
    			}
    		}
    	}
    }

}
