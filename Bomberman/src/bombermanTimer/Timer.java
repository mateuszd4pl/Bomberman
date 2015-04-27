package bombermanTimer;


import bombermanTimer.TimerEvent;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Mateusz
 */
public class Timer {
    private List<TimerEvent> events = new ArrayList<>();
    private Thread thread = null;
    private boolean lopped = false;
    private long delay = 100;
    public void addEvent(TimerEvent e){
        this.events.add(e);
    }
    public void start(){
        
        if(thread==null)
        {
            this.thread=new Thread(){

                @Override
                public void run() {
                 while(lopped){
                     
                     for(TimerEvent el : events)
                         el.tick();
                     
                     try {
                         Thread.sleep(delay);
                     } catch (InterruptedException ex) {
                         Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
                     }
                 }   
                }
              
            };
            
            this.lopped = true;
            this.thread.setName("TIMER");
            this.thread.start();
        }
    }
    
    public void stop(){
       if(this.thread!=null){
           this.lopped=false;
           try {
               this.thread.join();
           } catch (InterruptedException ex) {
               Logger.getLogger(Timer.class.getName()).log(Level.SEVERE, null, ex);
           }
           this.thread = null;
       }
    }
    
}
