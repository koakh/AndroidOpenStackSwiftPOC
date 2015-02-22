package com.koakh.swiftpoc.rest.identity.authenticate;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metadata {

  @SerializedName("is_admin")
  @Expose
  private Integer isAdmin;
  @Expose
  private List<String> roles = new ArrayList<String>();

  /**
   * @return The isAdmin
   */
  public Integer getIsAdmin() {
    return isAdmin;
  }

  /**
   * @param isAdmin The is_admin
   */
  public void setIsAdmin(Integer isAdmin) {
    this.isAdmin = isAdmin;
  }

  /**
   * @return The roles
   */
  public List<String> getRoles() {
    return roles;
  }

  /**
   * @param roles The roles
   */
  public void setRoles(List<String> roles) {
    this.roles = roles;
  }

}
