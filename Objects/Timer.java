package pvs.objects;
import pvs.University;

public class Timer implements Runnable {
	private int time;
	private final int interval;
	private boolean isActive;
	private University university;

	public Timer(int time, University university) {
		this.interval = 1;
		this.isActive = true;
		this.time = time;
		this.university = university;
	}

	// Threading
	@Override
	public void run() {
		try {
			Thread.sleep(7500);
		} catch(Exception e) {}

		switch(this.purpose) {
			case COUNTDOWN:
				while(this.time != 0) {
					this.university.log();
					this.time -= this.interval;

					try {
						Thread.sleep(1000);
					} catch(Exception e) {}
				}

				this.isActive = false;
				break;
			case TIMER:
				while(this.isActive) {
					this.university.log();
					this.time += this.interval;

					try {
						Thread.sleep(1000);
					} catch(Exception e) {}
				}

				break;
		}
	}

	// Setters
	public void stop() {
		this.isActive = false;
	}

	// Getters
	public boolean isActive() {
		return this.isActive;
	}

	public int getTime() {
		return this.time;
	}

	public int getPurpose() {
		return this.purpose;
	}

	public boolean isHellWeek() {
		return this.time < 25 ? true : false;
	}
}