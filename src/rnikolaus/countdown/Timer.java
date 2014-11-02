package rnikolaus.countdown;

import android.media.AudioAttributes;
import android.media.Ringtone;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

public class Timer {
	private static Timer timer = new Timer();
	private CountDownTimer countdownTimer;
	private TextView tv1;

	private Ringtone ringtone;
	private Vibrator vibrator;

	private Timer() {
	}

	public static Timer getInstance() {
		return timer;
	}

	public void createAndStart(int seconds) {
		stop();
		countdownTimer = new CountDownTimer(seconds * 1000, 1000) {
			public void onTick(long millisUntilFinished) {
				tv1.setText("Seconds remaining: " + millisUntilFinished / 1000);
			}

			public void onFinish() {
				tv1.setText("done!");
				try {
					if (vibrator != null) {
						vibrator.vibrate(1000);
					}
					if (ringtone != null){
						ringtone.play();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		countdownTimer.start();

	}

	public void stop() {
		if (countdownTimer != null) {
			countdownTimer.cancel();
			countdownTimer = null;
		}
		if (ringtone != null && ringtone.isPlaying()) {
			ringtone.stop();
		}
		if (vibrator != null) {
			vibrator.cancel();
		}
	}

	public void setTextView(TextView tv) {
		this.tv1 = tv;
	}

	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}

	public void setVibrator(Vibrator v) {
		this.vibrator = v;
	}

}
