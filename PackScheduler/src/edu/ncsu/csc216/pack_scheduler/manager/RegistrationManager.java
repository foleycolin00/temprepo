package edu.ncsu.csc216.pack_scheduler.manager;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Controls the flow of students registering for classes. Also used the
 * registrar.properties file to store the sensitive data for use in this class.
 * 
 * @author Sarah Heckman
 * @author Colin Foley
 * @author Kim Pollard
 * @author Drew Regulski
 *
 */
public class RegistrationManager {
	/**
	 * An instance of the registration manager.
	 */
	private static RegistrationManager instance;
	/**
	 * Initializes the courseCatalog.
	 */
	private CourseCatalog courseCatalog;
	/**
	 * Initializes the student directory object.
	 */
	private StudentDirectory studentDirectory;
	/**
	 * Initializes the registrar user object.
	 */
	private User registrar;
	/**
	 * Initializes the currentUser user object.
	 */
	private User currentUser;
	/**
	 * Uses the hashing algorithm.
	 */
	private static final String HASH_ALGORITHM = "SHA-256";
	/**
	 * Initializes the properties file for this class.
	 */
	private static final String PROP_FILE = "registrar.properties";

	/**
	 * Creates the registration manager.
	 */
	private RegistrationManager() {
		createRegistrar();
	}

	/**
	 * Creates the registrar using information from the properties file.
	 */
	private void createRegistrar() {
		Properties prop = new Properties();

		try (InputStream input = new FileInputStream(PROP_FILE)) {
			prop.load(input);

			String hashPW = hashPW(prop.getProperty("pw"));

			registrar = new Registrar(prop.getProperty("first"), prop.getProperty("last"), prop.getProperty("id"),
					prop.getProperty("email"), hashPW);
		} catch (IOException e) {
			throw new IllegalArgumentException("Cannot create registrar.");
		}
	}

	/**
	 * Contains the hashing algorithm to protect the users passwords which are
	 * pulled from the properties file.
	 * 
	 * @param pw
	 *            the users password
	 * @return the hashed password in a string
	 */
	private String hashPW(String pw) {
		try {
			MessageDigest digest1 = MessageDigest.getInstance(HASH_ALGORITHM);
			digest1.update(pw.getBytes());
			return new String(digest1.digest());
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalArgumentException("Cannot hash password");
		}
	}

	/**
	 * Gets an instance of the Registration manager.
	 * 
	 * @return the instance if there is one
	 */
	public static RegistrationManager getInstance() {
		if (instance == null) {
			instance = new RegistrationManager();
		}
		return instance;
	}

	/**
	 * Gets the course catalog.
	 * 
	 * @return courseCatalog the catalog of all the courses.
	 */
	public CourseCatalog getCourseCatalog() {
		if (courseCatalog == null) {
			courseCatalog = new CourseCatalog();
		}
		return courseCatalog;
	}

	/**
	 * Gets the studentDirectory.
	 * 
	 * @return studentDirectory the directory of all students that can register for
	 *         classes.
	 */
	public StudentDirectory getStudentDirectory() {
		if (studentDirectory == null) {
			studentDirectory = new StudentDirectory();
		}
		return studentDirectory;
	}

	/**
	 * Uses the student ID and password to allow the student to login to the system.
	 * 
	 * @param id
	 *            the students ID.
	 * @param password
	 *            student's password.
	 * @return true if the users password is correct.
	 * @throws IllegalArgumentException
	 *             if the password or id is blank or null, or if the user doesn't
	 *             exist or the hash algorithm is wrong
	 */
	public boolean login(String id, String password) {
		if (id == null || id.equals("") || password == null || password.equals("")) {
			throw new IllegalArgumentException();
		}
		Student s;
		if (currentUser != null && !currentUser.equals(registrar)) {
			return false;
		} else if (registrar.getId().equals(id)) {
			MessageDigest digest;
			try {
				digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (registrar.getPassword().equals(localHashPW)) {
					currentUser = registrar;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		} else {
			if (studentDirectory.getStudentById(id) == null) {
				throw new IllegalArgumentException("User doesn't exist.");
			} else {
				s = studentDirectory.getStudentById(id);
			}
			try {
				MessageDigest digest = MessageDigest.getInstance(HASH_ALGORITHM);
				digest.update(password.getBytes());
				String localHashPW = new String(digest.digest());
				if (s.getPassword().equals(localHashPW)) {
					currentUser = s;
					return true;
				}
			} catch (NoSuchAlgorithmException e) {
				throw new IllegalArgumentException();
			}
		}
		return false;
	}

	/**
	 * Takes the student back to the registrar and they are no longer logged in.
	 */
	public void logout() {
		currentUser = null;
	}

	/**
	 * Gets the current users information.
	 * 
	 * @return currentUser the users information.
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Clears the cache of data.
	 */
	public void clearData() {
		if (courseCatalog != null) {
			courseCatalog.newCourseCatalog();
		}
		if (studentDirectory != null) {
			studentDirectory.newStudentDirectory();
		}
	}

	/**
	 * Inner class that contains the Registrar constructor.
	 * 
	 * @author Sarah Heckman
	 *
	 */
	private static class Registrar extends User {
		/**
		 * Create a registrar user with the user id and password in the
		 * registrar.properties file.
		 */
		public Registrar(String firstName, String lastName, String id, String email, String hashPW) {
			super(firstName, lastName, id, email, hashPW);
		}
	}
}
