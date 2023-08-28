package com.example.qwickly;

import static com.example.qwickly.status_screen.setSignInTime;

import androidx.activity.result.ActivityResultLauncher;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QuerySnapshot;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

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
    FirebaseFirestore fStore;
    String userID;
    Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_screen);

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        userID = user.getUid();
        fStore = FirebaseFirestore.getInstance();
        logoutButton = findViewById(R.id.menuScreen_logoutButton);
        emailTextview = findViewById(R.id.menuScreen_accountEmail);

        calendar = Calendar.getInstance();

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
        if (result.getContents().equals("Wilson Makerspace Check-in Successful")){
            AlertDialog.Builder builder = new AlertDialog.Builder(menu_screen.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            }).show();

            int hour = calendar.get(Calendar.HOUR_OF_DAY);
            int minute = calendar.get(Calendar.MINUTE);
            int second = calendar.get(Calendar.SECOND);
            DocumentReference documentReference = fStore.collection("Users").document(userID);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                   if (value.getLong("IsSignedIn") == 0){
                        setSignInTime(hour, minute, second);
                        Map<String, Object> userDetail = new HashMap<>();
                        userDetail.put("IsSignedIn", 1);
                        fStore.collection("Users")
                                .document(userID)
                                .update(userDetail);
                    }
                }
            });

        }
        else if (result.getContents().equals("Wilson Makerspace Check-out Successful")){
            AlertDialog.Builder builder = new AlertDialog.Builder(menu_screen.this);
            builder.setTitle("Result");
            builder.setMessage(result.getContents());
            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int i) {
                    dialog.dismiss();
                }
            }).show();
            DocumentReference documentReference = fStore.collection("Users").document(userID);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                    if (value.getLong("IsSignedIn") == 1){
                        Map<String, Object> userDetail = new HashMap<>();
                        userDetail.put("IsSignedIn", 0);
                        fStore.collection("Users")
                                .document(userID)
                                .update(userDetail);
                    }
                }
            });
        }
    });


}