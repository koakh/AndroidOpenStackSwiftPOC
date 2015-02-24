package com.koakh.swiftpoc.background;


import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.koakh.swiftpoc.app.Singleton;
import com.koakh.swiftpoc.rest.ServiceGenerator;
import com.koakh.swiftpoc.rest.swift.objects.createorreplaceobject.CreateOrReplaceObjectServiceInterface;
import com.koakh.swiftpoc.rest.swift.objects.getobjectcontentandmetadata.GetObjectContentAndMetadataServiceInterface;

import java.io.File;

import retrofit.client.Response;

/**
 * Created by mario on 23/02/2015.
 */

/**
 * <Params, Progress, Result>
 */


public class UploadFileTask extends AsyncTask<String, Integer, Response> {

  private Singleton mApp;
  private String mContainer;
  private String mFileName;
  private String mMimeType;

  public UploadFileTask(Context context, String container, String fileName, String fileType) {
    this.mContainer = container;
    this.mFileName = fileName;
    this.mMimeType = fileType;
    mApp = ((Singleton) context.getApplicationContext());
  }

  @Override
  protected Response doInBackground(String... params) {

    File file = new File(mFileName);
    if (file.exists()) {

      final long totalSize = file.length();
      Log.d(mApp.TAG, String.format("Download FileSize[%d]", totalSize));

      //Create Listener
      ProgressListenerInterface listener = new ProgressListenerInterface() {
        @Override
        public void transferred(long num) {
          int progress = (int) ((num / (float) totalSize) * 100);
          publishProgress(progress);
          //Log.d(mApp.TAG, String.format("Progress [%d]", progress));
        }
      };

      String serviceUrl = String.format(mApp.API_URL_SWIFT, mApp.getTenant());
      CreateOrReplaceObjectServiceInterface service = ServiceGenerator.createService(CreateOrReplaceObjectServiceInterface.class, serviceUrl);
      CountingTypedFile countingTypedFile = new CountingTypedFile(mMimeType, file, listener);
      Response response = service.uploadFileAsyncTask(mApp.getAuthenticateToken(), file.length(), mContainer, mFileName, countingTypedFile);

      return response;
    }
    else {
      return null;
    }
  }

  @Override
  protected void onProgressUpdate(Integer... values) {
    //Log.d(mApp.TAG, String.format("progress[%d]", values[0]));
    //do something with values[0], its the percentage so you can easily do
    //progressBar.setProgress(values[0]);
  }
}