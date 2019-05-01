package pCenterInstanceGenerator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Generator
{
	String Sufix;
	int Instances;
	double Clients;
	double Percentage_facilities;
	double Percentage_P;
	double X_Y;
	double Sites;
	double P;
	
	ArrayList<Double[]> Clients_Positions;
	ArrayList<Double[]> Sites_Positions;
	ArrayList<Double[]> Clients_Sites_Distances;
	ArrayList<Double[]> Sites_Distances;
	
	Generator(String sufix, int instances, double clients, double percentage_facilities, double percentage_p, double x_y)
	{
		this.Sufix = sufix;
		this.Instances = instances;
		this.Clients = clients;
		this.Percentage_facilities = percentage_facilities;
		this.Percentage_P = percentage_p;
		this.X_Y = x_y;
		
		Double sites = Clients * Percentage_facilities;
		this.Sites = sites.intValue();
		Double p = sites * Percentage_P;
		this.P = p.intValue();
		System.out.println("" + this.Sites);
		System.out.println("" + this.P);
		System.out.println("");
	}
	
	public void GenerateFiles()
	{
		Random r = new Random();
		for (int i = 0; i < Instances; i++)
		{
			Clients_Positions = new ArrayList<Double[]>();
			for (int j = 0; j < Clients; j++)
			{
				double RandX = ThreadLocalRandom.current().nextDouble(0, X_Y);
				double RandY = ThreadLocalRandom.current().nextDouble(0, X_Y);
				
				Double[] cp = new Double[] { RandX, RandY };
				Clients_Positions.add(cp);
			}
			
			Sites_Positions = new ArrayList<Double[]>();
			for (int j = 0; j < Sites; j++)
			{
				double RandX = ThreadLocalRandom.current().nextDouble(0, X_Y);
				double RandY = ThreadLocalRandom.current().nextDouble(0, X_Y);
				
				Double[] sp = new Double[] { RandX, RandY };
				Sites_Positions.add(sp);
			}
			
			Clients_Sites_Distances = new ArrayList<Double[]>();
			for (int j = 0; j < Clients; j++)
			{
				Double sites = Sites;
				Double[] e = new Double[sites.intValue()];
				
				for (int k = 0; k < Sites; k++)
				{
					Double[] node0 = Clients_Positions.get(j);
					Double[] node1 = Sites_Positions.get(k);
					
					double distance = Distance.Euclidean(node0, node1);
					
					e[k] = distance;
				}
				Clients_Sites_Distances.add(e);
			}
			
			Sites_Distances = new ArrayList<Double[]>();
			for (int j = 0; j < Sites; j++)
			{
				Double sites = Sites;
				Double[] e = new Double[sites.intValue()];
				
				for (int k = 0; k < Sites; k++)
				{
					Double[] node0 = Sites_Positions.get(j);
					Double[] node1 = Sites_Positions.get(k);
					
					double distance = Distance.Euclidean(node0, node1);
					
					e[k] = distance;
				}
				Sites_Distances.add(e);
			}
			
			File file = new File(String.format("/tmp/%s-%s.dat", this.Sufix, i)); 
			try {
				if (file.createNewFile())
				{
					System.out.println(String.format("File is created %s!", file.getName()));
				} else {
					System.out.println("File already exists.");
				}
				
				FileWriter writer = new FileWriter(file);
				
				writer.write(String.format("%s\n\n", this.P));
				
				for (int j = 0; j < Clients; j++)
				{
					for (int k = 0; k < Sites; k++ )
					{
						writer.write(String.format("%.2f ", Clients_Sites_Distances.get(j)[k]));
					}
					writer.write("\n");
				}
				
				writer.write(String.format("\n"));
				for (int j = 0; j < Sites; j++)
				{
					for (int k = 0; k < Sites; k++ )
					{
						writer.write(String.format("%.2f ", Sites_Distances.get(j)[k]));
					}
					writer.write("\n");
				}
				
				writer.write(String.format("\n"));
				for (int j = 0; j < Clients; j++)
				{
					writer.write(String.format("%.2f %.2f", Clients_Positions.get(j)[0], Clients_Positions.get(j)[1]  ));
					writer.write("\n");
				}
				
				writer.write(String.format("\n"));
				for (int j = 0; j < Sites; j++)
				{
					writer.write(String.format("%.2f %.2f", Sites_Positions.get(j)[0], Sites_Positions.get(j)[1]  ));
					writer.write("\n");
				}
				writer.close();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
