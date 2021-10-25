import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class readMulipleFiles {
	public static void main(String[] args) throws IOException{  
        File dir = new File("C:\\Users\\Matthew Caruso\\Documents\\rwu\\COMSC330\\COMSC-330-Project\\textFiles");
        File[] files = dir.listFiles();
        String[] arr = new String[5];//array for characters
        // Fetching all the files
        for (File file : files) {
            if(file.isFile()) {
                BufferedReader inputStream = null;
                String line = "hello";
                char h;
           
                try {
                    inputStream = new BufferedReader(new FileReader(file));
                    while ((line = inputStream.readLine()) != null) {
                     h = line.charAt(line.length()-1);  
                     //if grade --> switch
                     //if +/- add/sub .333
                        //get char at length - 2 --> switch
                     //put into array
                     System.out.println(h);
                    }
                }catch(IOException e) {
                	System.out.println(e);
                }
                finally {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                }
            }
        }
        
	}
}