import java.io.*;
import java.util.Scanner;

public class TestProject3 {
    BufferedReader reader;
    //BST<String> bst;
    HashTableBuilder table;
    
	public static void main(String[] args) {
		//System.out.println("Hello World");
		TestProject3 test = new TestProject3("datafile.txt");
		//test.bst.inorder();
		
	}
	
	public TestProject3(String filename) {
	    try {
	        reader = new BufferedReader(new FileReader(filename));
	        int tableSize = getKeywordsCount();
	        table = new HashTableBuilder(tableSize);
	        //System.out.println("Test" + tableSize);
	        //bst = new BST<String>();
	        reader = new BufferedReader(new FileReader(filename));
	        //System.out.println("Test" + tableSize);
	        while (readFileContents());
	    }
	    catch(IOException e) {
	        e.printStackTrace();
	        //System.out.println(e);
	    }

        int choice = 1;
        Scanner in = new Scanner(System.in);
        while(choice >= 1 && choice < 3){
            System.out.print("MENU:\n1) Print of the entire hash table\n2) Search for a key\n3) Exit the program\n\nMake a selection: ");
            choice = in.nextInt();
            in.nextLine();

            switch (choice){
                case 1:
                    for(int i = 0; i < table.tableSize; i++){
                        if(table.hashTable[i] != null){
                            System.out.println((String)table.hashTable[i].keyword);
							System.out.println(table.hashTable[i].list.toString());
                            
                        }   
                    }
                    break;
                case 2:
                    System.out.print("\nPlease enter the key: ");
                    String temp = in.next();
                    System.out.println();
                    int index = table.searchKey(temp);
                    if( index != -1){
                        System.out.println((String)table.hashTable[index].keyword);
                        System.out.println(table.hashTable[index].list.toString());
                    }
                    else{
                        System.out.println("No such key exists");
                    }
                    break;
                default:
                    break;

            }
        }
        
	}
	
	public boolean readFileContents() {
	    
	    if (reader == null) {
	        System.out.println("Error: You  must open the file first.");
	        return false;
	    }
	    else {
	        try {
	            
	            String data = reader.readLine();
	            if (data == null) return false;
	            //System.out.printf("Testing %s\n", data);
	            int id = Integer.parseInt(data);
	            String title = reader.readLine();
	            String author = reader.readLine();
	            
	            Article article = new Article(id, title, author);
	            //System.out.printf("Testing %s\n", article);
	            
	            String keyword;
	            int numKeys = Integer.parseInt(reader.readLine());
	            for (int i=0; i<numKeys; i++) {
	                keyword = reader.readLine();
	                //bst.insert(keyword, article); 
	                //System.out.printf("Testing %s\n", keyword);
	                
	                table.insert(keyword, article);
	            }
	            reader.readLine();
	        }
	        catch(NumberFormatException e) {
	            System.out.println("Error: Number is expected!");
	            return false;
	        }
	        catch(Exception e) {
	            System.out.println("Error: Fatal error has occured: " + e);
	            return false;
	        }
	    }
	    //System.out.println("Test2");








        







        
	    return true;
	}
	
	private int getKeywordsCount() {
	    int keyCount = 0;
	    if (reader == null) {
	        System.out.println("Error: You  must open the file first.");
	        return keyCount;
	    }
	    
	    while (true) {
	        try {
	            String data;
	            data = reader.readLine();
	            if (data == null) break;
	            reader.readLine();
	            reader.readLine();
	            int numKeys = Integer.parseInt(reader.readLine());
	            keyCount += numKeys;
	            for (int i=0; i<numKeys; i++)
	                reader.readLine();
	            reader.readLine();
	        }
	        catch(NumberFormatException e) {
	            e.printStackTrace();
	        }
	        catch(Exception e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return keyCount;
	    
	}
}