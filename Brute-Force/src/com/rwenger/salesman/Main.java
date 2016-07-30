package com.rwenger.salesman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Runs a brute force algorithm to find the shortest path.
 * 
 * @author Robert Wenger
 */
public class Main
{

	public static void main(String[] args)
	{
		ArrayList<City> cityList = new ArrayList<City>();

		// First read the list of cities.
		BufferedReader reader = null;
		try
		{
			reader = new BufferedReader(new FileReader("cities.txt"));
		}
		catch(FileNotFoundException e1)
		{
			System.out.println("File not found.");
			return;
		}

		String line;
		try
		{
			while((line = reader.readLine()) != null)
			{
				String[] cityArray = line.split(" ");
				City city = new City(Integer.parseInt(cityArray[0]), Integer.parseInt(cityArray[1]), cityArray[2]);
				cityList.add(city);
			}
		}
		catch(IOException e)
		{
			System.out.println("Error reading file.");
		}

		for(City city : cityList)
		{
			System.out.println(city.getName());
			for(City city2 : cityList)
			{
				System.out.println("\tto " + city2.getName() + ": " + String.valueOf(city.distanceTo(city2)));
			}
		}

		// TODO:
		// Generate a matrix of distances.
		// Generate a list of paths with distances for each path.
		// Find the shortest distance and return the path that matches along
		// with the distance.

	}

}
