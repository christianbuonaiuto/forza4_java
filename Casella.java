import javax.swing.*;

public class Casella extends JLabel
{
    static private ImageIcon p1img = new ImageIcon("p1.png"), p2img = new ImageIcon("p2.png"), emptyimg = new ImageIcon("empty.png");
    private int stato; //0 = empty, 1 = player 1, 2 = player 2

    public Casella() {
        super("", emptyimg, JLabel.CENTER);
        stato = 0;
    }
    
    public int getStato() {
        return stato;
    }
    
    private void aggiornaImmagine() {
        if (stato == 0) {
            setIcon(emptyimg);
        } else if (stato == 1) {
            setIcon(p1img);
        } else {
            setIcon(p2img);
        }
    }
    
    public boolean cambiaStato(int stato) {
        if (this.stato == 0) {
            if ((stato > 0) && (stato < 3)) {
                this.stato = stato;
                aggiornaImmagine();
            } else {
                return false;
            }
        } else {
            return false;
        }
        return true;
    }
}