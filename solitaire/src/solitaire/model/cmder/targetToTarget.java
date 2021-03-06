/**
 * @file       targetToTarget.java
 * @brief      targetToTarget command implementation
 * @author     Peter Šuhaj
 * @author     Adrián Tóth
 */

package solitaire.model.cmder;

import solitaire.model.cards.*;

/**
 * Class for command representing movement from target deck to target deck.
 */
public class targetToTarget implements Commander {

    CardDeck target1;
    CardDeck target2;

    public targetToTarget(CardDeck target1,CardDeck target2) {
        this.target1 = target1;
        this.target2 = target2;
    }

    /**
     * Function executes this command.
     *
     * @return     True on success execution of command, false otherwise.
     */
    public boolean execute() {

        boolean retval = false;

        Card tmp = target1.pop();
        boolean success = target2.put(tmp);

        //if cant put card on target
        if (!success) {
            target1.put(tmp);
        }
        else {
            retval = true;
        }

        return retval;
    }

    /**
     * Function provide undo of this command.
     */
    public void undo() {

        Card tmp = this.target2.pop();
        this.target1.put(tmp);

    }

}
