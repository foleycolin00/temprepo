package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Superclass for Course and Event
 * 
 * @author Andrew Regulski 
 *
 */
public abstract class Activity implements Conflict {

	/**
	 * Checks to see if there is an activity
	 * 
	 * @param possibleConflictingActivity
	 *            the activity that could conflict
	 * @throws ConflictException
	 *             if there is a conflict
	 */
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * edu.ncsu.csc216.wolf_scheduler.course.Conflict#checkConflict(edu.ncsu.csc216.
	 * wolf_scheduler.course.Activity)
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		for (int i = 0; i < this.getMeetingDays().length(); i++) {
			for (int j = 0; j < possibleConflictingActivity.getMeetingDays().length(); j++) {
				if (this.getMeetingDays().charAt(i) == (possibleConflictingActivity.getMeetingDays().charAt(j))
						&& (testConflictingTime(this.getStartTime(), this.getEndTime(),
								possibleConflictingActivity.getStartTime(), possibleConflictingActivity.getEndTime())
								&& this.getMeetingDays().charAt(i) != 'A'
								&& possibleConflictingActivity.getMeetingDays().charAt(j) != 'A')) {
					throw new ConflictException();
				}
			}
		}
	}

	/**
	 * Helper method to determine if the times in two activities is conflicting on
	 * the same day
	 * 
	 * @param startTime1
	 *            start time of first activity
	 * @param endTime1
	 *            end time of first activity
	 * @param startTime2
	 *            start time of second activity
	 * @param endTime2
	 *            end time of second activity
	 * @return true or false depending on whether it is invalid or not
	 */
	public boolean testConflictingTime(int startTime1, int endTime1, int startTime2, int endTime2) {
		if (startTime2 >= startTime1 && startTime2 <= endTime1) {
			return true;
		} else if (startTime1 >= startTime2 && startTime1 <= endTime2) {
			return true;
		}
		return false;
	}

	private static final int UPPER_TIME = 2359;
	private static final int UPPER_HOUR = 59;
	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 * Short array of Activity info
	 * 
	 * @return shortArray of the activities schedule
	 */
	public abstract String[] getShortDisplayArray();

	/**
	 * Long array of Activity info.
	 * 
	 * @return longArray of the activities schedule
	 */
	public abstract String[] getLongDisplayArray();

	/**
	 * Method to check for duplicate activities
	 * 
	 * @param activity
	 *            the activity that the schedule is trying to add
	 * @return true or false depending on if its a duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);

	/**
	 * Makes an activity object with the following parameters
	 * 
	 * @param title
	 *            title of Activity
	 * @param meetingDays
	 *            meeting days for activity
	 * @param startTime
	 *            start time of activity
	 * @param endTime
	 *            end start of activity
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);

	}

	/**
	 * Returns the schedules title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the schedule title. If it is null or the title string is empty an
	 * exception is thrown
	 * 
	 * @param title
	 *            the title to set
	 * @throws IllegalArgumentException
	 *             if the title is null or too short
	 */
	public void setTitle(String title) {
		if (title == null) {
			throw new IllegalArgumentException();
		}
		if (title.length() < 1) {
			throw new IllegalArgumentException();
		}
		this.title = title;
	}

	/**
	 * Returns the meeting days
	 * 
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * Sets which days to meet. If the days are not equal to 'M','T','W','H','F', or
	 * 'A' then an exception is thrown. If the character is 'A' there cannot be
	 * another character or an exception is thrown
	 * 
	 * @param meetingDays
	 *            the meetingDays to set
	 */
	public void setMeetingDays(String meetingDays) {
		this.meetingDays = meetingDays;
	}

	/**
	 * gets the start time
	 * 
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * gets the end time
	 * 
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}

	/**
	 * Sets the classes starting time and ending time while also making sure that
	 * the times are valid and there is no time conflict
	 * 
	 * @param startTime
	 *            passes through the start time
	 * @param endTime
	 *            passes through the end time
	 * @throws IllegalArgumentException
	 *             if the start time or end time is invalid
	 **/
	public void setActivityTime(int startTime, int endTime) {
		if (meetingDays.equals("A")) {
			startTime = 0;
			endTime = 0;
		}
		if (startTime < 0 || startTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}
		if (endTime < 0 || endTime > UPPER_TIME) {
			throw new IllegalArgumentException();
		}

		if (startTime > 0) {
			String startString = Integer.toString(startTime);
			String startMinutes = startString.substring(startString.length() - 2, startString.length());
			int intMinutes = Integer.parseInt(startMinutes);
			if (intMinutes > UPPER_HOUR) {
				throw new IllegalArgumentException();
			}
		}

		if (endTime > 0) {
			String endString = Integer.toString(endTime);
			String endMinutes = endString.substring(endString.length() - 2, endString.length());
			int endIntMinutes = Integer.parseInt(endMinutes);
			if (endIntMinutes > UPPER_HOUR) {
				throw new IllegalArgumentException();
			}
		}

		if (endTime < startTime) {
			throw new IllegalArgumentException();
		}

		if (meetingDays.equals("A")) {
			startTime = 0;
			endTime = 0;
		}

		this.startTime = startTime;
		this.endTime = endTime;
	}

	/**
	 * Takes the military time and the days met and sets them all in string form.
	 * Additionally converts military time to standard time (am and pm). Lists the
	 * starting and ending times.
	 * 
	 * @return meetingString the string result showing the class times and days
	 */
	public String getMeetingString() {
		String meetingString = "";
		String startAMPM = "";
		String endAMPM = "";
		int startTime1 = startTime;
		int endTime1 = endTime;
		meetingString += meetingDays + " ";

		if (startTime1 < 1200) {
			startAMPM = "AM";
		} else if (startTime1 >= 1300) {
			startTime1 -= 1200;
			startAMPM = "PM";
		} else {
			startAMPM = "PM";
		}

		if (endTime1 < 1200) {
			endAMPM = "AM";
		} else if (endTime1 >= 1300) {
			endTime1 -= 1200;
			endAMPM = "PM";
		} else {
			endAMPM = "PM";
		}

		String startString = Integer.toString(startTime1);
		String endString = Integer.toString(endTime1);
		String startHours3 = "";
		String startMinutes3 = "";
		String startHours4 = "";
		String startMinutes4 = "";
		String endHours3 = "";
		String endMinutes3 = "";
		String endHours4 = "";
		String endMinutes4 = "";
		if (startString.length() == 3) {
			startHours3 = startString.substring(0, 1);
			startMinutes3 = startString.substring(1, 3);
		}
		if (startString.length() == 4) {
			startHours4 = startString.substring(0, 2);
			startMinutes4 = startString.substring(2, 4);
		}
		if (endString.length() == 3) {
			endHours3 = endString.substring(0, 1);
			endMinutes3 = endString.substring(1, 3);
		}
		if (endString.length() == 4) {
			endHours4 = endString.substring(0, 2);
			endMinutes4 = endString.substring(2, 4);
		}

		meetingString += startHours4 + startHours3 + ":";
		meetingString += startMinutes4 + startMinutes3 + startAMPM + "-";
		meetingString += endHours4 + endHours3 + ":";
		meetingString += endMinutes4 + endMinutes3 + endAMPM;
		if (meetingDays.equals("A")) {
			String arrangedString = "Arranged";
			return arrangedString;
		}
		return meetingString;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}

}