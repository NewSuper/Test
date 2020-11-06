package com.newsuper.code.matisse;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.newsuper.code.R;
import com.yalantis.ucrop.UCrop;
import com.zhihu.matisse.Matisse;
import com.zhihu.matisse.MimeType;
import com.zhihu.matisse.engine.impl.GlideEngine;
import com.zhihu.matisse.internal.entity.CaptureStrategy;
import com.zhihu.matisse.listener.OnCheckedListener;
import com.zhihu.matisse.listener.OnSelectedListener;

import java.io.File;
import java.util.List;

public class MatisseActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matisse11);
        findViewById(R.id.btnMatisse).setOnClickListener(v->{
            callGallery();
        });
    }
    private static final int REQUEST_CODE_CHOOSE = 23;
    private void callGallery() {
        Matisse.from(this)
                .choose(MimeType.ofImage(), false)
                .countable(true)
                .capture(true)
               // .captureStrategy(new CaptureStrategy(true, "com.thunder.sample.fileprovider"))
                .captureStrategy(new CaptureStrategy(true, "com.newsuper.code"))

                .maxSelectable(1)
                .restrictOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
                .thumbnailScale(0.85f)
                .imageEngine(new GlideEngine())
                .setOnSelectedListener(new OnSelectedListener() {
                    @Override
                    public void onSelected(@NonNull List<Uri> uriList, @NonNull List<String> pathList) {
                    }
                })
                .showSingleMediaType(true)
                .originalEnable(true)
                .maxOriginalSize(10)
                .autoHideToolbarOnSingleTap(true)
                .setOnCheckedListener(new OnCheckedListener() {
                    @Override
                    public void onCheck(boolean isChecked) {
                    }
                })
                .forResult(REQUEST_CODE_CHOOSE);
    }
    private static final int AVATAR_WIDTH = 240;
    private static final int AVATAR_HEIGHT = 240;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == UCrop.RESULT_ERROR) {
            final Throwable cropError = UCrop.getError(data);
        } else if (resultCode != Activity.RESULT_OK) {
            return;
        }

        if (requestCode == REQUEST_CODE_CHOOSE) {
            if (data != null) {
                List<Uri> pathList = Matisse.obtainResult(data);
                Uri sourceUri = pathList.get(0);
                Uri destinationUri = Uri.fromFile(new File(getCacheDir(), "cropped"));
                UCrop.of(sourceUri, destinationUri)
                        .withAspectRatio(1, 1)
                        .withMaxResultSize(AVATAR_WIDTH, AVATAR_HEIGHT)
                        .start(this);
            }
        } else if (requestCode == UCrop.REQUEST_CROP) {
            final Uri resultUri = UCrop.getOutput(data);
            if (resultUri != null) {
                String path = resultUri.getPath();
                if (path != null) {
                    upImage(path);
                    System.out.println("------REQUEST_CROP------"+path);
                }
            }
        }
    }
    public void upImage(String file) {
        System.out.println("------upImage------"+file);
//        final String newPath = "headportrait/" + UUID.randomUUID() + file.substring(file.lastIndexOf("."));
//        PutObjectRequest request = new PutObjectRequest(SPUtils.getBucket(), newPath, file);
//        oss.asyncPutObject(request, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
//            @Override
//            public void onSuccess(PutObjectRequest request, PutObjectResult result) {
//                imgUrl = "https://" + SPUtils.getEndPoint() + "/" + newPath;
//                Message msg = new Message();
//                msg.what = 10001;
//                handler.sendMessage(msg);
//            }
//
//            @Override
//            public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
//                // 请求异常
//                if (clientException != null) {
//                    clientException.printStackTrace();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                }
//            }
//        });
    }

}
