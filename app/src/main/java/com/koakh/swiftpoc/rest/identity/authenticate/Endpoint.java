package com.koakh.swiftpoc.rest.identity.authenticate;

import com.google.gson.annotations.Expose;

public class Endpoint {

  @Expose
  private String adminURL;
  @Expose
  private String region;
  @Expose
  private String internalURL;
  @Expose
  private String id;
  @Expose
  private String publicURL;

  /**
   * @return The adminURL
   */
  public String getAdminURL() {
    return adminURL;
  }

  /**
   * @param adminURL The adminURL
   */
  public void setAdminURL(String adminURL) {
    this.adminURL = adminURL;
  }

  /**
   * @return The region
   */
  public String getRegion() {
    return region;
  }

  /**
   * @param region The region
   */
  public void setRegion(String region) {
    this.region = region;
  }

  /**
   * @return The internalURL
   */
  public String getInternalURL() {
    return internalURL;
  }

  /**
   * @param internalURL The internalURL
   */
  public void setInternalURL(String internalURL) {
    this.internalURL = internalURL;
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
   * @return The publicURL
   */
  public String getPublicURL() {
    return publicURL;
  }

  /**
   * @param publicURL The publicURL
   */
  public void setPublicURL(String publicURL) {
    this.publicURL = publicURL;
  }

}
