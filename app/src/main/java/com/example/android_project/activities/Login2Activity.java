package com.example.android_project.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login2Activity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "RegisterActivity";

    private EditText editEmail, editPass;
    private String txtEmail, txtPass;
    private FirebaseAuth mAuth;

    private Button buttonRegister2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        editEmail = (EditText) findViewById(R.id.gett2EmailAddress);
        editPass = (EditText) findViewById(R.id.gett2Password);

        mAuth = FirebaseAuth.getInstance();

        buttonRegister2 = (Button) findViewById(R.id.buttonRegister2);
        buttonRegister2.setOnClickListener(this);
    }

    public void signIn2(View v){
        txtEmail = editEmail.getText().toString();
        txtPass = editPass.getText().toString();


        mAuth.signInWithEmailAndPassword(txtEmail, txtPass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user);

                            Intent intent = new Intent(Login2Activity.this, MainActivity.class);
                            intent.putExtra("Email",txtEmail);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login2Activity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);


                        }
                    }
                });


    }

    private void updateUI(FirebaseUser user) {

    }


    @Override
    public void onClick(View v) {
        openActivity2();
    }
    private void openActivity2() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}