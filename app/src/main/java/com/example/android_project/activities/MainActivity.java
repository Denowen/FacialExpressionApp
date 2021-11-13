package com.example.android_project.activities;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private DrawerLayout drawer;
    private TextView question, mailF;
    private FirebaseAuth mfire;
    private ImageView photo;
    private Button option1, option2, option3, option4, option5, option6;
    private List<Question> questionList;
    private int questionNum;
    private int score;
    private FirebaseFirestore firestore;
    private Dialog loadingDialog;
    private String mm;


    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.appBar);

        mfire = FirebaseAuth.getInstance();


        question = findViewById(R.id.question);

        photo = findViewById(R.id.photoQuestion);

        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);
        option5 = findViewById(R.id.option5);
        option6 = findViewById(R.id.option6);

        option1.setOnClickListener(this);
        option2.setOnClickListener(this);
        option3.setOnClickListener(this);
        option4.setOnClickListener(this);
        option5.setOnClickListener(this);
        option6.setOnClickListener(this);


        firestore = FirebaseFirestore.getInstance();


        getQuestionsList();

        score = 0;


        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawerLayout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawer,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close); drawer.addDrawerListener(toggle); toggle.syncState();

        String mailll = getIntent().getStringExtra("Email");
        String mailll2 = getIntent().getStringExtra("Mail2");

        mm = mailll;
        NavigationView navigationView = (NavigationView) findViewById(R.id.leftMenu);
        View headerView = navigationView.getHeaderView(0);
        TextView navUsername = (TextView) headerView.findViewById(R.id.UserMail);
        navUsername.setText(mailll);
        if(mailll2 != null) {
            navUsername.setText(mailll2);
        }


    }


    public void ClickMenu(View view){
        openDrawer(drawer);
    }

    private static void openDrawer(DrawerLayout drawer) {
        drawer.openDrawer(GravityCompat.START);
    }

    public void ClickLogo(View view){
        closeDrawer(drawer);
    }

    private static void closeDrawer(DrawerLayout drawer) {
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
    }

    public void ClickGame(View view){
        recreate();
    }
    
    public void ClickFacial(View view){
        redirectActivity(this,FacialExpression.class);
    }

    public void ClickLogout(View view){
        Logout();
    }

    private void Logout(){
        mfire.signOut();
        finish();
        Intent intent = new Intent(MainActivity.this,Login2Activity.class);
        startActivity(intent);
    }

    private static void redirectActivity(Activity activity, Class aClass) {
        Intent intent = new Intent(activity,aClass);
        intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
        activity.startActivity(intent);
    }

    @Override
    protected void onPause() {
        super.onPause();
        closeDrawer(drawer);
    }

    private void getQuestionsList() {
        questionList = new ArrayList<>();

            DocumentReference docRef = firestore.collection("Quiz").document("Question1");
            docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                    if (task.isSuccessful()) {
                        DocumentSnapshot document = task.getResult();
                        if (document.exists()) {
                            questionList.add(new Question(
                                    R.drawable.happy_face,
                                    document.getString("Question"),
                                    document.getString("A"),
                                    document.getString("B"),
                                    document.getString("C"),
                                    document.getString("D"),
                                    document.getString("E"),
                                    document.getString("F"),
                                    Integer.valueOf(document.getString("Answer"))
                            ));
                        }
                        setQuestion();
                    }
                }
            });

        DocumentReference docRef2 = firestore.collection("Quiz").document("Question2");
        docRef2.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        questionList.add(new Question(
                                R.drawable.sad_face,
                                document.getString("Question"),
                                document.getString("A"),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getString("E"),
                                document.getString("F"),
                                Integer.valueOf(document.getString("Answer"))
                        ));
                    }
                    setQuestion();
                }
            }
        });

        DocumentReference docRef3 = firestore.collection("Quiz").document("Question5");
        docRef3.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        questionList.add(new Question(
                                R.drawable.fear_face,
                                document.getString("Question"),
                                document.getString("A"),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getString("E"),
                                document.getString("F"),
                                Integer.valueOf(document.getString("Answer"))
                        ));
                    }
                    setQuestion();
                }
            }
        });

        DocumentReference docRef4 = firestore.collection("Quiz").document("Question3");
        docRef4.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        questionList.add(new Question(
                                R.drawable.cheerful_face,
                                document.getString("Question"),
                                document.getString("A"),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getString("E"),
                                document.getString("F"),
                                Integer.valueOf(document.getString("Answer"))
                        ));
                    }
                    setQuestion();
                }
            }
        });


        DocumentReference docRef5 = firestore.collection("Quiz").document("Question4");
        docRef5.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        questionList.add(new Question(
                                R.drawable.terrified,
                                document.getString("Question"),
                                document.getString("A"),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getString("E"),
                                document.getString("F"),
                                Integer.valueOf(document.getString("Answer"))
                        ));
                    }
                    setQuestion();
                }
            }
        });

        DocumentReference docRef6 = firestore.collection("Quiz").document("Question6");
        docRef6.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        questionList.add(new Question(
                                R.drawable.anger,
                                document.getString("Question"),
                                document.getString("A"),
                                document.getString("B"),
                                document.getString("C"),
                                document.getString("D"),
                                document.getString("E"),
                                document.getString("F"),
                                Integer.valueOf(document.getString("Answer"))
                        ));
                    }
                    setQuestion();
                }
            }
        });

    }

    private void setQuestion() {
        question.setText(questionList.get(0).getQuestion());
        photo.setImageResource(questionList.get(0).getPhoto());
        option1.setText(questionList.get(0).getOptionA());
        option2.setText(questionList.get(0).getOptionB());
        option3.setText(questionList.get(0).getOptionC());
        option4.setText(questionList.get(0).getOptionD());
        option5.setText(questionList.get(0).getOptionE());
        option6.setText(questionList.get(0).getOptionF());

        questionNum = 0;
    }


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {

        int selectedOption = 0;

        switch (v.getId()){
            case R.id.option1:
                selectedOption = 1;
                break;
            case R.id.option2:
                selectedOption = 2;
                break;
            case R.id.option3:
                selectedOption = 3;
                break;
            case R.id.option4:
                selectedOption = 4;
                break;
            case R.id.option5:
                selectedOption = 5;
                break;
            case R.id.option6:
                selectedOption = 6;
                break;
            default:
        }

        checkAnswer(selectedOption, v);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void checkAnswer(int selectedOption, View view){
        if (selectedOption == questionList.get(questionNum).getCorrectAns()){
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
            score++;
        }else{
            ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.RED));
            switch (questionList.get(questionNum).getCorrectAns()){
                case 1:
                    option1.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 2:
                    option2.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 3:
                    option3.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 4:
                    option4.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 5:
                    option5.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
                case 6:
                    option6.setBackgroundTintList(ColorStateList.valueOf(Color.GREEN));
                    break;
            }
        }

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                changeQuestion();
            }
        }, 1000);

    }

    private void changeQuestion() {
        if (questionNum < questionList.size()-1){

            questionNum++;

            playAnim(question, 0, 0);
            playAnim(option1, 0, 1);
            playAnim(option2, 0, 2);
            playAnim(option3, 0, 3);
            playAnim(option4, 0, 4);
            playAnim(option5, 0, 5);
            playAnim(option6, 0, 6);
            playAnim(photo, 0, 7);



        }else{
            Intent intent = new Intent(MainActivity.this, ScoreActivity.class);
            intent.putExtra("SCORE", String.valueOf(score) + "/" + String.valueOf(questionList.size()));
            intent.putExtra("Maill", mm);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }

    private void playAnim(View view, final int value, int viewNum) {
        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onAnimationEnd(Animator animation) {
                if (value == 0){
                    switch (viewNum){
                        case 0:
                            ((TextView)view).setText(questionList.get(questionNum).getQuestion());
                            break;
                        case 1:
                            ((Button)view).setText(questionList.get(questionNum).getOptionA());
                            break;
                        case 2:
                            ((Button)view).setText(questionList.get(questionNum).getOptionB());
                            break;
                        case 3:
                            ((Button)view).setText(questionList.get(questionNum).getOptionC());
                            break;
                        case 4:
                            ((Button)view).setText(questionList.get(questionNum).getOptionD());
                            break;
                        case 5:
                            ((Button)view).setText(questionList.get(questionNum).getOptionE());
                            break;
                        case 6:
                            ((Button)view).setText(questionList.get(questionNum).getOptionF());
                            break;
                        case 7:
                            ((ImageView)view).setImageResource(questionList.get(questionNum).getPhoto());
                            break;

                    }

                    if (viewNum != 0 && viewNum != 7){
                        ((Button)view).setBackgroundTintList(ColorStateList.valueOf(Color.parseColor("#565AFF")));
                    }

                    playAnim(view, 1, viewNum);
                }
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

}