package com.rafazup.bootcamp.servicosutils;

import javax.servlet.http.HttpServletRequest;

public class RequestUserIn {
    
    private String ip;
    private String userAgent;
  
    @Deprecated
    public RequestUserIn() {}
  
    public RequestUserIn(HttpServletRequest request) {
      this.ip = request.getRemoteAddr();
      this.userAgent = request.getHeader("User-Agent");
    }
  
    public String getIp() {
      return ip;
    }
  
    public String getUserAgent() {
      return userAgent;
    }

}
