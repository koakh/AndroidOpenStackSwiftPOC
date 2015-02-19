package com.koakh.swiftpoc.rest.swiftidentityauthenticate;

import com.google.gson.annotations.Expose;

public class Tenant {

  @Expose
  private Object description;
  @Expose
  private Boolean enabled;
  @Expose
  private String id;
  @Expose
  private String name;

  /**
   * @return The description
   */
  public Object getDescription() {
    return description;
  }

  /**
   * @param description The description
   */
  public void setDescription(Object description) {
    this.description = description;
  }

  /**
   * @return The enabled
   */
  public Boolean getEnabled() {
    return enabled;
  }

  /**
   * @param enabled The enabled
   */
  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
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
