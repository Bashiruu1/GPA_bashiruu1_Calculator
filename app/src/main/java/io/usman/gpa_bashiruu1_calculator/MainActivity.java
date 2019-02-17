package io.usman.gpa_bashiruu1_calculator;

import android.graphics.Color;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ConstraintLayout constraintLayout;
    Grader grader;
    
    //Declare view variables
    EditText editText1, editText2, editText3, editText4, editText5;
    TextView textView2;
    Button calculateButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        constraintLayout = findViewById(R.id.constraintLayout);
        grader = new Grader();

        //Assigns the view from the layout activity_main.xml files to corresponding variables
        editText1 = findViewById(R.id.editText1);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        editText5 = findViewById(R.id.editText5);
        textView2 = findViewById(R.id.textView2);
        calculateButton = findViewById(R.id.calculateButton);

        View.OnClickListener buttonListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    int grade1 = Integer.parseInt(editText1.getText().toString());
                    int grade2 = Integer.parseInt(editText2.getText().toString());
                    int grade3 = Integer.parseInt(editText3.getText().toString());
                    int grade4 = Integer.parseInt(editText4.getText().toString());
                    int grade5 = Integer.parseInt(editText5.getText().toString());

                    int score = grader.computerGrade(grade1, grade2, grade3, grade4, grade5);

                    //Sets the text of textView2 to result string recourse + score and makes the textColor transparent
                    textView2.setText(getString(R.string.result) + score);
                    calculateButton.setTextColor(Color.alpha(0));

                    if (score < 60) {
                        constraintLayout.setBackgroundColor(Color.RED);
                    } else if (score < 80 && score >= 60) {
                        constraintLayout.setBackgroundColor(Color.YELLOW);
                    } else {
                        constraintLayout.setBackgroundColor(Color.GREEN);
                    }
                } catch (IllegalArgumentException e) {
                    //Ensures the app does no crash if missing 1 or more input from user
                    Toast.makeText(getApplicationContext(), e.getMessage()+"Please Enter 5 Integers", Toast.LENGTH_LONG).show();
                }
            }
        };
        calculateButton.setOnClickListener(buttonListener);
    }
}
