package edu.ncsu.csc216.pack_scheduler.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.PrintStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

import edu.ncsu.csc216.collections.list.SortedList;
import edu.ncsu.csc216.pack_scheduler.user.Student;
import edu.ncsu.csc216.pack_scheduler.user.User;

/**
 * Reads through the files, reads off and separates the records/tokens within the files, and
 * then writes them as elements into the students ListArray
 * @author Andrew Regulski
 * @author Kim Pollard
 */
public class StudentRecordIO {


	/**
	 * Reads student records from a file and generates a sorted list of valid Students. Any
	 * invalid Students are ignored. If the file to read cannot be found or the
	 * permissions are incorrect a File NotFoundException is thrown.
	 * 
	 * @param fileName
	 *            file to read student records from
	 * @return a Sortedlist of valid students
	 * @throws FileNotFoundException
	 *             if the file cannot be found or read
	 */
	public static SortedList<Student> readStudentRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));
		SortedList<Student> studentDirectory = new SortedList<Student>();
		while (fileReader.hasNextLine()) {
			try {
				Student student = processStudent(fileReader.nextLine());
				boolean duplicate = false;
				for (int i = 0; i < studentDirectory.size(); i++) {
					User c = studentDirectory.get(i);
					if (student.getId().equals(c.getId())) {
						// it's a duplicate
						duplicate = true;
					}
				}
				if (!duplicate) {
					studentDirectory.add(student);
				}
			} catch (IllegalArgumentException e) {
				// skip the line
			}
		}
		fileReader.close();
		return studentDirectory;
	}
	/**
	 * This method reads a student line and return information
	 * @param line String of student information
	 * @return student student from line
	 * @throws IllegalArgumentException if the input is invalid
	 */
	private static Student processStudent(String line) {
		Scanner lineReader = new Scanner(line);
		lineReader.useDelimiter(",");
		Student student;
		try {
			String firstName = lineReader.next();
			String lastName = lineReader.next();
			String id = lineReader.next();
			String email = lineReader.next();
			String hashPW = lineReader.next(); 

			if (lineReader.hasNextInt()) {
				student = new Student(firstName, lastName, id, email, hashPW,
						lineReader.nextInt());
			} else {
				lineReader.close();
				throw new IllegalArgumentException();
			}
		} catch (NoSuchElementException e) {
			lineReader.close();
			throw new IllegalArgumentException();
		}
		lineReader.close();
		return student;
	}

	/**
	 * Writes the given list of students to file.
	 * 
	 * @param fileName String name of file to write
	 * @param studentDirectory SortedList of students
	 * @throws IOException if Unable to write to file
	 */
	public static void writeStudentRecords(String fileName, SortedList<Student> studentDirectory) throws IOException {
		PrintStream fileWriter = new PrintStream(new File(fileName));

		for (int i = 0; i < studentDirectory.size(); i++) {
			fileWriter.println(studentDirectory.get(i).toString());
		}

		fileWriter.close();

		}
	}