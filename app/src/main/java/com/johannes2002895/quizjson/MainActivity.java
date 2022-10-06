package com.johannes2002895.quizjson;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_question;
    Button btn1,btn2,btn3,btn4;
    List <QuestionItem> questionItems;
    int currentQuestion  = 0;
    int correct = 0,wrong = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_question = findViewById(R.id.txtsoal);
        btn1 = findViewById(R.id.jawaban1);
        btn2 = findViewById(R.id.jawaban2);
        btn3 = findViewById(R.id.jawaban3);
        btn4 = findViewById(R.id.jawaban4);
        btn1.setOnClickListener(this);
        btn2.setOnClickListener(this);
        btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);
        loadAllQuestions();
        Collections.shuffle(questionItems);
        setQuestionScreen(currentQuestion);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.jawaban1){
            if(questionItems.get(currentQuestion).getAnswer1().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText(MainActivity.this, "Benar!", Toast.LENGTH_SHORT).show();

            }else{
                wrong++;
                Toast.makeText(MainActivity.this, "Salah", Toast.LENGTH_SHORT).show();

            }
        }
        if(view.getId() == R.id.jawaban2){
            if(questionItems.get(currentQuestion).getAnswer2().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText(MainActivity.this, "Benar!", Toast.LENGTH_SHORT).show();

            }else{

                wrong++;
                Toast.makeText(MainActivity.this, "Salah", Toast.LENGTH_SHORT).show();

            }
        }
        if(view.getId() == R.id.jawaban3){
            if(questionItems.get(currentQuestion).getAnswer3().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText(MainActivity.this, "Benar!", Toast.LENGTH_SHORT).show();

            }else{

                wrong++;
                Toast.makeText(MainActivity.this, "Salah", Toast.LENGTH_SHORT).show();

            }
        }
        if(view.getId() == R.id.jawaban4){
            if(questionItems.get(currentQuestion).getAnswer4().equals(questionItems.get(currentQuestion).getCorrect())){
                correct++;
                Toast.makeText(MainActivity.this, "Benar!", Toast.LENGTH_SHORT).show();

            }else{
                wrong++;
                Toast.makeText(MainActivity.this, "Salah", Toast.LENGTH_SHORT).show();

            }
        }
        if(currentQuestion < questionItems.size() -1 ){
            currentQuestion++;
            setQuestionScreen(currentQuestion);
        }else{
            Intent inten = new Intent(MainActivity.this,selesai.class);
            inten.putExtra(selesai.benar,correct);
            inten.putExtra(selesai.salah,wrong);
            startActivity(inten);
        }

    }
    private void setQuestionScreen(int number){
        tv_question.setText(questionItems.get(number).getQuestion());
        btn1.setText(questionItems.get(number).getAnswer1());
        btn2.setText(questionItems.get(number).getAnswer2());
        btn3.setText(questionItems.get(number).getAnswer3());
        btn4.setText(questionItems.get(number).getAnswer4());



    }


    //membuat list
    private void loadAllQuestions(){
        questionItems = new ArrayList<>();
        String jsonStr = loadJSONFromAsset("empdata.json");
        try{
            JSONObject jsonObject = new JSONObject(jsonStr);
            JSONArray question = jsonObject.getJSONArray("questions");
            for(int i = 0;i< question.length();i++){
                JSONObject questions = question.getJSONObject(i);

                String questionString = questions.getString("soal");
                String answer1 = questions.getString("a");
                String answer2 = questions.getString("b");
                String answer3 = questions.getString("c");
                String answer4 = questions.getString("d");
                String correct = questions.getString("kunci");


                questionItems.add(new QuestionItem(
                        questionString,
                        answer1,
                        answer2,
                        answer3,
                        answer4,
                        correct
                ));

            }
        }catch (JSONException e){
            e.printStackTrace();
        }
    }
    //menload Soal dari json
    private String loadJSONFromAsset(String file){
        String json = "";
        try{
            InputStream is = getAssets().open(file);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");


        }catch(Exception e){
                e.printStackTrace();
        }
        return json;
    }
}