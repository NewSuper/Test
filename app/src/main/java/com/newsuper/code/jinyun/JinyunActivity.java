package com.newsuper.code.jinyun;

import android.Manifest;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.media.audiofx.Visualizer;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;

import android.view.WindowManager;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.newsuper.code.R;

import permison.PermissonUtil;
import permison.listener.PermissionListener;

@RequiresApi(api = Build.VERSION_CODES.KITKAT)
public class JinyunActivity extends AppCompatActivity {

    String[] permissons = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO};

    ImageView iv_bg, ivShowPic;
    ObjectAnimator objectAnimator;
    JinyunView jinyunView;
    private Visualizer visualizer;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_jinyun);
        initView();
        setBackground();
        PermissonUtil.checkPermission(this, new PermissionListener() {
            @Override
            public void havePermission() {
                initVisualizer();
            }

            @Override
            public void requestPermissionFail() {

            }
        }, permissons);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView() {
        jinyunView = findViewById(R.id.sv_bg);
        iv_bg = findViewById(R.id.iv_bg);

        ivShowPic = findViewById(R.id.ivShowPic);
        Glide.with(JinyunActivity.this).asBitmap().addListener(new RequestListener<Bitmap>() {
            @Override
            public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                return false;
            }

            @Override
            public boolean onResourceReady(Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {
                jinyunView.setmPaintColor(ImageUtil.getColor(resource, 3).getRgb());
                return false;
            }
        }).load(R.drawable.gift2).into(ivShowPic);


        ivShowPic.setClipToOutline(true);
        ivShowPic.setOutlineProvider(ImageUtil.getOutline(true, 20, 1));

        objectAnimator = ObjectAnimator.ofFloat(ivShowPic, "rotation", 0f, 360f);
        objectAnimator.setDuration(20 * 1000);
        objectAnimator.setRepeatMode(ValueAnimator.RESTART);
        objectAnimator.setInterpolator(new LinearInterpolator());
        objectAnimator.setRepeatCount(-1);
        objectAnimator.start();
    }

    private void setBackground() {
        Bitmap bitmap = BlurUtil.doBlur(BitmapFactory.decodeResource(getResources(), R.drawable.bi_bg2_icon), 10, 30);
        iv_bg.setImageBitmap(bitmap);
        iv_bg.setDrawingCacheEnabled(true);
        getBitmap();

        ColorMatrix colorMatrix = new ColorMatrix();
        colorMatrix.setScale(0.7f, 0.7f, 0.7f, 1);
        ColorMatrixColorFilter colorFilter = new ColorMatrixColorFilter(colorMatrix);
        iv_bg.setColorFilter(colorFilter);
    }

    public void getBitmap() {
        new Thread(() -> {
            //启用DrawingCache并创建位图
            iv_bg.buildDrawingCache();
            while (iv_bg.getDrawingCache() == null) {
                iv_bg.buildDrawingCache();
                SystemClock.sleep(10);
            }
            Bitmap bitmap2 = Bitmap.createBitmap(iv_bg.getDrawingCache());
            bitmap2 = Bitmap.createBitmap(bitmap2, 0, jinyunView.getTop(), jinyunView.getWidth(), jinyunView.getHeight());
            jinyunView.setBitmapBg(bitmap2);
            iv_bg.setDrawingCacheEnabled(false);
        }).start();

    }


    AudioVisualConverter visualConverter = new AudioVisualConverter();

    private Visualizer.OnDataCaptureListener dataCaptureListener = new Visualizer.OnDataCaptureListener() {
        @Override
        public void onWaveFormDataCapture(Visualizer visualizer, final byte[] waveform, int samplingRate) {
            jinyunView.setmBytes(visualConverter.converter(waveform));
        }

        @Override
        public void onFftDataCapture(Visualizer visualizer, final byte[] fft, int samplingRate) {

        }
    };

    public void initVisualizer() {
        visualizer = new Visualizer(0);
        //采样的最大值
        int captureSize = Visualizer.getCaptureSizeRange()[1];
        //采样的频率
        int captureRate = Visualizer.getMaxCaptureRate() * 2 / 3;
        visualizer.setCaptureSize(captureSize);
        visualizer.setDataCaptureListener(dataCaptureListener, captureRate, true, true);
        visualizer.setScalingMode(Visualizer.SCALING_MODE_NORMALIZED);
        visualizer.setMeasurementMode(Visualizer.MEASUREMENT_MODE_PEAK_RMS);
        visualizer.setEnabled(true);
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (objectAnimator.isPaused()) {
            objectAnimator.resume();
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        if (objectAnimator.isRunning()) {
            objectAnimator.pause();
        }
    }

}
