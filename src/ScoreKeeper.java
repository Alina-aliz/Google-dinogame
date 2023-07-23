public class ScoreKeeper {
    private static int score;
    public static int counter=0;
    private static int tickCount;
    public static double move=1.75;
    public ScoreKeeper(){
        score=1;
    }
    public static int increaseScore(){
        if(counter>=30){
            score++;
            counter=0;
        }
        counter++;
        return score;
    }
    public static void increaseMove(){
        if(score%100==0&&counter==1){
            move+=0.25;
            System.out.println(move);
        }
    }
    public static void increasetickCount(){
        tickCount++;
    }
    public static int getScore(){
        return score;
    }
    public static int gettickCount(){
        return tickCount;
    }
    public static void reset(){
        score=0;
        move=2;
    }
    
}
