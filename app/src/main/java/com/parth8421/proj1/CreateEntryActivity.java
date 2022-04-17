package com.parth8421.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class CreateEntryActivity extends AppCompatActivity {

    public static final String NAME = "com.parth8421.proj1.NAME";
    public static final String COST = "com.parth8421.proj1.COST";

    TextView tv_product_code;
    EditText et_product_name, et_product_cost, et_product_count, et_total_cost_price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        String scanned_code = getIntent().getStringExtra("SCANNED_CODE");

        tv_product_code = findViewById(R.id.activity_create_entry_tv_product_code);
        et_product_name = findViewById(R.id.activity_create_entry_et_product_name);
        et_product_cost = findViewById(R.id.activity_create_entry_et_product_cost);
        et_product_count = findViewById(R.id.activity_create_entry_et_product_count);
        et_total_cost_price = findViewById(R.id.activity_create_entry_et_total_cost_price);

        tv_product_code.setText(scanned_code);
        tv_product_code.setTextSize(30);
    }
}