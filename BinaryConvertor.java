class  BinaryConvertor
{
	public static void main(String[] args) 
	{
		int d = Integer.parseInt(args[0]);
		int[] a = new int[32];
		if(d>=0)
		{
				for(int i=31;i>=0;i--)
				{
					a[i]=d%2;
					d/=2;
				}
		}
		else
		{
			d=-d;
			for(int i=31;i>=0;i--)
			{
				a[i]=d%2;
				d/=2;
			}
			int j=31;
			for(;j>=0;j--)
			{
				if(a[j]==1)
					break;
			}
			j--;
			for(;j>=0;j--)
			{
				if(a[j]==1)
					a[j]=0;
				else
					a[j]=1;
			}


		}

		for(int i=0;i<a.length;i++)
		{
			System.out.print(a[i]);
		}
		System.out.println();
	}
}
