package com.newsuper.code.textpathview;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.newsuper.code.R;

public class PathActivity  extends AppCompatActivity implements View.OnClickListener {
    private Button btn_first, btn_second, btn_third, btn_forth, btn_fifth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_path);
        btn_first = findViewById(R.id.btn_first);
        btn_second = findViewById(R.id.btn_second);
        btn_third = findViewById(R.id.btn_third);
        btn_forth = findViewById(R.id.btn_forth);
        btn_fifth = findViewById(R.id.btn_fifth);
        btn_first.setOnClickListener(this);
        btn_second.setOnClickListener(this);
        btn_third.setOnClickListener(this);
        btn_forth.setOnClickListener(this);
        btn_fifth.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_first:
                startActivity(new Intent(this, PathFirstActivity.class));
                break;
            case R.id.btn_second:
                startActivity(new Intent(this, PathSecondActivity.class));
                break;
            case R.id.btn_third:
                startActivity(new Intent(this, PathThirdActivity.class));
                break;
            case R.id.btn_forth:
                startActivity(new Intent(this, PathForthActivity.class));
                break;
            case R.id.btn_fifth:
                startActivity(new Intent(this, PathFifthActivity.class));
                break;
        }
    }
}
