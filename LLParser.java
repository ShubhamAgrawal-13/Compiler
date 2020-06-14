import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

class LLParser
{
	static String[][] productionRules;
	static String[] nonTerminals;
	static String[] terminals;
	static String[][] first;
	static String[][] follow;
	static String[][] table;
	static int n;
	static Stack<String> stack = new Stack<String>();

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
		/*System.out.println("No. of Production rules : ");
		n = sc.nextInt();
		productionRules = new String[n][2];
		
		for(int i=0;i<n;i++)
		{
			System.out.println("Enter production rule "+(i+1)+" : ");
			String temp = sc.next();
			String[] temp1 = temp.split("->");
			productionRules[i] =  temp1;
		}
		
		
		first = new String[nonTerminals.length][];
		follow = new String[nonTerminals.length][];
		
		for(int i=0;i<nonTerminals.length;i++)
		{
			System.out.println("Enter first of "+nonTerminals[i]+" : ");
			String temp = sc.next();
			String[] temp1 = temp.split(",");
			first[i] =  temp1;
		}
		
		for(int i=0;i<nonTerminals.length;i++)
		{
			System.out.println("Enter follow of "+nonTerminals[i]+" : ");
			String temp = sc.next();
			String[] temp1 = temp.split(",");
			follow[i] =  temp1;
		}*/

		table = new String[nonTerminals.length][terminals.length];
		System.out.println("Enter table for LL Parser : \n\n");
		System.out.println("-----------If there is no entry then enter \'empty\' ------------");
		
		for(int i=0;i<nonTerminals.length;i++)
		{
			for(int j=0;j<terminals.length;j++)
			{
				System.out.print("Enter entry in table for table[\'"+nonTerminals[i]+"\']"+"[\'"+terminals[j]+"\'] : ");
				table[i][j]=sc.next();
			}
		}
		
		/*System.out.println("\nThe Production Rules are : ");
		for(int i=0;i<n;i++)
		{
			System.out.println(productionRules[i][0]+" -> "+productionRules[i][1]);
		}
		
		System.out.println("\nFirst Set : ");
		for(int i=0;i<nonTerminals.length;i++)
		{
			System.out.print(nonTerminals[i]+" = { ");
			for(int j=0;j<first[i].length;j++)
			{
				System.out.print(first[i][j]+" ");
			}
			System.out.println("}");
		}
		
		System.out.println("\nFollow Set : ");
		for(int i=0;i<nonTerminals.length;i++)
		{
			System.out.print(nonTerminals[i]+" = { ");
			for(int j=0;j<follow[i].length;j++)
			{
				System.out.print(follow[i][j]+" ");
			}
			System.out.println("}");
		}*/


		System.out.println("\n\t\t\tTable : \n");
		System.out.print("\t");
		for(int j=0;j<terminals.length;j++)
		{
			System.out.print(terminals[j]+"\t");
		}
		System.out.println("\n--------------------------------------------------");
		
		for(int i=0;i<nonTerminals.length;i++)
		{
			System.out.print(nonTerminals[i]+"\t");
			for(int j=0;j<terminals.length;j++)
			{
				System.out.print(table[i][j]+"\t");
			}
			System.out.println("\n--------------------------------------------------");
		}	


		System.out.print("\nEnter any String : ");
		String str = sc.next();
		str=str+"$";

		
		stack.push("$");
		stack.push(nonTerminals[0]);

		if(checkString(str))
		{
			System.out.println("\nIt is a valid String of the given grammar.");
		}
		else
		{
			System.out.println("\nIt is not a valid String of the given grammar.");
		}
	}

	public static boolean checkString(String str)
	{
		for(int i=0;i<str.length();i++)
		{
			while(true)
			{
				String tempp=stack.peek();
				char t=str.charAt(i);
				String tt=t+"";
				int j = findIndexNT(tempp);
				int k = findIndexT(tt);
				System.out.println(j+" "+k);

				Iterator<String> itr = stack.iterator();
				while(itr.hasNext())
				{
					System.out.print(itr.next());	
				}
				System.out.print("\t");
				for(int b=i;b<str.length();b++)
				{
					System.out.print(str.charAt(b));
				}
				System.out.println();

				if(stack.peek().equals(tt))
				{
					stack.pop();
					break;
				}
				
				
				String temp = table[j][k];
				
				if(temp.equals("empty"))
				{
					return false;
				}

				String[] temp1 = temp.split("->");
				
				if(temp1[1].equals("^"))
				{
					stack.pop();
				}
				else
				{
					stack.pop();
					for(int c=temp1[1].length()-1;c>=0;c--)
					{
						stack.push(temp1[1].charAt(c)+"");
					}
				}
				if(stack.peek().equals(tt))
				{
					stack.pop();
					break;
				}
			}

		}
		if(stack.empty())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static int findIndexNT(String s)
	{
		int i=0;
		for (;i<nonTerminals.length;i++)
		{
			if(s.equals(nonTerminals[i]))
			{
				break;
			}	
		}
		return i;
	}

	public static int findIndexT(String s)
	{
		int i=0;
		for (;i<terminals.length;i++)
		{
			if(s.equals(terminals[i]))
			{
				break;
			}	
		}
		return i;
	}
}