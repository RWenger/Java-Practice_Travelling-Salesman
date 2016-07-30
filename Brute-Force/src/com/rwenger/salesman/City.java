package com.rwenger.salesman;

/**
 * Models a city with coordinates and a name.
 * 
 * @author Robert Wenger
 *
 */
public class City
{
	private int x, y;
	private String name;

	/**
	 * Create a new City with coordinates and a name.
	 * 
	 * @param x
	 *            The x-coordinate of the city.
	 * @param y
	 *            The y-coordinate of the city.
	 * @param name
	 *            The name of the city.
	 */
	public City(int x, int y, String name)
	{
		this.x = x;
		this.y = y;
		this.name = name;
	}

	/**
	 * @return The x-coordinate of the city.
	 */
	public int getX()
	{
		return x;
	}

	/**
	 * @return The y-coordinate of the city.
	 */
	public int getY()
	{
		return y;
	}

	/**
	 * @return The name of the city.
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * Get the distance between this city and another city.
	 * 
	 * @param city
	 *            The city to determine the distance to.
	 * @return The difference between this city and the given city.
	 */
	public double distanceTo(City city)
	{
		int xDiff = Math.abs(this.x - city.x);
		int yDiff = Math.abs(this.y - city.y);

		return(Math.sqrt(((double) xDiff * (double) xDiff) + ((double) yDiff * (double) yDiff)));
	}

}
