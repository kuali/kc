/*
 * Copyright 2005-2014 The Kuali Foundation
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
package org.kuali.coeus.common.framework.sequence.owner;

import org.kuali.coeus.common.framework.sequence.associate.SequenceAssociate;

/**
 * This interface applies to classes that own a sequence number.
 * @param <T> the type of sequence owner which itself has an owner of unknown type
 */
public interface SequenceOwner<T extends SequenceOwner<?>> extends SequenceAssociate<T> {
    /**
     * This increments sequence number on an owner of a sequence.
     */
    void incrementSequenceNumber();
    
    /**
     * This method returns its owner's sequence number. If this is the top level owner; i.e. Protocol or Award, it should return null
     * @return the owner's sequence number
     */
    Integer getOwnerSequenceNumber();
    
    /**
     * For Award, this would be "awardNumber", for Committee "committeeName", for Protocol "protocolNumber", etc.
     * @return the field name that versions are "grouped" by
     */
    String getVersionNameField();

}
