package tp1.handlers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import tp1.Fenetre;
import tp1.exceptions.InvalidSignalException;

public class ButtonHandler implements ActionListener {
    /**
     * Fenêtre principale.
     */
    private Fenetre fenetre;

    public ButtonHandler(Fenetre fenetre) {
        this.fenetre = fenetre;
    }

    /**
     * Déclenché au clic sur le bouton valider.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        try
        {
            this.fenetre.updateSignalPanel();
        }
        catch (InvalidSignalException ex) {
            JOptionPane.showMessageDialog(this.fenetre,
                    "Le signal entré contient un caractère invalide (" + ex.getCharacter() + ") à la position: " + ex.getCharPos());
        }
    }
}
