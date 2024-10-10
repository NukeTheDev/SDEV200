// Charles "Nuke" Phillips
// p. 577
package ChapterFourteen.Tutorial14;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class JResortCalculator extends JFrame implements ItemListener
{
    final int BASE_PRICE = 200;
    final int WEEKEND_PREMIUM = 100;
    final int BREAKFAST_PREMIUM = 20;
    final int GOLF_PREMIUM = 75;
    int totalPrice = BASE_PRICE;
    JCheckBox weekendBox = new JCheckBox
    ("Weekend premium $" + WEEKEND_PREMIUM, false);
    JCheckBox breakfastBox =
        new JCheckBox("Breakfast $" + BREAKFAST_PREMIUM, false);
    JCheckBox golfBox = new JCheckBox("Golf $" + GOLF_PREMIUM, false);
    JLabel resortLabel = new JLabel("Resort Price Calculator");
    JLabel priceLabel = new JLabel("The price for your stay is");
    JTextField totPrice = new JTextField(4);
    JLabel optionExplainLabel = new JLabel("Base price for a room is $" + BASE_PRICE + ".");
    JLabel optionExplainLabel2 = new JLabel("Check the options you want.");
    public JResortCalculator()
    {
        super("Resort Price Estimator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());
        // Set the action commands for the checkboxes
        weekendBox.setActionCommand("weekendBox");
        breakfastBox.setActionCommand("breakfastBox");
        golfBox.setActionCommand("golfBox");
        add(resortLabel);
        add(optionExplainLabel);
        add(optionExplainLabel2);
        add(weekendBox);
        add(breakfastBox);
        add(golfBox);
        add(priceLabel);
        add(totPrice);
        totPrice.setText("$" + totalPrice);
        weekendBox.addItemListener(this);
        breakfastBox.addItemListener(this);
        golfBox.addItemListener(this);
    }
    @Override
    public void itemStateChanged(ItemEvent event) {
    Object source = event.getSource();
    int select = event.getStateChange();

    // Cast the source to a JCheckBox to access its action command
    JCheckBox checkBox = (JCheckBox) source;
    String detectedSource = checkBox.getActionCommand();  // Get the action command

        // Use switch on the action command
        switch (detectedSource) {
            case "weekendBox" -> {
                if (select == ItemEvent.SELECTED)
                    totalPrice += WEEKEND_PREMIUM;
                else
                    totalPrice -= WEEKEND_PREMIUM;
            }
            case "breakfastBox" -> {
                if (select == ItemEvent.SELECTED)
                    totalPrice += BREAKFAST_PREMIUM;
                else
                    totalPrice -= BREAKFAST_PREMIUM;
            }
            case "golfBox" -> {
                if (select == ItemEvent.SELECTED)
                    totalPrice += GOLF_PREMIUM;
                else
                    totalPrice -= GOLF_PREMIUM;
            }
        }
            // Update the total price in the text field
          totPrice.setText("$" + totalPrice);
    }
    public static void main(String[] args) 
    {
        JResortCalculator aFrame = new JResortCalculator();
        final int WIDTH = 300;
        final int HEIGHT = 200;
        aFrame.setSize(WIDTH, HEIGHT);
        aFrame.setVisible(true);
    }
}
