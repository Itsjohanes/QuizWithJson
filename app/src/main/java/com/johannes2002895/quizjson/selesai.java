package com.johannes2002895.quizjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class selesai extends AppCompatActivity {

    public static final String benar = "EXTRA_BENAR";
    public static final String salah = "EXTRA_SALAH";
    private TextView txtBenar,txtSalah;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai);
        txtBenar = findViewById(R.id.txtbenar);
        txtSalah = findViewById(R.id.txtsalah);
        int benars = getIntent().getIntExtra(benar,0);
        int salahs = getIntent().getIntExtra(salah,0);
        txtBenar.setText("Benar: " + benars);
        txtSalah.setText("Salah: " + salahs);
    }
}