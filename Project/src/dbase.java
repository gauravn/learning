/*******************************************************************************************************
Database class
Commands to be implemented:
1)create db database_name -    -------------------------------------------------completed
2)use db database_name         -------------------------------------------------completed
3)create table table_name column1 column2 column3...----------------------------completed
	if no database is selected by using "use" command then table 
	is created in default database(master)
4)insert into table_name col1 col2 col3 ....---------------------------
5)select col1 col2...from table_name where col1 value------------------	
6)exit    ----------------------------------------------------------------------completed
********************************************************************************************************/
import java.io.*;
import java.util.StringTokenizer;
public class dbase {
	static String currentdb="master";
	static String row;
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
			File file = new File(path);
			BufferedWriter writer = new BufferedWriter(new FileWriter(file));
			writer.write(row);
			writer.newLine();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main (String[] argv) throws IOException
	{
		boolean inf=true;
		do
		{
		String input; //For storing command string
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		input=in.readLine();
		StringTokenizer command = new StringTokenizer(input," "); // To read input command word by word
		String title = command.nextToken(); //First word in command string
		switch(title){
		case "create":                    //create command
			switch(command.nextToken()){  
			case "db":                   //create database
				System.out.println("CREATE Database");
				createdb(command.nextToken());
				break;
			case "table":              //create table
				System.out.println("CREATE Table");
				String path="D:\\dbase/"+currentdb+"/"+command.nextToken()+".txt";
				row="";
				while (command.hasMoreTokens())    //to make columns of the table 
				{
					row=row +command.nextToken()+" ";
				}
				System.out.println(row);
				createtable(path);
				row="";
				break;
			default:
				System.out.println("create : Invalid Command");
			}
			break;
		case "use":                             //use command
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
		case "exit":
			inf=false;
			break;
		default:
			System.out.println("Invalid Command");
		
		}
		
	  }while(inf);
	}

}
