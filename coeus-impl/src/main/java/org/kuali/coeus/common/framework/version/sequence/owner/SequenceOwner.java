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
package org.kuali.coeus.common.framework.version.sequence.owner;

import org.kuali.coeus.common.framework.version.sequence.associate.SequenceAssociate;

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

    String getVersionNameFieldValue();

}
