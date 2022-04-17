package com.parth8421.proj1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CreateEntryActivity extends AppCompatActivity {

    public static final String PRODUCT_NAME = "com.parth8421.proj1.PRODUCT_NAME";
    public static final String PRODUCT_COST = "com.parth8421.proj1.PRODUCT_COST";
    public static final String PRODUCT_COUNT = "com.parth8421.proj1.PRODUCT_COUNT";

    TextView tv_product_code;
    EditText et_product_name, et_product_cost, et_product_count, et_total_cost_price;
    Button btn_save;
    Intent replyIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_entry);

        replyIntent = new Intent();

        tv_product_code = findViewById(R.id.activity_create_entry_tv_product_code);
        et_product_name = findViewById(R.id.activity_create_entry_et_product_name);
        et_product_cost = findViewById(R.id.activity_create_entry_et_product_cost);
        et_product_count = findViewById(R.id.activity_create_entry_et_product_count);
        et_total_cost_price = findViewById(R.id.activity_create_entry_et_total_cost_price);
        btn_save = findViewById(R.id.activity_create_entry_btn_save);

        // Receiving results from previous activity
        String scanned_code = getIntent().getStringExtra("SCANNED_CODE");
        tv_product_code.setText(scanned_code);
        tv_product_code.setTextSize(30);

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validateForm()) {
                    setResult(RESULT_OK, replyIntent);
                    finish();
                }
            }
        });
    }

    private boolean validateForm() {
        if (TextUtils.isEmpty(et_product_name.getText())){
            Toast.makeText(this, "Product name not entered", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            replyIntent.putExtra(PRODUCT_NAME, et_product_name.getText().toString());
        }
        if (TextUtils.isEmpty(et_product_cost.getText())){
            Toast.makeText(this, "Product cost not entered", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            replyIntent.putExtra(PRODUCT_COST, et_product_cost.getText().toString());
        }
        if (TextUtils.isEmpty(et_product_count.getText())){
            Toast.makeText(this, "Product count not entered", Toast.LENGTH_SHORT).show();
            return false;
        } else {
            replyIntent.putExtra(PRODUCT_COUNT, et_product_count.getText().toString());
        }
        return true;
    }
}