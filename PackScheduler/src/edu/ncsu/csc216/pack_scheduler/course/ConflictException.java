/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Sets the message for the conflict (either a custom message or the default message)
 * @author Andrew Regulski
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;

	/**
	 * Calls the Conflict exception (recognizes that the problem has been triggered) and uses the provided message
	 *@param message the custom string message that can be used
	 */
	public ConflictException(String message) {
		super(message);
	}
	
	/**
	 * Calls the Conflict exception (recognizes that the problem has been triggered) and uses the provided message
	 */
	public ConflictException() {
		this("Schedule conflict.");
	}
}
