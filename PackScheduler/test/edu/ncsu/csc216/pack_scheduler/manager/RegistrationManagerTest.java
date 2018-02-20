package edu.ncsu.csc216.pack_scheduler.manager;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.pack_scheduler.catalog.CourseCatalog;
import edu.ncsu.csc216.pack_scheduler.directory.StudentDirectory;

/**
 * Tests the manager class.
 * 
 * @author Kim Pollard
 * @author Drew Regulski
 *
 */
public class RegistrationManagerTest {

	private RegistrationManager manager;
	private String rId;
	private String rPass;

	/**
	 * Sets up the CourseManager and clears the data.
	 * 
	 * @throws Exception
	 *             if error
	 */
	@Before
	public void setUp() throws Exception {
		manager = RegistrationManager.getInstance();
		manager.clearData();

		InputStream input = new FileInputStream("registrar.properties");
		Properties prop = new Properties();
		prop.load(input);
		rId = prop.getProperty("id");
		rPass = prop.getProperty("pw");
	}

	/**
	 * Tests getting the course catalog with new items in the list.
	 */
	@Test
	public void testGetCourseCatalog() {
		CourseCatalog cc = RegistrationManager.getInstance().getCourseCatalog();
		cc = RegistrationManager.getInstance().getCourseCatalog();
		assertEquals(0, cc.getCourseCatalog().length);
		assertTrue(
				cc.addCourseToCatalog("CSC116", "Intro to Programming - Java", "001", 3, "jdyoung2", "MW", 910, 1100));
		assertEquals(1, cc.getCourseCatalog().length);

	}

	/**
	 * Tests getting the student directory.
	 */
	@Test
	public void testGetStudentDirectory() {
		StudentDirectory sd = new StudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
		assertTrue(sd.addStudent("Kim", "Pollard", "krpollar", "krpollar@ncsu.edu", "pw1", "pw1", 14));
		assertEquals(1, sd.getStudentDirectory().length);
		assertTrue(sd.addStudent("Sarah", "Plott", "sgplott", "sgplott@ncsu.edu", "pw2", "pw2", 14));
		assertEquals(2, sd.getStudentDirectory().length);

	}

	/**
	 * Tests the login feature.
	 */
	@Test
	public void testLogin() {
		try {
			RegistrationManager.getInstance().login("", "u");
			fail();
		} catch (IllegalArgumentException e) {
			// passes
		}
		try {
			RegistrationManager.getInstance().login(null, "u");
			fail();
		} catch (IllegalArgumentException e) {
			// passes
		}
		try {
			RegistrationManager.getInstance().login("u", "");
			fail();
		} catch (IllegalArgumentException e) {
			// passes
		}
		try {
			RegistrationManager.getInstance().login("u", null);
			fail();
		} catch (IllegalArgumentException e) {
			// passes
		}
		try {
			RegistrationManager.getInstance().getStudentDirectory();
			RegistrationManager.getInstance().login("u", "j");
			fail();
		} catch (IllegalArgumentException e) {
			// passes
		}

		RegistrationManager.getInstance().getStudentDirectory().addStudent("Temp", "Stu", "tstu44", "tstu44@ncsu.edu",
				"badpass", "badpass", 16);
		RegistrationManager.getInstance().login("tstu44", "badpass");
		assertEquals(RegistrationManager.getInstance().getCurrentUser().getFirstName(), "Temp");
		RegistrationManager.getInstance().logout();
		assertTrue(RegistrationManager.getInstance().login(rId, rPass));
		assertFalse(RegistrationManager.getInstance().login("tstu44", "badpass2"));
		assertFalse(RegistrationManager.getInstance().login(RegistrationManager.getInstance().getCurrentUser().getId(),
				"badpass2"));
	}

	/**
	 * Tests the logout feature
	 */
	@Test
	public void testLogout() {
		RegistrationManager.getInstance().getStudentDirectory().addStudent("Temp", "Stu", "tstu44", "tstu44@ncsu.edu",
				"badpass", "badpass", 16);
		RegistrationManager.getInstance().login("tstu44", "badpass");
		assertEquals(RegistrationManager.getInstance().getCurrentUser().getFirstName(), "Temp");
		RegistrationManager.getInstance().logout();
		assertNull(RegistrationManager.getInstance().getCurrentUser());
	}

	/**
	 * Tests getting the current user.
	 */
	@Test
	public void testGetCurrentUser() {
		RegistrationManager.getInstance().logout();
		assertNull(RegistrationManager.getInstance().getCurrentUser());
	}

	/**
	 * Tests getting a student by their ID number.
	 */
	@Test
	public void testGetStudentById() {
		StudentDirectory sd = new StudentDirectory();
		assertEquals(0, sd.getStudentDirectory().length);
		try {
			// try to get student when the directory is empty
			sd.getStudentById("krpollar");
		} catch (IllegalArgumentException e) {
			// empty line
		}
		// add student and try to get student using the id
		assertTrue(sd.addStudent("Kim", "Pollard", "krpollar", "krpollar@ncsu.edu", "pw1", "pw1", 14));
		assertEquals(1, sd.getStudentDirectory().length);
		assertEquals("Kim", sd.getStudentById("krpollar").getFirstName());
		assertEquals("Pollard", sd.getStudentById("krpollar").getLastName());

		assertTrue(sd.addStudent("Sarah", "Plott", "sgplott", "sgplott@ncsu.edu", "pw2", "pw2", 14));
		assertEquals(2, sd.getStudentDirectory().length);
		assertEquals("Sarah", sd.getStudentById("sgplott").getFirstName());
		assertEquals("Plott", sd.getStudentById("sgplott").getLastName());
	}

}