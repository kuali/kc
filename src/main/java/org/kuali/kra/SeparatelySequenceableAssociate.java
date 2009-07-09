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
package org.kuali.kra;

import java.util.List;

/**
 * This interface specifies behavior for sequence associates that should not be 
 * deep copied when the SequenceOwner is versioned; i.e. attachment BOs
 * @param <T> the type of sequence owner of the associate
 */
public interface SeparatelySequenceableAssociate<T extends SequenceOwner> extends Sequenceable {
    /**
     * This sets the sequence owners on the associate; should no-op this associate is the sequence owner.
     * @param owners
     */
    void setSequenceOwners(List<T> owners);
    
    /**
     * @return The SequenceOwner is returned; "this" should be returned if this associate is the sequence owner
     */
    List<T> getSequenceOwners();
    
    /**
     * This method convenience method returning last owner in list of owners; null if none.
     * @return
     */
    T getLatestOwner();
}
