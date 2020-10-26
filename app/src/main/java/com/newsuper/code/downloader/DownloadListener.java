package com.newsuper.code.downloader;

import java.io.File;


public interface DownloadListener {
    void onSuccess(File file);
    void onProgress(long downloadSize, float progress);
    void onStart();
    void onPause();
    void onFail();
    void onDelete(boolean delete);
}