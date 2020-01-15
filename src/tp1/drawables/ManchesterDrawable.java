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
            int refX = (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2);
            int lineOne = (int)(model.diffHeight * 0.5);
            int lineZero = (int)(model.diffHeight * 2.5);

            if (signal[i] == 1) {
                drawLines(g, model, i, refX, lineZero, lineOne);
            } else {
                drawLines(g, model, i, refX, lineOne, lineZero);
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

    /**
     * Dessine les lignes permettant de représenter un élément du signal.
     * @param g Graphique sur lequel dessiner.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     * @param i Index du signal en train d'être dessiné.
     * @param refX Valeur X de référence sur laquelle se baser pour dessiner les lignes.
     * @param baseLine Ligne d'où les traits partent.
     * @param targetLine Ligne sur laquelle les traits arrivent.
     */
    private void drawLines(Graphics g, DrawableModel model, int i, int refX, int baseLine, int targetLine) {
        g.drawLine(model.diffWidth * i, targetLine, refX, targetLine);

        g.drawLine(refX, targetLine, refX, baseLine);

        g.drawLine(refX, baseLine, model.diffWidth * (i + 1), baseLine);
    }
}
