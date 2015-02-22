package com.koakh.swiftpoc.rest.identity.authenticate;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {

  @Expose
  private String username;
  @SerializedName("roles_links")
  @Expose
  private List<Object> rolesLinks = new ArrayList<Object>();
  @Expose
  private String id;
  @Expose
  private List<Role> roles = new ArrayList<Role>();
  @Expose
  private String name;

  /**
   * @return The username
   */
  public String getUsername() {
    return username;
  }

  /**
   * @param username The username
   */
  public void setUsername(String username) {
    this.username = username;
  }

  /**
   * @return The rolesLinks
   */
  public List<Object> getRolesLinks() {
    return rolesLinks;
  }

  /**
   * @param rolesLinks The roles_links
   */
  public void setRolesLinks(List<Object> rolesLinks) {
    this.rolesLinks = rolesLinks;
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
   * @return The roles
   */
  public List<Role> getRoles() {
    return roles;
  }

  /**
   * @param roles The roles
   */
  public void setRoles(List<Role> roles) {
    this.roles = roles;
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
