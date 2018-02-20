package edu.ncsu.csc216.pack_scheduler.user;
/**
 * This class defines Object type Student
 * 
 * @author Anna Mattapallil
 * @author Drew Regulski 
 * @author Kim Pollard
 */

public class Student extends User implements Comparable<Student> {
	private int maxCredits;
	/** Maximum credits that can be taken */
	public static final int MAX_CREDITS = 18;
	/**
	 * This method constructs object Student
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id student id
	 * @param email email for student
	 * @param hashPW students password
	 * @param maxCredits the credits that the student is taking
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW, int maxCredits) {
		super(firstName, lastName, id, email, hashPW);
		setMaxCredits(maxCredits);
		
	}
	/**
	 * This method constructs object Student
	 * @param firstName first name of student
	 * @param lastName last name of student
	 * @param id student id
	 * @param email email for student
	 * @param hashPW students password
	 */
	public Student(String firstName, String lastName, String id, String email, String hashPW) {
		this(firstName, lastName, id, email, hashPW, MAX_CREDITS);
		
	}
	
	/**
	 * This method gets max credits
	 * @return the maxCredits
	 */
	public int getMaxCredits() {
		return maxCredits;
	}
	
	/**
	 * This method sets max credits
	 * @param maxCredits the maxCredits to set
	 * @throws IllegalArgumentException if the input is invalid
	 */
	public void setMaxCredits(int maxCredits) {
		if (maxCredits < 3 || maxCredits > 18) {
			throw new IllegalArgumentException("Invalid max credits");
		}		
		this.maxCredits = maxCredits;
	}
	/**
	 * This method overrides default to String
	 * @return info String info
	 */
	@Override
	public String toString() {
		String info = super.getFirstName() + "," + super.getLastName() + "," + super.getId() + "," + super.getEmail() + "," + super.getPassword() + "," + maxCredits;
		return info;
	}
	/**
	 * This method compares two students by first and last name
	 * @returns if the student is greater than the other student  
	 */
	@Override
	public int compareTo(Student student) {
		if (super.getLastName().compareTo(student.getLastName()) == 0 ) {
			if (super.getFirstName().compareTo(student.getFirstName()) == 0) {
				return super.getId().compareTo(student.getId()) ;
			}
			return super.getFirstName().compareTo(student.getFirstName());
		}
		return super.getLastName().compareTo(student.getLastName());
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + maxCredits;
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
		Student other = (Student) obj;
		if (maxCredits != other.maxCredits)
			return false;
		return true;
	}
	
}
