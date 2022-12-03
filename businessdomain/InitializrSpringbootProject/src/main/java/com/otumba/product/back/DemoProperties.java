/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.otumba.product.back;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author peter coronel
 */
@Configuration
@ConfigurationProperties(prefix = "example.datasource")
public class DemoProperties {

  private String url;
  private String username;
  private String password;
  private String servicecheck;

    public String getServicecheck() {
        return servicecheck;
    }

    public void setServicecheck(String servicecheck) {
        this.servicecheck = servicecheck;
    }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
