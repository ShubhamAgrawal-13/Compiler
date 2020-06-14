import java.util.Scanner;

class FirstCFG
{
	static String[][] productionRules;
	static String[] nonTerminals;
	static String[] terminals;
	static int n;
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("-------- null is represented by ^ --------\n");
		System.out.println("Enter terminal Symbols : ");
		String ter = sc.nextLine();
		terminals = ter.split(" ");
		System.out.println("Enter non-terminal Symbols : ");
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
		System.out.println("The Production Rules are : ");
		for(int i=0;i<n;i++)
		{
			System.out.println(productionRules[i][0]+" -> "+productionRules[i][1]);
		}
		String[] First = new String[nonTerminals.length];
		for(int i=0;i<nonTerminals.length;i++)
		{
			First[i] = findFirst(nonTerminals[i],null);
		}
		for(int i=0;i<First.length;i++)
		{
			System.out.println("First("+nonTerminals[i]+") = { "+First[i]+" }");
		}
	}
	public static String findFirst(String s,String y)
	{
		String first = "";
		String second=s;
		//System.out.println("hello");
		for(int i=0;i<n;i++)
		{
			//System.out.println("hello1");
			if(productionRules[i][0].equals(s))
			{
				//System.out.println(s+"---");
				String k=productionRules[i][1];
				for(int j=0;j<k.length();j++)
				{
					String f="";
					char cha = k.charAt(j);
					String ch = ""+cha;
					boolean b1=false,b2=false;
					for(int c=0;c<terminals.length;c++)
					{
						if(terminals[c].equals(ch))
						{
							b1=true;
							break;
						}
					}
					for(int c=0;c<nonTerminals.length;c++)
					{
						if(nonTerminals[c].equals(ch))
						{
							b2=true;
							break;
						}
					}
					if(b1)
					{
						first=first+ch+" ";
						break;
					}
					else if(ch.equals("^"))
					{
						first=first+"^"+" ";
						break;
					}
					else if(b2)
					{
						if(!s.equals(y))
						{
							f=findFirst(ch,second);
						}
						boolean b=false;
						String[] ss=f.split(" ");
						for(int c=0;c<ss.length;c++)
						{
							if(ss[c].equals("^"))
							{
								b=true;
							}
							else
							{
								first=first+ss[c]+" ";
							}
						}
						if(!b)
							 break;
					}
				}
			}
		}
		return first;
	}
	
}

/*C:\Users\Admin\Desktop\Fulera>java FirstCFG
-------- null is represented by ^ --------

Enter terminal Symbols :
+ * ( ) i
Enter non-terminal Symbols :
E X T Y F
No. of Production rules :
8
Enter production rule 1 :
E->TX
Enter production rule 2 :
X->+TX
Enter production rule 3 :
X->^
Enter production rule 4 :
T->FY
Enter production rule 5 :
Y->*FY
Enter production rule 6 :
Y->^
Enter production rule 7 :
F->(E)
Enter production rule 8 :
F->i
The Production Rules are :
E -> TX
X -> +TX
X -> ^
T -> FY
Y -> *FY
Y -> ^
F -> (E)
F -> i
E---
T---
F---
F---
X---
X---
T---
F---
F---
Y---
Y---
F---
F---
E   TX
X   +TX
X   ^
T   FY
Y   *FY
Y   ^
F   (E)
F   i
First(E) = { ( i  }
First(X) = { + ^ }
First(T) = { ( i  }
First(Y) = { * ^ }
First(F) = { ( i  }

C:\Users\Admin\Desktop\Fulera>java FirstCFG
-------- null is represented by ^ --------

Enter terminal Symbols :
( ) a
Enter non-terminal Symbols :
S L O
No. of Production rules :
5
Enter production rule 1 :
S->(L)
Enter production rule 2 :
S->a
Enter production rule 3 :
L->SO
Enter production rule 4 :
O->^
Enter production rule 5 :
O->SO
The Production Rules are :
S -> (L)
S -> a
L -> SO
O -> ^
O -> SO
S---
S---
L---
S---
S---
O---
O---
S---
S---
S   (L)
S   a
L   SO
O   ^
O   SO
First(S) = { ( a  }
First(L) = { ( a  }
First(O) = { ^( a  }
*/