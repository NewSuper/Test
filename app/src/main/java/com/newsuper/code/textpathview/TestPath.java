package com.newsuper.code.textpathview;


import android.graphics.Path;


public class TestPath extends Path {
    public TestPath(){
        init();
    }

    private void init() {
        addCircle(450,180,150,Direction.CCW);
        addCircle(450,180,100,Direction.CW);
        addCircle(450,180,50,Direction.CCW);
        moveTo(450,180);
        lineTo(600,330);
    }
}