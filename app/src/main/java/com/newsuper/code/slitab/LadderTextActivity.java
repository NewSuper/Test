package com.newsuper.code.slitab;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.newsuper.code.R;

public class LadderTextActivity extends AppCompatActivity {
    private LadderTextView leftLadderTv, rightLadderTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ladder_text);
        leftLadderTv = findViewById(R.id.leftLadderTv);
        rightLadderTv = findViewById(R.id.rightLadderTv);

        initListeners();
    }

    private void initListeners() {
        leftLadderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!leftLadderTv.isSelected()) {
                    leftLadderTv.setMSelected(!leftLadderTv.isSelected());
                    rightLadderTv.setMSelected(!rightLadderTv.isSelected());
                }
            }
        });

        rightLadderTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!rightLadderTv.isSelected()) {
                    leftLadderTv.setMSelected(!leftLadderTv.isSelected());
                    rightLadderTv.setMSelected(!rightLadderTv.isSelected());
                }
            }
        });
    }
}
