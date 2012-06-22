import java.io.*;
import java.util.StringTokenizer;


public class dbase {
	static String currentdb="master";
	 static void createdb(String dbname)
	{
		//Create a folder(Database name) inside D:\dbase
		File db = new File("D:\\dbase/"+dbname);
        db.mkdir();
        System.out.print("Database Created :"+dbname);
	}
	
	static void createtable(String path)
	{
		File table = new File(path);
		try {
			table.createNewFile();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main (String[] argv) throws IOException
	{
		String input; //For storing command string
		//String title2 = null;
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input=in.readLine();
		StringTokenizer command = new StringTokenizer(input," "); // To read input command word by word
		String title = command.nextToken(); //First word in command string
		switch(title){
		case "create":
			//System.out.println("CREATE");
			//title2=command.nextToken();//Next word in command 
			switch(command.nextToken()){
			case "db":
				System.out.println("CREATE Database");
				createdb(command.nextToken());
				break;
			case "table":
				System.out.println("CREATE Table");
				String path="D:\\dbase/"+currentdb+"/"+command.nextToken()+".txt";
				createtable(path);
				break;
			default:
				System.out.println("create : Invalid Command");
			}
			break;
		case "use":
			//title2=command.nextToken();
			switch(command.nextToken()){
			case "db":
				currentdb=command.nextToken();
				File dir=new File("D:\\dbase/"+currentdb);
				if(dir.exists())
				{
					System.out.println("Database Selected:"+currentdb);
				}
				else
				{
					System.out.println("Database doesnot exists:"+currentdb);
				}	
				break;
			default:
				System.out.println("use : Invalid Command");
			}
			break;
		default:
			System.out.println("Invalid Command");
		
		}
		
	}

}
