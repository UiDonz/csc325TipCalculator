package com.example.tipcalcdb;

import javafx.beans.binding.Bindings;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class HelloController {
    // formatters for currency and percentages
    private static final NumberFormat currency =
            NumberFormat.getCurrencyInstance();
    private static final NumberFormat percent =
            NumberFormat.getPercentInstance();

    private BigDecimal tipPercentage = new BigDecimal(0.15); // 15% default

    // GUI controls defined in FXML and used by the controller's code
    @FXML
    private TextField amountTextField;

    @FXML
    private Label tipPercentageLabel;

    @FXML
    private Slider tipPercentageSlider;

    @FXML
    private TextField tipTextField;

    @FXML
    private TextField totalTextField;

    // calculates and displays the tip and total amounts
    @FXML
    private void calculateButtonPressed(ActionEvent event) {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        }
        catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }


    private void updateCalculations() {
        try {
            // Convert the text field input to a BigDecimal
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            // Calculate the tip using the current tip percentage
            BigDecimal tip = amount.multiply(tipPercentage);
            // Calculate the total by adding the tip to the amount
            BigDecimal total = amount.add(tip);

            // Update the text fields to display the formatted currency values
            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException e) {
            // Handle invalid input in the amount text field
            amountTextField.setText("Enter a valid amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
    private void calculateAndUpdate() {
        try {
            BigDecimal amount = new BigDecimal(amountTextField.getText());
            BigDecimal tip = amount.multiply(tipPercentage);
            BigDecimal total = amount.add(tip);

            tipTextField.setText(currency.format(tip));
            totalTextField.setText(currency.format(total));
        } catch (NumberFormatException ex) {
            amountTextField.setText("Enter amount");
            amountTextField.selectAll();
            amountTextField.requestFocus();
        }
    }
    public void initialize() {
        currency.setRoundingMode(RoundingMode.HALF_UP);

        // Bind the tip percentage label
        tipPercentageLabel.textProperty().bind(Bindings.createStringBinding(() ->
                percent.format(tipPercentageSlider.getValue() / 100), tipPercentageSlider.valueProperty()));

        // Listener for the amountTextField to perform calculations
        amountTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*([.]\\d{0,2})?")) {
                amountTextField.setText(oldValue); // Keep the old value if the new value is not numeric
            } else {
                updateCalculations(); // Call the method to update calculations if the input is valid
            }
        });

        // Adjust the tip percentage slider to update calculations and the label
        tipPercentageSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            tipPercentage = BigDecimal.valueOf(newValue.doubleValue() / 100.0);
            updateCalculations(); // Call the method to update calculations
        });
    }
}

/**************************************************************************
 * (C) Copyright 1992-2018 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
