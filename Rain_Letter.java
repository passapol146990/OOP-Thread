import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

class Rain_Letter{
    private boolean status = true;
    public static void main(String args[]){
        JFrame frame = new JFrame();
        frame.setBounds(50,50,500,500);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null);
        JPanel app = new JPanel();
        app.setSize(500,500);
        app.setLayout(new GridLayout(1,24));
        app.setBounds(0,0,500,400);

        Status status = new Status(true);

        for(int i=(int)'A'; i<=(int)'Z'; i++){
            char x = (char)i;
            panelText panel = new panelText(String.valueOf(x));
            RunThread thread = new RunThread(panel, status);
            thread.start();
            app.add(panel);
        }
        JButton button = new JButton("start/stop");
        button.setBounds(200,430,100,20);
        button.addActionListener(e->{
            status.changeStatus();
        });
        frame.add(app);
        frame.add(button);
    }
}
class Status{
    private boolean status = true;
    Status(boolean status){
        this.status = status;
    }
    public void changeStatus(){
        this.status = (this.status)?false:true;
    }
    public boolean getStatus(){
        System.out.println(this.status);
        return this.status;
    }
}

class panelText extends JPanel{
    private int positiony=50;
    private int intMove=50;
    private String title;
    private Color color;
    panelText(String title){
        this.title = title;
    }
    public void runTime(){
        if(this.positiony>=400){
            this.positiony = 50;
            this.intMove = (int)(Math.random()*10+1);
            this.color = new Color(new Random().nextInt(0,255),new Random().nextInt(0,255),new Random().nextInt(0,255));
        }
        this.positiony = this.positiony + this.intMove;
        repaint();
    }
    public void paint(Graphics g) {
        super.paint(g);
        g.setColor(this.color);
        g.drawString(this.title,0,this.positiony);
    }
}

class RunThread extends Thread {
    private panelText panel;
    private Status status;
    RunThread(panelText panel){
        this.panel = panel;
    }
    RunThread(panelText panel, Status status){
        this.panel = panel;
        this.status = status;
    }
    public void run(){
        while (true) {
            if(this.status.getStatus()){
                this.panel.runTime();
                try{Thread.sleep(10);}catch(InterruptedException e){}
            }
        }
    }
}


