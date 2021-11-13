package com.example.android_project.activities;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InlineSuggestionsRequest;
import android.widget.Button;

import com.example.android_project.R;

import java.util.Locale;

public class Login1Activity extends AppCompatActivity implements View.OnClickListener{

    private Button buttonStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login1);

        loadLocale();


        Button changeL = findViewById(R.id.button5);
        changeL.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

        buttonStarted = (Button) findViewById(R.id.buttonStarted);
        buttonStarted.setOnClickListener(this);
    }

    private void showChangeLanguageDialog(){
        final String[] listItems = {"English", "Türkçe"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(Login1Activity.this);
        mBuilder.setSingleChoiceItems(listItems, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int i) {
                if (i == 0){
                    setLocale("en");
                    recreate();
                } else if (i == 1){
                    setLocale("tr");
                    recreate();
                }
            }
        });

        AlertDialog mDialog = mBuilder.create();
        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration conf = new Configuration();
        conf.locale = locale;
        getBaseContext().getResources().updateConfiguration(conf, getBaseContext().getResources().getDisplayMetrics());
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My Lang", lang);
        editor.apply();
    }

    public void loadLocale(){
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My Lang","");
        setLocale(language);
    }

    @Override
    public void onClick(View v) {
        openActivity2();
    }

    private void openActivity2() {
        Intent intent = new Intent(this, Login2Activity.class);
        startActivity(intent);
    }
}