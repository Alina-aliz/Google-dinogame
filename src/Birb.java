
import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

public class Birb {
    private int x;
    private int y;
    private int move=2;
    private int mode;
    private int sprite;
    private int spriteDelay=15;
    private BufferedImage birb2;
    private BufferedImage birb1;
    int rangeX=0;
    int rangeY=0;
    
    public Birb(){
        this.x=920;
        rangeX=75;
        rangeY=50;
        mode=(int)(Math.random()*3)+1;
        switch (mode) {
            case 1:
                this.y=-75;
                break;
            case 2:
                this.y=-25;
                break;
            default:
                this.y=-130;
                break;
        }
         try {
           birb1 = ImageIO.read(Birb.class.getResource("/assets/birb1.jpeg"));
        } catch (IOException ex) {
        }
        try {
           birb2 = ImageIO.read(Birb.class.getResource("/assets/birb2.jpeg"));
        } catch (IOException ex) {
        } 
    }
    
    public void drawBirb(Graphics g){
        x-=ScoreKeeper.move;;
        if (sprite==1){
            g.drawImage(birb2,x,y+395,null);
            spriteDelay--;
            if (spriteDelay==0){
                sprite=2;
                spriteDelay=15;
            }
        }
        else{
            g.drawImage(birb1,x,y+395,null);
            spriteDelay--;
            if (spriteDelay==0){
                sprite=1;
                spriteDelay=15;
            }
        }
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