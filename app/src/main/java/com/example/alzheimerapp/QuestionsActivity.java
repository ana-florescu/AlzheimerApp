package com.example.alzheimerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionsActivity extends AppCompatActivity {
    TextView tv;
    Button submitbutton, quitbutton;
    RadioGroup radio_g;
    RadioButton rb1,rb2,rb3,rb4;

    String questions[] = {
            "Alzheimer disease is the most common form of which of these?",
            "How is Alzheimer disease diagnosed?",
            "Physiologically, what happens to the brain as Alzheimer disease progresses?",
            "Which of these is the strongest risk factor for developing the disease?",
            "Occasionally, other health conditions may mimic this disease. What are they?",
            "Signs of Alzheimer disease include which of these symptoms?",
            "Which age group has the highest rate of Alzheimer cases reported?",
            "Because no medicines cure this condition, emphasis is put on delaying the onset of severe symptoms. Which of these strategies helps?",
            "The average time from the onset of symptoms to death is how long?",
            "If you care for a relative with Alzheimer disease, which of these measures will help stabilize the patient mentally?"
    };
    String answers[] = {"Dementia","All of the above","Many cells die","Age","All of the above","All of the above","85 and older","All of the above","8 years","Establish a regular routine"};
    String opt[] = {
            "Malnutrition","Dementia"," Fatigue","Psychosis",
            "Mental-status tests","Blood tests","Neurological tests","All of the above",
            "Tissue swells","Fluid collects","Many cells die","Brain-stem atrophies",
            "Heredity","Age","Exposure to toxins","None of the above",
            "Side effects to medicine","Dehydration","Poor nutrition","All of the above",
            "Loss of memory","Increase in irritability","Restlessness","All of the above",
            "85 and older","74 to 84","65 to 74","55 to 65",
            "Exercise","Hobbies","Good nutrition","All of the above",
            "20 years","8 years","6 years","4 years",
            "Move to a small apartment","Correct \"bad\" behavior gently","Establish a regular routine","Repaint or buy new furniture"
    };
    int flag=0;
    public static int marks=0,correct=0,wrong=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        final TextView score = (TextView)findViewById(R.id.textView4);
        TextView textView=(TextView)findViewById(R.id.DispName);
        Intent intent = getIntent();
        String name= intent.getStringExtra("myname");

        if (name.trim().equals(""))
            textView.setText("Hello User");
        else
            textView.setText("Hello " + name);

        submitbutton=(Button)findViewById(R.id.button3);
        quitbutton=(Button)findViewById(R.id.buttonquit);
        tv=(TextView) findViewById(R.id.tvque);

        radio_g=(RadioGroup)findViewById(R.id.answersgrp);
        rb1=(RadioButton)findViewById(R.id.radioButton);
        rb2=(RadioButton)findViewById(R.id.radioButton2);
        rb3=(RadioButton)findViewById(R.id.radioButton3);
        rb4=(RadioButton)findViewById(R.id.radioButton4);
        tv.setText(questions[flag]);
        rb1.setText(opt[0]);
        rb2.setText(opt[1]);
        rb3.setText(opt[2]);
        rb4.setText(opt[3]);
        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //int color = mBackgroundColor.getColor();
                //mLayout.setBackgroundColor(color);

                if(radio_g.getCheckedRadioButtonId()==-1)
                {
                    Toast.makeText(getApplicationContext(), "Please select one choice", Toast.LENGTH_SHORT).show();
                    return;
                }
                RadioButton uans = (RadioButton) findViewById(radio_g.getCheckedRadioButtonId());
                String ansText = uans.getText().toString();
//                Toast.makeText(getApplicationContext(), ansText, Toast.LENGTH_SHORT).show();
                if(ansText.equals(answers[flag])) {
                    correct++;
                    Toast.makeText(getApplicationContext(), "Correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    wrong++;
                    Toast.makeText(getApplicationContext(), "Wrong", Toast.LENGTH_SHORT).show();
                }

                flag++;

                if (score != null)
                    score.setText(""+correct);

                if(flag<questions.length)
                {
                    tv.setText(questions[flag]);
                    rb1.setText(opt[flag*4]);
                    rb2.setText(opt[flag*4 +1]);
                    rb3.setText(opt[flag*4 +2]);
                    rb4.setText(opt[flag*4 +3]);
                }
                else
                {
                    marks=correct;
                    Intent in = new Intent(getApplicationContext(),ResultsActivity.class);
                    startActivity(in);
                }
                radio_g.clearCheck();
            }
        });

        quitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),ResultsActivity.class);
                startActivity(intent);
            }
        });

    }
}