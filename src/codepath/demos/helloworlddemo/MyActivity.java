package codepath.demos.helloworlddemo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MyActivity extends Activity {
	List<String> names;
	ArrayAdapter<String> adapter;
	ListView lv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);
		names = new ArrayList<String>();
		names.add("Alex");
		names.add("Claire");
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1,
				names);
		lv = (ListView)findViewById(R.id.name_list);
		lv.setAdapter(adapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}
	
	public void add(View v) {
		EditText editTextName = (EditText)findViewById(R.id.editTextName);
		adapter.add(editTextName.getText().toString());
		editTextName.setText("");
		adapter.notifyDataSetChanged();
		Toast.makeText(getBaseContext(), "Success!", Toast.LENGTH_SHORT).show();
	}
	
	public void reset(View v) {
		adapter.clear();
		adapter.notifyDataSetChanged();
		Toast.makeText(getBaseContext(), "Success!", Toast.LENGTH_SHORT).show();
	}

}
