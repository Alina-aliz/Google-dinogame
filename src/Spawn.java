
import java.util.ArrayList;
public class Spawn {
    ArrayList<Catus> cat=new ArrayList<Catus>();
    ArrayList<Birb> bird=new ArrayList<Birb>();
    ArrayList<Sand> dots= new ArrayList<Sand>();
    ArrayList<Cloud> cloudy=new ArrayList<Cloud>();
    
    public void addObjectCatus(){
            cat.add(new Catus());
    }
    public void addObjectbirb(){
            bird.add(new Birb());
    }
    public void addObjectSand(){
        dots.add(new Sand());
    }
    public void addObjectCloud(){
        cloudy.add(new Cloud());
    }
    public void removeUnseenObjects(){
        for (int k=cat.size()-1; k>=0;k--)
            if (cat.get(k).getX()<-30){
                cat.remove(k);
                }
         for (int h=bird.size()-1;h>=0;h--)
            if (bird.get(h).getX()<-30){
                bird.remove(h);
            }    
         for(int s=dots.size()-1;s>=0;s--){
            if(dots.get(s).getX()<-30)
                dots.remove(s);
        }
        for(int c=cloudy.size()-1;c>=0;c--){
            if(cloudy.get(c).getX()<-30)
                cloudy.remove(c);
        }
    }
    public void removeObjects(){
        for (int k=cat.size()-1; k>=0;k--)
            cat.remove(k);
        for (int h=bird.size()-1;h>=0;h--)
            bird.remove(h);
    }
}
