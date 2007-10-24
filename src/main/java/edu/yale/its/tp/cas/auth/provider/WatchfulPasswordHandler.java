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
package edu.yale.its.tp.cas.auth.provider;

import java.util.*;
import edu.yale.its.tp.cas.auth.*;

/**
 * A PasswordHandler base class that implements logic to block IP addresses
 * that engage in too many unsuccessful login attempts.  The goal is to
 * limit the damage that a dictionary-based password attack can achieve.
 * We implement this with a token-based strategy; failures are regularly
 * forgotten, and only build up when they occur faster than expiry.
 */
public abstract class WatchfulPasswordHandler implements PasswordHandler {

  //*********************************************************************
  // KFSConstants

  /**
   * The number of failed login attempts to allow before locking out
   * the source IP address.  (Note that failed login attempts "expire"
   * regularly.)
   */
  private static final int FAILURE_THRESHOLD = 100;

  /**
   * The interval to wait before expiring recorded failure attempts.
   */
  private static final int FAILURE_TIMEOUT = 60;


  //*********************************************************************
  // Private state

  /** Map of offenders to the number of their offenses. */
  private static Map offenders = new HashMap();

  /** Thread to manage offenders. */
  private static Thread offenderThread = new Thread() {
    public void run() {
      try {
        while (true) {
          Thread.sleep(FAILURE_TIMEOUT * 1000);
          expireFailures();
        }
      } catch (InterruptedException ex) {
        // ignore
      }
    }
  };

   static {
     offenderThread.setDaemon(true);
     offenderThread.start();
   }

  //*********************************************************************
  // Gating logic

  /**
   * Returns true if the given request comes from an IP address whose
   * allotment of failed login attemps is within reasonable bounds; 
   * false otherwise.  Note:  We don't actually validate the user
   * and password; this functionality must be implemented by subclasses.
   */
  public synchronized boolean authenticate(javax.servlet.ServletRequest request,
                              String netid,
                              String password) {
    return (getFailures(request.getRemoteAddr()) < FAILURE_THRESHOLD);
  }

  /** Registers a login failure initiated by the given address. */
  protected synchronized void registerFailure(javax.servlet.ServletRequest r) {
    String address = r.getRemoteAddr();
    offenders.put(address, new Integer(getFailures(address) + 1));
  }

  /** Returns the number of "active" failures for the given address. */
  private synchronized static int getFailures(String address) {
    Object o = offenders.get(address);
    if (o == null)
      return 0;
    else
      return ((Integer) o).intValue();
  }

  /**
   * Removes one failure record from each offender; if any offender's
   * resulting total is zero, remove it from the list.
   */
  private synchronized static void expireFailures() {
    // scoop up addresses from Map so as to avoid modifying the Map in-place
;    Set keys = offenders.keySet();
    Iterator ki = keys.iterator();
    List l = new ArrayList();
    while (ki.hasNext())
      l.add(ki.next());

    // now, decrement and prune as appropriate
    for (int i = 0; i < l.size(); i++) {
      String address = (String) l.get(i);
      int failures = getFailures(address) - 1;
      if (failures > 0)
        offenders.put(address, new Integer(failures));
      else
        offenders.remove(address);
    }
  }

}
