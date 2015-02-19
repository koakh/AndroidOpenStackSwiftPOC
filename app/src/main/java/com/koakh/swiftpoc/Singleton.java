package com.koakh.swiftpoc;

import android.app.Application;

import com.koakh.swiftpoc.rest.swiftidentityauthenticate.AuthenticateResponse;

/**
 * Created by mario on 15/02/2015.
 */
public class Singleton extends Application {

  public final String TAG = "SwiftPOC";

  private AuthenticateResponse authenticateResponse;

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
}
