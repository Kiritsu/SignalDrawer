package tp1;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import tp1.exceptions.InvalidSignalException;
import tp1.handlers.ButtonHandler;

public class Fenetre extends JFrame {
    /**
     * Zone de texte pour le signal à dessiner.
     */
    private JTextField inputTxt;

    /**
     * Choix des méthodes pour les signaux.
     */
    private JComboBox<SignalMethod> boxes;

    /**
     * Panel dédié au dessin.
     */
    private SignalPanel signalPanel;

    public Fenetre() {
        super("TP1 - La transmission en bande passe");
        this.setLayout(new BorderLayout());
        this.init();

        this.pack();
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    /**
     * Retourne le signal à dessiner sous la forme d'un tableau de bytes.
     */
    public byte[] getSignal() throws InvalidSignalException {
        char[] chars = this.inputTxt.getText().toCharArray();
        byte[] bytes = new byte[chars.length];

        for (int i = 0; i < bytes.length; ++i) {
            switch (chars[i]) {
                case '1':
                    bytes[i] = 1;
                    break;
                case '0':
                    bytes[i] = 0;
                    break;
                default:
                    throw new InvalidSignalException("Unknown char", i, chars[i]);
            }
        }

        return bytes;
    }

    /**
     * Indique au panel de dessin de tout recalculer.
     */
    public void updateSignalPanel() throws InvalidSignalException {
        this.signalPanel.redrawSignal();
        this.signalPanel.repaint();
    }

    /**
     * Retourne la méthode à utiliser pour dessiner le signal.
     */
    public SignalMethod getSelectedMethod() {
        return (SignalMethod)this.boxes.getSelectedItem();
    }

    /**
     * Initialise les éléments de la fenêtre.
     */
    private void init() {
        JButton btnOk = new JButton("Valider");
        btnOk.addActionListener(new ButtonHandler(this));

        this.inputTxt = new JTextField();
        this.inputTxt.setPreferredSize(new Dimension(400, 20));
        this.boxes = new JComboBox<>(SignalMethod.values());

        /*
          Panel avec tous les contrôles.
         */
        JPanel inputPanel = new JPanel(new GridLayout(3, 0));
        JPanel pnl = new JPanel();
        pnl.add(this.inputTxt);
        inputPanel.add(pnl);
        pnl = new JPanel();
        pnl.add(this.boxes);
        inputPanel.add(pnl);
        pnl = new JPanel();
        pnl.add(btnOk);
        inputPanel.add(pnl);

        this.signalPanel = new SignalPanel(this);

        this.add(inputPanel, BorderLayout.NORTH);
        this.add(this.signalPanel, BorderLayout.CENTER);
    }

    public static void main(String... args) {
        SwingUtilities.invokeLater(Fenetre::new);
    }
}
