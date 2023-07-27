

import java.awt.Graphics;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;


public class dino {
    private BufferedImage dinoStand;
    private BufferedImage dinoDead;
    private BufferedImage dinoRun1;
    private BufferedImage dinoRun2;
    private BufferedImage Duckdino1;
    private BufferedImage Duckdino2;
    private int x;
    private double y;
    private int width=73;
    private int height=85;
    private int sprite;
    private int spriteDelay=10;
    private boolean dead=false;
    private boolean ducking=false;
    double yv;
    double g=+0.09;//0.10
    boolean jumping=false;
    
    public dino (int x, int y){
        try {
           dinoStand = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\dinoStand.jpeg"));
        } catch (IOException ex) {
        }
        try {
           dinoRun1 = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\dinoRun1.jpeg"));
        } catch (IOException ex) {
        }
        try {
           dinoRun2 = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\dinoRun2.jpeg"));
        } catch (IOException ex) {
        }
        try {
           dinoDead = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\dinoDead.jpeg"));
        } catch (IOException ex) {
        }
        try {
           Duckdino1 = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\duckDino1.jpeg"));
        } catch (IOException ex) {
        }
        try {
           Duckdino2 = ImageIO.read(new File("C:\\Users\\Alina\\Documents\\projects\\Google-dinogame\\src\\assets\\duckDino2.jpeg"));
        } catch (IOException ex) {
        }
        this.sprite=1; this.x=x; this.y=0;
    }
    public void jump() {
       if (!jumping) {
           System.out.println("jumping");
           yv=-5.5;
           jumping=true;
       }
    }
    public void step(){
       if (jumping) {
           yv+=g;
           y+=yv;
           ducking=false;
           if (y>=0) {
               yv=0;
               y=0;
               jumping=false;
           }  
       }
       
   }
    public boolean isDead() {
        return dead;
    }
    
    public void setDead() {
        dead=!dead;
    }
    
    /*public void dinoY(Jumper j){
    y=j.getY();
    }*/
    
    public void drawDino(Graphics g){
        if (isDead()) {
            g.drawImage(dinoDead, x, (int)y+350,null);
            return;
        }
        if(ducking){
            if (sprite==1){
            g.drawImage(Duckdino1,x,(int)y+385,null);
            spriteDelay--;
                if (spriteDelay==0){
                    sprite=2;
                    spriteDelay=10;
                }
            }
            else{
                g.drawImage(Duckdino2,x,(int)y+385,null);
                spriteDelay--;
                if (spriteDelay==0){
                    sprite=1;
                    spriteDelay=10;
                }
            }
        }
        else if (jumping){
            ducking=false;
            g.drawImage(dinoStand,x,(int)y+350,null);
        }
        else if (sprite==1){
            g.drawImage(dinoRun1,x,(int)y+350,null);
            spriteDelay--;
            if (spriteDelay==0){
                sprite=2;
                spriteDelay=10;
            }
        }
        else{
            g.drawImage(dinoRun2,x,(int)y+350,null);
            spriteDelay--;
            if (spriteDelay==0){
                sprite=1;
                spriteDelay=10;
            }
        
        }
        
    }
    public int getX(){
        return x;
    }
    public int getY(){
        return (int)y+350;
    } 
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setY(){
        y=0;
    }
    public void setDucking(boolean b){
        ducking=b;
        if(ducking){
            width=99;
            height=46;
        }
        else if(!ducking){
            width=73;
            height=85;
        }
    }
    public boolean duckCheck(){
        return ducking;
    }
    

}
    

   
  

