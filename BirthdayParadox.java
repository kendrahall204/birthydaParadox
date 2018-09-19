//Kendra Hall 163548

/*This program tests out the birthday paradox by generating 10 sets
of 23 randomly chosen birthdays, each set it checked to see if it has
any matching birthday, everytime a match it found it is stored and
added to the total to then be displayed when the program finishes. The
program also display the birthdates chosen as well. */
import java.util.*;
public class BirthdayParadox
{
	private Random random = new Random();
	private String monthName = "";
	private int monthNumber;

	public static void main (String [] args)
	{
		BirthdayParadox birthday = new BirthdayParadox();
		ArrayList<String> birthdayArray = new ArrayList<String>();
		System.out.println("Generating random birthdays....");
		String date = "";
		int totalNumber = 0;

		//For loop used to generate the 10 sets of 23 birthdays chosen
		for(int i = 0; i < 10; i++)
		{
			/*Paradox number is reset after each loop so we can calculate how
			many time the paradox happens in each set. **/
			int paradoxNumber = 0;
			//Array is cleared after each loop
			birthdayArray.clear();

			/*Nested loop runs 23 times, it calls the getMonthDay method and getMonthName
			method and stores them in date, that String is then added into the arraylist */
			for (int j = 0; j < 23; j ++)
			{
				date = (birthday.getMonthName()+" "+birthday.getMonthDay());
				birthdayArray.add(date);
			}

			//After the array is filled we call the getBirthdayParadox method and pass it the array
			paradoxNumber = birthday.getBirthdayParadox(birthdayArray);
			//Accumulator to store the total times the paradox occurs
			totalNumber+= paradoxNumber;

			//Prints out the birthday array so we can confirm the paradox test was correct
			System.out.println(birthdayArray);

			//Prints out after the filled array is displayed to show how many times the paradox occured
			System.out.println("Set: "+(i+1)+" has "+(paradoxNumber)+" identical birthdays\n");
		}
		//Print out for the total number of times the paradox occured.
		System.out.println("Out of all 10 sets the birthday paradox happened "+totalNumber+" times");
	}

	/** Method takes the String array list and compares the elements to check for matching birthdays
	it then stores that number in an accumulator*/
	public int getBirthdayParadox (ArrayList <String> array)
	{
		int birthdayParadox = 0;
		//We use a nested for loop to be able to compare the arrayList to itself
		//We use array.size - 1 so we don't compare the last elements to each other.
		for (int i = 0; i < array.size() - 1; i++)
		{
			//J is initialized to i + 1 as we don't want to compare elements to themselves
			for (int j = i + 1; j <array.size(); j++)
			{
				//The .contains method is called to compare the array elements
				if (array.get(i).contains(array.get(j)))
					//Counter for the paradox
					birthdayParadox++;
			}
		}
		return birthdayParadox;
	}
	//Method stores the month names in an array, then uses a random object to randomly choose the month name
	public String getMonthName()
	{
		String[] nameArray = {"January", "February", "March", "April", "May", "June", "July", "August",
		                      "September", "October", "November", "December"};
		int monthNumber = random.nextInt(11);
		//We use the randomly chosen int to call the correct String from within the array
		monthName = nameArray[monthNumber];
		return monthName;
	}
	//Method checks the month name and uses the correct limit of numbers to call from
	//This prevents a date such as "February 31" from appearing
	//We also are skipping leap years
	public int getMonthDay()
	{
		//We use +1 as the random object number scale starts at 0
		if (monthName == "September" || monthName == "April" || monthName == "June" || monthName == "November")
			monthNumber = random.nextInt(30) + 1;
		else if (monthName == "February")
			monthNumber = random.nextInt(28) + 1;
		else monthNumber = random.nextInt(31) + 1;

		return monthNumber;
	}

}
