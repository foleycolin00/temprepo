package edu.ncsu.csc216.pack_scheduler.user;

/**
 * Creates a user that will be able to access the Registration System.
 * 
 * @author Colin Foley
 * @author Kim Pollard
 *
 */
public class User {

	private String firstName;
	private String lastName;
	private String id;
	private String email;
	private String hashPW;

	/**
	 * Constructor for User using first name, last name, id, email, and the password
	 * 
	 * @param firstName
	 *            the first name of the user
	 * @param lastName
	 *            the last name of the user
	 * @param id
	 *            the id of the user
	 * @param email
	 *            the email of the user
	 * @param password
	 *            the password of the user
	 */
	public User(String firstName, String lastName, String id, String email, String password) {
		setFirstName(firstName);
		setLastName(lastName);
		setId(id);
		setEmail(email);
		setPassword(password);
	}

	/**
	 * This method gets first name.
	 * 
	 * @return firstName First name
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * This method sets first name.
	 * 
	 * @param firstName
	 *            the firstName to set
	 * @throws IllegalArgumentException if the input is invalid           
	 */
	public void setFirstName(String firstName) {
		if (firstName == null || firstName.equals("")) {
			throw new IllegalArgumentException("Invalid first name");
		}
		this.firstName = firstName;
	}

	/**
	 * This method gets last name
	 * 
	 * @return lastName Last name
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * This method sets last name.
	 * 
	 * @param lastName
	 *            the lastName to set
	 *  @throws IllegalArgumentException if the input is invalid          
	 */
	public void setLastName(String lastName) {
		if (lastName == null || lastName.equals("")) {
			throw new IllegalArgumentException("Invalid last name");
		}
		this.lastName = lastName;
	}

	/**
	 * This method gets ID
	 * 
	 * @return id ID
	 */
	public String getId() {
		return id;
	}

	/**
	 * This method sets ID
	 * 
	 * @param id
	 *            the id to set
	 * @throws IllegalArgumentException if the input is invalid           
	 */
	protected void setId(String id) {
		if (id == null || id.equals("")) {
			throw new IllegalArgumentException("Invalid id");
		}
		this.id = id;
	}

	/**
	 * This method gets email
	 * 
	 * @return email Email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * This method sets the students email.
	 * 
	 * @param email
	 *            the email to set
	 * @throws IllegalArgumentException if the input is invalid           
	 */
	public void setEmail(String email) {
		if (email == null || email.equals("")) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (!(email.contains("@")) || !(email.contains("."))) {
			throw new IllegalArgumentException("Invalid email");
		}
		if (email.lastIndexOf(".") < email.indexOf("@")) {
			throw new IllegalArgumentException("Invalid email");
		}
		this.email = email;
	}

	/**
	 * This method gets hashPW
	 * 
	 * @return the hashPW
	 */
	public String getPassword() {
		return hashPW;
	}

	/**
	 * This method sets password.
	 * 
	 * @param hashPW
	 *            the hashPW to set
	 * @throws IllegalArgumentException if the input is invalid          
	 */
	public void setPassword(String hashPW) {
		if (hashPW == null || hashPW.equals("")) {
			throw new IllegalArgumentException("Invalid password");
		}
		this.hashPW = hashPW;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	/**
	 * This method overides the hashCode to allow for hashing for all personal
	 * information in this class.
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + email.hashCode();
		result = prime * result + firstName.hashCode();
		result = prime * result + hashPW.hashCode();
		result = prime * result + id.hashCode();
		result = prime * result + lastName.hashCode();
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	/**
	 * This method overrides the equals object to allow for equals objects for this
	 * specific class.
	 * 
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (!email.equals(other.email))
			return false;
		if (!firstName.equals(other.firstName))
			return false;
		if (!hashPW.equals(other.hashPW))
			return false;
		if (!id.equals(other.id))
			return false;
		if (!lastName.equals(other.lastName))
			return false;
		return true;
	}

}