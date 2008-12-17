/*
 * Copyright 2006-2008 The Kuali Foundation
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
package org.kuali.kra.award.bo;

/**
 * This interface should be used on any BO that is sequenceable
 */
public interface Sequenceable {
    
    /**
     * This method tells the BO to perform its sequence behavior. Owner would be null for top level BOs
     * @param owner
     * @return The newly sequenced object is returned 
     */
    Sequenceable sequence(Sequenceable owner);
    
    /** 
     * @return
     */
    Integer getSequenceNumber();
    
    /**
     * @param sequenceNumber
     */
    void setSequenceNumber(Integer sequenceNumber);
}
