import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList; 
import java.util.Scanner;
//C:\Users\Matthew Caruso\Documents\rwu\COMSC330\COMSC-330-Project\textFiles
public class gpaReader 
{
    //global variables
    public static double avg;
    public static int temp1;
    public static String line ="hello";//placeholder
    public static int counter = 0;
    public static  ArrayList<Double> lists[]=new ArrayList[8];
    public static void main(String[] args) throws IOException
    {  
        Scanner scan = new Scanner(System.in);
       
        System.out.println("enter the path that contains all the .txt files");
        String direc = scan.nextLine();

       
            //creats # of array lists based on # of SEC files
            for(int i=0;i<8;i++)
            {
            lists[i]=new ArrayList<>();
            }
        
       
        File dir = new File(direc);
        
        File[] files = dir.listFiles();//creates file based off directory
       
        
        for (File file : files) //loops through all files in folder
        {
            if(file.isFile()) //if file is in directory and is a file
            {
                BufferedReader inputStream = null;
                 
                
               
                char h;
           
                try
                 {
                    inputStream = new BufferedReader(new FileReader(file));//reads in file
                    while ((line = inputStream.readLine()) != null) 
                    {
                     h = line.charAt(line.length()-1);  // sets h to last char in line
                     if(h == 'A' || h == 'B' || h == 'C' || h == 'D' || h == 'F' || h == '+' || h == '-')//refactor thris through line 52
                     {
                         switches(h); 
                         
                     }
                      
                    }
                    
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
                
                counter++;//var used to itterate though different sec arrays
                
                //for (double i : list) 
                //{
                    //System.out.println(i);
                  //}
            }
        }

        
            printData(); //prints results for now
                    
            
        
        
	}

    public static void switches(char h)//converts chars into nums-method
    {
        
        switch(h)
        {
            case 'A':  
            {
                temp1++;
                lists[counter].add(4.0);
                
                
            }
                     break;
            case 'B':  
            {
                temp1++;
                lists[counter].add(3.0);

            }
                    break;
            case 'C':  
            {
                temp1++;
                lists[counter].add(2.0);
            }
                    break;  
            case '+':  
            {
                char c;
                c = line.charAt(line.length()-2);//Gets char before the plus sign
                double xx = convert(c);
                temp1++;
                lists[counter].add(xx+.333);
            }
                    break;          
            case '-':  
            {
                char c;
                c = line.charAt(line.length()-2);//gets char before minus sign
                double xx = convert(c);
               temp1++;
               lists[counter].add(xx-.333);
               
            }
                    break;
            default: System.out.println("");
            }
    }


    public static double convert(char h)//converts chars into nums(used for the "+" and "-")-method
    {
        double temp = 0;
        
        switch(h)
        {
            case 'A':  
            {
                temp = 4.0;
            }
               break;      
            case 'B':  
            {
                temp = 3.0;
            }
                break;
            case 'C':  
            {
               temp = 2.0;
            }
            break;
        }
        return temp;
    }

    public static void printData()
    {
        System.out.println("");  
        System.out.println("COMSC335_01's numerical grades are: " + lists[3]);  
        System.out.println("");     
        System.out.println("COMSC234_01 avg GPA = " + getAverage(0));
        System.out.println("COMSC330_01 avg GPA = " + getAverage(1));
        System.out.println("COMSC330_2 avg GPA = " + getAverage(2));
        System.out.println("COMSC335_01 avg GPA = " + getAverage(3));
        System.out.println("COMSC450_01 avg GPA = " + getAverage(4));
        System.out.println("COMSC490_01 avg GPA = " + getAverage(5));
        System.out.println("COMSC492_1 avg GPA = " + getAverage(6));
        
        System.out.println("");

        System.out.println(getSTDV(3));

        //System.out.println("GRP 330 avg GPA = " + ((getSum(1) / 23) + (getSum(2) / 15)) / 2);
        //System.out.println("GRP Senior_Design avg GPA = " + ((getSum(5) / 23) + (getSum(6) / 15)) / 2);
        //System.out.println("GRP COMSC avg GPA = " + (((getSum(0) / 23) + (getSum(1) / 23)+ (getSum(2) / 15)) + (getSum(3) / 21) + (getSum(4) / 9) + (getSum(5) / 10) + (getSum(6) / 10)) / 7);
    }

    public static double getSum(int listNum)//gets sum of all elements in a specific arraylist
    {
                double sum = 0;
                for(int i = 0; i < lists[listNum].size(); i++)
                sum += lists[listNum].get(i);
                return sum;
    }

    public static double getAverage(int listNum)//gets average of specific arraylist
    {
        return(getSum(listNum) / lists[listNum].size());
    }

   // public static double getZScore(int listNum, int listNum2)
    //{

    //}

    public static double getSTDV(int listNum)
    {
        double mean= getAverage(listNum);
        double temp =0;
        for ( int i= 0; i < lists[listNum].size(); i++)
        {
            temp= Math.pow(i-mean, 2);
        }

        return Math.sqrt(getAverage(listNum));
    }
    }


