import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.Timer;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class BusFrame extends JFrame{
    private final int WINDOW_SIZE = 60;
    private int wheelCount = 2;
    private int busLength = 300;
    private final int maxRadius = 40;
    private final int FRAME_HEIGHT = 800;
    private final int FRAME_WIDTH = 800;
    private final int FIELD_WIDTH = 4;
    private final int DELAY = 10;
    private int xMove = 3;
    private int xOfLeft = FRAME_WIDTH/4;
    private int xOfRight = xOfLeft + busLength;
    
    private JButton playButton;
    private JButton stopButton;
    private JButton updateButton;
    private Timer animationTimer;
    private JTextField lengthTextField;
    private JTextField wheelTextField;
    private JLabel wheel;
    private JLabel length;

    private JPanel topPanel; //add to this.Border North
    private JPanel textPanel; //add to topPanel west
    private JPanel buttonPanel; //add to topPanel east
    private drawingPanel drawingPanel; //the main panel containing the bus

    class buttonListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(event.getSource() == playButton){
                animationTimer.start();
            }
            else if(event.getSource() == stopButton){
                    animationTimer.stop();
            }
            else if(event.getSource() == updateButton){
                String text1 = lengthTextField.getText();
                busLength = Integer.parseInt(text1);
                String text2 = wheelTextField.getText();
                wheelCount = Integer.parseInt(text2);
                xOfLeft = 1;
                xOfRight = xOfLeft + busLength;
            }
            else if(event.getSource() == animationTimer){
                xOfRight = xOfLeft + busLength;
                if((xOfLeft <= 0) || (xOfRight >= FRAME_WIDTH)){
                    xMove *= -1;
                }
            }
            drawingPanel.repaint();
        }
    }

    

    public BusFrame(){
        this.setTitle("Bus Animation");
        createComponents();
        setSize(FRAME_WIDTH, FRAME_HEIGHT);
    }
    private void createComponents(){
        topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());

        buttonPanel = new JPanel();
        playButton = new JButton("Play");
        stopButton = new JButton("Stop");        
        buttonPanel.setLayout(new GridLayout(2,1));
        buttonPanel.add(playButton);
        buttonPanel.add(stopButton);

        textPanel = new JPanel();
        lengthTextField = new JTextField(FIELD_WIDTH);
        wheelTextField = new JTextField(FIELD_WIDTH);
        length = new JLabel("Length: ");
        wheel = new JLabel("Wheel: ");
        updateButton = new JButton("Update");
        textPanel.setLayout(new GridLayout(3,2));
        textPanel.add(length);
        textPanel.add(lengthTextField);
        textPanel.add(wheel);
        textPanel.add(wheelTextField);
        textPanel.add(updateButton);

        topPanel.add(textPanel, BorderLayout.WEST);
        topPanel.add(buttonPanel, BorderLayout.EAST);
        this.getContentPane().add(topPanel, BorderLayout.NORTH);

        drawingPanel = new drawingPanel();

        buttonListener listener = new buttonListener();
        playButton.addActionListener(listener);
        stopButton.addActionListener(listener);
        updateButton.addActionListener(listener);
        
        this.getContentPane().add(drawingPanel, BorderLayout.CENTER);

        animationTimer = new Timer(DELAY, listener);
        animationTimer.start();
        repaint();
    }
    class drawingPanel extends JPanel{
        public drawingPanel(){
            setPreferredSize(new Dimension(FRAME_WIDTH, FRAME_HEIGHT/2*5/4));
        }
        public void paint(Graphics g){
            super.paint(g);
            g.setColor(Color.GREEN);
            g.fillRect(0, FRAME_HEIGHT/2,FRAME_WIDTH, FRAME_HEIGHT/2);
            g.setColor(Color.YELLOW);
            int yOfBottomRectangle = FRAME_HEIGHT/8*4-20;
            int heightOfBottomRectangle = FRAME_HEIGHT/16*3/2;

            //The rectangle forming the top part of the bus
            g.fillRect(xOfLeft += xMove, yOfBottomRectangle-heightOfBottomRectangle, 6*busLength/7, FRAME_HEIGHT/8);

            //The longer rectangle of the bus
            g.fillRect(xOfLeft, yOfBottomRectangle, busLength, heightOfBottomRectangle);

            //Drawing the windows
            int windowedPartTotalLength = 6*busLength/7;
            int remainder = windowedPartTotalLength % (WINDOW_SIZE);
            int windowCount = windowedPartTotalLength/WINDOW_SIZE;
            if(remainder == 0){
                remainder = WINDOW_SIZE;
                windowCount--;
            }
            int betweenTwoWindows = remainder / (windowCount+1);
            if(betweenTwoWindows == 0){
                windowCount--;
                remainder += WINDOW_SIZE;
                betweenTwoWindows = remainder / (windowCount+1);
            }
            int xOfWindows = xOfLeft + betweenTwoWindows;
            int yOfWindows =  yOfBottomRectangle-heightOfBottomRectangle+10;
            g.setColor(Color.CYAN);
            for(int i = 1; i<=windowCount; i++){
          
                //if(xOfWindows + WINDOW_SIZE<=xOfRight-busLength/7){
                    g.fillRect(xOfWindows, yOfWindows, WINDOW_SIZE, WINDOW_SIZE);
                    xOfWindows += WINDOW_SIZE + betweenTwoWindows; 
                //}                
                     
            }
            //Drawing the wheels
            g.setColor(new Color(150,75,0)); //RGB of brown
            boolean check = false;
            int radiusCount = wheelCount*6;
            int radius = windowedPartTotalLength/radiusCount;
            if(radius > maxRadius){
                check = true;
                radius = maxRadius;
            }
            int wheelSize = 4*radius;
            int wheelX = xOfLeft;
            int wheelY =yOfBottomRectangle +heightOfBottomRectangle -wheelSize/2;
            int interval = radius+windowedPartTotalLength%radiusCount/wheelCount/2;
            if(check == true){
                interval = (windowedPartTotalLength-(wheelSize*wheelCount))/wheelCount/2;
            }


            for(int i = 1; i<=wheelCount; i++){
             
                wheelX += interval;
                g.fillOval(wheelX, wheelY, wheelSize, wheelSize);
                wheelX += wheelSize + interval;
            }

        }
    }
   
}
