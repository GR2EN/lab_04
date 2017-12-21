package com.gr2en;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JFrame;

class SegmentRotation extends javax.swing.JPanel {
    private static final String FRAME_TITLE = "Segment Rotation";
    private static final int FRAME_HEIGHT = 480;
    private static final int FRAME_WIDTH = 480;
    private static final int TIMER_DELAY = 1; // 0.001s
    private static final double SEGMENT_ROTATION_STEP = 0.001;

    private AffineTransform mAffineTransform;

    private SegmentRotation() {
        Timer timer = new Timer();
        timer.schedule(new MyTimerTask(), 0, TIMER_DELAY);
    }

    private class MyTimerTask extends TimerTask {
        int i = 0;

        @Override
        public void run() {
            // Rotate a segment
            mAffineTransform = AffineTransform.getRotateInstance(i++ * SEGMENT_ROTATION_STEP,
                    FRAME_WIDTH/2, FRAME_HEIGHT/2); // around center of screen
            repaint();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Constructs 2D-object
        Graphics2D g2d = (Graphics2D) g;
        g2d.transform(mAffineTransform);

        // Draw the segment
        g2d.draw(new Line2D.Float(FRAME_WIDTH/3, FRAME_HEIGHT/3, // Length of a segment
                FRAME_WIDTH / 2, FRAME_HEIGHT / 2)); // Center of screen
    }

    public static void main(String[] args) {
        // Create a new JFrame
        javax.swing.JFrame jFrame = new JFrame(FRAME_TITLE);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set the frame size
        jFrame.setSize(FRAME_WIDTH, FRAME_HEIGHT);

        // Center the JFrame on screen
        jFrame.setLocationRelativeTo(null);

        // Set the contentPane for the JFrame
        jFrame.setContentPane(new SegmentRotation());

        //Display the JFrame on screen
        jFrame.setVisible(true);
    }
}