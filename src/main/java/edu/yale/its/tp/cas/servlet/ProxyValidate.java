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
package edu.yale.its.tp.cas.servlet;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import edu.yale.its.tp.cas.ticket.*;

/**
 * Handles PT validations and sub-PGT acquisitions for the Central
 * Authentication Service.  Subclassed (versus collapsed into superclass)
 * in order to provided rigid segmentation, at runtime, between caches,
 * even though they share an implementation.
 */
public class ProxyValidate extends ServiceValidate {

  //*********************************************************************
  // Private state

  private ServletContext app;
  private String serviceValidate;				// URL

  //*********************************************************************
  // Initialization

  public void init(ServletConfig config) throws ServletException {
    // let our superclass handle initialization
    super.init(config);

    // replace the ST cache with the PT cache
    stCache = 
      (ServiceTicketCache) config.getServletContext().getAttribute("ptCache");

    // read relevant parameters
    app = config.getServletContext();
    serviceValidate =
      app.getInitParameter("edu.yale.its.tp.cas.serviceValidate");
    if (serviceValidate == null)
      throw new ServletException("need edu.yale.its.tp.cas.serviceValidate");
  }

 //*********************************************************************
  // Request handling  

  public void doGet(HttpServletRequest request, HttpServletResponse response)
      throws IOException, ServletException {
    /*
     * Interesting approach:  if we have a service ticket (one that starts
     * with "ST"), we forward to the actual serviceValidate URL, which
     * happens to be implemented by our superclass.  Otherwise, we invoke
     * the superclass directly, which lets modifications we've made in
     * init() take effect.
     */
    String ticketString = request.getParameter("ticket");
    if (ticketString != null && ticketString.startsWith("ST"))
      app.getRequestDispatcher(serviceValidate).forward(request, response);
    else
      super.doGet(request, response);
  }


  //*********************************************************************
  // Response-handling methods

  /** Sends a validation success message to the given PrintWriter. */
  protected void validationSuccess(PrintWriter out, ServiceTicket st,
      String pgtIOU) {
    // downcast the ticket
    if (!(st instanceof ProxyTicket)) {
      throw new IllegalArgumentException(
        "can't take generic ServiceTicket; need ProxyTicket");
    }
    ProxyTicket pt = (ProxyTicket) st;

    // send the response
    out.println("<cas:serviceResponse xmlns:cas='http://www.yale.edu/tp/cas'>");
    out.println("  <cas:authenticationSuccess>");
    out.println("    <cas:user>" + pt.getUsername() + "</cas:user>");
    if (pgtIOU != null && !pgtIOU.equals("")) {
      out.println("    <cas:proxyGrantingTicket>" + pgtIOU +
        "</cas:proxyGrantingTicket>");
    }
    out.println("    <cas:proxies>");
    {
      Iterator proxies = pt.getProxies().iterator();
      while (proxies.hasNext())
	out.println("      <cas:proxy>" + proxies.next() + "</cas:proxy>");
    }
    out.println("    </cas:proxies>");
    out.println("  </cas:authenticationSuccess>");
    out.println("</cas:serviceResponse>");
  }
}
