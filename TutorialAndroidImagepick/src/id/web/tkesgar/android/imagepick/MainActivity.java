package id.web.tkesgar.android.imagepick;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends Activity {
	
	private static final int REQUEST_CODE = 1;
	private Bitmap bitmap;
	private ImageView imageView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		imageView = (ImageView) findViewById(R.id.result);
	}

	public void pickImage(View View) {
		Intent intent = new Intent()
			.setType("image/*")
			.setAction(Intent.ACTION_GET_CONTENT)
			.addCategory(Intent.CATEGORY_OPENABLE);
		startActivityForResult(intent, REQUEST_CODE);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		InputStream stream = null;
		if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
			try {
				if (bitmap != null) {
					bitmap.recycle();
				}
				stream = getContentResolver().openInputStream(data.getData());
				bitmap = BitmapFactory.decodeStream(stream);
				imageView.setImageBitmap(bitmap);
			} catch (FileNotFoundException e) {
				Log.e(getClass().getSimpleName(), "Eep, file not found", e);
			} finally {
				if (stream != null) {
					try {
						stream.close();
					} catch (IOException e) {
						Log.e(getClass().getSimpleName(), "Eep, cannot close stream", e);
					}
				}
			}
		}
	}
	
} 
