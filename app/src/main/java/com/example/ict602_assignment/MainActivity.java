package com.example.ict602_assignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText goldWeight, current_value;
    Button btnCalculate, btnReset;
    TextView output1, output2, output3;
    RadioButton type_keep, type_wear;

    ImageButton price;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Toolbar myToolbar;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);


        goldWeight = (EditText) findViewById(R.id.goldWeight);
        current_value = (EditText) findViewById(R.id.current_value);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        btnReset = (Button) findViewById(R.id.btnReset);
        price = (ImageButton) findViewById(R.id.price);
        output1 = (TextView) findViewById(R.id.output1);
        output2 = (TextView) findViewById(R.id.output2);
        output3 = (TextView) findViewById(R.id.output3);
        type_keep = (RadioButton) findViewById(R.id.type_keep);
        type_wear = (RadioButton) findViewById(R.id.type_wear);

        btnCalculate.setOnClickListener(this);

        price.setOnClickListener(this);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Clear input fields
                goldWeight.setText("");
                current_value.setText("");

                // Clear the checked state of RadioGroup
                type_keep.setChecked(false);
                type_wear.setChecked(false);

                // Clear output fields
                output1.setText("");
                output2.setText("");
                output3.setText("");
            }
        });

    }

    @Override
    public void onClick(View view) {
        if(view == price) {
            String rateId = "https://www.goodreturns.in/gold-rates/malaysia.html";
            Intent rateIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(rateId));
            startActivity(rateIntent);
        }
        if (view == btnCalculate){
        if (goldWeight.getText().toString().isEmpty() || current_value.getText().toString().isEmpty() || (!type_keep.isChecked() && !type_wear.isChecked())) {
            output1.setText("*Form not completed!");
            // Clear other outputs
            output2.setText("");
            output3.setText("");
            return;
        }

        double weight = Double.parseDouble(goldWeight.getText().toString());
        double value = Double.parseDouble(current_value.getText().toString());

        double result1 = 0, result2 = 0, result3 = 0;
        if (type_keep.isChecked()) {

            result1 = weight * value;
            result2 = Math.max((weight - 85) * value, 0);
            result3 = result2 * 0.025;
        } else {

            result1 = weight * value;
            result2 = Math.max((weight - 200) * value, 0);
            result3 = result2 * 0.025;
        }

        output1.setText("Total Value of Gold : RM" + result1);
        output2.setText("Zakat Payable : RM" + result2);
        output3.setText("Total Zakat : RM" + result3);
    }}


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.item_share) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Feel free to use this application - https//t.co/app");
            startActivity(Intent.createChooser(shareIntent, null));

            return true;
        } else if (item.getItemId() == R.id.item_about) {
            Intent aboutIntent = new Intent(this, About.class);
            startActivity(aboutIntent);
        } else {
            Intent infoIntent = new Intent(this, Information.class);
            startActivity(infoIntent);


            return false;
        }
        return false;
    }
}