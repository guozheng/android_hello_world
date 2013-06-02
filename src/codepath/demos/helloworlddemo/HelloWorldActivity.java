package codepath.demos.helloworlddemo;

import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Menu;

public class HelloWorldActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_world);
		Log.d("TEST", "Activity created");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_hello_world, menu);
		return true;
	}
	
	@Override
	public void onPause() {
		super.onPause();
		Log.d("TEST", "Activity paused, before leaving activity");
	}
	
	@Override
	public void onResume() {
		super.onResume();
		Log.d("TEST", "Activity resumed, before returning to activity");
	}

}
