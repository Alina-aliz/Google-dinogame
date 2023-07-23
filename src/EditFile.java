import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class EditFile {
    
    public ArrayList<String> highScores=new ArrayList<String>();
        
    public void readFile(){
        String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + "/highScore.txt";
        System.out.println("The file path is " + fullFileName);
        highScores.clear();
        try {   
                FileReader fr = new FileReader(fullFileName);
                BufferedReader br = new BufferedReader(fr);
                String line="";
                while( (line = br.readLine()) != null) {
                    System.out.println("Just read: " + line);
                    highScores.add(line);
                }
                br.close();
        }
        catch(Exception e) {
                System.out.println("Something went wrong file reading!");
        }
        }
    public void writeFile(String s){
       String currentDirectory = System.getProperty("user.dir");
        String fullFileName = currentDirectory + "/highScore.txt";
        
        try {
            FileWriter fw = new FileWriter(fullFileName);
            BufferedWriter br = new BufferedWriter(fw);
            br.write(s + "\n");
            br.close();
        }
        catch(Exception e) {
            System.out.println("Error writing to file");
        }
        
    }

}
