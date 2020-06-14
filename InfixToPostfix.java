import java.util.Scanner;

class InfixToPostfix 
{
	static char[] stack=new char[50];
	static int top=-1;
	static String finalx="";
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter any Expression : ");
		String exp=sc.nextLine();
		exp="("+exp+")";
		//System.out.println(exp);
		char[] x = exp.toCharArray();

		
		for(char i:x)
		{
			//System.out.println(i);
			switch(i)
			{
				case '+':
					check(i);
					top++;
					stack[top]='+';
					break;
				case '-':
					check(i);
					top++;
					stack[top]='-';
					break;
				case '*':
					check(i);
					top++;
					stack[top]='*';
					break;
				case '/':
					check(i);
					top++;
					stack[top]='/';
					break;
				case '^':
					check(i);
					top++;
					stack[top]='^';
					break;
				case '(':
					top++;
					stack[top]='(';
					break;
				case ')':
					m1();
					break;
				default:
					finalx += i;
					break;
			}
		}
		System.out.println("\nPostfix Expression is : ");
		System.out.println(finalx);
		
	}
	public static int priority(char i)
	{
		switch(i)
		{
				case '+':
					return 1;
				case '-':
					return 1;	
				case '*':
					return 2;
				case '/':
					return 2;
				case '^':
					return 3;
				case '(':
					return 0;
				default:
					throw new AssertionError("something went wrong");
		}
	}
	public static void check(char i)
	{
		while(priority(stack[top])>=priority(i))
		{
			finalx += stack[top];
			top--;
		}
	}
	public static void m1()
	{
		while(stack[top]!='(')
		{
			finalx += stack[top];
			top--;
		}
		top--;
	}
}
