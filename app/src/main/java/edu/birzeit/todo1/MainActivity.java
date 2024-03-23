package edu.birzeit.todo1;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    TextView textView_score;
    TextView textView_time;
    TextView textView_question;
    Button button1;
    Button button2;
    Button button3;
    Button button4;

    int current_score = 0;
    int initial_time = 10;
    int current_time = 10;
    int current_question_index = 0;
    String[] questions = {"What is the color of the star?", "What is the color of the sky?", "What is the color of a lemon?", "What is the color of the grass?", "What is the color of an orange fruit?"};
    String[] answers = {"button4", "button3", "button4", "button1", "button2"};

    String[][] answersArray = {
            {"blue", "orange", "brown","yellow"},
            {"rose", "red", "blue","yellow"},
            {"blue", "orange", "brown","yellow"},
            {"green", "orange", "brown","gray"},
            {"green", "orange", "brown","yellow"}
    };

    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            current_time--;

            textView_time.setText(String.valueOf(current_time));
            if (current_time == 0) {
                nextQuestion();
            } else {
                handler.postDelayed(this, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView_score = findViewById(R.id.score2);
        textView_time = findViewById(R.id.time_left);
        textView_question = findViewById(R.id.question2);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        textView_score.setText(String.valueOf(current_score));
        textView_time.setText(String.valueOf(current_time));

        startGame();
    }

    private void startGame() {
        current_score = 0;
        current_question_index = 0;
        displayQuestion();
    }

    private void displayQuestion() {
        if (current_question_index < questions.length) {
            textView_question.setText(questions[current_question_index]);
            button1.setText(answersArray[current_question_index][0]);
            button2.setText(answersArray[current_question_index][1]);
            button3.setText(answersArray[current_question_index][2]);
            button4.setText(answersArray[current_question_index][3]);

            current_time = initial_time;
            textView_time.setText(String.valueOf(current_time));

            handler.removeCallbacks(runnable);

            // Start the timer
            handler.postDelayed(runnable, 1000);

            button1.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer("button1");
                }
            });

            button2.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer("button2");
                }
            });

            button3.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer("button3");
                }
            });

            button4.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    checkAnswer("button4");
                }
            });
        } else {
            // All questions answered, switch to result activity
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent.putExtra("score", current_score);
            startActivity(intent);
            handler.removeCallbacksAndMessages(null);
            finish(); // Finish current activity to prevent returning to it

        }
    }

    private void checkAnswer(String buttonClicked) {
        if (answers[current_question_index].equals(buttonClicked)) {
            current_score++;
            textView_score.setText(String.valueOf(current_score)); // Update score display
        }
        nextQuestion();
    }

    private void nextQuestion() {
        current_question_index++;
        displayQuestion();
    }
}
