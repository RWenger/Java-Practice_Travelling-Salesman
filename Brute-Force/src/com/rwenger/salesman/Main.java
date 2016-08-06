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

		// Generate all permutations.
		// First, generate all combinations of the positions.
		// For a 4-city problem, we have something like this:
		// 0000
		// 0001
		// 0002
		// 0003
		// 0010
		// 0011
		// 0012
		// 0013
		// ....
		// 3333
		// This means n^n possible paths including duplicated cities.
		// We then need to remove duplicates to get the n! possible paths.
		int numCombinations = (int) Math.pow(cityList.size(), cityList.size());
		int[][] permutationsWithDuplicateCities = new int[cityList.size()][numCombinations];

		// Now populate the 2d array. For each column starting from the right...
		for(int i = 0; i < cityList.size(); i++)
		{
			// Fill in (n^column) cells with each number.
			// Repeat until done.
			int numCellsToFill = (int) Math.pow(cityList.size(), i);
			int currentNumber = 0;
			for(int j = 0; j < numCombinations / numCellsToFill; j++, currentNumber++)
			{
				if(currentNumber == cityList.size())
				{
					currentNumber = 0;
				}
				int k = 0;

				while(k < numCellsToFill)
				{
					permutationsWithDuplicateCities[cityList.size() - 1 - i][j * numCellsToFill + k] = currentNumber;
					k++;
				}
			}
		}

		// Get n!
		int factorial = cityList.size();
		for(int i = cityList.size() - 1; i > 1; i--)
		{
			factorial = factorial * i;
		}

		// Keep only paths with no duplicates. That will leave only have n!
		// paths.
		int[][] permutations = new int[cityList.size()][factorial];
		int permutationCounter = 0;

		// For each potential path...
		for(int i = 0; i < numCombinations; i++)
		{
			boolean isValid = true;
			// ... check each column...
			for(int j = 0; j < cityList.size(); j++)
			{
				// .. to see if it matches a city from any subsequent column.
				for(int k = j + 1; k < cityList.size(); k++)
				{
					// If any city is duplicated, it's not valid.
					isValid = !(permutationsWithDuplicateCities[j][i] == permutationsWithDuplicateCities[k][i]);
					if(!isValid)
					{
						break;
					}
				}
				if(!isValid)
				{
					break;
				}
			}
			// But if the path is valid, add it to the list of good
			// permutations.
			if(isValid)
			{
				for(int n = 0; n < cityList.size(); n++)
				{
					permutations[n][permutationCounter] = permutationsWithDuplicateCities[n][i];
				}
				permutationCounter++;
			}
		}

		// Next, create a 2d matrix with the distances between cities.
		double distance[][] = new double[cityList.size()][cityList.size()];

		for(City city : cityList)
		{
			for(City city2 : cityList)
			{
				distance[cityList.indexOf(city)][cityList.indexOf(city2)] = city.distanceTo(city2);
			}
		}

		// Then, find the distance for each path.
		// Record the shortest path while we're at it.
		double pathDistances[] = new double[factorial];
		int shortestPath = Integer.MAX_VALUE;
		// For each path...
		for(int i = 0; i < factorial; i++)
		{
			double currentPathDistance = 0;
			// .. compare the distance between each step.
			for(int j = 0; j < cityList.size(); j++)
			{
				// If the city is the last on the list, we return to the
				// originating city.
				if(j == cityList.size() - 1)
				{
					currentPathDistance += distance[permutations[j][i]][permutations[0][i]];
				}
				// Otherwise, compare to the next city.
				else
				{
					currentPathDistance += distance[permutations[j][i]][permutations[j + 1][i]];
				}
			}

			pathDistances[i] = currentPathDistance;

			// Check if this path is shorter than any checked before.
			if(i == 0 || currentPathDistance < pathDistances[shortestPath])
			{
				shortestPath = i;
			}
		}

		// Return the shortest path along with the distance.
		String pathString = cityList.get(permutations[0][shortestPath]).getName();

		for(int i = 1; i < cityList.size(); i++)
		{
			pathString += " -> " + cityList.get(permutations[i][shortestPath]).getName();
		}

		System.out.println("Shortest Path: " + pathString);
		System.out.println("Distance: " + pathDistances[shortestPath]);

	}

}
