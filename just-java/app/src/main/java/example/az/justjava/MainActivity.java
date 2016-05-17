package example.az.justjava;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    int price = 5;
    int quantity = 0;
    private CheckBox mWhippedCreamCheck;
    private CheckBox mChocolateCheck;
    private EditText mNameEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWhippedCreamCheck = (CheckBox) findViewById(R.id.whipped_cream_checkbox);
        mChocolateCheck = (CheckBox) findViewById(R.id.chocolate_checkbox);
        mNameEditText = (EditText) findViewById(R.id.name_edit_text);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        calculateOrderTotal();

        displayMessage(createOrderSummary(quantity * price));
    }

    private void calculateOrderTotal() {
        if(mWhippedCreamCheck.isChecked()) {
            price += 1;
        }

        if(mChocolateCheck.isChecked()) {
            price += 2;
        }
    }

    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayPrice(int price) {
        TextView priceTextView = (TextView) findViewById(R.id.order_summary_text_view);
        priceTextView.setText(NumberFormat.getCurrencyInstance().format(price));
    }

    public void increment(View view) {
        if(quantity < 100){
            quantity += 1;
            displayQuantity(quantity);
        }
    }

    public void decrement(View view) {
        if(quantity > 0) {
            quantity -= 1;
            displayQuantity(quantity);
        }
    }

    public String createOrderSummary(int orderPrice) {
        String stringOrderPrice = NumberFormat.getCurrencyInstance().format(orderPrice);
        String orderSummary = "Name: " + mNameEditText.getText().toString() +
                "\nAdd whipped cream? " + mWhippedCreamCheck.isChecked() +
                "\nAdd chocolate? " + mChocolateCheck.isChecked() +
                "\nQuantity: " + quantity +
                "\nTotal: " + stringOrderPrice +
                "\nThank you!";
        sendOrderToMail(orderSummary, mNameEditText.getText().toString());
        return orderSummary;
    }

    private void sendOrderToMail(String orderSummary, String name){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, orderSummary);
        Intent mailer = Intent.createChooser(intent, null);
        startActivity(mailer);
    }
}
