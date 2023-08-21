package com.example.qwickly;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

public class menu_screen extends AppCompatActivity {

    private Button scanQRCodeButton;
    private Button seeCurrentStatusButton;
    private Button viewTotalHoursButton;
    private ImageButton menuScreenToqrCodeScreen;
    private ImageButton menuScreenTostatusScreen;
    private ImageButton menuScreenToHoursScreen;
    FirebaseAuth auth;
    Button logoutButton;
    TextView emailTextview;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        auth = FirebaseAuth.getInstance();
        logoutButton = findViewById(R.id.menuScreen_logoutButton);
        emailTextview = findViewById(R.id.menuScreen_accountEmail);
        user = auth.getCurrentUser();

        //checks account email and displays it
        if (user == null){
            Intent intent = new Intent(getApplicationContext(), signIn_screen.class);
            startActivity(intent);
            finish();
        }
        else{
            emailTextview.setText(user.getEmail());
        }

        //logout button to change user accounts
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(getApplicationContext(), signIn_screen.class);
                startActivity(intent);
                finish();
            }
        });

        //changes screen from Menu Screen to QR Code Screen
        scanQRCodeButton = (Button) findViewById(R.id.menuScreen_scanQRCodeButton);
        scanQRCodeButton.setOnClickListener(v->
        {
            scanCode();
        });

        //changes screen from Menu Screen to Status Screen
        seeCurrentStatusButton = (Button) findViewById(R.id.menuScreen_seeCurrentStatusButton);
        seeCurrentStatusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Hour Log Screen
        viewTotalHoursButton = (Button) findViewById(R.id.menuScreen_viewTotalHoursButton);
        viewTotalHoursButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to QR Code Screen
        menuScreenToqrCodeScreen = (ImageButton) findViewById(R.id.menuScreen_QRCodeIcon);
        menuScreenToqrCodeScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, status_screen.class);
                scanCode();
            }
        });

        //changes screen from Menu Screen to Status Screen
        menuScreenTostatusScreen = (ImageButton) findViewById(R.id.menuScreen_statusIcon);
        menuScreenTostatusScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, status_screen.class);
                startActivity(intent);
            }
        });

        //changes screen from Menu Screen to Hour Log Screen
        menuScreenToHoursScreen = (ImageButton) findViewById(R.id.menuScreen_hourLogIcon);
        menuScreenToHoursScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(menu_screen.this, hour_log_screen.class);
                startActivity(intent);
            }
        });

    }

    private void scanCode(){
        ScanOptions options = new ScanOptions();
        options.setPrompt("Volume up to flash on");
        options.setOrientationLocked(true);
        options.setCaptureActivity(CaptureAct.class);
        barLauncher.launch(options);
    }

    ActivityResultLauncher<ScanOptions> barLauncher = registerForActivityResult(new ScanContract(), result->
    {
        if (result.getContents() != null){
            AlertDialog.Builder builder = new AlertDialog.Builder(menu_screen.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            }).show();
        }
    });
}