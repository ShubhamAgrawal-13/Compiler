import java.util.Scanner;
import java.util.Map;
import java.util.Set;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;

class ThreeAddrCode 
{
	static int count = 0;

	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("\n****Each symbol should be separated by space delimiter****\n");
		System.out.print("Enter the Expression : ");
		String expr1 = sc.nextLine();
		String[] expr2 = expr1.split(" ");
		List<String> expr = new ArrayList<String>();
		for(int i=0;i<expr2.length;i++)
		{
			expr.add(expr2[i]);
		}
		System.out.println("\nThree Address Code for the following expression is : \n");

		convertToThreeAddrCode(expr);

	}
	public static void convertToThreeAddrCode(List<String> expr)
	{
		Map<String,String> map = new LinkedHashMap<String,String>();

		int pLeft = -1;
		int pRight = -1;
		int plus = -1;
		int minus = -1;
		int multiply = -1;
		int divide = -1;
		int assignmentOp = -1;

		while(expr.size() > 5)
		{
				pLeft = expr.indexOf("(");
				pRight = expr.indexOf(")");
				plus = expr.indexOf("+");
				minus = expr.indexOf("-");
				multiply = expr.indexOf("*");
				divide = expr.indexOf("/");
				int op=-1;


				if(pLeft != -1 && pRight != -1 && pLeft < pRight)
				{
					List<String> l = sublist(expr,pLeft,pRight);
					convertToThreeAddrCode(l);
				}
				else if(divide!=-1 || multiply!=-1)
				{
					if(divide!=-1 && multiply!=-1)
					{
						op = divide<multiply?divide:multiply;
					}
					else if(divide!=-1)
					{
						op = divide;
					}
					else
					{
						op = multiply;
					}
				}
				else if(plus!=-1 || minus!=-1)
				{
					if(plus!=-1 && minus!=-1)
					{
						op = plus<minus?plus:minus;
					}
					else if(plus!=-1)
					{
						op = plus;
					}
					else
					{
						op = minus;
					}
				}
				if(op!=-1)
				{
					++count;
					String str = subString(expr,op-1,op+2);
					map.put("T"+count,str);
					//System.out.println(str);
					replace(expr,op-1,op+2,"T"+count);
				}

		}

		Set<Map.Entry<String,String>> set= map.entrySet();
		for(Map.Entry<String,String> entry : set)
		{
			System.out.println(entry.getKey()+"="+entry.getValue());
		}
		System.out.println(subString(expr,0,expr.size()));
	}

	public static String subString(List<String> expr,int l,int r)
	{
		String str="";
		for(int i=l;i<r;i++)
		{
			str=str+expr.get(i);
		}
		return str;
	}
	public static void replace(List<String> expr,int l,int r,String temp)
	{
		int t = r-l;
		for(int i=0;i<t;i++)
		{
			expr.remove(l);
		}
		expr.add(l,temp);
	}
	public static List<String> sublist(List<String> expr,int l,int r)
	{
		List<String> l1 = new ArrayList<String>(); 
		String str = "T"+(++count);
		l1.add(str);
		l1.add("=");
		for(int i=l+1;i<r;i++)
		{
			l1.add(expr.get(i));
		}
		//System.out.println(str);
		int t = r-l+1;
		for(int i=0;i<t;i++)
		{
			expr.remove(l);
		}
		expr.add(l,str);
		return l1;
	}

}
