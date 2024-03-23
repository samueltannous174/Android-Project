package edu.birzeit.todo1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    TextView textView_feedback;
    TextView textView_score;
    Button button_reset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        textView_feedback = findViewById(R.id.feedBack);
        textView_score = findViewById(R.id.result_score);
        button_reset = findViewById(R.id.reset);

        int score = getIntent().getIntExtra("score", 0);
        textView_score.setText(score + "/5");

        if (score >= 4) {
            textView_feedback.setText("You Won!");
        } else {
            textView_feedback.setText("You Lost!");
        }

        button_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Reset the game and switch to Main Activity
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);
                finish(); // Finish current activity
            }
        });

    }
}
