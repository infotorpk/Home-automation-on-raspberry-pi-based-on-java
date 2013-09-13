//package frame.mine;
import javax.swing.*;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;
public class Pigui implements  ItemListener{

    // Definition of global values and items that are part of the GUI.

    JToggleButton toggleButton;
    JPanel totalGUI;
    final GpioController gpio = GpioFactory.getInstance();
    final GpioPinDigitalOutput pin = gpio.provisionDigitalOutputPin(RaspiPin.GPIO_01, PinState.LOW);

    public JPanel createContentPane (){

        // We create a bottom JPanel to place everything on.
        totalGUI = new JPanel();
        totalGUI.setLayout(null);
      
        

        toggleButton = new JToggleButton("OFF");
        ImageIcon icon = new ImageIcon("/home/pi/off1.png");    //give the path of your image file
        toggleButton.setIcon(icon);
        toggleButton.setLocation(10,10);
        toggleButton.setSize(210,207);
        toggleButton.addItemListener(this);
        totalGUI.add(toggleButton);
        
        totalGUI.setOpaque(true);
        return totalGUI;
    }

    // This is the new itemStateChanged Method.
    // It catches any events with an ItemListener attached.
    // Using an if statement, we can determine if the button is now selected or deselected
	// after the action and perform changes to the GUI accordingly.

    public void itemStateChanged(ItemEvent e) {
        if(e.getStateChange() == ItemEvent.SELECTED)
        {
            	pin.high();
		toggleButton.setText("ON");
        	ImageIcon img = new ImageIcon("/home/pi/on1.png");
            	toggleButton.setIcon(img);
        }
        else
        {
        	pin.low();
		ImageIcon icon = new ImageIcon("/home/pi/off1.png");
            	toggleButton.setIcon(icon);            
            	toggleButton.setText("OFF");
        } 
    }

    private static void createAndShowGUI() {

        JFrame.setDefaultLookAndFeelDecorated(true);
        JFrame frame = new JFrame("Raspberry Pi");
        //Create and set up the content pane.
        Pigui demo = new Pigui();
        frame.setContentPane(demo.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(235,240);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}


