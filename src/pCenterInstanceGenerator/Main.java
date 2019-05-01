package pCenterInstanceGenerator;
import java.util.Scanner;

public class Main
{
	Scanner sc = new Scanner(System.in);
	String reader;
	
	public static void main(String[] args)
	{
		Main m = new Main();
		
		System.out.println("Name of sufix:");
		String sufix = m.sc.nextLine();
		
		int instances = m.ReadInt("How many instances?");
		if (instances <= 0)
		{
			System.out.println("Must be greater than 0");
			return;
		}
		
		double clients = m.ReadInt("How many Clients?");
		
		if (clients <= 0)
		{
			System.out.println("Must be greater than 0");
			return;
		}
		
		double facilities = m.ReadDouble("Percentage of facilities?");
		
		if (facilities <= 0)
		{
			System.out.println("Must be greater than 0");
			return;
		}
		
		double p = m.ReadDouble("Percentage of active facilities?");
		
		if (p <= 0)
		{
			System.out.println("Must be greater than 0");
			return;
		}
		
		double x_y = m.ReadInt("Size of map?");
		
		if (x_y <= 0)
		{
			System.out.println("Must be greater than 0");
			return;
		}
		
		Generator g = new Generator(sufix, instances, clients, facilities, p, x_y);
		g.GenerateFiles();
		
		System.out.println("Ready");
	}
	
	public double ReadDouble(String question)
	{
		reader = "";
		while (!this.isDouble(reader))
		{
			System.out.println(question);
			reader = sc.nextLine();
		}
		
		return new Double(reader);
	}
	
	public int ReadInt(String question)
	{
		reader = "";
		while (!this.isInteger(reader))
		{
			System.out.println(question);
			reader = sc.nextLine();
		}
		
		return new Integer(reader);
	}
	
	public boolean isDouble(String str)
	{
		try
		{
			double d = Double.parseDouble(str);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
	
	public boolean isInteger(String str)
	{
		try
		{
			Integer i = Integer.parseInt(str);
		}
		catch(NumberFormatException nfe)
		{
			return false;
		}
		return true;
	}
}
