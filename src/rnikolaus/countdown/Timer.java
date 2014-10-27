package rnikolaus.countdown;

import android.content.Context;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.CountDownTimer;
import android.widget.TextView;

public class Timer {
	private static Timer t = new Timer();
	private CountDownTimer timer;
	private TextView tv1;
	private Context context;

	private Timer() {
	}

	public static Timer getInstance() {
		return t;
	}

	public void createAndStart(int seconds) {
		stop();
		timer = new CountDownTimer(seconds * 1000, 1000) {

			public void onTick(long millisUntilFinished) {

				tv1.setText("Seconds remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {

				tv1.setText("done!");
				try {
					Uri notification = RingtoneManager
							.getDefaultUri(RingtoneManager.TYPE_ALARM);
					Ringtone r = RingtoneManager.getRingtone(context,
							notification);
					r.play();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		timer.start();

	}

	public void stop() {
		if (timer != null) {
			timer.cancel();
			timer=null;
		}
	}

	public void setTextView(TextView tv) {
		this.tv1 = tv;
	}

	public void setContext(Context context) {
		this.context = context;
	}

}
