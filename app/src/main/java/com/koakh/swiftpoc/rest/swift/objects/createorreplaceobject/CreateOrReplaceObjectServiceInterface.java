package com.koakh.swiftpoc.rest.swift.objects.createorreplaceobject;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Body;
import retrofit.http.Header;
import retrofit.http.PUT;
import retrofit.http.Path;
import retrofit.mime.TypedFile;

/**
 * Created by mario on 22/02/2015.
 */

/**
 * Retrofit: How to upload/post a file
 * https://medium.com/@giuder91/retrofit-how-to-upload-post-a-file-46f28fe2a37e
 */

/**
 * HTTP/1.1 201 Created
 * Last-Modified: Sun, 22 Feb 2015 22:40:37 GMT
 * Content-Length: 0
 * Etag: 49bd0e5c02a71f34ad438660ca5d3874
 * Content-Type: text/html; charset=UTF-8
 * X-Trans-Id: tx775f80b0ddaf445e87074-0054ea5ae4
 * Date: Sun, 22 Feb 2015 22:40:37 GMT
 */
public interface CreateOrReplaceObjectServiceInterface {

  @PUT("/{container}/{fileName}")
  void uploadFile(
    @Header("X-Auth-Token") String xAuthToken,
    @Header("Content-Length") Long contentLength,
    @Path("container") String container,
    @Path("fileName") String fileName,
    //Plain Body Without Content-Disposition and Trash, Send Body :)
    @Body TypedFile typedFile,
    Callback<Response> callback
  );

}
