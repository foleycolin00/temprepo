package edu.ncsu.csc216.pack_scheduler.course.validator;

import static org.junit.Assert.*;

import org.junit.Test;

public class CourseNameValidatorFSMTest {

	/**
	 * Test the IsValidTest
	 */
	@Test
	public void testIsValid() {
		CourseNameValidatorFSM f = new CourseNameValidatorFSM();
		try {
			f.isValid("!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("cs!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("csc!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("cscc!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c1!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c121!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c121r!");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("cewer");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c2e");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c22e");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c2222");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c222rr");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("c222r2");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			f.isValid("2");
			fail();
		} catch (InvalidTransitionException e) {
			// Passes
		}
		try {
			assertTrue(f.isValid("c232"));
			assertTrue(f.isValid("cc232"));
			assertTrue(f.isValid("ccs232"));
			assertTrue(f.isValid("ccsc232"));
			assertTrue(f.isValid("ccsc232r"));
		} catch (InvalidTransitionException e) {
			fail();
		}
		
	}

}
