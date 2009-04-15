/*
 * Copyright 2006-2009 The Kuali Foundation.
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
package org.kuali.kra.logging;

/**
 * Interface to define a class as permitting Log Traces
 * 
 */
public interface Traceable<T> {
        
    /**
     * Rely on the class to know how to build its own proxy for trace logging
     * 
     * @param archetype instance applied to the proxy. 
     * @return a proxy for <code>T</code> with <code>archetype</code> set
     */
    T getProxy(T archetype);
}
