import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Griglia extends JPanel {
    private int grigliaH, grigliaW;
    private Casella[][] griglia;
    private JButton[] bottoni;
    private Image logo;
    private int actualPlayer=1;
    private JPanel gridPanel;
    private Finestra finestra;
    private JLabel giocatore;
    private boolean showingVictory;
    
    public Griglia(Finestra finestra, int h, int w) {
        super();
        this.finestra = finestra;
        gridPanel = new JPanel();
        grigliaH = h;
        grigliaW = w;
        gridPanel.setLayout(new GridLayout(h+1,w)); // 6righe e 7 colonne per la grafica + 1 riga per i pulsanti
        gridPanel.setBackground(new Color(63,72,204));
        
        giocatore = new JLabel("Turno di:");
        
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        giocatore.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        add(giocatore);
        add(gridPanel);
    }
    
    public void inizializzaGriglia() {
        actualPlayer = 1;
        aggiornaLabel();
        
        gridPanel.removeAll();
        griglia = new Casella[6][7];
        for (int i=0; i<6; i++) {
            for (int j=0; j<7; j++) {
                griglia[i][j] = new Casella();
                gridPanel.add(griglia[i][j]);
            }
        }
        
        bottoni = new JButton[7];
        for (int i=0; i<7; i++) {
            bottoni[i] = new JButton(""+i);
            bottoni[i].setActionCommand(""+i);
            bottoni[i].addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    JButton bottone = (JButton) ae.getSource();
                    aggiungiCasella(Integer.parseInt(bottone.getActionCommand()));
                }
            });
            gridPanel.add(bottoni[i]);
        }
    }
    
    private void aggiornaLabel() {
        giocatore.setText("Turno di: Player " + actualPlayer);
    }
    
    private boolean controllaVittoria(int row, int col, int p) {
        boolean vittoria = false;
        if (row < (grigliaH-3)) { //possibile vittoria verso il basso
            for (int i=row; i<=row+3; i++) { //verifica verticale
                if (griglia[i][col].getStato() == p) {
                    vittoria = true;
                } else {
                    vittoria = false;
                    break;
                }
            }
            if (vittoria) {
                return true;
            }
        }
        int obDestra = 0, obSinistra = 0, obDestraSu = 0, obSinistraSu = 0;
        boolean obDestraFin = false, obSinistraFin = false, obDestraSuFin = false, obSinistraSuFin = false;
        for (int i=1; i<4; i++) { //verifica obliqua
            if ((col+i) < (grigliaW)) {
                if (row < (grigliaH-3)) {
                    if (griglia[row+i][col+i].getStato() == p) { //verso destra
                        if (!(obDestraFin)) {
                            obDestra++;
                        }
                    } else {
                        obDestraFin = true;
                    }
                }
                if ((row-i) >= 0) {
                    if (griglia[row-i][col+i].getStato() == p) { //verso sopra
                        if (!(obDestraSuFin)) {
                            obDestraSu++;
                        }
                    } else {
                        obDestraSuFin = true;
                    }
                }
            }
            if ((col-i) >= 0) {
                if (row < (grigliaH-3)) {
                    if (griglia[row+i][col-i].getStato() == p) { //verso sinistra
                        if (!(obSinistraFin)) {
                            obSinistra++;
                        }
                    } else {
                        obSinistraFin = true;
                    }
                }
                if ((row-i) >= 0) {
                    if (griglia[row-i][col-i].getStato() == p) { //verso sopra
                        if (!(obSinistraSuFin)) {
                            obSinistraSu++;
                        }
                    } else {
                        obSinistraSuFin = true;
                    }
                }
            }
        }
        if ((obDestra == 4) || (obSinistra == 4) || (obSinistraSu == 4) || (obDestraSu == 4) || ((obDestraSu+obDestra) > 2) || ((obSinistraSu + obSinistra) > 2)) {
            return true;
        }
        int orSinistra = 0, orDestra = 0;
        boolean orSinistraFin = false, orDestraFin = false;
        for (int i=1; i<4; i++) { //verifica orizzontale
            if ((col+i)<(grigliaW)) {
                
                if (griglia[row][col+i].getStato() == p) { //verso destra
                    if (!(orDestraFin)) {
                        orDestra++;
                    }
                } else {
                    orDestraFin = true;
                }
            }
            if ((col-i) >= 0) {
                if (griglia[row][col-i].getStato() == p) { //verso sinistra
                    if (!(orSinistraFin)) {
                        orSinistra++;
                    }
                } else {
                    orSinistraFin = true;
                }
            }
        }
        if ((orDestra == 4) || (orSinistra == 4) || ((orDestra+orSinistra)>2)) {
            return true;
        }
        return false;
    }
   
    public void aggiungiCasella(int col) {
        boolean added = false;
        int row=0;
        
        for (int i=grigliaH-1; i>=0; i--) {
            if (griglia[i][col].getStato() == 0) {
                griglia[i][col].cambiaStato(actualPlayer);
                added = true;
                row = i;
                break;
            }
        }
        
        if (added) {
            if (controllaVittoria(row, col, actualPlayer)) {
                finestra.endGame(actualPlayer);
                return;
            }
            actualPlayer = 3-actualPlayer;
            aggiornaLabel();
        }
    }
    
    public void test() {
        griglia[2][3].cambiaStato(1);
    }
}
