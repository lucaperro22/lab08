package it.unibo.mvc;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * This class is a simple application that writes a random number on a file.
 *
 * <p>
 * This application DOES NOT exploit the model-view-controller pattern, and as
 * such is just to be used to learn the basics, NOT AS A TEMPLATE for your
 * applications.
 */
public class MiniGUI {

    private static final String TITLE = "A very simple GUI application";
    private static final int PROPORTION = 5;
    private final Random randomGenerator = new Random();
    private final JFrame frame = new JFrame(TITLE);

    /* ## Do
    ### Part 1
    1. Create a new `JPanel`
    2. Use an horizontal `BoxLayout` as layout
    3. Set the new `JPanel` as the only content of the center of the previously existing `BorderLayout`
    4. Add the `JButton` to the new panel
    5. Test your application: it should appear similar, but the button will get smaller
    6. In `display()`, use `JFrame.pack()` to resize the frame to the minimum size prior to displaying */ 

    /**
     * Creates a new {@link MiniGUI}.
     */
    public MiniGUI() { 
        final JPanel canvas = new JPanel(); //due panelli uno nell'altro esterno e interno rispettivamente canva e mycanva 
        final JPanel Mycanvas = new JPanel();
        canvas.setLayout(new BorderLayout());
        Mycanvas.setLayout(new BoxLayout(Mycanvas, BoxLayout.X_AXIS)); //container e' il panel in cui inserisco il box layout
        final JButton write = new JButton("Print a random number on standard output");
        //canvas.add(write, BorderLayout.CENTER);
        Mycanvas.add(write);
        JLabel result = new JLabel("Result");
        canvas.add(result, BorderLayout.NORTH); //leggi accuratamente la consegna
        canvas.add(Mycanvas);
        frame.setContentPane(canvas); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        /*
         * Handlers
         */
        write.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(final ActionEvent e) {
                int number; 
                number=randomGenerator.nextInt();
                IO.println(number);
                result.setText(Integer.toString(number)); 
            }
        });
    }

    private void display() {
        /*
         * Make the frame one fifth the resolution of the screen. This very
         * method is enough for a single screen setup. In case of multiple
         * monitors, the primary is selected. In order to deal coherently with
         * multimonitor setups, other facilities exist (see the Java
         * documentation about this issue). It is MUCH better than manually
         * specify the size of a window in pixel: it takes into account the
         * current resolution.
         */
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / PROPORTION, sh / PROPORTION);
        /*
         * Instead of appearing at (0,0), upper left corner of the screen, this
         * flag makes the OS window manager take care of the default positioning
         * on screen. Results may vary, but it is generally the best choice.
         */
        frame.setLocationByPlatform(true);
        /*
         * Resize the frame to minimum size
         */
        frame.pack();
        /*
         * OK, ready to pull the frame onscreen
         */
        frame.setVisible(true);
    }

    /**
     * Launches the application.
     */
    public static void main() {
        new MiniGUI().display();
    }

}
