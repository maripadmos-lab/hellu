import java.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;

public class FlappyBird extends JPanel implements ActionListener, KeyListener { 
    private int width = 800; private int height = 600;
    private Rectangle bird = new Rectangle(width / 2 - 100, height / 2 - 10, 20, 20);
    private ArrayList<Rectangle> pipes = new ArrayList<>();
    private int ticks, yMotion, score;
    private boolean gameOver, started;

    public FlappyBird() {
        Timer timer = new Timer(20, this);
        jframe = new JFrame("Flappy Bird");
        jframe.add(this);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jframe.setSize(width, height);
        jframe.addKeyListener(this);
        jframe.setResizable(false);
        jframe.setVisible(true);

        addPipe(true);
        addPipe(true);
        timer.start();
    }

    public void addPipe(boolean start) {
        int space = 300;
        int widthPipe = 100;
        int minheight = 50;
        int maxheight = height - space - minheight;
        int h = minheight + new Random().nextInt(maxheight);
        
        if (start) {
            pipes.add(new Rectangle(width + widthPipe + pipes.size() * 300, height - h, widthPipe, h));
            pipes.add(new Rectangle(width + widthPipe + (pipes.size() - 1) * 300, 0, widthPipe, height - h - space));
        } else {
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x + 600, height - h, widthPipe, h));
            pipes.add(new Rectangle(pipes.get(pipes.size() - 1).x, 0, widthPipe, height - h - space));
        }
    }

private void jump() {
    if (gameOver) {
        bird = new Rectangle(width / 2 - 100, height / 2 - 10, 20, 20);
        pipes.clear();
        yMotion = 0;
        score = 0;
        addPipe(true);
        addPipe(true);
        gameOver = false;
    }
    if (!started) started = true;
    if (yMotion > 0) yMotion = 0;
    yMotion -= 10;
}


@Override
public void actionPerformed(ActionEvent e) {
    if (started && !gameOver) {
        ticks++;
       for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            pipe.x -= 10;
        }
        if (ticks % 2 == 0 && yMotion < 15) yMotion += 2;
    
        for (int i = 0; i < pipes.size(); i++) {
            Rectangle pipe = pipes.get(i);
            if (pipe.x + pipe.width < 0) {
                pipes.remove(pipe);
                if (pipe.y == 0) addPipe(false);
            }
        }
        bird.y += yMotion;

        for (Rectangle pipe : pipes) {
                if (pipe.intersects(bird)) gameOver = true;
            }
            if (bird.y > height || bird.y < 0) gameOver = true;
        }
        repaint();
    }

@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    g.setColor(Color.cyan);
    g.fillRect(0, 0, width, height);
    g.setColor(Color.orange);
    g.fillRect(0, height - 120, width, 120);
    g.setColor(Color.red);
    g.fillRect(bird.x, bird.y, bird.width, bird.height);

    for (Rectangle pipe : pipes) {
    g.setColor(Color.green);
    g.fillRect(pipe.x, pipe.y, pipe.width, pipe.height);
    }
   
    g.setColor(Color.white);
        g.setFont(new Font("Arial", Font.BOLD, 50));
        if (!started) g.drawString("Press Space to Start", 175, height / 2 - 50);
        if (gameOver) g.drawString("Game Over!", 275, height / 2 - 50);
    }

    public static void main(String[] args) { new FlappyBird(); }
    public void keyPressed(KeyEvent e) { if (e.getKeyCode() == KeyEvent.VK_SPACE) jump(); }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}