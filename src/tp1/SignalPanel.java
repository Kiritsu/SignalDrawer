package tp1;

import java.awt.Color;
import java.awt.Graphics;
import java.util.HashMap;
import javax.swing.JPanel;
import tp1.drawables.*;
import tp1.exceptions.InvalidSignalException;

public class SignalPanel extends JPanel {
    /**
     * Fenêtre principale.
     */
    private Fenetre fenetre;

    /**
     * Signal à dessiner.
     */
    private byte[] signal;

    /**
     * Méthode à utiliser pour dessiner le signal.
     */
    private SignalMethod method;

    /**
     * HashMap contenant pour chacune des méthodes de signal, une instance de classe implémentant tp1.drawables.IDrawableSignal.
     */
    private HashMap<SignalMethod, IDrawableSignal> drawables;

    public SignalPanel(Fenetre fenetre) {
        this.fenetre = fenetre;
        this.init();
    }

    /**
     * Initialise les différents drawables.
     */
    private void init() {
        this.drawables = new HashMap<>();
        this.drawables.putIfAbsent(SignalMethod.NRZ, new NRZDrawable());
        this.drawables.putIfAbsent(SignalMethod.Manchester, new ManchesterDrawable());
        this.drawables.putIfAbsent(SignalMethod.ManchesterDiff, new ManchesterDiffDrawable());
        this.drawables.putIfAbsent(SignalMethod.Miller, new MillerDrawable());
    }

    /**
     * Indique au contrôle de récupérer les valeurs de la fenêtre principale.
     */
    public void redrawSignal() throws InvalidSignalException {
        this.signal = this.fenetre.getSignal();
        this.method = this.fenetre.getSelectedMethod();
    }

    /**
     * Redessine le signal en fonction de la méthode sélectionnée.
     * @param g Graphique sur lequel dessiner.
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        try {
            this.redrawSignal();
        } catch (InvalidSignalException e) {
            return;
        }

        int nbSig = this.signal.length;

        if (nbSig == 0) {
            return;
        }

        int widthMax = this.fenetre.getWidth();
        int diffWidth = widthMax / (nbSig + 1);

        int thisHeight = this.getHeight();
        int diffHeight = thisHeight / 3;

        g.setColor(Color.BLACK);
        g.drawLine(0, (int)(diffHeight * 0.5), widthMax, (int)(diffHeight * 0.5));
        g.drawLine(0, (int)(diffHeight * 1.5), widthMax, (int)(diffHeight * 1.5));
        g.drawLine(0, (int)(diffHeight * 2.5), widthMax, (int)(diffHeight * 2.5));

        for (int i = 0; i < nbSig; ++i) {
            g.drawString(Byte.toString(this.signal[i]), (int)((diffWidth * (i + 1)) - (diffWidth / 2)), 10);
            g.drawLine(diffWidth * (i + 1), 15, diffWidth * (i + 1), thisHeight);
        }

        this.drawables.get(this.method).draw(g, this.signal,
                new DrawableModel(widthMax, diffWidth, thisHeight, diffHeight));
    }

}
