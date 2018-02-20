package edu.ncsu.csc216.collections.list;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test the SortedList works correctly
 * @author Qiuyu Chen
 *
 */
public class SortedListTest {

	/**
	 * Test the list works
	 */
	@Test
	public void testSortedList() {
		SortedList<String> list = new SortedList<String>();
		assertEquals(0, list.size());
		assertFalse(list.contains("apple"));
		
		//  Test that the list grows by adding at least 11 elements
		//Remember the list's initial capacity is 10
		//Add 12 elements
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		list.add("g");
		list.add("h");
		list.add("i");
		list.add("j");
		list.add("k");
		list.add("l");
		assertEquals(12, list.size());

	}

	/**
	 * Test add valid elments and invalid elements
	 */
	@Test
	public void testAdd() {
		SortedList<String> list = new SortedList<String>();
		
		list.add("banana");
		assertEquals(1, list.size());
		assertEquals("banana", list.get(0));
		
		//  Test adding to the front, middle and back of the list
		//Test adding to the front
		list.add("apple") ;
		//Test adding to the middle
		list.add("orange");
		assertTrue(list.indexOf("apple") < list.indexOf("orange"));
		//Test adding to the back
		list.add("zoo");
		assertTrue(list.indexOf("orange") < list.indexOf("zoo"));
		//  Test adding a null element
		try {
			list.add(null);
		} catch (NullPointerException e) {
			assertEquals(4, list.size());
		}
		//  Test adding a duplicate element
		list.add("dog");
		assertEquals(5, list.size());
		try {
			list.add("dog");
		} catch (IllegalArgumentException e) {
			assertEquals(5, list.size());
		}
		
	}
	
	/**
	 * Test the Get method
	 */
	@Test
	public void testGet() {
		SortedList<String> list = new SortedList<String>();
		
		//Since get() is used throughout the tests to check the
		//contents of the list, we don't need to test main flow functionality
		//here.  Instead this test method should focus on the error 
		//and boundary cases.
		
		//  Test getting an element from an empty list
		try {
			list.get(1);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//  Add some elements to the list
		list.add("apple");
		assertEquals(1, list.size());
		assertEquals("apple", list.get(0));
		//  Test getting an element at an index < 0
		try {
			list.get(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
		//  Test getting an element at size
		try {
			list.get(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(1, list.size());
		}
	}
	
	/**
	 * Test the remove method
	 */
	@Test
	public void testRemove() {
		SortedList<String> list = new SortedList<String>();
		
		//  Test removing from an empty list
		try {
			list.remove(0);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(0, list.size());
		}
		//  Add some elements to the list - at least 4
		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		assertEquals(4, list.size());
		//  Test removing an element at an index < 0
		try {
			list.remove(-2);
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//  Test removing an element at size
		try {
			list.remove(list.size());
			fail();
		} catch (IndexOutOfBoundsException e) {
			assertEquals(4, list.size());
		}
		//  Test removing a middle element
		list.remove(2);
		assertEquals(3, list.size());
		assertEquals("d", list.get(2));
		//  Test removing the last element
		list.remove(2);
		assertEquals(2, list.size());
		assertEquals("b", list.get(1));
		//  Test removing the first element
		list.remove(0);
		assertEquals(1, list.size());
		assertEquals("b", list.get(0));
		//  Test removing the last element
		list.remove(0);
		assertEquals(0, list.size());
	}
	
	/**
	 * Test the IndexOf method
	 */
	@Test
	public void testIndexOf() {
		SortedList<String> list = new SortedList<String>();
		
		// Test indexOf on an empty list
		assertEquals(-1, list.indexOf(""));
		// Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		// Test various calls to indexOf for elements in the list
		//and not in the list
		assertEquals(0, list.indexOf("apple"));
		assertEquals(1, list.indexOf("banana"));
		assertEquals(2, list.indexOf("orange"));
		assertEquals(-1, list.indexOf("pear"));
		assertEquals(-1, list.indexOf("watermelon"));
		// Test checking the index of null
		try{
			list.indexOf(null);
			fail();
		} catch(NullPointerException e) {
			//Ignore
		}
	}
	
	/**
	 * Test the Clear method
	 */
	@Test
	public void testClear() {
		SortedList<String> list = new SortedList<String>();

		//  Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		//  Clear the list
		list.clear();
		//  Test that the list is empty
		assertTrue(list.isEmpty());
	}

	/**
	 * Test IsEmpty method
	 */
	@Test
	public void testIsEmpty() {
		SortedList<String> list = new SortedList<String>();
		
		//  Test that the list starts empty
		assertTrue(list.isEmpty());
		//  Add at least one element
		list.add("apple");
		//  Check that the list is no longer empty
		assertFalse(list.isEmpty());
	}

	/**
	 * Test the Contains method
	 */
	@Test
	public void testContains() {
		SortedList<String> list = new SortedList<String>();
		
		//  Test the empty list case
		assertTrue(list.isEmpty());
		//  Add some elements
		list.add("apple");
		list.add("banana");
		list.add("orange");
		//  Test some true and false cases
		assertTrue(list.contains("apple"));
		assertTrue(list.contains("banana"));
		assertTrue(list.contains("orange"));
		assertFalse(list.contains("pear"));
		assertFalse(list.contains(null));
	}
	
	/**
	 * Test the Equals method
	 */
	@Test
	public void testEquals() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple"); 
		list1.add("banana");
		list1.add("mango");
		list1.add("pear");
		
		list2.add("apple"); 
		list2.add("banana");
		list2.add("mango");
		list2.add("pear");
		
		list3.add("grapes"); 
		list3.add("banana");
		list3.add("watermelon");
		list3.add("pear");
		
		//  Test for equality and non-equality
		assertTrue(list1.equals(list2));
		assertTrue(list2.equals(list1));
		
		assertFalse(list1.equals(list3));
		assertFalse(list3.equals(list2));
	}
	
	/**
	 * Test the HashCode method
	 */
	@Test
	public void testHashCode() {
		SortedList<String> list1 = new SortedList<String>();
		SortedList<String> list2 = new SortedList<String>();
		SortedList<String> list3 = new SortedList<String>();
		
		list1.add("apple"); 
		list1.add("banana");
		list1.add("mango");
		list1.add("pear");
		
		list2.add("apple"); 
		list2.add("banana");
		list2.add("mango");
		list2.add("pear");
		
		list3.add("grapes"); 
		list3.add("banana");
		list3.add("watermelon");
		list3.add("pear");
		
		
		assertEquals(list1.hashCode(), list2.hashCode());
		
		assertNotEquals(list1.hashCode(), list3.hashCode());
		assertNotEquals(list2.hashCode(), list3.hashCode());
		
		//  Test for the same and different hashCodes
	}

}
 
