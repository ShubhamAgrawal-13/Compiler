import java.util.Scanner;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

class OperatorPrecedence 
{
	static String[] nonTerminals;
	static String[] terminals;
	static String[][] productionRules;
	static int n;
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\t-------- null is represented by ^ --------\n\t(Note: Please also Enter $ in terminals)");
		System.out.println("Enter terminal Symbols : ");
		String ter = sc.nextLine();
		terminals = ter.split(" ");
		System.out.println("Enter non-terminal Symbols : \n(Note: First non-terminal should be Starting Symbol)");
		String nonTer = sc.nextLine();
		nonTerminals = nonTer.split(" ");
		System.out.println("No. of Production rules : ");
		n = sc.nextInt();
		productionRules = new String[n][2];
		
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter production rule "+(i+1)+" : ");
			String temp = sc.next();
			String[] temp1 = temp.split("->");
			productionRules[i] =  temp1;
		}

		System.out.println("\nThe Production Rules are : ");
		for(int i=0;i<n;i++)
		{
			System.out.println(productionRules[i][0]+" -> "+productionRules[i][1]);
		}
		
		Map<String,Integer> f = new HashMap<String,Integer>();
		Map<String,Integer> g = new HashMap<String,Integer>();


		System.out.print("Enter the Operator Precedence function table : \n\n");
		for(int k=0;k<terminals.length;k++)
		{
			System.out.print("Enter entry for \'f\' corresponding to terminal \'"+ terminals[k] +"\' : ");
			f.put(terminals[k],sc.nextInt());
		}
		System.out.println();
		for(int k=0;k<terminals.length;k++)
		{
			System.out.print("Enter entry for \'g\' corresponding to terminal \'"+ terminals[k] +"\' : ");
			g.put(terminals[k],sc.nextInt());
		}

		for(int k=0;k<terminals.length;k++)
		{
			System.out.print("\t"+terminals[k]);
		}
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.print("f\t");
		for(int k=0;k<terminals.length;k++)
		{
			System.out.print(f.get(terminals[k])+"\t");
		}
		System.out.println();
		System.out.println("------------------------------------------");
		System.out.print("g\t");
		for(int k=0;k<terminals.length;k++)
		{
			System.out.print(g.get(terminals[k])+"\t");
		}
		System.out.println("\n");

		System.out.print("Enter the String to be parsed : ");
		String str = sc.next();
		str = "$"+str+"$";

		Stack stack = new Stack();

		int l=0,r=0;

		for(int r=1;r<str.length();)
		{
			if(str.charAt(r)!='+','*','i','$')
					continue;
			else if(f.get(""+str.charAt(r)) > g.get(""+str.charAt(r)))
			{
				str = new StringBuffer(str).insert(r,">").toString();
			}
			else if(f.get(""+str.charAt(r)) < g.get(""+str.charAt(r)))
			{
				str = new StringBuffer(str).insert(r,"<").toString();
			}
			r=r+2;
			l=r+1;

		}
	
		System.out.println(str);
	}
}
/*
i * + $
4 4 2 0
5 3 1 0
*/