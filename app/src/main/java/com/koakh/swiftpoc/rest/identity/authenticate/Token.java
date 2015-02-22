package com.koakh.swiftpoc.rest.identity.authenticate;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Token {

  @SerializedName("issued_at")
  @Expose
  private String issuedAt;
  @Expose
  private String expires;
  @Expose
  private String id;
  @Expose
  private Tenant tenant;

  /**
   * @return The issuedAt
   */
  public String getIssuedAt() {
    return issuedAt;
  }

  /**
   * @param issuedAt The issued_at
   */
  public void setIssuedAt(String issuedAt) {
    this.issuedAt = issuedAt;
  }

  /**
   * @return The expires
   */
  public String getExpires() {
    return expires;
  }

  /**
   * @param expires The expires
   */
  public void setExpires(String expires) {
    this.expires = expires;
  }

  /**
   * @return The id
   */
  public String getId() {
    return id;
  }

  /**
   * @param id The id
   */
  public void setId(String id) {
    this.id = id;
  }

  /**
   * @return The tenant
   */
  public Tenant getTenant() {
    return tenant;
  }

  /**
   * @param tenant The tenant
   */
  public void setTenant(Tenant tenant) {
    this.tenant = tenant;
  }

}
