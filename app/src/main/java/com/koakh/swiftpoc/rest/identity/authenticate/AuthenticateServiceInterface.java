package com.koakh.swiftpoc.rest.identity.authenticate;

import retrofit.Callback;
import retrofit.mime.TypedInput;

import retrofit.http.Body;
import retrofit.http.Headers;
import retrofit.http.POST;

/**
 * Created by mario on 16/02/2015.
 */

public interface AuthenticateServiceInterface {
  @Headers("Content-Type: application/json")
  @POST("/tokens")
  void authenticateUser(
    @Body TypedInput rawJsonBody,
    Callback<AuthenticateResponse> callback
  );
}
