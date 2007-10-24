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
 * Represents a generic cache of CAS tickets.  Ticket caches have the 
 * following characteristics:
 * 
 *  - At any given time, they store a list of valid tickets, each indexed
 *    with a unique String that acts as a ticket identifier.
 *  - Tickets can expire based on cache-specific rules.
 *
 */
public interface TicketCache {

  /** 
   * Adds a new Ticket to the cache, returning a String identifier
   * that uniquely matches the registered Ticket.  This String identifier
   * need not be globally unique, but two properties must be guaranteed:
   *   - The ticket cache must associate the identifier with the Ticket
   *     for the lifetime of the ticket.
   *   - The ticket cache must ensure that once a ticket expires,
   *     its identifier does not return a valid ticket.
   */
  String addTicket(Ticket t) throws TicketException;

  /**
   * Retrieves a Ticket based on a ticket identifier String.  If the
   * identifier matches no current ticket, returns null.
   */
  Ticket getTicket(String ticketId);

  /**
   * Deletes a ticket given a ticket identifier String.  (Implementations
   * need not provide a meaningful implementation of this method; they
   * must throw UnsupportedOperationException if they do not.)
   */
  void deleteTicket(String ticketId);

}
