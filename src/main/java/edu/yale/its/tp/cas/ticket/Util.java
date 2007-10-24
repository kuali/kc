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

import java.security.SecureRandom;

/**
 * Some static utility methods.
 */
public class Util {

  private static int TRANSACTION_ID_LENGTH = 32;

  /** Returns a printable String corresponding to a byte array. */
  public static synchronized String toPrintable(byte[] b) {
    final char[] alphabet = ("abcdefghijklmnopqrstuvwxyz"
      + "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
      + "1234567890").toCharArray();
    char[] out = new char[b.length];
    for (int i = 0; i < b.length; i++) {
      int index = b[i] % alphabet.length;
        if (index < 0)
          index += alphabet.length;
        out[i] = alphabet[index];
      }
    return new String(out);
  }

  public static String getTransactionId() {
    // produce the random transaction ID
    byte[] b = new byte[TRANSACTION_ID_LENGTH];
    SecureRandom sr = new SecureRandom();
    sr.nextBytes(b);
    return Util.toPrintable(b);
  }
}
