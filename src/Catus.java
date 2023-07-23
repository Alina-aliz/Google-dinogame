import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Catus {
    private int x;
    private int y;
    private int type=1;  
    private int move=2;
    private int counter=50;    
    private BufferedImage cactus;
    int rangeX=0;
    int rangeY=0;
    
    public Catus(){
        x=920;
        type=(int)(Math.random()*3)+1;
        switch (type) {
            case 1:
                y=11;
                try {
                    cactus = ImageIO.read(new File("lonelyCactus.jpeg"));
                    rangeX=37;
                    rangeY=65;
                } catch (IOException ex) {
                }   break;
            case 2:
                y=-1;
                try {
                    cactus = ImageIO.read(new File("lonelyCactusButBig.jpeg"));
                    rangeX=40;
                    rangeY=80;
                } catch (IOException ex) {
                }   break;
            default:
                y=5;
                try {
                    cactus = ImageIO.read(new File("partyCactus.jpeg"));
                    rangeX=71;
                    rangeY=70;
                } catch (IOException ex) {
                }   break;
        }
    }   
    
        
    public void drawCatus(Graphics g){
        x-=ScoreKeeper.move;
        g.drawImage(cactus,x,y+350,null);
        
    }     
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    public int getRangeX(){
        return rangeX;
    }
    public int getRangeY(){
        return rangeY;
    }
    
}
