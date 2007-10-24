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
package edu.yale.its.tp.cas.ticket;

import java.util.*;

/**
 * Represents a CAS proxy-granting ticket (PGT), used to retrieve proxy
 * tickets (PTs).  Note that we extend TicketGrantingTicket; this doesn't
 * mean we can act as a TGC:  a TicketCache for TGCs will never store a
 * PGT.
 */
public class ProxyGrantingTicket extends TicketGrantingTicket {

  //*********************************************************************
  // Private, ticket state

  /**
   * A PGT is constructed with a ServiceTicket (potentially a ProxyTicket).
   * We store this ticket to inherit ticket expiration.
   */
  private ServiceTicket parent;

  /**
   * A PGT is also consturcted with the ID of the proxy.  In CAS 2.0.1,
   * this ID corresponds to the callback URL to which the PGT's ID
   * is sent.
   */
  private String proxyId;

  //*********************************************************************
  // Constructor

  /** Constructs a new, immutable ProxyGrantingTicket. */
  public ProxyGrantingTicket(ServiceTicket parent, String proxyId) {
    super(parent.getUsername());
    this.parent = parent;
    this.proxyId = proxyId;
  }


  //*********************************************************************
  // Public interface

  /** Retrieves the ticket's username. */
  public String getUsername() {
    return parent.getUsername();
  }

  /** Returns the ticket that was exchanged for this ProxyGrantingTicket. */
  public ServiceTicket getParent() {
    return parent;
  }
 
  /**
   * Returns the identifier for the service to whom this ticket will
   * grant proxy tickets.
   */
  public String getProxyService() {
    return proxyId;
  }

  /** Retrieves trust chain. */
  public List getProxies() {
    List l = new ArrayList();
    l.add(getProxyService());
    if (parent.getGrantor() instanceof ProxyGrantingTicket) {
      ProxyGrantingTicket p = (ProxyGrantingTicket) parent.getGrantor();
      l.addAll(p.getProxies());
    }
    return l;
  }

  /**
   * Returns true if the ticket (or any ticket in its grantor chain) is
   * expired, false otherwise.
   */
  public boolean isExpired() {
    return super.isExpired() || parent.getGrantor().isExpired();
  }
}
