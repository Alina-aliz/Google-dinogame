import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.*;
import java.awt.Image;
import java.awt.KeyEventDispatcher;    
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;



public class GameFace  extends javax.swing.JFrame implements KeyEventDispatcher {
    boolean keySpace=false;
    boolean keyDown=false;
   // private double delta_time;
    private long prev_time;
    public long tickCount=0;
    //timer object
    private Timer timer=null;
    //private boolean active=false;
    private int timerDelay=5;
    private Image im;  
    private Graphics graphi;  
    private int counter=50;
    private int counterCloud=10;
    dino di=new dino(50,450);
    Spawn s=new Spawn();
    //Jumper j=new Jumper();
    EditFile ah=new EditFile();
    private int high=0;
    
   
           
    public GameFace() {
        initComponents();
        setLocationRelativeTo(null);
        KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        manager.addKeyEventDispatcher(this);
        setUpImageBuffer();
        buttonDie.setVisible(false);
    }
     public void setUpImageBuffer(){
        im=backdrop.createImage(backdrop.getWidth(),backdrop.getHeight());
        graphi=im.getGraphics();
    }
      public void redraw() {
        //switch to white and draw a white rectangle over the entire image buffer to clear it
        //System.out.println("redraw");
        graphi.setColor(Color.WHITE);
        graphi.clearRect(0,0, backdrop.getWidth(), backdrop.getHeight() );
        graphi.fillRect(0,0,backdrop.getWidth(), backdrop.getHeight() );
        drawStuff(graphi);
        Graphics g = backdrop.getGraphics();
        g.drawImage(im,0,0,this);
      }
       public void drawStuff(Graphics g) {
       //re draw all birbs, sand, and catus
        for(int c=0;c<s.cat.size();c++)
            s.cat.get(c).drawCatus(g);
        for(int b=0;b<s.bird.size();b++)
            s.bird.get(b).drawBirb(g);
        for(int d=0;d<s.dots.size();d++)
            s.dots.get(d).drawSand(g);
        for(int e=0;e<s.cloudy.size();e++)
            s.cloudy.get(e).drawCloud(g);
        //draw our player
        di.drawDino(g); 
        g.setColor(Color.black);
        g.drawLine(0,425,920,425);
        //draw score
        g.drawString("HI: "+high+"     Current: " +ScoreKeeper.getScore(),650,100);
       }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backdrop = new javax.swing.JPanel();
        cry = new javax.swing.JButton();
        buttonDie = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(java.awt.Color.white);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        backdrop.setBackground(java.awt.Color.white);
        backdrop.setPreferredSize(new java.awt.Dimension(900, 650));
        backdrop.setLayout(null);

        cry.setText("Start Game");
        cry.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cryActionPerformed(evt);
            }
        });
        backdrop.add(cry);
        cry.setBounds(20, 20, 240, 23);

        buttonDie.setText("Reset");
        buttonDie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDieActionPerformed(evt);
            }
        });
        backdrop.add(buttonDie);
        buttonDie.setBounds(420, 270, 61, 23);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backdrop, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 920, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(backdrop, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated

    }//GEN-LAST:event_formWindowActivated

    private void cryActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cryActionPerformed
        startTimer();
        redraw();
        cry.setEnabled(false);
        cry.setVisible(false);
        ah.readFile();
        high=Integer.parseInt(ah.highScores.get(0));
    }//GEN-LAST:event_cryActionPerformed

    private void buttonDieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDieActionPerformed
        ScoreKeeper.reset();
        di.setY();
        s.removeObjects();
        startTimer();
        redraw();
        di.setDead();
        buttonDie.setVisible(false);
    }//GEN-LAST:event_buttonDieActionPerformed
    public boolean dispatchKeyEvent(KeyEvent e) {
        if (e.getID() == KeyEvent.KEY_PRESSED) {
            if (e.getKeyCode()==e.VK_SPACE)    
                keySpace=true;
            else if(e.getKeyCode()==e.VK_DOWN){
               keyDown=true;
               di.setDucking(true);
            }
        } 
        else if (e.getID() == KeyEvent.KEY_RELEASED) {
            if (e.getKeyCode()==e.VK_SPACE)
                keySpace=false;
            
            else if(e.getKeyCode()==e.VK_DOWN){
               keyDown=false;
               di.setDucking(false);
            }
        }
        return false;
    }    
    public void startTimer(){
        if (timer!=null) {
            System.out.println("A timer is already working!");
            return;
        }
        //make a new timer object
        timer= new Timer(true);
        //make a timertask that has a job to do (call updateTime)
        TimerTask task= new TimerTask() {
            public void run() {
                tick();
            }
        };
        //tell timer to start repeating the task
        timer.scheduleAtFixedRate(task, 0, timerDelay);
        prev_time=System.nanoTime();
    }
    
    public void tick(){
        //jump step method for gravity
        //temporay scrore keeper textfeild
        //spawn items at given intervals 
        //removeUnseenObjects()clear offscreen birbs and catus
        di.step();
        jump();
        checkCollision();
        ScoreKeeper.increaseScore();
        ScoreKeeper.increaseMove();
        ScoreKeeper.increasetickCount();
        redraw();
        spawn();
        s.removeUnseenObjects();
        
    }
    public void checkCollision(){
        Rectangle saur=new Rectangle(di.getX()+20,di.getY(),di.getWidth()-20,di.getHeight()-10);
        if (di.duckCheck()){
            saur.setBounds(di.getX()+20,di.getY()+35,di.getWidth()-20,di.getHeight());
        }
        for(int c=0;c<s.cat.size();c++){
            Catus meow=s.cat.get(c);
            Rectangle cac=new Rectangle(meow.getX()+15,meow.getY()+350,meow.getRangeX()-15,meow.getRangeY());
            if(saur.intersects(cac)){
                System.out.println("hit! dead!");
                di.setDead();
                timer.cancel();
                timer=null;
                if(ScoreKeeper.getScore()>high){
                    high=ScoreKeeper.getScore();
                    ah.writeFile(""+high);
                }
                buttonDie.setVisible(true);
                buttonDie.setSelected(true);
            }
        }
        for(int b=0;b<s.bird.size();b++){
            Birb tweet=s.bird.get(b);
            Rectangle chirp=new Rectangle(tweet.getX(),tweet.getY()+395,tweet.getRangeX(),tweet.getRangeY());
            if(saur.intersects(chirp)){
                di.setDead();
                timer.cancel();
                timer=null;
                if(ScoreKeeper.getScore()>high){
                    high=ScoreKeeper.getScore();
                    ah.writeFile(""+high);
                }
                buttonDie.setVisible(true);
            }
        }
    }
        
    public void spawn(){
        counter--;
        counterCloud--;
        if (counterCloud==0){
            s.addObjectCloud();
            counterCloud=(int)(Math.random()*500)+500; 
        }
        if (counter==0){
        counter=(int)(Math.random()*200)+150; 
      
            //s.addObjectCloud();
            if (ScoreKeeper.getScore()>15){
                if (counter%2==0){
                    s.addObjectCatus();
                }
                else
                    s.addObjectbirb();
                }
            else{
                s.addObjectCatus();
            }
        }
        else if(counter%10==0)
            s.addObjectSand();

    }

    
    public void jump(){
        if(keySpace){
            di.jump();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GameFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFace.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
       
       
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFace().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel backdrop;
    private javax.swing.JButton buttonDie;
    private javax.swing.JButton cry;
    // End of variables declaration//GEN-END:variables

    
   

   
}
