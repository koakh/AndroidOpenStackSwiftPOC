package com.koakh.swiftpoc.rest;

import java.util.List;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by mario on 15/02/2015.
 */

//Test url https://api.github.com/users/koakh/repos

public interface IGitHubService {
  @GET("/users/{user}/repos")
  List<Object> listRepos(@Path("user") String user);
}
