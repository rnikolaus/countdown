package rnikolaus.countdown;

import android.media.Ringtone;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.widget.TextView;

/**
 * This singleton holds the CountDownTimer reference
 * @author rapnik
 *
 */
public class Timer {
	private static Timer timer = new Timer();
	private CountDownTimer countdownTimer;
	private TextView tv1;

	private Ringtone ringtone;
	private Vibrator vibrator;

	/**
	 * Singleton, no public constructor
	 */
	private Timer() {
	}

	/**
	 * Get the Timer singleton
	 * 
	 * @return
	 */
	public static Timer getInstance() {
		return timer;
	}

	/**
	 * Create a timer and start it
	 * 
	 * @param seconds
	 */
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
					if (ringtone != null) {
						ringtone.play();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		};
		countdownTimer.start();

	}

	/**
	 * Stop the timer and/or ringtone
	 */
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

	/**
	 * Set the Textview to write to
	 * 
	 * @param tv
	 */
	public void setTextView(TextView tv) {
		this.tv1 = tv;
	}

	/**
	 * Set the ringtone to be played
	 * 
	 * @param ringtone
	 */
	public void setRingtone(Ringtone ringtone) {
		this.ringtone = ringtone;
	}

	/**
	 * Set the vibrator to use
	 * 
	 * @param vibrator
	 */
	public void setVibrator(Vibrator vibrator) {
		this.vibrator = vibrator;
	}

}
