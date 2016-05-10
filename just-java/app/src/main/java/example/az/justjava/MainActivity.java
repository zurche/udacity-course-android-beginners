package example.az.justjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int price = 5;
    int quantity = 0;
    private boolean hasAddedWhippedCream;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CheckBox whippedCreamCheck = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        hasAddedWhippedCream = whippedCreamCheck.isChecked();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        displayMessage(createOrderSummary(quantity * price));
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int price) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    public void increment(View view) {
        quantity = 2;
        quantity = quantity + 1;
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        quantity = 2;
        quantity = quantity - 1;
        displayQuantity(quantity);
    }

    public String createOrderSummary(int orderPrice) {
        String stringOrderPrice = NumberFormat.getCurrencyInstance().format(orderPrice);
        return "Name: Kaptain Kunal" +
                "\nAdd whipped cream? " + hasAddedWhippedCream +
                "\nQuantity: " + quantity +
                "\nTotal: " + stringOrderPrice +
                "\nThank you!";
    }
}
