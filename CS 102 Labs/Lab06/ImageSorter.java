import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.Color;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ImageSorter extends JPanel{
    private String fileName;
    private int delay = 100;
    private int sortedHeight = 0;
    private int sortedWidth = 0;
    private boolean isDoneH = false;
    private boolean isDoneV = false;
    private double[][] pixels;
    private int width;
    private int height;
    private BufferedImage image;
    private Timer timer;
    private TimerListener timerListener;
    //private ButtonListener kListener;
    private JLabel label;
    class TimerListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            if(isDoneH == true && isDoneV == true){
                timer.stop();
            }
            else{
                diagonalStep();
            }
        }
    }
    public ImageSorter(String fileName){
        this.fileName = fileName;
        loadImage(fileName);
        
        label = new JLabel(new ImageIcon(image));
        this.add(label);
        timerListener = new TimerListener();
        timer = new Timer(delay, timerListener);
        width = image.getWidth();
        height = image.getHeight();
        pixels = new double[height][width];
        for(int i = 0; i<height; i++){
            for(int j = 0; j<width; j++){
                Color c = new Color(image.getRGB(j, i));
                int red = c.getRed();
                int green = c.getGreen();
                int blue = c.getBlue();
                double brightness = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
                pixels[i][j] = brightness;
            }
        }
        startAnimatedBubbleSort();        
    }
    public void loadImage(String fileName){
        try{
            image = ImageIO.read(getClass().getResource(fileName + ".png"));
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }
    public void displayImage(){
        repaint();
    }
    public void horizontalStep(){
        boolean isSwappedH = false;
            for(int i = 0; i<height; i++){
                int rgbTemp;
                double brightnessTemp;
                for(int k = 0; k<width-sortedWidth-1; k++){
                    if(pixels[i][k] < pixels[i][k+1]){
                        isSwappedH = true;
                        brightnessTemp = pixels[i][k];
                        rgbTemp = image.getRGB(k, i);
                        image.setRGB(k, i, image.getRGB(k+1, i));
                        image.setRGB(k+1, i, rgbTemp);
                        pixels[i][k] = pixels[i][k+1];
                        pixels[i][k+1] = brightnessTemp;
                    }
                }
            }
            sortedWidth++;
            isDoneH = !isSwappedH;
    }
    public void verticalStep(){
        boolean isSwappedV = false;
            for(int i = 0; i<width; i++){
                int rgbTemp;
                double brightnessTemp;
                for(int k = 0; k<height-sortedHeight-1; k++){
                    if(pixels[k][i] < pixels[k+1][i]){
                        isSwappedV = true;
                        brightnessTemp = pixels[k][i];
                        rgbTemp = image.getRGB(i, k);
                        image.setRGB(i, k, image.getRGB(i, k+1));
                        image.setRGB(i,k+1, rgbTemp);
                        pixels[k][i] = pixels[k+1][i];
                        pixels[k+1][i] = brightnessTemp;
                        displayImage();
                    }
                }
            }
            sortedHeight++;
            isDoneV = !isSwappedV;            
    }
    public void diagonalStep(){
        if(isDoneH == false){
            horizontalStep();
        }
        if(isDoneV == false){
            verticalStep();
        }
        displayImage();
    }
    public void startAnimatedBubbleSort(){
        timer.start();
    }
    public void restartSorting(){
        this.removeAll();
        this.revalidate();
        this.repaint();
        loadImage(fileName);
        this.add(new JLabel(new ImageIcon(image)));

       isDoneH = false;
       isDoneV = false;
       sortedHeight = 0;
       sortedWidth = 0;
       for(int i = 0; i<height; i++){
        for(int j = 0; j<width; j++){
            Color c = new Color(image.getRGB(j, i));
            int red = c.getRed();
            int green = c.getGreen();
            int blue = c.getBlue();
            double brightness = 0.2126 * red + 0.7152 * green + 0.0722 * blue;
            pixels[i][j] = brightness;
        }
        startAnimatedBubbleSort();
    }

    }
    public int getDelay(){
        return this.delay;
    }
    public void setDelay(int delayChanged){
        timer.setDelay(delayChanged);
        this.delay = delayChanged;
    }
    public boolean isTimerRunning(){
        return timer.isRunning();
    }
    public void setTimer(boolean startStop){
        if(startStop == true){
            timer.start();
        }
        else{
            timer.stop();
        }
    }

}