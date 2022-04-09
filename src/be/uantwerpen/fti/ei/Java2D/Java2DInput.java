package be.uantwerpen.fti.ei.Java2D;
import be.uantwerpen.fti.ei.space.Input;
// Imports used for keyboard inputs.
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * This class is used to get the inputs from the keyboard.
 * @author Bavo Lesy
 * @version 1.0
 */
public class Java2DInput extends Input{
    private final LinkedList<Inputs> keyInputs;
    public Java2DInput(Java2DWorld gr) {
        gr.getFrame().addKeyListener(new KeyInputAdapter()); // Activate key input
        keyInputs = new LinkedList<>(); // Create an empty linked list used to add the inputs
    }
    public boolean inputAvailable() {
        return keyInputs.size() > 0;
    }
    public Inputs getInput() {
        return keyInputs.poll();
    }
    /**
     * This class links the keys pressed to one of the enum we use in our project.
     * @author Bavo Lesy
     * @version 1.0
     */
    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT: // Move left.
                    keyInputs.add(Inputs.LEFT);
                    break;
                case KeyEvent.VK_RIGHT: // Move right.
                    keyInputs.add(Inputs.RIGHT);
                    break;
                case KeyEvent.VK_SPACE: // Shoot bullets.
                    keyInputs.add(Inputs.SPACEBAR);
                    break;
                case KeyEvent.VK_ENTER: // Pause or start the game.
                    keyInputs.add(Inputs.ENTER);
                    break;
                case KeyEvent.VK_SHIFT: // Stand still.
                    keyInputs.add(Inputs.SHIFT);
                    break;
            }
        }
    }
}


