/*
 * Copyright 2006-2009 The Kuali Foundation
 * 
 * Licensed under the Educational Community License, Version 2.0 (the "License");
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
package org.kuali.kra.service;

/**
 * KC Pessimistic Lock Service.
 */
public interface KcPessimisticLockService {
    
    /**
     * Clear expired locks.  An expired lock is defined as a lock
     * that older than a given amount of time, e.g. 24 hours.
     */
    public void clearExpiredLocks();
}
