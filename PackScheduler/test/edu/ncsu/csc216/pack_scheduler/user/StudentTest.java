package edu.ncsu.csc216.pack_scheduler.user;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests all of the separate variables of a student to ensure they are all valid and the program can
 * construct a student correctly
 * @author Andrew Regulski 
 */
public class StudentTest {
	/** Student first name */
	private static final String FIRSTNAME = "Daniel";
	/** Student last name */
	private static final String LASTNAME = "Wu";
	/** Student id */
	private static final String ID = "200215724";
	/** Student email */
	private static final String EMAIL = "Danwu15@ncsu.edu";
	/** Student password */
	private static final String PASSWORD = "Danwu15";
	/** Student max credits */
	private static final int MAXCREDITS = 17;


	/**
	 * Tests to create a null student, an empty string student, and a valid student
	 */
	@Test
	public void testStudentStringStringStringStringStringInt() {
		//assertEquals("first", s1.getFirstName());
		 //Start testing invalid input for each parameter - one at a time.  The standard structure of an invalid test is the following:
		Student s = null; //Initialize a student reference to null
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		    //Note that for testing purposes, the password doesn't need to be hashedpassword
		    fail(); //If we reach this point a Student was constructed when it shouldn't have been!
		} catch (IllegalArgumentException e) {
		    //We should get here if the expected IllegalArgumentException is thrown, but that's not enough
		    //for the test.  We also need to make sure that the reference s is still null!
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 15);
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(15, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
	}

