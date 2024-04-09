import java.awt.BorderLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class ImageFrame extends JFrame{
    class ButtonListener implements KeyListener{
        @Override
        public void keyPressed(KeyEvent e) {
            int delay = panel.getDelay();
            if(e.getKeyCode() == KeyEvent.VK_W){                
                if(delay > 10){
                    delay -= 10;
                }
                if(delay <= 10 && delay > 0){
                    delay--;
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_S){
                delay += 10;
            }
            if(e.getKeyCode() == KeyEvent.VK_B){
                if(panel.isTimerRunning() == false){
                    panel.setTimer(true);
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_E){
                if(panel.isTimerRunning() == true){
                    panel.setTimer(false);
                }
            }
            if(e.getKeyCode() == KeyEvent.VK_R){
                delay = 100;
                panel.setDelay(delay);
                panel.restartSorting();
            }
            label.setAlignmentX(CENTER_ALIGNMENT);
            panel.setDelay(delay);      
            label.setText("Press W to increase and S to decrease the speed. Current delay is: " + panel.getDelay() + " ms. Press R to restart sorting." 
            + " Press E to stop sorting and B to continue.");    
        }
        @Override
        public void keyReleased(KeyEvent e) {
            //EMPTY            
        }
        @Override
        public void keyTyped(KeyEvent e) {
            //EMPTY
            
        }
    }
    private ImageSorter panel;
    private JLabel label;
    public ImageFrame(String fileName){
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        panel = new ImageSorter(fileName);
        ButtonListener kListener = new ButtonListener();
        label = new JLabel("Press W to increase and S to decrease the speed. Current delay is: " + panel.getDelay() + " ms. Press R to restart sorting." 
         + " Press E to stop sorting and B to continue.", SwingConstants.CENTER);
         label.setAlignmentX(CENTER_ALIGNMENT);
        this.add(label, BorderLayout.NORTH);
        this.add(panel,BorderLayout.CENTER);
        this.setTitle("Image Sorter");
        this.pack();
        this.addKeyListener(kListener);
    }
  
    public static void main(String[] args) {
        new ImageFrame("pic");
    } 
}
