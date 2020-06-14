import java.util.Scanner;

class CRC 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter data bits : ");
		String dataB = sc.next();
		System.out.println("Enter generator polynomial bits : ");
		String polyB = sc.next();
		int d = dataB.length();
		int p = polyB.length();
		char[] data = dataB.toCharArray();
		char[] poly = polyB.toCharArray();
		char[] remainder = new char[d+p-1];
		for (int i=0;i<data.length ;i++ )
			remainder[i]=data[i];
		for (int i=0;i<poly.length-1 ;i++ )
			remainder[data.length+i]='0';
		int l = 0;
		int e = p-1;
		for(;e<remainder.length;l++,e++)
		{
			if(remainder[l]=='0')
				continue;
			else
			{
				//System.out.println("hello");
				for(int i=l,j=0;i<=e;i++,j++)
				{
					remainder[i]=xor(remainder[i],poly[j]);
					//System.out.println("hello");
				}
			}
			
		}
		
		for(int i=0;i<remainder.length-p+1;i++)
		{
			remainder[i]=data[i];
		}
		
		System.out.println("Generated bits are : ");
		for(int i=0;i<remainder.length;i++)
		{
			System.out.print(remainder[i]);
		}
		System.out.println();


		System.out.println("Enter received bits : ");
		String receive = sc.next();
		char[] rece = receive.toCharArray();

		l = 0;
		e = p-1;
		for(;e<rece.length;l++,e++)
		{
			if(rece[l]=='0')
				continue;
			else
			{
				//System.out.println("hello");
				for(int i=l,j=0;i<=e;i++,j++)
				{
					rece[i]=xor(rece[i],poly[j]);
					//System.out.println("hello");
				}
			}
			
		}
		System.out.println("Remainder bits : ");
		int flag=0;
		for(int i=rece.length-p;i<rece.length;i++)
		{
			System.out.print(rece[i]);
			if(rece[i]!='0')
				flag=1;
		}
		System.out.println();
		if(flag==0)
			System.out.println("No Error");
		else
			System.out.println("Error");
		
	}
	public static char xor(char a,char b)
	{
		if(a == b)
			return '0';
		return '1';
	}
}
