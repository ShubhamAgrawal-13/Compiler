import java.util.Scanner;

class BitStuffing
{
	public static void main(String[] args)
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter data bits : ");
		String dataBits = sc.next();
		String codeWord = "";
		int n = 5;
		int count = 0;
		
	
		for(int i = 0 ; i < dataBits.length() ; i++)
		{
			if(dataBits.charAt(i) != '1' && dataBits.charAt(i) != '0')
			{
				System.out.println("Enter only 1 or 0");
				return;
			}
			if(dataBits.charAt(i) == '1')
			{
				count++;
				codeWord += dataBits.charAt(i);
			}
			else
			{
				codeWord += dataBits.charAt(i);
				count =0;
			}
			if(count == n)
			{
				codeWord+="0";
				count=0;
			}
			
		}
		System.out.println("Sending message Code Word : " +"01111110"+codeWord+"01111110");

		String receivedData = "";
		for(int i = 0; i < codeWord.length(); i++)
		{

			if(codeWord.charAt(i) == '1')
			{
				receivedData +=codeWord.charAt(i);
				count++;
			}
			else
			{
				receivedData +=codeWord.charAt(i);
				count =0;
			}
			if(count == n)
			{
				i++;
				count=0;
			}
			
		}
		System.out.println("Received message : " + receivedData);

		
	}
}