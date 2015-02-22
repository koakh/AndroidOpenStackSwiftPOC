package com.koakh.swiftpoc.rest.swift.accounts.showaccountdetailsandlistcontainers;

import com.google.gson.annotations.Expose;

public class ListContainersResponse {

  @Expose
  private Integer bytes;
  @Expose
  private Integer count;
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
   * @return The count
   */
  public Integer getCount() {
    return count;
  }

  /**
   * @param count The count
   */
  public void setCount(Integer count) {
    this.count = count;
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
