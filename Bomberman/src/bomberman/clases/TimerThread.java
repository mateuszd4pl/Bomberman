/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.clases;

/**
 *
 * @author Mateusz
 */
public class TimerThread extends Thread {
   private Thread t;
   private final String threadName;
   private long startTime;
   private long diffTime;
   
   public TimerThread( String name){
       threadName = name;
       System.out.println("Creating " +  threadName );
   }
   @Override
   public void run() {
      startTime = System.nanoTime();
      
      while(true){
          //timer jak zrobic osobny watek w osobnej klasie i zeby to wyslac potem do labelki w mapPanelu + przeslanie refer
      }
   }
   public void captureTime(){
       diffTime = (long) ((System.nanoTime()-startTime) / 1000000000.0);   
       System.out.println(diffTime);
   }
}


// jak zrobić potworki - na jednym watku czy kazdy potworek na innym
// potworek (na nowym watku) sie rusza i jak przerysować to w mapPanelu w tablicy