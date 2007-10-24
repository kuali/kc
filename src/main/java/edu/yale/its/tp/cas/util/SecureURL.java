/*
 * Copyright 2007 The Kuali Foundation.
 * 
 * Licensed under the Educational Community License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 * http://www.opensource.org/licenses/ecl1.php
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package edu.yale.its.tp.cas.util;

import java.io.*;
import java.net.*;

/**
 * <p>A class housing some utility functions exposing secure URL validation
 * and content retrieval.  The rules are intended to be about as restrictive
 * as a common browser with respect to server-certificate validation.</p>
 *
 * NOTE: Depends on JSSE or JDK 1.4!
 */
public class SecureURL {

  /**
   * For testing only...
   */
  public static void main(String args[]) throws IOException {
    System.setProperty("java.protocol.handler.pkgs",
      "com.sun.net.ssl.internal.www.protocol");
    System.out.println(SecureURL.retrieve(args[0]));
  }

  /** 
   * Retrieve the contents from the given URL as a String, assuming the
   * URL's server matches what we expect it to match.
   */
  public static String retrieve(String url) throws IOException {
    BufferedReader r = null;
    try {
      URL u = new URL(url);
      if (!u.getProtocol().equals("https"))
        throw new IOException("only 'https' URLs are valid for this method");
      URLConnection uc = u.openConnection();
      uc.setRequestProperty("Connection", "close");
      r = new BufferedReader(new InputStreamReader(uc.getInputStream()));
      String line;
      StringBuffer buf = new StringBuffer();
      while ((line = r.readLine()) != null)
        buf.append(line + "\n");
      return buf.toString();
    } finally {
      try {
        if (r != null)
          r.close();
      } catch (IOException ex) {
        // ignore
      }
    }
  }
}
