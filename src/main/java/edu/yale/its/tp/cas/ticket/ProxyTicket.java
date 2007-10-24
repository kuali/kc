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
 * Represents a CAS proxy ticket (PT).
 */
public class ProxyTicket extends ServiceTicket {

  //*********************************************************************
  // ProxyTicket-specific private state

  private ProxyGrantingTicket grantor;


  //*********************************************************************
  // Constructor

  /** Constructs a new, immutable proxy ticket. */
  public ProxyTicket(ProxyGrantingTicket t, String service) {
    /*
     * By convention, a proxy ticket is never taken to proceed from
     * an initial login.  (That is, "renew=true" will always fail for
     * a proxy ticket.)  Because of this, we pass "false" to the parent
     * class's constructor.
     */
    super(t, service, false);
    this.grantor = t;
  }


  //*********************************************************************
  // ProxyTicket-specific public interface

  /** Retrieves the proxy ticket's lineage -- its chain of "trust." */
  public List getProxies() {
    return grantor.getProxies();
  }

}
