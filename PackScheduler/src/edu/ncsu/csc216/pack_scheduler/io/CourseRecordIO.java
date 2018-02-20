/**
 * 
 */
package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.course.Course;

/**
 * Reads through the files, reads off and separates the records/tokens within the files, and
 * then writes them as elements into the course ListArray
 * @author Andrew Regulski
 * @author Kim Pollard
 *
 */
public class CourseRecordIO {

	/**
	  * Reads course records from file and generates list of valid Courses.  Invalid
	  * Courses are ignored.  If the file to read can't be found or the permissions are wrong
	  * a File NotFoundException is thrown.
	  * @param fileName file to read Course records from
	  * @return a list of valid Courses
	  * @throws FileNotFoundException if the file cannot be found or read
	  * @throws IllegalArgumentException if the input is invalid
	  */
	public static SortedList<Course> readCourseRecords(String fileName) throws FileNotFoundException{
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
	    SortedList<Course> courses = new SortedList<Course>();
	    while (fileReader.hasNextLine()) {
	        try {
	            Course course = readCourse(fileReader.nextLine());
	            boolean duplicate = false;
	            for (int i = 0; i < courses.size(); i++) {
	                Course c = courses.get(i);
	                if (course.getName().equals(c.getName()) &&
	                        course.getSection().equals(c.getSection())) {
	                    //it's a duplicate
	                    duplicate = true;
	                }
	            }
	            if (!duplicate) {
	                courses.add(course);
	            }
	        } catch (IllegalArgumentException e) {
	            //skip the line
	        }
	    }
	    fileReader.close();
	    return courses;
	}

	private static Course readCourse(String nextLine) {
		Scanner s = new Scanner(nextLine);
		s.useDelimiter(",");
		int startTime = 0;
		int endTime = 0;
		
		String name = s.next(); 
		String title = s.next();
		String section = s.next();
		String credits1 = s.next();
		int credits = Integer.parseInt(credits1);
		String instructorId = s.next();
		String meetingDays = s.next();
		if(meetingDays.charAt(0) == 'A' && s.hasNext()) {
			s.close();
			throw new IllegalArgumentException();
		}
		
		if(s.hasNextInt()) {
			startTime = s.nextInt();
		}
		if((startTime % 100) > 59) {
				s.close();
				throw new IllegalArgumentException();
		}		
		if(s.hasNextInt()) {
			endTime = s.nextInt();	
		}
		if(startTime > endTime) {
			s.close();
			throw new IllegalArgumentException();
		}
		s.close();
		Course course = new Course (name, title, section, credits, instructorId, meetingDays, startTime, endTime); 
		System.out.println(course);
		return course;
	}

	/**
     * Writes given list of Courses to 
     * @param fileName passes through the file names
     * @param courses the name of the arrayList
     * @throws IOException exception that shows that IO processes were disturbed
     */
	public static void writeCourseRecords(String fileName, SortedList<Course> courses) throws IOException{
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < courses.size(); i++) {
		    fileWriter.println(courses.get(i).toString());
		}

		fileWriter.close();		
	}
}
