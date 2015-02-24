package com.koakh.swiftpoc.rest;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by mario on 18/02/2015.
 */
public class ServiceGenerator {

  // No need to instantiate this class.
  private ServiceGenerator() {
  }

  public static <S> S createService(Class<S> serviceClass, String baseUrl) {

    RequestInterceptor requestInterceptor = new RequestInterceptor() {
      @Override
      public void intercept(RequestInterceptor.RequestFacade request) {
        request.addHeader("Content-Type", "application/json");
      }
    };

    RestAdapter.Builder builder = new RestAdapter.Builder()
      .setEndpoint(baseUrl)
      .setLogLevel(RestAdapter.LogLevel.BASIC)
      .setRequestInterceptor(requestInterceptor)
      .setClient(
        new OkClient(new OkHttpClient())
      );

    RestAdapter adapter = builder.build();

    return adapter.create(serviceClass);
  }

}
