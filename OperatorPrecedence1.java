import java.util.Scanner;
import java.util.Stack;
import java.util.Map;
import java.util.HashMap;

class OperatorPrecedence1 
{
	static String[] nonTerminals;
	static String[] terminals;
	static String[][] productionRules;
	static Map<String,Integer> f;
	static Map<String,Integer> g;
	static int n;
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n\t-------- null is represented by ^ --------\n\t(Note: Please also Enter $ in terminals)");
		//System.out.println("Enter terminal Symbols : ");
		//String ter = sc.nextLine();
		terminals = "i * + $".split(" ");
		//System.out.println("Enter non-terminal Symbols : \n(Note: First non-terminal should be Starting Symbol)");
		//String nonTer = sc.nextLine();
		nonTerminals = "E".split(" ");
		//System.out.println("No. of Production rules : ");
		n = 3;
		productionRules = new String[n][2];
		
		
		String[] temp1 = "E->E+E".split("->");
		productionRules[0] =  temp1;

		temp1 = "E->E*E".split("->");
		productionRules[1] =  temp1;

		temp1 = "E->i".split("->");
		productionRules[2] =  temp1;
		

		System.out.println("\nThe Production Rules are : ");
		for(int i=0;i<n;i++)
		{
			System.out.println(productionRules[i][0]+" -> "+productionRules[i][1]);
		}
		
		f = new HashMap<String,Integer>();
		g = new HashMap<String,Integer>();


		System.out.print("Enter the Operator Precedence function table : \n\n");

		f.put(terminals[0],4);
		f.put(terminals[1],4);
		f.put(terminals[2],2);
		f.put(terminals[3],0);
		
		g.put(terminals[0],5);
		g.put(terminals[1],3);
		g.put(terminals[2],1);
		g.put(terminals[3],0);

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
		str=check(str);
		if("$E$".equals(str))
		{
			System.out.println("Given String is parsed by the grammar");
		}
		else
		{
			System.out.println("Given String is not parsed by the grammar");
		}
		
	}

	public static String check(String str)
	{
		int l=0,r=0;
	
		for(r=1;r<str.length();)
		{

			if(str.charAt(r)!='+' || str.charAt(r)!='*' || str.charAt(r)!='i' || str.charAt(r)!='$')
			{
				if(f.get(""+str.charAt(l)) > g.get(""+str.charAt(r)))
				{
					str = new StringBuffer(str).insert(l+1,">").toString();
					System.out.println(str);
				}
				else if(f.get(""+str.charAt(l)) < g.get(""+str.charAt(r)))
				{
					str = new StringBuffer(str).insert(r,"<").toString();
					System.out.println(str);
				}
				else
				{
					//System.out.println(str);
					return str;
				}
			}
			else
			{
				continue;
			}
			//System.out.println(l);
			//System.out.println(r);
			l=r+1;
			r=r+2;
			
		}

		System.out.println(str);

		int lessThan = -1;
		int greaterThan = -1;

		lessThan = str.indexOf('<');
		greaterThan = str.indexOf('>');

		String sub = str.substring(lessThan+1,greaterThan);

		for(int i=0;i<n;i++)
		{
			if(productionRules[i][1].equals(sub))
			{
				str=str.replace("<"+sub+">",productionRules[i][0]);
			}
		}
		System.out.println(str);
		return check1(str);
	}

	public static String check1(String str)
	{
		Stack<Integer> stack = new Stack<Integer>();
		int l=0,r=0;
		
		for(r=1;r<str.length();)
		{

			if(str.charAt(r)=='+' || str.charAt(r)=='*' || str.charAt(r)=='$')
			{
				if(f.get(""+str.charAt(l)) > g.get(""+str.charAt(r)))
				{
					str = new StringBuffer(str).insert(r,">").toString();
					System.out.println(str);
					l=++r;
					r++;
				}
				else if(f.get(""+str.charAt(l)) < g.get(""+str.charAt(r)))
				{
					str = new StringBuffer(str).insert(l+1,"<").toString();
					System.out.println(str);
					l=++r;
					r++;
				}
				else
				{
					//System.out.println(str);
					return str;
				}
			}
			else
			{
				r++;
				continue;
			}
			//System.out.println(l);
			//System.out.println(r);
			
		}

		for(int i=0;i<str.length();i++)
		{
			if(str.charAt(i) == '<')
			{
				stack.push(i);
			}
			if(str.charAt(i) == '>')
			{
				stack.push(i);
				int greaterThan = stack.pop();
				int lessThan = stack.pop();

				String sub = str.substring(lessThan+1,greaterThan);
				boolean flag = false;
				for(int j=0;j<n;j++)
				{
					if(productionRules[j][1].equals(sub))
					{
						flag=true;
						str=str.replace("<"+sub+">",productionRules[j][0]);
					}
				}
				if(!flag)
					return null;
				str=str.replace("<","");
				str=str.replace(">","");
				
				System.out.println(str);
				break;
			}
		}
		return check1(str);
	}
}
/*
i * + $
4 4 2 0
5 3 1 0
*/
