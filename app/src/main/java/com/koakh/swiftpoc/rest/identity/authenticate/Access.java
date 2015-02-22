package com.koakh.swiftpoc.rest.identity.authenticate;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;

public class Access {

  @Expose
  private Token token;
  @Expose
  private List<ServiceCatalog> serviceCatalog = new ArrayList<ServiceCatalog>();
  @Expose
  private User user;
  @Expose
  private Metadata metadata;

  /**
   * @return The token
   */
  public Token getToken() {
    return token;
  }

  /**
   * @param token The token
   */
  public void setToken(Token token) {
    this.token = token;
  }

  /**
   * @return The serviceCatalog
   */
  public List<ServiceCatalog> getServiceCatalog() {
    return serviceCatalog;
  }

  /**
   * @param serviceCatalog The serviceCatalog
   */
  public void setServiceCatalog(List<ServiceCatalog> serviceCatalog) {
    this.serviceCatalog = serviceCatalog;
  }

  /**
   * @return The user
   */
  public User getUser() {
    return user;
  }

  /**
   * @param user The user
   */
  public void setUser(User user) {
    this.user = user;
  }

  /**
   * @return The metadata
   */
  public Metadata getMetadata() {
    return metadata;
  }

  /**
   * @param metadata The metadata
   */
  public void setMetadata(Metadata metadata) {
    this.metadata = metadata;
  }

}
