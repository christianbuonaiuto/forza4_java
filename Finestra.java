import javax.swing.*;

public class Finestra extends JFrame {
    private Inizio inizio;
    private Griglia griglia;
    
    public Finestra() {
        super("Forza4");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(450,250,700,600);
        griglia = new Griglia(this,6,7);
        inizio = new Inizio(this);
        setContentPane(inizio);
        
        setVisible(true);
    }
    
    public void startGame() {
        griglia.inizializzaGriglia();
        setContentPane(griglia);
        validate();
        repaint();
    }
    
    public void endGame(int winner) {
        inizio.trasformaFine(winner);
        setContentPane(inizio);
    }
    
    public static void main(String[] args) {
        Finestra finestra = new Finestra();
        finestra.setVisible(true);
    }
}