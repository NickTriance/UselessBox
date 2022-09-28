//4-5-22 i spent 2 hours coding this...
import java.awt.Robot;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.PointerInfo;
import java.awt.MouseInfo;
import java.awt.Point;
import java.util.Random;

public class Uselessbox {

    JFrame frame;
    JLabel label;

    String[] messages = new String[] {
        "Hello there!",
        "Hello!",
        "Goodbye!",
        "Hello and goodbye!",
        "Five more minutes, please",
        "I'm trying to sleep >:(",
        "Oi!",
        "Howdy!",
        "Go away.",
        "Fuck off.",
        "What did you expect?",
        "Sample Text.",
        "TODO: Write something clever here.",
        "You are now breathing manually.",
        "Boo!",
        "Beep",
        "Boop",
        "Bleep-blorp, death to all humans.",
        "0100 0101, nice.",
        "According to all known laws, of aviation,\n there is no reason a bee should be able to fly. \nIts wings are simply too small to get its fat little body off the ground. \nThe bee, of course, flies anyway, because bees don't care what humans think is impossible.",
        "I have brought peace, freedom, justice, and security to my new Empire. Your new Empire? Don't make me kill you. Anakin, my alligence is to the Republic, to DEMOCRACY! I will do what I must. You will try!"
    };

    public Uselessbox() throws Exception {

        frame = new JFrame();
        frame.setLayout(new GridLayout(1,1));
        Random rand = new Random(System.currentTimeMillis());
        label = new JLabel(messages[rand.nextInt(messages.length)]);
        label.setHorizontalAlignment(JLabel.CENTER);
        label.setFont(new Font(label.getFont().getName(), label.getFont().getStyle(), 40));
        frame.add(label);
        frame.setSize(640, 480);
        frame.setTitle("word.exe");
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        this.movemouse();
    }

    void movemouse() throws Exception{
        
        Robot robot = new Robot();
        Thread mousemover = new Thread(new Runnable() {
            @Override
            public void run() {
                
                int targetX = frame.getX() + 610;
                int targetY = frame.getY() + 15;
                PointerInfo info = MouseInfo.getPointerInfo();
                Point current = info.getLocation();
                int curX = (int) current.getX();
                int curY = (int) current.getY();
                int totalDistance = (int) Math.sqrt(Math.pow((curX - targetX), 2) + Math.pow((curY - targetX), 2));
                int sleepAmt = 3000 / totalDistance;

                while (curX != targetX || curY != targetY) {
                    
                    info = MouseInfo.getPointerInfo();
                    current = info.getLocation();
                    curX = (int) current.getX();
                    curY = (int) current.getY();
                    int nextX;
                    int nextY;

                    if (curX == targetX) {
                        nextX = curX;
                    } else if (curX < targetX) {
                        nextX = curX + 1;
                    } else {
                        nextX = curX - 1;
                    }

                    if (curY == targetY) {
                        nextY = curY;
                    } else if (curY < targetY) {
                        nextY = curY + 1;
                    } else {
                        nextY = curY - 1;
                    }

                    robot.mouseMove(nextX, nextY);
                    frame.setLocationRelativeTo(null);
                    try {
                        Thread.sleep(sleepAmt);
                    } catch (Exception e) {
                        System.out.println("I don't even know: " + e.toString());
                    }
                }
                
                robot.mousePress(MouseEvent.BUTTON1_DOWN_MASK);
                try {
                    Thread.sleep(250);
                } catch (Exception e) {
                    System.out.println("So close...: " + e.toString());
                }
                robot.mouseRelease(MouseEvent.BUTTON1_DOWN_MASK);
                System.exit(0);
            }
        });
        mousemover.run();
    }

    public static void main(String[] args) throws Exception{
        new Uselessbox();
    }
}