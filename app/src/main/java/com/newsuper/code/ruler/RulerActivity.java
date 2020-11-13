package com.newsuper.code.ruler;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.newsuper.code.R;

public class RulerActivity extends AppCompatActivity {

    private SlideRuler slideruler;
    private TextView data_tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider_ruler);
        slideruler=(SlideRuler) findViewById(R.id.slideruler);
        data_tv=(TextView)findViewById(R.id.data);
        slideruler.setSlideRulerDataInterface(new SlideRulerDataInterface() {
            @Override
            public void getText(String data) {
                data_tv.setText(data);
            }
        });
    }


}
