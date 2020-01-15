package tp1.drawables;

import java.awt.Color;
import java.awt.Graphics;

public class ManchesterDrawable implements IDrawableSignal {
    /**
     * Dessine un signal en Manchester.
     * @param g Graphique sur lequel dessiner.
     * @param signal Signal à représenter.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     */
    @Override
    public void draw(Graphics g, byte[] signal, DrawableModel model) {
        g.setColor(Color.RED);

        for (int i = 0; i < signal.length; ++i) {
            if (signal[i] == 1) {
                g.drawLine(model.diffWidth * i,
                        (int)(model.diffHeight * 0.5),
                        (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 0.5));

                g.drawLine((i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 0.5),
                        (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 2.5));

                g.drawLine((i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 2.5),
                        model.diffWidth * (i + 1),
                        (int)(model.diffHeight * 2.5));
            } else {
                g.drawLine(model.diffWidth * i,
                        (int)(model.diffHeight * 2.5),
                        (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 2.5));

                g.drawLine((i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 2.5),
                        (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 0.5));

                g.drawLine((i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2),
                        (int)(model.diffHeight * 0.5),
                        model.diffWidth * (i + 1),
                        (int)(model.diffHeight * 0.5));
            }

            if (i + 1 == signal.length) {
                continue;
            }

            if ((signal[i] == 1 && signal[i + 1] == 1) || (signal[i] == 0 && signal[i + 1] == 0)) {
                g.drawLine((model.diffWidth * i) + model.diffWidth, (int)(model.diffHeight * 0.5),
                        (model.diffWidth * i) + model.diffWidth, (int)(model.diffHeight * 2.5));
            }
        }
    }
}