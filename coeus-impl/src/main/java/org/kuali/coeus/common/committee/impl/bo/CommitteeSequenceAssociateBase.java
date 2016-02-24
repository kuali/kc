/*
 * Kuali Coeus, a comprehensive research administration system for higher education.
 * 
 * Copyright 2005-2016 Kuali, Inc.
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package org.kuali.coeus.common.committee.impl.bo;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;
import org.kuali.coeus.sys.framework.model.KcPersistableBusinessObjectBase;

public abstract class CommitteeSequenceAssociateBase extends KcPersistableBusinessObjectBase implements SequenceAssociate<CommitteeBase<?, ?, ?>> {

    private static final long serialVersionUID = -9040705064557493297L;

    private CommitteeBase sequenceOwner;

    public CommitteeBase getSequenceOwner() {
        return this.sequenceOwner;
    }

    public void setSequenceOwner(CommitteeBase newOwner) {
        this.sequenceOwner = newOwner;
    }

    public Integer getSequenceNumber() {
        return this.sequenceOwner.getSequenceNumber();
    }

    public abstract boolean equals(Object obj);
    
    public abstract int hashCode();
    
    public abstract void resetPersistenceState();

}
