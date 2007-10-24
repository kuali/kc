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
 * Represents a cache of tickets that each expire after a configurable
 * period of inactivity (i.e., not being retrieved).
 */
public abstract class ActiveTicketCache implements TicketCache {

  //*********************************************************************
  // Placeholders for subclasses to insert relevant logic.

  /** Generates and returns a new, unique ticket ID */
  protected abstract String newTicketId();

  /** Stores the given ticket, associating it with the given identifier. */
  protected abstract void storeTicket(String ticketId, Ticket t)
    throws TicketException;

  /** Retrieves the ticket with the given identifier. */
  protected abstract Ticket retrieveTicket(String ticketId);

  // subclasses also need deleteTicket(String ticketId) from TicketCache

  //*********************************************************************
  // TicketCache implementation methods

  // inherit Javadoc
  public synchronized String addTicket(Ticket t) throws TicketException {
    String ticketId = newTicketId();
    resetTimer(ticketId);
    storeTicket(ticketId, t);
    return ticketId;
  }

  // inherit Javadoc
  public Ticket getTicket(String ticketId) {
    resetTimer(ticketId);
    return retrieveTicket(ticketId);
  }


  //*********************************************************************
  // Private state

  /** Stores the last accessed Date for each currently valid ticketId. */
  private Map timer;

  /** Number of seconds after which the current cache will expire tickets. */
  private int tolerance;


  //*********************************************************************
  // Constructor

  /**
   * Constucts a new ActiveTicketCache that will expire tickets after
   * <i>tolerance</i> seconds of inactivity.
   */
  public ActiveTicketCache(int tolerance) {
    // set up the timer
    timer = Collections.synchronizedMap(new WeakHashMap());
    this.tolerance = tolerance;

    // set up the timer thread
    Thread t = new timerThread();
    t.setDaemon(true);
    t.start();
  }

  /** A thread used to monitor and clean up inactive tickets. */
  private class timerThread extends Thread {
    public static final int PERIOD = 60*1000;
    public boolean done = false;
    public void run() {
      while (!done) {
        try {
          expireInactive();
          Thread.sleep(PERIOD);
        } catch (InterruptedException ex) {
           // ignore
        } 
      }
    }
  }

  //*********************************************************************
  // Timer management

  /**
   * Resets (and creates, if necessary) the timer entry for the given
   * ticket identifier
   */
  private void resetTimer(String ticketId) {
    timer.put(ticketId, new Date());
  }

  /** Expires all inactive tickets. */
  private void expireInactive() {
    // for consistency, record starting time
    long now = (new Date()).getTime();

    // remove all entries that have been inactive too long
    synchronized(timer) {
      Iterator i = timer.entrySet().iterator();
      while (i.hasNext()) {
        Map.Entry e = (Map.Entry) i.next();
        long ticketTime = ((Date) e.getValue()).getTime();
        // delete the ticket if it's expired
        if (now - (tolerance * 1000) >= ticketTime) {
          i.remove();
          deleteTicket((String) e.getKey());
        }
      }
    }
  }
}
