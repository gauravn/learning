import java.io.*;
import java.lang.Math;
public class First {
	public static void main (String[] argv) throws IOException
	{
		double a;
		String name;
		String rndm;
		a=0;
		System.out.print("Enter Name  :");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		name = in.readLine();
		System.out.print("No. to Mul  :");
		rndm = in.readLine();
		a = Integer.parseInt(rndm); 
		a=a*Math.random();
		System.out.println(name+":"+a);
	}
}
