import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.GridLayout;    
import java.awt.BorderLayout;
import java.awt.Color;    
class Random_Color{
    public static void main(String args[]){
        JFrame  frame = new JFrame();
        frame.setBounds(50,50,500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Panel
        JPanel app = new JPanel();
        app.setSize(500,500);
        app.setLayout(new GridLayout(2,2));    
        frame.add(app);
        int[] time = {5000,10000,(int)(Math.random()*5+1)*1000,(int)(Math.random()*5+5)*1000};
        for(int i=0;i<4;i++){
            RunThread thread = new RunThread();
            JPanel panel = new JPanel();
            JLabel title = new JLabel("Change Color in "+(int)(time[i]/5)+" sec");
            thread.setLabels(time[i],i,title);
            panel.add(title);
            app.add(panel);
            thread.start();
        }

    }
}

class RunThread extends Thread{
    JLabel label;
    int timeSleep;
    void setLabels(int timeSleep,int index,JLabel label){
        this.timeSleep = timeSleep;
        this.label = label;
    }

    public void run(){
        while (true) {
            this.label.setForeground(new Color((int)(Math.random()*255),(int)(Math.random()*255),(int)(Math.random()*255)));
            try{Thread.sleep(this.timeSleep);}catch(InterruptedException e){}
        }
    }
    
}