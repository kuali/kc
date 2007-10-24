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

/**
 * <p>A class housing some utility functions related to String manipulation.</p>
 * <p>Copyright 2000, Shawn Bayern.</p>
 */
public class StringUtil {

  /**
   * Replaces all occurrences of an old String with a new String in
   * the given String.
   */
  public static String substituteAll(String s, String o, String n) {
    if (s == null)
      return null;
    while (s.indexOf(o) != -1)
      s = substituteOne(s, o, n);
    return s;
  }

  /**
   * Replaces one occurrence of an old String with a new String in
   * the given String.
   */
  public static String substituteOne(String s, String o, String n) {
    if (s == null)
      return null;
    int begin = s.indexOf(o);
    if (begin == -1)
      return s;
    int end = begin + o.length();
    return (new StringBuffer(s)).replace(begin, end, n).toString();
  }
}
