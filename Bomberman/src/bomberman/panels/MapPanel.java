/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.panels;

import bomberman.clases.TimerThread;
import bomberman.jFrame.MapFrame;
import bomberman.maps.MapLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

/**
 *
 * @author Mateusz
 */
public class MapPanel extends javax.swing.JPanel {

    String hardPath = "src\\bomberman\\jFrame\\images\\hard.png";
    String softPath = "src\\bomberman\\jFrame\\images\\soft.png";
    String manPath = "src\\bomberman\\jFrame\\images\\man.png";
    String monsterPath = "src\\bomberman\\jFrame\\images\\monster.png";
    String monsterDownPath = "src\\bomberman\\jFrame\\images\\monsterDown.png";
    String bombPath = "src\\bomberman\\jFrame\\images\\bomb.png";
    String manDownPath = "src\\bomberman\\jFrame\\images\\manDown.png";
    String manLeftPath = "src\\bomberman\\jFrame\\images\\manLeft.png";
    String manRightPath = "src\\bomberman\\jFrame\\images\\manRight.png";
    String manUpPath = "src\\bomberman\\jFrame\\images\\manUp.png";
    int maps[][];
    int x, y;
    BufferedImage hardImage;
    BufferedImage softImage;
    BufferedImage manImage;
    BufferedImage monsterImage;
    BufferedImage monsterDownImage;
    BufferedImage bombImage;
    BufferedImage manDownImage;
    BufferedImage manLeftImage;
    BufferedImage manRightImage;
    BufferedImage manUpImage;
    TimerThread timer;
    MapLoader map;
    MapFrame frame;
    /**
     * Creates new form MapPanel
     */
    
