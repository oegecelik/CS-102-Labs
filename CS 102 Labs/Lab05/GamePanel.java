import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.Random;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel{
    Random rand = new Random();

    private GameFrame frame;
    private int scoreCount;
    private int currentScoreCount = 0;
    private static int callCount = 0;
    private static final int width = 640;
    private static final int height = 640;
    public static int score = 10;
    private static int divisor = 1;
    private static int currentCount = 0;
    private static int totalButtonCount;
    public static JButton[] buttons;
    private static int[] colors; 
    private static int initialDepth;
    private static buttonListener listener;
    public void setFrame(GameFrame frame){
        this.frame = frame; 
    }
    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            GamePanel.this.isGameOver();
            JButton clicked = (JButton) e.getSource();
            int index = -1;
            for(int i = 0; i<totalButtonCount; i++){
                if(clicked.equals(buttons[i])){
                    index = i;
                    break;
                }
            }

            int col = rand.nextInt(3);
            if(col == 0){
                clicked.setBackground(Color.RED);
            }
            else if(col == 1){
                clicked.setBackground(Color.GREEN);
            }
            else if(col == 2){
                clicked.setBackground(Color.BLUE);
            }
            colors[index] = col;
            boolean scored = false;

                      

            if(index % 4 == 0){
              if(colors[index] == colors[index+1] && colors[index]== colors[index+2] && colors[index] == colors[index+3]){
                scored = true;
                for(int i = index; i<=index+3; i++){
                    buttons[i].setBackground(Color.GRAY);
                    buttons[i].setEnabled(false);
                    colors[i] = -1;
                }
              }

            }
            else if(index % 4 == 1){
                if(colors[index] == colors[index-1] && colors[index]== colors[index+1] && colors[index] == colors[index+2]){
                    scored = true;
                    for(int i = index-1; i<=index+2; i++){
                        buttons[i].setBackground(Color.GRAY);
                        buttons[i].setEnabled(false);
                        colors[i] = -1;
                    }
                  }
            }
            else if(index % 4 == 2){
                if(colors[index] == colors[index-2] && colors[index]== colors[index-1] && colors[index] == colors[index+1]){
                    scored = true;
                    for(int i = index-2; i<=index+1; i++){
                        buttons[i].setBackground(Color.GRAY);
                        buttons[i].setEnabled(false);
                        colors[i] = -1;
                    }
                  }
            }
            else if(index % 4 == 3){
                if(colors[index] == colors[index-1] && colors[index]== colors[index-2] && colors[index] == colors[index-3]){
                    scored = true;
                    for(int i = index-3; i<=index; i++){
                        buttons[i].setBackground(Color.GRAY);
                        buttons[i].setEnabled(false);
                        colors[i] = -1;
                    }
                  }
            }
            if(scored){
                score += 10;
                currentScoreCount++;
            }
            score--;
            repaint();
            frame.updateTitle();
            if(score == 0){
                GamePanel.this.isGameOver();
            }
            if(currentScoreCount == scoreCount){
                GamePanel.this.isGameOver();
            }
        }
    }


    public GamePanel(int depth){
        super();
        if(depth<0){
            return;
        }

        if(callCount == 0){
            this.reset(); 
            listener = new buttonListener();
            totalButtonCount = (int) Math.pow(4, depth + 1);
            buttons = new JButton[totalButtonCount];
            colors = new int[totalButtonCount];
            initialDepth = depth;
            scoreCount = (int) Math.pow(4,initialDepth);
            callCount++;
        }
        divisor = (int) Math.pow(2, initialDepth-depth);
        this.setPreferredSize(new Dimension(width/divisor, height/divisor));
        if(depth != 0){
            depth--;
            this.setLayout(new GridLayout(2,2));
            for(int i = 0; i<4; i++){
                this.add(new GamePanel(depth));
            }
        }
        else {
            this.setLayout(new GridLayout(2, 2));
            for(int i = 0; i<4; i++){
                JButton button = new JButton();
                button.addActionListener(listener);
                int col = rand.nextInt(3);
                if(col == 0){
                    button.setBackground(Color.RED);
                }
                else if(col == 1){
                    button.setBackground(Color.GREEN);
                }
                else if(col == 2){
                    button.setBackground(Color.BLUE);
                }
                this.add(button);
                buttons[currentCount] = button;
                colors[currentCount] = col;
                currentCount++;

                if(i == 3){
                    currentCount--;
                    if(colors[currentCount] == colors[currentCount-1] && colors[currentCount] == colors[currentCount-2] && colors[currentCount] == colors[currentCount-3]){
                        for(int j = 0; j<=3; j++){                        
                            buttons[currentCount-j].setBackground(Color.GRAY);
                            buttons[currentCount-j].setEnabled(false);
                            colors[currentCount-j] = -1;
                        }
                    }  
                    currentCount++;                
                }
            }
        }
    }

    public int getScore(){
        return score;
    }
    public boolean isGameOver(){
        if(score <= 0){
            for(int i = 0; i<GamePanel.buttons.length; i++){
                GamePanel.buttons[i].setEnabled(false);
            }
            frame.GameOver();
            return true;
        }

        for(int i = 0; i<totalButtonCount; i++){
            if(colors[i] != -1){
                return false;
            }
        }

        frame.GameOver();
        return true;
    }
    public void reset(){
        score = 10;
        divisor = 1;
        currentCount = 0;
        listener = null;
        callCount = 0;
        currentScoreCount = 0;
    }

}