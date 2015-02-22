package com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;

/**
 * Created by mario on 19/02/2015.
 */
public interface ListContainersServiceInterface {

  @Headers("Content-Type: application/json")
  @GET("/?format=json")
  void getContainers(
    @Header("X-Auth-Token") String token,
    Callback<List<ListContainersResponse>> callback
  );
}
