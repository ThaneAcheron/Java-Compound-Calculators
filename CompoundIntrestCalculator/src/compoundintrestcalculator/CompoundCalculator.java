import java.awt.event.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*; 

public class CompoundCalculator {

  static JButton confirmBtn = new JButton("Submit"); 
  public static int yearsTotal = 0; 
  public static JFrame frame = new JFrame("Compound Intrest Calculator");
  public static int amountTotal = 0;
  public static JLabel valuesLble = new JLabel("Values");
  public static int YEARS_TOTAL; 
  public static int AMOUNT_TOTAL;
  
  public static void main(String[] args){     
  java.awt.EventQueue.invokeLater(new Runnable() {
  @SuppressWarnings("SleepWhileInLoop")
  public void run() {
      if (args.length == 2){
        if (Integer.parseInt(args[0]) >= 1 && Integer.parseInt(args[0]) <= 25){
          if (Integer.parseInt(args[1]) >= 20 && Integer.parseInt(args[0]) <= 200){
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setSize(800,800);
                frame.setVisible(true); 

                JLabel yearsTagLble = new JLabel("           Years");
                JLabel MoneyTagLble = new JLabel("Value           ");   

                frame.add(BorderLayout.SOUTH, valuesLble);
                frame.add(BorderLayout.WEST, yearsTagLble);
                frame.add(BorderLayout.EAST, MoneyTagLble);
                frame.add(BorderLayout.CENTER, new DrawLine());
                
                yearsTotal = Integer.parseInt(args[0]); 
                amountTotal = Integer.parseInt(args[1]);  
                AMOUNT_TOTAL = Integer.parseInt(args[1]);
                
                Thread2 t2 = new Thread2(); 
                t2.start(); 
            }
        }
      }
    }
  });  
 }
}

class Thread2 extends Thread {
    public void run() {
        try {
            CompoundCalculator cal = new CompoundCalculator();
            for(int i = 0; i < cal.yearsTotal + 1; i++){
                    Thread.sleep(200);
                    System.out.println(i);
                    cal.frame.add(new DrawLine(cal.amountTotal, i));  
                    cal.amountTotal = (int)Math.round(cal.amountTotal + (cal.amountTotal * 0.05));
                    cal.valuesLble.setText("Year:    " + i + "   Amount:   " + cal.amountTotal);
                    
                    if (i == cal.yearsTotal){
                        i = 0; 
                        cal.amountTotal = cal.AMOUNT_TOTAL;
                    }
                }
        } catch (InterruptedException e) {
        e.printStackTrace();
        }
    }
}

class DrawLine extends JPanel implements ActionListener{

  int x1;
  int y1;
  int yearsVal;

  Timer time = new Timer(200, (ActionListener) this);

  public DrawLine(int Amount, int Years){
    this.x1 = Years + 80 * Years;
    this.y1 = 600 - Amount;
     
    //time.start();
  }

  public DrawLine(){}
  
  public void animateLine(Graphics2D g){
    g.drawOval(x1, y1, 5, 5);
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