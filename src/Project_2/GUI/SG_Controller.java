package Project_2.GUI;

import Project_2.GUI.Chess_Panels.Start_Game;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Jose
 */
public class SG_Controller implements ActionListener {

    Start_Game sg;
    Chess_Model model;

    public SG_Controller (Start_Game sg, Chess_Model model) {
        this.sg = sg;
        this.model = model;

        sg.save.addActionListener(this);
        sg.back.addActionListener(this);

        for (Chess_Square_Button[] row : this.sg.board) {
            for (Chess_Square_Button col : row) {
                col.addActionListener(this);

            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() instanceof Chess_Square_Button) {

            Chess_Square_Button spot = (Chess_Square_Button)e.getSource();
            System.out.println(spot.getxCoordinate()+" X : "+spot.getyCoordinate()+" Y");
            model.movePiece(spot.getxCoordinate(), spot.getyCoordinate());

        } else {

            String command = e.getActionCommand();

            switch (command) {

                case "Save" :
                    model.saveGame();
                    sg.savedGame.setVisible(true);
                    int delay = 1000; //appear for two seconds
                    ActionListener visibility = (ActionEvent ev) -> {
                        sg.savedGame.setVisible(false);
                    };
                    new javax.swing.Timer(delay, visibility).start();
                    break;
                 
                case "Back" :
                    model.back(model.data);
                    break;
            }
        }
    }
}
