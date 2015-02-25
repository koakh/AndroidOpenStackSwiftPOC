package com.koakh.swiftpoc.rest;

/**
 * Created by mario on 25/02/2015.
 */

/*
on error the server sends JSON
{ "error": { "data": { "message":"A thing went wrong" } } }
*/

public class ErrorResponse {
  Error error;

  public static class Error {
    Data data;

    public static class Data {
      String message;
    }
  }
}
