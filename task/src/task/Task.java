package task;

import java.util.Scanner;

public class Task {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner scanner=new Scanner(System.in);
		
		int n=scanner.nextInt();
		System.out.println("number entered "+n);
		int sum=0;
		while(n>0) {
			int rem=n%10;
			sum=sum+rem;
			n=n/10;
			
		}
		System.out.println("sum of given number: "+sum);
		
		

	}

}
