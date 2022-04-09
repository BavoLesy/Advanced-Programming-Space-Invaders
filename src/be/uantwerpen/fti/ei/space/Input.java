package be.uantwerpen.fti.ei.space;
/**
 * This class is used to declare the different inputs used.
 * @author Bavo Lesy
 * @version 1.0
 */
public abstract class Input{
    // An enum is a special data type that allows for a variable to be set of predefined values (constants are uppercase).

    /**
     * This enum is used for our keyboard inputs.
     * An enum is a special data type that allows for a variable to be set of predefined values (constants are uppercase).
     */
    public enum Inputs {LEFT,RIGHT,SPACEBAR,ENTER,SHIFT} // The different keyboard inputs used.
    public abstract Inputs getInput(); // Getter used to access the inputs.
    public abstract boolean inputAvailable(); // If there is an input being given: no other input available!
}
