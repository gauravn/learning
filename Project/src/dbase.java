/*******************************************************************************************************
Database class
Commands to be implemented:
1)create db database_name -    -------------------------------------------------completed
2)use db database_name         -------------------------------------------------completed
3)create table table_name column1 column2 column3...----------------------------completed
	if no database is selected by using "use" command then table 
	is created in default database(master)
4)insert into table_name col1 col2 col3 ....------------------------------------completed
5)select col1 col2...from table_name where col1 value------------------	()
              select * from table_name    ------------------------complete
              select col1 col2...from table_name-------------------complete
6)exit    ----------------------------------------------------------------------completed
********************************************************************************************************/
import java.io.*;
import java.util.StringTokenizer;
public class dbase {
	static String currentdb="master";
	static String row;
	static String path;
	static void create(StringTokenizer command)
	{		
		switch(command.nextToken()){  
		case "db":                   //create database
			System.out.println("CREATE Database");
			createdb(command.nextToken());
			break;
		case "table":              //create table
			System.out.println("CREATE Table");
		    path="D:\\dbase/"+currentdb+"/"+command.nextToken()+".csv";
			row="";
			while (command.hasMoreTokens())    //to make columns of the table 
			{
				row=row +command.nextToken()+",";
			}
			System.out.println(row);
			createtable(path);
			row="";
			break;
		default:
			System.out.println("create : Invalid Command");
		}
	}
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
	static void use(StringTokenizer command)
	{
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
	}
	static void insert(StringTokenizer command)
	{
		switch(command.nextToken()){
		case "into":
			path="D:\\dbase/"+currentdb+"/"+command.nextToken()+".csv";
			File insert=new File(path);
			row="";
			while (command.hasMoreTokens())    //to make columns of the table 
			{
				row=row +command.nextToken()+",";
			}
			if(insert.exists())
			{
				
				try {
					insert.createNewFile();
					BufferedWriter writer = new BufferedWriter(new FileWriter(insert,true));
					writer.write(row);
					writer.newLine();
					writer.close();
					System.out.println("Inserted to Table");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			else
			{
				System.out.println("Table Not Found");
			}	
			break;
		default:
			System.out.println("Invalid Insert Command ");
		}
	}
	static void select(StringTokenizer command)
	{
		String dbcolumn[]=new String[10];
		String column[]=new String[10];   //storing name of columns
		boolean index[]=new boolean[10]; //storing index for columns
		int count=0;
		String temp="";
		temp=command.nextToken();
		while(!temp.equals("from"))
		{
			column[count]=temp;
			System.out.println(column[count]);
			temp=command.nextToken();
		    count++;	
		}
		System.out.println("count :"+count);
		path="D:\\dbase/"+currentdb+"/"+command.nextToken()+".csv";
		System.out.println(path);
		File selct=new File(path);
		if(selct.exists())
		{
			try {
				selct.createNewFile();
				BufferedReader reader = new BufferedReader(new FileReader(selct));
				row=reader.readLine();
				StringTokenizer data = new StringTokenizer(row,","); // To read input command word by word
				//System.out.println(column[0]);
				if(column[0].equals("*"))
				{
					while(row!=null)
					{
						System.out.println(row);
						row=reader.readLine();
					}
				}
				else
				{
				temp=data.nextToken();
				int count1=0;
				while(data.hasMoreTokens())
				{
					dbcolumn[count1]=temp;
					System.out.print(temp+" ");
					temp=data.nextToken();
					count1++;
				}
				dbcolumn[count1]=temp;
				System.out.print(temp+" ");
				System.out.println();
				System.out.println("Count1:"+count1);
				for(int j=0;j<count1;j++)
				{
					index[j]=false;
					//System.out.println(j+":"+index[j]);
				}
				for(int i=0;i<=count1;i++)
				{
					for(int j=0;j<count;j++)
					{
						//System.out.println(temp+","+column[i]);
						if(dbcolumn[i].equals(column[j]))
						{
							index[i]=true;
							//System.out.print(count1+" "+index[count1]);
							System.out.print(dbcolumn[i]+" ");
						}
					}
					for(int j=0;j<=count;j++)
					{
						//System.out.println(j+":"+index[j]);
					}
				    //count1++;	
				}
				System.out.println();
				row=reader.readLine();
				while(row!=null)
				{
					StringTokenizer datarow = new StringTokenizer(row,","); // To read input command word by word
					//temp=datarow.nextToken();
					int count2=0;
					do
					{
						//dbcolumn[count]=temp;
						//System.out.println(index[count1]);
						//System.out.println(temp);
						temp=datarow.nextToken();
						if (index[count2])
						{
						System.out.print(temp+" ");
						}
					    count2++;	
					}while(datarow.hasMoreTokens());
					//System.out.println(row);
					System.out.println();
					row=reader.readLine();
				}
				reader.close();
				//System.out.println("Inserted to Table");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else
		{
			System.out.println("Table Not Found");
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
			create(command);
			break;
		case "use":                             //use command
			//title2=command.nextToken();
			use(command);
			break;
		case "insert":
			insert(command);
			break;
		case "select":
			select(command);
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
