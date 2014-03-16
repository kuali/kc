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
package org.kuali.coeus.common.framework.sequence.associate;

import org.kuali.coeus.common.framework.sequence.Sequenceable;
import org.kuali.coeus.common.framework.sequence.owner.SequenceOwner;

/**
 * This interface applies to classes that are associated 
 * with a sequence owner; i.e. child BOs of an Award or Protocol
 * 
 * SequenceAssociates must override equals and hashCode!
 * @param <T> the type of sequence owner which itself has an owner of unknown type
 */
public interface SequenceAssociate<T extends SequenceOwner<?>> extends Sequenceable {
    /**
     * This sets the sequence owner on the associate. 
     * Should no-op if this associate has no sequence owner 
     * @param newlyVersionedOwner
     */
    void setSequenceOwner(T newlyVersionedOwner);
    
    /**
     * The SequenceOwner is returned; "this" should be returned if this associate is the sequence owner.
     * @return the SequenceOwner
     */
    T getSequenceOwner();
}
