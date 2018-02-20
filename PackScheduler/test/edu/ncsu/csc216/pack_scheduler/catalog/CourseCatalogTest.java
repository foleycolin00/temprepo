package edu.ncsu.csc216.pack_scheduler.catalog;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

import org.junit.Before;
import org.junit.Test;


import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;


/**
 * Tests StudentDirectory.
 * @author Sarah Heckman
 * @author Andrew Regulski
 */
public class CourseCatalogTest {

	/** Valid course records */
	private final String validTestFile = "test-files/course_records.txt";
	/** Test name */
	private static final String NAME = "CSC216";
	/** Test Title */
	private static final String TITLE = "Learning 2 code";
	/** Test Section */
	private static final String SECTION = "002";
	/** Test Credits */
	private static final int CREDITS = 3;
	/** Test InstrcutorID */
	private static final String INSTRUCTOR_ID = "dbugg";
	/** Test MeetingDays */
	private static final String MEETING_DAYS = "MW";
	/** Test start time */
	private static final int START_TIME = 1330;
	/** Test end time */
	private static final int END_TIME = 1445;
	
	/**
	 * Resets course_records.txt for use in other tests.
	 * @throws Exception if something fails during setup.
	 */
	@Before
	public void setUp() throws Exception {		
		Path sourcePath = FileSystems.getDefault().getPath("test-files", "expected_course_records.txt");
		Path destinationPath = FileSystems.getDefault().getPath("test-files", "course_records.txt");
		try {
			Files.deleteIfExists(destinationPath);
			Files.copy(sourcePath, destinationPath);
		} catch (IOException e) {
			fail("Unable to reset files");
		}
	}

	/**
	 * Tests CourseCatalog().
	 */
	@Test
	public void testCourseCatalog() {
		//Test that the CourseCatalog is initialized to an empty list
		CourseCatalog sd = new CourseCatalog();
		assertFalse(sd.removeCourseFromCatalog("CSC216", "001"));
		assertEquals(0, sd.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.testNewCourseCatalog().
	 */
	@Test
	public void testNewCourseCatalog() {
		//Test that if there are courses in the catalog, they 
		//are removed after calling newCourseCatalog().
		CourseCatalog sd = new CourseCatalog();
		
		sd.loadCoursesFromFile(validTestFile);
		assertEquals(3, sd.getCourseCatalog().length);
		
		sd.newCourseCatalog();
		assertEquals(0, sd.getCourseCatalog().length);
	}

	/**
	 * Tests CourseCatalog.loaCoursesFromFile().
	 */
	@Test
	public void testLoadCoursesFromFile() {
		CourseCatalog sd = new CourseCatalog();
				
		//Test valid file
		sd.loadCoursesFromFile(validTestFile);
		assertEquals(3, sd.getCourseCatalog().length);
		
		try {
			CourseRecordIO.readCourseRecords(validTestFile);
			assertEquals(3, sd.getCourseCatalog().length);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException();
		}
	}

	/**
	 * Tests CourseCatalog.addCourseToCatalog().
	 */
	@Test
	public void testAddCourseToCatalog() {
		
		CourseCatalog sd = new CourseCatalog();

		// Test valid Student
		sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		String[][] courseCatalog = sd.getCourseCatalog();
		assertEquals(1, courseCatalog.length);
		assertEquals(NAME, courseCatalog[0][0]);
		assertEquals(TITLE, courseCatalog[0][2]);
		assertEquals(SECTION, courseCatalog[0][1]); 

		// Test null Name
		try {
			sd.addCourseToCatalog(null, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid name", e.getMessage());
		} 
		
		// Test blank Name
				try {
					sd.addCourseToCatalog("", TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Invalid name", e.getMessage());
				} 

		// Test null Title
		try {
			sd.addCourseToCatalog(NAME, null, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid title", e.getMessage());
		}
		
		// Test blank Title
				try {
					sd.addCourseToCatalog(NAME, "", SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Invalid title", e.getMessage());
				}

		// Test blank Meeting days
		try {
			sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, "", START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid meetingDays", e.getMessage());
		}
		
		// Test null Meeting days
				try {
					sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, null, START_TIME, END_TIME);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Invalid meetingDays", e.getMessage());
				}

		// Test null section
		try {
			sd.addCourseToCatalog(NAME, TITLE, null, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid section", e.getMessage());
		}

		// Try null instructor id
		try {
			sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, null, MEETING_DAYS, START_TIME, END_TIME);
			fail();
		} catch (IllegalArgumentException e) {
			assertEquals("Invalid instructorId", e.getMessage());
		}

		// Add Courses with same name
		try {
			sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
		} catch (IllegalArgumentException e) {
			fail(); 
		}
		
		// Try credits over 18
				try {
					sd.addCourseToCatalog(NAME, TITLE, SECTION, 20, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Invalid credits", e.getMessage());
				}
				
				// Try credits under 3
				try {
					sd.addCourseToCatalog(NAME, TITLE, SECTION, 1, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals("Invalid credits", e.getMessage());
				}
	}

	
	/**
	 * Tests CourseCatalog.getCourseFromCatalog().
	 */
	@Test
	public void testGetCourseFromCatalog() {
		CourseCatalog sd = new CourseCatalog();
		try{
			sd.addCourseToCatalog(NAME, TITLE, SECTION, CREDITS, INSTRUCTOR_ID, MEETING_DAYS, START_TIME, END_TIME);
			sd.getCourseFromCatalog(NAME, SECTION);
		} catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	/**
	 * Tests CourseCatalog.removeCourseFromCatalog().
	 */
	@Test
	public void testRemoveCourseFromCatalog() {
		CourseCatalog sd = new CourseCatalog();
				
		//Add students and remove
		sd.loadCoursesFromFile(validTestFile);
		assertEquals(3, sd.getCourseCatalog().length);
		assertTrue(sd.removeCourseFromCatalog("CSC116", "001"));
		String [][] courseCatalog = sd.getCourseCatalog();
		assertEquals(2, courseCatalog.length);
		assertEquals("CSC216", courseCatalog[1][0]);
		assertEquals("Programming Concepts - Java", courseCatalog[1][2]);
		assertEquals("601", courseCatalog[1][1]);
	}
	
	/**
	 * Tests CourseCatalog.saveCourseCatalog().
	 */
	@Test
	public void testSaveCourseCatalog() {
		CourseCatalog sd = new CourseCatalog();
		
		//Add a student
		sd.addCourseToCatalog("CSC116", "Intro to Programming - Java", "003", 3, "spbalik", "MW", 1250, 1440);
		sd.addCourseToCatalog("CSC216", "Programming Concepts - Java", "001", 4, "sesmith5", "MW", 1000, 1130);
		sd.addCourseToCatalog("CSC226", "Programming Concepts - Java", "601", 4 , "jep", "A", 0, 0);
		assertEquals(3, sd.getCourseCatalog().length);
		sd.saveCourseCatalog("test-files/actual_course_records.txt");
		checkFiles("test-files/expected_records_c.txt", "test-files/actual_course_records.txt");
	}
	
	/**
	 * Helper method to compare two files for the same contents
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));
			
			while (expScanner.hasNextLine()) {
				assertEquals(expScanner.nextLine(), actScanner.nextLine());
			}
			
			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

}
