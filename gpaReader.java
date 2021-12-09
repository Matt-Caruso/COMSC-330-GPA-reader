import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
////C:\Users\Matthew Caruso\Documents\rwu\COMSC330\COMSC-330-Project\textFiles
public class gpaReader extends Application
{
    //global variables
    public static double avg;
    public static int aa,a,bbb,bb,b,ccc,cc,c,ddd,dd,d,f;
    public static int temp1; 
    public static String line ="hello";//placeholder
    public static int counter = 0;
    public static  ArrayList<Double> lists[]=new ArrayList[15];
    public static String direc;
    
    public static void main(String[] args) throws IOException
    {  
        launch();
	}

    public static void loop(String direc1) throws IOException
    {
        //creats # of array lists based on # of SEC files
        for(int i=0;i<8;i++)
        {
        lists[i]=new ArrayList<>();
        }
        
        File dir = new File(direc1);
        
        File[] files = dir.listFiles();//creates file based off directory
       
        
        for (File file : files) //loops through all files in folder
        {
            String temp3 ="";
            temp3 = getFileName(file);
            if(file.isFile() && (temp3.endsWith("SEC") || temp3.endsWith("sec"))) //if file is in directory and is a file
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
                
                
            }
            
        }
    } 
    @Override
    public void start(Stage primaryStage) throws Exception {

        //Label user_id=new Label("Enter a Directory that contains the .txt files");    
        TextField tf1=new TextField("Enter a Directory that contains the .txt files Here");
        
        ComboBox comboBox = new ComboBox(); 
        comboBox.setPromptText("");   
        comboBox.getItems().add("COMSC234_01 ");
        comboBox.getItems().add("COMSC330_01 ");
        comboBox.getItems().add("COMSC330_2 ");
        comboBox.getItems().add("COMSC335_01 ");
        comboBox.getItems().add("COMSC450_01");
        comboBox.getItems().add("COMSC490_01 ");
        comboBox.getItems().add("COMSC492_1 ");
        HBox hbox = new HBox(comboBox);
       
        Label grp1 = new Label();
        Label grp2 = new Label();
        Label grp3 = new Label();

        Label studentNum = new Label();

        

        Label Sum = new Label();
        Label stdv = new Label();

        Label zScore1 = new Label();
        Label zScore2 = new Label();
        Label zScore3 = new Label();

        GridPane root = new GridPane();
        Button secB = new Button("Compare");
        Button reset = new Button("Reset");
        
        secB.setOnAction(value ->  {
            ListView listView = new ListView();
            Label Num330Grp = new Label();
        Label NumSnrGrp = new Label();
        Label NumComscGrp = new Label();
            int selectedIndex = comboBox.getSelectionModel().getSelectedIndex();
            
            Object selectedItem = comboBox.getSelectionModel().getSelectedItem();
            
            int nummy = lists[selectedIndex].size();
            String n = String.valueOf(nummy);
            studentNum.setText(selectedItem + " has " + n + " Students"); 
            
            double temp4 = getAverage(selectedIndex);//CHANGE
            DecimalFormat df1 = new DecimalFormat("#.###");
            Sum.setText(selectedItem + "'s Average is: " + df1.format(temp4)); 

            temp4 = getSTDV(selectedIndex);
             
             DecimalFormat df2 = new DecimalFormat("#.###");
             stdv.setText(selectedItem + "'s Standard Deviation is: " + df2.format(temp4));


         
            
           
             CategoryAxis xAxis    = new CategoryAxis();
             xAxis.setLabel("Letter Grades");
 
             NumberAxis yAxis = new NumberAxis();
             yAxis.setLabel("Number of Students");
             BarChart     barChart = new BarChart(xAxis, yAxis);
 
             XYChart.Series dataSeries1 = new XYChart.Series();
             dataSeries1.setName("" + selectedItem);
 
             dataSeries1.getData().add(new XYChart.Data("A", Collections.frequency(lists[selectedIndex], 4.0)));
             dataSeries1.getData().add(new XYChart.Data("A-"  , Collections.frequency(lists[selectedIndex], 3.67)));
             dataSeries1.getData().add(new XYChart.Data("B+"  , Collections.frequency(lists[selectedIndex], 3.33)));
             dataSeries1.getData().add(new XYChart.Data("B"  , Collections.frequency(lists[selectedIndex], 3.0)));
             dataSeries1.getData().add(new XYChart.Data("B-", Collections.frequency(lists[selectedIndex], 2.67)));
             dataSeries1.getData().add(new XYChart.Data("C+"  , Collections.frequency(lists[selectedIndex], 2.33)));
             dataSeries1.getData().add(new XYChart.Data("C"  , Collections.frequency(lists[selectedIndex], 2.0)));
             dataSeries1.getData().add(new XYChart.Data("C-"  , Collections.frequency(lists[selectedIndex], 1.67)));
             dataSeries1.getData().add(new XYChart.Data("D+", Collections.frequency(lists[selectedIndex], 1.33)));
             dataSeries1.getData().add(new XYChart.Data("D"  , Collections.frequency(lists[selectedIndex], 1.0)));
             dataSeries1.getData().add(new XYChart.Data("D-"  , Collections.frequency(lists[selectedIndex], 0.67)));
             dataSeries1.getData().add(new XYChart.Data("F"  , Collections.frequency(lists[selectedIndex], 0.0)));
             barChart.getData().add(dataSeries1);
 
             
             
           
           
           
           
           
             
             CategoryAxis xAxis2    = new CategoryAxis();
             xAxis2.setLabel("Letter Grades");
 
             NumberAxis yAxis2 = new NumberAxis();
             yAxis2.setLabel("Number of Students");
             BarChart     barChart2 = new BarChart(xAxis2, yAxis2);
 
             XYChart.Series dataSeries2 = new XYChart.Series();
             XYChart.Series dataSeries3 = new XYChart.Series();
             XYChart.Series dataSeries4 = new XYChart.Series();
             
             if(selectedIndex == 1 || selectedIndex == 2)
            {
                DecimalFormat df = new DecimalFormat("#.###");
                grp1.setText(selectedItem + " appears in COMSC330 GRP \n GPA Average: " + df.format(get330GrpGpa()));
                 nummy = getNum330Grp();
                 n = String.valueOf(nummy);
                Num330Grp.setText("- " + " 2 Sections | " + n + " total Students");
                
                double nummy1 = getZScore(selectedIndex, 1);
                 n = String.valueOf(nummy1);
                zScore1.setText( selectedItem + " GPA " + " Compared to COMSC330 GRP GPA \n yields a Z-Score of " + df.format(nummy1) );
                dataSeries2.setName("COMSC330 GRP");
              
               getOccurrence(1);
                dataSeries2.getData().add(new XYChart.Data("A", aa ) );
                dataSeries2.getData().add(new XYChart.Data("A-", a ) );
                dataSeries2.getData().add(new XYChart.Data("B+", bbb ) );
                dataSeries2.getData().add(new XYChart.Data("B", bb ) );
                dataSeries2.getData().add(new XYChart.Data("B-", b ) );
                dataSeries2.getData().add(new XYChart.Data("C+", ccc ) );
                dataSeries2.getData().add(new XYChart.Data("C", cc ) );
                dataSeries2.getData().add(new XYChart.Data("C-", c ) );
                dataSeries2.getData().add(new XYChart.Data("D+", ddd ) );
                dataSeries2.getData().add(new XYChart.Data("D", dd ) );
                dataSeries2.getData().add(new XYChart.Data("D-", d ) );
                dataSeries2.getData().add(new XYChart.Data("F", f ) );

                
                barChart2.getData().add(dataSeries2);

               
            }
            if(selectedIndex == 5 || selectedIndex == 6)
            {
                DecimalFormat df = new DecimalFormat("#.###");
                grp2.setText(selectedItem + " appears in Senior Design GRP \n GPA Average: " + df.format(getSnrDesGrpGpa()));
                nummy = getNumSnrGrp();
                n = String.valueOf(nummy);
               NumSnrGrp.setText("- " + " 2 Sections | " + n + " total Students");

               dataSeries3.setName("Senior Design GRP");
               getOccurrence(3);
                dataSeries3.getData().add(new XYChart.Data("A", aa ) );
                dataSeries3.getData().add(new XYChart.Data("A-", a ) );
                dataSeries3.getData().add(new XYChart.Data("B+", bbb ) );
                dataSeries3.getData().add(new XYChart.Data("B", bb ) );
                dataSeries3.getData().add(new XYChart.Data("B-", b ) );
                dataSeries3.getData().add(new XYChart.Data("C+", ccc ) );
                dataSeries3.getData().add(new XYChart.Data("C", cc ) );
                dataSeries3.getData().add(new XYChart.Data("C-", c ) );
                dataSeries3.getData().add(new XYChart.Data("D+", ddd ) );
                dataSeries3.getData().add(new XYChart.Data("D", dd ) );
                dataSeries3.getData().add(new XYChart.Data("D-", d ) );
                dataSeries3.getData().add(new XYChart.Data("F", f ) );

                
                barChart2.getData().add(dataSeries3);

                double nummy1 = getZScore(selectedIndex, 2);
                 n = String.valueOf(nummy1);
                zScore1.setText( selectedItem + " GPA " + " Compared to Senior Design GRP GPA \n yields a Z-Score of " + df.format(nummy1) );
                
            }
            if(selectedIndex == 0 || selectedIndex == 1 || selectedIndex == 2 || selectedIndex == 3 || selectedIndex == 4 || selectedIndex == 5 || selectedIndex == 6)
            {
                DecimalFormat df = new DecimalFormat("#.###");
                grp3.setText(selectedItem + " appears in COMSC GRP \n GPA Average: " + df.format(getComscGrpGpa()));
                nummy = getNumComscGrp();
                n = String.valueOf(nummy);
               NumComscGrp.setText("- " + " 7 Sections | " + n + " total Students");

               dataSeries4.setName("COMSC GRP");
               getOccurrence(2);
               dataSeries4.getData().add(new XYChart.Data("A", aa ) );
                dataSeries4.getData().add(new XYChart.Data("A-", a ) );
                dataSeries4.getData().add(new XYChart.Data("B+", bbb ) );
                dataSeries4.getData().add(new XYChart.Data("B", bb ) );
                dataSeries4.getData().add(new XYChart.Data("B-", b ) );
                dataSeries4.getData().add(new XYChart.Data("C+", ccc ) );
                dataSeries4.getData().add(new XYChart.Data("C", cc ) );
                dataSeries4.getData().add(new XYChart.Data("C-", c ) );
                dataSeries4.getData().add(new XYChart.Data("D+", ddd ) );
                dataSeries4.getData().add(new XYChart.Data("D", dd ) );
                dataSeries4.getData().add(new XYChart.Data("D-", d ) );
                dataSeries4.getData().add(new XYChart.Data("F", f ) );

                
                barChart2.getData().add(dataSeries4);

                double nummy1 = getZScore(selectedIndex, 3);
                 n = String.valueOf(nummy1);
                zScore1.setText( selectedItem + " GPA " + " Compared to COMSC GRP GPA \n yields a Z-Score of " + df.format(nummy1) );
                
            }


            
            
    

           
            VBox vbox = new VBox(barChart);
            VBox vbox2 = new VBox(barChart2);

            vbox.setVisible(true);
            root.addRow(8, vbox);
            root.addRow(15, vbox2);

            
  
            GridPane gridPane = new GridPane();

             gridPane.add(Sum, 0, 0);
             gridPane.add(stdv, 0, 1);
             
             gridPane.add(grp1, 3, 0);
             gridPane.add(Num330Grp, 4, 0);
             
             gridPane.add(grp2, 3, 1);
             gridPane.add(NumSnrGrp, 4, 1);
             
             gridPane.add(grp3, 3, 2);
             gridPane.add(NumComscGrp, 4, 2);
             
             gridPane.add(studentNum, 0, 2);

             gridPane.add(zScore1, 5, 0);
             gridPane.add(zScore2, 5, 1);
             gridPane.add(zScore3, 5, 2);

             gridPane.setHgap(60);
             gridPane.setVgap(30);
            
            
           
            
           root.addRow(11, gridPane);
            reset.setOnAction(value1 ->  {
            
                grp1.setText("");
                grp2.setText("");
                grp3.setText("");
                Sum.setText("");
                stdv.setText("");
                Sum.setText("");
                stdv.setText("");
                zScore1.setText("");
                zScore2.setText("");
                zScore3.setText("");
                root.getChildren().remove(vbox);
                root.getChildren().remove(vbox2);
                root.getChildren().remove(gridPane);
              });
            
          });
          
          
          
          comboBox.setTranslateX(600);
          comboBox.setTranslateY(1);
          comboBox.setPrefWidth(100);

         secB.setTranslateX(600);
         secB.setTranslateY(1);
         secB.setPrefWidth(100);
         
          

          


        Button b = new Button("Enter");  
          
        
         
     

       
        Scene scene=new Scene(root,1400,700); 
       
        root.addRow(0, tf1,b);    
        root.addRow(2, hbox, secB);
        
        root.addRow(7, reset);
         
        
        
        
        b.setOnAction(value ->  {
           direc = tf1.getText();
           try {
            loop(direc);
        } catch (IOException e) {
            e.printStackTrace();
        }
         });

        primaryStage.setScene(scene);  
        primaryStage.setTitle("GPA READER");  
        primaryStage.show();  

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
            case 'D':  
            {
                temp1++;
                lists[counter].add(1.0);
            }
                    break;  
            case 'F':  
            {
                temp1++;
                lists[counter].add(0.0);
            }
                    break;
            case '+':  
            {
                char c;
                c = line.charAt(line.length()-2);//Gets char before the plus sign
                double xx = convert(c);
                temp1++;
                lists[counter].add(xx+.33);
            }
                    break;          
            case '-':  
            {
                char c;
                c = line.charAt(line.length()-2);//gets char before minus sign
                double xx = convert(c);
               temp1++;
               lists[counter].add(xx-.33);
               
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
            case 'D':  
            {
               temp = 1.0;
            }
            break;
            case 'F':  
            {
               temp = 0.0;
            }
            break;
        }
        return temp;
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

    public static double getZScore(int listNum, int num)
    {
            if(num == 1)
            {
             return ((getAverage(listNum)-get330GrpGpa())/getSTDV(listNum));
            }
            else if(num == 2)
            {
             return ((getAverage(listNum)-getComscGrpGpa())/getSTDV(listNum));
            }
            else
            {
             return ((getAverage(listNum)-getSnrDesGrpGpa())/getSTDV(listNum));
            }
    }

    public static double getSTDV(int listNum)
    {
        double sum = 0.0, standardDeviation = 0.0;
        int length = lists[listNum].size();

        for(double num : lists[listNum]) {
            sum += num;
        }

        double mean = sum/length;

        for(double num: lists[listNum]) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation/length);
    
    }

    public static String getFileName(File file)
    {
        return file.getName();
    }
    
    public static double get330GrpGpa()
    {
        return(((getAverage(1))+(getAverage(2)))/2);
    }
    
    public static double getComscGrpGpa()
    {
        return(((getAverage(1))+(getAverage(2))+(getAverage(0))
        +(getAverage(3))+(getAverage(4))+(getAverage(5))+(getAverage(6)))/7);
    }  

    public static double getSnrDesGrpGpa()
    {
        return(((getAverage(5))+(getAverage(6)))/2);
    }

    public static int getNum330Grp()
    {
            return (lists[1].size() + lists[2].size());
          
    }

    public static int getNumSnrGrp()
    {
            return (lists[5].size() + lists[6].size());
          
    }

    public static int getNumComscGrp()
    {
            return (lists[0].size() + lists[1].size()+ lists[2].size()
             + lists[3].size() + lists[4].size() + lists[5].size() + lists[6].size());
          
    }

    public static void getOccurrence(int num)
    {
        
        
        if (num == 1)
        {
            aa = Collections.frequency(lists[1], 4.0) + (Collections.frequency(lists[2], 4.0));
            a = Collections.frequency(lists[1], 3.67) + (Collections.frequency(lists[2], 3.67));
            bbb = Collections.frequency(lists[1], 3.33) + (Collections.frequency(lists[2], 3.33));
            bb = Collections.frequency(lists[1], 3.0) + (Collections.frequency(lists[2], 3.0));
            b = Collections.frequency(lists[1], 2.67) + (Collections.frequency(lists[2], 2.67));
            ccc = Collections.frequency(lists[1], 2.33) + (Collections.frequency(lists[2], 2.33));
            cc = Collections.frequency(lists[1], 2.0) + (Collections.frequency(lists[2], 2.0));
            c = Collections.frequency(lists[1], 1.67) + (Collections.frequency(lists[2], 1.67));
            ddd = Collections.frequency(lists[1], 1.33) + (Collections.frequency(lists[2], 1.33));
            dd = Collections.frequency(lists[1], 1.0) + (Collections.frequency(lists[2], 1.0));
            d = Collections.frequency(lists[1], 0.67) + (Collections.frequency(lists[2], 0.67));
            f = Collections.frequency(lists[1], 0.0) + (Collections.frequency(lists[2], 0.0));
        }
        if (num == 2)
        {
            aa =  Collections.frequency(lists[0], 4.0) + (Collections.frequency(lists[1], 4.0)) + Collections.frequency(lists[2], 4.0) + 
            (Collections.frequency(lists[3], 4.0)) + Collections.frequency(lists[4], 4.0) + (Collections.frequency(lists[5], 4.0));
            
            a =  Collections.frequency(lists[0], 3.67) + (Collections.frequency(lists[1], 3.67)) + Collections.frequency(lists[2], 3.67) + 
            (Collections.frequency(lists[3], 3.67)) + Collections.frequency(lists[4], 3.67) + (Collections.frequency(lists[5], 3.67));

            bbb =  Collections.frequency(lists[0], 3.33) + (Collections.frequency(lists[1], 3.33)) + Collections.frequency(lists[2], 3.33) + 
            (Collections.frequency(lists[3], 3.33)) + Collections.frequency(lists[4], 3.33) + (Collections.frequency(lists[5], 3.33));

            bb =  Collections.frequency(lists[0], 3.0) + (Collections.frequency(lists[1], 3.0)) + Collections.frequency(lists[2], 3.0) + 
            (Collections.frequency(lists[3], 3.0)) + Collections.frequency(lists[4], 3.0) + (Collections.frequency(lists[5], 3.0));

            b =  Collections.frequency(lists[0], 2.67) + (Collections.frequency(lists[1], 2.67)) + Collections.frequency(lists[2], 2.67) + 
            (Collections.frequency(lists[3], 2.67)) + Collections.frequency(lists[4], 2.67) + (Collections.frequency(lists[5], 2.67));

            ccc =  Collections.frequency(lists[0], 2.33) + (Collections.frequency(lists[1], 2.33)) + Collections.frequency(lists[2], 2.33) + 
            (Collections.frequency(lists[3], 2.33)) + Collections.frequency(lists[4], 2.33) + (Collections.frequency(lists[5], 2.33));

            cc =  Collections.frequency(lists[0], 2.0) + (Collections.frequency(lists[1], 2.0)) + Collections.frequency(lists[2], 2.0) + 
            (Collections.frequency(lists[3], 2.0)) + Collections.frequency(lists[4], 2.0) + (Collections.frequency(lists[5], 2.0));

            c =  Collections.frequency(lists[0], 1.67) + (Collections.frequency(lists[1], 1.67)) + Collections.frequency(lists[2], 1.67) + 
            (Collections.frequency(lists[3], 1.67)) + Collections.frequency(lists[4], 1.67) + (Collections.frequency(lists[5], 1.67));

            ddd =  Collections.frequency(lists[0], 1.33) + (Collections.frequency(lists[1], 1.33)) + Collections.frequency(lists[2], 1.33) + 
            (Collections.frequency(lists[3], 1.33)) + Collections.frequency(lists[4], 1.33) + (Collections.frequency(lists[5], 1.33));

            dd =  Collections.frequency(lists[0], 1.0) + (Collections.frequency(lists[1], 1.0)) + Collections.frequency(lists[2], 1.0) + 
            (Collections.frequency(lists[3], 1.0)) + Collections.frequency(lists[4], 1.0) + (Collections.frequency(lists[5], 1.0));

            d =  Collections.frequency(lists[0], 0.67) + (Collections.frequency(lists[1], 0.67)) + Collections.frequency(lists[2], 0.67) + 
            (Collections.frequency(lists[3], 0.67)) + Collections.frequency(lists[4], 0.67) + (Collections.frequency(lists[5], 0.67));

            f =  Collections.frequency(lists[0], 0.0) + (Collections.frequency(lists[1], 0.0)) + Collections.frequency(lists[2], 0.0) + 
            (Collections.frequency(lists[3], 0.0)) + Collections.frequency(lists[4], 0.0) + (Collections.frequency(lists[5], 0.0));

            
        }
        if (num == 3)
        {
            aa = Collections.frequency(lists[5], 4.0) + (Collections.frequency(lists[6], 4.0));
            a = Collections.frequency(lists[5], 3.67) + (Collections.frequency(lists[6], 3.67));
            bbb = Collections.frequency(lists[5], 3.33) + (Collections.frequency(lists[6], 3.33));
            bb = Collections.frequency(lists[5], 3.0) + (Collections.frequency(lists[6], 3.0));
            b = Collections.frequency(lists[5], 2.67) + (Collections.frequency(lists[6], 2.67));
            ccc = Collections.frequency(lists[5], 2.33) + (Collections.frequency(lists[6], 2.33));
            cc = Collections.frequency(lists[5], 2.0) + (Collections.frequency(lists[6], 2.0));
            c = Collections.frequency(lists[5], 1.67) + (Collections.frequency(lists[6], 1.67));
            ddd = Collections.frequency(lists[5], 1.33) + (Collections.frequency(lists[6], 1.33));
            dd = Collections.frequency(lists[5], 1.0) + (Collections.frequency(lists[6], 1.0));
            d = Collections.frequency(lists[5], 0.67) + (Collections.frequency(lists[6], 0.67));
            f = Collections.frequency(lists[5], 0.0) + (Collections.frequency(lists[6], 0.0));
        }
    }
    }