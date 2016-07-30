package com.rwenger.salesman;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for the City class.
 * 
 * @author Robert Wenger
 */
public class CityTest
{

	/**
	 * Test method for
	 * {@link com.rwenger.salesman.City#City(int, int, java.lang.String)}.
	 */
	@Test
	public void testCity()
	{
		// Making sure all of the variables are set correctly.
		City city = new City(0, 1, "Pittsburg");
		assertEquals(0, city.getX());
		assertEquals(1, city.getY());
		assertEquals("Pittsburg", city.getName());
	}

	/**
	 * Test method for
	 * {@link com.rwenger.salesman.City#distanceTo(com.rwenger.salesman.City)}.
	 */
	@Test
	public void testDistanceTo()
	{
		// Simple first test.
		City city = new City(0, 1, "Pittsburg");
		City city2 = new City(0, 0, "New Orleans");
		assertEquals(1, city.distanceTo(city2), 0.01);

		// Testing 0 distance.
		City city3 = new City(0, 0, "New York");
		assertEquals(0, city2.distanceTo(city3), 0.01);

		// Testing negative x coordinate.
		City city4 = new City(-18, 72, "DC");
		assertEquals(74.215, city2.distanceTo(city4), 0.01);

		// Testing negative y coordinate.
		City city5 = new City(72, -18, "LA");
		assertEquals(74.215, city2.distanceTo(city5), 0.01);

		// Testing negative x and y coordinates.
		City city6 = new City(72, -18, "Seattle");
		assertEquals(74.215, city2.distanceTo(city6), 0.01);

	}

}
