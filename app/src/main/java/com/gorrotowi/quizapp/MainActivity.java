package com.gorrotowi.quizapp;

import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.edtxtMathQ)
    EditText edtxtMathQ;
    @BindView(R.id.rdGAnimals)
    RadioGroup rdGAnimals;
    @BindView(R.id.rdGVersions)
    RadioGroup rdGVersions;
    @BindView(R.id.rdAnimalsOne)
    RadioButton rdAnimalsOne;
    @BindView(R.id.rdAnimalsTwo)
    RadioButton rdAnimalsTwo;
    @BindView(R.id.rdAnimalsThree)
    RadioButton rdAnimalsThree;
    @BindView(R.id.rdVersionsOne)
    RadioButton rdVersionsOne;
    @BindView(R.id.rdVersionsTwo)
    RadioButton rdVersionsTwo;
    @BindView(R.id.rdVersionsThree)
    RadioButton rdVersionsThree;
    @BindView(R.id.cbxFirst)
    CheckBox cbxFirst;
    @BindView(R.id.cbxSecond)
    CheckBox cbxSecond;
    @BindView(R.id.cbxThird)
    CheckBox cbxThird;

    int correctAnswers;

    CompoundButton.OnCheckedChangeListener listenerOne;
    CompoundButton.OnCheckedChangeListener listenerTwo;
    CompoundButton.OnCheckedChangeListener listenerThree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listenerOne = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rdAnimalsTwo.setOnCheckedChangeListener(null);
                rdAnimalsThree.setOnCheckedChangeListener(null);
                rdAnimalsTwo.setChecked(false);
                rdAnimalsThree.setChecked(false);
                rdAnimalsTwo.setOnCheckedChangeListener(listenerTwo);
                rdAnimalsThree.setOnCheckedChangeListener(listenerThree);
            }
        };

        listenerTwo = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rdAnimalsOne.setOnCheckedChangeListener(null);
                rdAnimalsThree.setOnCheckedChangeListener(null);
                rdAnimalsOne.setChecked(false);
                rdAnimalsThree.setChecked(false);
                rdAnimalsOne.setOnCheckedChangeListener(listenerOne);
                rdAnimalsThree.setOnCheckedChangeListener(listenerThree);
            }
        };

        listenerThree = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                rdAnimalsOne.setOnCheckedChangeListener(null);
                rdAnimalsTwo.setOnCheckedChangeListener(null);
                rdAnimalsOne.setChecked(false);
                rdAnimalsTwo.setChecked(false);
                rdAnimalsOne.setOnCheckedChangeListener(listenerOne);
                rdAnimalsTwo.setOnCheckedChangeListener(listenerTwo);
            }
        };

        rdAnimalsOne.setOnCheckedChangeListener(listenerOne);
        rdAnimalsTwo.setOnCheckedChangeListener(listenerTwo);
        rdAnimalsThree.setOnCheckedChangeListener(listenerThree);

    }

    public void submitAnswers(View v) {
        if (allIsReply()) {
            if (edtxtMathQ.getText().toString().trim().equals(getString(R.string.first_question_correct_answer))) {
                correctAnswers++;
            }
            if (rdAnimalsOne.isChecked()) {
                correctAnswers++;
            }
            if (rdGVersions.getCheckedRadioButtonId() == R.id.rdVersionsTwo) {
                correctAnswers++;
            }
            if (cbxFirst.isChecked() && cbxThird.isChecked() && !cbxSecond.isChecked()) {
                correctAnswers++;
            }

            Toast.makeText(this, String.format(getString(R.string.number_of_correct_answers), correctAnswers), Toast.LENGTH_SHORT).show();

            if (correctAnswers == 4) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.results)
                        .setMessage(R.string.correct_answers)
                        .setPositiveButton(R.string.uhraa, null)
                        .create()
                        .show();
            } else {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle(R.string.results)
                        .setMessage(R.string.try_again_msg)
                        .setPositiveButton(R.string.retry, null)
                        .create()
                        .show();
            }

            correctAnswers = 0;
        }
    }

    private boolean allIsReply() {
        if (edtxtMathQ.getText().length() == 0) {
            Toast.makeText(this, R.string.please_answer_first, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!rdAnimalsOne.isChecked() && !rdAnimalsTwo.isChecked() && !rdAnimalsThree.isChecked()) {
            Toast.makeText(this, R.string.please_answer_second, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!rdVersionsOne.isChecked() && !rdVersionsTwo.isChecked() && !rdVersionsThree.isChecked()) {
            Toast.makeText(this, R.string.please_answer_third, Toast.LENGTH_SHORT).show();
            return false;
        } else if (!cbxFirst.isChecked() && !cbxSecond.isChecked() && !cbxThird.isChecked()) {
            Toast.makeText(this, R.string.please_answer_fourth, Toast.LENGTH_SHORT).show();
            return false;
        } else {
            return true;
        }

    }

}
