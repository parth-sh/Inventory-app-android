package com.parth8421.proj1;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class MainActivity extends AppCompatActivity {

    private final ActivityResultLauncher<ScanOptions> barcodeLauncher = registerForActivityResult(new ScanContract(),
            result -> {
                if (false && result.getContents() == null) {
                    //TODO: snack bar display not correct (should shift fab above before appearing)
                    Snackbar.make(findViewById(R.id.activity_main_fab_add_items), "Cancelled", Snackbar.LENGTH_LONG)
                            .setAction("Action", null).show();
                } else {
                    Intent intent = new Intent(MainActivity.this, CreateEntryActivity.class);
                    intent.putExtra("SCANNED_CODE", result.getContents());
                    startActivity(intent);
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
}