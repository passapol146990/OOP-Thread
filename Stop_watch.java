import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Stop_watch {
    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBounds(50,50,500,200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setLayout(null);

        // Status status = new Status(true);
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0,0,500,100);

        JLabel label = new JLabel("00:00:00:000");
        label.setBounds(215,50,100,50);
        panel.add(label);
        
        // RunThread runThread = new RunThread(label,status);
        // runThread.start();

        JButton button = new JButton("start/stop");
        button.setBounds(200,100,100,20);

        frame.add(panel);
        frame.add(button);
    }
}


class Time_Count extends JLabel{
        
}

class Status {
    private boolean status=true;
    Status(boolean status){
        this.status = status;
    }
    public void changeStatus(){
        this.status=(this.status)?false:true;
    }
    public boolean getStatus(){
        return this.status;
    }
}

class RunThread extends Thread{
    private JLabel label;
    private Status status;
    private int timer=0;
    RunThread(JLabel label,Status status){
        this.label = label;
        this.status = status;
    }
    public void run(){
        while (true) {
            if(status.getStatus()){
                this.timer += 1;
                int hours = (int) (this.timer / 3600000);
                int minutes = (int) ((this.timer % 3600000) / 60000);
                int seconds = (int) ((this.timer % 60000) / 1000);
                int milliseconds = (int) (this.timer % 1000);
                label.setText(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds));
                try{Thread.sleep(100);}catch(InterruptedException e){}
            }
        }
    }
}





