package com.koakh.swiftpoc.rest.swift.objects.getobjectcontentandmetadata;

/**
 * Created by mario on 23/02/2015.
 */

/**
 * Retrofit: how to download/get a file
 * https://medium.com/@giuder91/retrofit-how-to-download-get-a-file-e83a9badcf6c
 */

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;
import retrofit.http.Header;
import retrofit.http.Path;

/**
 * Get object content and metadata
 * Downloads the object content and gets the object metadata.
 * http://developer.openstack.org/api-ref-objectstorage-v1.html#storage_object_services
 *
 * GET
 * /v1/{account}/{container}/{object}​
 *
 * curl -s $OS_SWIFT_URL/container/2015-02-01_21_57_23.png -X GET -H "Content-Type: text/html; charset=UTF-8" -H "X-Auth-Token: $TOKEN" > 2015-02-01_21_57_23.png
 */
public interface GetObjectContentAndMetadataServiceInterface {

  @GET("/{container}/{fileName}")
  void downloadFile(
    @Header("X-Auth-Token") String xAuthToken,
    @Path("container") String container,
    @Path("fileName") String fileName,
    Callback<Response> callback
  );

}
