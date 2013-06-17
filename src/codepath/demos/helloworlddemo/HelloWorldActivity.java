package codepath.demos.helloworlddemo;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class HelloWorldActivity extends Activity {
	TextView tv;
	Users info;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hello_world);
		info = (Users) getIntent().getSerializableExtra("users");
		tv = (TextView)findViewById(R.id.say_hi);
		tv.setText("Hi, " + info.names);

		// add PhoneStateListener so when phone call hangs up, it returns to main activity
		PhoneCallListener phoneListener = new PhoneCallListener();
		TelephonyManager telephonyManager = (TelephonyManager) this
			.getSystemService(Context.TELEPHONY_SERVICE);
		telephonyManager.listen(phoneListener, PhoneStateListener.LISTEN_CALL_STATE);
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
	
	/**
	 * Go to my activity
	 */
	public void prev(View v) {
		Intent goPrev = new Intent(HelloWorldActivity.this, MyActivity.class);
		startActivity(goPrev);
	}
	
	public void call(View v) {
		EditText edt = (EditText)findViewById(R.id.edtPhoneNumber);
		String number = edt.getText().toString();
		// use default phone number if user does not provide one
		if (number.equals("")) {
			number = "14089992345";
		}
		number = "tel:" + number;
		Intent call = new Intent(Intent.ACTION_CALL, Uri.parse(number));
		startActivity(call);
		Log.d("TEST", "Calling phone number: " + number);
	}
	
	public void browse(View v) {
		EditText edt = (EditText)findViewById(R.id.edtUrl);
		String address = edt.getText().toString();
		// use default URL if user does not provide one
		if (address.equals("")) {
			address = "http://www.google.com/";
		}
		Intent browse = new Intent(Intent.ACTION_VIEW, Uri.parse(address));
		
		//let user to choose which application to handle this intent
		Intent chooser = Intent.createChooser(browse, "Choose a browser");
		startActivity(chooser);
		Log.d("TEST", "Browseing url: " + address);
	}
	
	//monitor phone call activities
	private class PhoneCallListener extends PhoneStateListener {
 
		private boolean isPhoneCalling = false;
 
		String LOG_TAG = "LOGGING phone calls";
 
		@Override
		public void onCallStateChanged(int state, String incomingNumber) {
 
			if (TelephonyManager.CALL_STATE_RINGING == state) {
				// phone ringing
				Log.i(LOG_TAG, "RINGING, number: " + incomingNumber);
			}
 
			if (TelephonyManager.CALL_STATE_OFFHOOK == state) {
				// active
				Log.i(LOG_TAG, "OFFHOOK");
 
				isPhoneCalling = true;
			}
 
			if (TelephonyManager.CALL_STATE_IDLE == state) {
				// run when class initial and phone call ended, 
				// need detect flag from CALL_STATE_OFFHOOK
				Log.i(LOG_TAG, "IDLE");
 
				if (isPhoneCalling) {
 
					Log.i(LOG_TAG, "restart app");
 
					// restart application
					Intent i = getPackageManager()
							.getLaunchIntentForPackage(
								getPackageName()
							);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(i);
 
					isPhoneCalling = false;
				}
 
			}
		}
	}

}
