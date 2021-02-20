import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Inizio extends JPanel {
    private Finestra finestraMadre;
    private JLabel scritta, vuoto;
    private JButton bottone;
    private BoxLayout layout;
    
    public Inizio(Finestra finestra) {
        super();
        finestraMadre = finestra;
        
        layout = new BoxLayout(this, BoxLayout.Y_AXIS);
        //layout.setHgap(250);
        //layout.setVgap(250);
        setLayout(layout);
        bottone = new JButton("Start");
        //bottone.setPreferredSize(new Dimension(70,30));
        bottone.setAlignmentX(JButton.CENTER_ALIGNMENT);
        
        scritta = new JLabel("FORZA 4");
        scritta.setAlignmentX(JLabel.CENTER_ALIGNMENT);
        //scritta.setAlignmentY(JLabel.CENTER_ALIGNMENT);
        vuoto = new JLabel("");
        add(Box.createVerticalGlue());
        add(scritta);
        add(Box.createVerticalGlue());
        //add(vuoto, BorderLayout.LINE_START);
        add(bottone);
        add(Box.createVerticalGlue());
        //add(vuoto, BorderLayout.LINE_END);
        //add(vuoto, BorderLayout.SOUTH);
        bottone.setActionCommand("0");
        bottone.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent ae) {
                finestra.startGame();
            }
        });
    }
    
    public void trasformaFine(int winner) {
        scritta.setText("Player " + winner + " has won!");
        bottone.setText("Restart!");
    }
}