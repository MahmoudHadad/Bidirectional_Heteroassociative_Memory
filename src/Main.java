import java.util.ArrayList;
import java.util.Scanner;

import BidirectionalHeteroassociativeMemory.Heteroassociative_Memory;
import BidirectionalHeteroassociativeMemory.Matrix_Operations;


public class Main {

	private static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		
		
		int choice =0;
		Heteroassociative_Memory.Xs = new ArrayList<int[]>();
		Heteroassociative_Memory.Ys = new ArrayList<int[]>();
		Heteroassociative_Memory.n =3;
		Heteroassociative_Memory.m =4;
		
		while(true){
			System.out.println("Choose one option");
			System.out.println("1- Add to memory");
			System.out.println("2- Initiate memory");
			System.out.println("3- Recall X0");
			System.out.println("4- Recall Y0");
			System.out.println("5- View Energy");
			
			
			
			choice = sc.nextInt();
			if(choice == 1)
			{
				addToMemory();
			}
			else if(choice == 2)
				Heteroassociative_Memory.initiateMemory();
			
			else if(choice == 3)
			{
				System.out.println("Enter x0 elements");
				int []x0 = new int [Heteroassociative_Memory.m];
				for (int i = 0; i < x0.length; i++) {
					x0[i] = sc.nextInt();
				}
				
				Heteroassociative_Memory.reCallX0(x0);
			}
			
			else if(choice ==4)
			{
				
			}
			else if(choice ==5)
			{
				Heteroassociative_Memory.viewEnergies();
			}
			
		}
			

	}
//////////////////////////////////
	private static void addToMemory(){
		System.out.println("Enter Y elements");
		int []y = new int [Heteroassociative_Memory.n];
		int []x = new int [Heteroassociative_Memory.m];
		
		for (int i = 0; i < y.length; i++) {
			y[i] = sc.nextInt();
		}
		
		System.out.println("Enter x elements");
		
		for (int i = 0; i < x.length; i++) {
			x[i] = sc.nextInt();
		}
		
		Heteroassociative_Memory.Xs.add(x);
		Heteroassociative_Memory.Ys.add(y);
		
	}
	
}
