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

/**
 * Represents a cache of tickets, each of which may be retrieved only
 * once.  That is, retrieval entails deletion.  Expiration also occurs
 * for inactivity.
 */
public abstract class OTUTicketCache extends ActiveTicketCache {

  /**
   * Constucts a new OTUTicketCache that will, additionally,
   * expire tickets after <i>tolerance</i> seconds of inactivity.
   */
  public OTUTicketCache(int tolerance) {
    super(tolerance);
  }

  // inherit Javadoc
  public Ticket getTicket(String ticketId) {
    Ticket t = super.getTicket(ticketId);
    deleteTicket(ticketId);
    return t;
  }
}
