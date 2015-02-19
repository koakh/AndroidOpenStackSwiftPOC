package com.koakh.swiftpoc.rest.swiftidentityauthenticate;

import com.google.gson.annotations.Expose;

public class AuthenticateResponse {

  @Expose
  private Access access;

  /**
   * @return The access
   */
  public Access getAccess() {
    return access;
  }

  /**
   * @param access The access
   */
  public void setAccess(Access access) {
    this.access = access;
  }
}
