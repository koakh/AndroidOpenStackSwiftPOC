package com.koakh.swiftpoc.rest.swift.containers.showcontainerdetailsandlistobjects;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContainerDetailsAndObjectsResponse {

  @Expose
  private Integer bytes;
  @SerializedName("content_type")
  @Expose
  private String contentType;
  @Expose
  private String hash;
  @SerializedName("last_modified")
  @Expose
  private String lastModified;
  @Expose
  private String name;

  /**
   * @return The bytes
   */
  public Integer getBytes() {
    return bytes;
  }

  /**
   * @param bytes The bytes
   */
  public void setBytes(Integer bytes) {
    this.bytes = bytes;
  }

  /**
   * @return The contentType
   */
  public String getContentType() {
    return contentType;
  }

  /**
   * @param contentType The content_type
   */
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }

  /**
   * @return The hash
   */
  public String getHash() {
    return hash;
  }

  /**
   * @param hash The hash
   */
  public void setHash(String hash) {
    this.hash = hash;
  }

  /**
   * @return The lastModified
   */
  public String getLastModified() {
    return lastModified;
  }

  /**
   * @param lastModified The last_modified
   */
  public void setLastModified(String lastModified) {
    this.lastModified = lastModified;
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
