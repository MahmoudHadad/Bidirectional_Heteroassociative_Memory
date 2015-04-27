package BidirectionalHeteroassociativeMemory;

import java.util.ArrayList;

public class Heteroassociative_Memory {

	static public int m;
	static public int n;
	public static ArrayList<int []>Xs;
	public static ArrayList<int []>Ys;
	
	static public int[][] memory;
	
	public static void initiateMemory ( )
	{
		memory = new int [n][m];
		
		for (int i = 0; i < Xs.size(); i++) {
			
			int [][]xi_Mult_yi = Matrix_Operations.Multiply_Vector_Vector(Ys.get(i), Xs.get(i));
			Matrix_Operations.Addition(memory, xi_Mult_yi);
			
		}
		System.out.println("Memory");
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				System.out.print(memory[i][j] + " ");
			}
			System.out.println();
		}
		
	}
	
	/////////////////////////////////
	public static void reCallX0(int []x0)
	{
		// Y_  <-  W*x0
		int [][]y_ = Matrix_Operations.Multiply_Matrix_Vector(memory, x0);
		y_ = Matrix_Operations.convertToHorizontalVector(y_);
		System.out.println("Y_");
		Matrix_Operations.print(y_);
		
		boolean zeroExists = false;
		for(int i =0 ; i <y_[0].length; i++)
		{
			if(y_[0][i]>0)
				y_[0][i] = 1;
			else if(y_[0][i] <0)
				y_[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []ytemp = getMinDistance(y_, Ys);
			
			for(int i =0 ; i <y_[0].length; i++)
			{
				if(y_[0][i] == 0)
					y_[0][i] = ytemp[i];
			}
		}
		
		System.out.println("Y_ after Hebb's rule");
		Matrix_Operations.print(y_);
		/////////
		
		// Y_ * w -> x_
		int [][]x_ = Matrix_Operations.Multiplication(y_, memory);
		zeroExists = false;
		for(int i =0 ; i <x_[0].length; i++)
		{
			if(x_[0][i]>0)
				x_[0][i] = 1;
			else if(x_[0][i] <0)
				x_[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []xtemp = getMinDistance(x_, Xs);
			
			for(int i =0 ; i <x_[0].length; i++)
			{
				if(x_[0][i] == 0)
					x_[0][i] = xtemp[i];
			}
		}
		
		x_ = Matrix_Operations.convertToVerticalVector(x_);
		System.out.println("x_");
		Matrix_Operations.print(x_);
		
		
		/////////////////////////////////
		// Y__
		int [][]y__ = Matrix_Operations.Multiplication(memory, x_);
		y__ = Matrix_Operations.convertToHorizontalVector(y__);
		System.out.println("Y__");
		Matrix_Operations.print(y__);
		
		zeroExists = false;
		for(int i =0 ; i <y__[0].length; i++)
		{
			if(y__[0][i]>0)
				y__[0][i] = 1;
			else if(y__[0][i] <0)
				y__[0][i] = -1;
			else 
				zeroExists = true;
		}
		// solve 0 problem by getting Y with min distance
		if(zeroExists)
		{
			int []ytemp = getMinDistance(y__, Ys);
			
			for(int i =0 ; i <y__[0].length; i++)
			{
				if(y__[0][i] == 0)
					y__[0][i] = ytemp[i];
			}
		}
		
		System.out.println("Y__ after Hebb's rule");
		Matrix_Operations.print(y__);
		///////////////////////////////////////////////////////
		// Y__ * w -> x__
				int [][]x__ = Matrix_Operations.Multiplication(y__, memory);
				zeroExists = false;
				for(int i =0 ; i <x__[0].length; i++)
				{
					if(x__[0][i]>0)
						x__[0][i] = 1;
					else if(x__[0][i] <0)
						x__[0][i] = -1;
					else 
						zeroExists = true;
				}
				// solve 0 problem by getting Y with min distance
				if(zeroExists)
				{
					int []xtemp = getMinDistance(x__, Xs);
					
					for(int i =0 ; i <x__[0].length; i++)
					{
						if(x__[0][i] == 0)
							x__[0][i] = xtemp[i];
					}
				}
				
				System.out.println("x__");
				Matrix_Operations.print(x__);
				
				if(Matrix_Operations.areEqual(x_, Matrix_Operations.convertToVerticalVector(x__)) &&
						Matrix_Operations.areEqual(y_, y__))
				{
					System.out.println("Resonanse Occurred ");
					int []x = getMinDistance(x__, Xs);
					int []y = getMinDistance(y__, Ys);
					
					System.out.println("X = ");
					print(x);
					System.out.println("Y = ");
					print(y);
					
				}
				else{
					System.out.println("No resonance Occurred");
				}
		
	}
	
	
	/////////////////////////////////////////
	private static int hamingDistance(int []h1, int []h2, boolean[]complement){
		int counter = 0;
		if(h1.length != h2.length)
			return -1;
		complement [0] = false;
		for (int i = 0; i < h2.length; i++) {
			if(h1[i] != h2[i])
				counter++;
		}
		if(counter > h1.length/2)
		{
			counter -= h1.length/2;
			complement[0] = true;
		}
		
		return counter;
	}
	/////////////////
	private static int[]  getMinDistance(int []x, ArrayList<int []>arr) {
		boolean []complement = new boolean[1];
		
		int min = hamingDistance(x, arr.get(0), complement);
		int indx = 0;
		boolean c = complement[0];
		
		for (int i = 1; i < arr.size(); i++) {
			int h = hamingDistance(x, arr.get(i), complement);
			if(h < min)
			{
				min = h;
				indx = i;
				c = complement[0];
			}
		}
		if(!c)
			return arr.get(indx);
		// return complement
		int []xx = arr.get(indx);
		for (int i = 0; i < xx.length; i++) {
			xx[i]*=-1;
		}
		return xx;
	}
	///////////////////////////////////////
	private static int[] getMinDistance(int [][]x, ArrayList<int []>arr) {
		boolean []complement = new boolean[1];
		
		int min = hamingDistance(x[0], arr.get(0), complement);
		int indx = 0;
		boolean c = complement[0];
		
		for (int i = 1; i < arr.size(); i++) {
			int h = hamingDistance(x[0], arr.get(i), complement);
			if(h < min)
			{
				min = h;
				indx = i;
				c = complement[0];
			}
		}
		if(!c)
			return arr.get(indx);
		// return complement
		int []xx = arr.get(indx);
		for (int i = 0; i < xx.length; i++) {
			xx[i]*=-1;
		}
		return xx;
	}
	//////////////////////////////////////////////////////////
	private static void print (int []x)
	{
		for (int i = 0; i < x.length; i++) {
			System.out.print(x[i] + " ");
		}
		System.out.println();
	}
	/////////////////////////////////
	public static void viewEnergies()
	{
		System.out.println("Energy ");
		System.out.println(calcEnergy());
		
		System.out.println("Computational Energy");
		calcComputationalEnergy();
		
	}
	////////////////////////////////
	public static int calcEnergy()
	{
		int e = 0;
		for (int i = 0; i < memory.length; i++) {
			for (int j = 0; j < memory[0].length; j++) {
				e += (Math.abs(memory[i][j]));
			}	
		}
		return -e;
	}
	///////////
	public static void calcComputationalEnergy()
	{
		int e =0;
		
		for (int i = 0; i < Xs.size(); i++) {
			
			int [][]E = Matrix_Operations.Multiply_Vector_Matrix(Ys.get(i), memory);
			E = Matrix_Operations.Multiply_Matrix_Vector(E, Xs.get(i)); 
			
			//System.out.println(E.length + " * " + E[0].length);
			System.out.println("For X"+(i+1) +" Y"+ (i+1) + " Energy = " + -1*E[0][0]);
			
		}
		
	}
	
}
