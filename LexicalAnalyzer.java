import java.io.*;
import java.util.Scanner;
import java.util.regex.*;

class LexicalAnalyzer
{
	static String[] keywords = {"auto","break","case","const","char","continue","switch","for","while","do","int","double","float","if","else","default","enum","extern","goto","long","register","return","short","signed","sizeof","static","struct","typedef","union","unsigned","void","volatile"};
	static FileReader fr=null;
	static String[] operators={"+","-","*","/","=",".",",",":",";","{","}","[","]","(",")","%","|","!","&","^","?",">","<","~"};
	static int keyword=0;
	static int operator=0;
	static int literal=0;
	static int identifier=0;
	static String kw="";
	static String op="";
	static String id="";
	static String li="";
	public static void main(String[] args)
	{
			Scanner sc = new Scanner(System.in);
			System.out.print("Enter the path of the file : ");
			String path = sc.next();

			
			try
			{
				fr = new FileReader(path);
				BufferedReader br=new BufferedReader(fr);
				String line=null;
				while((line=br.readLine())!=null)
				{
					String[] words=line.split(" ");
					for(String word : words)
					{
						word=word.trim();
						if(isKeyword(word))
						{
							kw+=word+" ";
							keyword++;
						}
						else
						{
							checkOperator(word);
							//checkLiterals(word);
							checkIdentifier(word);
							checkLiteral(word);
						}
					}
				}
				fr.close();
			}
			catch(IOException e)
			{
				System.out.println(e);
			} 
			System.out.println("Keywords : "+keyword+" { "+kw+"}");
			System.out.println("Operators : "+operator+" { "+op+"}");
			System.out.println("Identifiers : "+identifier+" { "+id+"}");
			System.out.println("Literals : "+literal+" { "+li+"}");
	}
	public static boolean isKeyword(String word)
	{
		for(String s : keywords)
		{
			if(s.equals(word))
			{
				return true;
			}
		}
		return false;
	}
	public static void checkOperator(String word)
	{
		for(int i=0;i<word.length();i++)
		{
			char chh = word.charAt(i);
			String ch=""+chh;
			for(String s : operators)
			{
				if(s.equals(ch))
				{
					op+=ch+" ";
					operator++;
					break;
				}
			}
		}
	}

	public static void checkIdentifier(String word)
	{
		String temp="";
		l1:for(int i=0;i<word.length();)
		{
			char chh = word.charAt(i);
			for(int j=65;j<=90;j++)
			{
				if(chh==j)
				{
					i++;
					temp=temp+chh;
					continue l1;
				}
			}
			
			for(int j=97;j<=122;j++)
			{
				if(chh==j)
				{
					i++;
					temp=temp+chh;
					//System.out.println(temp);
					continue l1;
				}
			}
			if(!temp.equals(""))
			{
				for(int j=48;j<=57;j++)
				{
					if(chh==j)
					{
						i++;
						temp=temp+chh;
						continue l1;
					}
				}
			}
			if(chh==95)
			{
				i++;
				temp=temp+chh;
				continue l1;
			}
			i++;
			if(!temp.equals(""))
			{
				id=id+temp+" ";
				identifier++;
				//System.out.println(temp);
				temp="";
			}
			//System.out.println("hello");
		}
	}

	public static void checkLiteral(String word)
	{
		String temp="";
		boolean bbb=false;
		l2:for(int i=0;i<word.length();)
		{
			char chh=word.charAt(i);
			String ch=""+chh;
			boolean bb=false;
			if(chh==46)
			{
				bb=true;
			}
			if(!bb)
			{
				for(String s : operators)
				{
					if(s.equals(ch))
					{
						i++;
						bbb = !bbb;
						if(bbb==false)
						{
							if(!temp.equals(""))
							{
								li=li+temp+" ";
								literal++;
								//System.out.println(temp);
								temp="";
							}
						}
						continue l2;
					}
				}
			}
			if(bbb)
			{
				for(int j=48;j<=57;j++)
				{
					if(chh==j)
					{
						i++;
						temp=temp+chh;
						//System.out.println(temp);
						continue l2;
					}
				}
				if(chh==46)
				{
					i++;
					temp=temp+chh;
					continue l2;
				}
			}
			i++;
			if(!temp.equals(""))
			{
				boolean fff=false;
				if(temp.equals("."))
				{
					char ff=word.charAt(i);
					for(int j=48;j<=57;j++)
					{
						if(ff==j)
						{
							fff=true;
							break;
						}
					}
					if(fff==false)
					{
						temp="";
						continue l2;
					}
				}
				li=li+temp+" ";
				literal++;
				//System.out.println(temp);
				temp="";
			}
		}
	}
}
