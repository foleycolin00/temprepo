/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Makes sure the methods are working properly and are outputing the correct messages
 * @author Andrew Regulski
 *
 */
public class InvalidTransitionExceptionTest {

	/**
	 * Test method for the Conflict Exception.
	 */
	@Test
	public void testConflictExceptionString() {
		InvalidTransitionException ce = new InvalidTransitionException("Custom exception message");
	    assertEquals("Custom exception message", ce.getMessage());
	}

	/**
	 * Test method for the conflict Exception
	 */
	@Test
	public void testConflictException() {
		InvalidTransitionException ce = new InvalidTransitionException();
	    assertEquals("Invalid FSM Transition.", ce.getMessage());
	}

}