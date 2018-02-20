/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;


/**
 * Sets all of the different parts of the course and makes it so exceptions cannot
 * pass through
 * @author Andrew Regulski 
 *
 */
public class Course extends Activity implements Comparable<Course> { 
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 * Constructs a Course object with values for all fields.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as series of chars
	 * @param startTime
	 *            start time for Course
	 * @param endTime
	 *            end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
			int startTime, int endTime) {
		super(title, meetingDays, startTime, endTime);
		setName(name);
		setTitle(title);
		setSection(section);
		setCredits(credits);
		setInstructorId(instructorId);
		setMeetingDays(meetingDays);
		setActivityTime(startTime, endTime);
	}

	/**
	 * Creates a Course with the given name, title, section, credits, instructorId,
	 * and meetingDays for courses that are arranged.
	 * 
	 * @param name
	 *            name of Course
	 * @param title
	 *            title of Course
	 * @param section
	 *            section of Course
	 * @param credits
	 *            credit hours for Course
	 * @param instructorId
	 *            instructor's unity id
	 * @param meetingDays
	 *            meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}

	/**
	 * Returns the course name
	 * 
	 * @return the name
	 */
	public String getName(){
		return name;
	}

	/**
	 * Sets the Course's name. If the name is null, has a length less than 4 or
	 * greater than 6, an IllegalArgumentException is thrown.
	 * 
	 * @param name
	 *            the name to set
	 * @throws IllegalArgumentException
	 *             if name is null or length is less than 4 or greater than 6
	 */
	private void setName(String name) {
		if (name == null) {
			throw new IllegalArgumentException();
		}
		if (name.length() < 4 || name.length() > 6) {
			throw new IllegalArgumentException();
		}
		this.name = name;
	}

	/**
	 * Returns the section name If the section is null an IllegalArgumentException
	 * is thrown.
	 * 
	 * @return the section
	 */
	public String getSection() {
		return section;
	}

	/**
	 * Sets the section name. If the section is null or there are 3 digits in a row
	 * then an exception is thrown
	 * 
	 * @param section
	 *            the section to set
	 *            
	 * @throws IllegalArgumentException if the input is invalid
	 */
	public void setSection(String section) {
		if (section == null) {
			throw new IllegalArgumentException();
		}
		int digit = 0;
		for (int index = 0; index < section.length(); index++)
			if (Character.isDigit(section.charAt(index))) {
				digit++;
			}
		if (digit != 3) {
			throw new IllegalArgumentException();
		}
		this.section = section;
	}

	/**
	 * Returns the number of credits taken
	 * 
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}

	/**
	 * Sets the credits taken If the credits are greater than 5 or less than 1 an
	 * exception is thrown
	 * 
	 * @param credits
	 *            the credits to set
	 * @throws IllegalArgumentException if the input is invalid
	 */
	public void setCredits(int credits) {
		if (credits < 1 || credits > 5) {
			throw new IllegalArgumentException();
		}
		this.credits = credits;
	}

	/**
	 * Returns the instructors ID
	 * 
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}

	/**
	 * Sets the instructors ID. If the instructorId is null or the string is empty
	 * than an exception is thrown
	 * 
	 * @param instructorId
	 *            the instructorId to set
	 * @throws IllegalArgumentException if the input is invalid          
	 */
	public void setInstructorId(String instructorId) {
		if (instructorId == null || instructorId.length() < 1) {
			throw new IllegalArgumentException();
		}
		this.instructorId = instructorId;
	}
	
	/**
	 * Overrides the old setMeeting days to meet criteria for the course class.
	 * @param meetingDays the days the class meets
	 * @throws IllegalArgumentException if the input is invalid
	 */
	@Override
	public void setMeetingDays(String meetingDays) {
		if (meetingDays == null || meetingDays.length() < 1) {
			throw new IllegalArgumentException();
		}
		for (int i = 0; i < meetingDays.length(); i++) {
			if (meetingDays.charAt(i) != 'M' && meetingDays.charAt(i) != 'T' && meetingDays.charAt(i) != 'W'
					&& meetingDays.charAt(i) != 'H' && meetingDays.charAt(i) != 'F' && meetingDays.charAt(i) != 'A') {
				throw new IllegalArgumentException();
			}
			if (meetingDays.charAt(i) == 'A' && meetingDays.length() > 1) {
				throw new IllegalArgumentException();
			}
		}
		super.setMeetingDays(meetingDays);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}

	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override
	public String toString() {
		if (getMeetingDays().equals("A")) {
			return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
		}
		return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + ","
				+ getStartTime() + "," + getEndTime();
	} 

	@Override
	public String[] getShortDisplayArray() {
		String[] shortArray = new String[] {name, section, getTitle(), getMeetingString()};		
		return shortArray;
	}

	@Override
	public String[] getLongDisplayArray() {
		String[] longArray = new String[] {name, section, getTitle(), Integer.toString(getCredits()), instructorId, getMeetingString(), "" };
		return longArray;
	}

	@Override
	public boolean isDuplicate(Activity activity) {
		if(activity instanceof Course && (this.getName().equals(((Course) activity).getName()))) {
				return true;
			}
		return false; 
	}
	
	@Override
	public int compareTo(Course course) {
		if (name.compareTo(course.getName()) == 0 ) {
			if (section.compareTo(course.getSection()) == 0) {
				return toString().compareTo(course.toString()) ;
			}
			return section.compareTo(course.getSection());
		}
		return name.compareTo(course.getName());
	}
	
}
