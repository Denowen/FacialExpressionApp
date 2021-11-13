package com.example.android_project.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.android_project.R;

public class ScoreActivity extends AppCompatActivity {

    private TextView score;
    private Button done;
    private String mm1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        score = findViewById(R.id.scoreText);
        done = findViewById(R.id.scoreButton);

        String score_str = getIntent().getStringExtra("SCORE");
        String mail1 = getIntent().getStringExtra("Maill");
        mm1 = mail1;
        score.setText(score_str);

        done.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(ScoreActivity.this, MainActivity.class);
                intent.putExtra("Mail2", mm1);
                ScoreActivity.this.startActivity(intent);
                ScoreActivity.this.finish();
            }
        });

    }
}