/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.course;

/**
 * Calls on the conflict method to compare to activities and check if they conflict time-wise
 * @author Andrew Regulski
 */
public interface Conflict {
	/**
	 * The method it refers to
	 * @param possibleConflictingActivity whichever other activity the primary activity
	 * is being compared to
	 * @throws ConflictException which is the error for if there is a conflicting activity
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
