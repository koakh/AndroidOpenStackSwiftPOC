package com.koakh.swiftpoc.rest.swiftaccountslistcontainers;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;

/**
 * Created by mario on 19/02/2015.
 */
public interface IListContainers {

  @Headers("Content-Type: application/json")
  @GET("")
  List<ListContainersResponse> listContainers(
    @Header("X-Auth-Token") String token,
    Callback<ListContainersResponse> callback
  );
}
