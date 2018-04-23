/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package compoundcalculator;

import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*; 


/**
 *
 * @author Thane_Acheron
 */
public class CompoundCalculator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
       if (args.length == 2){
        if (Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0]) <= 25){
          if (Integer.parseInt(args[1]) >= 20 && Integer.parseInt(args[0]) <= 200){
                JFrame frame = new JFrame("Compound Intrest Calculator");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800,800);
                frame.setVisible(true); 

                JLabel yearsTagLble = new JLabel("           Years");
                JLabel MoneyTagLble = new JLabel("Value           "); 
                JLabel valuesLble = new JLabel("Values");

                frame.add(BorderLayout.SOUTH, valuesLble);
                frame.add(BorderLayout.WEST, yearsTagLble);
                frame.add(BorderLayout.EAST, MoneyTagLble);
                frame.add(BorderLayout.CENTER, new DrawLine());
                
                yearsTotal = 0; 
                amountTotal = Integer.parseInt(args[1]);
                
                
                for(int i = 0; i < Integer.parseInt(args[0]); i++){
                    frame.add(new DrawLine(amountTotal, yearsTotal));  
                    amountTotal = (int)Math.round(amountTotal + (amountTotal * 0.05));
                    yearsTotal++;
                    valuesLble.setText("Year: " + yearsTotal + "Amount: " + amountTotal);
                    Thread2 th2 = new Thread2(); 
                    th2.start();
                }
            }
         }
      }
        Thread2 th2 = new Thread2();
        th2.start();
    }
    
}

class Thread2 extends Thread {
    public void run() {
        try {
            for (int j = 0; j < 20; j++) {
                Thread.sleep(2000);
                System.out.println("Thread2");
            }
        } catch (InterruptedException e) {
           e.printStackTrace();
        }
    }
}

class DrawLine extends JPanel{

  int x1;
  int y1;
  int yearsVal;

  Timer time = new Timer(200, (ActionListener) this);

  public DrawLine(int Amount, int Years){
    this.x1 = Years + 80;
    this.y1 = Amount + 440;
     
    //time.start();
  }

  public DrawLine(){}
  
  public void animateLine(Graphics2D g){
    g.drawOval(x1, y1, 2, 2);
    System.out.println(x1 + "" + y1);
  }

  public void actionPerformed(ActionEvent arg0) {
      repaint();   
  }

  public void paintComponent(Graphics newG){
    super.paintComponent(newG);
    Graphics2D g2d = (Graphics2D)newG;
    animateLine(g2d);
    
    Graphics2D comp2D = (Graphics2D) newG;
    comp2D.setColor(Color.BLUE); 
    comp2D.drawLine(80, 80, 80, 600); 
    comp2D.drawLine(80, 600, 700, 600);
  }
}
