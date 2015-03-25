/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bomberman.maps;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author Mateusz
 */
public class MapLoader {
          
    String sciezka;
    int [][] map;
    int rows;
    int cols;
    
    public void loadMap(String path) throws FileNotFoundException{
         this.sciezka = path;
         System.out.println("Ścieżka"+sciezka);
         
      File file = new File(sciezka);
      Scanner in = new Scanner(file);
      String mapSize = in.nextLine();
      String [] tmpSize = mapSize.split("-");
      this.rows = Integer.parseInt(tmpSize[0]);
      this.cols = Integer.parseInt(tmpSize[1]);
      map = new int[rows][cols];
      int rowNumber = 0;
        while (in.hasNextLine()){
            String linia = in.nextLine();
            String [] tmp = linia.split(";");
           
            for (int i=0; i<tmp.length-1; i++)
            {   
                map[rowNumber][i]= Integer.parseInt(tmp[i]);
            }
            
            rowNumber++;
      }
      System.out.println("Wczytywanie mapy zakończone.");
      this.displayMap();
     }
    
    public void displayMap(){
        System.out.println("Wczytana mapa:");
        for (int i=0; i<rows; i++){
            for(int j=0; j<cols;j++){
                System.out.print(map[i][j]+ " ");
                
            }
            System.out.println(" ");
        }
    }
            
    }
    
    
    
   
