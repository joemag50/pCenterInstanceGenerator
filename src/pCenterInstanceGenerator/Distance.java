package pCenterInstanceGenerator;

public class Distance {
	public static double Euclidean(Double[] node0, Double[] node1)
	{
		double x = node0[0] - node1[0];
		double y = node0[1] - node1[1];
		double euDistance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
		return euDistance;
	}
}
