package tp1.drawables;

import java.awt.*;

public class ManchesterDiffDrawable implements IDrawableSignal {
    /**
     * Dessine un signal en Manchester Différentiel.
     * @param g Graphique sur lequel dessiner.
     * @param signal Signal à représenter.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     */
    @Override
    public void draw(Graphics g, byte[] signal, DrawableModel model) {
        g.setColor(Color.RED);

        int lineOne = (int)(model.diffHeight * 0.5);
        int lineZero = (int)(model.diffHeight * 2.5);
        int currentLine = lineZero;

        for (int i = 0; i < signal.length; ++i) {
            int refX = (i == 0 ? model.diffWidth : model.diffWidth * (i + 1)) - (model.diffWidth / 2);

            if (signal[i] == 1) {
                if (i > 0 && signal[i - 1] == 0) {
                    if (currentLine == lineOne) {
                        currentLine = lineZero;
                    } else {
                        currentLine = lineOne;
                    }
                }

                if (currentLine == lineZero) { // on fait une transition vers le haut
                    drawLines(g, model, lineOne, lineZero, currentLine, i, refX);
                    currentLine = lineOne;
                } else { // on fait une transition vers le bas
                    drawLines(g, model, lineZero, lineOne, currentLine, i, refX);
                    currentLine = lineZero;
                }
            } else {
                g.drawLine(model.diffWidth * i, lineOne, model.diffWidth * i, lineZero);

                if (i > 0 && signal[i - 1] == 1) {
                    if (currentLine == lineOne) {
                        currentLine = lineZero;
                    } else {
                        currentLine = lineOne;
                    }
                }

                if (currentLine == lineOne) {
                    drawLines(g, model, lineZero, lineOne, currentLine, i, refX);
                } else {
                    drawLines(g, model, lineOne, lineZero, currentLine, i, refX);
                }
            }
        }
    }

    /**
     * Dessine la transition d'un signal 1, vers le haut, ou vers le bas.
     * @param g Graphique sur lequel dessiner.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     * @param lineNext Ligne vers laquelle aller au prochain signal 1.
     * @param lineNow Ligne actuelle sur laquelle se trouve le signal.
     * @param currentLine Variable permettant de se souvenir de la ligne sur laquel aller pour dessiner le prochain signal.
     * @param i Index du signal en train d'être dessiné.
     * @param refX 1/2 signal en fonction de x.
     */
    private void drawLines(Graphics g, DrawableModel model, int lineNext, int lineNow, int currentLine, int i, int refX) {
        g.drawLine(model.diffWidth * i, currentLine, refX, currentLine);
        g.drawLine(refX, lineNow, refX, lineNext);
        g.drawLine(refX, lineNext, model.diffWidth * (i + 1), lineNext);
    }
}
