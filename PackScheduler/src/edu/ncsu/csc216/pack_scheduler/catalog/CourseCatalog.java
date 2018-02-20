package edu.ncsu.csc216.pack_scheduler.catalog;


import java.io.FileNotFoundException;
import java.io.IOException;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;
import edu.ncsu.csc216.pack_scheduler.io.CourseRecordIO;

/**
 * Gives the GUI objects meaning and gives the program functionality through many
 * different options and settings
 * @author Andrew Regulski
 * @author Kim Pollard
 *
 */

public class CourseCatalog {
		
		private SortedList<Course> courseCatalog = new SortedList<Course>();
		/**
		 * Creates a new CourseCatalog
		 */  
		public CourseCatalog() {
			newCourseCatalog();
		}
		
		/**
		 * Makes the new courseCatalog a sorted list
		 */
		public void newCourseCatalog() {
			courseCatalog = new SortedList<Course>();
		}
		
		/**
		 * Loads the courses from the provided files
		 * @param fileName the file to load courses from
		 * @throws IllegalArgumentException if the file is not found
		 */
		public void loadCoursesFromFile(String fileName) {
			try {
				courseCatalog = CourseRecordIO.readCourseRecords(fileName);
			} catch (FileNotFoundException e) {
				throw new IllegalArgumentException("Unable to read file " + fileName);
			}
		}
		
		/**
		 * Adds the given Course to the catalog
		 * @param name Name of course
		 * @param title Title of course
		 * @param section Section of course
		 * @param credits Number of credits for course
		 * @param instructorId Instructor Id of course
		 * @param meetingDays Meeting days for course 
		 * @param startTime Start time of course
		 * @param endTime End time of course
		 * @throws IllegalArgumentException if there is an invalid input
		 * @return true or false if the course can be added or not
		 */
		public boolean addCourseToCatalog(String name, String title, String section, int credits, String instructorId, String meetingDays, int startTime, int endTime) {
			if(name == null || name.equals("")) {
				throw new IllegalArgumentException("Invalid name");
			}
			if(title == null || title.equals("")) {
				throw new IllegalArgumentException("Invalid title");
			}
			if(section == null || section.equals("")) {
				throw new IllegalArgumentException("Invalid section");
			}
			if(credits < 3 || credits > 18) {
				throw new IllegalArgumentException("Invalid credits");
			}
			if(instructorId == null || instructorId.equals("")) {
				throw new IllegalArgumentException("Invalid instructorId");
			}
			if(meetingDays == null || meetingDays.equals("")) {
				throw new IllegalArgumentException("Invalid meetingDays");
			}
			Course course = new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
			
			for (int i = 0; i < courseCatalog.size(); i++) {
				Course c = courseCatalog.get(i);
				if (c.getName().equals(course.getName()) && c.getSection().equals(course.getSection())) {
					return false;
				}
			}
			return courseCatalog.add(course);
		}
		
		/**
		 * Removes the given course from the catalog based on name and section
		 * @param name Name of course
		 * @param section Section of course 
		 * @return true or false if the course can be removed or not
		 */
		public boolean removeCourseFromCatalog(String name, String section) {
			for (int i = 0; i < courseCatalog.size(); i++) {
				Course c = courseCatalog.get(i);
				if (c.getName().equals(name)) {
					courseCatalog.remove(i);
					return true;
				}
			}
			return false;			
		}

		/**
		 * Retrieves a certain course from the Catalog based on name and section
		 * @param name Name of course
		 * @param section Section of course
		 * @return Course A single course from the course catalog
		 */
		public Course getCourseFromCatalog(String name, String section) {
			for(int i = 0; i < courseCatalog.size(); i++) {
				if(courseCatalog.get(i).getName().equals(name) && courseCatalog.get(i).getSection().equals(section)) {
					return courseCatalog.get(i);
				}
			}	
			return null;			
		}
		
		/**
		 * Gets the full course catalog as a String[][]
		 * @return String[][] the CourseCatalog
		 */
		public String[][] getCourseCatalog(){
			String [][] directory = new String[courseCatalog.size()][4];
			for (int i = 0; i < courseCatalog.size(); i++) {
				Course c = courseCatalog.get(i);
				directory[i][0] = c.getName();
				directory[i][1] = c.getSection();
				directory[i][2] = c.getTitle();
				directory[i][3] = c.getMeetingString();
			}
			return directory;		
		}
		
		/**
		 * Saves the given course Catalog to the file
		 * @param fileName the file to save the CourseCatalog to
		 * @throws IllegalArgumentException if there is an input issue
		 */
		public void saveCourseCatalog(String fileName) {
			try {
				CourseRecordIO.writeCourseRecords(fileName, courseCatalog);
			} catch (IOException e) {
				throw new IllegalArgumentException("Unable to write to file " + fileName);
			}
		}
}

