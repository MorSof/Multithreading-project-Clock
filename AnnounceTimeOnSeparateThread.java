import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;

import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.Timer;

public class AnnounceTimeOnSeparateThread implements Runnable {
	private int hour, minute;
	private final int HOURS_NUM = 12;
	private final int MINUTES_NUM = 60;

	protected AudioClip[] hourAudio = new AudioClip[HOURS_NUM];
	protected AudioClip[] minuteAudio = new AudioClip[MINUTES_NUM];
	// Create audio clips for pronouncing am and pm
	protected AudioClip amAudio = Applet.newAudioClip(this.getClass().getResource("/audio/am.au"));
	protected AudioClip pmAudio = Applet.newAudioClip(this.getClass().getResource("/audio/pm.au"));

	/** Get Audio clips */

//	public AnnounceTimeOnSeparateThread(int hour, int minute) {
//		this.hour = hour;
//		this.minute = minute;
//		init();
//	}

	public AnnounceTimeOnSeparateThread(int hours, int minutes) {
		this.hour = hours;
		this.minute = minutes;
		init();
	}

	public void run() {

		try { // Announce hour
			hourAudio[hour % HOURS_NUM].play();
			// Time delay to allow hourAudio play to finish
			Thread.sleep(1500);
			// Announce minute
			minuteAudio[minute].play();			
			// Time delay to allow minuteAudio play to finish
			Thread.sleep(1500);
		} catch (InterruptedException ex) {
		}
		// Announce am or pm
		if (hour < HOURS_NUM) {
			amAudio.play();
		}
		else
			pmAudio.play();
	}

	/** Initialize the applet */
	public void init() { // Create audio clips for pronouncing hours
		for (int i = 0; i < HOURS_NUM; i++)
			hourAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/hour" + i + ".au"));
		// Create audio clips for pronouncing minutes
		for (int i = 0; i < MINUTES_NUM; i++)
			minuteAudio[i] = Applet.newAudioClip(this.getClass().getResource("/audio/minute" + i + ".au"));
		// Add clock and time label to the content pane of the applet
		// add(clock, BorderLayout.CENTER);
		// add(jlblDigitTime, BorderLayout.SOUTH);
	}

	public void setHour(int hour) {
		this.hour = hour;
	}

	public void setMinute(int minute) {
		this.minute = minute;
	}
	
	

}
