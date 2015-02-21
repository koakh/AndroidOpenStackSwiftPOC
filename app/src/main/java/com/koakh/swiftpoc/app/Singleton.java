package com.koakh.swiftpoc.app;

import android.app.Application;
import android.widget.EditText;

import com.koakh.swiftpoc.rest.swiftidentityauthenticate.AuthenticateResponse;

/**
 * Created by mario on 15/02/2015.
 */
public class Singleton extends Application {

  public final String TAG = "SwiftPOC";

  /**
   * Swift/OpenStack
   */
  //Store FULL Authentication Response, Includes all Details, ex Token used for all Request
  private AuthenticateResponse authenticateResponse;

  private EditText editTextLog;

  public Singleton() {
  }

  public String getTag() {
    return TAG;
  }

  public AuthenticateResponse getAuthenticateResponse() {
    return authenticateResponse;
  }
  public void setAuthenticateResponse(AuthenticateResponse authenticateResponse) {
    this.authenticateResponse = authenticateResponse;
  }

  public String getAuthenticateToken() {
    return authenticateResponse.getAccess().getToken().getId();
  }

  public String getTenant() {
    return authenticateResponse.getAccess().getToken().getTenant().getId();
  }

  public EditText getEditTextLog() {
    return editTextLog;
  }

  public void setEditTextLog(EditText editTextLog) {
    this.editTextLog = editTextLog;
  }
}
