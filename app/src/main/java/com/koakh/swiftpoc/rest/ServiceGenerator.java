package com.koakh.swiftpoc.rest;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

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

  public static <S> S createService(Context context, Class<S> serviceClass, String baseUrl) {

    RequestInterceptor requestInterceptor = new RequestInterceptor() {
      @Override
      public void intercept(RequestInterceptor.RequestFacade request) {
        request.addHeader("Content-Type", "application/json");
      }
    };

    //Setup OkHttpClient
    OkHttpClient client = new OkHttpClient();
    client.setConnectTimeout(10, TimeUnit.SECONDS);

    RestAdapter.Builder builder = new RestAdapter.Builder()
      .setEndpoint(baseUrl)
      .setLogLevel(RestAdapter.LogLevel.NONE)
      .setRequestInterceptor(requestInterceptor)
      .setErrorHandler(new ServiceErrorHandler(context))
      .setClient(new OkClient(client));

    RestAdapter adapter = builder.build();

    return adapter.create(serviceClass);
  }

}