package com.koakh.swiftpoc.rest.identity.authenticate;

import com.google.gson.annotations.Expose;

public class Role {

  @Expose
  private String name;

  /**
   * @return The name
   */
  public String getName() {
    return name;
  }

  /**
   * @param name The name
   */
  public void setName(String name) {
    this.name = name;
  }

}
