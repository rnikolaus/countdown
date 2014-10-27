package rnikolaus.countdown;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {
	

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
final TextView tv1 =(TextView)findViewById(R.id.textView1);
    	
    	Timer.getInstance().setTextView(tv1);
    	Timer.getInstance().setContext(getApplicationContext());
        
    }
    
   
    
    public void clickStart(View view){
    	final EditText edText1 = (EditText)findViewById(R.id.editText1);
    	String string = edText1.getText().toString();
		try{
		double i = Double.valueOf(string.replaceAll(", ",""));
		Timer.getInstance().createAndStart((int)(i*60));
		
		}catch (NumberFormatException ex){
			final TextView tv1 =(TextView)findViewById(R.id.textView1);
			tv1.setText("Invalid number format");
		}
    }
    public void clickStop(View view){
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
