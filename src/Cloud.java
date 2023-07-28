import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Cloud {
    private int x;
    private int y;
    private BufferedImage cloud;
    
    public Cloud(){
        x=920;
        y=(int)(Math.random()*50)+10;
        try {
           cloud = ImageIO.read(Cloud.class.getResource("/assets/cloud.jpeg"));
        } catch (IOException ex) {
        }
    }
    public void drawCloud(Graphics g){
        if(ScoreKeeper.counter%3==0)
            x-=1;
        g.drawImage(cloud,x,y,null);
    }
    
    public int getX(){
         return x;
     }
}
