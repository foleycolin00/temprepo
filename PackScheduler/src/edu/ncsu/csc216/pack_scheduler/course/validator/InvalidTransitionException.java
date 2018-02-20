package edu.ncsu.csc216.pack_scheduler.course.validator;

public class InvalidTransitionException extends Exception {

	/**    
	 * the default id 
	 */
	private static final long serialVersionUID = 1L;

	/** 
	 * Calls the Conflict exception (recognizes that the problem has been triggered) and uses the provided message
	 *@param message the custom string message that can be used
	 */
	public InvalidTransitionException(String message) {
		super(message);
	}
	
	/**
	 * Calls the Conflict exception (recognizes that the problem has been triggered) and uses the provided message
	 */ 
	public InvalidTransitionException() {
		this("Invalid FSM Transition.");
	}
	
}
