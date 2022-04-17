package com.parth8421.proj1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<Intent> startCreateEntryActivityForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getResultCode() == RESULT_OK) {
                        Intent intent = result.getData();
                        String product_name = intent.getStringExtra(CreateEntryActivity.PRODUCT_NAME);
                        String product_cost = intent.getStringExtra(CreateEntryActivity.PRODUCT_COST);
                        String product_count = intent.getStringExtra(CreateEntryActivity.PRODUCT_COUNT);
                    } else {
                        Toast.makeText(MainActivity.this, "Invalid return", Toast.LENGTH_SHORT).show();
                    }
                }
            });

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (result.getContents() == null) {
                    //TODO: snack bar display not correct (should shift fab above before appearing)
                    Snackbar.make(findViewById(R.id.activity_main_fab_add_items), "Cancelled", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CreateEntryActivity.class);
                    intent.putExtra("SCANNED_CODE", result.getContents());
                    startCreateEntryActivityForResult.launch(intent);
                }
            });

    FloatingActionButton fab_add_items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fab_add_items = findViewById(R.id.activity_main_fab_add_items);
        fab_add_items.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                barcodeLauncher.launch(new ScanOptions()
                        .setDesiredBarcodeFormats(ScanOptions.ONE_D_CODE_TYPES)
                        .setPrompt("Scan a barcode")
                        .setCameraId(0)
                        .setBeepEnabled(false)
                        .setBarcodeImageEnabled(true)
                );
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.delete_entries:
                Toast.makeText(MainActivity.this, "Delete text", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}