package rnikolaus.countdown;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initComponents();
	}


	private void initComponents() {
		final TextView tv1 = (TextView) findViewById(R.id.textView1);
		Timer.getInstance().setTextView(tv1);
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		// Create an ArrayAdapter using the string array and a default spinner layout
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
		        R.array.time_units, android.R.layout.simple_spinner_item);
		// Specify the layout to use when the list of choices appears
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		// Apply the adapter to the spinner
		spinner.setAdapter(adapter);
		spinner.setSelection(0);
	}
	

	public void clickStart(View view) {
		setupAlarm();
		final EditText edText1 = (EditText) findViewById(R.id.editText1);
		String string = edText1.getText().toString();
		
		try {
			double i = Double.valueOf(string.replaceAll(", ", ""));
			Timer.getInstance().createAndStart((int) (i * getMultiplier()));

		} catch (NumberFormatException ex) {
			final TextView tv1 = (TextView) findViewById(R.id.textView1);
			tv1.setText("Invalid number format");
		}
	}


	private void setupAlarm() {
		Vibrator v = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);
		Timer.getInstance().setVibrator(v);
		Uri notification = RingtoneManager
				.getDefaultUri(RingtoneManager.TYPE_ALARM);
		
		Ringtone ringtone = RingtoneManager.getRingtone(
				getApplicationContext(), notification);
		Timer.getInstance().setRingtone(ringtone);
	}


	private int getMultiplier() {
		Spinner spinner = (Spinner) findViewById(R.id.spinner1);
		int multiplier=1;
		if (spinner.getSelectedItemPosition()==0)multiplier=60;
		return multiplier;
	}

	public void clickStop(View view) {
		Timer.getInstance().stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
