class CRC 
{
	public static void main(String[] args) 
	{
		System.out.println("Enter data bits : ");
		Scanner sc = new Scanner(System.in);
		String data=sc.next();
		System.out.println("Enter generator polynomial bits : ");
		String poly= sc.next();
		int dl=data.length();
		int pl=poly.length();
		char[] dataBits=data.toCharArray();
		char[] polyBits=poly.toCharArray();
		char[] zero=new char[pl];
		char[] remainder=new char[dl+pl-1];
		for(int i=0;i<dl;i++)
			remainder[i]=dataBits[i];
		for(int i=dl;i<dl+pl-1;i++)
			remainder[i]=dataBits[i];
		for(int i=0;i<dl;i++)
		{
			for(int j=0;j<pl;j++)
			{
				int msb=remainder[i];
				if(msb == 0)
					remainder=xor(remainder[i+j],);

			}
		}

	}
	public staic char xor(char a,char b)
	{
		if(a == b)
			return '0';
		else
			return '1';
	}
}
