package com.shohayeb.quizapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private final String NUMBER_OF_QUESTION = "question number";
    private final String SCORE = "score";
    private RadioButton radioButton2;
    private RadioButton radioButton3;
    private RadioButton radioButton4;
    private RadioGroup radiogroup;
    private TextView questionName;
    private TextView question;
    private EditText editText;
    private CheckBox checkBox1;
    private CheckBox checkBox2;
    private CheckBox checkBox3;
    private CheckBox checkBox4;
    private int score = 0;
    private int questionNumber = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            questionNumber = savedInstanceState.getInt(NUMBER_OF_QUESTION, 0);
            score = savedInstanceState.getInt(SCORE, 0);
            if (questionNumber == 1)
                start(question);
            else {
                questionNumber--;
                start(radiogroup);
                next(findViewById(R.id.question));
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(NUMBER_OF_QUESTION, questionNumber);
        outState.putInt(SCORE, score);
        super.onSaveInstanceState(outState);
    }

    public void start(View view) {
        setContentView(R.layout.questions);
        radiogroup = findViewById(R.id.radioGroup);
        radioButton2 = findViewById(R.id.radioButton2);
        radioButton3 = findViewById(R.id.radioButton3);
        radioButton4 = findViewById(R.id.radioButton4);
        editText = findViewById(R.id.editText);
        checkBox1 = findViewById(R.id.checkBox1);
        checkBox2 = findViewById(R.id.checkBox2);
        checkBox3 = findViewById(R.id.checkBox3);
        checkBox4 = findViewById(R.id.checkBox4);
    }

    public void next(View view) {
        if (view.getId() == R.id.button) {
            if (!calculateScore()) {
                Toast.makeText(MainActivity.this, getString(R.string.answer_the_question), Toast.LENGTH_SHORT).show();
                return;
            }
        }
        questionNumber++;
        setVisibility();
        changeQuestion();
        if (questionNumber > 9) {
            Toast.makeText(MainActivity.this, "Your score is " + score + "/9", Toast.LENGTH_SHORT).show();
            questionNumber = 9;
            editText.setEnabled(false);
        }
    }

    private int getQuestionNameId() {
        String name = "question_" + questionNumber;
        return getResources().getIdentifier(name, "string", getPackageName());
    }

    private int getQuestionId() {
        String name = "question_" + questionNumber + "_value";
        return getResources().getIdentifier(name, "string", getPackageName());
    }

    private void changeQuestion() {
        if (questionNumber < 8) {
            for (int i = 'a'; i < 'e'; i++) {
                String name = "answer_" + (char) i + "_question_" + questionNumber;
                int a = getResources().getIdentifier(name, "string", getPackageName());
                String b = "radioButton" + (i - 96);
                int x = getResources().getIdentifier(b, "id", getPackageName());
                RadioButton f = findViewById(x);
                f.setText(getResources().getString(a));
            }
        }
        question = findViewById(R.id.question_value);
        questionName = findViewById(R.id.question);
        if (questionNumber < 10) {
            questionName.setText(getQuestionNameId());
            question.setText(getQuestionId());
        }
    }

    private boolean calculateScore() {
        switch (questionNumber) {
            case 1:
                if (radioButton2.isChecked())
                    score++;
                break;
            case 2:
                if (radioButton4.isChecked())
                    score++;
                break;
            case 3:
                if (radioButton4.isChecked())
                    score++;
                break;
            case 4:
                if (radioButton4.isChecked())
                    score++;
                break;
            case 5:
                if (radioButton3.isChecked())
                    score++;
                break;
            case 6:
                if (radioButton3.isChecked())
                    score++;
                break;
            case 7:
                if (radioButton3.isChecked())
                    score++;
                break;
            case 8:
                if (!(checkBox1.isChecked() || checkBox2.isChecked() || checkBox3.isChecked() || checkBox4.isChecked()))
                    return false;
                else {
                    if (checkBox2.isChecked() && checkBox3.isChecked() && !(checkBox1.isChecked() || checkBox4.isChecked()))
                        score++;
                    return true;
                }
            case 9:
                if (!editText.getText().toString().trim().equalsIgnoreCase("")) {
                    if (editText.isEnabled())
                        if (editText.getText().toString().equals("44"))
                            score++;
                    return true;
                } else {
                    return false;
                }
        }
        if (radiogroup.getCheckedRadioButtonId() != -1) {
            radiogroup.clearCheck();
            return true;
        } else
            return false;
    }

    private void setVisibility() {
        RadioButton radioButton = findViewById(R.id.radioButton4);
        Button button = findViewById(R.id.button);
        EditText editText = findViewById(R.id.editText);
        if (questionNumber == 7) {
            radioButton.setVisibility(View.GONE);
        }
        if (questionNumber == 8) {
            radiogroup.setVisibility(View.GONE);
        }
        if (questionNumber == 9) {
            button.setText(R.string.view_score);
            editText.setVisibility(View.VISIBLE);
            radiogroup.setVisibility(View.INVISIBLE);
        }
        CheckBoxSetVisibility();
    }

    private void CheckBoxSetVisibility() {
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        if (questionNumber == 8)
            linearLayout.setVisibility(View.VISIBLE);
        else
            linearLayout.setVisibility(View.GONE);
    }
}
