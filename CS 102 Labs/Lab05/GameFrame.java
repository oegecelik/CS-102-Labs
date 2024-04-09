import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class GameFrame extends JFrame{
    private int depth = 2;
    private JFrame diJFrame;
    private JButton yes;
    private JButton no;
    private JPanel dialogue;
    private buttonListener listener;
    GamePanel panel;
    public GameFrame(){        
        super();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    
        yes = new JButton("Yes");
        no = new JButton("No");
        dialogue = new JPanel();
        dialogue.add(new JLabel("Play Again?"));
        dialogue.add(yes);
        dialogue.add(no);
        listener = new buttonListener();
        yes.addActionListener(listener);
        no.addActionListener(listener);    
        panel = new GamePanel(depth);
        panel.setFrame(this);       
        this.add(panel,BorderLayout.CENTER);
        
        GameFrame.this.setTitle("Game Frame   Score: " + GamePanel.score);
    } 
    
    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent e){     
            if(e.getSource() == yes){
                GameFrame.this.reset();
                diJFrame.dispose();
            }
            else if(e.getSource() == no){
                GameFrame.this.dispose();
                diJFrame.dispose();
            }
        }
    }
    public void GameOver(){
        diJFrame = new JFrame();
        diJFrame.setSize(200, 200);;
        diJFrame.add(dialogue);
        diJFrame.setVisible(true);
        diJFrame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    }
    public void updateTitle(){
        GameFrame.this.setTitle("Game Frame   Score: " + GamePanel.score);
    }

    
    public void reset(){
        panel.reset();
        GameFrame restart = new GameFrame();
        restart.setVisible(true);
        restart.setSize(800,800);
        this.dispose();
    }

    public static void main(String[] args) {
            GameFrame game = new GameFrame();
            game.setVisible(true);
            game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            game.setSize(800, 800);
    }
}
  
    

