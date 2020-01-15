package tp1.drawables;

import java.awt.Color;
import java.awt.Graphics;

public class NRZDrawable implements IDrawableSignal {
    /**
     * Dessine un signal en NRZ.
     * @param g Graphique sur lequel dessiner.
     * @param signal Signal à représenter.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     */
    @Override
    public void draw(Graphics g, byte[] signal, DrawableModel model) {
        g.setColor(Color.RED);
        for (int i = 0; i < signal.length; ++i) {
            int currY = signal[i] == 1 ? (int)(model.diffHeight * 0.5) : (int)(model.diffHeight * 2.5);
            g.drawLine(model.diffWidth * i, currY, (model.diffWidth * i) + model.diffWidth, currY);

            if (i + 1 == signal.length) {
                continue;
            }

            if (signal[i] == 1 && signal[i + 1] == 0) {
                g.drawLine((model.diffWidth * i) + model.diffWidth, currY,
                        (model.diffWidth * i) + model.diffWidth, (int)(model.diffHeight * 2.5));
            } else if (signal[i] == 0 && signal[i + 1] == 1) {
                g.drawLine((model.diffWidth * i) + model.diffWidth, currY,
                        (model.diffWidth * i) + model.diffWidth, (int)(model.diffHeight * 0.5));
            }
        }
    }
}
