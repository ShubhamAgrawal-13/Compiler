import java.util.Scanner;

class HammingCode 
{
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the data bits : ");
		String data=sc.next();
		int dataL = data.length();
		int r = calRedundantBits(dataL);
		System.out.println(r);
		char codeWord[] = new char[dataL+r+1];
		char p[] = new char[r+1];
		//System.out.print(codeWord[i]);
		for (int i=1,j=0,k=0;i<=codeWord.length-1;i++ )
		{
			if(i!=(int)Math.pow(2,k))
			{
				codeWord[i]=data.charAt(j++);
			}
			else
			{
				codeWord[i]='p';
				k++;
			}

		}
		for(int i= 1;i<codeWord.length;i++)
				System.out.print(codeWord[i]);
		System.out.println();
		int c=1,k=1;
		int count = 0,red=r;
		while(red!=0)
		{
			int l=c;
			count=0;
			for (;l<codeWord.length ;l+=c )
			{
				for(int y=1;y<=c;y++)
				{
					if(codeWord[l]=='p')
					{
					}
					else if(codeWord[l]=='1')
					{
						count++;
					}
					l++;
					if(l>=codeWord.length)
					{
						break;
					}
				}
			}
			if(count%2==0)
			{
				codeWord[c]='0';
				p[k++]='0';
			}
			else
			{
				codeWord[c]='1';
				p[k++]='1';
			}
			//System.out.println(count);
			c+=c;
			red--;
		}
		for(int i= 1;i<codeWord.length;i++)
				System.out.print(codeWord[i]);
		System.out.println();
		for(int i= 1;i<p.length;i++)
				System.out.print(p[i]);
		System.out.println();

		System.out.println("Enter received bits :");
		String receive='r'+sc.next();
		char[] rece = receive.toCharArray();
		//System.out.println(rece.length);
		

		char[] p1 = new char[r+1];
		c=1;
		k=1;
		count = 0;
		red=r;
		while(red!=0)
		{
			int l=c;
			count=0;
			for (;l<rece.length ;l+=c )
			{
				for(int y=1;y<=c;y++)
				{
				    if(rece[l]=='1')
					{
						count++;
					}
					l++;
					if(l>=rece.length)
					{
						break;
					}
				}
			}
			if(count%2==0)
			{
				p1[k]='0';
			}
			else
			{
				p1[k]='1';
			}
			//System.out.println(count);
			c+=c;
			k++;
			red--;
		}
		String s="";
		for(int i=r;i>=1;i--)
		{
			s=s+p1[i];
		}
		int v=Integer.valueOf(s,2);
		if(v==0)
			System.out.println("No Error");
		else
			System.out.println("Error found at : "+v+" position");

	}
	public static int calRedundantBits(int l)
	{
		int i=0;
		while(true)
		{
			if(Math.pow(2,i)>=i+l+1)
			{
				break;
			}
			i++;
		}
		return i;
	}
}
