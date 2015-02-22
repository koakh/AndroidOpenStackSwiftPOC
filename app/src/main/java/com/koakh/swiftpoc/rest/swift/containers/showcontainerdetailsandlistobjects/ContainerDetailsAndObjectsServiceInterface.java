package com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects;

import com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers.ListContainersResponse;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by mario on 22/02/2015.
 */
public interface ContainerDetailsAndObjectsServiceInterface {

  @Headers("Content-Type: application/json")
  @GET("/{container}/?format=json")
  void getContainerDetails(
    @Header("X-Auth-Token") String token,
    @Path("container") String container,
    Callback<List<ContainerDetailsAndObjectsResponse>> callback
  );
}
