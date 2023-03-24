package com.example.dialog;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String tag = "DialogFragmentExample";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void showMessage(String message){
        Toast.makeText(getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void doSelectedItem(int which, boolean isChecked){
        showMessage(AlertDialogFragment.items[which]+(isChecked ? "checked" : "unchecked"));
    }

    public void actionClick(String action){
        showMessage(action + "clicked");
        Log.d(tag,"User click on " + action);
    }

    public void onClick(View v){
        AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance("Seguro",0);
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }

    public void onClick2(View v){
        final ProgressDialog dialog = ProgressDialog.show(
                this, "Doing Something", "Please Wait", true);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                    dialog.dismiss();
                }
                catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }
    public void onClick3(View v){
        AlertDialogFragment dialogFragment = AlertDialogFragment.newInstance("Descargando",1);
        dialogFragment.show(getSupportFragmentManager(), "dialog");
    }
}