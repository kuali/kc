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
package org.kuali.kra.committee.bo;

import org.kuali.kra.SequenceAssociate;
import org.kuali.kra.bo.KraPersistableBusinessObjectBase;

public abstract class CommitteeSequenceAssociate extends KraPersistableBusinessObjectBase implements SequenceAssociate<Committee> {

    private static final long serialVersionUID = -9040705064557493297L;

    private Committee sequenceOwner;

    public Committee getSequenceOwner() {
        return this.sequenceOwner;
    }

    public void setSequenceOwner(Committee newOwner) {
        this.sequenceOwner = newOwner;
    }

    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    public abstract boolean equals(Object obj);
    
    public abstract int hashCode();
    
    public abstract void resetPersistenceState();

}