	/**
	 * Tests to create a null student, an empty string student, and a valid student with the default
	 * max credits of 18
	 */
	@Test
	public void testStudentStringStringStringStringString() {
		Student s = null;
		try {
		    s = new Student(null, "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {   
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("", "last", "id", "email@ncsu.edu", "hashedpassword");
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertNull(s);
		}
		
		s = null;
		try {
		    s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		    assertEquals("first", s.getFirstName());
		    assertEquals("last", s.getLastName());
		    assertEquals("id", s.getId());
		    assertEquals("email@ncsu.edu", s.getEmail());
		    assertEquals("hashedpassword", s.getPassword());
		    assertEquals(18, s.getMaxCredits());
		} catch (IllegalArgumentException e) {
		    fail();
		}
	}

	/**
	 * Tests to create a null student first name, an empty string student first name,
	 *  and a valid student first name
	 */
	@Test
	public void testSetFirstName() {
		//Construct a valid Student and test for null first name
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		try {
		    s.setFirstName(null);
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		}
		//Test for empty string first name
		try {
		    s.setFirstName("");
		    fail(); //We don't want to reach this point - an exception should be thrown!
		} catch (IllegalArgumentException e) {
		    //We've caught the exception, now we need to make sure that the field didn't change
		    assertEquals("first", s.getFirstName());
		}
		//Test for valid first name
		s.setFirstName("Valid First Name");
		assertEquals("Valid First Name", s.getFirstName());
	}

	/**
	 * Tests to create a null student last name, an empty string student last name,
	 *  and a valid student last name
	 */
	@Test
	public void testSetLastName() {		 
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		//Test for null last name
		try {
		    s.setLastName(null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
		//Test for empty string last name
		try {
		    s.setLastName("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("last", s.getLastName());
		}
		//Test for valid last name
		s.setLastName("Valid Last Name");
		assertEquals("Valid Last Name", s.getLastName());
	}
	
	/**
	 * Tests to create a null student email, an empty string student email,
	 *  an email missing an '@' symbol, an email missing a '.', an email with the
	 *  '.' symbol before the '@' symbol (instead of afterwards), and a valid email
	 */
	@Test
	public void testSetEmail() {
		// Test that setting the email to null doesn't change the title (or anything
				// else).
				Student s = new Student(FIRSTNAME, LASTNAME, ID, EMAIL, PASSWORD, MAXCREDITS);

				assertEquals(FIRSTNAME, s.getFirstName());
				assertEquals(LASTNAME, s.getLastName());
				assertEquals(ID, s.getId());
				assertEquals(EMAIL, s.getEmail());
				assertEquals(PASSWORD, s.getPassword());
				assertEquals(MAXCREDITS, s.getMaxCredits());

				try {
					s.setEmail(null);
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals(FIRSTNAME, s.getFirstName());
					assertEquals(LASTNAME, s.getLastName());
					assertEquals(ID, s.getId());
					assertEquals(EMAIL, s.getEmail());
					assertEquals(PASSWORD, s.getPassword());
					assertEquals(MAXCREDITS, s.getMaxCredits());
				}

				// Test that setting the email to an empty string doesn't change the title (or
				// anything else).
				try {
					s.setEmail("");
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals(FIRSTNAME, s.getFirstName());
					assertEquals(LASTNAME, s.getLastName());
					assertEquals(ID, s.getId());
					assertEquals(EMAIL, s.getEmail());
					assertEquals(PASSWORD, s.getPassword());
					assertEquals(MAXCREDITS, s.getMaxCredits());
				}

				// Test that setting the email without '@' doesn't change the title (or anything
				// else).
				try {
					s.setEmail("zhuang15ncsu.edu");
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals(FIRSTNAME, s.getFirstName());
					assertEquals(LASTNAME, s.getLastName());
					assertEquals(ID, s.getId());
					assertEquals(EMAIL, s.getEmail());
					assertEquals(PASSWORD, s.getPassword());
					assertEquals(MAXCREDITS, s.getMaxCredits());
				}

				// Test that setting the email without '.' doesn't change the title (or anything
				// else).
				try {
					s.setEmail("zhuang15@ncsuedu");
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals(FIRSTNAME, s.getFirstName());
					assertEquals(LASTNAME, s.getLastName());
					assertEquals(ID, s.getId());
					assertEquals(EMAIL, s.getEmail());
					assertEquals(PASSWORD, s.getPassword());
					assertEquals(MAXCREDITS, s.getMaxCredits());
				}

				// Test that setting the email with '.' before '@' doesn't change the title (or
				// anything else).
				try {
					s.setEmail("zhuang.15@ncsuedu");
					fail();
				} catch (IllegalArgumentException e) {
					assertEquals(FIRSTNAME, s.getFirstName());
					assertEquals(LASTNAME, s.getLastName());
					assertEquals(ID, s.getId());
					assertEquals(EMAIL, s.getEmail());
					assertEquals(PASSWORD, s.getPassword());
					assertEquals(MAXCREDITS, s.getMaxCredits());
				}

				// Valid set
				s.setEmail("zhuang@ncsu.edu");
				assertEquals(FIRSTNAME, s.getFirstName());
				assertEquals(LASTNAME, s.getLastName());
				assertEquals(ID, s.getId());
				assertEquals("zhuang@ncsu.edu", s.getEmail());
				assertEquals(PASSWORD, s.getPassword());
				assertEquals(MAXCREDITS, s.getMaxCredits());
	}

	/**
	 * Tests to create a null student password, an empty string student password,
	 *  and a valid student password
	 */
	@Test
	public void testSetPassword() {
		User s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		//Test for null password
		try {
		    s.setPassword(null);
		    fail(); 
		} catch (IllegalArgumentException e) {
		    assertEquals("hashedpassword", s.getPassword());
		}
		//Test for empty string password
		try {
		    s.setPassword("");
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals("hashedpassword", s.getPassword());
		}
		//Test for valid password
		s.setPassword("Hunter2");
		assertEquals("Hunter2", s.getPassword());
	}

	/**
	 * Tests to create max credits with a value above 18, a value below 3 , and a valid
	 * max credits value
	 */
	@Test
	public void testSetMaxCredits() {
		Student s = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		try {
		    s.setMaxCredits(20);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(14, s.getMaxCredits());
		}
		
		try {
		    s.setMaxCredits(1);
		    fail();
		} catch (IllegalArgumentException e) {
		    assertEquals(14, s.getMaxCredits());
		}
		
		s.setMaxCredits(12);
		assertEquals(12, s.getMaxCredits());
	}
	
	/**
	 * Tests Student objects against each other to ensure that there is consistency between 
	 * creating student objects WITH HASHCODE ENABLED FOR PASSWORD
	 */
	@Test
	public void testHashCode() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s3 = new Student("He Protec", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s4 = new Student("first", "He Attac", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s5 = new Student("first", "last", "but most", "email@ncsu.edu", "hashedpassword", 14);
		User s6 = new Student("first", "last", "id", "import@ntl.y ", "hashedpassword", 14);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "hes bacc", 14);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 17);
		
		//Test for the same hash code for the same values
		assertEquals(s1.hashCode(), s2.hashCode());
		
		//Test for each of the fields
		assertNotEquals(s1.hashCode(), s3.hashCode());
		assertNotEquals(s2.hashCode(), s3.hashCode());
		assertNotEquals(s1.hashCode(), s4.hashCode());
		assertNotEquals(s2.hashCode(), s4.hashCode());
		assertNotEquals(s1.hashCode(), s5.hashCode());
		assertNotEquals(s2.hashCode(), s5.hashCode());
		assertNotEquals(s1.hashCode(), s6.hashCode());
		assertNotEquals(s2.hashCode(), s6.hashCode());
		assertNotEquals(s1.hashCode(), s7.hashCode());
		assertNotEquals(s2.hashCode(), s7.hashCode());
		assertNotEquals(s1.hashCode(), s8.hashCode());
		assertNotEquals(s2.hashCode(), s8.hashCode());
	}

	/**
	 * Tests Student objects against each other to ensure that there is consistency between 
	 * creating student objects
	 */
	@Test
	public void testEqualsObject() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s3 = new Student("He Protec", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s4 = new Student("first", "He Attac", "id", "email@ncsu.edu", "hashedpassword", 14);
		User s5 = new Student("first", "last", "but most", "email@ncsu.edu", "hashedpassword", 14);
		User s6 = new Student("first", "last", "id", "import@ntl.y ", "hashedpassword", 14);
		User s7 = new Student("first", "last", "id", "email@ncsu.edu", "hes bacc", 14);
		User s8 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 17);
		User s9 = new Student("Changing", "it", "all", "up@ncsu.edu", "hashedpassword", 17);
		User s10 = new Student("Changing", "it", "all", "up@ncsu.edu", "passcheck", 17);
		
		//Test for equality in both directions
		assertTrue(s1.equals(s2));
		assertTrue(s2.equals(s1));
				
		//Test for each of the fields
		assertFalse(s1.equals(s3));
		assertFalse(s1.equals(s4));
		assertFalse(s1.equals(s5));
		assertFalse(s1.equals(s6));
		assertFalse(s1.equals(s7));
		assertFalse(s1.equals(s8));
		assertFalse(s9.equals(s10));
		assertFalse(s3.equals(s4));
		assertFalse(s5.equals(s6));
	}

	/**
	 * Tests the toString output of the student objects against what they're supposed to be and makes
	 * sure they're equivalent
	 */
	@Test
	public void testToString() {
		User s1 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword", 14);
		String string1 = "first,last,id,email@ncsu.edu,hashedpassword,14";
		assertEquals(string1, s1.toString());
		
		User s2 = new Student("first", "last", "id", "email@ncsu.edu", "hashedpassword");
		String string2 = "first,last,id,email@ncsu.edu,hashedpassword,18";
		assertEquals(string2, s2.toString());
	}
	
	/**
	 * Test compareTo method works correctly
	 */
	@Test
	public void testCompareTo() {
		Student s1 = new Student("Glenda", "Aaron", "glaar", "glaar@ncsu.edu", "hashedpassword1", 14);
		Student s2 = new Student("Robert", "Brill", "robri", "robri@ncsu.edu", "hashedpassword2", 15);
		Student s3 = new Student("Sadie", "Cotter", "sadcot", "sadcot@ncsu.edu", "hashedpassword3", 17);
		Student s4 = new Student("Glenda", "Aaron", "glaar", "glaar@ncsu.edu", "hashedpassword1", 14);
		//Test x.compareTo(y) = y.compareTo(x)
		assertTrue(s1.compareTo(s2) == -s2.compareTo(s1));
		//Test the relation is transitive
		assertTrue(s3.compareTo(s1) > 0);
		//Test s1 = s4
		assertEquals(s1.compareTo(s2), s4.compareTo(s2));
		assertEquals(s1.compareTo(s4) == 0, s1.equals(s4));
		}

}
