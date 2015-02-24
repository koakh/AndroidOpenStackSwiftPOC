package com.koakh.swiftpoc.background;

import com.koakh.swiftpoc.background.ProgressListenerInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import retrofit.mime.TypedFile;

/**
 * Created by mario on 23/02/2015.
 */

/**
 * Android Retrofit - onProgressUpdate for showing Progress Notification
 * http://stackoverflow.com/questions/23348812/android-retrofit-onprogressupdate-for-showing-progress-notification
 */
public class CountingTypedFile extends TypedFile {

  private static final int BUFFER_SIZE = 4096;
  private final ProgressListenerInterface listener;

  public CountingTypedFile(String mimeType, File file, ProgressListenerInterface listener) {
    super(mimeType, file);
    this.listener = listener;
  }

  @Override public void writeTo(OutputStream out) throws IOException {
    byte[] buffer = new byte[BUFFER_SIZE];
    FileInputStream in = new FileInputStream(super.file());
    long total = 0;
    try {
      int read;
      while ((read = in.read(buffer)) != -1) {
        total += read;
        this.listener.transferred(total);
        out.write(buffer, 0, read);
      }
    } finally {
      in.close();
    }
  }
}