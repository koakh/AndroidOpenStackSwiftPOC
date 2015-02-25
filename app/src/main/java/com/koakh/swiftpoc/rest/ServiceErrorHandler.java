package com.koakh.swiftpoc.rest;

import android.content.Context;
import android.util.Log;

import com.koakh.swiftpoc.R;
import com.koakh.swiftpoc.app.Singleton;

import retrofit.ErrorHandler;
import retrofit.RetrofitError;

/**
 * Created by mario on 24/02/2015.
 */

//

/**
 * benvium / retrofit-custom-error-handling.java
 * https://gist.github.com/benvium/66bf24e0de80d609dac0#file-retrofit-custom-error-handling-java
 * Converts the complex error structure into a single string you can get with error.getLocalizedMessage() in Retrofit error handlers.
 * Also deals with there being no network available
 *
 * Uses a few string IDs for user-visible error messages
 */
class ServiceErrorHandler implements ErrorHandler {

  private Context mContext;
  private Singleton mApp;

  public ServiceErrorHandler(Context context) {
    this.mContext = context;
    mApp = ((Singleton) context.getApplicationContext());
  }

  @Override
  public Throwable handleError(RetrofitError cause) {

    String errorDescription;

    if (cause.getKind() == RetrofitError.Kind.NETWORK) {
      errorDescription = mContext.getString(R.string.error_network) + " : " + cause.getMessage();
    } else {
      if (cause.getResponse() == null) {
        errorDescription = mContext.getString(R.string.error_no_response);
      } else {
        // Error message handling - return a simple error to Retrofit handlers..
        try {
          ErrorResponse errorResponse = (ErrorResponse) cause.getBodyAs(ErrorResponse.class);
          errorDescription = errorResponse.error.data.message;
        } catch (Exception ex) {
          try {
            errorDescription = mContext.getString(R.string.error_network_http_error, cause.getResponse().getStatus());
          } catch (Exception ex2) {
            Log.e(mApp.TAG, "handleError: " + ex2.getLocalizedMessage());
            errorDescription = mContext.getString(R.string.error_unknown);
          }
        }
      }
    }
    //Log.e(mApp.TAG, errorDescription);
    return new Exception(errorDescription);
  }
}
