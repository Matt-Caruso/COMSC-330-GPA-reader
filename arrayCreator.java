import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class arrayCreator extends gpaReader
{
    public static String name;
    public static void setName(File file) throws IOException
    {
        Scanner scan = new Scanner(System.in);
        if(file.isFile()) //if file is in directory and is a file
        {
            BufferedReader inputStream = null;
            try
                 {
                    inputStream = new BufferedReader(new FileReader(file));//reads in file
                    name = scan.next();
                } catch(IOException e) 
                {
                	System.out.println(e);
                }
                finally 
                {
                    if (inputStream != null) 
                    {
                        inputStream.close();//closes file
                    }
                }
        }
    }
    public static String getName()
    {
        return name;
    }
}
