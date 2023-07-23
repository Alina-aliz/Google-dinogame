import java.awt.Color;
import java.awt.Graphics;


public class Sand {
    private int x;
    private int y;
    private int size;
    
    public Sand(){
        x=920;
        y=(int)(Math.random()*50)+430;
        size=(int)(Math.random()*10)+1;
    }
    
    public void drawSand(Graphics g){
        x-=ScoreKeeper.move;
        g.setColor(Color.black);
        g.drawLine(x, y, x-size, y);
    }
    
    public int getX(){
         return x;
     }
    
    
}