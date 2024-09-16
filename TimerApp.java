import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerApp extends JFrame {
    private JLabel timerLabel;
    private JButton startButton, stopButton;
    private Timer timer;
    private long startTime;
    private long elapsedTime = 0; // Time in milliseconds
    private boolean isRunning = false;

    public TimerApp() {
        // Set up JFrame
        setTitle("Timer App");
        setSize(300, 150);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Create components
        timerLabel = new JLabel("Time: 00:00:00:000", SwingConstants.CENTER);
        timerLabel.setBounds(50, 20, 200, 30);

        startButton = new JButton("Start");
        startButton.setBounds(50, 70, 80, 30);

        stopButton = new JButton("Stop");
        stopButton.setBounds(170, 70, 80, 30);

        // Add components to JFrame
        add(timerLabel);
        add(startButton);
        add(stopButton);

        // Timer setup
        timer = new Timer(1, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long currentTime = System.currentTimeMillis();
                long timeDifference = currentTime - startTime + elapsedTime;
                updateTimerLabel(timeDifference);
            }
        });

        // Button actions
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!isRunning) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                    isRunning = true;
                }
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (isRunning) {
                    elapsedTime += System.currentTimeMillis() - startTime;
                    timer.stop();
                    isRunning = false;
                }
            }
        });
    }

    private void updateTimerLabel(long time) {
        int hours = (int) (time / 3600000);
        int minutes = (int) ((time % 3600000) / 60000);
        int seconds = (int) ((time % 60000) / 1000);
        int milliseconds = (int) (time % 1000);
        
        timerLabel.setText(String.format("%02d:%02d:%02d:%03d", hours, minutes, seconds, milliseconds));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TimerApp().setVisible(true);
            }
        });
    }
}
