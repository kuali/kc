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
package org.kuali.coeus.common.framework.version.sequence.associate;

import org.kuali.coeus.common.framework.version.sequence.Sequenceable;
import org.kuali.coeus.common.framework.version.sequence.owner.SequenceOwner;

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
