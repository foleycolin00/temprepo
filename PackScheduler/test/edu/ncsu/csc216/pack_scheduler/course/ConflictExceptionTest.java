/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Makes sure the methods are working properly and are outputing the correct messages
 * @author Andrew Regulski
 *
 */
public class ConflictExceptionTest {

	/**
	 * Test method for the Conflict Exception.
	 */
	@Test
	public void testConflictExceptionString() {
	    ConflictException ce = new ConflictException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for the conflict Exception
	 */
	@Test
	public void testConflictException() {
		ConflictException ce = new ConflictException();
	    assertEquals("Schedule conflict.", ce.getMessage());
	}

}
