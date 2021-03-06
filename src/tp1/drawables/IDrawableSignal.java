package tp1.drawables;

import java.awt.Graphics;

public interface IDrawableSignal {
    /**
     * Dessine un signal.
     * @param g Graphique sur lequel dessiner.
     * @param signal Signal à représenter.
     * @param model Modèle indiquant tous les détails nécessaires au dessin du signal.
     */
    void draw(Graphics g, byte[] signal, DrawableModel model);
}
