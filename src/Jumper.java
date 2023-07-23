public class Jumper {
 
   double y;
   double yv;
   double g=+0.10;//0.75
   boolean jumping=false;
   
   public Jumper(){
       y=0;
       yv=0;
   }
   
   public void jump() {
       if (!jumping) {
           System.out.println("jumping");
           yv=-5.5;//-10
           jumping=true;
       }
   }
   
   public void step(){
       if (jumping) {
           yv+=g;
           y+=yv;
           if (y>=0) {
               yv=0;
               y=0;
               jumping=false;
           }  
       }
       //System.out.println(y);
       
   }
   
   public int getY(){
       return (int)y;
   }
}
