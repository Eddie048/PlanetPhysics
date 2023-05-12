import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import java.util.Timer;
import java.util.TimerTask;


public class ControlPanel extends JPanel
{
    private DrawingPanel canvas;
    private JButton updateButton;
    private JComboBox<String> choosePlanetType, moveView;
    private boolean isRunning = false;
    private Timer timer;
    private TimerTask task;

    public ControlPanel(DrawingPanel canvas)
    {
        this.canvas = canvas;



        updateButton = new JButton("Toggle Motion");
        updateButton.addActionListener(new ColorButtonListener());


        String[] planetTypeNames = {"Sun", "Planet", "Satellite Close", "Satellite Far", "Planet Far"};
        choosePlanetType = new JComboBox<>(planetTypeNames);

        choosePlanetType.addActionListener(new PlanetButtonListener());

        String[] moveType = {"Up", "Down", "Left", "Right", "Zoom Out", "Zoom in"};
        moveView = new JComboBox<>(moveType);

        moveView.addActionListener(new viewButtonListener());



        add(updateButton);

        add(moveView);

        add(choosePlanetType);
    }

    private class ColorButtonListener
            implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            if(!isRunning){

                isRunning = true;

                timer = new Timer();
                task = new Updater(canvas);

                timer.schedule(task, 500, 50);

            }else {
                timer.cancel();
                isRunning = false;
            }
            //canvas.updatePlanets();
            //canvas.requestFocus();



        }
    }

    private class PlanetButtonListener
            implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            canvas.addPlanet(choosePlanetType.getSelectedIndex() + 1);
            canvas.requestFocus();
        }
    }

    private class viewButtonListener
            implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            canvas.moveView(moveView.getSelectedIndex() + 1);
            canvas.requestFocus();
        }
    }
}

class Updater extends TimerTask{
    private DrawingPanel canvas;
    public Updater(DrawingPanel c){
        canvas = c;

    }

    public void run() {

        canvas.updatePlanets();
        canvas.updatePlanets();
        canvas.updatePlanets();
        canvas.updatePlanets();
        canvas.updatePlanets();
        canvas.requestFocus();
    }
}