    /**
     * Creates new form MapPanel
     * @param f
     */
    public MapPanel(MapFrame f) {
        this.frame=f;
        initComponents();
        
      timer = new TimerThread( "Timer");
      timer.start();
        
         this.addKeyListener(new KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
            int value;
               String direction=String.valueOf(e.getKeyChar());
               switch(direction){
                    case "w": 
                        System.out.println("gora");
                        value=maps[x-1][y];
                        if(value!=0 && value!=1 && value!=6){
                            if(value!=4){
                                if(maps[x][y]==5) 
                                    maps[x][y]=6;
                                else
                                    maps[x][y]=2;
                                x--;
                                maps[x][y]=3;
                                repaint();
                            }
                            else{
                                System.out.println("Koniec gry");
                                maps[x][y]=10;
                                repaint();
                                gameOver();
                                break;
                            }
                        }
                        maps[x][y]=9;
                        repaint();
                        break;
                    case "s":  
                        System.out.println("dol");
                        value=maps[x+1][y];
                        if(value!=0 && value!=1 && value!=6){
                            if(value!=4){
                                if(maps[x][y]==5) 
                                    maps[x][y]=6;
                                else
                                    maps[x][y]=2;
                                x++;
                                maps[x][y]=3;
                                repaint();
                            }
                            else{
                                System.out.println("Koniec gry");
                                maps[x][y]=10;
                                repaint();
                                gameOver();
                            }
                        }
                        break;
                    case "a": 
                        System.out.println("lewo");
                        value=maps[x][y-1];
                        if(value!=0 && value!=1 && value!=6){
                            if(value!=4){
                                if(maps[x][y]==5) 
                                    maps[x][y]=6;
                                else
                                    maps[x][y]=2;
                                y--;
                                maps[x][y]=3;
                                repaint();
                            }
                            else{
                                System.out.println("Koniec gry");
                                maps[x][y]=10;
                                repaint();
                                gameOver();
                                break;
                            }
                        }
                        maps[x][y]=7;
                        repaint();
                        break;
                    case "d": 
                        System.out.println("prawo");
                        value=maps[x][y+1];
                        if(value!=0 && value!=1 && value!=6){
                            if(value!=4){
                                if(maps[x][y]==5) 
                                    maps[x][y]=6;
                                else
                                    maps[x][y]=2;
                                y++;
                                maps[x][y]=3;
                                repaint();
                            }
                            else{
                                System.out.println("Koniec gry");
                                maps[x][y]=10;
                                repaint();
                                gameOver();
                                break;
                            }
                        }
                        maps[x][y]=8;
                        repaint();
                        break;
                    case " ":
                        maps[x][y]=5;
                        repaint();
                        break;
                    default: break;
        }
               
            }

            @Override
            public void keyPressed(KeyEvent e) {
                 //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void keyReleased(KeyEvent e) {
                 //To change body of generated methods, choose Tools | Templates.
            }
                });
         
         
        
        try {
            hardImage= ImageIO.read(new File(hardPath));
            softImage= ImageIO.read(new File(softPath));
            manImage= ImageIO.read(new File(manPath));
            manDownImage= ImageIO.read(new File(manDownPath));
            manUpImage= ImageIO.read(new File(manUpPath));
            manLeftImage= ImageIO.read(new File(manLeftPath));
            manRightImage= ImageIO.read(new File(manRightPath));
            monsterImage= ImageIO.read(new File(monsterPath));
            monsterDownImage= ImageIO.read(new File(monsterDownPath));
            bombImage= ImageIO.read(new File(bombPath));
        } catch (IOException ex) {
            Logger.getLogger(MapPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }
    
    public void loadMap(MapLoader m){
        
        this.map=m;
        System.out.println(map.getRows());
        maps=m.getMap();
        System.out.println(maps[1][1]);
        
    }
    
    public void gameOver() {
	timer.captureTime();
        frame.stopTimer();
        JOptionPane.showMessageDialog(this, "Game Over", "Game Over", JOptionPane.YES_NO_OPTION);
        frame.dispose();
    }

    
 private void doDrawing(Graphics g) {
        this.setBackground(Color.GREEN);
        Graphics2D g2d = (Graphics2D) g;
        
        for (int i=0; i<map.getCols(); i++){
             for (int j=0; j<map.getRows(); j++){
            switch(maps[j][i]){
                    case 0: 
                        g.drawImage(hardImage, i*25, j*25, null);
                        break;
                    case 1:  
                        g.drawImage(softImage, i*25, j*25, null);
                        break;
                    case 2: 
                        break;
                    case 3: 
                        x=j;
                        y=i;
                        g.drawImage(manImage, i*25, j*25, null);
                        break;
                    case 4: 
                        g.drawImage(monsterImage, i*25, j*25, null);
                        break;
                    case 5:
                        g.drawImage(bombImage, i*25, j*25, null);
                        g.drawImage(manImage, i*25, j*25, null);
                        break;
                    case 6: 
                        g.drawImage(bombImage, i*25, j*25, null);
                        break;
                    case 7: 
                        g.drawImage(manLeftImage, i*25, j*25, null);
                        break;
                    case 8: 
                        g.drawImage(manRightImage, i*25, j*25, null);
                        break;
                    case 9: 
                        g.drawImage(manUpImage, i*25, j*25, null);
                        break;
                    case 10: 
                        g.drawImage(manDownImage, i*25, j*25, null);
                        break;
                    case 11:
                         g.drawImage(monsterDownImage, i*25, j*25, null);
                        break;
                        
                    default: break;
        }
             }
   } 
 }
    @Override
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);
        doDrawing(g);
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(51, 51, 255));
        addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                formKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                formKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyPressed
           // TODO add your handling code here:
    }//GEN-LAST:event_formKeyPressed

    private void formKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_formKeyTyped
      // TODO add your handling code her e:
    }//GEN-LAST:event_formKeyTyped

    public void initiateMonsters(){
    int x1=10, y1=8;
    while(true){
        maps[x1][y1]=2;
        maps[x1-1][y1]=4;
        repaint();
        maps[x1-1][y1]=2;
        maps[x1-2][y1]=4;
        repaint();
    }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
