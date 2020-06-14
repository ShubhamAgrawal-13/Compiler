
import java.util.*;
import java.io.*;

class CodeOptimizer 
{
	public static void main(String[] args) 
	{
		System.out.print("Enter the C code file name which is to be Optimized : ");
		Scanner sc = new Scanner(System.in);
		String path = sc.next();

		ArrayList<String> list = new ArrayList<String>();

		File file = null;
		FileReader fir = null;
		BufferedReader br = null;

		System.out.println("\n Code is : \n");
		try{
		    file = new File(path);
			fir = new FileReader(file);
			br = new BufferedReader(fir);
			String line="";
			
			int count=0;
			while((line=br.readLine())!=null)
			{
				System.out.println((++count)+". "+line);
				if(!list.contains(line))
				{
					list.add(line);
				}
			}
			
			
			System.out.println("\n Optimized Code is : \n");
			Iterator itr = list.iterator();
			count=0;
			while(itr.hasNext())
			{
				System.out.println((++count)+". "+itr.next());
			}

		}
		catch(Exception e )
		{
			System.out.println(e);
		}
		System.out.println("Hello World!");
	}
}
