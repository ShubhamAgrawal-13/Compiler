import java.util.Scanner;
import java.util.Stack;
import java.util.Iterator;

class LLParser1
{
	static String[] nonTerminals;
	static String[] terminals;
	static String[][] table;
	static int n;
	static Stack<String> stack = new Stack<String>();

	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\t-------- null is represented by ^ --------\n\t(Note: Please also Enter $ in terminals)");
		//System.out.println("Enter terminal Symbols : ");
		String ter = "+ * ( ) i $";
		terminals = ter.split(" ");
		//System.out.println("Enter non-terminal Symbols : \n(Note: First non-terminal should be Starting Symbol)");
		String nonTer = "E X T Y F";
		nonTerminals = nonTer.split(" ");
		
		table = new String[nonTerminals.length][terminals.length];

		table[0][0] = "empty";
		table[0][0] = "empty";
		table[0][2] = "E->TX";
		table[0][3] = "empty";
		table[0][4] = "E->TX";
		table[0][5] = "empty";

		table[1][0] = "X->+TX";
		table[1][1] = "empty";
		table[1][2] = "empty";
		table[1][3] = "X->^";
		table[1][4] = "empty";
		table[1][5] = "X->^";

		table[2][0] = "empty";
		table[2][1] = "empty";
		table[2][2] = "T->FY";
		table[2][3] = "empty";
		table[2][4] = "T->FY";
		table[2][5] = "empty";

		table[3][0] = "Y->^";
		table[3][1] = "Y->*FY";
		table[3][2] = "empty";
		table[3][3] = "Y->^";
		table[3][4] = "empty";
		table[3][5] = "Y->^";

		table[4][0] = "empty";
		table[4][1] = "empty";
		table[4][2] = "F->(E)";
		table[4][3] = "empty";
		table[4][4] = "F->i";
		table[4][5] = "empty";


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